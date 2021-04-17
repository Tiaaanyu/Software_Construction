package P4.twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MySocialNetwork {

	public static boolean isStrongTie(String s1, String s2, Map<String, Set<String>> map) {
		if (map.keySet().contains(s1) && map.get(s1).contains(s2)) {
			if (map.keySet().contains(s2) && map.get(s2).contains(s1))
				return true;
			else
				return false;
		} else
			return false;
	}

	public static Map<String, Set<String>> MyguessFollowsGraph(List<Tweet> tweets) {
		// throw new RuntimeException("not implemented");
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		int i;
		for (i = 0; i < tweets.size(); ++i) {
			String name = tweets.get(i).getAuthor();
			Set<String> followers = new HashSet<>();
			followers = P4.twitter.Extract.getMentionedUsers(Arrays.asList(tweets.get(i)));
			if (map.containsKey(name)) {
				for (Iterator<String> it = followers.iterator(); it.hasNext();) {
					map.get(name).add(it.next());
				}
			} else
				map.put(name, followers);}
			Map<String, List<String>> map1 = new HashMap<>();
			for (String s : map.keySet()) {
				for (String k : map.get(s)) {
					if (map.keySet().contains(k) && map.get(k).contains(s)) {
						for (String m : map.get(k)) {
							if (map.keySet().contains(m) && map.get(m).contains(k)) {
								if (map1.containsKey(s) && map1.containsKey(m)) {
									map1.get(s).add(m);
									map1.get(m).add(s);
								}
								else if(map1.containsKey(s) && !map1.containsKey(m)) {
									map1.get(s).add(m);
									List<String> l1 = new ArrayList<>();
									l1.add(s);
									map1.put(m, l1);
								}
								else if(!map1.containsKey(s) && map1.containsKey(m)) {
									map1.get(m).add(s);
									List<String> l1 = new ArrayList<>();
									l1.add(m);
									map1.put(s, l1);
								}
								else if(!map1.containsKey(s) && !map1.containsKey(m)) {
									List<String> l1 = new ArrayList<>();
									List<String> l2 = new ArrayList<>();
									l1.add(s);
									l2.add(m);
									map1.put(s, l2);
									map1.put(m, l1);
								}
								
							}
						}
					}
				}
			}
			for (String s : map1.keySet()) {
				for (i = 0; i < map1.get(s).size(); ++i) {
					map.get(s).add(map1.get(s).get(i));
				}
			}
            for(String s: map.keySet()) {
            	map.get(s).remove(s);
            }
			return map;
		}


	public static void main(String[] args) {
		Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
		Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
		Instant d3 = Instant.parse("2016-02-16T11:00:00Z");
		Instant d4 = Instant.parse("2016-02-17T11:00:01Z");
		Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?,@tianyu,@bbk", d1);
		Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype,@tianyu", d2);
		Tweet tweet3 = new Tweet(3, "tianyu", "I like to study the Software Construction!,@alyssa", d3);
		Tweet tweet4 = new Tweet(4, "tianyu", "why don't you love me?@bbk, @bbitdiddle", d4);
		Map<String, Set<String>> followsGraph = MySocialNetwork
				.MyguessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
		System.out.println(followsGraph.get("alyssa"));
		System.out.println(followsGraph.get("bbitdiddle"));
		System.out.println(followsGraph.get("tianyu"));

	}
}
