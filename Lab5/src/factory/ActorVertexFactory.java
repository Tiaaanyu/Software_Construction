package factory;

import vertex.Actor;
import vertex.Vertex;
import vertex.Word;

public class ActorVertexFactory extends VertexFactory {
	@Override
	public Vertex createVertex(String label, String[] args) {
		Vertex vertex = new Actor(label);
		vertex.fillVertexInfo(args);
		return vertex;
	}
}
