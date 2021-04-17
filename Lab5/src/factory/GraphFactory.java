package factory;

import edge.Edge;
import graph.Graph;
import hep.aida.bin.StaticBin1D;
import vertex.Vertex;

public abstract class GraphFactory {
	public abstract Graph<Vertex, Edge> createGraph(String filePath);
	public abstract void writeGraph(String fileName, Graph<Vertex, Edge> graph);
}
