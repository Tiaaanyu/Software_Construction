package v1;

import java.util.ArrayList;
import java.util.List;

public class MininumStrategy implements Strategy {
	@Override
	public Ladder selecter(List<Ladder> ladders, String direction) {
		synchronized (ladders) {
			for(int i = 0; i < ladders.size(); ++i) {
				if(ladders.get(i).empty())
					return ladders.get(i);
			}
			List<Ladder> list = new ArrayList<>();
			for(int i = 0; i < ladders.size(); ++i) {
				List<Monkey> rungs = ladders.get(i).getRungs();
				for(int j = 0; j < rungs.size(); ++j) {
					if(rungs.get(j) != null && rungs.get(j).getDirection().equals(direction)) {
						if(direction.equals("L->R") && rungs.get(0) == null) {
							list.add(ladders.get(i));
						}
						if(direction.equals("R->L") && rungs.get(ladders.get(i).getLength() - 1) == null) {
							list.add(ladders.get(i));
						}
					}
				}
			}
			Ladder l = null;
			int num = 1000;
			for(Ladder ladder: list) {
				if(ladder.getTotalNumber() < num) {
					num = ladder.getTotalNumber();
					l = ladder;
				}
			}
			return l;
		}
	}
}
