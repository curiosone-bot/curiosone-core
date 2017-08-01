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

  private StringBuilder inputUser;

  /**
   * String that at the beginnig is equals to {@link #iu},
   * but it will be modified.
   */
  private StringBuilder iu;

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
      "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5",
      "6", "7","8","9","0");

  /**
   * Constructor.
   * @param input is a string provided directly from the user
   */

  public Tokenizer(String input) {
    inputUser = new StringBuilder(input);
    iu = new StringBuilder(input);
    type = null;
    tokens = new ArrayList<IToken>();
  }

  /**
   * Tokenizes the string provided in input by the user.
   * @return a new Sentence
   */

  public Sentence getSentence() {
    checkSentence();
    checkPunct();
    createListOfTokens();
    return new Sentence(getType(), getInputUser(), getTokens());
  }

  /**
   * Detected in the {@link #input} if there is more than one sentence, and if it contains more
   * than one sentence, {@link #type} has a special value.
   * Also checks if the {@link #input} string is a question or an affirmation/answer.
   * @return type as a special value
   */
  public SentenceT checkSentence() {
    for (int i = 0; i < iu.length(); i++) {
      if (i < iu.length() - 2) {
        if (!alphaNum.contains(("" + iu.charAt(i)).toLowerCase())) {
          switch ("" + iu.charAt(i)) {
            case ".":
              setType(SentenceT.MORE_SENTENCE);
              return getType();
            case "?":
              setType(SentenceT.MORE_SENTENCE);
              return getType();
            case "!":
              setType(SentenceT.MORE_SENTENCE);
              return getType();
            default:
              continue;
          }
        }
      } else {
        setType(checkType());
      }
    }
    return getType();
  }

  /**
   * Checks if the {@link #input} string is a question or an affirmation/answer.
   * @return type of sentence
  * @see SentenceT
  */

  private SentenceT checkType() {
    return iu.charAt(iu.length() - 1) == '?'
         ? SentenceT.QUESTION : SentenceT.ANSWER;
  }
  /**
   * This method creates the elements of {@link #tokens} from {@link #iu}.
   * @return {@link #tokens} with all elements
   */

  public List<IToken> createListOfTokens() {
    String[] tok = checkPunct().split(" ");
    for (String t : tok) {
      addToken(new Token(new Word(t, false, " "), t, false));
    }
    return tokens;
  }

  /**
   * This method checks if in {@link #input} there is an incorrect punctuation.
   * @return {@link #input} cleaned from punctuation errors
   */

  public String checkPunct() {
    for (int i = 0; i < iu.length(); i++) {
      if (!alphaNum.contains(("" + iu.charAt(i)).toLowerCase())) {
        switch (iu.charAt(i) + "") {
          case "." :
            iu = checkPoint(i);
            break;
          case "'":
            iu = checkApost(i + 1);
            break;
          case "@":
            iu = checkNet(i);
            break;
          case " ":
            continue;
          default:
            iu.delete(i, i + 1);
            iu.insert(i, " ");
        }
      }
    }
    return iu.toString();
  }

  /**
   * Checks if before and/or after a point there are some numeric value and in the case it's false,
   * the point will be deleted.
   * @param startIndex the point where is the point
   * @return sb modified and cleaned by typing errors
   */

  private StringBuilder checkPoint(int startIndex) {
    boolean delete = false;
    if (startIndex < 1) {
      if (!Character.isDigit(iu.charAt(startIndex + 1))) {
        delete = true;
        return delete(startIndex);
      }
    } else {
      if ((!(Character.isDigit(iu.charAt(startIndex - 1))
          || Character.isDigit(iu.charAt(startIndex + 1))) && iu.indexOf("@") == -1)
          || startIndex == iu.length() - 1) {
        delete = true;
        return delete(startIndex);
      }
    }
    if (!delete) {
      if (!iu.substring(0, startIndex - 1).contains(".")) {
        setType(checkType());
      } else {
        delete(startIndex);
      }
    }
    return iu;
  }

  private StringBuilder delete(int index) {
    iu.deleteCharAt(index);
    iu.insert(index, " ");
    return iu;
  }

  /**
   * MANCA 's
   * Checks if the apostrophe in the {@link #input} is a typing error
   * or an abbreviation for a particular verb (ex: 's -> is/has, 'm -> am, 'll -> will, etc...).
   * @param startIndex index after the apostrophe
   * @return sb modified and cleaned by typing errors
   */

  private StringBuilder checkApost(int startIndex) {
    boolean substitute = false;
    if (iu.charAt(startIndex) == 'm') {
      iu.replace(startIndex - 1, startIndex + 1, " am");
      substitute = true;
    }
    if (("" + iu.charAt(startIndex) + iu.charAt(startIndex + 1)).equals("re")) {
      iu.replace(startIndex - 1, startIndex + 2, " are");
      substitute = true;
    }
    if (("" + iu.charAt(startIndex) + iu.charAt(startIndex + 1)).equals("ll")) {
      iu.replace(startIndex - 1, startIndex + 2 , " will");
      substitute = true;
    }
    if (("" + iu.charAt(startIndex) + iu.charAt(startIndex + 1)).equals("ve")) {
      iu.replace(startIndex - 1, startIndex + 2, " have");
      substitute = true;
    }

    if (!substitute) {
      delete(startIndex - 1);
    }
    return iu;
  }

  /**
   * Check if the net found is a typing error or belongs to an email address.
   * @param startIndex index where is the net
   * @return sb modified and cleanded by typing errors
   */

  private StringBuilder checkNet(int startIndex) {

    return iu;
  }

  /**
   * Get {@link #inputUser} as string.
   * @return {@link #inputUser}
   */
  public String getInputUser() {
    return inputUser.toString();
  }

  /**
   * Get {@link #iu} as string.
   * @return {@link #iu}
   */
  public String getModifiedInputUser() {
    return iu.toString();
  }

  /**
   * Get the type.
   * @return {@link #type}
   */

  public SentenceT getType() {
    return type;
  }

  /**
   * Set {@link #type} to a new value provided in input.
   * @param t type of new value
   */

  public void setType(SentenceT t) {
    this.type = t;
  }

  /**
   * Get {@link #tokens}.
   * @return  {@link #tokens}
   */
  public List<IToken> getTokens() {
    return tokens;
  }

  /**
   * Add a new element to {@link #tokens}.
   * @param t token to be add
   * @see #tokens
   */
  public void addToken(Token t) {
    tokens.add(t);
  }
}
