package IOStrategy;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public class StreamOutPutMethod implements OutPutStrategy {
	public void writeToFilesFromGraph(String fileName, Graph<Vertex, Edge> graph) {
		try {
			int num = 1;
			FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
			//BufferedOutputStream bf = new BufferedOutputStream(fileOutputStream);
			long time = System.currentTimeMillis();
			for(Vertex x: graph.vertices()) {
				fileOutputStream.write(x.toString().getBytes());
				++num;
				if(num % 100 == 0)
					System.out.println(num / 100);
			}
				
			for(Edge y: graph.edges()) {
				fileOutputStream.write(y.toString().getBytes());
				++num;
				if(num % 100 == 0)
					System.out.println(num / 100);
			}
				
			long totalTime = System.currentTimeMillis() - time;
			System.out.println("The writing costs " + totalTime + "ms");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
