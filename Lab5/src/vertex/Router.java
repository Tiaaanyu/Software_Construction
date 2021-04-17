package vertex;

public class Router extends Vertex {
	private final String vertexType = "Router";
	private String IPAddress = new String();
	
// Abstraction function:
//  -label is the label of each vertex, so different vertex cannot have the same label;
//   label is the only symbol of the vertex.
//  -Vertex class can create a Vertex object.
//	-IPAddress is a String which represents the IP address of the router.
//Representation invariant:
//  -label cannot be null.
//	-The IP address will be split into four parts, each part ranges [0, 255].
//Safety from rep exposure:
//  -There's no method return the mutable.
	/**
	 * Constructor of the Person class.
	 * @param label the label of the vertex.
	 */
	public Router(String label) {
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
	 * Get the IP address.
	 * @return String, the IP address.
	 */
	public String getIPAddress() {
		return IPAddress;
	}
	
	/**
	 * Filling the info of the vertex.
	 */
	@Override
	public void fillVertexInfo(String[] args) {
		IPAddress = args[0];
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
		String string = new String();
		string += ("<\"" + this.getLabel() + "\", \"" + vertexType + "\", <");
		string += ("\"" + IPAddress + "\">>");
		return string;
	}
	
	
	/**
	 * Override the method equals().
	 */
	@Override
	public boolean equals(Object that) {
		if(that == null) {
			return false;
		}
		if(this == that) {
			return true;
		}
		if(that instanceof Router) {
			Router other = (Router) that;
			return other.getLabel().equals(this.getLabel()) && other.getIPAddress().equals(IPAddress);
		}
		return false;
	}
	
	/**
	 * Override the method hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + IPAddress.hashCode();
		result = result * 31 + this.getLabel().hashCode();
		return result;
	}

}
