package factory;

import java.util.List;

import edge.Edge;
import edge.MovieActorRelation;
import edge.WordEdge;
import vertex.Vertex;

public class MovieActorRelationFactory extends EdgeFactory {
	@Override
	public Edge createEdge(String label, double weight, List<Vertex> vertices) {
		Edge edge = new MovieActorRelation(label, weight);
		edge.addVertices(vertices);
		return edge;
	}
}
