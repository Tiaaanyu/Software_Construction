package helper;

import graph.Graph;
import vertex.*;
import edge.*;
public interface CentralityStrategy {
	
	public abstract double centralityStrategy(Graph<Vertex, Edge> graph, Vertex v);
}
