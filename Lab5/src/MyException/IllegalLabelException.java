package MyException;

public class IllegalLabelException extends Exception {
	public IllegalLabelException() {
		super();
	}
	@Override
	public String getMessage() {
		return "The Label contains illegal character(s), please check out or "
				+ "change an input file.";
	}
}
