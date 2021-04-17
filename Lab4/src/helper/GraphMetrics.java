package helper;

import edu.uci.ics.jung.*;
import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance;
import edu.uci.ics.jung.algorithms.util.IterativeProcess;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.algorithms.importance.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.Graph;
import graph.GraphPoet;
import graph.SocialNetwork;
import vertex.Vertex;
import vertex.Word;
import edge.*;

public class GraphMetrics {
	
	/**
	 * get the betweenness of a vertex in the graph.
	 * @param g, the graph
	 * @param v, the vertex
	 * @return the betweenness
	 */
	public static double betweennessCentrality(Graph<Vertex,Edge> g, Vertex v) {
		if(g instanceof GraphPoet || g instanceof SocialNetwork) {
			SparseMultigraph<Vertex, Edge> graph = new SparseMultigraph<>();
			for(Vertex x: g.vertices()) {
				graph.addVertex(x);
			}
			for(Edge y: g.edges()) {
				if(y.vertices().size() > 1)
				graph.addEdge(y, y.getList().get(0), y.getList().get(1), EdgeType.DIRECTED);
			}
			BetweennessCentrality<Vertex, Edge> b = new BetweennessCentrality<>(graph);
			return b.getVertexScore(v);
			
		
			
		}
		else {
			SparseMultigraph<Vertex, Edge> graph = new SparseMultigraph<>();
			for(Vertex x: g.vertices()) {
				graph.addVertex(x);
			}
			for(Edge y: g.edges()) {
				if(y instanceof HyperEdge)
					continue;
				else graph.addEdge(y, y.getList().get(0), y.getList().get(1), EdgeType.UNDIRECTED);
			}
			
			BetweennessCentrality<Vertex, Edge> b = new BetweennessCentrality<>(graph);
			return b.getVertexScore(v);
		}
		
	}
	
	

	/**
	 * Get the closeness of a vertex
	 * @param g, the graph
	 * @param v, the vertex
	 * @return the closeness
	 */
	public static double closenessCentrality(Graph<Vertex,Edge> g, Vertex v) {
		if(g instanceof GraphPoet || g instanceof SocialNetwork) {
			SparseMultigraph<Vertex, Edge> graph = new SparseMultigraph<>();
			for(Vertex x: g.vertices()) {
				graph.addVertex(x);
			}
			for(Edge y: g.edges()) {
				if(y.vertices().size() > 1)
				graph.addEdge(y, y.getList().get(0), y.getList().get(1), EdgeType.DIRECTED);
			}
			ClosenessCentrality<Vertex, Edge> ranker = new ClosenessCentrality<>(graph);
			return ranker.getVertexScore(v);
			
		
			
		}
		else {
			SparseMultigraph<Vertex, Edge> graph = new SparseMultigraph<>();
			for(Vertex x: g.vertices()) {
				graph.addVertex(x);
			}
			for(Edge y: g.edges()) {
				if(y instanceof HyperEdge)
					continue;
				else graph.addEdge(y, y.getList().get(0), y.getList().get(1), EdgeType.UNDIRECTED);
			}
			
			ClosenessCentrality<Vertex, Edge> ranker = new ClosenessCentrality<>(graph);
			return ranker.getVertexScore(v);
		}
	}
	
	/**
	 * calculate the degree centrality of a vertex
	 * @param g, the graph
	 * @param v, the vertex
	 * @return the degree centrality.
	 */
	public static double degreeCentrality(Graph<Vertex, Edge> g, Vertex v) {
		int cnt = 0;
		for(Edge x: g.edges()) {
			if(x.vertices().contains(v))
				++cnt;
		}
		return (double) cnt / (g.edges().size() - 1);
	}
	
	public static double degreeCentrality(Graph<Vertex, Edge> g) {
		double maxDegreeCentrality = -1;
		double sum = 0;
		int size = g.vertices().size();
		for(Vertex x: g.vertices()) {
			if(degreeCentrality(g, x) > maxDegreeCentrality) {
				maxDegreeCentrality = degreeCentrality(g, x);
			}
		}
		for(Vertex y: g.vertices()) {
			sum += Math.abs(maxDegreeCentrality - degreeCentrality(g, y));
		}
		return (double) sum / (size * size - 3 * size + 2);
	}
	

	
	
	/**
	 * the Dijkstra 
	 * @param graph, the graph
	 * @param source, the source vertex
	 * @param target, the target vertex
	 * @return the shortest distance and the reverse path
	 */
	private static <L extends Vertex, E extends Edge> Map<Integer, List<L>> Dijkstra(Graph<L, E> graph, L source, L target) {
		Map<Integer, List<L>> result = new HashMap<>();
		Map<L, Boolean > allFlags = new HashMap<>();    // set the flags
		Map<L, Integer> allDistances = new HashMap<>(); // the closest distance.
		Map<L, L> previous = new HashMap<>();           // the previous
		if(graph.targets(source).containsKey(target)) {
			result.put(1, Arrays.asList(source, target));  // the path
			return result;
		}
		else {
			 
			for(L x: graph.vertices()) {   //set the flag = false
				allFlags.put(x, false);
			}
			allFlags.put(source, true);
			for(L x: graph.vertices()) {
				if(x.equals(source)) {
					allDistances.put(x, 0);
				}
				else if(graph.targets(source).containsKey(x)) {
					allDistances.put(x, 1);
				}
					allDistances.put(x, 10000000);
				}
			}
			for(L x: graph.vertices()) {   // The core code of the Dijkstra.
				int min = 10000000;
				L minVertex = source;
				for(L y: graph.vertices()) {
					if(allDistances.get(y) < min && allFlags.get(y) != true) {  //find the closest and non visited vertex.
						minVertex = y;
						min = allDistances.get(y);
					}
					
			}
				allFlags.put(minVertex, true);
				for(L z: graph.vertices()) {     // use minVertex to relax all vertices
					if(allFlags.get(z) == false) {
						if(graph.targets(minVertex).containsKey(z)) {
							if(allDistances.get(minVertex) + 1 < allDistances.get(z)) {
								allDistances.put(z, allDistances.get(minVertex) + 1);
								previous.put(z, minVertex);
							}
						}
					}
					else continue;
				}
					
			
			}
			L m = target;
			List<L> path = new ArrayList<>();
			while(!m.equals(source)) {
				path.add(m);
				m = previous.get(m);
			}
			path.add(m);
			Collections.reverse(path);
			result.put(allDistances.get(target), path);
			return result;
		}

	/** 
	 * Only for the directed graph, calculate the in degree centrality.
	 * @param g, the graph
	 * @param v, the vertex
	 * @return the in degree centrality.
	 */
	public static double inDegreeCentrality(Graph<Vertex, Edge> g, Vertex v) {
		
		return (double)g.sources(v).size() / (g.vertices().size() - 1);
	}
	
	/**
	 * Only for the directed graph, calculate the out degree centrality.
	 * @param g, the graph.
	 * @param v, the vertex.
	 * @return the out degree centrality.
	 */
	public static double outDegreeCentrality(Graph<Vertex, Edge> g, Vertex v) {
		return (double)g.targets(v).size() / (g.vertices().size() - 1);
		
	}
	
	/**
	 * calculate the shortest distance of two vertices in the graph.
	 * @param g, the graph
	 * @param start, the start vertex
	 * @param end, the end vertex.
	 * @return the distance
	 */
	public static double distance(Graph<Vertex, Edge> g, Vertex start, Vertex end) {
		SparseMultigraph<Vertex, Edge> graph = new SparseMultigraph<>();
		if(g instanceof GraphPoet || g instanceof SocialNetwork) {
			
			for(Vertex x: g.vertices()) {
				graph.addVertex(x);
			}
			for(Edge y: g.edges()) {
				if(y.vertices().size() > 1)
				graph.addEdge(y, y.getList().get(0), y.getList().get(1), EdgeType.DIRECTED);
			}
			
		}
		else {
			for(Vertex x: g.vertices()) {
				graph.addVertex(x);
			}
			for(Edge y: g.edges()) {
				if(y instanceof HyperEdge)
					continue;
				else graph.addEdge(y, y.getList().get(0), y.getList().get(1), EdgeType.UNDIRECTED);
			}
			
		}
		DijkstraDistance<Vertex, Edge> dijkstraDistance = new DijkstraDistance<>(graph);
		try {
			double d = (double)dijkstraDistance.getDistance(start, end);
			return d;
		} catch (Exception e) {
			return -1;
		}
		//return (double)dijkstraDistance.getDistance(start, end);
	}
	
	/**
	 * calculate the eccentricity of a special vertex.
	 * @param g, the graph
	 * @param v, the vertex
	 * @return the eccentricity.
	 */
	public static double eccentricity(Graph<Vertex, Edge> g, Vertex v) {
		double max = -1;
		for(Vertex x: g.vertices()) {
			if(x.equals(v))
				continue;
			else if(distance(g, v, x) > max) {
				
				max = distance(g, v, x);
			}
			else continue;
		}
		return max;
	}
	
	/**
	 * calculate the radius of the graph
	 * @param g, the graph
	 * @return the radius
	 */
	public static double radius(Graph<Vertex, Edge> g) {
		double min = 10000000;
		for(Vertex x: g.vertices()) {
			if(eccentricity(g, x) < min && eccentricity(g, x) > 0) {
				min = eccentricity(g, x);
			}
		}
		return min;
	}
	
	/**
	 * calculate the diameter of the graph.
	 * @param g, the graph
	 * @return the diameter.
	 */
	public static double diameter(Graph<Vertex, Edge> g) {
		double max = -1;
		for(Vertex v: g.vertices()) {
			if(eccentricity(g, v) > max) {
				max = eccentricity(g, v);
			}
		}
		return max;
	}
	public static void main(String[] args) {
		GraphPoet graphPoet = new GraphPoet();
		Word A = new Word("A");
		Word B = new Word("B");
		Word C = new Word("C");
		Word D = new Word("D");
		Word E = new Word("E");
		graphPoet.addVertex(A);
		graphPoet.addVertex(B);
		graphPoet.addVertex(C);
		graphPoet.addVertex(D);
		graphPoet.addVertex(E);
		WordEdge e1 = new WordEdge("e1", 1);
		WordEdge e2 = new WordEdge("e2", 1);
		WordEdge e3 = new WordEdge("e3", 1);
		WordEdge e4 = new WordEdge("e4", 1);
		WordEdge e5 = new WordEdge("e5", 1);
		e1.addVertices(Arrays.asList(A, B));
		e2.addVertices(Arrays.asList(C, E));
		e3.addVertices(Arrays.asList(B, C));
		e4.addVertices(Arrays.asList(A, D));
		e5.addVertices(Arrays.asList(D, E));
		graphPoet.addEdge(e1);
		graphPoet.addEdge(e2);
		graphPoet.addEdge(e3);
		graphPoet.addEdge(e4);
		graphPoet.addEdge(e5);
		System.out.println(betweennessCentrality(graphPoet, B));
		System.out.println(closenessCentrality(graphPoet, C));
		System.out.println(degreeCentrality(graphPoet));
		System.out.println(degreeCentrality(graphPoet, D));
		System.out.println(inDegreeCentrality(graphPoet, C));
		System.out.println(outDegreeCentrality(graphPoet, A));
		System.out.println(distance(graphPoet, C, B));
		System.out.println(eccentricity(graphPoet, B));
		System.out.println(diameter(graphPoet));
		System.out.println(radius(graphPoet));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
