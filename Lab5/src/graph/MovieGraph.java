package graph;

import java.util.Iterator;

import edge.Edge;
import edge.HyperEdge;
import edge.SameMovieHyperEdge;
import vertex.Vertex;

public class MovieGraph extends ConcreteGraph<Vertex, Edge>{
	
	// Abstraction function:
	//    -allVertices is a list where stored all the vertices in the graph.
	//    -allEdges is a list where stored all the edges in the graph.
	//    -The ConcreteGraph class can create a graph.
	// Representation invariant:
	//    -allVertices is a list of L objects, which does not allow the same element.
	//    -allEdges is a list of E objects, which also does not allow the same element.
	//    -Assert allVertices.size() greater than allEdges.size() * 2 or equal to
	//     allEdges.size() * 2.
	//    -When removing a vertex from the graph, if the vertex is in a hyper graph, we
	//	   should specially pay attention to the hyper edge is legal or not, and if it is
	//	   legal, we should not delete the edge.
	// Safety from rep exposure:
	//    -Some methods will return a Set such as the method vertices(), so to avoid the 
	//     representation exposure to the client, we must have the defensive copies.
	//    -In the graph, we have a private and final collection to store the vertices and 
	//     the edges, so when we need return the vertices or the edges, we can make a copy 
	//     to make sure only we know the data.
	
	@Override 
	public boolean removeVertex(Vertex v) {
		
		if(allVertices.contains(v)) {
			allVertices.remove(v);
			Iterator<Edge> it = allEdges.iterator();
			while (it.hasNext()) {
				Edge edge = (Edge) it.next();
				if(edge.vertices().contains(v)) {
					if(edge instanceof HyperEdge) {
						if(edge.vertices().size() >= 2) {
							((HyperEdge) edge).removeVertex(v);
							continue; 
						}
							                    // hyper edge is already legal.
						else it.remove();
					}
					else it.remove();
				}
			}
			
			
			return true;
		}
		else return false;
	}

}
