package factory;

import java.util.List;

import edge.Edge;
import edge.FriendConnection;
import vertex.Vertex;

public class FriendConnectionFactory extends EdgeFactory {
	@Override
	public Edge createEdge(String label, double weight, List<Vertex> vertices) {
		Edge edge = new FriendConnection(label, weight);
		edge.addVertices(vertices);
		return edge;
	}
}
