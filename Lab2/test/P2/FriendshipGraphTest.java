package P2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import P2.*;

public class FriendshipGraphTest {
	// Test strategy
	//   - addVertexTest(): New some Person objects, then add them into the graph,
	//     then call the method vertices() to check add successfully or not.
	//   - addEdgeTest(): Similarly to the addVertexTest(), add the Edge object into
	//     the graph, then check the Person.friends contains the other person of the  
	//     edge or not.
	//   - getDistanceTest(): According to the graph we created just now, we can check 
	//     the distance of any two vertex in the graph.
	//     When we are creating the graph, we should make some isolated points, which 
	//     means have no edges with other vertex, this can make sure our test strategy 
	//     more completed. 
	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    FriendshipGraph graph = new FriendshipGraph();
   	Person a = new Person("A");
   	Person b = new Person("B");
   	Person c = new Person("C");
   	Person d = new Person("D");
   	Person e = new Person("E");
   	Person f = new Person("F");
   	Person g = new Person("G");
   	/**
     * Test addVertex
     */
    @Test
    public void addVertexTest() {
    	graph.addVertex(a);
    	graph.addVertex(b);
    	graph.addVertex(c);
    	graph.addVertex(d);
    	graph.addVertex(e);
    	graph.addVertex(f);
    	graph.addVertex(g);
    	assertTrue(graph.graph.vertices().contains(a));
    	assertTrue(graph.graph.vertices().contains(b));
    	assertTrue(graph.graph.vertices().contains(c));
    	assertTrue(graph.graph.vertices().contains(d));
    	assertTrue(graph.graph.vertices().contains(e));
    	assertTrue(graph.graph.vertices().contains(f));
    	assertTrue(graph.graph.vertices().contains(g));
    	
    }
    
    /**
     * Test addEdge
     */
    
    @Test
    public void addEdgeTest() {
    	graph.addVertex(a);
    	graph.addVertex(b);
    	graph.addVertex(c);
    	graph.addVertex(d);
    	graph.addVertex(e);
    	graph.addVertex(f);
    	graph.addVertex(g);
    	graph.addEdge(a, b);
		graph.addEdge(b, a);
		graph.addEdge(a, c);
		graph.addEdge(c, a);
		graph.addEdge(a, d);
		graph.addEdge(d, a);
		graph.addEdge(b, f);
		graph.addEdge(f, b);
		graph.addEdge(c, e);
		graph.addEdge(e, c);
		graph.addEdge(f, e);
		graph.addEdge(e, f);
		graph.addEdge(c, d);
		graph.addEdge(d, c);
		assertTrue(graph.graph.sources(a).containsKey(b));
		assertTrue(graph.graph.sources(a).containsKey(c));
		
		
    }
    /**
     * Test getDistance
     */
    
    @Test
    public void getDistanceTest() {
    	graph.addVertex(a);
    	graph.addVertex(b);
    	graph.addVertex(c);
    	graph.addVertex(d);
    	graph.addVertex(e);
    	graph.addVertex(f);
    	graph.addVertex(g);
    	graph.addEdge(a, b);
		graph.addEdge(b, a);
		graph.addEdge(a, c);
		graph.addEdge(c, a);
		graph.addEdge(a, d);
		graph.addEdge(d, a);
		graph.addEdge(b, f);
		graph.addEdge(f, b);
		graph.addEdge(c, e);
		graph.addEdge(e, c);
		graph.addEdge(f, e);
		graph.addEdge(e, f);
		graph.addEdge(c, d);
		graph.addEdge(d, c);
    	assertEquals(1, graph.getDistance(a, b));
    	assertEquals(2, graph.getDistance(a, f));
    	assertEquals(0, graph.getDistance(a, a));
    	assertEquals(-1, graph.getDistance(a, g));
    	assertEquals(1, graph.getDistance(e, f));
    }
}
