package MyException;

public class LackOfPropertyException extends Exception{
	public LackOfPropertyException() {
		super();
	}
	@Override
	public String getMessage() {
		return "The file lack of properties, please change an input file.";
	}
}
