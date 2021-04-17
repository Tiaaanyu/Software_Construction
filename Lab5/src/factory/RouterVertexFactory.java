package factory;

import vertex.Router;
import vertex.Vertex;


public class RouterVertexFactory extends VertexFactory {
	@Override
	public Vertex createVertex(String label, String[] args) {
		Vertex vertex = new Router(label);
		vertex.fillVertexInfo(args);
		return vertex;
	}
}
