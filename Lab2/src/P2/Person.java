package P2;

import java.util.ArrayList;
import java.util.List;
    //-Abstraction Function:
	//    name is the name of a person
	// -Representation invariant:
	//    name is a non-null String.
    // -Safety from rep exposure
	//    no operation will make the rep exposure.




public class Person {
	private String name;   //名字

	public Person(String s) {
		this.name = s;
	}
}
