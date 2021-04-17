package factory;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public abstract class ConcreteGraphFactory {
	public abstract Graph<Vertex, Edge> concreteGraph(String fileName);
}
