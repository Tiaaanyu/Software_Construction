package helper;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public class CentralityStrategyHelper {
	public static double parseAndExecute(String string, Graph<Vertex, Edge> g, Vertex v) {
		if(string.equals("degreeCentrality")) {
			return new DegreeCentralityStrategy().centralityStrategy(g, v);
		}
		else if(string.equals("closenessCentrality")) {
			return new ClosenessCentralityStrategy().centralityStrategy(g, v);
		}
		else if(string.equals("betweennessCentrality")) {
			return new BetweennessCentralityStrategy().centralityStrategy(g, v);
		}
		else if(string.equals("indegreeCentrality")) {
			return new InDegreeCentralityStrategy().centralityStrategy(g, v);
		}
		else if(string.equals("outdegreeCentrality")) {
			return new OutDegreeCentralityStrategy().centralityStrategy(g, v);
		}
		else if(string.equals("eccentricity")) {
			return new EccentricityStrategy().centralityStrategy(g, v);
		}
		else return 0;
	}
}
