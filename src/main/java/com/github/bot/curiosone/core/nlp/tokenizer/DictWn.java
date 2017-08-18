package com.github.bot.curiosone.core.nlp.tokenizer;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.WordnetStemmer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Dictionary for tokenizer using WordNet DB.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see https://wordnet.princeton.edu/
 */

public class DictWn {

  /**
   * Singleton instance on class loading is thread-safe.
   */
  private static DictWn instance = null;

  /**
   * Path of Wordnet database files.
   */
  private static final String WND_PATH = "src/main/res/dict";

  /**
   * Private dictionary.
   */
  private static IDictionary dictionary;

  /**
   * Private constructor.
   */
  private DictWn() {
  }

  /**
   * Get DictWn instance.
   * @return a new {@link #instance}
   */
  public static DictWn getInstance() {
    if  (instance != null) {
      return instance;
    }
    instance = new DictWn();
    try {
      dictionary = new edu.mit.jwi.Dictionary(new URL("file", null, WND_PATH));
      dictionary.open();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return instance;
  }

  /**
   *
   * @param item String to search in WordNet
   * @return Token Structure that contains Dictionary info
   * @see com.github.bot.curiosone.core.nlp.tokenizer.Token
   */
  public static Token getToken(String item) {
    if (item.length() == 0 || item.equals(" ")) {
      return null;
    }
    Token token = new Token(item);
    token = getTokenNotWn(token, item);
    token = getTokenWn(token, item);
    return token;
  }

  /**
   * Nouns outside WN.
   */
  private enum NounsOutWn {

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

    private String[] items;

    private NounsOutWn(String...items) {
      this.items = items;
    }

    public String[] getItems() {
      return items;
    }
  }

  /**
   * Determiners outside WN.
   */
  private enum DeterminersOutWn {

    INDEFINITE_ARTICLE("a", "an"),
    DEFINITE_ARTICLE("the");

    private String[] items;

    private DeterminersOutWn(String...items) {
      this.items = items;
    }

    public String[] getItems() {
      return items;
    }
  }

  /**
   * Conjunctions outside WN.
   */
  private enum ConjunctionsOutWn {

    COORDINATOR("and", "or", "but"),
    SUBORDINATOR("while", "because", "before", "since", "till", "unless", "whereas", "wheter");

    private String[] items;

    private ConjunctionsOutWn(String...items) {
      this.items = items;
    }

    public String[] getItems() {
      return items;
    }
  }

  /**
   * Adverbs outside WN.
   */
  private enum AdverbsOutWn {

    INTERROGATIVE("how");

    private String[] items;

    private AdverbsOutWn(String...items) {
      this.items = items;
    }

    public String[] getItems() {
      return items;
    }
  }

  /**
   * Interjections outside WN.
   */
  private enum InterjectionsOutWn {

    GENERIC("ah", "eh", "hmm", "phew", "tsk", "uhm"),
    REGARDS("bye", "goodbye", "hello", "farewell", "hi"),
    APOLOGIZE("so long excuse me", "sorry", "pardon", "i am sorry", "i'm sorry"),
    GRATITUDE("thanks", "thank you", "thanks a lot"),
    DISGUST("yuk"),
    SURPRISE("oh"),
    PAIN("ouch", "ohi");

    private String[] items;

    private InterjectionsOutWn(String...items) {
      this.items = items;
    }

    public String[] getItems() {
      return items;
    }
  }

  /**
   * Verify if an array of String contains a String.
   *
   */
  private static <T> boolean contains(T[] array, T searchValue) {
    return Arrays.stream(array).anyMatch(searchValue::equals);
  }

  /**
   * Get Token outside of WordNet Database.
   *
   */
  private static Token getTokenNotWn(Token token, String item) {
    /**
     * Check Nouns.
     */
    for (NounsOutWn n: NounsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord
              retWord = new Word();
      retWord.setLemma(item);
      retWord.setPos(PosT.PRON);
      retWord.setLexType(LexT.valueOf(n.toString()));
      retWord.setGloss("Pronoun outside WordNet");
      token.addWord(retWord);
      return token;
    }
    /**
     * Check Determiners.
     */
    for (DeterminersOutWn n: DeterminersOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord
          retWord = new Word();
      retWord.setLemma(item);
      retWord.setPos(PosT.DET);
      retWord.setLexType(LexT.valueOf(n.toString()));
      retWord.setGloss("Determiners outside WordNet");
      token.addWord(retWord);
      return token;
    }
    /**
     * Check Conjunctions.
     */
    for (ConjunctionsOutWn n: ConjunctionsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord
          retWord = new Word();
      retWord.setLemma(item);
      retWord.setPos(PosT.CONJ);
      retWord.setLexType(LexT.valueOf(n.toString()));
      retWord.setGloss("Conjunctions outside WordNet");
      token.addWord(retWord);
      return token;
    }
    /**
     * Check Interjections.
     */
    for (InterjectionsOutWn n: InterjectionsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord
          retWord = new Word();
      retWord.setLemma(item);
      retWord.setPos(PosT.INTERJ);
      retWord.setLexType(LexT.valueOf(n.toString()));
      retWord.setGloss("Interjections outside WordNet");
      token.addWord(retWord);
      return token;
    }
    /**
     * Check Adverbs.
     */
    for (AdverbsOutWn n: AdverbsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord
          retWord = new Word();
      retWord.setLemma(item);
      retWord.setPos(PosT.ADV);
      retWord.setLexType(LexT.valueOf(n.toString()));
      retWord.setGloss("Adverbs outside WordNet");
      token.addWord(retWord);
      return token;
    }
    return token;
  }

  /**
   * Get Token from WordNet Database.
   * List of Word were ordered descending based on frequency occurrence (getTagCount()).
   * @See https://stackoverflow.com/questions/21264158/how-to-access-frequency-count-in-wordnet-in-any-java-wordnet-interface
   */
  private static Token getTokenWn(Token token, String item) {

    if (dictionary == null) {
      getInstance();
    }

    Set<com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord>
        retWords = new HashSet<com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord>();

    for (POS p : POS.values()) {
      List<String> stems = new WordnetStemmer(dictionary).findStems(item, p);

      for (String lemma : stems) {
        IIndexWord indexWord = dictionary.getIndexWord(lemma, p);
        if (indexWord != null) {
          List<IWordID> wordIDs = indexWord.getWordIDs();
          for (IWordID id : wordIDs) {
            com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord retWord = new Word();
            IWord word  = dictionary.getWord(id);

            retWord.setLemma(lemma);
            switch (p) {
              case NOUN:
                retWord.setPos(PosT.N);
                break;
              case VERB:
                retWord.setPos(PosT.V);
                break;
              case ADJECTIVE:
                retWord.setPos(PosT.ADJ);
                break;
              case ADVERB:
                retWord.setPos(PosT.ADV);
                break;
              default: retWord.setPos(PosT.UNKN);
            };
            retWord.setLexType(LexT.valueOf(
                word.getSynset()
                            .getLexicalFile()
                            .getName()
                            .split("\\.")[1]
                            .toUpperCase()
                            ));
            retWord.setGloss(word.getSynset().getGloss());
            retWord.setWordId(id);
            retWord.setNum(dictionary
                .getSenseEntry(word.getSenseKey())
                .getTagCount());

            retWords.add(retWord);

          } // end for IWordID
        } // end if indexWOrd is null
        // end for lemma
      } // end for POS

      Comparator<com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord> cmp =
          Comparator.comparing(
              com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord::getNum).reversed();
      List<com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord> retOrderedWords =
              new ArrayList<com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord>();
      retOrderedWords.addAll(retWords);
      Collections.sort(retOrderedWords, cmp);
      if (retOrderedWords.size() > 0) {
        token.setKnown(true);
        token.addAllWords(retOrderedWords);
      }
    }
    return token;
    // end getToken
  }

  /**
   * Test DictWn.
   * @param args Argument list
   */
  public static void main(String[] args) {

    String item = "";
    //item = "next door";
    //item = "Mark Twain";
    //item = "fast food";
    //item = "Calamity Jane";
    //item = "running";
    //item = "speed up";
    //item = "feet";
    //item = "pull down";
    //item = "and";
    //item = "break down";
    //item = "ouch";
    item = "house";
    //item = "yourghj";
    System.out.println(getToken(item));
  }
}