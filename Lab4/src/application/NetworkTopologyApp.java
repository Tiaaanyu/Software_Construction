package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edge.Edge;
import factory.GraphPoetFactory;
import factory.NetworkTopologyFactory;
import graph.Graph;
import helper.CentralityContext;
import helper.GraphMetrics;
import helper.GraphVisualizationHelper;
import helper.ParseCommandHelper;
import vertex.Vertex;

public class NetworkTopologyApp {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Graph<Vertex, Edge> graph = null;
		System.out.println("---------------NetworkTopologyApp--------------");
		System.out.println("1. Input the filepath of graph");
		System.out.println("2. Command for graph");
		System.out.println("3. Vertex centrality");
		System.out.println("4. Graph centrality, radius and diameter");
		System.out.println("5. Shortest distance between two vertices");
		System.out.println("6. Exit");
		System.out.println("7. GUI");
		System.out.println("8. Search log files.");
		System.out.println("-------------------------Dedigned by muty");
		System.out.println(" ");
		System.out.println(" ");
		int num;
		do {
			System.out.println("Please choose a function(1 - 8) and input the number.");
			num = Integer.valueOf(input.nextLine());
			switch (num) {
			case 1:
				System.out.println("Function 1, please input the path to concrete the graph.");
				try {
					graph = new NetworkTopologyFactory().createGraph(input.nextLine());
					//System.out.println("Concrete graph successfully!");
				}
				catch (Exception e) {
					System.out.println("Wrong path!");
				}
				break;
			case 2:
				System.out.println("Function 2, please input the command.(\"exit\" to stop inputing command)");
				String command = new String();
				do {
					command = input.nextLine();
					ParseCommandHelper.parseAndExecuteCommand(command, graph);
				} while (!command.equals("exit"));
				break;
			case 3:
				System.out.println("Function 3, please input the label of the vertex.");
				String label = input.nextLine();
				for(Vertex v: graph.vertices()) {
					if(v.getLabel().equals(label)) {
						System.out.println("The closeness of the " + label + " is " + 
								new CentralityContext().setCentralityStrategy("closenessCentrality", graph, v));
									System.out.println("The betweenness of the " + label + " is " + 
											new CentralityContext().setCentralityStrategy("betweennessCentrality", graph, v));
									System.out.println("The degree centrality of the " + label + " is "+
											new CentralityContext().setCentralityStrategy("degreeCentrality", graph, v));
									System.out.println("The eccentricity of the " + label + " is " + 
											new CentralityContext().setCentralityStrategy("eccentricity", graph, v));
//									System.out.println("The indegree centrality of the " + label + " is " +
//											new CentralityContext().setCentralityStrategy("indegreeCentrality", graph, v));
//									System.out.println("The outdegree centrality of the " + label + " is " +
//											new CentralityContext().setCentralityStrategy("outdegreeCentrality", graph, v));
					}
				}
				break;
			case 4:
				System.out.println("Function 4, you do not need make any input.");
				System.out.println("Graph centrality: " + GraphMetrics.degreeCentrality(graph));
				System.out.println("Graph radius: " + GraphMetrics.radius(graph));
				System.out.println("Graph diameter: " + GraphMetrics.diameter(graph));
				break;
			case 5:
				System.out.println("Function 5: calculate the shortest distance.");
				System.out.println("Please input the source label");
				String sourceLabel = input.nextLine();
				System.out.println("Please input the target label");
				String targetLabel = input.nextLine();
				Vertex source = null;
				Vertex target = null;
				for(Vertex v: graph.vertices()) {
					if(v.getLabel().equals(sourceLabel))
						source = v;
					else if(v.getLabel().equals(targetLabel))
						target = v;
				}
				if(source != null && target != null) {
					System.out.println("The shortest distance is " 
				+ GraphMetrics.distance(graph, source, target));
					break;
				}
				else {
					System.out.println("Wrongly input!");
					break;
				}
			case 6:
				System.out.println("Thank you for using.");
				System.exit(0);
			case 7:
				GraphVisualizationHelper gvh = new GraphVisualizationHelper(graph);
			case 8:
				int f = -1;
				System.out.println("1 By time\n"
						+ "2 By class\n"
						+ "3 By method\n"
						+ "4 By operation");
				Scanner log = null;
				try {
					log = new Scanner(new File("log/graph.log"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
				}
				if(log == null) break;
				 while(f<1 || f>5) {
					 System.out.println("Please input the number");
					 f = input.nextInt();
				 }
				 if(f == 1) {
					 System.out.println("Please input the time, from(such as 2018.05.19.18:41:07):");
					 String s1 = input.next();
					 System.out.println("Please input the time, to(such as 2018.05.19.18:43:07):");
					 String s2 = input.next();
					 System.out.println("Here are logs founded:");
					 String s = null;
					 while(log.hasNext()) {
						 s = log.nextLine();
						 if(compareTime(s1, s.split(" ")[0]) && compareTime(s.split(" ")[0], s2))	printLog(s);
					 }
				 }
				 else if(f == 2) {
					 System.out.println("Please input the class name");
					 String c = input.next(), s;
					 System.out.println("Here are logs founded:");
					 while(log.hasNext()) {
						 s = log.nextLine();
						 if(s.split(" ")[2].equals(c)) printLog(s);
					 }
					 
				 }
				 else if(f == 3) {
					 System.out.println("Please input the method name");
					 String c = input.next(), s;
					 System.out.println("Here are logs founded:");
					 while(log.hasNext()) {
						 s = log.nextLine();
						 if(s.split(" ")[3].equals(c)) printLog(s);
					 }
				 }
				 else if(f == 4) {
					 System.out.println("Please input the operation name");
					 String c = input.next(), s;
					 System.out.println("Here are logs founded:");
					 while(log.hasNext()) {
						 s = log.nextLine();
						 if(s.split(" ")[4].equals(c)) printLog(s);
					 }
				 }
				 input.nextLine();
				 break;
			default:
				System.out.println("Please input the right number(1 - 8)");
			}
			
		} while (true);
	}
	public static boolean compareTime(String s1, String s2) {
		String[] a1 = s1.split("\\."), a2 = s2.split("\\.");
		if(Integer.valueOf(a1[0]) > Integer.valueOf(a2[0]))	return false;
		else if (Integer.valueOf(a1[0]) < Integer.valueOf(a2[0])) return true;
		if(Integer.valueOf(a1[1]) > Integer.valueOf(a2[1])) return false;
		else if(Integer.valueOf(a1[1]) < Integer.valueOf(a2[1])) return true;
		if(Integer.valueOf(a1[2]) > Integer.valueOf(a2[2])) return false;
		else if(Integer.valueOf(a1[2]) < Integer.valueOf(a2[2])) return true;
		a1 = a1[3].split(":"); a2 = a2[3].split(":");
		if(Integer.valueOf(a1[0]) > Integer.valueOf(a2[0]))	return false;
		else if (Integer.valueOf(a1[0]) < Integer.valueOf(a2[0])) return true;
		if(Integer.valueOf(a1[1]) > Integer.valueOf(a2[1])) return false;
		else if(Integer.valueOf(a1[1]) < Integer.valueOf(a2[1])) return true;
		System.err.println(a1[2] + "\t" + a2[2]);
		if(Integer.valueOf(a1[2]) > Integer.valueOf(a2[2])) return false;
		else if(Integer.valueOf(a1[2]) < Integer.valueOf(a2[2])) return true;
		return false;
	}
	public static void printLog(String str) {
		String[] a = str.split(" ");
		System.out.print("Time: " + a[0] + "\t" +
				"Class: "  + a[2] + "\t" +
				"Method: " + a[3] + "\t" +
				"Operation: " + a[4]);
		str = "";
		for(int i=5; i<a.length; i++) {
			str += a[i] + " ";
		}
		System.out.println(str);
		
	}
}
