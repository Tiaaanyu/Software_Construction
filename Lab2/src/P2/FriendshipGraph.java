package P2;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import P1.*;
import P1.graph.ConcreteEdgesGraph;
import P1.graph.ConcreteVerticesGraph;
import P1.graph.Graph;
public class FriendshipGraph {
	// -Abstraction Function:
	//    graph is the social network.
	// -Representation invariant:
	//    graph is a non-null graph, so the vertices.size() is more than 1.
    // -Safety from rep exposure
	//    no operation will make the rep exposure. 
     Graph<Person> graph = new ConcreteVerticesGraph<>();
     /**
      * and an vertex into the graph
      * @param p represents the person 
      */ 
     public void addVertex(Person p) {
    	 graph.add(p);
     }
     /**
      * add an edge between two people
      * @param p1  person 1
      * @param p2  person 2
      */
     public void addEdge(Person p1, Person p2) {
    	 graph.set(p1, p2, 1);
     }
     /**
      * get the distance of two people
      * @param p1
      * @param p2
      * @return the distance
      */
     public int getDistance(Person p1, Person p2) {
    	 Iterator<Person> it = graph.vertices().iterator();
    	 Map<Person, Boolean> flag = new HashMap<>();
    	 Map<Person, Integer> dist = new HashMap<>();
    	 while (it.hasNext()) {
			Person person = (Person) it.next();
			flag.put(person, false);
			dist.put(person, 0);
		}
    	 Queue<Person> queue = new LinkedList<>();
    	 queue.add(p1);
    	 flag.put(p1,true);
    	 if(p1.equals(p2)) 
    		 return 0;
    	 while (!queue.isEmpty()) {
    		 for(Person p: graph.sources(queue.peek()).keySet()) {
    			 if(flag.get(p) == false) {
 					queue.add(p);
 					flag.put(p,true);
 					dist.put(p, dist.get(queue.peek()) + 1);
 					if(p.equals(p2)) {
 						return dist.get(p);
 					}
 				}
    		 }
    		 queue.remove();
    	 }
    	 return -1;
     }
    public static void main(String[] args) {
    	FriendshipGraph graph = new FriendshipGraph();
    	Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel));
		System.out.println(graph.getDistance(rachel, kramer));
    	
    }
}
