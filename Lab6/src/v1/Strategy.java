package v1;

import java.util.List;

public interface Strategy {
	/**
	 * according different strategies, select the ladder in all ladders.
	 * @param ladders, all ladders waiting to be chosen
	 * @param direction, the direction that monkey moves, which can only be "L->R" or "R->L".
	 * @return a ladder chosen according to the strategy.
	 */
	public abstract Ladder selecter(List<Ladder> ladders, String direction);
}
