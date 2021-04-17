import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edge.Edge;
import edge.HyperEdge;
import edge.MovieActorRelation;
import edge.SameMovieHyperEdge;
import graph.MovieGraph;
import vertex.Actor;
import vertex.Vertex;

public class MovieGraphTest {
	
	//Test strategy: 
	//	-For the hyper edge, when deleting a vertex which is also a member of the edge.
	//	 we should determine if the hyper edge is still legal after the operation, if
	//	 legal, then keep this edge, else delete it.
	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    MovieGraph graph = new MovieGraph();
    SameMovieHyperEdge edge_0 = new SameMovieHyperEdge("edge_0", -1);
    Actor actor_0 = new Actor("actor_0");
    Actor actor_1 = new Actor("actor_1");
    Actor actor_2 = new Actor("actor_2");
    Actor actor_3 = new Actor("actor_3");
    Actor actor_4 = new Actor("actor_4");
    @Test
    public void removeVertexTest() {
    	List<Vertex> vertices = new ArrayList<>();
    	vertices.add(actor_0);
    	vertices.add(actor_1);
    	vertices.add(actor_2);
    	vertices.add(actor_3);
    	vertices.add(actor_4);
    	edge_0.addVertices(vertices);
    	graph.addVertex(actor_0);
    	graph.addVertex(actor_1);
    	graph.addVertex(actor_2);
    	graph.addVertex(actor_3);
    	graph.addVertex(actor_4);
    	graph.addEdge(edge_0);
    	assertTrue(graph.removeVertex(actor_0));
    	//System.out.println(graph.edges().size());
    	//for(Edge x: graph.edges())
    	//	System.out.println(x.vertices().size());
    	assertTrue(graph.edges().contains(edge_0));
    	assertEquals(4, edge_0.vertices().size());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}