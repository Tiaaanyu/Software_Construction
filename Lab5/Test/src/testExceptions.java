package src;

import static org.junit.Assert.assertEquals;

import javax.sound.midi.VoiceStatus;

import org.junit.Test;

import MyException.*;
import factory.*;
import edge.*;
import vertex.*;
import graph.*;
public class testExceptions {
	
	//  -Test strategy
	//   the method in the factory which can throw my exceptions is parse(), 
	//	 so I just call it to parse and execute the string which can appear
	//   in the input files, and then input the false string to make sure it 
	//   can throw the expected exceptions.
	
	/**
	 * Test for the illegal input for "Y"("N") instead of "Yes"("No")
	 * @throws Exception
	 */
	@Test(expected = YorNException.class)
	public void testYorNException() throws Exception {
		//Graph<Vertex, Edge> g = new GraphPoetFactory().createGraph("src/GraphPoet.txt");
		GraphPoetFactory graphPoetFactory = new GraphPoetFactory();
		graphPoetFactory.createGraph("src/GraphPoet.txt");
		new GraphPoetFactory().parse("Edge=<ab,WordNeighborhood,1,a,b,Y>");
	}
	
	/**
	 * Test for the illegal for the edges which should have weight, but actually
	 * client didn't input it.
	 * @throws Exception
	 */
	@Test(expected = NoWeightException.class)
	public void testNoWeightException() throws Exception {
		new GraphPoetFactory().parse("Edge=<ac,WordNeighborhood,a,c,Yes>");
	}
	
	/**
	 * Test for the lack of property.
	 * @throws Exception
	 */
	@Test(expected = LackOfPropertyException.class)
	public void testLackOfPropertyException() throws Exception {
		new MovieGraphFactory().parse("Vertex=<FrankDarabont,Director,<M>>");
	}
	
	/**
	 * Test for the lack of components
	 * @throws Exception
	 */
	@Test(expected = LackOfComponentsException.class)
	public void testLackOfComponentsException() throws Exception {
		new MovieGraphFactory().parse("Vertex=<Movie,<1994,USA,9.3>>");
	}
	
	/**
	 * Test for the illegal weight.
	 * @throws Exception
	 */
	@Test(expected = IllegalWeightException.class)
	public void testIllegalWeightException() throws Exception {
		new SocialNetworkFactory().parse("Edge=<LiFriendLiu,FriendConnection,1.8,Li,Liu,Yes>");
	}
	
	/**
	 * Test for the illegal type of vertex or edge input.
	 * @throws Exception
	 */
	@Test(expected = IllegalTypeException.class)
	public void testIllegalType() throws Exception {
		new GraphPoetFactory().parse("Edge=<ab,Relation,1,a,b,Yes>");
	}
	
	/**
	 * Test for the illegal label exception.
	 * @throws Exception
	 */
	@Test(expected = IllegalLabelException.class)
	public void testIllegalLabelException() throws Exception {
		new GraphPoetFactory().parse("Edge=<ab!,WordNeighborhood,1,a,b,Yes>");
	}
	
	/**
	 * Test for the illegal hyper edge exception.
	 * @throws Exception
	 */
	@Test(expected = HyperEdgeException.class)
	public void testHyperEdgeException() throws Exception {
		new MovieGraphFactory().parse("HyperEdge=<ActorsInSR,SameMovieHyperEdge,{TimRobbins}>");
	}
	
	@Test
	public void testGetGraphName() {
		GraphPoetFactory graphPoetFactory1 = new GraphPoetFactory();
		graphPoetFactory1.createGraph("src/GraphPoet1.txt");
		assertEquals("MyGraphPoet", graphPoetFactory1.getGraphName());
	}
	/**
	 * Test for the exception that the edge contains vertex which is not in the graph yet.
	 * @throws Exception
	 */
	@Test(expected = NoVertexException.class)
	public void testNoVertexException() throws Exception {
		GraphPoetFactory graph = new GraphPoetFactory();
		graph.parse("GraphName=PoetGraph");
		graph.parse("EdgeType=WordNeighborhood");
		graph.parse("GraphType=GraphPoet");
		graph.parse("VertexType=Word");
		graph.parse("Vertex=<a,Word>");
		graph.parse("Vertex=<b,Word>");
		graph.parse("Edge=<ab,WordNeighborhood,1,a,b,Yes>");
		graph.parse("Edge=<ac,WordNeighborhood,1,a,c,Yes>");
	}
	/**
	 * Test for the robustness
	 */
	@Test
	public void testExceptions() {
		GraphPoetFactory graphPoetFactory1 = new GraphPoetFactory();
		graphPoetFactory1.createGraph("src/GraphPoet1.txt");
		GraphPoetFactory graphPoetFactory2 = new GraphPoetFactory();
		graphPoetFactory2.createGraph("src/GraphPoet2.txt");
		GraphPoetFactory graphPoetFactory3 = new GraphPoetFactory();
		graphPoetFactory3.createGraph("src/GraphPoet3.txt");
		GraphPoetFactory graphPoetFactory4 = new GraphPoetFactory();
		graphPoetFactory4.createGraph("src/GraphPoet4.txt");
		GraphPoetFactory graphPoetFactory5 = new GraphPoetFactory();
		graphPoetFactory5.createGraph("src/GraphPoet5.txt");
		GraphPoetFactory graphPoetFactory6 = new GraphPoetFactory();
		graphPoetFactory6.createGraph("src/GraphPoet6.txt");
		GraphPoetFactory graphPoetFactory7 = new GraphPoetFactory();
		graphPoetFactory7.createGraph("src/GraphPoet7.txt");
		GraphPoetFactory graphPoetFactory8 = new GraphPoetFactory();
		graphPoetFactory8.createGraph("src/GraphPoet8.txt");
	}
	
	
}
