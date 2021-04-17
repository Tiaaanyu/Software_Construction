package MyException;

public class YorNException extends Exception{
	public YorNException() {
		super();
	}
	@Override
	public String getMessage() {
		return "Please use 'Yes'('No') instead of 'Y'('N'), then change an input file.";
	}
}
