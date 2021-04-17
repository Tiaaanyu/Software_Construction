package P3;

import java.awt.geom.RoundRectangle2D;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.junit.experimental.theories.FromDataPoints;
import org.junit.runner.Computer;

import P1.graph.ConcreteVerticesGraph;
import P1.graph.Graph;
//  -Abstraction Function:
//     trafficMapFromTheFile is a map which stores the information read from the file
//     maxWaitTime is the wait time limit, which should be 1200 according the experiment manual
//  -Representation invariant:
//     trafficMapFromTheFile is a non-null map
//  -Safety from rep exposure
//     must make defensive copies to avoid sharing code with client.
public class RoutePlanner {
	
	TrafficGraph trafficGraph = new TrafficGraph();
	private int i, j;
	private int maxWaitTime;
	private Map<Route, List<Stop>> trafficMapFromTheFile = new HashMap<>();
	private boolean involve(Stop st, List<Stop> ls) {
		for(Stop x: ls) {
			if(x.getName().contains(st.getName()))
				return true;
		}
		return false;
	}
	/**
	 * create graph from the file
	 */
	public void createGraph() {
		List<String> stopNameList = new ArrayList<>();   //统计所有车站名字 便于建立虚点
		for(Route route: trafficMapFromTheFile.keySet()) {
			for(i = 0; i < trafficMapFromTheFile.get(route).size(); ++i) {
				if(!stopNameList.contains(trafficMapFromTheFile.get(route).get(i).getName()) && 
					stopNameList.add(trafficMapFromTheFile.get(route).get(i).getName()));
			}
		}
		
		for(Route rt: trafficMapFromTheFile.keySet()) {         //每一条路线先连成一条边
			for(i = 0; i < trafficMapFromTheFile.get(rt).size(); ++i) {
				
					trafficGraph.add(trafficMapFromTheFile.get(rt).get(i));
					if(i > 0) {
						trafficGraph.set(trafficMapFromTheFile.get(rt).get(i - 1), trafficMapFromTheFile.get(rt).get(i), 
						trafficMapFromTheFile.get(rt).get(i).getTime() - trafficMapFromTheFile.get(rt).get(i - 1).getTime());
					}
					
			}
		}
		for(String x: stopNameList) {
			List<Stop>sameNameStop = new ArrayList<>();//找出同一站名的所有stop
			for(Stop s: trafficGraph.getVertices()) {
				if(s.getName().equals(x) && s.virtualNumber == 0) {
					sameNameStop.add(s);
				}
			}
			for(i = 0; i < sameNameStop.size(); ++i) {   //两两枚举 建虚点加边
				for(j = 0; j < sameNameStop.size(); ++j) {
					
					if(i == j || sameNameStop.get(i).getRouteName().equals(sameNameStop.get(j).getRouteName()) ||
							(sameNameStop.get(i).getTime() - sameNameStop.get(j).getTime()) < 0) {
						continue;
					}
					else if(sameNameStop.get(i).getTime() - sameNameStop.get(j).getTime() >= 0 && 
							sameNameStop.get(i).getTime() - sameNameStop.get(j).getTime() <= maxWaitTime)
					{
						if(sameNameStop.get(i).flag == false && sameNameStop.get(j).flag == false) {             //开始建虚点
							Stop virtual_0 = new Stop(sameNameStop.get(i).getLatitude(), sameNameStop.get(i).getLongitude(), 
									sameNameStop.get(i).getName(), sameNameStop.get(i).getTime());
							sameNameStop.get(i).flag = true;
							virtual_0.flag = true;
							virtual_0.virtualNumber = 1;
							virtual_0.setRouteName(sameNameStop.get(i).getRouteName());
							sameNameStop.get(i).virtualStop = virtual_0;
							virtual_0.virtualStop = sameNameStop.get(i);
							trafficGraph.add(virtual_0);
							for(Stop s: sameNameStop.get(i).friends.keySet()) {
								trafficGraph.set(virtual_0, s, sameNameStop.get(i).friends.get(s).intValue());
							}
							sameNameStop.get(i).friends.clear();
							trafficGraph.set(sameNameStop.get(i), virtual_0, 0);
							Stop virtual_1 = new Stop(sameNameStop.get(j).getLatitude(), sameNameStop.get(j).getLongitude(), 
									sameNameStop.get(j).getName(), sameNameStop.get(j).getTime());
							sameNameStop.get(j).flag = true;
							virtual_1.flag = true;
							virtual_1.virtualNumber = 1;
							virtual_1.setRouteName(sameNameStop.get(j).getRouteName());
							sameNameStop.get(j).virtualStop = virtual_1;
							virtual_1.virtualStop = sameNameStop.get(j);
							trafficGraph.add(virtual_1);
							for(Stop s: sameNameStop.get(j).friends.keySet()) {
								trafficGraph.set(virtual_1, s, sameNameStop.get(j).friends.get(s).intValue());
							}
							sameNameStop.get(j).friends.clear();
							trafficGraph.set(sameNameStop.get(j), virtual_1, 0);
						}
						else if(sameNameStop.get(j).flag == false && sameNameStop.get(i).flag == true) {
							Stop virtual_1 = new Stop(sameNameStop.get(j).getLatitude(), sameNameStop.get(j).getLongitude(), 
									sameNameStop.get(j).getName(), sameNameStop.get(j).getTime());
							sameNameStop.get(j).flag = true;
							virtual_1.flag = true;
							virtual_1.virtualNumber = 1;
							virtual_1.setRouteName(sameNameStop.get(j).getRouteName());
							sameNameStop.get(j).virtualStop = virtual_1;
							virtual_1.virtualStop = sameNameStop.get(j);
							trafficGraph.add(virtual_1);
							for(Stop s: sameNameStop.get(j).friends.keySet()) {
								trafficGraph.set(virtual_1, s, sameNameStop.get(j).friends.get(s).intValue());
							}
							sameNameStop.get(j).friends.clear();
							trafficGraph.set(sameNameStop.get(j), virtual_1, 0);
						}
						else if(sameNameStop.get(i).flag == false && sameNameStop.get(j).flag == true) {
							Stop virtual_0 = new Stop(sameNameStop.get(i).getLatitude(), sameNameStop.get(i).getLongitude(), 
									sameNameStop.get(i).getName(), sameNameStop.get(i).getTime());
							sameNameStop.get(i).flag = true;
							virtual_0.flag = true;
							virtual_0.virtualNumber = 1;
							virtual_0.setRouteName(sameNameStop.get(i).getRouteName());
							sameNameStop.get(i).virtualStop = virtual_0;
							virtual_0.virtualStop = sameNameStop.get(i);
							trafficGraph.add(virtual_0);
							for(Stop s: sameNameStop.get(i).friends.keySet()) {
								trafficGraph.set(virtual_0, s, sameNameStop.get(i).friends.get(s).intValue());
							}
							sameNameStop.get(i).friends.clear();
							trafficGraph.set(sameNameStop.get(i), virtual_0, 0);
						}
						else continue;
						trafficGraph.set(sameNameStop.get(j), sameNameStop.get(i).virtualStop, 
								sameNameStop.get(i).getTime() - sameNameStop.get(j).getTime());
					}
				}
			}
			sameNameStop.clear();
		}

	}
	
	
	public RoutePlanner(Map<Route, List<Stop>> m, int maxWaitTime) {
		trafficMapFromTheFile = m;
		this.maxWaitTime = maxWaitTime;
	}
	/**
	 * find all stops which name contains some special String search
	 * @param search is the special String
	 * @return  a list which contains all the qualified stops.
	 */
	public List<Stop> findStopsBySubstring(String search) {
		List<Stop> allStopsContainsSubstring = new ArrayList<>();
		Iterator<Map.Entry<Route, List<Stop>>> it = trafficMapFromTheFile.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<P3.Route, java.util.List<P3.Stop>> entry = (Map.Entry<P3.Route, java.util.List<P3.Stop>>) it
					.next();
			for(Stop st: entry.getValue()) {
				if(st.getName().contains(search) && !involve(st, allStopsContainsSubstring))
					allStopsContainsSubstring.add(st);
			}
		}
		return allStopsContainsSubstring;
	}
	/**
	 * compute the shortest route between the two stops
	 * @param src  source stop
	 * @param dest end stop
	 * @param time is the leaving time
	 * @return an Itinerary object which represents the itinerary.
	 */
	public Itinerary computeRoute(Stop src, Stop dest, int time) {
		List<Integer> cursor = new ArrayList<>();
		List<Stop> vertices = new ArrayList<>();
		for(int i = 0; i < trafficGraph.getVertices().size(); ++i) {
			if(trafficGraph.getVertices().get(i).getTime() < time)
				cursor.add(i);
		}
		for(int i = 0; i < trafficGraph.getVertices().size(); ++i) {
			if(!cursor.contains(i))
				vertices.add(trafficGraph.getVertices().get(i));
		}
		trafficGraph.getVertices().clear();
		for(Stop x: vertices)
			trafficGraph.getVertices().add(x);
		Stop source = new Stop(src.getLatitude(), src.getLongitude(), src.getName() + "source", time);
		Stop target = new Stop(dest.getLatitude(), dest.getLongitude(), dest.getName() + "target", dest.getTime());
		trafficGraph.add(source);
		trafficGraph.add(target);
		for(Stop st: trafficGraph.getVertices()) {
			if(st.getName().equals(src.getName()) && st.virtualNumber == 0) {
				if(st.getTime() - time >= 0 && st.getTime() - time <= maxWaitTime) {
					trafficGraph.set(source, st, st.getTime() - time);
					st.setForward(source.getName(), time, src.getRouteName());
				}
				  
			}
			else if(st.getName().equals(dest.getName())) {
				trafficGraph.set(st, target, 0);
			}
		}
		for(Stop st: source.friends.keySet()) {
			st.setDist(source.friends.get(st).intValue());
		}
		source.setDist(0);
		source.visit = true;
		for(Stop st: trafficGraph.getVertices()) {
			int min = Integer.MAX_VALUE;
			Stop minStop = source;
			for(Stop s: trafficGraph.getVertices()) {  //找到距离source最近的且没确定最短路的点
			if(s.getDist() < min && !s.visit) { 
				min = s.getDist();
				minStop = s;
			}
		}
			minStop.visit = true;
			if(minStop.getName().equals(source.getName()))
				minStop.setForward(source.getName(), source.getTime(), source.getRouteName());
			for(Stop s: trafficGraph.getVertices()) {
				if(s.visit == false) {      //用minStop去松弛和他相邻的点
					if(minStop.friends.containsKey(s)) {
						if(minStop.getDist() + minStop.friends.get(s).intValue() < s.getDist()) {
							s.setDist(minStop.getDist() + minStop.friends.get(s).intValue());
							s.setForward(minStop.getName(), minStop.getTime(), minStop.getRouteName());
						}
					}
				}
				else continue;
			}
			
		}
		List<Stop> path = new ArrayList<>();
		Stop stop = target;
		path.add(stop);
		while(stop.getTime() != time) {
			for(int i = 0; i < trafficGraph.getVertices().size(); ++i) {
				if(trafficGraph.getVertices().get(i).getName().equals(stop.getForward().getName())
						&& trafficGraph.getVertices().get(i).getTime() == stop.getForward().getTime()
						&& trafficGraph.getVertices().get(i).getRouteName().equals(stop.getForward().getRouteName()))
				{
					path.add(trafficGraph.getVertices().get(i));
					stop = trafficGraph.getVertices().get(i);
				}
			}
		}
        return new Itinerary(path);
	}
	/*
	public static void main(String[] args) {
		RoutePlannerBuilder rb = new RoutePlannerBuilder();
		RoutePlanner rp = rb.build("src/inputdata.csv", 45);
		rp.createGraph();
		Stop A = new Stop(1, 0, "A", 335);
		Stop D = new Stop(4, 0, "D", 0);
		Itinerary it = rp.computeRoute(A, D, 300);
		System.out.println(it.getInstructions());
	}
	*/
}
