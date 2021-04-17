package helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edge.Edge;
import edge.SameMovieHyperEdge;
import graph.Graph;
import vertex.Vertex;
import vertex.*;
import edge.*;
public class ParseCommandHelper {
	
	/**
	 * parse and execute the command, which can match the command input.
	 * @param string, the command we input.
	 * @param graph, the graph we operate.
	 */
	public static void parseAndExecuteCommand(String string, Graph<Vertex, Edge> graph) {
		Matcher addVertexMatcher = Pattern.compile("vertex --add \"(\\w+)\" \"(\\w+)\"").matcher(string);
		if(addVertexMatcher.matches()) {
			String label = addVertexMatcher.group(1);
			String type = addVertexMatcher.group(2);
			addVertexByType(label, type, graph);
		}
		Matcher deleteVertexMatcher = Pattern.compile("vertex --delete \"(\\w+)\"").matcher(string);
		if(deleteVertexMatcher.matches()) {
			String regex = deleteVertexMatcher.group(1);
			Pattern regexPattern = Pattern.compile(regex);
			for(Vertex x: graph.vertices()) {
				if(regexPattern.matcher(x.getLabel()).find())
					graph.removeVertex(x);
			}
		}
		Matcher addEdgeMatcher = Pattern.compile("edge --add \"(\\w+)\" \"(\\w+)\" (weighted=(\\w) )?"
				+ "(\\d+ )?(directed=(\\w) )?\"(\\w+)\" \"(\\w+)\"").matcher(string);
		if(addEdgeMatcher.matches()) {
			String label = addEdgeMatcher.group(1);
			String type = addEdgeMatcher.group(2);
			String weight = addEdgeMatcher.group(4);
			String v1 = addEdgeMatcher.group(6);
			String v2 = addEdgeMatcher.group(7);
			addEdgeByType(label, type, weight, v1, v2, graph);
		}
		Matcher deleteEdgeMatcher = Pattern.compile("edge --delete \"(\\w+)\"").matcher(string);
		if(deleteEdgeMatcher.matches()) {
			String regex = deleteEdgeMatcher.group(1);
			Pattern regexPattern = Pattern.compile(regex);
			for(Edge e: graph.edges()) {
				if(regexPattern.matcher(e.getLabel()).find())
					graph.removeEdge(e);
			}
		}
		Matcher addHyperEdgeMatcher = Pattern.compile("hyperedge --add \"(\\w+)\" \"(\\w+)\" ([\\w,\"]+)").matcher(string);
		if(addHyperEdgeMatcher.matches()) {
			String label = addHyperEdgeMatcher.group(1);
			String type = addHyperEdgeMatcher.group(2);
			String vertices = addHyperEdgeMatcher.group(3);
			if(!type.equals("SameMovieHyperEdge"))
				return;
			List<Vertex> allVertices = new ArrayList<>();
			Vertex vertex = null;
			for(String x: vertices.split(",")) {
				vertex = null;
				for(Vertex y: graph.vertices()) {
					if(y.getLabel().equals(x))
						vertex = y;
				}
				if(vertex != null)
					allVertices.add(vertex);
				else return;
			}
			SameMovieHyperEdge edge = new SameMovieHyperEdge(label, -1);
			edge.addVertices(allVertices);
			graph.addEdge(edge);
		}
	}
	
	/**
	 * add different kinds of vertices.
	 * @param label, the label of the vertex
	 * @param type, the type of the vertex.
	 * @param graph, the graph we operate.
	 */
	public static void addVertexByType(String label, String type, Graph<Vertex, Edge> graph) {
		if (type.equals("Actor")) {
			Actor new_vertex = new Actor(label);
			graph.addVertex(new_vertex);
		}
		if (type.equals("Computer")) {
			Computer new_vertex = new Computer(label);
			graph.addVertex(new_vertex);
		}
		if (type.equals("Director")) {
			Director new_vertex = new Director(label);
			graph.addVertex(new_vertex);
		}
		if (type.equals("Movie")) {
			Movie new_vertex = new Movie(label);
			graph.addVertex(new_vertex);
		}
		if (type.equals("Person")) {
			Person new_vertex = new Person(label);
			graph.addVertex(new_vertex);
		}
		if (type.equals("Router")) {
			Router new_vertex = new Router(label);
			graph.addVertex(new_vertex);
		}
		if (type.equals("Server")) {
			Server new_vertex = new Server(label);
			graph.addVertex(new_vertex);
		}
		if (type.equals("WirelessRouter")) {
			WirelessRouter new_vertex = new WirelessRouter(label);
			graph.addVertex(new_vertex);
		}
		if (type.equals("Word")) {
			Word new_vertex = new Word(label);
			graph.addVertex(new_vertex);
		}
	}
	
	public static void addEdgeByType(String label, String type, String weight, String v1, 
			String v2, Graph<Vertex, Edge> graph) {
		if (type.equals("CommentConnection")) {
			CommentConnection new_edge = new CommentConnection(label, Double.valueOf(weight));
			List<Vertex> allVertices = new ArrayList<>();
			Vertex vertex1 = null;
			Vertex vertex2 = null;
			for(Vertex v: graph.vertices()) {
				if(v1.equals(v.getLabel()))
					vertex1 = v;
				else if(v2.equals(v.getLabel()))
					vertex2 = v;
			}
			if(vertex1 != null && vertex2 != null) {
				allVertices.add(vertex1);
				allVertices.add(vertex2);
			}
			new_edge.addVertices(allVertices);
			graph.addEdge(new_edge);
		}
		else if (type.equals("ForwardConnection")) {
			ForwardConnection new_edge = new ForwardConnection(label, Double.valueOf(weight));
			List<Vertex> allVertices = new ArrayList<>();
			Vertex vertex1 = null;
			Vertex vertex2 = null;
			for(Vertex v: graph.vertices()) {
				if(v1.equals(v.getLabel()))
					vertex1 = v;
				else if(v2.equals(v.getLabel()))
					vertex2 = v;
			}
			if(vertex1 != null && vertex2 != null) {
				allVertices.add(vertex1);
				allVertices.add(vertex2);
			}
			new_edge.addVertices(allVertices);
			graph.addEdge(new_edge);
		}
		else if (type.equals("FriendConnection")) {
			FriendConnection new_edge = new FriendConnection(label, Double.valueOf(weight));
			List<Vertex> allVertices = new ArrayList<>();
			Vertex vertex1 = null;
			Vertex vertex2 = null;
			for(Vertex v: graph.vertices()) {
				if(v1.equals(v.getLabel()))
					vertex1 = v;
				else if(v2.equals(v.getLabel()))
					vertex2 = v;
			}
			if(vertex1 != null && vertex2 != null) {
				allVertices.add(vertex1);
				allVertices.add(vertex2);
			}
			new_edge.addVertices(allVertices);
			graph.addEdge(new_edge);
		}
		else if (type.equals("MovieActorRelation")) {
			MovieActorRelation new_edge = new MovieActorRelation(label, Double.valueOf(weight));
			List<Vertex> allVertices = new ArrayList<>();
			Vertex vertex1 = null;
			Vertex vertex2 = null;
			for(Vertex v: graph.vertices()) {
				if(v1.equals(v.getLabel()))
					vertex1 = v;
				else if(v2.equals(v.getLabel()))
					vertex2 = v;
			}
			if(vertex1 != null && vertex2 != null) {
				allVertices.add(vertex1);
				allVertices.add(vertex2);
			}
			new_edge.addVertices(allVertices);
			graph.addEdge(new_edge);
		}
		else if (type.equals("MovieDirectorRelation")) {
			MovieDirectorRelation new_edge = new MovieDirectorRelation(label, Double.valueOf(weight));
			List<Vertex> allVertices = new ArrayList<>();
			Vertex vertex1 = null;
			Vertex vertex2 = null;
			for(Vertex v: graph.vertices()) {
				if(v1.equals(v.getLabel()))
					vertex1 = v;
				else if(v2.equals(v.getLabel()))
					vertex2 = v;
			}
			if(vertex1 != null && vertex2 != null) {
				allVertices.add(vertex1);
				allVertices.add(vertex2);
			}
			new_edge.addVertices(allVertices);
			graph.addEdge(new_edge);
		}
		else if (type.equals("NetworkConnection")) {
			NetworkConnection new_edge = new NetworkConnection(label, Double.valueOf(weight));
			List<Vertex> allVertices = new ArrayList<>();
			Vertex vertex1 = null;
			Vertex vertex2 = null;
			for(Vertex v: graph.vertices()) {
				if(v1.equals(v.getLabel()))
					vertex1 = v;
				else if(v2.equals(v.getLabel()))
					vertex2 = v;
			}
			if(vertex1 != null && vertex2 != null) {
				allVertices.add(vertex1);
				allVertices.add(vertex2);
			}
			new_edge.addVertices(allVertices);
			graph.addEdge(new_edge);
		}
		else if (type.equals("WordEdge")) {
			WordEdge new_edge = new WordEdge(label, Double.valueOf(weight));
			List<Vertex> allVertices = new ArrayList<>();
			Vertex vertex1 = null;
			Vertex vertex2 = null;
			for(Vertex v: graph.vertices()) {
				if(v1.equals(v.getLabel()))
					vertex1 = v;
				else if(v2.equals(v.getLabel()))
					vertex2 = v;
			}
			if(vertex1 != null && vertex2 != null) {
				allVertices.add(vertex1);
				allVertices.add(vertex2);
			}
			new_edge.addVertices(allVertices);
			graph.addEdge(new_edge);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
