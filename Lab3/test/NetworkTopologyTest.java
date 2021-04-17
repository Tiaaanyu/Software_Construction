import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edge.NetworkConnection;
import graph.NetworkTopology;
import vertex.Router;
import vertex.Vertex;

public class NetworkTopologyTest {
	
	//Test strategy:
	//	-Same as ConcreteGraph class.
	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    NetworkTopology graph = new NetworkTopology();
    NetworkConnection edge_0 = new NetworkConnection("edge_0", 1);
    NetworkConnection edge_1 = new NetworkConnection("edge_1", 2);
    Router A = new Router("A");
    Router B = new Router("B");
    Router C = new Router("C");
    Router D = new Router("D");
    
    /**
	 * Test of the method addVertex().
	 */
	@Test
	public void addVertexTest() {
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		assertEquals(4, graph.vertices().size());
		assertTrue(graph.vertices().contains(A));
		assertTrue(graph.vertices().contains(B));
		assertTrue(graph.vertices().contains(D));
		assertFalse(graph.addVertex(B));   // the graph has already have the vertex B, so assert false here.
	}
	
	/**
	 * Test of the method removeVertex().
	 */
	@Test 
	public void removeVertexTest() {
		List<Vertex> vertices = new ArrayList<>();
		vertices.add(C);
		vertices.add(D);
		edge_0.addVertices(vertices);
		graph.addVertex(D);
		assertFalse(graph.removeVertex(C));
		graph.addVertex(C);
		graph.addEdge(edge_0);
		assertTrue(graph.removeVertex(D));
		assertFalse(graph.vertices().contains(D));
		assertFalse(graph.edges().contains(edge_0));
		assertTrue(graph.addVertex(D));
	}
	
	/**
	 * Test of the method vertices().
	 */
	@Test
	public void verticesTest() {
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		assertEquals(4, graph.vertices().size());
		assertTrue(graph.vertices().contains(A));
		assertTrue(graph.vertices().contains(D));
	}
	
	/**
	 * Test of the method sources().
	 */
	@Test
	public void sourcesTest() {
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		List<Vertex> vertices = new ArrayList<>();
		vertices.add(A);
		vertices.add(B);
		edge_0.addVertices(vertices);
		graph.addEdge(edge_0);
		assertTrue(graph.sources(B).keySet().contains(A));
		assertTrue(graph.sources(B).get(A) == 1);
	}
	
	/**
	 * Test of the method targets().
	 */
	@Test
	public void targetsTest() {
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		List<Vertex> vertices = new ArrayList<>();
		vertices.add(A);
		vertices.add(B);
		edge_0.addVertices(vertices);
		graph.addEdge(edge_0);
		assertTrue(graph.targets(A).keySet().contains(B));
		assertTrue(graph.targets(A).get(B) == 1);
	}
	
	/**
	 * Test of the method addEdge().
	 */
	@Test
	public void addEdgeTest() {
		List<Vertex> vertices = new ArrayList<>();
		vertices.add(C);
		vertices.add(D);
		edge_0.addVertices(vertices);
		assertTrue(graph.addEdge(edge_0));
		assertFalse(graph.addEdge(edge_0));
	}
	
	/**
	 * Test of the method removeEdge().
	 */
	@Test
	public void removeEdgeTest() {
		List<Vertex> vertices = new ArrayList<>();
		vertices.add(C);
		vertices.add(D);
		edge_0.addVertices(vertices);
		List<Vertex> vertices1 = new ArrayList<>();
		vertices.add(A);
		vertices.add(B);
		edge_1.addVertices(vertices1);
		graph.addEdge(edge_0);
		graph.addEdge(edge_1);
		assertTrue(graph.removeEdge(edge_0));
		assertFalse(graph.edges().contains(edge_0));
		assertTrue(graph.edges().size() == 1);
	}
	
	/**
	 * Test of the method edges().
	 */
	@Test
	public void edgesTest() {
		List<Vertex> vertices = new ArrayList<>();
		vertices.add(C);
		vertices.add(D);
		edge_0.addVertices(vertices);
		List<Vertex> vertices1 = new ArrayList<>();
		vertices.add(A);
		vertices.add(B);
		edge_1.addVertices(vertices1);
		graph.addEdge(edge_0);
		graph.addEdge(edge_1);
		assertEquals(2, graph.edges().size());
		assertTrue(graph.edges().contains(edge_0));
		assertTrue(graph.edges().contains(edge_1));
	}
}
