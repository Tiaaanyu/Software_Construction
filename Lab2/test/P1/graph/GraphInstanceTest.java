/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

import junit.extensions.TestSetup;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   - testAdd(): Add a vertex to the graph for the first time, then add the same
	//     label vertex for the second time, expected false this time. Then add a different
	//     label vertex, still return true;
    //   - testSet(): New an Edge object, then set the weight, first-time add must return 0,
	//     then set a different weight, the method will return the old weight, at last set 
	//     the weight as 0, which will delete the edge, at this time, if we set this edge with 
	//     a weight again, it will return 0 again.
	//   - testSources()(testTargets()): Add some edges to the graph, then check the set sources(targets) is okay.
	//   - testRemove(): Add some vertices to the graph, then call the method remove() to remove
	//     them, then check the return bool value is okay.
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    
    @Test
    public void testAdd() {
    	Graph<String> graph = emptyInstance();
    	assertTrue("expected true here", graph.add("vertex"));
    	assertFalse(graph.add("vertex"));
    	assertTrue("expected true here", graph.add("vertex1"));
    }
    @Test
    public void testSet() {
    	Graph<String> graph = emptyInstance();
    	graph.add("source");
    	graph.add("target");
    	assertEquals(0, graph.set("source", "target", 5));
    	assertEquals(5, graph.set("source", "target", 6));
    	assertEquals(6, graph.set("source", "target", 0));
      	assertEquals(0, graph.set("source", "target", 5));
    	
    }
    @Test
    public void testRemove() {
    	Graph<String> graph = emptyInstance();
		assertTrue(graph.add("vertex2"));
		assertTrue(graph.remove("vertex2"));
		assertFalse(graph.remove("vertex3"));
	}
    @Test
    public void testSources() {
    	Graph<String> graph = emptyInstance();
    	assertTrue(graph.add("a"));
    	assertTrue(graph.add("b"));
    	assertTrue(graph.add("c"));
    	assertTrue(graph.add("d"));
    	assertEquals(0, graph.set("a", "b", 3));
    	assertEquals(0, graph.set("a", "d", 5));
    	assertEquals(0, graph.set("c", "d", 4));
    	assertEquals(0, graph.set("b", "d", 6));
    	assertTrue(graph.sources("d").containsKey("a"));
    	assertTrue(graph.sources("d").containsKey("b"));
    	assertTrue(graph.sources("d").containsKey("c"));
    	
    }
    @Test
    public void testTargets() {
    	//Graph<String> graph = emptyInstance();
    	Graph<String> graph = emptyInstance();
    	assertTrue(graph.add("1"));
    	assertTrue(graph.add("2"));
    	assertTrue(graph.add("3"));
    	assertTrue(graph.add("4"));
    	assertEquals(0, graph.set("1", "2", 3));
    	assertEquals(0, graph.set("1", "4", 5));
    	assertEquals(0, graph.set("3", "4", 4));
    	assertEquals(0, graph.set("2", "4", 6));
    	assertTrue(graph.targets("1").containsKey("2"));
    	assertTrue(graph.targets("1").containsKey("4"));
    	assertTrue(graph.targets("2").containsKey("4"));
    }
    
    
    
    
    
    
    
    
    
    
}
