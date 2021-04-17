package graph;

import java.util.HashMap;
import java.util.Map;

import edge.Edge;
import vertex.Vertex;

public class GraphPoet extends ConcreteGraph<Vertex, Edge>{
	
	// Abstraction function:
	//    -allVertices is a list where stored all the vertices in the graph.
	//    -allEdges is a list where stored all the edges in the graph.
	//    -The ConcreteGraph class can create a graph.
	// Representation invariant:
	//    -allVertices is a list of L objects, which does not allow the same element.
	//    -allEdges is a list of E objects, which also does not allow the same element.
	//    -Assert allVertices.size() greater than allEdges.size() * 2 or equal to
	//     allEdges.size() * 2.
	//	  -When entering a loop, the list can have only one element.
	// Safety from rep exposure:
	//    -Some methods will return a Set such as the method vertices(), so to avoid the 
	//     representation exposure to the client, we must have the defensive copies.
	//    -In the graph, we have a private and final collection to store the vertices and 
	//     the edges, so when we need return the vertices or the edges, we can make a copy 
	//     to make sure only we know the data.
	
	/**
	 * Because of the loop, which means a directed edge, the source and the target
	 * are the same vertex. So we must change something to adapt for the GraphPoet class. 
	 */
	@Override
	public Map<Vertex, Double> sources(Vertex target) {
		Map<Vertex, Double> sources = new HashMap<>();
		for(Edge x: allEdges) {
			if(x.vertices().contains(target)) {
				for(Vertex y: x.sourceVertices())    
					sources.put(y, x.getWeight());
			}
		}
		return sources;
	}
	
	/**
	 * Because of the loop, which means a directed edge, the source and the target
	 * are the same vertex. So we must change something to adapt for the GraphPoet class. 
	 */
	@Override
	public Map<Vertex, Double> targets(Vertex source) {
		Map<Vertex, Double> targets = new HashMap<>();
		for(Edge x: allEdges) {
			if(x.vertices().contains(source)) {
				for(Vertex y: x.targetVertices())
					targets.put(y, x.getWeight());
			}
		}
		return targets;
	}
}
