package factory;

import vertex.Director;
import vertex.Vertex;
import vertex.Word;

public class DirectorVertexFactory extends VertexFactory {
	@Override
	public Vertex createVertex(String label, String[] args) {
		Vertex vertex = new Director(label);
		vertex.fillVertexInfo(args);
		return vertex;
	}
}
