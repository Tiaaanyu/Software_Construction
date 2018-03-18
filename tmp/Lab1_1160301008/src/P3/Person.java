package P3;

import java.util.ArrayList;
import java.util.List;

public class Person {
	protected String name;   //名字
	protected boolean flag = false; //访问标志
	protected int num; //编号
	public List<Person> friends = new ArrayList<Person>();   //存储每个人相连的其他人
	protected int dist;
	public Person(String s) {
		this.name = s;
		dist = 0;
	}
}
