package P3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.text.html.HTML.Tag;
// -Abstraction Function:
//    name is the stop's name, time is the time that bus arrived here. 
//    the flag is the symbol of virtual vertex, the visit is the symbol of
//    visited or not when we are running the Dijkstra, dist is the distance 
//    from the source stop
// -Representation invariant:
//    name is a non-null string, friends is a non-null map
// -Safety from rep exposure
//    the getForward() method  will return a Stop object, which is a mutable 
//    object, so we must make the defensive copy to avoid sharing our code with
//    client.
public class Stop {
	private double latitude = 0, longitude = 0;
	private String name = new String();
	private int time = 0;
	private String routeName = new String();
	public boolean flag = false;
	public boolean visit = false;
	public Map<Stop, Integer> friends = new HashMap<>();
	public Stop virtualStop;
	public int virtualNumber = 0;
	private int dist = Integer.MAX_VALUE;
	private String forwardStopName = new String();
	private int forwardStopTime = 0;
	private String forwardStopRouteName = new String();
	public Stop(double latitude, double longitude, String name, int time) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.time = time;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String getName() {
		return name;
	}
	public int getTime() {
		return time;
	}
	public String getRouteName() {
		return routeName;
	}
	public int getDist(){
		return dist;
	}
	public void setDist(int a) {
		dist = a;
	}
	public Stop getForward() {
		
		Stop stop = new Stop(0, 0, forwardStopName, forwardStopTime);
		stop.setRouteName(forwardStopRouteName);
		return stop;
	}
	public void setForward(String na, int ti, String routename) {
		
		forwardStopName = na;
		forwardStopTime = ti;
		forwardStopRouteName = routename;
	}
}
