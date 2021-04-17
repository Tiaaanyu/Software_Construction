package edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vertex.Vertex;

public abstract class HyperEdge extends Edge {
	
	private List<Vertex> allVertices = new ArrayList<>();
	
	// Abstraction function:
	//   -label represents the label of the edge.
	//   -weight represents the weight of the edge.
	//   -allVertices is a List object which contains all Vertex in the edge.
	// Representation invariant:
	//   -label cannot be null.
	//   -weight must more than 0 as long as it is not the hyper edge.
	//   -After adding vertex to the edge, allVertices.size() must more than 2
	//    or be equal to 2. 
	// Safety from rep exposure:
	//   -Some methods such as vertices() will return a Set, which is a mutable
	//    collection, so, to prevent exposing the representation to the client, 
	//    we must make the defensive copy.
	
	/**
	 * The constructor.
	 * @param label, the label of edge.
	 * @param weight, the weight of edge.
	 */
	public HyperEdge(String label, double weight) {
		super(label, weight);
	}
	
	/**
	 * Get the label of the undirected edge.
	 */
	@Override
	public String getLabel() {
		return super.getLabel();
	}
	
	/**
	 * Get the weight of the undirected edge.
	 */
	@Override
	public double getWeight() {
		return super.getWeight();
	}
	
	/**
	 * Add Vertex into the edge.
	 */
	public boolean addVertices(List<Vertex> vertices) {
		for(Vertex x: vertices)               
			allVertices.add(x);               
		return true;
	}
	
	/**
	 * Determine the edge contains the given vertex or not.
	 */
	public boolean containVertex(Vertex V) {
		return allVertices.contains(V);
	}
	
	/**
	 * Return the vertices contained in the edges.
	 */
	public Set<Vertex> vertices() {
		Set<Vertex> vertices = new HashSet<>();
		for(Vertex x: allVertices)
			vertices.add(x);
		return vertices;
	}
	
	/**
	 * Return all of the vertices of the hyper edge. 
	 */
	public Set<Vertex> sourceVertices() {
		return this.vertices();
	}
	
	/**
	 * Return all of the vertices of the hyper edge. 
	 */
	public Set<Vertex> targetVertices() {
		return this.vertices();
	}
	
	public boolean removeVertex(Vertex v) {
		if(allVertices.contains(v)) {
			allVertices.remove(v);
			return true;
		}
		else return false;
	}
	
	public List<Vertex> getList() {
		return allVertices;
	}
	
	
	
	
	
	
	
	
	
	
	
}
