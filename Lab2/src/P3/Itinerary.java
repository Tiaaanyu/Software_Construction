package P3;

import java.net.PasswordAuthentication;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Itinerary {
	private String instructions = new String();
	private int startTime;
	private int endTime;
	private Stop startLocation;
	private Stop endLocation;
	private List<Stop> path = new ArrayList<>();
    public Itinerary(List<Stop> Path) {
    	path = Path;
    	startLocation = Path.get(Path.size() - 2);
    	endLocation = Path.get(1);
    	startTime = Path.get(Path.size() - 1).getTime();
    	endTime = endLocation.getTime();
    }
    public int getStartTime() {
    	return startTime;
    }
    public int getEndTime() {
    	return endTime;
    }
    public int getWaitTime() {
		return endTime - startTime;
	}
    public Stop getStartLocation() {
    	return startLocation;
    }
    public Stop getEndLocation() {
    	return endLocation;
    }
	public String getInstructions() {
		instructions += ("You start at " + startLocation.getName() + " at " + path.get(path.size() - 1).getTime() + '\n');
		instructions += ("At first, you take " + startLocation.getRouteName() + " at " + startLocation.getName() + " at " + startLocation.getTime() + '\n');
		for(int i = path.size() - 3; i >= 1; --i) {
			if(path.get(i).getName().equals(path.get(i + 1).getName()) 
					&& path.get(i).getRouteName().equals(path.get(i + 1).getRouteName())) {
				continue;
			}
			else if(path.get(i).getName().equals(path.get(i + 1).getName()) 
					&& !path.get(i).getRouteName().equals(path.get(i + 1).getRouteName())) {
				instructions += ("Change route here, wait for " + (path.get(i).getTime() - path.get(i + 1).getTime()) + '\n');
				instructions += ("take " + path.get(i).getRouteName() + '\n');
			}
			else {
				instructions += ("take " + path.get(i).getRouteName() + " arrive at " + path.get(i).getName() + " at " + path.get(i).getTime() +'\n');
			}
		}
		instructions += ("You arrive at the destination at " + endLocation.getTime() +", totally spend " + (endTime - startTime));
		return instructions;
	}
}
