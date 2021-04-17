package IOStrategy;

import java.io.BufferedReader;
import java.io.FileReader;

import edge.Edge;
import factory.GraphFactory;
import factory.GraphPoetFactory;
import factory.MovieGraphFactory;
import factory.NetworkTopologyFactory;
import factory.SocialNetworkFactory;
import graph.Graph;
import graph.GraphPoet;
import graph.MovieGraph;
import graph.SocialNetwork;
import vertex.Vertex;
import graph.*;

public class ReaderMethod implements InputStrategy {
	public void readGraphFromFiles(String fileName, Graph<Vertex, Edge> graph) {
		BufferedReader bf = null;
		int num = 1;
		String string;
		try {
			bf = new BufferedReader(new FileReader(fileName));
		} catch (Exception e) {
			System.err.println("Wrong path!");
		}
		long time = System.currentTimeMillis();
		try {
			string = bf.readLine();
			while(string != null ) {
//				System.err.println(string.length());
				if(!string.matches("\\b*")) {
					if(graph instanceof GraphPoet)
						GraphPoetFactory.parse(string);
					else if(graph instanceof SocialNetwork)
						SocialNetworkFactory.parse(string);
					else if(graph instanceof MovieGraph)
						MovieGraphFactory.parse(string);
					else NetworkTopologyFactory.parse(string);
					++num;
				}
				if(num % 100 == 0)
					System.out.println(num / 100);
//				Thread.sleep(100);
				string = bf.readLine();
			}
			long totalTime = System.currentTimeMillis() - time;
			 System.out.println("The reading costs " + totalTime + " ms");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
	}
}
