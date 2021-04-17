package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sound.midi.VoiceStatus;

import edge.Edge;
import vertex.Vertex;
/*
 * Concrete a graph.
 */

public class ConcreteGraph<L extends Vertex, E extends Edge> implements Graph<L, E> {
	protected final List<L> allVertices = new ArrayList<>();
	protected final List<E> allEdges = new ArrayList<>();
	protected Map<L, Double> weightMap = new HashMap<>();
	// Abstraction function:
	//    -allVertices is a list where stored all the vertices in the graph.
	//    -allEdges is a list where stored all the edges in the graph.
	//    -The ConcreteGraph class can create a graph.
	// Representation invariant:
	//    -allVertices is a list of L objects, which does not allow the same element.
	//    -allEdges is a list of E objects, which also does not allow the same element.
	//    -Assert allVertices.size() greater than allEdges.size() * 2 or equal to
	//     allEdges.size() * 2.
	// Safety from rep exposure:
	//    -Some methods will return a Set such as the method vertices(), so to avoid the 
	//     representation exposure to the client, we must have the defensive copies.
	//    -In the graph, we have a private and final collection to store the vertices and 
	//     the edges, so when we need return the vertices or the edges, we can make a copy 
	//     to make sure only we know the data.
	/**
	 * Constructor of ConcreteGraph.
	 */
	public void ConcreteGraph() {
		
	}
	public void checkRep() {
		assert allVertices.size() >= allEdges.size() * 2;
	}
	
	/**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified).
     */
	public boolean addVertex(L vertex) {
		if(!allVertices.contains(vertex)) {
			allVertices.add(vertex);
			return true;
		}
		else return false;
	}
	
	/**
	 * Remove a vertex from this graph; if the edge is an undirected edge or 
	 * directed edge. But if it's a hyper edge, the edge can still exist if 
	 * the edge without this vertex is still legal, 
	 * @param vertex label of the vertex to remove.
	 * @return true if this graph included a vertex with the given label;
	 *         otherwise false (and this graph is not modified).
	 */
	public boolean removeVertex(L vertex) {
		if(allVertices.contains(vertex)) {
			allVertices.remove(vertex);
			Iterator<E> it = allEdges.iterator();
			while (it.hasNext()) {
				E edge = (E) it.next();
				if(edge.vertices().contains(vertex))
					it.remove();
			}
			checkRep();
			return true;
		}
		else return false;
		
	}
	

	/**
     * Get all the vertices in this graph.
     * 
     * @return the set of labels of vertices in this graph
     */
	@Override
	public Set<L> vertices() {
		Set<L> vertices = new HashSet<>();
		for(L x: allVertices) {
			vertices.add(x);
		}
		return vertices;
	}

	/**
     * Get the source vertex of the directed edges to a target vertex and the 
     * weights of those edges. If it is an undirected edge. 
     * @param target a label 
     * @return a map where the key is the set of labels of vertices such
     *         that this graph includes an edge from source to that vertex,
     *         not only the directed edges, but also the undirected edges.
     *         and the value for each key is the (nonzero) weight of the edge 
     *         from source to the key
     */
	@Override
	public Map<L, Double> sources(L target) {
		Map<L, Double> sources = new HashMap<>();
		for(E x: allEdges) {
			if(x.vertices().contains(target)) {
				for(Vertex y: x.sourceVertices()) {
					if(!y.equals(target))             // avoid to put itself into the map
						sources.put((L) y, x.getWeight());
				}
			}
		}
		return sources;
	}

	/**
     * Get the target vertices with directed edges from a source vertex and the
     * weights of those edges.
     * @param source a label.
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from source to that vertex, 
     *         not only the directed edges, but also the undirected edges. and
     *         the value for each key is the (nonzero) weight of the edge from
     *         source to the key
     */
	@Override
	public Map<L, Double> targets(L source) {
		Map<L, Double> targets = new HashMap<>();
		for(E x: allEdges) {
			if(x.vertices().contains(source)) {
				for(Vertex y: x.targetVertices()) {
					if(!y.equals(source))        // avoid to put itself into the map
						targets.put((L) y, x.getWeight());
				}
			}
		}
		return targets;
	}
	
	/**
     * Add an edge to the graph, including the hyper edge.
     * @param edge a label of the edge
     * @return true if the graph didn't have the edge before; otherwise is false
     *         (and this graph is not modified)
     */
	@Override
	public boolean addEdge(E edge) {
		if(!allEdges.contains(edge)) {
			allEdges.add(edge);
			//checkRep();
			return true;
		}
		else return false;
	}

	/**
     * Delete an edge to the graph, including the hyper edge.
     * @param edge a label of the edge
     * @return true if the graph have the edge; otherwise is false
     *         (and this graph is not modified)
     */
	@Override
	public boolean removeEdge(E edge) {
		if(allEdges.contains(edge)) {
			allEdges.remove(edge);
			checkRep();
			return true;
		}
		else return false;
	}

	/**
     * Get all the edges in this graph. 
     * @return the set of labels of edges in this graph
     */
	@Override
	public Set<E> edges() {
		Set<E> edges = new HashSet<>();
		for(E x: allEdges) {
			edges.add(x);
		}
		return edges;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
