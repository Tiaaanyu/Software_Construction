/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   - wordsOfCorpusTest(): method wordsOfCorpus() will return a list of the words
	//     which are created by split file corpus by blank space, so, check some random
	//     words in the list is okay.
	//   - createGraphFromCorpusTest():
	//     check some properties of the created graph is okay, such as vertices.size()
	//     the position of a word in the list.
    
	File corpus = new File("src/P1/poet/mugar-omni-theater.txt");
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void wordsOfCorpusTest() throws IOException {
    	GraphPoet gp = new GraphPoet(corpus);
    	assertEquals(11, gp.getCorpusList().size());
    	assertTrue(gp.getCorpusList().contains("this"));
    	assertTrue(gp.getCorpusList().contains("system."));
    }
    @Test
    public void createGraphFromCorpusTest() throws IOException {
    	GraphPoet gp = new GraphPoet(corpus);
    	gp.createGraphFromCorpus(gp.getCorpusList());
    	assertEquals(11, gp.getGraph().vertices().size());
    	assertEquals(2, gp.getGraph().set("test", "of", 1));
   }
    }
