package graph;

import edge.Edge;
import vertex.Vertex;

public class NetworkTopology extends ConcreteGraph <Vertex, Edge>{
	// Abstraction function:
	//    -allVertices is a list where stored all the vertices in the graph.
	//    -allEdges is a list where stored all the edges in the graph.
	//    -The ConcreteGraph class can create a graph.
	// Representation invariant:
	//    -allVertices is a list of L objects, which does not allow the same element.
	//    -allEdges is a list of E objects, which also does not allow the same element.
	//    -Assert allVertices.size() greater than allEdges.size() * 2 or equal to
	//     allEdges.size() * 2.
	// Safety from rep exposure:
	//    -Some methods will return a Set such as the method vertices(), so to avoid the 
	//     representation exposure to the client, we must have the defensive copies.
	//    -In the graph, we have a private and final collection to store the vertices and 
	//     the edges, so when we need return the vertices or the edges, we can make a copy 
	//     to make sure only we know the data.

}
