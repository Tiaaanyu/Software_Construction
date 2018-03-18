/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import twitter.*;
/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        //throw new RuntimeException("not implemented");
    	int i;
    	List<Tweet> writtenBy = new ArrayList<Tweet>();
    	for(i = 0; i < tweets.size(); ++i) {
    		if(tweets.get(i).getAuthor() == username) {
    			writtenBy.add(tweets.get(i));
    		}
    	}	
    	return writtenBy;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        //throw new RuntimeException("not implemented");
    	int i;
    	List<Tweet> inTimespan = new ArrayList<Tweet>();
    	for(i = 0; i < tweets.size(); ++i) {
    		if(tweets.get(i).getTimestamp().getEpochSecond() > timespan.getStart().getEpochSecond() && tweets.get(i).getTimestamp().getEpochSecond()
    				< timespan.getEnd().getEpochSecond()) {
    			inTimespan.add(tweets.get(i));
    		}
    	}
    	return inTimespan;
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        //throw new RuntimeException("not implemented");
    	List<Tweet> containing = new ArrayList<Tweet>();
    	List<String> textL = new ArrayList<String>();
    	List<String> wordsL = new ArrayList<String>();
    	int i, j, k = 0, flag = 0;
    	for(i = 0; i < tweets.size(); ++i) {
    		textL.add(tweets.get(i).getText().toLowerCase());
    	}
    	for(i = 0; i < words.size(); ++i) {
    		wordsL.add(words.get(i).toLowerCase());
    	}
    	for(i = 0; i < tweets.size(); ++i) {
    		for(j = 0; j < words.size(); ++j) {
    			if(textL.get(i).contains(wordsL.get(j)) == true) {
    				flag = 1;
    				break;
    			}
    			
    		}
    		if(flag == 1)
    			containing.add(tweets.get(i));
    		flag = 0;
    	}
    	return containing;
    }
    /*
    public static void main(String[] args) {
    	Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
        Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
        Instant d3 = Instant.parse("2016-02-16T11:00:00Z");
        Instant d4 = Instant.parse("2016-02-17T11:00:01Z");
        Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
        Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
        Tweet tweet3 = new Tweet(3, "tianyu", "I like to study the Software Construction!", d3);
        Tweet tweet4 = new Tweet(4, "tianyu", "why don't you love me?", d4);
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3, tweet4), Arrays.asList("talk"));
        System.out.println(containing.size());
        for(Tweet t: containing) {
        	System.out.println(t.getText());
        }
    }
    */
}

	












