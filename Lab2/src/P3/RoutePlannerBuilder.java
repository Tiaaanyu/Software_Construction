package P3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NameList;

import P1.graph.ConcreteVerticesGraph;
import P1.graph.Graph;
class Route {
	private String name;
	private int firstTime;
	public Route(String name, int firstTime) {
		this.name = name;
		this.firstTime = firstTime;
	}
	public String getName() {
		return name;
	}
	public int getFirstTime() {
		return firstTime;
	}
	
}
public class RoutePlannerBuilder {
	public RoutePlanner build(String filename, int maxWaitLimit) {
		//List<Stop> stopsInTheFile = new ArrayList<>();
		Map<Route, List<Stop>> allRoutes = new HashMap<>();
		String routeName = new String();
		File Filename = new File(filename);
		Route route = new Route(" ", 0);
		try {                                        //read all routes in the csv file into the Map "allRoutes"
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String myline = "";
			while((myline = br.readLine()) != null) {
				
				String[] oneLineInTheFile = myline.split(",");
				if(oneLineInTheFile.length == 2) {
					//stopsInTheFile.clear();
					List<Stop> stopsInTheFile = new ArrayList<>();
					routeName = oneLineInTheFile[0];
					route = new Route(routeName, Integer.valueOf(oneLineInTheFile[1]));
					allRoutes.put(route, stopsInTheFile);
				}
				else if(oneLineInTheFile.length == 4) {
					Stop oneStop = new Stop(Double.valueOf(oneLineInTheFile[1]), Double.valueOf(oneLineInTheFile[2]), 
							oneLineInTheFile[0], Integer.valueOf(oneLineInTheFile[3]));
					allRoutes.get(route).add(oneStop);
				}
		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Route r: allRoutes.keySet()) {
			for(Stop x: allRoutes.get(r)) {
				x.setRouteName(r.getName());
			}
		}
		return new RoutePlanner(allRoutes, maxWaitLimit);
	}
}
