package factory;

import vertex.Router;
import vertex.Vertex;
import vertex.WirelessRouter;

public class WirelessRouterFactory extends VertexFactory{
	@Override
	public Vertex createVertex(String label, String[] args) {
		Vertex vertex = new WirelessRouter(label);
		vertex.fillVertexInfo(args);
		return vertex;
	}
}
