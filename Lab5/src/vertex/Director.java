package vertex;

public class Director extends Vertex {
	private final String vertexType = "Director";
	private String gender = new String();
	private int age = 0;
	
// Abstraction function:
//  -label is the label of each vertex, so different vertex cannot have the same label;
//   label is the only symbol of the vertex.
//	-age is the age of the person.
//  -gender is the gender of the person.
//  -Vertex class can create a Vertex object.
//Representation invariant:
//  -label cannot be null.
//  -gender can only be "M" or "F".
//	-age must be greater than 0.	
//Safety from rep exposure:
//  -There's no method return the mutable.

	/**
	 * Constructor of the Director class.
	 * @param label the label of the vertex.
	 */
	public Director(String label) {
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
	 * Filling the info of the vertex.
	 */
	@Override
	public void fillVertexInfo(String[] args) {
		//System.out.println(args[0]);
		//System.out.println(args[1]);
		gender = args[0];
		age = Integer.valueOf(args[1]).intValue();
		
	}
	
	/**
	 * Get the gender.
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Get the age.
	 * @return age.
	 */
	public int getAge() {
		return age;
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
		string += ("\"" + age + "\", ");
		string += ("\"" + gender + "\">>");
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
		if(that instanceof Director) {
			Director other = (Director) that;
			return other.getGender().equals(gender) && other.getAge() == age && other.getLabel().equals(this.getLabel());
		}
		return false;
	}
	
	/**
	 * Override the method hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 17;
		Integer a = (Integer) age;
		result = 31 * result + gender.hashCode();
		result = 31 * result + a.hashCode();
		result = 31 * result + this.getLabel().hashCode();
		return result;
	}

}
