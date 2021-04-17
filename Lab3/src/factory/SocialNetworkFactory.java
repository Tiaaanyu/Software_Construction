package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private boolean parse(String string) {
		if(string.equals("GraphType=SocialNetwork")) {
			graph = new SocialNetwork();
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
		Matcher matcher3 = Pattern.compile("Vertex=<(\\w+),Person,<([MF]),(\\d+)>>").matcher(string);
		if(matcher3.matches()) {
			Person person = new Person(matcher3.group(1));
			person.fillVertexInfo(new String[] {matcher3.group(2), matcher3.group(3)});
			graph.addVertex(person);
			return true;
		}
		Matcher matcher4 = Pattern.compile("EdgeType=(\\w+),(\\w+),(\\w+)").matcher(string);
		if(matcher4.matches()) {
			edgeTypes.add(matcher4.group(1));
			edgeTypes.add(matcher4.group(2));
			edgeTypes.add(matcher4.group(3));
			return true;
		}
		Matcher matcher5 = Pattern.compile("Edge=<(\\w+),(\\w+),([\\d\\.]+),(\\w+),(\\w+),(\\w+)>").matcher(string);
		if(matcher5.matches()) {
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
				if(p1 != null && p2 != null) {
					edge.addVertices(Arrays.asList(p1, p2));
					graph.addEdge(edge);
					return true;
				}
				else return false;
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
				if(p1 != null && p2 != null) {
					edge.addVertices(Arrays.asList(p1, p2));
					graph.addEdge(edge);
					return true;
				}
				else return false;
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
				if(p1 != null && p2 != null) {
					edge.addVertices(Arrays.asList(p1, p2));
					graph.addEdge(edge);
					return true;
				}
				else return false;
			}
		}
		System.out.println("Wrong input!  " + string);
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
