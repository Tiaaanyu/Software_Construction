/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
	
	Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    Instant d3 = Instant.parse("2016-02-16T11:00:00Z");
    Instant d4 = Instant.parse("2016-02-17T11:00:01Z");
    Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?,@tianyu,@bbk", d1);
    Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    Tweet tweet3 = new Tweet(3, "tianyu", "I like to study the Software Construction!,@alyssa", d3);
    Tweet tweet4 = new Tweet(4, "tianyu", "why don't you love me?@bbk, @bbitdiddle", d4);
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        
        assertFalse("expected empty graph", followsGraph.isEmpty());
        assertTrue(followsGraph.get("tianyu").contains("alyssa"));
        assertTrue(followsGraph.get("alyssa").contains("tianyu"));
        assertFalse(followsGraph.get("alyssa").contains("bbitdiddle"));
        
      
        
    }
    
    @Test
    public void testInfluencersEmpty() {
        //Map<String, Set<String>> followsGraph = new HashMap<>();
    	Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        List<String> influencer = SocialNetwork.influencers(followsGraph);
        
        assertFalse("expected empty list", SocialNetwork.influencers(followsGraph).isEmpty());
        assertEquals("bbk", SocialNetwork.influencers(followsGraph).get(0));
        //assertEquals("alyssa", influencers.get(1));
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
