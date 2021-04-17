package application;

import java.util.Scanner;

import javax.swing.JFrame;

import IOStrategy.NioFilesOutputMethod;
import IOStrategy.StreamOutPutMethod;
import IOStrategy.WriterMethod;
import edge.Edge;
import factory.GraphPoetFactory;
import graph.Graph;
import vertex.Vertex;
import helper.*;
public class GraphPoetApp {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		GraphPoetFactory gpf = new GraphPoetFactory();
		Graph<Vertex, Edge> graph = null;
		System.out.println("---------------GraphPoetApp--------------");
		System.out.println("1. Input the filepath of graph");
		System.out.println("2. Command for graph");
		System.out.println("3. Vertex centrality");
		System.out.println("4. Graph centrality, radius and diameter");
		System.out.println("5. Shortest distance between two vertices");
		//System.out.println("6. Help");
		System.out.println("6. Exit");
		System.out.println("7. Filter the edge");
		System.out.println("8. GUI");
		System.out.println("9. Write the graph to files");
		System.out.println("-------------------------Dedigned by muty");
		System.out.println(" ");
		System.out.println(" ");
		int num;
		do {
			System.out.println("Please choose a function(1 - 9) and input the number.");
			num = Integer.valueOf(input.nextLine());
			switch (num) {
			case 1:
				System.out.println("Function 1, please input the path to concrete the graph.");
				try {
					//long time = System.currentTimeMillis();
					graph = gpf.createGraph(input.nextLine());
					//long totalTime = System.currentTimeMillis() - time;
					//System.out.println(totalTime + "ms");
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
						new CentralityContext();
						System.out.println("The closeness of the " + label + " is " + 
					CentralityContext.setCentralityStrategy("closenessCentrality", graph, v));
						new CentralityContext();
						System.out.println("The betweenness of the " + label + " is " + 
								CentralityContext.setCentralityStrategy("betweennessCentrality", graph, v));
						new CentralityContext();
						System.out.println("The degree centrality of the " + label + " is "+
								CentralityContext.setCentralityStrategy("degreeCentrality", graph, v));
						new CentralityContext();
						System.out.println("The eccentricity of the " + label + " is " + 
								CentralityContext.setCentralityStrategy("eccentricity", graph, v));
						new CentralityContext();
						System.out.println("The indegree centrality of the " + label + " is " +
								CentralityContext.setCentralityStrategy("indegreeCentrality", graph, v));
						new CentralityContext();
						System.out.println("The outdegree centrality of the " + label + " is " +
								CentralityContext.setCentralityStrategy("outdegreeCentrality", graph, v));
								
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
				System.out.println("Function 7, please input the smallest weight");
				int weight = Integer.valueOf(input.nextLine());
				for(Edge x: graph.edges()) {
					if(x.getWeight() < weight)
						graph.removeEdge(x);
				}
			case 8:
				GraphVisualizationHelper gvh = new GraphVisualizationHelper(graph);
			case 9:
//				System.out.println("Choose the method to write");
//				System.out.println("1. Stream");
//				System.out.println("2. Writer");
//				System.out.println("3. Nio.Files");
//				int number = Integer.valueOf(input.nextLine());
//				if(number == 1) 
//					new StreamOutPutMethod().writeToFilesFromGraph("src/output.txt", graph);
//				else if(number == 2)
//					new WriterMethod().writeToFilesFromGraph("src/output.txt", graph);
//				else new NioFilesOutputMethod().writeToFilesFromGraph("src/output.txt", graph);
				 gpf.writeGraph("src/output.txt", graph);
			default:
				System.out.println("Please input the right number(1 - 9)");
			}
			
		} while (true);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
