package MyException;

public class HyperEdgeException extends Exception{
	public HyperEdgeException() {
		super();
	}
	@Override
	public String getMessage() {
		return "The HyperEdge is illegal, please change an input.";
	}
}
