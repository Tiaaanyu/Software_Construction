package helper;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public class CentralityContext {
	public static double setCentralityStrategy(String string, Graph<Vertex, Edge> g, Vertex v) {
		return CentralityStrategyHelper.parseAndExecute(string, g, v);
	}
}
