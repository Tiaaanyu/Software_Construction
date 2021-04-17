/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.naming.event.NamespaceChangeListener;
//import javax.print.attribute.standard.RequestingUserName;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	//    The class "Edge" represents the edge in the graph, and every edge has a source vertex, 
	//    a target vertex and a weight, different edges can have the same weight. 
	//    The ConcreteEdgesGraph can create a graph based on Edge.
	//    vertices represents the set of vertices in the graph.
	//    edges is a list that store all the edges in the graph.
	// Representation invariant:
	//    vertices is a set of the object L
	//    edges is a list of all the edges in the graph, which can not be repeated, and the 
	//    weight must be an integer greater than 1 or equal to 1
	//    vertices.size() must be greater than edges.size() * 2 or equal to edges.size() * 2
	//    an edge's source and target must be different and must in the vertices.
	// Safety from rep exposure:
	//    All fields are private and final 
	//    vertices and edges are both mutable, so the methods must have defensive copies to avoid sharing 
	//    fields with client, such as the method "vertices()".
	private void checkRep() {
		assert vertices.size() >= edges.size();
	}
	
	/**
	 * constructor of ConcreteEdgesGraph
	 */
	public ConcreteEdgesGraph() {
		
	}
	/**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
	@Override
	public boolean add(L vertex) {
		// throw new RuntimeException("not implemented");
		if (!vertices.contains(vertex)) {
			vertices.add(vertex);
			return true;
		}

		else
			return false;
	}
	/**
     * Add, change, or remove a weighted directed edge in this graph.
     * If weight is nonzero, add an edge or update the weight of that edge;
     * vertices with the given labels are added to the graph if they do not
     * already exist.
     * If weight is zero, remove the edge if it exists (the graph is not
     * otherwise modified).
     * 
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight nonnegative weight of the edge
     * @return the previous weight of the edge, or zero if there was no such
     *         edge
     */
	@Override
	public int set(L source, L target, int weight) {
		if (weight > 0) {
			for (int i = 0; i < edges.size(); ++i) {
				if (edges.get(i).getSource().equals(source) && edges.get(i).getTarget().equals(target)) {
					int previousWeight = edges.get(i).getWeight();
					edges.remove(i);
					Edge<L> e = new Edge<L>(source, target, weight);
					edges.add(i, e);
					checkRep();
					return previousWeight;
				}
			}

			Edge<L> edge = new Edge<L>(source, target, weight);
			edges.add(edge);
			checkRep();
			return 0;
		} 
		else {
			
			for (int i = 0; i < edges.size(); ++i) {
				if (edges.get(i).getSource().equals(source) && edges.get(i).getTarget().equals(target)) {
					int previousWeight = edges.get(i).getWeight();
					edges.remove(edges.get(i));
					checkRep();
					return previousWeight;
				}
			}
		}
		checkRep();
		return 0;
	}
	/**
     * Remove a vertex from this graph; any edges to or from the vertex are
     * also removed.
     * 
     * @param vertex label of the vertex to remove
     * @return true if this graph included a vertex with the given label;
     *         otherwise false (and this graph is not modified)
     */
	@Override
	public boolean remove(L vertex) {
		if (!vertices.contains(vertex)) {
			checkRep();
			return false;
		} 
		else {
			vertices.remove(vertex);
			for (int i = 0; i < edges.size(); ++i) {
				if (edges.get(i).getSource().equals(vertex) || edges.get(i).getTarget().equals(vertex))
					edges.remove(edges.get(i));
			}
			checkRep();
			return true;
		}
	}
	/**
     * Get all the vertices in this graph.
     * 
     * @return the set of labels of vertices in this graph
     */

	@Override
	public Set<L> vertices() {
		// throw new RuntimeException("not implemented");
		Set<L> verticesCopy = new HashSet<>();
		for(L x: vertices)
			verticesCopy.add(x);
		checkRep();
		return verticesCopy;
	}
	/**
     * Get the source vertices with directed edges to a target vertex and the
     * weights of those edges.
     * 
     * @param target a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from that vertex to target, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         the key to target
     */
	@Override
	public Map<L, Integer> sources(L target) {
		// throw new RuntimeException("not implemented");
		Map<L, Integer> sources = new HashMap<>();
		for (int i = 0; i < edges.size(); ++i) {
			if (edges.get(i).getTarget().equals(target)) {
				sources.put(edges.get(i).getSource(), edges.get(i).getWeight());
			}
		}
		checkRep();
		return sources;
	}
	/**
     * Get the target vertices with directed edges from a source vertex and the
     * weights of those edges.
     * 
     * @param source a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from source to that vertex, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         source to the key
     */
	@Override
	public Map<L, Integer> targets(L source) {
		// throw new RuntimeException("not implemented");
		Map<L, Integer> targets = new HashMap<>();
		for (int i = 0; i < edges.size(); ++i) {
			if (edges.get(i).getSource().equals(source)) {
				targets.put(edges.get(i).getTarget(), edges.get(i).getWeight());
			}
		}
		checkRep();
		return targets;
	}
   /**
    * Returns a string representation of the graph.
    * @return a string of graph
    */
   @Override
   public String toString() {
	   if (vertices.isEmpty()) {
		return "The graph is empty!";
	}
	   else if(edges.isEmpty()) {
		   return "The graph has no edges!";
	   }
		else {
			String s = new String();
			for(int i = 0; i < edges.size(); ++i) {
				s += edges.get(i).toString();
			}
			checkRep();
			return s;
		}
	}
   
}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
 class Edge<L> {

	// Abstraction function:
	//    the class Edge represents a edge in the graph, every edge is from source to target,
	//    also has a weight which is more than 1 or equal to 1.
	// Representation invariant:
	//    source is a non-null L, which represents the begin of a edge.
	//    target is a non-null L, which represents the end of a edge.
	//    weight is an integer more than 1 or equal to 1, which represents the length of a edge.
	//    According to the spec, L must be immutable type.
	//    For example: source = "a"; target = "b", weight = 5
	//    so the directed graph must contains edges below:
	//    a ---5--> b 
	// Safety from rep exposure:
	//    All fields are private and final.
	//    source and target are both immutable
	//    may be need defensive copy
	private final L source, target;
	private final int weight;
    
	public Edge(L source, L target, int weight) {
		assert weight > 0;
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}
	/**
	 * check the rep
	 */
	private void checkRep() {
		assert source != null;
		assert target != null;
		assert weight >= 0;
	}
	/**
	 * return the source of an edge
	 * @return the L object source
	 */
	public L getSource() {
		L s = source;
		checkRep();
		return s; 
	}
	/**
	 * return the target of an edge
	 * @return the L object target
	 */
	public L getTarget() {
		L t = target;
		checkRep();
		return t;
	}
	/**
	 * return the weight of an edge
	 * @return the int object represents the weight.
	 */
	public int getWeight() {
		checkRep();
		return weight;
	}
	/**
	 * Returns a string representation of the Edge.
	 * @return a string of edge
	 */

	public String toString() {
		checkRep();
		return (source + "->" + target + weight + " "); 
	}
}

