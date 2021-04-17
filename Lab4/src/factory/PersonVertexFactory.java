package factory;

import vertex.Person;
import vertex.Vertex;


public class PersonVertexFactory extends VertexFactory {
	@Override
	public Vertex createVertex(String label, String[] args) {
		Vertex vertex = new Person(label);
		vertex.fillVertexInfo(args);
		return vertex;
	}
}
