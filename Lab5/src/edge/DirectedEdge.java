package edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.validator.PublicClassValidator;

import vertex.Vertex;
import vertex.Word;

public abstract class DirectedEdge extends Edge {
	private List<Vertex> allVertices = new ArrayList<>();
	
	// Abstraction function:
	//   -label represents the label of the edge.
	//   -weight represents the weight of the edge.
	//   -allVertices is a List object which contains all Vertex in the edge.
	// Representation invariant:
	//   -label cannot be null.
	//   -weight must more than 0 as long as it is not the hyper edge.
	//   -After adding vertex to the edge, allVertices.size() must be 2. 
	// Safety from rep exposure:
	//   -Some methods such as vertices() will return a Set, which is a mutable
	//    collection, so, to prevent exposing the representation to the client, 
	//    we must make the defensive copy.
	
	/**
	 * The constructor.
	 * @param label, the label of edge.
	 * @param weight, the weight of edge.
	 */
	public DirectedEdge(String label, double weight) {
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
		for(Vertex x: vertices)               //allVertices.get(0) is the source vertex.
			allVertices.add(x);               //allVertices.get(1) is the target vertex.
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
	 * Return the source vertex of the edge;equivalently return allVertices.get(0)
	 */
	public Set<Vertex> sourceVertices() {
		Set<Vertex> source = new HashSet<>();
		source.add(allVertices.get(0));
		return source;
	}
	
	/**
	 * Return the target vertex of the edge;equivalently return allVertices.get(1)
	 */
	public Set<Vertex> targetVertices() {
		Set<Vertex> target = new HashSet<>();
		target.add(allVertices.get(1));
		return target;
	}
	
	/**
	 * Return the list
	 */
	public List<Vertex> getList() {
		return allVertices;
	}
	
	
	
	
	
	
	
	
	
	
}
