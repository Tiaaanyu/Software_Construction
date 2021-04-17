package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Computer;

import MyException.IllegalLabelException;
import MyException.IllegalTypeException;
import MyException.LackOfComponentsException;
import MyException.NoVertexException;
import MyException.NoWeightException;
import MyException.YorNException;
import edge.Edge;
import edge.NetworkConnection;
import graph.Graph;
import graph.NetworkTopology;
import vertex.Actor;
import vertex.Director;
import vertex.Router;
import vertex.Server;
import vertex.Vertex;

public class NetworkTopologyFactory extends GraphFactory {
	private Graph<Vertex, Edge> graph;
	private String graphName = new String();
	private List<String> vertexTypes = new ArrayList<>();
	private List<String> edgeTypes = new ArrayList<>();
	public void parse(String string) throws Exception{
		if(string.equals("GraphType=NetworkTopology")) {
			graph = new NetworkTopology();
			log.Log.logger.info("GraphType=NetworkTopology");
			//return true;
		}
		Matcher matcher = Pattern.compile("GraphName=(\\w+)").matcher(string);
		if(matcher.matches()) {
			graphName = matcher.group(1);
			log.Log.logger.info("GraphName is " + matcher.group(1));
			//return true;
		}
		Matcher matcher2 = Pattern.compile("VertexType=(\\w+),(\\w+),(\\w+),(\\w+)").matcher(string);
		if(matcher2.matches()) {
			vertexTypes.add(matcher2.group(1));
			vertexTypes.add(matcher2.group(2));
			vertexTypes.add(matcher2.group(3));
			vertexTypes.add(matcher2.group(4));
			log.Log.logger.info("VertexType are " + matcher2.group(1) + ", "
					+ matcher2.group(2) + ", " + matcher2.group(3) + ", " + matcher2.group(3) + ".");
			//return true;
		}
		Matcher matcher3 = Pattern.compile("Vertex=<(\\w+),(\\w+),<(\\d+\\.\\d+\\.\\d+\\.\\d+)>>").matcher(string);
		if(matcher3.matches()) {
			if(matcher3.group(2).equals("Server")) {
				Server server = new Server(matcher3.group(1));
				server.fillVertexInfo(new String[] {matcher3.group(3)});
				graph.addVertex(server);
				log.Log.logger.info("Addvertex, Server: " + matcher3.group(1));
				return;
			}
			else if(matcher3.group(2).equals("Computer")) {
				vertex.Computer computer = new vertex.Computer(matcher3.group(1));
				computer.fillVertexInfo(new String[] {matcher3.group(3)});
				graph.addVertex(computer);
				log.Log.logger.info("Addvertex, Computer: " + matcher3.group(1));
				return;
			}
			else if(matcher3.group(2).equals("Router")) {
				Router router = new Router(matcher3.group(1));
				router.fillVertexInfo(new String[] {matcher3.group(3)});
				graph.addVertex(router);
				log.Log.logger.info("Addvertex, Router: " + matcher3.group(1));
				return;
			}
			else if(matcher3.group(2).equals("WirelessRouter")) {
				vertex.Computer computer = new vertex.Computer(matcher3.group(1));
				computer.fillVertexInfo(new String[] {matcher3.group(3)});
				graph.addVertex(computer);
				log.Log.logger.info("Addvertex, WirelessRouter: " + matcher3.group(1));
				return;
			}
		}
		Matcher matcher7 = Pattern.compile("Vertex=<(\\w+),<(\\d+\\.\\d+\\.\\d+\\.\\d+)>>").matcher(string);
		if(matcher7.matches()) {
			throw new LackOfComponentsException();
		}
		Matcher matcher4 = Pattern.compile("EdgeType=(\\w+)").matcher(string);
		if(matcher4.matches()) {
			edgeTypes.add(matcher4.group(1));
			log.Log.logger.info("EdgeType is " + matcher4.group(1));
			//return true;
		}
		Matcher matcher5 = Pattern.compile("Edge=<(\\w+),NetworkConnection,(\\d+),(\\w+),(\\w+),No>").matcher(string);
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
			if((v1 instanceof Actor && v2 instanceof Director) || (v1 instanceof Director && v2 instanceof Actor)) {
				return;
			}
			NoVertexException exception = new NoVertexException();
			if(v1 != null && v2 != null) {
				boolean flag = false;
				for(Edge x: graph.edges()) {
					if(x.vertices().contains(v1) && x.vertices().contains(v2))
						flag = true;
				}
				if(flag == false) {
					edge.addVertices(Arrays.asList(v1, v2));
					graph.addEdge(edge);
					log.Log.logger.info("Addedge, NetWorkConnection: " + matcher5.group(1));
					
				}
				//return true;
			} 	
			else //return false;
				throw exception;
		}
		//System.out.println("Wrong input!  " + string);
		//return false;
		Matcher matcher9 = Pattern.compile("Edge=<(\\w+),NetworkConnection,(\\w+),(\\w+),No>").matcher(string);
		if(matcher9.matches()) {
			throw new NoWeightException();
		}
		Matcher matcher6 = Pattern.compile("Edge=<(\\w+),NetworkConnection,(\\d+),(\\w+),(\\w+),N>").matcher(string);
		if(matcher6.matches()) {
			throw new YorNException();
		}
		Matcher matcher8 = Pattern.compile("Edge=<(\\w+),NetworkConnection,(\\d+),(\\w+),(\\w+),Yes>").matcher(string);
		if(matcher8.matches()) {
			NetworkConnection edge = new NetworkConnection(matcher8.group(1), Integer.valueOf(matcher8.group(2)));
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
			if((v1 instanceof Actor && v2 instanceof Director) || (v1 instanceof Director && v2 instanceof Actor)) {
				return;
			}
			NoVertexException exception = new NoVertexException();
			if(v1 != null && v2 != null) {
				edge.addVertices(Arrays.asList(v1, v2));
				graph.addEdge(edge);
				log.Log.logger.info("Addedge, NetWorkConnection: " + matcher5.group(1));
				//return true;
			}
			else //return false;
				throw exception;
		}
		else {
		if(string.contains("Person") || string.contains("Word")
				|| string.contains("Movie")
				|| string.contains("Director") || string.contains("Actor")
				|| string.contains("WordEdge") || string.contains("Relation"))
			throw new IllegalTypeException();
		}
		Matcher matcher10 = Pattern.compile("Vertex=<(.*),(\\w+),<(\\d+\\.\\d+\\.\\d+\\.\\d+)>>").matcher(string);
		if(matcher10.matches())
			throw new IllegalLabelException();
		Matcher matcher11 = Pattern.compile("Edge=<(.*),NetworkConnection,(\\d+),(\\w+),(\\w+),No>").matcher(string);
		if(matcher11.matches()) {
			throw new IllegalLabelException();
		}
	}
	
	public String getName() {
		return graphName;
	}
	@Override
	public Graph<Vertex, Edge> createGraph(String filePath) {
		
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
						catch (IllegalTypeException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
							log.Log.logger.error("IllegalTypeException, " + e.getMessage());
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
