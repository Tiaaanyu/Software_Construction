package MyException;

public class IllegalWeightException extends Exception {
	public IllegalWeightException() {
		super();
	}
	@Override
	public String getMessage() {
		return "The weight of some edges are illegal, please check out or change an input file";
	}
}
