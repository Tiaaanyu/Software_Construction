/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;
import org.omg.PortableServer.ServantActivator;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-16T11:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T11:00:01Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "@is, it reasonable to talk ,@ggabout rivest so much?@tianyu,?@what,@you,bala@qq.com", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "tianyu", "I like to study the Software Construction!", d3);
    private static final Tweet tweet4 = new Tweet(4, "lalala", "why don't you love me?", d4);
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        
        assertEquals(d3, timespan.getStart());
        assertEquals(d4, timespan.getEnd());
        Timespan timespan2 = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3)); 
        assertEquals(d3, timespan2.getStart());
        assertEquals(d2, timespan2.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        //assertTrue("expected empty set", mentionedUsers.isEmpty());
        assertFalse(mentionedUsers.contains("qq"));
        assertEquals(true, mentionedUsers.contains("is"));
        assertEquals(true, mentionedUsers.contains("tianyu"));
        
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
