package MyException;

public class NoVertexException extends Exception{
	public NoVertexException() {
		super();
	}
	@Override
	public String getMessage() {
		return "The edge contains vertex not in the graph, Please change an input file.";
	}
}
