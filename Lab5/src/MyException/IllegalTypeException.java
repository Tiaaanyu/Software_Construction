package MyException;

public class IllegalTypeException extends Exception {
	public IllegalTypeException() {
		super();
	}
	@Override
	public String getMessage() {
		return "There's illegal type of the vertices or edges, please change an input file";
	}
}
