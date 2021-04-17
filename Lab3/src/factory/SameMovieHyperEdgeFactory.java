package factory;

import java.util.List;

import edge.Edge;
import edge.SameMovieHyperEdge;
import edge.WordEdge;
import vertex.Vertex;

public class SameMovieHyperEdgeFactory extends EdgeFactory{
	@Override
	public Edge createEdge(String label, double weight, List<Vertex> vertices) {
		Edge edge = new SameMovieHyperEdge(label, weight);
		edge.addVertices(vertices);
		return edge;
	}
}
