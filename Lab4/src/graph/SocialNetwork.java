package graph;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edge.Edge;
import vertex.Person;
import vertex.Vertex;

public class SocialNetwork extends ConcreteGraph<Vertex, Edge> {
	private Map<Vertex, Double> weightMap = new HashMap<>();
	
	// Abstraction function:
	//    -allVertices is a list where stored all the vertices in the graph.
	//    -allEdges is a list where stored all the edges in the graph.
	//    -The ConcreteGraph class can create a graph.
	// Representation invariant:
	//    -allVertices is a list of L objects, which does not allow the same element.
	//    -allEdges is a list of E objects, which also does not allow the same element.
	//    -Assert allVertices.size() greater than allEdges.size() * 2 or equal to
	//     allEdges.size() * 2.
	//	  -Must make sure the summary of all edges' weight is equal to 1.
	// Safety from rep exposure:
	//    -Some methods will return a Set such as the method vertices(), so to avoid the 
	//     representation exposure to the client, we must have the defensive copies.
	//    -In the graph, we have a private and final collection to store the vertices and 
	//     the edges, so when we need return the vertices or the edges, we can make a copy 
	//     to make sure only we know the data.
	
	/**
	 * override the method checkRep(), assure the total weight of all edges is 1.
	 */
	@Override
	public void checkRep() {
		super.checkRep();
		double sum = 0;
		for(Edge x: allEdges) {
			sum += x.getWeight();
		}
		assertEquals(1, sum, 0.01);
	}
	/**
	 * To make sure the total weight of all edges is 1, when deleting a vertex, 
	 * must change the weight of all other edges.
	 */
	@Override
	public boolean removeVertex(Vertex v) {
		if(allVertices.contains(v)) {
			allVertices.remove(v);
			Iterator<Edge> it = allEdges.iterator();
			while (it.hasNext()) {
				Edge edge = (Edge) it.next();
				if(edge.vertices().contains(v)) {
					it.remove();
					for(Edge x: allEdges) {
						double newWeight = x.getWeight() / (1 - edge.getWeight());
						x.setWeight(newWeight);
					}
				}
			}
			checkRep();
			return true;
		}
		else return false;
	}
	
	/**
	 *To make sure the total weight of all edges is 1, when adding a new edge, 
	 * must change the weight of all other edges. 
	 */
	@Override
	public boolean addEdge(Edge e) {
		if(!allEdges.contains(e)) {
			for(Edge x: allEdges) {
				double newWeight = x.getWeight() * (1 - e.getWeight());
				x.setWeight(newWeight);
			}
			allEdges.add(e);
			checkRep();
			return true;
		}
		else return false;
	}
	
	/**
	 * To make sure the total weight of all edges is 1, when deleting an edge, 
	 * must change the weight of all other edges.
	 */
	@Override
	public boolean removeEdge(Edge e) {
		if(allEdges.contains(e)) {
			allEdges.remove(e);
			for(Edge x: allEdges) {
				double newWeight = x.getWeight() / (1 - e.getWeight());
				x.setWeight(newWeight);
			}
			checkRep();
			return true;
		}
		else return false;
	}
	
	/**
	 * Set a new weight of the edge.
	 * @param e, edge
	 * @param weight, new weight
	 * @return true or false
	 */
	public boolean setWeight(Edge e, double weight) {
		if(allEdges.contains(e)) {
			for(Edge x: allEdges) {
				if(x.equals(e))
					continue;
				else {
					double newWeight = x.getWeight() * (1 - weight) / (1 - e.getWeight());
					x.setWeight(newWeight);
				}
			}
			e.setWeight(weight);
			checkRep();
			return true;
		}
		else return false;
	}
	
	public void setWeightMap(Map<Vertex, Double> map) {
		weightMap = map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
