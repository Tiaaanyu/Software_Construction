package factory;

import helper.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private boolean parse(String string) {
		if(string.equals("GraphType=GraphPoet")) {
			graph = new GraphPoet();
			return true;
		}
		Matcher matcher = Pattern.compile("GraphName=(\\w+)").matcher(string);
		if(matcher.matches()) {
			graphName = matcher.group(1);
			return true;
		}
		Matcher matcher2 = Pattern.compile("VertexType=(\\w+)").matcher(string);
		if(matcher2.matches()) {
			vertexTypes.add(matcher2.group(1));
			return true;
		}
		Matcher matcher3 = Pattern.compile("Vertex=<(\\w),Word>").matcher(string);
		if(matcher3.matches()) {
			Word word = new Word(matcher3.group(1));
			graph.addVertex(word);
			return true;
		}
		Matcher matcher4 = Pattern.compile("EdgeType=(\\w+)").matcher(string);
		if(matcher4.matches()) {
			edgeTypes.add(matcher4.group(1));
			return true;
		}
		Matcher matcher5 = Pattern.compile("Edge=<(\\w+),WordNeighborhood,(\\d+),(\\w),(\\w),(\\w+)>").matcher(string);
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
			if(v1 != null && v2 != null) {
				edge.addVertices(Arrays.asList(v1, v2));
				graph.addEdge(edge);
				return true;
			}
			else return false;
		}
		System.out.println("Wrong input!  " + string);
		return false;
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
		try {
			string = bf.readLine();
			while(string != null) {
				parse(string);
				string = bf.readLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		return graph;
	}
	public static void main(String[] args) {
		GraphPoetFactory mgf = new GraphPoetFactory();
		mgf.createGraph("src/GraphPoet.txt");
		//Word xWord = new Word("a");
		for(Vertex x: mgf.graph.vertices()) {
			System.out.println(x.toString());
		}
		for(Edge x: mgf.graph.edges()) {
			System.out.println(x.toString());
		}
		
}
	}
