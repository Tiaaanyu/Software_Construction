package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ParseConversionEvent;

import MyException.HyperEdgeException;
import MyException.IllegalLabelException;
import MyException.IllegalTypeException;
import MyException.LackOfComponentsException;
import MyException.LackOfPropertyException;
import MyException.NoVertexException;
import MyException.NoWeightException;
import MyException.YorNException;
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
	public boolean parse(String string) throws Exception{
		if(string.equals("GraphType=MovieGraph")) {      
			graph = new MovieGraph();
			log.Log.logger.info("GraphType=MovieGraph");
			return true;
		}
		Matcher matcher = Pattern.compile("GraphName=(\\w+)").matcher(string);    // match the graph name.
		if (matcher.matches()) {
			graphName = matcher.group(1);
			//System.out.println(graphName);
			log.Log.logger.info("GraphName is " + matcher.group(1));
			return true;
		}
		Matcher matcher2 = Pattern.compile("VertexType=(\\w+),(\\w+),(\\w+)").matcher(string);    // match the vertex type.
		if(matcher2.matches()) {
			vertexTypes.add(matcher2.group(1));
			vertexTypes.add(matcher2.group(2));
			vertexTypes.add(matcher2.group(3));
			log.Log.logger.info("VertexType are " + matcher2.group(1) + ", "
					+ matcher2.group(2) + ", " + matcher2.group(3) + ".");
			return true;
		}
		Matcher matcher3 = Pattern.compile("Vertex=<(\\w+),(\\w+),<([MF]),(\\d+)>>").matcher(string);  // match the vertex.
		if(matcher3.matches()) {
			if(matcher3.group(2).equals("Actor")) {
				Actor actor = new Actor(matcher3.group(1));
				actor.fillVertexInfo(new String[] {matcher3.group(3), matcher3.group(4)});
				graph.addVertex(actor);
				log.Log.logger.info("Addactor: " + matcher3.group(1));
				return true;
			}
			if(matcher3.group(2).equals("Director")) {
				Director director = new Director(matcher3.group(1));
				director.fillVertexInfo(new String[] {matcher3.group(3), matcher3.group(4)});
				graph.addVertex(director);
				log.Log.logger.info("Adddirector: " + matcher3.group(1));
				return true;
			}
		}
		Matcher matcher11 = Pattern.compile("Vertex=<(\\w+),(\\w+),<([MF])>>").matcher(string);
		if(matcher11.matches()) {
			throw new LackOfPropertyException();
		}
		Matcher matcher9 = Pattern.compile("Vertex=<(\\w+),<([MF]),(\\d+)>>").matcher(string);
		if(matcher9.matches()) {
			throw new LackOfComponentsException();
		}
		Matcher matcher4 = Pattern.compile("Vertex=<(\\w+),Movie,<(\\d{4}),(\\w+),([\\d\\.]+)>>").matcher(string);
		if(matcher4.matches()) {
			Movie movie = new Movie(matcher4.group(1));
			movie.fillVertexInfo(new String[]{matcher4.group(2), matcher4.group(3), matcher4.group(4)});
			graph.addVertex(movie);
			log.Log.logger.info("Addmovie: " + matcher4.group(1));
			return true;
		}
		Matcher matcher10 = Pattern.compile("Vertex=<Movie,<(\\d{4}),(\\w+),([\\d\\.]+)>>").matcher(string);
		if(matcher10.matches()) {
			throw new LackOfComponentsException();
		}
		Matcher matcher5 = Pattern.compile("EdgeType=(\\w+),(\\w+),(\\w+)").matcher(string);
		if(matcher5.matches()) {
			edgeTypes.add(matcher5.group(1));
			edgeTypes.add(matcher5.group(2));
			edgeTypes.add(matcher5.group(3));
			log.Log.logger.info("EdgeType are " + matcher5.group(1) + ", "
					+ matcher5.group(2) + ", " + matcher5.group(3) + ".");
			return true;
		}
		Matcher matcher6 = Pattern.compile("Edge=<(\\w+),(\\w+),([\\-\\d]+),(\\w+),(\\w+),No>").matcher(string);  // match the edge
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
			NoVertexException exception = new NoVertexException();
			if(v1 != null && v2 != null) {
				edge.addVertices(Arrays.asList(v1, v2));
				graph.addEdge(edge);
				log.Log.logger.info("Addedge, MovieActorRelation, " + matcher6.group(1));
				return true;
			}
			else {
				throw exception;
				//return false;
			}
		}
		Matcher matcher12 = Pattern.compile("Edge=<(\\w+),(\\w+),([\\-\\d]+),(\\w+),(\\w+),Yes>").matcher(string);  // match the edge
		if(matcher12.matches()) {
			//System.out.println(Integer.valueOf(matcher6.group(3)));
			Edge edge = null;
			if(matcher12.group(2).equals("MovieActorRelation")) {
				edge = new MovieActorRelation(matcher12.group(1), Integer.valueOf(matcher12.group(3)));
			}
			else if(matcher12.group(2).equals("MovieDirectorRelation")) {
				edge = new MovieDirectorRelation(matcher12.group(1), Integer.valueOf(matcher12.group(3)));
			}
			if(edge == null) return false;
			Vertex v1 = null;
			Vertex v2 = null;
			for(Vertex x: graph.vertices()) {
				if(x.getLabel().equals(matcher12.group(4)))
					v1 = x;
				else if(x.getLabel().equals(matcher12.group(5)))
					v2 = x;
			}
			NoVertexException exception = new NoVertexException();
			if(v1 != null && v2 != null) {
				edge.addVertices(Arrays.asList(v1, v2));
				graph.addEdge(edge);
				log.Log.logger.info("Addedge, MovieActorRelation, " + matcher6.group(1));
				return true;
			}
			else {
				throw exception;
				//return false;
			}
		}
		Matcher matcher13 = Pattern.compile("Edge=<(\\w+),(\\w+),(\\w+),(\\w+),No>").matcher(string);
		if(matcher13.matches()) {
			throw new NoWeightException();
		}
		Matcher matcher7 = Pattern.compile("HyperEdge=<(\\w+),SameMovieHyperEdge,\\{(.*)\\}>").matcher(string);
		if(matcher7.matches()) {
			Edge edge = new SameMovieHyperEdge(matcher7.group(1), -1);
			List<Vertex> vertices = new ArrayList<>();
			String allVertices = matcher7.group(2);
			String[] Vertex = allVertices.split(",");
			if(Vertex.length < 2)
				throw new HyperEdgeException();
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
			if(vertices.size() >= 2) {
			edge.addVertices(vertices);
			graph.addEdge(edge);
			log.Log.logger.info("AddHyperEdge, " + matcher7.group(1));
			return true;
			}
			else throw new HyperEdgeException();
		}
		Matcher matcher8 = Pattern.compile("Edge=<(\\w+),(\\w+),([\\-\\d]+),(\\w+),(\\w+),N>").matcher(string);
		if(matcher8.matches()) {
			YorNException execption = new YorNException();
			throw execption;
		}
		//System.out.println("Wrong input!  " + string);
		if(string.contains("Person") || string.contains("Computer") || string.contains("Router")
				|| string.contains("Server") || string.contains("Word")
				|| string.contains("Connection") || string.contains("WordEdge"))
			throw new IllegalTypeException();
		Matcher matcher14 = Pattern.compile("Vertex=<(.*),(\\w+),<([MF]),(\\d+)>>").matcher(string);
		if(matcher14.matches()) {
			throw new IllegalLabelException();
			//return true;
		}
		Matcher matcher15 = Pattern.compile("Edge=<(.*),(\\w+),([\\-\\d]+),(\\w+),(\\w+),No>").matcher(string);
		if(matcher15.matches())
			throw new IllegalLabelException();
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
		if(bf != null) {
		try {
			string = bf.readLine();
			while(string != null) {
				try {
				parse(string);
				}
				catch (NoVertexException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("NoVertexException, " + e.getMessage());
					return graph;
				}
				catch (YorNException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("YorNException, " + e.getMessage());
					return graph;
				}
				catch (LackOfComponentsException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("LackofComponentsException, " + e.getMessage());
					return graph;
				}
				catch (LackOfPropertyException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("LackofPropertyException, " + e.getMessage());
					return graph;
				}
				catch (IllegalTypeException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("IllegalTypeException, " + e.getMessage());
					return graph;
				}
				catch (HyperEdgeException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("HyperEdgeException, " + e.getMessage());
					return graph;
				}
				catch (NoWeightException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("NoWeightException, " + e.getMessage());
					return graph;
				}
				catch (IllegalLabelException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					log.Log.logger.error("IllegalLabelException, " + e.getMessage());
					return graph;
				}
				string = bf.readLine();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
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
