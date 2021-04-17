package v1;

import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

public class WaitStrategy implements Strategy {
	@Override
	public Ladder selecter(List<Ladder> ladders, String direction) {
		synchronized (ladders) {
			for(int i = 0; i < ladders.size(); ++i) {
				if(ladders.get(i).empty())
					return ladders.get(i);
			}
		}
		return null;
	}
}
