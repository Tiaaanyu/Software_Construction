/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //  - test empty graph
    //  - test a graph only contains vertices
    //  - test a normal graph
    
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
    	graph.add("C");
    	assertEquals("The graph has no edges!", graph.toString());
    }
    
    @Test 
    public void toStringofNormalGraphTest() {
    	Graph<String> graph = emptyInstance();
    	graph.add("A");
    	graph.add("B");
    	graph.add("C");
    	graph.add("D");
    	graph.add("E");
    	graph.set("A", "B", 3);
    	graph.set("A", "C", 4);
    	graph.set("B", "D", 5);
    	graph.set("D", "E", 6);
    	String str = graph.toString();
    	assertTrue(str.indexOf("A->B3 ") > -1);
    	assertTrue(str.indexOf("A->C4 ") > -1);
    	assertTrue(str.indexOf("B->D5 ") > -1);
    	assertTrue(str.indexOf("D->E6 ") > -1);
    	assertTrue(str.indexOf("A->D3 ") == -1);
    }
    
    
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   - getNameTest(): new a Vertex object, and return the label of the vertex.
    //   - setSourceWeightTest() / setTargetWeightTest():
    //     new a Vertex object, and call the method setSourceWeight()(setTargetWeight())
    //     for the first time, determine the actual result is the same as the expected
    //     result. Then change the weight, call the method for the second time, still 
    //     judge the two results.
    //   - removeSourceEdgeTest() / removeTargetEdgeTest():
    //     new a Vertex object, set a weighted edge first, make sure we set the edge successfully,
    //     then remove it, then remove it, at this time, we test the sources / targets contains the 
    //     other vertex of the edge or not. If there is no the other vertex, it proves that our code
    //     module is right.
    //   - getTargetsTest(): return a Vertex's targets, call the method assertEqual is okay.
    //   - toStringTest(): test the returned String and our expected String is equal or not. 
    
    @Test
    public void getNameTest() {
    	Vertex<String> ver = new Vertex<>("vertex");
    	assertEquals("vertex", ver.getName());
    }
    @Test
    public void setSourceWeightTest() {
    	Integer a = new Integer(3);
    	Integer b = new Integer(6);
    	Vertex<String> ver = new Vertex<>("vertex");
    	ver.setSourceWeight("A", 3);
    	assertEquals(a, ver.getSources().get("A"));
    	ver.setSourceWeight("A", 6);
    	assertEquals(b, ver.getSources().get("A"));
    }
    @Test
    public void setTargetWeightTest() {
    	Integer a = new Integer(3);
    	Integer b = new Integer(6);
    	Vertex<String> ver = new Vertex<>("vertex");
    	ver.setTargetWeight("A", 3);
    	assertEquals(a, ver.getTargets().get("A"));
    	ver.setTargetWeight("A", 6);
    	assertEquals(b, ver.getTargets().get("A"));
    }
    @Test
    public void removeSourceEdgeTest() {
    	Vertex<String> ver = new Vertex<>("vertex");
    	ver.setSourceWeight("target", 3);
    	ver.removeSourceEdge("target");
    	assertFalse(ver.getSources().keySet().contains("target"));
    }
    @Test
    public void removeTargetEdgeTest() {
    	Vertex<String> ver = new Vertex<>("vertex");
    	ver.setTargetWeight("target", 3);
    	ver.removeTargetEdge("target");
    	assertFalse(ver.getTargets().keySet().contains("target"));
    }
    @Test
    public void getSourcesTest() {
    	Vertex<String> ver = new Vertex<>("vertex");
    	ver.setSourceWeight("target1", 3);
    	ver.setSourceWeight("target2", 4);
    	ver.setSourceWeight("target3", 5);
    	Map<String, Integer> sources = ver.getSources();
    	assertEquals(3, sources.keySet().size());
    	assertEquals(3, sources.get("target1").intValue());
    	assertEquals(4, sources.get("target2").intValue());
    	assertEquals(5, sources.get("target3").intValue());
    }
    @Test
    public void getTargetsTest() {
    	Vertex<String> ver = new Vertex<>("vertex");
    	ver.setTargetWeight("target1", 3);
    	ver.setTargetWeight("target2", 4);
    	ver.setTargetWeight("target3", 5);
    	Map<String, Integer> targets = ver.getTargets();
    	assertEquals(3, targets.keySet().size());
    	assertEquals(3, targets.get("target1").intValue());
    	assertEquals(4, targets.get("target2").intValue());
    	assertEquals(5, targets.get("target3").intValue());
    }
    @Test
    public void toStringTest() {
    	Vertex<String> ver = new Vertex<>("vertex");
    	ver.setSourceWeight("target1", 3);
    	ver.setSourceWeight("target2", 4);
    	ver.setSourceWeight("target3", 5);
    	String str = ver.toString();
    	assertTrue(str.indexOf("vertex->target1") != -1);
    	assertTrue(str.indexOf("vertex->target2") != -1);
    	assertTrue(str.indexOf("vertex->target3") != -1);
    	assertTrue(str.indexOf("vertex->ta3") == -1);
    }
}
