package IOStrategy;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public interface OutPutStrategy {
	public void writeToFilesFromGraph(String fileName, Graph<Vertex, Edge> graph);
}
