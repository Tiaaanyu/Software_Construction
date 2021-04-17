/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

//import static org.junit.Assert.assertArrayEquals;

//import java.awt.Checkbox;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    private final List<String> corpusList = new ArrayList<>();
    // Abstraction function:
    //    Represents a poem generator, combined input string and the corpus, 
    //    we can get a beautiful poem.
    // Representation invariant:
    //    graph is a non-null graph
    //    corpusList is a list contains all words in the corpus.
    // Safety from rep exposure:
    //    All fields are private and final.
    //    graph is a mutable type, so references to it are provided for the 
    //    client to mutate.
    //    corpusList is a mutable list, so we must have a method getCorpusList()
    //    to avoid sharing the fields with client so that client cannot modify it.
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	wordsOfCorpus(corpus);
    	createGraphFromCorpus(corpusList);
    	checkRep();
    }
    
    public void checkRep() {
    	assert graph != null;
    }
    /**
     * read the file in the pc, to create the graph
     * @param corpus   the file need to be read. 
     * @throws IOException
     */
    
   public void wordsOfCorpus(File corpus) throws IOException{
	   
	   try(Scanner sc = new Scanner(new BufferedReader(new FileReader(corpus)))) {
		   while(sc.hasNext()) {
			   corpusList.add(sc.next().toLowerCase());
		   }
	   }
   }
   /**
    * create the graph from the corpusList
    * @param list which stores all the words in the file.
    */
   public void createGraphFromCorpus(List<String> list) {
	   
	   for(int i = 0; i < list.size() - 1; ++i) {
		   graph.add(list.get(i));
		   graph.add(list.get(i + 1));
		   int previousWeight = graph.set(list.get(i), list.get(i + 1), 1);
		   graph.set(list.get(i), list.get(i + 1), previousWeight + 1);

	   }
	   
   }
   public List<String> getCorpusList() {
	   List<String> list = new ArrayList<>();
	   for(int i = 0; i < corpusList.size(); ++i) {
		   list.add(corpusList.get(i));
	   }
	   return list;
   }
   public Graph<String> getGraph() {
	   Graph<String> gp = graph;
	   return gp;
   }
    
    
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	String[] inputWords = input.split(" ");
    	List<String> inputWordsList = new ArrayList<>();
    	Map<Integer, String> map = new HashMap<>();
    	for(int i = 0; i < inputWords.length; ++i) {
    		inputWordsList.add(inputWords[i]);
    	}
    	for(int i = 0; i < inputWordsList.size() - 1; ++i) {
    		int maxWeight = 0;
    		String middle = new String();
    		for(String x: graph.targets(inputWordsList.get(i).toLowerCase()).keySet()) {
    			/*
    			if(graph.targets(inputWordsList.get(i).toLowerCase()).get(x).intValue() == 1) {
    				for(String y: graph.targets(x).keySet()) {
    					if(y.equals(inputWordsList.get(i + 1).toLowerCase()) && graph.targets(x).get(y).intValue() == 1) {
    						map.put(i + 1, x);
    					}
    				}
    			}
    			else continue;
    			*/
    			for(String y: graph.targets(x).keySet()) {
    				if(y.equals(inputWordsList.get(i + 1).toLowerCase())) {
    					
    					if(graph.targets(inputWordsList.get(i).toLowerCase()).get(x).intValue() + graph.targets(x).get(y).intValue() > maxWeight) {
    						maxWeight = graph.targets(inputWordsList.get(i).toLowerCase()).get(x).intValue() + graph.targets(x).get(y).intValue();
    						middle = x;
    					}
    				}
    			}
    		map.put(i + 1, middle);
    		
    		
    		}
    	}
    	List<String> list = new ArrayList<>();
    	for(int i = 0; i < inputWordsList.size(); ++i) {
    		if(map.containsKey(i)) {
    			list.add(map.get(i));
    			map.remove(i);
    			i -= 1;
    		}
    		else list.add(inputWordsList.get(i));
    	}
    	String poem = new String();
    	for(int i = 0; i < list.size(); ++i) {
    		if(i == list.size() - 1) 
    			poem += list.get(i);
    		else poem += (list.get(i) + " ");
    	}
    	return poem;
    }
    /**
     * Returns a string representation of the poetry.
     */
    @Override public String toString() {
    	return graph.toString();
    }
    
}
