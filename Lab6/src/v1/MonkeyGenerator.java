package v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;

public class MonkeyGenerator implements Runnable {
	private final int timeInterval;
	private final int k;
	private final int maxNum;
	private final int maxSpeed;
	private List<Monkey> monkeys = Collections.synchronizedList(new ArrayList<>());
	private List<Ladder> ladders;
	private Logger logger;
	
	//Abstraction Function
	//		represents the monkey generator.
	//Rep Invariant
	//		assert the timeInterval, k, maxNum and maxSpeed > 0.
	//Safety from rep exposure
	//		all fields are private and final.
	//Thread safety argument
	//		all operations are protected by the lock.
	
	/**
	 * the constructor of the class MonkeyGenerator.
	 * @param timeInterval
	 * @param k
	 * @param maxNum
	 * @param maxSpeed
	 */
	public MonkeyGenerator(int timeInterval, int k, int maxNum, int maxSpeed) {
		this.timeInterval = timeInterval;
		this.k = k;
		this.maxNum = maxNum;
		this.maxSpeed = maxSpeed;
	}
	
	/**
	 * set the ladders.
	 * @param ladders
	 */
	public void setLadders(List<Ladder> ladders) {
		this.ladders = ladders;
	}
	
	/**
	 * set the logger.
	 * @param logger
	 */
	public void setLog(Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public void run() {
		int num = 0;
		int bornTime = 0;
		Random random = new Random();
		CountDownLatch doneSignal = new CountDownLatch(maxNum);
		while (num + k <= maxNum) {
			for(int i = 0; i < k; ++i) {
				++num;
				int speed = random.nextInt(maxSpeed) + 1;
				String direction = speed % 2 == 0 ? "R->L" : "L->R";
				Monkey monkey = new Monkey(direction, num, bornTime, speed, ladders);
				monkey.setLog(logger);
				monkey.setCountDownLaunch(doneSignal);
				monkeys.add(monkey);
				(new Thread(monkey)).start();
			}
			try {
				bornTime += timeInterval;
				Thread.sleep(timeInterval * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			int size = maxNum - num;
			for(int j = 0; j < size; ++j) {
				++num;
				int speed = random.nextInt(maxSpeed) + 1;
				String direction = speed % 2 == 0 ? "R->L" : "L->R";
				Monkey monkey = new Monkey(direction, num, bornTime, speed, ladders);
				monkey.setLog(logger);
				monkey.setCountDownLaunch(doneSignal);
				monkeys.add(monkey);
				(new Thread(monkey)).start();
			}
			try {
				doneSignal.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
			double totalTime = 0;
			for(Monkey monkey: monkeys) {
				if(monkey.getTotalTime() > totalTime) {
					totalTime = monkey.getTotalTime();
				}
			}
			logger.info("total time: " + totalTime);
			logger.info("Throughput rate: " + maxNum / totalTime);
			int fair = 0;
			for(int i = 0; i < monkeys.size() - 1; i++) {
	      for(int j = i + 1; j < monkeys.size(); j++) {
	        if((monkeys.get(j).getTime() - monkeys.get(i).getTime()) * (monkeys.get(j).getBornTime() - monkeys.get(i).getBornTime()) >= 0) {
	          fair += 1;
	        } else {
	          fair -=1;
	        }
	      }
	    }
			int x = maxNum 	* (maxNum - 1) / 2;
			logger.info("fairness: " + (double)fair / x); 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
