package P3;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.omg.IOP.ENCODING_CDR_ENCAPS;

public class FriendshipGraph {
	private int i=0, j=0;
	public List<Person> Vertex = new ArrayList<Person>(); //list来存点
	/*
	protected int[][]  edge = new int[10000][10000];  //邻接矩阵来存图
	public FriendshipGraph() {                        //邻接矩阵全部初始化为0
		for(i = 0; i < 10000; ++i) {
			for(j = 0; j < 10000; ++j) {
				edge[i][j] = 0;
			}
		}
	}
	*/	
	public void addVertex(Person p) {
		for(i = 0; i < Vertex.size(); ++i) {
			if(Vertex.get(i).name == p.name) {
				System.out.println("Wrong input, " + p.name + " has been inputed!");
				System.exit(0);
			}
			
		}
		Vertex.add(p);
		p.num = Vertex.size() - 1;   //为每个人设置编号
	}
	public void addEdge(Person p1, Person p2) {
		//edge[p1.num][p2.num] = 1;   //加边, 此处为无向图加边, 所以加两条
		//edge[p2.num][p1.num] = 1;
		p1.friends.add(p2);
	}
	public int getDistance(Person p1, Person p2) {
		int i;
		for(i = 0; i < Vertex.size(); ++i)  //初始情况flag全部设为没访问过
			Vertex.get(i).flag = false;
		for(Person p: Vertex) {
			p.dist = 0;
		}
		//int [] dist = new int[10000];      //dist数组存储每个点到规定起点的最短距离
		//List <Integer> dist = new ArrayList<Integer>();
		Queue<Person> queue = new LinkedList<Person>();
		queue.add(p1);
		p1.flag = true;
		if(p1.equals(p2)) {
			return 0;
		}
		while (!queue.isEmpty()) {      //利用队列进行广度优先搜索
			for(Person p: queue.peek().friends) {
				if(p.flag == false) {
					queue.add(p);
					p.flag = true;
//					Person t=Vertex.get(i);
//					t.flag = true;
//					Vertex.set(i, t);
					p.dist = queue.peek().dist + 1;
					if(p.equals(p2)) {
						return p.dist;
					}
				}
			}
			queue.remove();
		}
//		return dist[p2.num];
		return -1;   //找不到路就返回 -1
	}
	
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		//Person ross = new Person("Rachel");
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
		/*
		Person a = new Person("A");
		Person b = new Person("B");
		Person c = new Person("C");
		Person d = new Person("D");
		Person e = new Person("E");
		Person f = new Person("F");
		Person g = new Person("G");
		graph.addVertex(a);
    	graph.addVertex(b);
    	graph.addVertex(c);
    	graph.addVertex(d);
    	graph.addVertex(e);
    	graph.addVertex(f);
    	graph.addVertex(g);
    	graph.addEdge(a, b);
		graph.addEdge(b, a);
		graph.addEdge(a, c);
		graph.addEdge(c, a);
		graph.addEdge(a, d);
		graph.addEdge(d, a);
		graph.addEdge(b, f);
		graph.addEdge(f, b);
		graph.addEdge(c, e);
		graph.addEdge(e, c);
		graph.addEdge(f, e);
		graph.addEdge(e, f);
		graph.addEdge(c, d);
		graph.addEdge(d, c);
		System.out.println(graph.getDistance(a, b));
		System.out.println(graph.getDistance(a, c));
		System.out.println(graph.getDistance(a, d));
		System.out.println(graph.getDistance(e, f));
		System.out.println(graph.getDistance(a, e));
		System.out.println(graph.getDistance(c, d));
		*/
	}
	
}
