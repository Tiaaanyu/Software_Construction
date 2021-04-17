package factory;

import java.util.List;

import edge.Edge;
import edge.ForwardConnection;
import edge.WordEdge;
import vertex.Vertex;

public class ForwardConnectionFactory extends EdgeFactory {
	@Override
	public Edge createEdge(String label, double weight, List<Vertex> vertices) {
		Edge edge = new ForwardConnection(label, weight);
		edge.addVertices(vertices);
		return edge;
	}
}
