package v1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	private static final String name = Main.class.getName();
	private static Logger logger = Logger.getLogger(name);
	private static FileHandler fileHandler = null;
	
	public static MonkeyGenerator setParameter(String filePath) throws SecurityException, IOException {
		MonkeyGenerator monkeyGenerator = null;
		logger.setUseParentHandlers(false);
		fileHandler = new FileHandler("src/log.txt", true);
		fileHandler.setFormatter(new LogFormat());
		logger.addHandler(fileHandler);
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String string = bufferedReader.readLine();
			Matcher matcher = null;
			if ((matcher = Pattern.compile(
          "^n\\s*=\\s*(\\d+),\\s*h\\s*=\\s*(\\d+),\\s*t\\s*=\\s*(\\d+),\\s*N\\s*=\\s*(\\d+),\\s*k\\s*=\\s*(\\d+),\\s*MV\\s*=\\s*(\\d+)")
          .matcher(string)).find()) {
				int ladderNum = Integer.parseInt(matcher.group(1));
        int rung = Integer.parseInt(matcher.group(2));
        int t = Integer.parseInt(matcher.group(3));
        int k = Integer.parseInt(matcher.group(5));
        int maxNum = Integer.parseInt(matcher.group(4));
        int maxSpeed = Integer.parseInt(matcher.group(6));
        List<Ladder> ladders = Collections.synchronizedList(new ArrayList<>());
        for(int i = 0; i < ladderNum; ++i) {
        	String name = "ladder " + i;
        	Ladder ladder = new Ladder(name, rung);
        	ladders.add(ladder);
        }
        monkeyGenerator = new MonkeyGenerator(t, k, maxNum, maxSpeed);
      	monkeyGenerator.setLadders(ladders);
      	monkeyGenerator.setLog(logger);
      	 logger.info("--------------------------------------------------------------------------------------------------------------------------------");
         logger.info("--------------------------------------------------------------------------------------------------------------------------------");
         logger.info("n:" + ladderNum + ", h:" + rung + ", t:" + t + ", k:" + k + ", N:" + maxNum + ", MV:" + maxSpeed);
			}
			if(bufferedReader != null)
				bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monkeyGenerator;
	}
	
	public static void main(String[] args) throws SecurityException, IOException {
		MonkeyGenerator monkeyGenerator = setParameter("src/parameter.txt");
		new Thread(monkeyGenerator).start();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
