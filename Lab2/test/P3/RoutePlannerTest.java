package P3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.*;
import P3.RoutePlanner;
public class RoutePlannerTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	//   test strategy:
	// -routePlannerTest
	//   create a graph from the "inputdata.csv" ,
	//   the file is under:
	//  AA,4
	//  A,1,0,340
	//  B,2,0,370
	//  C,3,0,450
	//  D,4,0,510
	//  BB,4
	//  A,1,0,335
	//  B,2,0,385
	//  E,5,0,435
	//  D,4,0,485
	//  CC,4
	//  A,1,0,400
	//  B,2,0,425
	//  F,6,0,435
	//  D,4,0,445
	//    and make the start stop is A, and the destination is D
	//    then compute the route, and get the itinerary, check some
	//    members in the itinerary, at last, check some words in the instructions
	//    to determine our result is right or not.
	// -findStopsBySubstringTest
	//    create a graph form the "inputdata2.csv", 
	//    and check the rp.findStopsBySubstring("at").size() and 
	//    rp.findStopsBySubstring("at").size()
	@Test
	public void RoutePlannerTest() {
		RoutePlannerBuilder rb = new RoutePlannerBuilder();
		RoutePlanner rp = rb.build("src/inputdata.csv", 45);  //maxtimelimit, will be 1200 in teacher's test
		rp.createGraph();
		Stop A = new Stop(1, 0, "A", 0);
		Stop D = new Stop(4, 0, "D", 0); 
		Itinerary it = rp.computeRoute(A, D, 300);
		//System.out.println(it.getInstructions());
		assertEquals("D", it.getEndLocation().getName());
		assertEquals(445, it.getEndLocation().getTime());
		assertEquals("CC", it.getEndLocation().getRouteName());
		assertEquals(145, it.getWaitTime());
		assertEquals(300, it.getStartTime());
		assertTrue(it.getInstructions().contains("take CC arrive at D at 445"));
		assertTrue(it.getInstructions().contains("At first, you take BB at A at 335"));
		assertTrue(it.getInstructions().contains("Change route here, wait for 40"));
		
	}
	@Test
	public void findStopsBySubstringTest() {
		RoutePlannerBuilder rb = new RoutePlannerBuilder();
		RoutePlanner rp = rb.build("src/inputdata2.csv", 45); 
		rp.createGraph();
		assertEquals(5, rp.findStopsBySubstring("at").size());
		assertEquals(2, rp.findStopsBySubstring("wh").size());
	}
}
