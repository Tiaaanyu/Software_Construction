package v1;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormat extends Formatter{
	@Override
	public String format(LogRecord lr) {
		return lr.getMessage() + "\r\n";
	}
}
