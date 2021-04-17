package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ParseConversionEvent;

import edge.Edge;
import edge.MovieActorRelation;
import edge.MovieDirectorRelation;
import edge.SameMovieHyperEdge;
import graph.Graph;
import graph.MovieGraph;
import vertex.Actor;
import vertex.Director;
import vertex.Movie;
import vertex.Vertex;

public class MovieGraphFactory extends GraphFactory{
	private Graph<Vertex, Edge> graph;
	private String graphName = new String();
	private List<String> vertexTypes = new ArrayList<>();
	private List<String> edgeTypes = new ArrayList<>(); 
	private boolean parse(String string) {
		if(string.equals("GraphType=MovieGraph")) {      
			graph = new MovieGraph();
			return true;
		}
		Matcher matcher = Pattern.compile("GraphName=(\\w+)").matcher(string);    // match the graph name.
		if (matcher.matches()) {
			graphName = matcher.group(1);
			//System.out.println(graphName);
			return true;
		}
		Matcher matcher2 = Pattern.compile("VertexType=(\\w+),(\\w+),(\\w+)").matcher(string);    // match the vertex type.
		if(matcher2.matches()) {
			vertexTypes.add(matcher2.group(1));
			vertexTypes.add(matcher2.group(2));
			vertexTypes.add(matcher2.group(3));
			return true;
		}
		Matcher matcher3 = Pattern.compile("Vertex=<(\\w+),(\\w+),<([MF]),(\\d+)>>").matcher(string);  // match the vertex.
		if(matcher3.matches()) {
			if(matcher3.group(2).equals("Actor")) {
				Actor actor = new Actor(matcher3.group(1));
				actor.fillVertexInfo(new String[] {matcher3.group(3), matcher3.group(4)});
				graph.addVertex(actor);
				return true;
			}
			if(matcher3.group(2).equals("Director")) {
				Director director = new Director(matcher3.group(1));
				director.fillVertexInfo(new String[] {matcher3.group(3), matcher3.group(4)});
				graph.addVertex(director);
				return true;
			}
		}
		Matcher matcher4 = Pattern.compile("Vertex=<(\\w+),Movie,<(\\d{4}),(\\w+),([\\d\\.]+)>>").matcher(string);
		if(matcher4.matches()) {
			Movie movie = new Movie(matcher4.group(1));
			movie.fillVertexInfo(new String[]{matcher4.group(2), matcher4.group(3), matcher4.group(4)});
			graph.addVertex(movie);
			return true;
		}
		Matcher matcher5 = Pattern.compile("EdgeType=(\\w+),(\\w+),(\\w+)").matcher(string);
		if(matcher5.matches()) {
			edgeTypes.add(matcher5.group(1));
			edgeTypes.add(matcher5.group(2));
			edgeTypes.add(matcher5.group(3));
			return true;
		}
		Matcher matcher6 = Pattern.compile("Edge=<(\\w+),(\\w+),([\\-\\d]+),(\\w+),(\\w+),(\\w+)>").matcher(string);  // match the edge
		if(matcher6.matches()) {
			//System.out.println(Integer.valueOf(matcher6.group(3)));
			Edge edge = null;
			if(matcher6.group(2).equals("MovieActorRelation")) {
				edge = new MovieActorRelation(matcher6.group(1), Integer.valueOf(matcher6.group(3)));
			}
			else if(matcher6.group(2).equals("MovieDirectorRelation")) {
				edge = new MovieDirectorRelation(matcher6.group(1), Integer.valueOf(matcher6.group(3)));
			}
			if(edge == null) return false;
			Vertex v1 = null;
			Vertex v2 = null;
			for(Vertex x: graph.vertices()) {
				if(x.getLabel().equals(matcher6.group(4)))
					v1 = x;
				else if(x.getLabel().equals(matcher6.group(5)))
					v2 = x;
			}
			if(v1 != null && v2 != null) {
				edge.addVertices(Arrays.asList(v1, v2));
				graph.addEdge(edge);
				return true;
			}
			else return false;
		}
		Matcher matcher7 = Pattern.compile("HyperEdge=<(\\w+),SameMovieHyperEdge,\\{(.*)\\}>").matcher(string);
		if(matcher7.matches()) {
			Edge edge = new SameMovieHyperEdge(matcher7.group(1), -1);
			List<Vertex> vertices = new ArrayList<>();
			String allVertices = matcher7.group(2);
			String[] Vertex = allVertices.split(",");
			for(String x: Vertex) {
				if(!x.matches("(\\w+)"))
					return false;
			}
			for(String x: Vertex) {
				if(x.length() == 0)
					continue;
				boolean flag = false;
				for(Vertex y: graph.vertices()) {
					if(y.getLabel().equals(x)) {
						vertices.add(y);
						flag = true;
						break;
					}
				}
				if(!flag) {
					System.out.println("The vertex " + x + " is not in the graph yet.");
					return false;
				}
			}
			edge.addVertices(vertices);
			graph.addEdge(edge);
			return true;
		}
		
		System.out.println("Wrong input!  " + string);
		return false;
		
	}
	@Override
	public Graph<Vertex, Edge> createGraph(String filePath) {
		//return new MovieGraph();
		BufferedReader bf = null;
		String string;
		try {
			bf = new BufferedReader(new FileReader(filePath));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Wrong path!");
		}
		try {
			//System.out.println("sss");
			string = bf.readLine();
			while(string != null) {
				parse(string);
				string = bf.readLine();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		return graph;
	}
	public String getGraphName() {
		return graphName;
	}
	public static void main(String[] args) {
		MovieGraphFactory mgf = new MovieGraphFactory();
		mgf.createGraph("src/MovieGraph.txt");
		for(Vertex x: mgf.graph.vertices()) {
			System.out.println(x.toString());
		}
		for(Edge x: mgf.graph.edges()) {
			System.out.println(x.toString());
		}
	}

}
