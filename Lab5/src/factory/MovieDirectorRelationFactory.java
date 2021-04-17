package factory;

import java.util.List;

import edge.Edge;
import edge.MovieDirectorRelation;
import edge.WordEdge;
import vertex.Vertex;

public class MovieDirectorRelationFactory extends EdgeFactory {
	@Override
	public Edge createEdge(String label, double weight, List<Vertex> vertices) {
		Edge edge = new MovieDirectorRelation(label, weight);
		edge.addVertices(vertices);
		return edge;
	}
}
