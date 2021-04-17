package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import MyException.IllegalLabelException;
import MyException.IllegalTypeException;
import MyException.IllegalWeightException;
import MyException.LackOfComponentsException;
import MyException.LackOfPropertyException;
import MyException.NoVertexException;
import MyException.NoWeightException;
import MyException.YorNException;
import edge.CommentConnection;
import edge.Edge;
import edge.ForwardConnection;
import edge.FriendConnection;
import graph.Graph;
import graph.SocialNetwork;
import vertex.Person;
import vertex.Vertex;

public class SocialNetworkFactory extends GraphFactory {
	private Graph<Vertex, Edge> graph;
	private String graphName = new String();
	private List<String> vertexTypes = new ArrayList<>();
	private List<String> edgeTypes = new ArrayList<>(); 
	public boolean parse(String string) throws Exception {
		if(string.equals("GraphType=SocialNetwork")) {
			graph = new SocialNetwork();
			log.Log.logger.info("GraphType=SocialNetwork");
			return true;
		}
		Matcher matcher = Pattern.compile("GraphName=(\\w+)").matcher(string);
		if(matcher.matches()) {
			graphName = matcher.group(1);
			log.Log.logger.info("GraphName is " + matcher.group(1));
			return true;
		}
		Matcher matcher2 = Pattern.compile("VertexType=(\\w+)").matcher(string);
		if(matcher2.matches()) {
			vertexTypes.add(matcher2.group(1));
			log.Log.logger.info("VertexType is " + matcher2.group(1));
			return true;
		}
		Matcher matcher3 = Pattern.compile("Vertex=<(\\w+),Person,<([MF]),(\\d+)>>").matcher(string);
		if(matcher3.matches()) {
			Person person = new Person(matcher3.group(1));
			person.fillVertexInfo(new String[] {matcher3.group(2), matcher3.group(3)});
			graph.addVertex(person);
			log.Log.logger.info("Addvertex, Person: " + matcher3.group(1));
			return true;
		}
		Matcher matcher10= Pattern.compile("Vertex=<(.*),Person,<([MF]),(\\d+)>>").matcher(string);
		if(matcher10.matches())
			throw new IllegalLabelException();
		Matcher matcher7 = Pattern.compile("Vertex=<Person,<([MF]),(\\d+)>>").matcher(string);
		if(matcher7.matches()) {
			throw new LackOfComponentsException();
		}
		Matcher matcher8 = Pattern.compile("Vertex=<(\\w+),Person,<([MF])>>").matcher(string);
		if(matcher8.matches()) {
			throw new LackOfPropertyException();
		}
		Matcher matcher4 = Pattern.compile("EdgeType=(\\w+),(\\w+),(\\w+)").matcher(string);
		if(matcher4.matches()) {
			edgeTypes.add(matcher4.group(1));
			edgeTypes.add(matcher4.group(2));
			edgeTypes.add(matcher4.group(3));
			log.Log.logger.info("EdgeType are " + matcher4.group(1) + ", "
					+ matcher4.group(2) + ", " + matcher4.group(3) + ".");
			return true;
		}
		Matcher matcher5 = Pattern.compile("Edge=<(\\w+),(\\w+),([\\d\\.]+),(\\w+),(\\w+),Yes>").matcher(string);
		if(matcher5.matches()) {
			if(Double.valueOf(matcher5.group(3)) > 1)
				throw new IllegalWeightException();
			if(matcher5.group(2).equals("ForwardConnection")) {
				ForwardConnection edge = new ForwardConnection(matcher5.group(1), Double.valueOf(matcher5.group(3)));
				Vertex p1 = null;
				Vertex p2 = null;
				for(Vertex x: graph.vertices()) {
					if(x.getLabel().equals(matcher5.group(4))) {
						p1 = x;
					}
					else if(x.getLabel().equals(matcher5.group(5))) {
						p2 = x;
					}
				}
				NoVertexException exception = new NoVertexException();
				if(p1 != null && p2 != null) {
					edge.addVertices(Arrays.asList(p1, p2));
					graph.addEdge(edge);
					log.Log.logger.info("Addedge, ForwardConnection: " + matcher5.group(1));
					return true;
				}
				else {
					//return false;
					throw exception;
				}
			}
			else if(matcher5.group(2).equals("CommentConnection")) {
				CommentConnection edge = new CommentConnection(matcher5.group(1), Double.valueOf(matcher5.group(3)));
				Vertex p1 = null;
				Vertex p2 = null;
				for(Vertex x: graph.vertices()) {
					if(x.getLabel().equals(matcher5.group(4))) {
						p1 = x;
					}
					else if(x.getLabel().equals(matcher5.group(5))) {
						p2 = x;
					}
				}
				NoVertexException exception = new NoVertexException();
				if(p1 != null && p2 != null) {
					edge.addVertices(Arrays.asList(p1, p2));
					graph.addEdge(edge);
					log.Log.logger.info("Addedge, CommentConnection: " + matcher5.group(1));
					return true;
				}
				else {
					//return false;
					throw exception;
				}
			}
			else if(matcher5.group(2).equals("FriendConnection")) {
				FriendConnection edge = new FriendConnection(matcher5.group(1), Double.valueOf(matcher5.group(3)));
				Vertex p1 = null;
				Vertex p2 = null;
				for(Vertex x: graph.vertices()) {
					if(x.getLabel().equals(matcher5.group(4))) {
						p1 = x;
					}
					else if(x.getLabel().equals(matcher5.group(5))) {
						p2 = x;
					}
				}
				NoVertexException exception = new NoVertexException();
				if(p1 != null && p2 != null) {
					edge.addVertices(Arrays.asList(p1, p2));
					graph.addEdge(edge);
					log.Log.logger.info("Addedge, FriendConnection: " + matcher5.group(1));
					return true;
				}
				else {
					//return false;
					throw exception;
				}
			}
		}
		Matcher matcher11 = Pattern.compile("Edge=<(.*),(\\w+),([\\d\\.]+),(\\w+),(\\w+),Yes>").matcher(string);
		if(matcher11.matches()) {
			throw new IllegalLabelException();
		}
		Matcher matcher6 = Pattern.compile("Edge=<(\\w+),(\\w+),([\\d\\.]+),(\\w+),(\\w+),Y>").matcher(string);
		if(matcher6.matches()) {
			throw new YorNException();
		}
		Matcher matcher9 = Pattern.compile("Edge=<(\\w+),(\\w+),(\\w+),(\\w+),Yes>").matcher(string);
		if(matcher9.matches()) {
			throw new NoWeightException();
		}
		//System.out.println("Wrong input!  " + string);
		
		if(string.contains("Word") || string.contains("Computer") || string.contains("Router")
				|| string.contains("Server") || string.contains("Movie")
				|| string.contains("Director") || string.contains("Actor")
				|| string.contains("Relation"))
			throw new IllegalTypeException();
		
		return false;
	}
	
	public String getGraphName() {
		return graphName;
	}
	@Override
	public Graph<Vertex, Edge> createGraph(String filePath) {
		//return new MovieGraph();
				BufferedReader bf = null;
				String string;
				try {
					bf = new BufferedReader(new FileReader(filePath));
				} catch (Exception e) {
					System.out.println("Wrong path!");
				}
				if (bf != null) {
				try {
					
					string = bf.readLine();
					while(string != null) {
						try {
						parse(string);
						}
						catch (NoVertexException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
							log.Log.logger.info("NoVertexException, " + e.getMessage());
							return graph;
						}
						catch (YorNException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
							log.Log.logger.info("YorNException, " + e.getMessage());
							return graph;
						}
						catch (LackOfComponentsException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
							log.Log.logger.info("LackofComponentsException, " + e.getMessage());
							return graph;
						}
						catch (LackOfPropertyException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
							log.Log.logger.info("LackofPropertyException, " + e.getMessage());
							return graph;
						}
						catch (IllegalTypeException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
							log.Log.logger.info("IllegalTypeException, " + e.getMessage());
							return graph;
						}
						catch (NoWeightException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
							log.Log.logger.info("NoWeightException, " + e.getMessage());
							return graph;
						}
						catch (IllegalWeightException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
							log.Log.logger.info("IllegalWeightException, " + e.getMessage());
							return graph;
						}
						catch (IllegalLabelException e) {
							// TODO: handle exception 
							System.out.println(e.getMessage());
							log.Log.logger.info("IllegalLabelException, " + e.getMessage());
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
	public static void main(String[] args) {
		SocialNetworkFactory mgf = new SocialNetworkFactory();
		mgf.createGraph("src/SocialNetwork.txt");
		for(Vertex x: mgf.graph.vertices()) {
			System.out.println(x.toString());
		}
		for(Edge x: mgf.graph.edges()) {
			System.out.println(x.toString());
		}
	}
}
