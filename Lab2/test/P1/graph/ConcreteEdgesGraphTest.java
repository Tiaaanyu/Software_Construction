/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;
import P1.graph.ConcreteEdgesGraph;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   - test for empty graph 
    //   - test the graph without edges
    //   - test formal graph
    @Test
    public void toStringofEmptyGraphTest() {
    	Graph<String> graph = emptyInstance();
    	assertEquals("The graph is empty!", graph.toString());
    }
    
    @Test 
    public void toStringofOnlyVertexGraphTest() {
    	Graph<String> graph = emptyInstance();
    	graph.add("A");
    	graph.add("B");
    	assertEquals("The graph has no edges!", graph.toString());
    }
    
    @Test 
    public void toStringofNormalGraphTest() {
    	Graph<String> graph = emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.add("d");
    	graph.set("a", "b", 3);
    	graph.set("c", "d", 4);
    	graph.set("b", "c", 5);
    	assertTrue(graph.toString().indexOf("a->b3") > -1);
    	assertTrue(graph.toString().indexOf("c->d4") > -1);
    	assertTrue(graph.toString().indexOf("b->c5") > -1);
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   - Test every method in the class Edge to make sure our code and logic is okay.
    //   - getSourceTest()(getTargetTest()): new an Edge object, and test the method 
    //     getSource()(getTarget())
    //   - getWeightTest(): similarly, new an Edge object, and test the method getWeight(),
    //     assert the actual fact and our planned result is equal or not.
    //   - toStringTest(): test the String returned by toString() is right or not.
    
    @Test
    public void getSourceTest() {
    	//Graph<String> graph = emptyInstance();
    	Edge<String> e = new Edge<>("source", "target", 5);
    	assertEquals("source", e.getSource());
	}
    @Test
    public void getTargetTest() {
    	Edge<String> edge = new Edge<>("source", "target", 5);
    	assertEquals("target", edge.getTarget());
    }
    @Test
    public void getWeightTest() {
    	Edge<String> edge = new Edge<>("source", "target", 5);
    	assertEquals(5, edge.getWeight());
    }
    @Test
    public void toStringTest() {
    	Edge<String> edge = new Edge<>("source", "target", 5);
    	assertEquals("source->target5 ", edge.toString());
    }
    
    
    
}
