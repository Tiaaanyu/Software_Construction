package edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.RequestingUserName;

import vertex.Vertex;

/**
 * The undirected edge class.
 * @author muty
 *
 */
public abstract class UndirectedEdge extends Edge {
	private List<Vertex> allVertices = new ArrayList<>(); 
	
	// Abstraction function:
	//   -label represents the label of the edge.
	//   -weight represents the weight of the edge.
	//   -allVertices is a List object which contains all Vertex in the edge.
	// Representation invariant:
	//   -label cannot be null.
	//   -weight must more than 0 as long as it is not the hyper edge.
	//   -After adding vertex to the edge, allVertices.size() must be equal to 2. 
	// Safety from rep exposure:
	//   -Some methods such as vertices() will return a Set, which is a mutable
	//    collection, so, to prevent exposing the representation to the client, 
	//    we must make the defensive copy.
	
	/**
	 * The constructor.
	 * @param label, the label of edge.
	 * @param weight, the weight of edge.
	 */
	public UndirectedEdge(String label, double weight) {
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
	 * Return the two vertices of the undirected edge, because of 
	 * it has neither source nor target. 
	 */
	public Set<Vertex> sourceVertices() {
		return this.vertices();
	}
	
	/**
	 * Return the two vertices of the undirected edge, because of 
	 * it has neither source nor target. 
	 */
	public Set<Vertex> targetVertices() {
		return this.vertices();
	}
	
	
	public List<Vertex> getList() {
		return allVertices;
	}
	
	
	
	
	
	
	
	
	
	
	
}
