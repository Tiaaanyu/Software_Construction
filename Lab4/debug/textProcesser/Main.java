package textProcesser;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		SearchEngine google = new SearchEngine();
		google.processText("src/testText.txt");
		google.trie.display();
		
		System.out.println("\n");
		System.out.println(google.map.toString());
				
	}
}
