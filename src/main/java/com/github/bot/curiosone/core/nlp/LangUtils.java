package com.github.bot.curiosone.core.nlp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides utility methos to perform basic Natural Language Process tasks.
 */
public class LangUtils {

  /**
   * Stores the regex used to remove all non-alphanumeric characters from a String.
   */
  private static final String REGEX_REMOVE_NON_ALPHANUM = "[^A-Za-z0-9 ]";

  /**
   * Stores an empty String.
   */
  private static final String EMPTY_STRING = "";

  /**
   * Stores all supported verbs abbreviations in the expandVerbs method.
   */
  private static final String[] SHORTS = {"'m", "'s", "'re", "'ve", "'ll",
      "won't", "n't"};

  /**
   * Stores all the expanded forms for the supported contracted verbs by expandVerbs method.
   */
  private static final String[] LONGS = {" am", " is", " are", " have", " will",
      "will not", " not"};

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
    return StringUtils.normalizeSpace(str);
  }

  /**
   * Expands all the contracted form verbs in a sentence.
   * Note: Works only with lowercased Strings.
   * Note: Saxon Genitive abbreviations could wrongly be treated as "is".
   * @param contracted the phrase to work with
   * @return a new String, containing the old phrase with all verbs expanded
   */
  public static String expandVerbs(String contracted) {
    return StringUtils.replaceEachRepeatedly(contracted, SHORTS, LONGS);
  }

  /**
   * Removes non-alphanumeric characters from the given String.
   * @param str the String to remove non-alphanumeric characters from
   * @return a new String containing the result of the non-alphanumeric characters removal
   */
  public static String removeNonAlphaNumeric(String str) {
    return str.replaceAll(REGEX_REMOVE_NON_ALPHANUM, "");
  }
}
