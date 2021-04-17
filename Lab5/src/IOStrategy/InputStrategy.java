package IOStrategy;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public interface InputStrategy {
	public void readGraphFromFiles(String fileName, Graph<Vertex, Edge> graph);
}
