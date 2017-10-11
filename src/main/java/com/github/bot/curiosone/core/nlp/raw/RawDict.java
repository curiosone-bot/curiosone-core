package com.github.bot.curiosone.core.nlp.raw;

import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.POS;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Interfaces with the WordNet database.
 * @see  <a href="https://wordnet.princeton.edu/">WordNet Homepage</a>
 */
public class RawDict {

  /**
   * The Singleton instance of this Class.
   */
  private static RawDict instance = null;

  /**
   * Path to the WordNet database files.
   */
  private static final String wdnPath = "/dict";

  /**
   * Dictionary.
   */
  private Dictionary dictionary;

  /**
   * Private constructor.
   */
  private RawDict() {
    try {
      Path path = null;
      try {
        URL resource = RawDict.class.getResource(wdnPath);
        path = Paths.get(resource.toURI());
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
      dictionary = new Dictionary(new URL("file", null, path.toString()));
      dictionary.open();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the Singleton instance.
   * @return  the Singleton instance
   */
  public static RawDict getInstance() {
    if  (instance != null) {
      return instance;
    }
    instance = new RawDict();
    return instance;
  }

  /**
   * Creates a RawToken Structure that contains dict info.
   * @param  item
   *         String to be searched in WordNet
   * @return  RawToken Structure that contains RawDict info
   * @see  com.github.bot.curiosone.core.nlp.raw.RawToken
   */
  public RawToken getRawToken(String item) {
    if (item.length() == 0 || item.equals(" ")) {
      return null;
    }
    RawToken token = new RawToken(item);
    token = getRawTokenNotWn(token, item);
    token = getRawTokenWn(token, item);
    return token;
  }

  /**
   * Stores pronouns outside WN.
   */
  private enum PronounsOutWn {

      PERSONAL_SUBJECTIVE("i", "you", "he", "she", "it", "we", "you", "they"),
      PERSONAL_OBJECTIVE("me", "you", "him", "her", "it", "us", "you", "them"),
      POSSESSIVE("mine", "yours", "his", "hers", "ours", "theirs"),
      REFLEXIVE("myself", "yourself", "himself", "herself", "itself", "oneself",
          "ourselves", "yourselves", "themselves"),
      RECIPROCAL("each", "other", "one", "another"),
      RELATIVE("that", "which", "who", "whose", "whom", "where", "when"),
      DEMONSTRATIVE("this", "that", "these", "those"),
      INTERROGATIVE("who", "what", "why", "where", "when", "whatever"),
      INDEFINITE("anything", "anybody", "anyone", "something", "somebody",
              "someone", "nothing", "nobody", "none", "no one");

    /**
     * Stores some additional pronouns.
     */
    private String[] items;

    /**
     * Private constructor.
     * @param  items
     *         One or more additional pronouns
     */
    private PronounsOutWn(String...items) {
      this.items = items;
    }

    /**
     * Gets the additional pronouns.
     * @return  an array containing all the additional pronouns
     */
    public String[] getItems() {
      return items;
    }
  }

  /**
   * Stores determiners outside WN.
   */
  private enum DeterminersOutWn {

    INDEFINITE_ARTICLE("a", "an"),
    DEFINITE_ARTICLE("the");

    /**
     * Stores some additional pronouns.
     */
    private String[] items;

    /**
     * Private constructor.
     * @param  items
     *         One or more additional determiners
     */
    private DeterminersOutWn(String...items) {
      this.items = items;
    }

    /**
     * Gets the additional determiners.
     * @return  an array containing all the additional determiners
     */
    public String[] getItems() {
      return items;
    }
  }

  /**
   * Stores conjunctions outside WN.
   */
  private enum ConjunctionsOutWn {

    COORDINATOR("and", "or", "but"),
    SUBORDINATOR("while", "because", "before", "since", "till", "unless", "whereas", "wheter");

    /**
     * Stores some additional conjunctions.
     */
    private String[] items;

    /**
     * Private constructor.
     * @param  items
     *         One or more additional conjunctions
     */
    private ConjunctionsOutWn(String...items) {
      this.items = items;
    }

    /**
     * Gets the additional conjunctions.
     * @return  an array containing all the additional conjunctions
     */
    public String[] getItems() {
      return items;
    }
  }

  /**
   * Stores adverbs outside WN.
   */
  private enum AdverbsOutWn {

    INTERROGATIVE("how");

    /**
     * Stores some additional adverbs.
     */
    private String[] items;

    /**
     * Private constructor.
     * @param  items
     *         One or more additional adverbs
     */
    private AdverbsOutWn(String...items) {
      this.items = items;
    }

    /**
     * Gets the additional adverbs.
     * @return  an array containing all the additional adverbs
     */
    public String[] getItems() {
      return items;
    }
  }

  /**
   * Stores interjections outside WN.
   */
  private enum InterjectionsOutWn {

    GENERIC("ah", "eh", "hmm", "phew", "tsk", "uhm"),
    REGARDS("bye", "goodbye", "hello", "farewell", "hi"),
    APOLOGIZE("so long excuse me", "sorry", "pardon", "i am sorry", "i'm sorry"),
    GRATITUDE("thanks", "thank you", "thanks a lot"),
    DISGUST("yuk"),
    SURPRISE("oh"),
    PAIN("ouch", "ohi");

    /**
     * Stores some additional interjections.
     */
    private String[] items;

    /**
     * Private constructor.
     * @param  items
     *         One or more additional interjections
     */
    private InterjectionsOutWn(String...items) {
      this.items = items;
    }

    /**
     * Gets the additional interjections.
     * @return  an array containing all the additional interjections
     */
    public String[] getItems() {
      return items;
    }
  }

  /**
   * Gets a Token outside of WordNet Database.
   * @param  token
   *         the external Token
   *
   * @param  item
   *         the value of the given Token
   * @return  the original Token
   */
  private RawToken getRawTokenNotWn(RawToken token, String item) {

    // Check Numeric.
    if (isNumeric(item)) {
      token.setKnown(true);
      RawWord retWord = new RawWord();
      retWord.setLemma(item);
      retWord.setPos(POS.NUMB);
      retWord.setLexType(LEX.QUANTITY);
      retWord.setGloss("Numeric outside WordNet");
      token.addWord(retWord);
      return token;
    }

    // Check mail address.
    if (isValidEmailAddress(item)) {
      token.setKnown(true);
      RawWord retWord = new RawWord();
      retWord.setLemma(item);
      retWord.setPos(POS.N);
      retWord.setLexType(LEX.MAIL);
      retWord.setGloss("Mail address outside WordNet");
      token.addWord(retWord);
      return token;
    }

    // Check Pronouns.
    for (PronounsOutWn n: PronounsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      RawWord retWord = new RawWord();
      retWord.setLemma(item);
      retWord.setPos(POS.PRON);
      retWord.setLexType(LEX.valueOf(n.toString()));
      retWord.setGloss("Pronoun outside WordNet");
      token.addWord(retWord);
      return token;
    }

    // Check Determiners.
    for (DeterminersOutWn n: DeterminersOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      RawWord retWord = new RawWord();
      retWord.setLemma(item);
      retWord.setPos(POS.DET);
      retWord.setLexType(LEX.valueOf(n.toString()));
      retWord.setGloss("Determiners outside WordNet");
      token.addWord(retWord);
      return token;
    }

    // Check Conjunctions.
    for (ConjunctionsOutWn n: ConjunctionsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      RawWord retWord = new RawWord();
      retWord.setLemma(item);
      retWord.setPos(POS.CONJ);
      retWord.setLexType(LEX.valueOf(n.toString()));
      retWord.setGloss("Conjunctions outside WordNet");
      token.addWord(retWord);
      return token;
    }

    // Check Interjections.
    for (InterjectionsOutWn n: InterjectionsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      RawWord retWord = new RawWord();
      retWord.setLemma(item);
      retWord.setPos(POS.INTERJ);
      retWord.setLexType(LEX.valueOf(n.toString()));
      retWord.setGloss("Interjections outside WordNet");
      token.addWord(retWord);
      return token;
    }

    // Check Adverbs.
    for (AdverbsOutWn n: AdverbsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      RawWord retWord = new RawWord();
      retWord.setLemma(item);
      retWord.setPos(POS.ADV);
      retWord.setLexType(LEX.valueOf(n.toString()));
      retWord.setGloss("Adverbs outside WordNet");
      token.addWord(retWord);
      return token;
    }
    return token;
  }

  /**
   * Gets the given Token from the WordNet Database.
   * In case of ambiguity, the Token with higher frequency is returned.
   * @param  token
   *         The desired Token
   * @param  item
   *         String content of the desired Token
   * @return  the desired Token, taken from the WordNet database
   * @see  <a href="https://goo.gl/mCeRcp">How to get the Token with higher frequency</a>
   */
  private RawToken getRawTokenWn(RawToken token, String item) {
    Set<RawWord> retWords = new HashSet<RawWord>();

    for (edu.mit.jwi.item.POS p : edu.mit.jwi.item.POS.values()) {
      List<String> stems = new WordnetStemmer(dictionary).findStems(item, p);

      for (String lemma : stems) {
        IIndexWord indexWord = dictionary.getIndexWord(lemma, p);
        if (indexWord != null) {
          List<IWordID> wordIDs = indexWord.getWordIDs();
          for (IWordID id : wordIDs) {
            RawWord retWord = new RawWord();
            IWord word = dictionary.getWord(id);

            retWord.setLemma(lemma);
            switch (p) {
              case NOUN:
                retWord.setPos(POS.N);
                break;
              case VERB:
                retWord.setPos(POS.V);
                break;
              case ADJECTIVE:
                retWord.setPos(POS.ADJ);
                break;
              case ADVERB:
                retWord.setPos(POS.ADV);
                break;
              default: retWord.setPos(POS.UNKN);
            };
            retWord.setLexType(LEX.valueOf(
                word.getSynset()
                  .getLexicalFile()
                  .getName()
                  .split("\\.")[1]
                  .toUpperCase()
            ));
            retWord.setGloss(word.getSynset().getGloss());
            retWord.setWordId(id);
            retWord.setNum(
                dictionary.getSenseEntry(word.getSenseKey()).getTagCount()
            );

            // Get semantic relations from synset.
            ISynset synset = word.getSynset();

            for (Pointer pt: Pointer.values()) {
              List<ISynsetID> synList = synset.getRelatedSynsets(pt) ;
              List<IWord> words;
              for (ISynsetID sid: synList) {
                words = dictionary.getSynset(sid).getWords();
                for (Iterator<IWord> i = words.iterator(); i.hasNext();) {
                  retWord.addRelation(pt.toString(), i.next().getLemma());
                }
              }
            }

            // Get lexical relations from word.
            for (Pointer pt: Pointer.values()) {
              for (IWordID wid: word.getRelatedWords(pt)) {
                retWord.addRelation(pt.toString(), dictionary.getWord(wid).getLemma());
              }
            }

            // Add retWord
            retWords.add(retWord);
          }
        }
      }
    }

    Comparator<RawWord> cmp = Comparator.comparing(RawWord::getNum).reversed();
    List<RawWord> retOrderedWords = new ArrayList<>();
    retOrderedWords.addAll(retWords);
    Collections.sort(retOrderedWords, cmp);
    if (retOrderedWords.size() > 0) {
      token.setKnown(true);
      token.addAllWords(retOrderedWords);
    }

    return token;
  }

  /**
   * Checks whether a given array of a type contains the given object.
   * @param  <T>
   *         the type of the object to be searched and the objects stored in the array
   * @param  array
   *         the array to search in
   * @param  searchValue
   *         the value to be searched in the given array
   * @return  {@code true} if the given array contains the given object;
   *          {@code false} otherwise
   */
  private static <T> boolean contains(T[] array, T searchValue) {
    return Arrays.stream(array).anyMatch(searchValue::equals);
  }

  /**
   * Validates the given email addres.
   * @param  email
   *         the email address to be validated.
   * @return  {@code true} if the given email address is valid;
              {@code false} otherwise
   * @see  <a href="https://goo.gl/vRTEkF">The Regex used for the validation</a>
   * @see  <a href="http://www.rfc-editor.org/rfc/rfc5322.txt">Internet Message Format</a>
   */
  public static boolean isValidEmailAddress(String email) {
    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]"
        + "+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    return Pattern.compile(regex).matcher(email).matches();
  }

  /**
   * Checks whether the given String represents a numeric value or not.
   * Accepts both integers and floating point values.
   * @param  str
   *         the string to be checked.
   * @return  {@code true} if the given String is a numeric value;
              {@code false} otherwise.
   */
  private static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}
