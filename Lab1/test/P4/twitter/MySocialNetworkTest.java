package P4.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MySocialNetworkTest {
	Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    Instant d3 = Instant.parse("2016-02-16T11:00:00Z");
    Instant d4 = Instant.parse("2016-02-17T11:00:01Z");
    Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?,@tianyu,@bbk", d1);
    Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype, @tianyu", d2);
    Tweet tweet3 = new Tweet(3, "tianyu", "I like to study the Software Construction!,@alyssa", d3);
    Tweet tweet4 = new Tweet(4, "tianyu", "why don't you love me?@bbk, @bbitdiddle", d4);
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test 
    public void guessFollowsGraphTest() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.MyguessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
    	assertTrue(followsGraph.get("alyssa").contains("bbitdiddle"));
    	assertTrue(followsGraph.get("bbitdiddle").contains("alyssa"));
    	assertTrue(followsGraph.get("alyssa").contains("bbk"));
    }
}
