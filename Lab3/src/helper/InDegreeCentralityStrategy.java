package helper;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public class InDegreeCentralityStrategy implements CentralityStrategy{
	public double centralityStrategy(Graph<Vertex, Edge> graph, Vertex v) {
		return GraphMetrics.inDegreeCentrality(graph, v);
	}
}
