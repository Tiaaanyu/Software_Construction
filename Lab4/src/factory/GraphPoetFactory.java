package factory;

import helper.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Level;
import org.apache.log4j.*;

import MyException.IllegalLabelException;
import MyException.IllegalTypeException;
import MyException.IllegalWeightException;
import MyException.LackOfComponentsException;
import MyException.NoVertexException;
import MyException.NoWeightException;
import MyException.YorNException;
import edge.Edge;
import edge.WordEdge;
import graph.Graph;
import graph.GraphPoet;
import vertex.Vertex;
import vertex.Word;


public class GraphPoetFactory extends GraphFactory {
	private Graph<Vertex, Edge> graph;
	private String graphName = new String();
	private List<String> vertexTypes = new ArrayList<>();
	private List<String> edgeTypes = new ArrayList<>(); 
	//public static Logger logger = Logger.getRootLogger();
	
	public void parse(String string) throws Exception{
		
		if(string.equals("GraphType=GraphPoet")) {
			log.Log.logger.info("GraphType=GraphPoet");
			graph = new GraphPoet();
			//return true;
		}
		Matcher matcher = Pattern.compile("GraphName=(\\w+)").matcher(string);
		if(matcher.matches()) {
			graphName = matcher.group(1);
			log.Log.logger.info("GraphName is " + graphName);
			//return true;
		}
		Matcher matcher2 = Pattern.compile("VertexType=(\\w+)").matcher(string);
		if(matcher2.matches()) {
			vertexTypes.add(matcher2.group(1));
			log.Log.logger.info("VertexType is " + matcher2.group(1));
			//return true;
		}
		Matcher matcher3 = Pattern.compile("Vertex=<(\\w+),Word>").matcher(string);
		if(matcher3.matches()) {
			Word word = new Word(matcher3.group(1));
			graph.addVertex(word);
			log.Log.logger.info("Addvertex: " + matcher3.group(1));
			return;
		}
		Matcher matcher4 = Pattern.compile("EdgeType=(\\w+)").matcher(string);
		if(matcher4.matches()) {
			edgeTypes.add(matcher4.group(1));
			log.Log.logger.info("EdgeType is " + matcher4.group(1));
			//return true;
		}
//		if(vertexTypes.size() > 1 || edgeTypes.size() > 1)
//			throw new IllegalTypeException();
		Matcher matcher5 = Pattern.compile("Edge=<(\\w+),WordNeighborhood,(\\d+),(\\w),(\\w),Yes>").matcher(string);
		if(matcher5.matches()) {
			WordEdge edge = new WordEdge(matcher5.group(1), Integer.valueOf(matcher5.group(2)));
			Vertex v1 = null;
			Vertex v2 = null;
			for(Vertex x: graph.vertices()) {
				if(x.getLabel().equals(matcher5.group(3)))
					v1 = x;
				else if(x.getLabel().equals(matcher5.group(4)))
					v2 = x;
			}
			NoVertexException exception = new NoVertexException();
			if(v1 != null && v2 != null) {
				edge.addVertices(Arrays.asList(v1, v2));
				graph.addEdge(edge);
				log.Log.logger.info("Addedge: " + matcher5.group(1) + ", which contains vertices: " + 
				matcher5.group(3) + ", " + matcher5.group(4) + ", and the weight is " + matcher5.group(2));
				return;
			} else
				
					throw exception;
				
		}
		Matcher matcher8 = Pattern.compile("Edge=<(\\w+),WordNeighborhood,(-\\d+),(\\w),(\\w),Yes>").matcher(string);
		if(matcher8.matches()) {
			throw new IllegalWeightException();
		}
		Matcher matcher7 = Pattern.compile("Edge=<(\\w+),WordNeighborhood,(\\w),(\\w),Yes>").matcher(string);
		if(matcher7.matches()) {
			throw new NoWeightException();
		}
		//System.out.println("Wrong input!  " + string);
		//return false;
		Matcher matcher6 = Pattern.compile("Edge=<(\\w+),WordNeighborhood,(\\d+),(\\w),(\\w),Y>").matcher(string);
		if(matcher6.matches()) {
			YorNException execption1 = new YorNException();
			throw execption1;
		}
		else {
		if(string.contains("Person") || string.contains("Computer") || string.contains("Router")
				|| string.contains("Server") || string.contains("Movie")
				|| string.contains("Director") || string.contains("Actor")
				|| string.contains("Connection") || string.contains("Relation"))
			throw new IllegalTypeException();
		Matcher matcher9 = Pattern.compile("Edge=<(.*),WordNeighborhood,(\\d+),(\\w),(\\w),Yes>").matcher(string);
		if(matcher9.matches()) {
			throw new IllegalLabelException();
		}
		Matcher matcher10 = Pattern.compile("Vertex=<(.*),Word>").matcher(string);
		if(matcher10.matches()) {
			throw new IllegalLabelException();
		}
		}
		
		
	}
	
	public String getGraphName() {
		return graphName;
	}
	
	@Override
	public Graph<Vertex, Edge> createGraph(String filePath) {
		BufferedReader bf = null;
		String string;
		try {
			bf = new BufferedReader(new FileReader(filePath));
		} catch (Exception e) {
			System.out.println("Wrong path!");
		}
		if(bf != null) {
		try {
			string = bf.readLine();
			while(string != null) {
				try {
				parse(string);
				}
				catch (NoVertexException a) {
					System.out.println(a.getMessage());
					log.Log.logger.error("NoVertexException: " + a.getMessage());
					return graph;
				}
				catch (YorNException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("YorNException: " + e.getMessage());
					return graph;
				}
				catch (IllegalTypeException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("IllegalTypeException: " + e.getMessage());
					return graph;
				}
				catch (NoWeightException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("NoWeightException: " + e.getMessage());
					return graph;
				}
				catch (IllegalWeightException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("IllegalWeightException: " + e.getMessage());
					return graph;
				}
				catch (IllegalLabelException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("IllegalLabelException: " + e.getMessage());
					return graph;
				}
				string = bf.readLine();
			}
		} 
		catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		}
		
		return graph;
	}
//	public static void main(String[] args) {
//		GraphPoetFactory mgf = new GraphPoetFactory();
//		mgf.createGraph("src/GraphPoet.txt");
//		for(Vertex x: mgf.graph.vertices()) {
//			System.out.println(x.toString());
//		}
//		for(Edge x: mgf.graph.edges()) {
//			System.out.println(x.toString());
//		}
//		
//}
	}
