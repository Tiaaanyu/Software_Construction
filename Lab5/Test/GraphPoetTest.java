import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edge.Edge;
import edge.WordEdge;
import graph.GraphPoet;
import vertex.Vertex;
import vertex.Word;

public class GraphPoetTest {
	
	//Test strategy:
	//	-the only different in the poet graph is it can have a loop in edges
	//	 which means an edge, the source and the target is the same vertex.
	//	 so I make a loop, to test the method.
	
	GraphPoet graph = new GraphPoet();
	Word A = new Word("A");
	Word B = new Word("B");
	Word C = new Word("C");
	Word D = new Word("D");
	WordEdge edge_0 = new WordEdge("edge_0", 0);
	WordEdge edge_1 = new WordEdge("edge_1", 1);
	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    /**
     * Test of the sources().
     */
    @Test
    public void sourcesTest() {
    	List<Vertex> vertices = new ArrayList<>();
    	vertices.add(A);
    	//vertices.add(B);
    	edge_0.addVertices(vertices);
    	graph.addEdge(edge_0);
    	//System.out.println(helper.GraphMetrics.betweennessCentrality(graph, A));
    	assertEquals(1, graph.sources(A).keySet().size());
    	assertTrue(graph.sources(A).keySet().contains(A));
    }
    
    /**
     * Test of the targets().
     */
    @Test
    public void targetsTest() {
    	List<Vertex> vertices = new ArrayList<>();
    	vertices.add(A);
    	vertices.add(A);
    	edge_0.addVertices(vertices);
    	graph.addEdge(edge_0);
    	assertEquals(1, graph.targets(A).keySet().size());
    	assertTrue(graph.targets(A).keySet().contains(A));
    }
}
