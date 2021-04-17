package application;

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
		System.out.println("-------------------------Dedigned by muty");
		System.out.println(" ");
		System.out.println(" ");
		int num;
		do {
			System.out.println("Please choose a function(1 - 7) and input the number.");
			num = Integer.valueOf(input.nextLine());
			switch (num) {
			case 1:
				System.out.println("Function 1, please input the path to concrete the graph.");
				try {
					graph = new NetworkTopologyFactory().createGraph(input.nextLine());
					System.out.println("Concrete graph successfully!");
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
			default:
				System.out.println("Please input the right number(1 - 7)");
			}
			
		} while (true);
	}
}
