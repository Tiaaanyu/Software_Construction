package MyException;

public class LackOfComponentsException extends Exception {
	public LackOfComponentsException() {
		super();
		//System.out.println("The file lack of components, please change an input file.");
	}
	@Override
	public String getMessage() {
		return "The file lack of components, please change an input file.";
	}
}
