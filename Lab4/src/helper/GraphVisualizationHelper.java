package helper;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


import vertex.*;
import edge.Edge;
import edge.SameMovieHyperEdge;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;

import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import graph.Graph;


public class GraphVisualizationHelper {
	static SparseMultigraph<Vertex, Edge> graph;
	JFrame frame;
	public GraphVisualizationHelper(Graph<Vertex,Edge> g) {
		createWindow();
		visualize(g);
		showGraph();
	}
	//build GUI window
	private void createWindow() {
		frame=new JFrame("Visual");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    //create graph
	public static void visualize(Graph<Vertex,Edge> g) {
		graph=new SparseMultigraph<Vertex,Edge>();
		for(Vertex v:g.vertices())
			graph.addVertex(v);
		for(Edge e:g.edges())
		{
			if(e.vertices().size()<=2)
			{
				List<Vertex> list = new ArrayList<>();
				for(Vertex ve:e.vertices())
					list.add(ve);
				graph.addEdge(e, list);
			}
			else
			{
				List<Vertex> list = new ArrayList<>();
				for(Vertex ve:e.vertices())
					list.add(ve);
				for(int i=0;i<list.size()-1;i++)
				{
					Edge ed = new SameMovieHyperEdge("actors in a same movie",-1);
					List<Vertex> edl = new ArrayList<>();
					edl.add(list.get(i));
					edl.add(list.get(i+1));
					ed.addVertices(edl);
					graph.addEdge(ed, list.get(i),list.get(i+1));
				}
			}
		}
		
	}
	//show GUI
	private void showGraph() {
		Layout<Vertex, Edge> layout = new CircleLayout<Vertex,Edge>(graph);
		layout.setSize(new Dimension(400, 400)); 
		BasicVisualizationServer<Vertex, Edge> vv = new BasicVisualizationServer<Vertex, Edge>(layout);
		vv.setPreferredSize(new Dimension(500, 500));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		vv.getRenderContext().setEdgeLabelTransformer(
				new ToStringLabeller<Edge>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
		log.Log.logger.info("Show the GUI.");
	}
}
