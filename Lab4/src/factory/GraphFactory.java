package factory;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public abstract class GraphFactory {
	public abstract Graph<Vertex, Edge> createGraph(String filePath);
}