package factory;

import vertex.Vertex;
import vertex.Word;

public class WordVertexFactory extends VertexFactory {
	@Override
	public Vertex createVertex(String label, String[] args) {
		Vertex vertex = new Word(label);
		vertex.fillVertexInfo(args);
		return vertex;
	}
}
