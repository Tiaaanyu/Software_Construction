package vertex;

public class Word extends Vertex {
	
	private final String vertexType = "Word";
	
// Abstraction function:
//  -label is the label of each vertex, so different vertex cannot have the same label;
//   label is the only symbol of the vertex.
//  -Vertex class can create a Vertex object.
//Representation invariant:
//  -label cannot be null.
//Safety from rep exposure:
//  -There's no method return the mutable.

	
	/**
	 * Constructor of the Word class.
	 * @param label the label of the vertex.
	 */
	public Word(String label) {
		super(label);
	}
	
	/**
	 * Get the type of Vertex.
	 * @return the type of the vertex.
	 */
	public String getType() {
		return vertexType;
	}
	
	/**
	 * For the word class, we don't have the method fillVertexInfo().
	 */
	@Override
	public void fillVertexInfo(String[] args) {
		
	}
	
	/**
	 * Get the label of the vertex.
	 */
	@Override
	public String getLabel() {
		return super.getLabel();
	}
	
	/**
	 * Override the method toString(), to output the vertex's info.
	 */
	@Override
	public String toString() {
		String string = "<\"" + this.getLabel() + "\", \"" + vertexType + "\">";
		return string;
	}
}
