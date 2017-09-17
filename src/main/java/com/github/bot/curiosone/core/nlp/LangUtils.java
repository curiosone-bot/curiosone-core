package com.github.bot.curiosone.core.nlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides utility methos to perform basic Natural Language Process tasks.
 */
public class LangUtils {

  /**
   * Splits a text in sentences by punctuation.
   * @param str The original text to be splitted into sentences.
   * @return a list of splitted sentences.
   */
  public static List<String> splitByPuntaction(String str) {
    StringBuffer buff = new StringBuffer();
    ArrayList<String> l = new ArrayList<>();

    boolean email = false;
    boolean uri = false;

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);

      // Ignore puntaction and spacing at the begining of a phrase.
      if (buff.length() == 0 && (c == '!' || c == '?' || c == '.')) {
        continue;
      }
      if (c == ' ') {
        uri = false;
        email = false;
      }
      buff.append(c);
      // Ignore puntaction inside URIs and inside emails
      if ((email || uri) && (c == '!' || c == '?' || c == '.')) {
        continue;
      }
      if (Character.isLetter(c)) {
        continue;
      }

      // Look ahead for @
      int j = i;
      while (++j < str.length()) {
        if (str.charAt(j) == ' ') {
          break;
        }
        if (str.charAt(j) == '@') {
          email = true;
          break;
        }
      }
      if (email) {
        continue;
      }

      // Look behind for ://
      int len = 0;
      int k = i;
      while (--k >= 0) {
        if (str.charAt(k) == ' ') {
          break;
        }
        if (str.charAt(k) == '/') {
          if (len <= 1) {
            len++;
          } else {
            len = 0;
          }
        } else if (str.charAt(k) == ':') {
          if (len == 2) {
            uri = true;
            break;
          }
        }
      }
      if (uri) {
        continue;
      }

      char p = 0;
      char n = 0;
      if (i + 1 < str.length()) {
        n = str.charAt(i + 1);
      }
      if (i - 1 >= 0) {
        p = str.charAt(i - 1);
      }
      if (c == '?'
          || c == '!'
          || c == '.' && !Character.isDigit(p) && !Character.isDigit(n)) {
        l.add(buff.toString());
        buff = new StringBuffer();
      }
    }
    if (buff.length() > 0) {
      buff.append('.');
      l.add(buff.toString());
    }
    return l;
  }

  /**
   * Removes duplicated spaces from a String.
   * @param str The string with duplicated spaces.
   * @return the original string without double spaces.
   */
  public static String removeDuplicatedSpaces(String str) {
    StringBuffer buff = new StringBuffer();

    int start = 0;
    int last = str.length() - 1;
    while (start < str.length() && str.charAt(start) == ' ') {
      start++;
    }
    while (last >= 0 && str.charAt(last) == ' ') {
      last--;
    }
    for (int i = start; i < last; i++) {
      char c = str.charAt(i);
      char n = str.charAt(i + 1);
      if (c == ' ' && n == ' ') {
        continue;
      }
      buff.append(c);
    }
    if (last >= 0) {
      buff.append(str.charAt(last));
    }
    return buff.toString();
  }

  public static String expandVerbs(String toExpand) {
    String exp = toExpand;
    String[] from = {"'m", "'M", "'s", "'S", "'re", "'RE", "'Re", "'rE",
        "'ve", "'VE", "'vE", "'Ve", "'ll", "'LL", "'lL", "'Ll", "won't",
        "can't", "wouldn't", "couldn't", "didn't"};
    String[] to = {" am", " AM", " is", " IS", " are", " ARE", " are", " are",
        " have", " HAVE", " have", " have", " will", " WILL", " will", " will",
        "will not", "cannot", "would not", "could not", "did not"};

    for (int i = 0; i < from.length; i++) {
      exp = toExpand.replace(from[i], to[i]);
      toExpand = exp;
    }
    return exp;
  }

  /**
   * Expands all contracted form verbs from a String.
   * @param str The sentence with contracted form verbs.
   * @return The content of the original String with all expanded form verbs.
   */

  public static String expandVerbs2(String str) {
    StringBuffer buff = new StringBuffer();
    String[] subjs = {"i", "you", "he", "she", "it", "we", "you", "they"};
    String[] shorts = {"m", "M", "s", "S", "re", "rE", "Re", "RE", "ve", "vE",
      "Ve", "VE", "ll", "lL", "Ll", "LL", "won't"};
    String[] longs = {" am", " AM", " is", " IS", " are", " are", " are",
      " ARE", " have", " have", " have", " HAVE", " will", " will", " will",
      " WILL", " will not"};

    for (int i = 0; i < str.length() - 1; i++) {
      char c = str.charAt(i);
      if ((c != '\'' || i == 0) && c != ' ') {
        buff.append(c);
        continue;
      }
      // Search for a subject before the apostrophe
      boolean found = false;
      String subject = str.substring(Math.max(i - 5, 0), i).toLowerCase();
      for (String sub : subjs) {
        if (subject.length() < sub.length()) {
          continue;
        }
        int j = 0;
        boolean fail = false;
        for (; j < sub.length(); j++) {
          if (subject.charAt(subject.length() - 1 - j) != subject.charAt(sub.length() - 1 - j)) {
            fail = true;
            break;
          }
        }
        if (!fail) {
          // If there isn't a space before the found subject
          if (i - 1 - j > 0 && str.charAt(i - 1 - j) != ' ') {
            continue;
          }
          found = true;
          break;
        }
      }
      if (!found) {
        buff.append(c);
        continue;
      }
      // Search if we know this abbreviation
      boolean match = false;
      String verb = str.substring(i + 1, Math.min(i + 5 + 1, str.length()));
      for (int j = 0; j < shorts.length; j++) {
        String sub = verb.substring(0, Math.min(shorts[j].length(), verb.length()));
        //System.out.print("sub: " + sub + " ");
        if (!shorts[j].equals(sub)) {
          continue;
        }
        // If there isn't a space after the found match
        if (i + 1 + sub.length() < str.length() && str.charAt(i + 1 + shorts[j].length()) != ' ') {
          continue;
        }
        match = true;
        buff.append(longs[j]);
        i += sub.length();
        break;
      }
      if (!match) {
        buff.append(c);
        continue;
      }
    }
    buff.append(str.charAt(str.length() - 1));
    return buff.toString();
  }
}
