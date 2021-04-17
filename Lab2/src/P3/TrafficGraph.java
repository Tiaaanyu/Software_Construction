package P3;

import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import P1.graph.ConcreteVerticesGraph;

public class TrafficGraph {
	private List<Stop> vertices = new ArrayList<>();
 	
	public void set(Stop source, Stop target , int weight) {
			source.friends.put(target, weight);
	}
	public void add(Stop st) {
		vertices.add(st);
	}
	public void remove (Stop source, Stop target) {
		if(source.friends.keySet().contains(target)) {
			source.friends.remove(target);
		}
	}
	public void removeVertex(Stop st) {
		vertices.remove(st);
		for(Stop stop: vertices) {
			if(stop.friends.containsKey(st)) 
				stop.friends.remove(stop);
		}
	}
	public List<Stop> getVertices() {
		return vertices;
	}

}
