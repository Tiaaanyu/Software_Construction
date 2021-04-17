package edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabSelectionHandler;

import vertex.*;

/**
 * The superclass of all kinds of edges, an Edge object has 
 * a weight and a label. All kinds of other edges must be inherited
 * from the Edge class. 
 * @author muty
 *
 */
public abstract class Edge {
	protected final String label;             //标签 
	protected double weight;            //权重
	private final List<Vertex> allVertices = new ArrayList<>();
	
	// Abstraction function:
	//   -label represents the label of the edge.
	//   -weight represents the weight of the edge.
	//   -allVertices is a List object which contains all Vertex in the edge.
	// Representation invariant:
	//   -label cannot be null.
	//   -weight must more than 0 as long as it is not the hyper edge.
	//   -After adding vertex to the edge, allVertices.size() must more than 2
	//    or be equal to 2 as long as the edge is not a LOOP. 
	// Safety from rep exposure:
	//   -Some methods such as vertices() will return a Set, which is a mutable
	//    collection, so, to prevent exposing the representation to the client, 
	//    we must make the defensive copy.
	
	/**
	 * The constructor
	 */
	public Edge(String label, double weight) {
		this.label = label;
		this.weight = weight;
	}
	
	/**
	 * Get the label.
	 * @return the String label.
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Get the weight.
	 * @return the double weight.
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Set the weight.
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/**
	 * Add Vertex into the edge.
	 *  -If the edge is a HyperEdge, then assert the vertices.size() >= 2, then add all 
	 *   vertices to the graph.
	 *  -If the edge is a DirectedEdge, then assert the vertices.size() == 2, then the 
	 *   first element is the source, and the second element is the target.
	 *  -If the edge is a Undirected Edge, then the order is arbitrary.
	 *  -If it represents a loop, assert the vertices.size() == 1.
	 * @param vertices, the List object which has at least two elements.
	 * @return return whether the add is successful.
	 */
	public abstract boolean addVertices(List<Vertex> vertices);
	
	/**
	 * Determine the edge contains the given vertex or not.
	 * @param V, the given vertex.
	 * @return contains or not.
	 */
	public abstract boolean containVertex(Vertex V);
	
	/**
	 * Return the vertices contained in the edges.
	 * @return a Set which has all Vertex objects in the edge.
	 */
	public abstract Set<Vertex> vertices();
	
	/**
	 * Return the sources.
	 * -If it is a directed edge, return the source vertex.
	 * -If it is an undirected edge, return the both two vertices.
	 * -If it is a hyper edge, return all the vertices.
	 * @return a Set which represents the source vertex.
	 */
	public abstract Set<Vertex> sourceVertices();
	
	/**
	 * Return the targets.
	 * -If it is a directed edge, return the target vertex.
	 * -If it is an undirected edge, return the both two vertices.
	 * -If it is a hyper edge, return all the vertices. 
	 * @return a Set which represents the target vertex.
	 */
	public abstract Set<Vertex> targetVertices();
	
	/**
	 * Get the list
	 * @return list
	 */
	public abstract List<Vertex> getList();
	/**
	 * Override the method toString(). Return all the info of the edge.
	 */
	
	@Override
	public String toString() {
		String newString = new String();
		newString += ("Label: " + '\n');
		newString += (label + '\n');
		newString += ("Vertices: " + '\n');
		for(Vertex x: this.vertices()) {
			newString += (x.getLabel() +" ");
		}
		newString += '\n';
		newString += ("Weight: " + '\n');
		newString += (this.weight + "\n");
		//newString += '\n';
		return newString;
	}
	
	/**
	 * Override the method hashCode().
	 * Return new hash code.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + label.hashCode();
		return result;
	}
	
	/**
	 * Override the method equals().
	 */
	@Override
	public boolean equals(Object that) {
		if(this == that)
			return true;
		if(that == null)
			return false;
		if(that instanceof Edge) {
			Edge other = (Edge) that;
			return other.vertices().equals(this.vertices()) && other.getLabel().equals(this.getLabel())
					&& (other.getWeight() == this.getWeight());
		}
		return false;
	}
	
	
	
}
