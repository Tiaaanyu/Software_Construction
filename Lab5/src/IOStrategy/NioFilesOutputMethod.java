package IOStrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.validator.PublicClassValidator;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public class NioFilesOutputMethod implements OutPutStrategy{
	public void writeToFilesFromGraph(String fileName, Graph<Vertex, Edge> graph) {
		try {
			String str = new String();
			long time = System.currentTimeMillis();
			File file = new File("src/output.txt");
			FileOutputStream outputStream = new FileOutputStream(file);
      FileChannel channel = outputStream.getChannel();
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      for(Vertex x: graph.vertices()) {
      	str = x.toString();
      	buffer.put(str.toString().getBytes());
  			buffer.flip();
  			channel.write(buffer);
  			buffer.clear();
      }
			for(Edge y: graph.edges()) {
				str = y.toString();
				buffer.put(str.toString().getBytes());
  			buffer.flip();
  			channel.write(buffer);
  			buffer.clear();
			}
			
			channel.close();
			outputStream.close();
			
			long totalTime = System.currentTimeMillis() - time;
			System.out.println("The writing costs " + totalTime + "ms");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
