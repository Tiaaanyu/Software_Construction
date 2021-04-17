package v1;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class Monkey implements Runnable{
	
	//Abstraction Function
	//		represent the monkey.
	//Rep Invariant
	//		assert name >= 1.
	//		direction can only be "L->R" or "R->L".
	//    assert bornTime >= 0.
	//Safety from exposure
	//		all fields are private and final.
	//Thread safety argument
	//		when the monkey modify the ladder, use the lock, so all operations
	//    are protected by the lock.
	
	
	private final String direction;
	private final int name;
	private final int bornTime;
	private int speed;
	private int speedTime;
	private int location;
	private Ladder ladder;
	private List<Ladder> allLadders;
	private Logger logger;
	private CountDownLatch doneSignal;
	
	/**
	 * the constructor of the class Monkey.
	 * @param direction, the direction of the movement, which can only be "L->R" or "R->L".  
	 * @param name, the name of the monkey, which must be an Integer.
	 * @param bornTime, the born time of the monkey.
	 * @param speed, the speed of the monkey, requires 1 <= speed <= MV. 
	 * @param allLadders, requires >= 1.
	 */
	public Monkey(String direction, int name, int bornTime, int speed, List<Ladder> allLadders) {
		this.direction = direction;
		this.name = name;
		this.bornTime = bornTime;
		this.speed = speed;
		this.allLadders = allLadders;
	}
	
	/**
	 * get the name of the monkey.
	 * @return the name.
	 */
	public int getName() {
		return name;
	}
	
	/**
	 * get the speed of the monkey.
	 * @return the speed.
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * get the direction of the monkey.
	 * @return the direction.
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * get the born time of the monkey.
	 * @return the bornTime.
	 */
	public int getBornTime() {
		return bornTime;
	}
	
	/**
	 * get the time from the monkey born to leaving the ladder. 
	 * @return the speedTime.
	 */
	public int getTime() {
		return speedTime;
	}
	
	/**
	 * get the time from the program beginning to the monkey leave the ladder.
	 * @return the total time.
	 */
	public int getTotalTime() {
		return bornTime + speedTime;
	}
	
	/**
	 * set the logging class.
	 * @param logger
	 */
	public void setLog(Logger logger) {
		this.logger = logger;
	}
	
	/**
	 * count the number of the monkeys which have already leave the ladder.
	 * @param doneSignal
	 */
	public void setCountDownLaunch(CountDownLatch doneSignal) {
		this.doneSignal = doneSignal;
	}
	
	@Override
	public void run() {
		int time = 0;
		String strategyName = new String();
		Strategy strategy = null;
		Random random = new Random();
		int slection = random.nextInt(3);
		//int slection = 2;
		if(slection == 0) {
			strategyName = "Wait for the empty ladder.";
			strategy = new WaitStrategy();
		} else if(slection == 1) {
			strategyName = "Same direction ladder.";
			strategy = new SameDirectionStrategy();
		}
		else {
			strategyName = "Same direction and least monkeys ladder.";
			strategy = new MininumStrategy();
		}
		while(ladder == null) {
			synchronized (this) {
				ladder = strategy.selecter(allLadders, direction);
				if(ladder == null) {
					try {
						Thread.sleep(1000);
						++time;
						System.out.println("name: " + name + ", wait time: " + time + 
								", direction: " + direction + ", strategy: " + strategyName);
						logger.info("name: " + name + ", wait time: " + time + 
								", direction: " + direction + ", strategy: " + strategyName);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					synchronized (ladder) {
						if(direction.equals("L->R"))
							ladder.setMonkey(0, this);
						else ladder.setMonkey(ladder.getLength() - 1, this);
					}
				}
			}
		}
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		if (direction.equals("L->R")) {
			synchronized (ladder) {
				ladder.setMonkey(0, this);
			}
			location = 0;
			while(location < ladder.getLength()) {
				++time;
				synchronized (ladder) {
					List<Monkey> rungs = ladder.getRungs();
					for(int i = location + 1; i < rungs.size(); ++i) {
						if(rungs.get(i) != null && rungs.get(i).getSpeed() < speed) {
							synchronized (this) {
								speed = rungs.get(i).getSpeed();
							}
							break;
						}
					}
					if(location + speed < ladder.getLength()) {
						ladder.setMonkey(location, null);
						ladder.setMonkey(location + speed, this);
						location += speed;
					} else {
						ladder.setMonkey(location, null);
						 String message = "name: " + name + ", direction: "
	                + direction + ", spend time: " + time + ", strategy: "
	                + strategyName;
	            System.out.println(message);
	            logger.info(message);
	            this.speedTime = time;
	            doneSignal.countDown();
	            break;
					}
				}
				String message = "time: " + time + ", name: " + name
            + ", ladder: " + ladder.getName() + ", direction: "
            + direction + ", speed: " + speed + ", location: "
            + location + ", strategy: " + strategyName;
        System.out.println(message);
        logger.info(message);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
			}
		} else if(direction.equals("R->L")) {
			synchronized (ladder) {
				ladder.setMonkey(ladder.getLength() - 1, this);
				location = ladder.getLength() - 1;
			}
			while(location > 0) {
				++time;
				synchronized (ladder) {
					List<Monkey> rungs = ladder.getRungs();
					for(int i = location - 1; i >= 0; --i) {
						if(rungs.get(i) != null && rungs.get(i).getSpeed() < speed) {
							synchronized (this) {
								speed = rungs.get(i).getSpeed();
							}
							break;
						}
					}
					if(location - speed > 0) {
						ladder.setMonkey(location, null);
						ladder.setMonkey(location - speed, this);
						location -= speed;
					} else {
						ladder.setMonkey(location, null);
            String message = "name: " + name + ", direction: "
                + direction + ", spend time: " + time + ", strategy: "
                + strategyName;
            System.out.println(message);
            logger.info(message);
            this.speedTime = time;
            doneSignal.countDown();
            break;
					}
				}
				String message = "time: " + time + ", name: " + name
            + ", ladder: " + ladder.getName() + ", direction: "
            + direction + ", speed: " + speed + ", location: "
            + location + ", strategy: " + strategyName;
        System.out.println(message);
        logger.info(message);
        try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
