import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Test;

import edge.CommentConnection;
import edge.Edge;
import graph.SocialNetwork;
import vertex.Person;

public class SocialNetworkTest {
	
	//Test strategy:
	//	-For all the methods which are override:
	//	 the one thing that needs a special attention is other edges' weight
	//   should be changed to make sure the total weight is 1.
	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    SocialNetwork graph = new SocialNetwork();
    Person p1 = new Person("p1");
    Person p2 = new Person("p2");
    Person p3 = new Person("p3");
    Person p4 = new Person("p4");
    CommentConnection edge_0 = new CommentConnection("edge_0", 1);
    CommentConnection edge_1 = new CommentConnection("edge_1", 0.4);
    CommentConnection edge_2 = new CommentConnection("edge_2", 0.4);
    
    /**
     * Test of the removeVertex().
     */
    @Test
    public void removeVertexTest() {
    	edge_0.addVertices(Arrays.asList(p1, p2));
    	edge_1.addVertices(Arrays.asList(p3, p4));
    	edge_2.addVertices(Arrays.asList(p2, p4));
    	graph.addEdge(edge_0);
    	graph.addEdge(edge_1);
    	graph.addEdge(edge_2);
    	graph.addVertex(p1);
    	graph.addVertex(p2);
    	graph.addVertex(p3);
    	graph.addVertex(p4);
    	assertTrue(graph.removeVertex(p1));
    	assertEquals(2, graph.edges().size());
    	assertTrue(edge_2.getWeight() == 0.625);
    	assertTrue(edge_1.getWeight() == 0.375);
    }
    
    /**
     * Test of the addEdge().
     */
    @Test
    public void addEdgeTest() {
    	edge_0.addVertices(Arrays.asList(p1, p2));
    	edge_1.addVertices(Arrays.asList(p3, p4));
    	edge_2.addVertices(Arrays.asList(p2, p4));
    	graph.addEdge(edge_0);
    	graph.addEdge(edge_1);
    	assertTrue(edge_0.getWeight() == 0.6);
    	assertTrue(edge_1.getWeight() == 0.4);
    	graph.addEdge(edge_2);
    	assertTrue(edge_0.getWeight() == 0.36);
    	assertTrue(edge_1.getWeight() == 0.24);
    	assertTrue(edge_2.getWeight() == 0.4);
    }
    
    /**
     * Test of removeEdge().
     */
    @Test
    public void removeEdgeTest() {
    	edge_0.addVertices(Arrays.asList(p1, p2));
    	edge_1.addVertices(Arrays.asList(p3, p4));
    	edge_2.addVertices(Arrays.asList(p2, p4));
    	graph.addEdge(edge_0);
    	graph.addEdge(edge_1);
    	graph.addEdge(edge_2);
    	graph.addVertex(p1);
    	graph.addVertex(p2);
    	graph.addVertex(p3);
    	graph.addVertex(p4);
    	graph.removeEdge(edge_0);
    	assertEquals(2, graph.edges().size());
    	assertTrue(edge_2.getWeight() == 0.625);
    	assertTrue(edge_1.getWeight() == 0.375);
    }
    
    /**
     * Test of setWeight().
     */
    @Test
    public void setWeightTest() {
    	edge_0.addVertices(Arrays.asList(p1, p2));
    	edge_1.addVertices(Arrays.asList(p3, p4));
    	edge_2.addVertices(Arrays.asList(p2, p4));
    	graph.addEdge(edge_0);
    	graph.addEdge(edge_1);
    	graph.addEdge(edge_2);
    	graph.addVertex(p1);
    	graph.addVertex(p2);
    	graph.addVertex(p3);
    	graph.addVertex(p4);
    	graph.setWeight(edge_0, 0.3);
    	System.out.println(edge_0.getWeight());
    	System.out.println(edge_1.getWeight());
    	System.out.println(edge_2.getWeight());
    	assertTrue(edge_0.getWeight() == 0.3);
    	assertTrue(Math.abs(edge_1.getWeight() - 0.2624) < 0.01);
    	assertTrue(Math.abs(edge_2.getWeight() - 0.4374) < 0.01);
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
