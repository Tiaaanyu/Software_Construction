package graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.border.EmptyBorder;

import edge.Edge;
import vertex.Vertex;

public interface Graph<L, E> {
	
	/**
	 * Create an empty graph.
	 * @param <L, E> type of vertex labels and edge labels in the graph.
	 * @return a new empty weighted graph.
	 */
	public static <L extends Vertex , E extends Edge> Graph<L, E> empty() {
		return new ConcreteGraph<L, E>();
		
	}
	
	/**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified).
     */
	public boolean addVertex(L vertex);
	
	/**
	 * Remove a vertex from this graph; if the edge is an undirected edge or 
	 * directed edge. But if it's a hyper edge, the edge can still exist if 
	 * the edge without this vertex is still legal, 
	 * @param vertex label of the vertex to remove.
	 * @return true if this graph included a vertex with the given label;
	 *         otherwise false (and this graph is not modified).
	 */
	public boolean removeVertex(L vertex);
	
	/**
     * Get all the vertices in this graph.
     * 
     * @return the set of labels of vertices in this graph
     */
    public Set<L> vertices();
    
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
    public Map<L, Double> sources(L target);
    
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
    public Map<L, Double> targets(L source);
    
    /**
     * Add an edge to the graph, including the hyper edge.
     * @param edge a label of the edge
     * @return true if the graph didn't have the edge before; otherwise is false
     *         (and this graph is not modified)
     */
    public boolean addEdge(E edge);
    
    /**
     * Delete an edge to the graph, including the hyper edge.
     * @param edge a label of the edge
     * @return true if the graph have the edge; otherwise is false
     *         (and this graph is not modified)
     */
    public boolean removeEdge(E edge);
    
    /**
     * Get all the edges in this graph. 
     * @return the set of labels of edges in this graph
     */
    public Set<E> edges();
    
    
    
    
    
    
    
    
    
    
    
	
}
