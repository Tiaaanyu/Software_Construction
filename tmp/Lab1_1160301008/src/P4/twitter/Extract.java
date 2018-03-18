/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

	/**
	 * Get the time period spanned by tweets.
	 * 
	 * @param tweets
	 *            list of tweets with distinct ids, not modified by this method.
	 * @return a minimum-length time interval that contains the timestamp of every
	 *         tweet in the list.
	 */
	public static Timespan getTimespan(List<Tweet> tweets) {
		// throw new RuntimeException("not implemented");
		int i, j, start = 0, end = 0;
		// long timespan = Long.MAX_VALUE;
		// long[] time = new long[10000];
		for (i = 0; i < tweets.size(); ++i) {
			if (tweets.get(i).getTimestamp().isBefore(tweets.get(start).getTimestamp()))
				start = i;
			else if (tweets.get(i).getTimestamp().isAfter(tweets.get(end).getTimestamp()))
				end = i;
		}
		Timespan ts = new Timespan(tweets.get(start).getTimestamp(), tweets.get(end).getTimestamp());
		return ts;
	}

	/**
	 * Determine the char is legal for a name or not.
	 * 
	 * @param ch
	 *            the char before the '@'
	 * @return if the ch is legal
	 */
	public static boolean isLegal(char ch) {
		if (ch >= 'A' && ch <= 'Z')
			return true;
		else if (ch >= 'a' && ch <= 'z')
			return true;
		else if (ch >= '0' && ch <= '9')
			return true;
		else if (ch == '-' || ch == '_')
			return true;
		else
			return false;

	}

	/**
	 * Get usernames mentioned in a list of tweets.
	 * 
	 * @param tweets
	 *            list of tweets with distinct ids, not modified by this method.
	 * @return the set of usernames who are mentioned in the text of the tweets. A
	 *         username-mention is "@" followed by a Twitter username (as defined by
	 *         Tweet.getAuthor()'s spec). The username-mention cannot be immediately
	 *         preceded or followed by any character valid in a Twitter username.
	 *         For this reason, an email address like bitdiddle@mit.edu does NOT
	 *         contain a mention of the username mit. Twitter usernames are
	 *         case-insensitive, and the returned set may include a username at most
	 *         once.
	 */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
		// throw new RuntimeException("not implemented");
		int i = 0, j = 0;
		boolean flag = false;
		Set<String> mentionedUsers = new HashSet<String>();
		StringBuffer mentioned = new StringBuffer();
		for (i = 0; i < tweets.size(); ++i) {
			mentioned.setLength(0);
			flag = false;
			for (j = 0; j < tweets.get(i).getText().length(); ++j) {
				if ((tweets.get(i).getText().charAt(j) == '@' && j > 0
						&& (!isLegal(tweets.get(i).getText().charAt(j - 1))))
						|| (j == 0 && tweets.get(i).getText().charAt(j) == '@')) {
					flag = true;
					continue;
				} else if (isLegal(tweets.get(i).getText().charAt(j)) && flag == true) {
					mentioned.append(tweets.get(i).getText().charAt(j));
					if (j == tweets.get(i).getText().length() - 1) {
						String h = mentioned.toString().toLowerCase();
						if (!mentionedUsers.contains(h)) {

							mentionedUsers.add(h);
							mentioned.setLength(0);
							flag = false;
						}
						// System.out.println(mentioned.toString());
					}
				} else if (!isLegal(tweets.get(i).getText().charAt(j))) {
					if (mentioned.length() != 0) {
						String h = mentioned.toString().toLowerCase();
						if (!mentionedUsers.contains(h)) {
							mentionedUsers.add(h);
							 mentioned.setLength(0);
							 flag = false;
						}

					}
					mentioned.setLength(0);
					flag = false;
				}
			}
		}
		// System.out.println(mentionedUsers.size());
		mentionedUsers.remove(" ");
		return mentionedUsers;
	}
	/*
	 * public static Set<String> getMentionedUsers1(List<Tweet> tweets) {
	 * Set<String> ans = new HashSet<String>(); Set<String> allname = new
	 * HashSet<String>(); for (int i = 0; i < tweets.size(); i++) { String s =
	 * tweets.get(i).getText(); Pattern pattern =
	 * Pattern.compile("(?<![a-zA-Z0-9_-])@[a-zA-Z0-9_-]+"); Matcher matcher =
	 * pattern.matcher(s); while (matcher.find()) { String user =
	 * tweets.get(i).getText().substring(matcher.start() + 1, matcher.end()); if
	 * (allname.contains(user.toLowerCase())) continue; ans.add(user.toLowerCase());
	 * allname.add(user.toLowerCase()); } }
	 * 
	 * return ans;
	 * 
	 * }
	 */
}
