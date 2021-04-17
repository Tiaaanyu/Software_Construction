package factory;

import vertex.Movie;
import vertex.Vertex;
import vertex.Word;

public class MovieVertexFactory extends VertexFactory {
	@Override
	public Vertex createVertex(String label, String[] args) {
		Vertex vertex = new Movie(label);
		vertex.fillVertexInfo(args);
		return vertex;
	}
}
