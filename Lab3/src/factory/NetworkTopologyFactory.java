package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Computer;

import edge.Edge;
import edge.NetworkConnection;
import graph.Graph;
import graph.NetworkTopology;
import vertex.Router;
import vertex.Server;
import vertex.Vertex;

public class NetworkTopologyFactory extends GraphFactory {
	private Graph<Vertex, Edge> graph;
	private String graphName = new String();
	private List<String> vertexTypes = new ArrayList<>();
	private List<String> edgeTypes = new ArrayList<>();
	private boolean parse(String string) {
		if(string.equals("GraphType=NetworkTopology")) {
			graph = new NetworkTopology();
			return true;
		}
		Matcher matcher = Pattern.compile("GraphName=(\\w+)").matcher(string);
		if(matcher.matches()) {
			graphName = matcher.group(1);
			return true;
		}
		Matcher matcher2 = Pattern.compile("VertexType=(\\w+),(\\w+),(\\w+),(\\w+)").matcher(string);
		if(matcher2.matches()) {
			vertexTypes.add(matcher2.group(1));
			vertexTypes.add(matcher2.group(2));
			vertexTypes.add(matcher2.group(3));
			vertexTypes.add(matcher2.group(4));
			return true;
		}
		Matcher matcher3 = Pattern.compile("Vertex=<(\\w+),(\\w+),<(\\d+\\.\\d+\\.\\d+\\.\\d+)>>").matcher(string);
		if(matcher3.matches()) {
			if(matcher3.group(2).equals("Server")) {
				Server server = new Server(matcher3.group(1));
				server.fillVertexInfo(new String[] {matcher3.group(3)});
				graph.addVertex(server);
				return true;
			}
			else if(matcher3.group(2).equals("Computer")) {
				vertex.Computer computer = new vertex.Computer(matcher3.group(1));
				computer.fillVertexInfo(new String[] {matcher3.group(3)});
				graph.addVertex(computer);
				return true;
			}
			else if(matcher3.group(2).equals("Router")) {
				Router router = new Router(matcher3.group(1));
				router.fillVertexInfo(new String[] {matcher3.group(3)});
				graph.addVertex(router);
				return true;
			}
			else if(matcher3.group(2).equals("WirelessRouter")) {
				vertex.Computer computer = new vertex.Computer(matcher3.group(1));
				computer.fillVertexInfo(new String[] {matcher3.group(3)});
				graph.addVertex(computer);
				return true;
			}
		}
		Matcher matcher4 = Pattern.compile("EdgeType=(\\w+)").matcher(string);
		if(matcher4.matches()) {
			edgeTypes.add(matcher4.group(1));
			return true;
		}
		Matcher matcher5 = Pattern.compile("Edge=<(\\w+),NetworkConnection,(\\d+),(\\w+),(\\w+),(\\w+)>").matcher(string);
		if(matcher5.matches()) {
			NetworkConnection edge = new NetworkConnection(matcher5.group(1), Integer.valueOf(matcher5.group(2)));
			Vertex v1 = null;
			Vertex v2 = null;
			for(Vertex x: graph.vertices()) {
				if(x.getLabel().equals(matcher5.group(3))) {
					v1 = x;
				}
				else if(x.getLabel().equals(matcher5.group(4))) {
					v2 = x;
				}
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
	
	public String getName() {
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
		NetworkTopologyFactory mgf = new NetworkTopologyFactory();
		mgf.createGraph("src/NetworkTopology.txt");
		for(Vertex x: mgf.graph.vertices()) {
			System.out.println(x.toString());
		}
		for(Edge x: mgf.graph.edges()) {
			System.out.println(x.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
