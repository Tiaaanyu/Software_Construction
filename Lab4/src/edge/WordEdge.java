package edge;

import java.util.Set;

import vertex.Vertex;

public class WordEdge extends DirectedEdge{
	
	// Abstraction function:
	//   -label represents the label of the edge.
	//   -weight represents the weight of the edge.
	//   -allVertices is a List object which contains all Vertex in the edge.
	// Representation invariant:
	//   -label cannot be null.
	//   -weight must more than 0 as long as it is not the hyper edge.
	//   -After adding vertex to the edge, allVertices.size() must be 2. 
	// Safety from rep exposure:
	//   -Some methods such as vertices() will return a Set, which is a mutable
	//    collection, so, to prevent exposing the representation to the client, 
	//    we must make the defensive copy.
	
	/**
	 * Constructor of the WordEdge class.
	 * @param label
	 * @param weight
	 */
	public WordEdge(String label, double weight) {
		super(label, weight);
	}

}
