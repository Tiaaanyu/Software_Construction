/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import twitter.Extract;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.attribute.standard.RequestingUserName;
import javax.security.auth.kerberos.KerberosKey;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
    	Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    	int i;
    	for(i = 0; i < tweets.size(); ++i) {
    		String name = tweets.get(i).getAuthor();
    		Set<String> followers = new HashSet<>();
    		followers = twitter.Extract.getMentionedUsers(Arrays.asList(tweets.get(i)));
    		if(map.containsKey(name)) {
    			for(Iterator<String> it = followers.iterator(); it.hasNext();) {
    				map.get(name).add(it.next());
    			}
    		}
    		else map.put(name, followers);
    		
    	}
    	return map;
    	
    	
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> followers = new HashMap<>();
        int i = 0, j = 0;
        List<String> name = new ArrayList<>();
        List<String> influencer = new ArrayList<>();
        for(String s: followsGraph.keySet()) {
        	for(String k: followsGraph.get(s)) {
        		if(!name.contains(k))
        			name.add(k);
        	}
        }
        for(i = 0; i < name.size(); ++i) {
        	for(String s: followsGraph.keySet()) {
        		for(String k: followsGraph.get(s)) {
        			if(k.equals(name.get(i)))
        				j += 1;
        		}
        	}
        	followers.put(name.get(i), j);
        	j = 0;
        }
        //System.out.println(followers.get("realdonaldtrump--this"));
        String h = new String();
    while(!followers.isEmpty()) {
    	j = 0;
    	for(String s: followers.keySet()) {
    		if(followers.get(s) > j) {
    			h = s;
    			j = followers.get(s);
    		}
    	}
    	influencer.add(h);
    	followers.remove(h);
    }
   
    return influencer;
    }
    
     
}
