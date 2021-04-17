import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edge.Edge;
import graph.ConcreteGraph;
import graph.Graph;
import vertex.Vertex;

public class GraphTest {
	
	//Test strategy:
	//	-Concrete an empty graph, then check the number of its edges and vertices,
	//	 if the method is legal in logical, 
	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    /**
     * Test of the static method empty().
     */
    @Test
    public void emptyTest() {
    	Graph<Vertex, Edge> graph = Graph.empty();
    	assertTrue(graph != null);
    	assertEquals(0, graph.edges().size());
    	assertEquals(0, graph.vertices().size());
    }
}
