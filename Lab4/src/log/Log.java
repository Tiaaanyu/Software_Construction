package log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
public class Log {
	public static Logger logger = Logger.getRootLogger();

//    public static void main(String... args) {
//        logger.setLevel(Level.INFO);    //可以去掉
//        logger.info("this is an info");
//        logger.warn("this is a warn");
//        logger.error("this is an error");
//        logger.fatal("this is a fatal");
//    }

}
