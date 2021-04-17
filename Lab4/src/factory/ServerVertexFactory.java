package factory;

import vertex.Server;
import vertex.Vertex;

public class ServerVertexFactory extends VertexFactory {
	@Override
	public Vertex createVertex(String label, String[] args) {
		Vertex vertex = new Server(label);
		vertex.fillVertexInfo(args);
		return vertex;
	}

}
