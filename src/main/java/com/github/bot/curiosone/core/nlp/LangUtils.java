package com.github.bot.curiosone.core.nlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.lang.StringBuffer;

/**
 * Provides utility methos to perform basic Natural Language Process tasks.
 */
public class LangUtils {

  private static final String[] CONTRACTED_VERBS = {"'m", "'M", "'s", "'S",
      "'re", "'RE", "'Re", "'rE", "'ve", "'VE", "'vE", "'Ve", "'ll", "'LL",
      "'lL", "'Ll", "won't", "can't", "wouldn't", "couldn't", "didn't"};

  private static final String[] EXPANDED_VERBS = {" am", " AM", " is", " IS",
      " are", " ARE", " are", " are", " have", " HAVE", " have", " have",
      " will", " WILL", " will", " will", "will not", "cannot", "would not",
      "could not", "did not"};
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

  /**
   * Expands contracted form verbs in the given sentence.
   * Please note that "'s" (Saxon Genitive) could be interpreted as contraction
   * for "is" verb.
   * @param contracted the phrase to work with
   * @return the given phrase with all verbs in extended form.
   */
  public static String expandVerbs(String contracted) {
    //StringBuffer sb = new StringBuffer(contracted);
    String expanded = contracted;
    for (int i = 0; i < CONTRACTED_VERBS.length; i++) {
      expanded = contracted.replace(CONTRACTED_VERBS[i], EXPANDED_VERBS[i]);
      contracted = expanded;
    }
    return expanded;
  }
}
