package factory;

import edge.Edge;
import edge.NetworkConnection;
import java.util.List;
import edge.*;
import vertex.*;
public class NetworkConnectionFactory extends EdgeFactory{
	@Override
	public Edge createEdge(String label, double weight, List<Vertex> vertices) {
		Edge edge = new NetworkConnection(label, weight);
		edge.addVertices(vertices);
		return edge;
	}
}
