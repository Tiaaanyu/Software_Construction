package MyException;

public class NoWeightException extends Exception {
	public NoWeightException() {
		super();
	}
	@Override
	public String getMessage() {
		return "There's no weight for some edges, please change an input file.";
	}
}
