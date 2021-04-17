import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.experimental.theories.Theories;

import edge.Edge;
import edge.WordEdge;
import graph.ConcreteGraph;
import graph.Graph;
import vertex.Vertex;
import vertex.Word;
import helper.*;

public class ConcreteGraphTest {
	
	// Test strategy:
	//	-Previous remarks: create a graph through class ConcreteGraph, and then create some edges 
	//   and vertices. It is worth mentioning that, the edges cannot be "Edge", but some other instance,
	//   such as WordEdge, the vertices is the same. So I used WordEdge and Word here.
	//	-addVertexTest(): create some vertices, first add them into the graph, assert true here, then add 
	//   a vertex which has already exists in the graph, assert false here.
	//	-removeVertexTest(): create some vertices and edges, when we call the method removeVertex, we should
	//   pay attention to two things: the vertex is disappeared and the edges which have the vertex we want 
	//   delete in its vertices().
	//  -verticesTest(): it is easy to be tested, we can just test the Set which method edges() returned, 
	//   test the size and the elements in it. 
	//  -sourcesTest() / targetsTest(): this two methods are similar, so them have the similar strategy, 
	//   for directed edges, (here I am coding according to directed graph) if the edge is like that:
	//   a ---6---> b, the source is "a" and the target is "b".
	//	-addEdgeTest() / removeEdgeTest(): similarly as the addVertexTest() and the removeVertexTest(), 
	//  -edgesTest(): determine the size and elements of the Set which method edges() returned.
	//  
	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    ConcreteGraph<Vertex, Edge> graph = new ConcreteGraph<Vertex, Edge>();
    Vertex A = new Word("A");
    Vertex B = new Word("B");
    Vertex C = new Word("C");
    Vertex D = new Word("D");
	Edge edge_0 = new WordEdge("edge_0", 1);
	Edge edge_1 = new WordEdge("edge_1", 1);
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
		assertFalse(graph.sources(A).keySet().contains(B));
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
		//System.out.println(helper.GraphMetrics.betweennessCentrality(graph, C));
		assertEquals(2, graph.edges().size());
		assertTrue(graph.edges().contains(edge_0));
		assertTrue(graph.edges().contains(edge_1));
	}

	
	
	
	
	
	
	
}
