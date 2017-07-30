package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tokenization of the input string.
 * @author Eugenio Schintu && Andrea Rivitto
 */

public class Tokenizer {

  /**
   * String provided in input by the user.
   */

  private StringBuilder sb;

  /**
   * Typology of Sentence.
   * @see SentenceT
   */

  private SentenceT type;

  /**
   * Generated tokens' list.
   */

  private List<IToken> tokens;

  /**
   * Alphanumeric values' list.
   */

  private final List<String> alphaNum = Arrays.asList("a","b","c","d","e","f","g","h","j","k","i",
      "l", "m","n","o","p","q","r","s","t","u","v","w", "x", "y", "z","1", "2","3","4", "5",
      "6", "7", "8", "9", "0");

  /**
   * Constructor.
   * @param input is a string provided directly from the user
   */

  public Tokenizer(String input) {
    sb = new StringBuilder(input);
    type = null;
    tokens = new ArrayList<IToken>();
  }

  /**
   * Tokenizes the string provided in input by the user.
   * @return a new Sentence
   */

  public Sentence getSentence() {
    type = checkSentence();
    return new Sentence(getType(), sb.toString(), tokens);
  }

  /**
   * Detected in the {@link #input} if there is more than one sentence, and if it contains more
   * than one sentence, {@link #type} has a special value.
   * @return type as a special value
   */
  public SentenceT checkSentence() {
    if (sb.length() - 1 == sb.indexOf("?") || sb.length() - 1 == sb.indexOf("!")
        || sb.length() - 1 == sb.indexOf(".")) {
      return type = checkType();
    }
    return type = SentenceT.MORE_SENTENCE;
  }

  /**
   * Checks if the {@link #input} string is a question or an affirmation/answer.
   * @return type of sentence
   * @see SentenceT
   */

  public SentenceT checkType() {
    return sb.charAt(sb.length() - 1) == '?'
        ? SentenceT.QUESTION : SentenceT.ANSWER;
  }

  /**
   * This method checks if in {@link #input} there is an incorrect punctuation.
   * @return {@link #input} cleaned from punctuation errors
   */

  public String checkPunct() {
    for (int i = 0; i < sb.length(); i++) {
      if (!alphaNum.contains(("" + sb.charAt(i)).toLowerCase())) {
        switch (sb.charAt(i) + "") {
          case "." :
            sb = checkPoint(i);
            break;
          case "'":
            sb = checkApost(i + 1);
            break;
          case "@":
            sb = checkNet(i);
            break;
          case " ":
            continue;
          default:
            sb.delete(i, i + 1);
            sb.insert(i, " ");
        }
      }
    }
    return sb.toString();
  }

  /**
   * Checks if before and/or after a point there are some numeric value and in the case it's false,
   * the point will be deleted.
   * @param startIndex the point where is the point
   * @return sb modified and cleaned by typing errors
   */

  private StringBuilder checkPoint(int startIndex) {
    if (startIndex < 1) {
      if (!Character.isDigit(sb.charAt(startIndex + 1)) && sb.indexOf("@") == -1) {
        delete(startIndex);
      }
    } else if ((!(Character.isDigit(sb.charAt(startIndex - 1))
          || Character.isDigit(sb.charAt(startIndex + 1))) && sb.indexOf("@") == -1)
          || startIndex == sb.length() - 1) {
      delete(startIndex);
    }
    return sb;
  }

  private StringBuilder delete(int index) {
    sb.deleteCharAt(index);
    sb.insert(index, " ");
    return sb;
  }

  /**
   * MANCANO 'd E 's PERCHE' NON SO COME IMPLEMENTARLI
   * Checks if the apostrophe in the {@link #input} is a typing error
   * or an abbreviation for a particular verb (ex: 's -> is/has, 'm -> am, 'll -> will, etc...).
   * @param startIndex index after the apostrophe
   * @return sb modified and cleaned by typing errors
   */

  private StringBuilder checkApost(int startIndex) {
    boolean flag = false;
    if (sb.charAt(startIndex) == 'm') {
      sb.replace(startIndex - 1, startIndex + 1, " am");
      flag = true;
    }
    if (("" + sb.charAt(startIndex) + sb.charAt(startIndex + 1)).equals("re")) {
      sb.replace(startIndex - 1, startIndex + 2, " are");
      flag = true;
    }
    if (("" + sb.charAt(startIndex) + sb.charAt(startIndex + 1)).equals("ll")) {
      sb.replace(startIndex - 1, startIndex + 2 , " will");
      flag = true;
    }
    if (("" + sb.charAt(startIndex) + sb.charAt(startIndex + 1)).equals("ve")) {
      sb.replace(startIndex - 1, startIndex + 2, " have");
      flag = true;
    }

    if (!flag) {
      delete(startIndex - 1);
    }
    return sb;
  }

  /**
   * Check if the net found is a typing error or belongs to an email address.
   * @param startIndex index where is the net
   * @return sb modified and cleanded by typing errors
   */

  private StringBuilder checkNet(int startIndex) {

    return sb;
  }

  /**
   * Get the type.
   * @return type
   */

  public SentenceT getType() {
    return type;
  }

  /**
   * Set {@link #type} to a new value provided in input.
   * @param t of new value
   */

  public void setType(SentenceT t) {
    this.type = t;
  }
}
