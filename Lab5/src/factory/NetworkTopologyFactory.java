package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Computer;

import IOStrategy.NioFilesInputMethod;
import IOStrategy.NioFilesOutputMethod;
import IOStrategy.ReaderMethod;
import IOStrategy.StreamInputMethod;
import IOStrategy.StreamOutPutMethod;
import IOStrategy.WriterMethod;
import edge.Edge;
import edge.NetworkConnection;
import graph.Graph;
import graph.NetworkTopology;
import vertex.Router;
import vertex.Server;
import vertex.Vertex;

public class NetworkTopologyFactory extends GraphFactory {
	private static Graph<Vertex, Edge> graph = new NetworkTopology();
	private static String graphName = new String();
	private static List<String> vertexTypes = new ArrayList<>();
	private static List<String> edgeTypes = new ArrayList<>();
	public static boolean parse(String string) {
		if(string.equals("GraphType=NetworkTopology")) {
			//graph = new NetworkTopology();
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
	@Override
	public void writeGraph(String fileName, Graph<Vertex, Edge> graph) {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose the method to write");
		System.out.println("1. Stream");
		System.out.println("2. Writer");
		System.out.println("3. Nio.Files");
		int number = Integer.valueOf(input.nextLine());
		if(number == 1) 
			new StreamOutPutMethod().writeToFilesFromGraph("src/output.txt", graph);
		else if(number == 2)
			new WriterMethod().writeToFilesFromGraph("src/output.txt", graph);
		else new NioFilesOutputMethod().writeToFilesFromGraph("src/output.txt", graph);
	}
	public String getName() {
		return graphName;
	}
	@Override
	public Graph<Vertex, Edge> createGraph(String filePath) {
//		//return new MovieGraph();
//		int num = 1; 
//				BufferedReader bf = null;
//				String string;
//				try {
//					bf = new BufferedReader(new FileReader(filePath));
//				} catch (Exception e) {
//					// TODO: handle exception
//					System.out.println("Wrong path!");
//				}
//				try {
//					//System.out.println("sss");
//					string = bf.readLine();
//					while(string != null) {
//						if(!string.matches("\\b*")) {
//							parse(string);
//							++num;
//						}
//						if(num % 100 == 0)
//							System.out.println(num / 100);
//						string = bf.readLine();
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
//					System.out.println(e.getMessage());
//					e.printStackTrace();
//					
//				}
		System.out.println("Please input the I/O method:");
		System.out.println("1. Stream");
		System.out.println("2. Reader");
		System.out.println("3. java.nio.file.Files");
		Scanner input = new Scanner(System.in);
		int number = Integer.valueOf(input.nextLine());
		if(number == 1) {
			new StreamInputMethod().readGraphFromFiles(filePath, graph);
		}
		else if(number == 2) {
			new ReaderMethod().readGraphFromFiles(filePath, graph);
		}
		else {
			new NioFilesInputMethod().readGraphFromFiles(filePath, graph);
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
