package IOStrategy;

import java.io.IOException;
//import java.awt.List;
import java.nio.file.Files;
import java.nio.file.Paths;

import cern.colt.Arrays;
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
//import java.util.*;
import java.util.List;
public class NioFilesInputMethod implements InputStrategy {
	public void readGraphFromFiles(String fileName, Graph<Vertex, Edge> graph) {
		try {
			//int num = 1;
			long time = System.currentTimeMillis();
			List<String> stringStream=Files.readAllLines(Paths.get(fileName));
			for(String string: stringStream) {
			if(!string.matches("\\b*")) {
				if(graph instanceof GraphPoet)
					GraphPoetFactory.parse(string);
				else if(graph instanceof SocialNetwork)
					SocialNetworkFactory.parse(string);
				else if(graph instanceof MovieGraph)
					MovieGraphFactory.parse(string);
				else NetworkTopologyFactory.parse(string);
				//++num;
			}
			//if(num % 100 == 0)
			//	System.out.println(num / 100);
			}
			long totalTime = System.currentTimeMillis() - time;
			 System.out.println("The reading costs " + totalTime + " ms");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
