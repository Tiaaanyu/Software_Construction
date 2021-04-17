package IOStrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public class WriterMethod implements OutPutStrategy {
	public void writeToFilesFromGraph(String fileName, Graph<Vertex, Edge> graph) {
		try {
			int num = 1;
			long time = System.currentTimeMillis();
      FileWriter fw = new FileWriter("src/output.txt", true);
      for(Vertex x: graph.vertices()) {
      	fw.write(x.toString());
      	++num;
      	if(num % 100 == 0)
      		System.out.println(num / 100);
      }
      for(Edge y: graph.edges()) {
      	fw.write(y.toString());
      	++num;
      	if(num % 100 == 0)
      		System.out.println(num / 100);
      }
      fw.close();
			long totalTime = System.currentTimeMillis() - time;
			System.out.println("The writing costs " + totalTime + "ms");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
