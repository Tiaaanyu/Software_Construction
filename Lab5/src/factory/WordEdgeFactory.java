package factory;

import java.util.List;

import edge.Edge;
import edge.WordEdge;
import vertex.Vertex;

public class WordEdgeFactory extends EdgeFactory {
	@Override
	public Edge createEdge(String label, double weight, List<Vertex> vertices) {
		Edge edge = new WordEdge(label, weight);
		edge.addVertices(vertices);
		return edge;
	}
}
