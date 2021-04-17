package v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ladder {
	private final String name;
	private final int rung;
	private final List<Monkey> rungs = Collections.synchronizedList(new ArrayList<>());
	
	//Rep Invariant
	//		assert rung >= 1
	//		name != null;
	//Abstraction Function
	//		represent the ladder.
	//Safety form rep exposure
	//		name, rung, rungs are all private and final.
	//Thread safety argument
	//		all accesses to rungs are protected by the Ladder lock.
	/**
	 * the constructor of class Ladder
	 * @param name, the name.
	 * @param rung, requires >= 1.
	 */
	public Ladder(String name, int rung) {
		this.name = name;
		this.rung = rung;
		for(int i = 0; i < rung; ++i)
			rungs.add(null);
	}
	
	/**
	 * get the length of the ladder.
	 * @return the length.
	 */
	public int getLength() {
		return rung;
	}
	
	/**
	 * get the name of the ladder.
	 * @return the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * return the monkey and location.
	 * @return rungs.
	 */
	public List<Monkey> getRungs() {
		return rungs;
	}
	
	/**
	 * test the ladder is empty or not.
	 * @return true if there is no monkey on it, otherwise false.
	 */
	public synchronized boolean empty() {
		for(Monkey monkey: rungs) {
			if (monkey != null) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * set a monkey on the ladder.
	 * @param location, the location we hope the monkey at.
	 * @param m, the monkey.
	 * @return true if we can set the monkey on the ladder successfully, otherwise false.
	 */
	public synchronized boolean setMonkey(int location, Monkey m) {
		if(location < rung) {
				rungs.set(location, m);
				return true;
		}
		else return false;
	}
	
	/**
	 * get the total number of the monkeys on the ladder.
	 * @return the total number.
	 */
	public synchronized int getTotalNumber() {
		int cnt = 0;
		for(Monkey monkey: rungs) {
			if (monkey != null) {
				++cnt;
			}
		}
		return cnt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
