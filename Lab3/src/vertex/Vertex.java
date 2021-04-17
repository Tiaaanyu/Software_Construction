package vertex;


/**
 * The abstract class for all kinds of the vertices.
 * @author muty
 * 
 */

public abstract class Vertex {
	
	private final String label;      //the label of the vertex.
	
// Abstraction function:
//   -label is the label of each vertex, so different vertex cannot have the same label;
//    label is the only symbol of the vertex.
//   -Vertex class can create a Vertex object.
// Representation invariant:
//   -label cannot be null.
// Safety from rep exposure:
//   -There's no method return the mutable.


	/**
	 * Constructor of Vertex.
	 * @param label, String object, which can not be null.
	 */
	public Vertex(String label) {
		this.label = label;
	}
	
	/**
	 * Filling the info of the vertex.
	 * @param args, an array, which stores the info of the vertex.
	 */
	public abstract void fillVertexInfo(String[] args);
	
	/**
	 * Get the label.
	 * @return this.label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Override the toString() method.
	 * To show the String of vertex, different kinds of Vertex has 
	 * different shows.
	 * @return the label
	 */
	@Override
	public String toString() {
		return label;
	}
	
	/**
	 * Override the the equals() method.
	 * Determine the two Vertex objects are the same or not.
	 * @return true if equal, false if not equal.
	 */
	@Override
	public boolean equals(Object that) {
		if(that == null) {
			return false;
		}
		if(this == that) {
			return true;
		}
		if(that instanceof Vertex) {
			Vertex vertex = (Vertex) that;
			return vertex.getLabel().equals(label);
		}
		return false;
	}
	
	/**
	 * Override the hashCode() method.
	 * Compute the hash code of a Vertex object.
	 * @return the new hash code of a Vertex object.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + label.hashCode();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
