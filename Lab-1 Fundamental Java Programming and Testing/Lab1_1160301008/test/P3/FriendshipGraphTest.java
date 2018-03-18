package P3;
import P3.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class FriendshipGraphTest {
	
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
    	assertEquals(a, graph.Vertex.get(0));
    	assertEquals(b, graph.Vertex.get(1));
    	assertEquals(c, graph.Vertex.get(2));
    	assertEquals(d, graph.Vertex.get(3));
    	assertEquals(e, graph.Vertex.get(4));
    	assertEquals(f, graph.Vertex.get(5));
    	assertEquals(g, graph.Vertex.get(6));
    }
    
    /**
     * Test addEdge
     */
    
    @Test
    public void addEdgeTest() {
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
		assertEquals(0, a.friends.indexOf(b));
		assertEquals(1, a.friends.indexOf(c));
		//assertEquals(1, graph.edge[a.num][d.num]);
		//assertEquals(1, graph.edge[c.num][d.num]);
		//assertEquals(1, graph.edge[b.num][f.num]);
		//assertEquals(1, graph.edge[e.num][f.num]);
		
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
