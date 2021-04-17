package IOStrategy;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import edge.Edge;
import factory.GraphPoetFactory;
import factory.MovieGraphFactory;
import factory.NetworkTopologyFactory;
import factory.SocialNetworkFactory;
import graph.Graph;
import graph.GraphPoet;
import graph.MovieGraph;
import graph.SocialNetwork;
import vertex.Vertex;

public class StreamInputMethod implements InputStrategy{
	public void readGraphFromFiles(String fileName, Graph<Vertex, Edge> graph) {
		
		try {
			long time = System.currentTimeMillis();
			FileInputStream inputStream = new FileInputStream(fileName);
			 BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
			 String string = bf.readLine();
			 int num = 1;
			 while(string != null) {
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
//					Thread.sleep(100);
					string = bf.readLine();
			 }
			 long totalTime = System.currentTimeMillis() - time;
			 System.out.println("The reading costs " + totalTime + " ms");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
