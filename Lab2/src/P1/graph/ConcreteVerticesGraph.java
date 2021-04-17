/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

//import static org.junit.Assert.assertArrayEquals;

//import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //    Represents the directed graph with vertices with weight.
    //    Every edge can be represented by the start point and the end point.
    // Representation invariant:
    //    Only one instance of a vertex can exist in vertices.
    //    Weight must be an integer more than 1 or equal to 1. A graph can not
    //    have repeated edges.
    // Safety from rep exposure:
    //    vertices is a mutable list, so must make defensive copies to avoid 
    //    sharing fields with client.
    //    Vertex is also a mutable type, so still need the defensive copies to
    //    avoid sharing with client.
    private void checkRep() {
    	assert vertices.size() >= 0;
    }
    /**
     * constructor
     */
    public ConcreteVerticesGraph() {
		
	}
    /**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
    	for(int i = 0; i < vertices.size(); ++i) {
    		if(vertices.get(i).getName().equals(vertex))
    			//checkRep();
    			return false;
    	}
    	Vertex<L> v = new Vertex<L>(vertex);
    	vertices.add(v);
    	//checkRep();
    	return true;
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
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
    	int previousWeight = 0, flag = 0;
    	if(weight > 0) {
    		for(int i = 0; i < vertices.size(); ++i) {
    			if(vertices.get(i).getName() == source) {    //设置source中的以source为起点的边
    				flag = 0;
    				Iterator<Map.Entry<L, Integer>> it = vertices.get(i).getSources().entrySet().iterator();
    				while (it.hasNext()) {
						Map.Entry<L, java.lang.Integer> entry = (Map.Entry<L, java.lang.Integer>) it
								.next();
						if(entry.getKey().equals(target)) {  //之前含有这条边，修改weight就好
							previousWeight = entry.getValue();
							vertices.get(i).setSourceWeight(target, weight);
							flag = 1;
						}
					}
    				if(flag == 0) {
    					vertices.get(i).setSourceWeight(target, weight);
    				}
    				flag = 0;
    			}
    			else if(vertices.get(i).getName() == target) {  //设置target中以target为终点的边
    				flag = 0;
    				Iterator<Map.Entry<L, Integer>> it = vertices.get(i).getTargets().entrySet().iterator();
    				while (it.hasNext()) {
						Map.Entry<L, java.lang.Integer> entry = (Map.Entry<L, java.lang.Integer>) it
								.next();
						if(entry.getKey().equals(source)) {  //这条边已存在 修改weight
							previousWeight = entry.getValue();
							vertices.get(i).setTargetWeight(source, weight);
							flag = 1;
						}
					}
    				if(flag == 0) {
    					vertices.get(i).setTargetWeight(source, weight);
    				}
    				flag = 0;
    			}
    			else continue;
    		}
    	}
    	else {
    		
    		for(int i = 0; i < vertices.size(); ++i) {
    			if(vertices.get(i).getName().equals(source)) {
    				if(vertices.get(i).getSources().containsKey(target))
    					previousWeight = vertices.get(i).getSources().get(target);	
    				vertices.get(i).removeSourceEdge(target);
    			}
    			else if(vertices.get(i).getName().equals(target)) {
    				vertices.get(i).removeTargetEdge(source);
    			}
    			else continue;
    		}
    	}
    	checkRep();
    	return previousWeight;
    }
    /**
     * Remove a vertex from this graph; any edges to or from the vertex are
     * also removed.
     * 
     * @param vertex label of the vertex to remove
     * @return true if this graph included a vertex with the given label;
     *         otherwise false (and this graph is not modified)
     */
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
    	List<L> l = new ArrayList<>();
    	for(int i = 0; i < vertices.size(); ++i)
    		l.add(vertices.get(i).getName());
    	if(!l.contains(vertex)) 
    		return false;
    	else {
    	for(int i = 0; i < vertices.size(); ++i) {
    		if(vertices.get(i).getName().equals(vertex))
    			vertices.remove(vertices.get(i));
    		else {
    			Iterator<Map.Entry<L, Integer>> it1 = vertices.get(i).getSources().entrySet().iterator();
    			while (it1.hasNext()) {
					Map.Entry<L, java.lang.Integer> entry = (Map.Entry<L, java.lang.Integer>) it1
							.next();
					if(entry.getKey().equals(vertex)) {
						vertices.get(i).removeSourceEdge(vertex);
					}
				}
    			Iterator<Map.Entry<L, Integer>> it2 = vertices.get(i).getTargets().entrySet().iterator();
    			while (it2.hasNext()) {
					Map.Entry<L, java.lang.Integer> entry = (Map.Entry<L, java.lang.Integer>) it2
							.next();
					if(entry.getKey().equals(vertex)) {
						vertices.get(i).removeTargetEdge(vertex);
					}
				}
    		}
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
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
    	Set<L> allVertices = new HashSet<>();
    	for(int i = 0; i < vertices.size(); ++i) {
    		allVertices.add(vertices.get(i).getName());
    	}
    	checkRep();
    	return allVertices;
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
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
    	for(int i = 0; i < vertices.size(); ++i) {
    		if(vertices.get(i).getName().equals(target)) {
    			return vertices.get(i).getTargets();
    		}
    	}
    	Map<L, Integer> map = new HashMap<>();
    	checkRep();
    	return map;
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
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
    	for(int i = 0; i < vertices.size(); ++i) {
    		if(vertices.get(i).getName().equals(source)) {
    			checkRep();
    			return vertices.get(i).getSources();
    		}
    	}
    	Map<L, Integer> map = new HashMap<>();
    	checkRep();
    	return map;
    }
    public boolean haveNoEdges() {
    	boolean flag = true;
    	for(int i = 0; i < vertices.size(); ++i)
    	{
    		if(!vertices.get(i).getSources().isEmpty() || !vertices.get(i).getTargets().isEmpty()) {
    			flag = false;
    			break;
    		}
    	}
    	
    	return flag;
    }
    /**
     * Returns a string representation of the graph.
     * @return a string of graph
     */
    @Override public String toString() {
    	if(vertices.size() == 0) {
    		return "The graph is empty!";
    	}
    	else if(vertices.size() != 0 && haveNoEdges()) {
    		return "The graph has no edges!";
    	}
    	else {
    	String a = new String();
    	for(Vertex<L> v: vertices) {
    		a += v.toString();
    	}
    	
    	return a;
    	}
    }
    public List<Vertex<L>> getVertices() {
    	List<Vertex<L>> list = new ArrayList<>();
    	for(int i = 0; i < vertices.size(); ++i) {
    		list.add(vertices.get(i));
    	}
    	return list;
    }
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    
    // Abstraction function:
    //    Vertex represents a vertex in the directed graph.
	//    name is a L type object which means the label of a vertex.
	//    sources is a map, which stored the edge whose source is this vertex.
	//    targets is a map, which stored the edge whose target is this vertex.
	//    For example: name = "a"; sources = [<"b", 5>]; targets = [<"c", 3>];
	//    so the graph must contains two edges below:
	//    a --5--> b     c --3--> a
    // Representation invariant:
    //    Vertex label must be non-null
	//    A vertex can be neither its own source nor target.
	//    weight must be an integer more than 1 or equal to 1.
	//    A directed graph cannot have repeated edges.
    // Safety from rep exposure:
    //    Fields must be private and final.
	//    According to the spec, L must be immutable.
	//    sources and targets are both mutable type, so operations must have defensive
	//    copies to avoid sharing fields with client.
    
	private final L name;
	private final Map<L, Integer> sources = new HashMap<>();  
	private final Map<L, Integer> targets = new HashMap<>();  
	public Vertex(L name) {
		this.name = name;
	}
	/**
	 * check the rep
	 */
	private void checkRep() {
		assert !sources.keySet().contains(name);
		assert !targets.keySet().contains(name);
	}
    /**
     * get the name
     * @return object L represents the name
     */
	public L getName() {
		checkRep();
		return name;
	}
	/**
	 * set the source weight
	 * @param target  the target
	 * @param weight  the weight
	 */
	public void setSourceWeight(L target, int weight) {
		if(sources.containsKey(target)) {
			sources.remove(target);
		}
		sources.put(target, weight);
	}
	/**
	 * set the target weight
	 * @param source  the source
	 * @param weight  the weight
	 */
	public void setTargetWeight(L source, int weight) {
		if(targets.containsKey(source)) {
			targets.remove(source);
		}
		targets.put(source, weight);
	}
	/**
	 * remove the source edge
	 * @param target the target
	 */
	public void removeSourceEdge(L target) {
		sources.remove(target);
	}
	/**
	 * remove the target edge
	 * @param source the source
	 */
	public void removeTargetEdge(L source) {
		targets.remove(source);
	}
	/**
	 * get the sources of an vertex
	 * @return the set of sources 
	 */
	public Map<L, Integer> getSources() {
		return sources;
	}
	/**
	 * get the targets of an vertex
	 * @return the set of targets 
	 */
	public Map<L, Integer> getTargets() {
		checkRep();
		return targets;
	}
	/**
	 * Returns a string representation of the Vertex.
	 * @return a string of edge
	 */
	public String toString() {
		String a = new String();
		for(L x: sources.keySet()) {
			a += name + "->" + x + sources.get(x) + " ";
		}
		checkRep();
		return a;
	}
    
}
