package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
   * Typology of Sentence.
   * @see SentenceT
   */
  private SentenceT type;

  /**
   * Generated tokens' list.
   */
  private List<IToken> tokens;

  /**
   * First form of {@link #tokens}: a list of single word cleaned from any punctuation error
   * or any blank space error.
   */
  private List<String> stringToken;

  /**
   * Constructor.
   * @param input is a string provided directly from the user
   */
  public Tokenizer(String input) {
    inputUser = new StringBuilder(input);
    type = null;
    tokens = new ArrayList<IToken>();
    stringToken = new ArrayList<String>();
  }

  /**
   * Tokenizes the string provided in input by the user.
   * @return a new Sentence
   */
  public Sentence getSentence() throws TokenNotFound {
    checkSentence();
    stringToken = Arrays.asList(checkPunct().toString().split(" "));
    createListOfTokens();
    return new Sentence(getType(), getInputUser(), getTokens());
  }

  /**
   * Detected in the {@link #inputUser} if there is more than one sentence, and if it contains more
   * than one sentence, {@link #type} has a special value.
   * Also checks if the {@link #inputUser} string is a question or an affirmation/answer.
   * @return {@link #type}
   */
  public SentenceT checkSentence() {
    for (int i = 0; i < inputUser.length(); i++) {
      if (isSpecial(inputUser.charAt(i))) {
        if (i < inputUser.length() - 2) {
          switch ("" + inputUser.charAt(i)) {
            case "?":
              setType(SentenceT.MORE_SENTENCE);
              return getType();
            case "!":
              setType(SentenceT.MORE_SENTENCE);
              return getType();
            case ".":
              setType(verifyPunct(i));
              break;
            default:
              continue;
          }
        } else {
          setType(checkType());
          break;
        }
      }
    }
    return getType();
  }

  /**
   * Verify if the punctuation in the phrase is an error (if true, the {@link #type}'ll be setted
   * to {@link SentenceT#MORE_SENTENCE}, or if a symbol used for something else
   * (and in thise case {@link #type} will be setted to {@link SentenceT#ANSWER}.
   * @param index of start
   * @return {@link #type}
   */
  private SentenceT verifyPunct(int index) {
    char before = inputUser.charAt(index - 1);
    char next = inputUser.charAt(index - 1);
    if ((isBlank(before) && isBlank(next))
        || (isSpecial(before) || isSpecial(next))) {
      setType(SentenceT.MORE_SENTENCE);
    } else {
      setType(SentenceT.ANSWER);
    }
    return getType();
  }

  /**
   * Check if the last character of {@link #inputUser} represents
   * an {@link SentenceT#ANSWER} or a {@link SentenceT#QUESTION}.
   * @return {@link #type}
   */
  private SentenceT checkType() {
    if (getType().equals(SentenceT.MORE_SENTENCE)) {
      return getType();
    } else {
      return inputUser.charAt(inputUser.length() - 1) == '?'
        ? SentenceT.QUESTION : SentenceT.ANSWER;
    }
  }

  /**
   * Check for every syntax errors in {@link #inputUser} and correct them.
   * @return an instance of StringBuilder that represents {@link #inputUser} cleared from errors
   */
  public StringBuilder checkPunct() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < inputUser.length(); i++) {
      char elem = inputUser.charAt(i);
      //simple letter
      if (isAlpha(elem)) {
        sb.append(elem);
        //eveutual numeric sequence (first case)
      } else if (isNumber(elem)) {
        List<String> ls = checkNumbers(i);
        sb.append(" ").append(ls.get(0)).append(" ");
        i = Integer.parseInt(ls.get(1));

      } else if (isBlank(elem)) {
        if (!isBlank(sb.charAt(sb.length() - 1))) {
          sb.append(elem);
        }

      } else if (isSpecial(elem)) {
        char last = sb.charAt(sb.length() - 1);
        //acronyms or names separated by a dash or numeric sequence (second case)
        if (elem == '.' || elem == '-') {
          if (isAlpha(last)) {
            List<String> ls = checkAcronyms(i);
            sb.append(ls.get(0));
            i = Integer.parseInt(ls.get(1));
          } else if (isNumber(last)) {
            List<String> ls = checkNumbers(i);
            sb.append(" ").append(ls.get(0)).append(" ");
            i = Integer.parseInt(ls.get(1));
          }
          //verbal apostrophe
        } else if (("" + elem).equals("'")) {
          sb.append(checkApost(i + 1));
          i += 2;
          //email
        } else if (elem == '@') {
          List<String> ls = checkNet(i);
          sb.replace(Integer.parseInt(ls.get(1)), Integer.parseInt(ls.get(2)), ls.get(0));
          i = Integer.parseInt(ls.get(2));
          //URLs or mistakes
        } else if (elem == ':') {
          if (inputUser.substring(i - 4, i).equals("http")) {
            List<String> ls = checkUrl(i - 4);
            sb.replace(i - 4, i, ls.get(0));
            i = Integer.parseInt(ls.get(1));
          } else {
            if (!isBlank(sb.charAt(sb.length() - 1))) {
              sb.append(" ");
            }
          }
          //all insignificant symbols
        } else {
          if (!isBlank(sb.charAt(sb.length() - 1))) {
            sb.append(" ");
          }
        }
      }
    }
    return sb;
  }

  /**
   * Check any acronyms in {@link #inputUser}.
   * @param index where to start looking for
   * @return a list of two elements: in the first position the value of acronyms
   *        and in second position the value of the current index
   */
  private List<String> checkAcronyms(int index) {
    List<String> ls = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int ind = index;
    while (!isBlank(inputUser.charAt(ind))) {
      ind++;
    }
    if (!inputUser.substring(index, ind + 1).contains("@")) {
      while (isAlpha(inputUser.charAt(index))
          || (inputUser.charAt(index) == '.' && inputUser.charAt(index - 1) != '.')
          || (inputUser.charAt(index) == '-'
             && (!isSpecial(inputUser.charAt(index - 1))
                 && !isBlank(inputUser.charAt(index - 1))))) {
        sb.append(inputUser.charAt(index));
        index++;
      }
    } else {
      checkNet(index);
    }
    ls.add(sb.toString());
    ls.add("" + index);
    return ls;
  }

  /**
   * Check any numeric sequences (IP address, arithmetic values, etc) in {@link #inputUser}.
   * @param index where to start looking for
   * @return a list of two elements: in the first position the value of the numeric sequence
   *        and in second position the value of the current index
   */
  private List<String> checkNumbers(int index) {
    List<String> ls = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    while (isNumber(inputUser.charAt(index))
        || (inputUser.charAt(index) == '.' && inputUser.charAt(index - 1) != '.')) {
      sb.append(inputUser.charAt(index));
      index++;
    }
    ls.add(sb.toString());
    ls.add("" + index);
    return ls;
  }

  /**
   * Convert every apostrophe in its real semantic value.
   * @param index where to start looking for
   * @return string with the extended verbal form
   */
  private String checkApost(int index) {
    StringBuilder sb = new StringBuilder();
    char verb = inputUser.charAt(index);
    char verb2 = inputUser.charAt(index + 1);
    if (verb == 'm') {
      sb.append(" am ");
    } else if (verb == 'r' && verb2 == 'e') {
      sb.append(" are");
    } else if (verb == 'v' && verb2 == 'e') {
      sb.append(" have");
    } else if (verb == 'l' && verb2 == 'l') {
      sb.append(" will");
    } else if (verb == 's') {
      int end = index + 2;
      while (isAlpha(inputUser.charAt(end))) {
        end++;
      }
      String sub = inputUser.substring(index + 2, end++);
      if (sub.substring(sub.length() - 2, sub.length()).equals("ed")) {
        sb.append(" has ");
      } else {
        sb.append(" is ");
      }
    }
    return sb.toString();
  }

  /**
   * Check any email address in {@link #inputUser}.
   * @param index where to start looking for
   * @return a list of two elements: in the first position the email address,
   *         in second position the index the start of email
   *         and in third position the and of the email
   */
  private List<String> checkNet(int index) {
    int start = index - 1;
    int end = index;
    List<String> ls = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    while (!isBlank(inputUser.charAt(start))) {
      start--;
    }

    while (!isBlank(inputUser.charAt(end))) {
      end++;
    }
    ls.add(sb.append(inputUser.substring(start, end)).toString());
    ls.add("" + (start));
    ls.add("" + end);
    return ls;
  }

  /**
   * Check any URL address in {@link #inputUser}.
   * @param index where to start looking for
   * @return a list of two elements: in the first position the URL
   *        and in second position the value of the current index
   */
  private List<String> checkUrl(int index) {
    List<String> ls = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    while (!isBlank(inputUser.charAt(index++))) {
      sb.append(inputUser.charAt(index));
    }
    ls.add(sb.toString());
    ls.add("" + index);
    return ls;
  }

  /**
   * Create final tokens' list.
   * @return {@link #tokens}
   */
  public List<IToken> createListOfTokens() throws TokenNotFound {
    StringBuilder sb = new StringBuilder();
    stringToken.stream().filter(s -> !s.equals(" ")).forEach(s -> sb.append(s + " "));
    addAllTokens(createToken(sb.toString(), new ArrayList<Token>(), 0, 4));
    return getTokens();
  }

  /**
   * This method creates the final tokens' list.
   * @param sentence the string representation of {@link #stringToken}
   * @param t a provisional new array of token, useful for recursion
   * @param index setting out where to start watching
   * @param indexForTok has a maximum value of four and is used for the recursion
   * @return the list with all tokens of the {@link #inputUser} after that
   *        it'll be cleaned and analyzed
   * @throws TokenNotFound it was launch because a word passed in input by the user
   *        is bad written
   */
  private List<Token> createToken(String sentence, ArrayList<Token> t, int index, int indexForTok)
      throws TokenNotFound {
    // base case 1:IndexOutOfBounds
    if (index <= sentence.length() - 1) {
      // base case 2: last element of the string
      if (index == (sentence.length() - 1)) {
        t.add(pincopallo(sentence.split(" ")[index]));
        return t;
        // base case 3: remain only the first element of quartet to analyze
      } else if (indexForTok == 1) {
        Token tk = pincopallo(sentence.split(" ")[index]);
        if (!tk.isKnown()) {
          throw new TokenNotFound();
        }
        t.add(tk);
        return index < sentence.length() ? createToken(sentence, t, index + 1, 4) : t;

        // recursive step
      } else {
        String st = sentence.substring(index, index + indexForTok);
        Token tk = pincopallo(st);
        if (tk.isKnown()) {
          t.add(tk);
          return t;
        } else {
          return indexForTok == 1
              ?  createToken(sentence, t, index + 1, 4)
                  : createToken(sentence, t, index, indexForTok - 1);
        }
      }
    }
    return t;
  }

  private Token pincopallo(String s) {
    System.out.println(s);
    return null;
  }

  /**
   * Verify if the input is a letter of alphabet.
   * @param c character to check
   * @return a boolean
   */
  private boolean isAlpha(char c) {
    return Character.isLetter(c);
  }

  /**
   * Verify if the input is a number of alphabet.
   * @param c character to check
   * @return a boolean
   */
  private boolean isNumber(char c) {
    return Character.isDigit(c);
  }

  /**
   * Verify if the input is a blanck space.
   * @param c character to check
   * @return a boolean
   */
  private boolean isBlank(char c) {
    return c == ' ';
  }

  /**
   * Verify if the input is a special value.
   * @param c character to check
   * @return a boolean
   */
  private boolean isSpecial(char c) {
    return !(isNumber(c)) && !(isAlpha(c)) && !(isBlank(c));
  }

  /**
   * Get {@link #inputUser} as string.
   * @return {@link #inputUser}
   */
  public String getInputUser() {
    return inputUser.toString();
  }

  /**
   * Get {@link #stringToken}.
   * @return {@link #stringToken}
   */
  public List<String> getStringToken() {
    return stringToken;
  }

  /**
   * Add a new element to {@link #stringToken}.
   * @param stringToken element to add to {@link #stringToken}
   */
  public void addStringToken(String stringToken) {
    this.stringToken.add(stringToken);
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
   */
  public void addToken(Token t) {
    tokens.add(t);
  }

  /**
   * Add a new collection of tokens to {@link #tokens}.
   * @param toks collection of token to be add all
   * @return true if this list changed as a result of the call, false otherwise
   */
  public boolean addAllTokens(Collection<? extends IToken> toks) {
    return tokens.addAll(toks);
  }
}