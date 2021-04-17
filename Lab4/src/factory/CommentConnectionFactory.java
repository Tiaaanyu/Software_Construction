package factory;

import java.util.List;

import edge.CommentConnection;
import edge.Edge;
import vertex.Vertex;

public class CommentConnectionFactory extends EdgeFactory {
	@Override
	public Edge createEdge(String label, double weight, List<Vertex> vertices) {
		Edge edge = new CommentConnection(label, weight);
		edge.addVertices(vertices);
		return edge;
	}
}
