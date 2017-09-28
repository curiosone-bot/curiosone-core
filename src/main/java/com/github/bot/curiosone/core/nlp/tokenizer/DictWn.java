package com.github.bot.curiosone.core.nlp.tokenizer;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;

import java.net.URL;
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
 * Dictionary for tokenizer using WordNet DB.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see https://wordnet.princeton.edu/
 */

class DictWn {

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
  protected static DictWn getInstance() {
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
   * Method to create Token Structure that contains Dictionary info.
   * @param item String to search in WordNet
   * @return Token Structure that contains Dictionary info
   * @see com.github.bot.curiosone.core.nlp.tokenizer.Token
   */
  protected static Token getToken(String item) {
    if (item.length() == 0 || item.equals(" ")) {
      return null;
    }
    Token token = new Token(item);
    token = getTokenNotWn(token, item); // token outside WordNet
    token = getTokenWn(token, item);    // token inside WordNet
    return token;
  }

  /**
   * Pronouns outside WN.
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

    private String[] items;

    private PronounsOutWn(String...items) {
      this.items = items;
    }

    private String[] getItems() {
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

    private String[] getItems() {
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

    private String[] getItems() {
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

    private String[] getItems() {
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

    private String[] getItems() {
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
   * Verify Mail String.
   * @see http://howtodoinjava.com/regex/java-regex-validate-email-address/
   * @see http://www.rfc-editor.org/rfc/rfc5322.txt
   */

  private static boolean isValidEmailAddress(String email) {
    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]"
        + "+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    return Pattern.compile(regex).matcher(email).matches();
  }

  /**
   * Verify Numeric String.
   */
  private static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  /**
   * Get Token outside of WordNet Database.
   *
   */

  private static Token getTokenNotWn(Token token, String item) {

    /**
     * Check Numeric.
     */
    if (isNumeric(item)) {
      token.setKnown(true);
      com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord
              retWord = new Word();
      retWord.setLemma(item);
      retWord.setPos(PosType.NUMB);
      retWord.setLexType(LexType.QUANTITY);
      retWord.setGloss("Numeric outside WordNet");
      token.addWord(retWord);
      return token;
    }
    /**
     * Check mail address.
     */
    if (isValidEmailAddress(item)) {
      token.setKnown(true);
      com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord
              retWord = new Word();
      retWord.setLemma(item);
      retWord.setPos(PosType.N);
      retWord.setLexType(LexType.MAIL);
      retWord.setGloss("Mail address outside WordNet");
      token.addWord(retWord);
      return token;
    }
    /**
     * Check Pronouns.
     */
    for (PronounsOutWn n: PronounsOutWn.values()) {
      if (!contains(n.getItems(),item)) {
        continue;
      }

      token.setKnown(true);
      com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord
              retWord = new Word();
      retWord.setLemma(item);
      retWord.setPos(PosType.PRON);
      retWord.setLexType(LexType.valueOf(n.toString()));
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
      retWord.setPos(PosType.DET);
      retWord.setLexType(LexType.valueOf(n.toString()));
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
      retWord.setPos(PosType.CONJ);
      retWord.setLexType(LexType.valueOf(n.toString()));
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
      retWord.setPos(PosType.INTERJ);
      retWord.setLexType(LexType.valueOf(n.toString()));
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
      retWord.setPos(PosType.ADV);
      retWord.setLexType(LexType.valueOf(n.toString()));
      retWord.setGloss("Adverbs outside WordNet");
      token.addWord(retWord);
      return token;
    }
    return token;
  }

  /**
   * Get Token from WordNet Database.
   * List of Word descending ordered based on frequency occurrence (getTagCount()).
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
                retWord.setPos(PosType.N);
                break;
              case VERB:
                retWord.setPos(PosType.V);
                break;
              case ADJECTIVE:
                retWord.setPos(PosType.ADJ);
                break;
              case ADVERB:
                retWord.setPos(PosType.ADV);
                break;
              default: retWord.setPos(PosType.UNKN);
            };
            retWord.setLexType(LexType.valueOf(
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

            /**
             * Get semantic relations from synset.
            */

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

            /**
             * Get lexical relations from word.
             */

            for (Pointer pt: Pointer.values()) {
              for (IWordID wid: word.getRelatedWords(pt)) {
                retWord.addRelation(pt.toString(), dictionary.getWord(wid).getLemma());
              }
            }

            //add retWord
            retWords.add(retWord);

          } // end for IWordID
        } // end if indexWOrd is null
      } // end for lemma
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

    return token;
  } // end getToken

  /**
   * For test only.
   * @param args input args

  public static void main(String[] args) {

    //System.out.println(DictWn.getToken("arivitto@gmail.com"));
    //System.out.println("\n" + DictWn.getToken("rivitto.662503@studenti.uniroma1.it"));
    //System.out.println("\n" + DictWn.getToken("13410"));
    //System.out.println("\n" + DictWn.getToken("12.34"));
    //System.out.println("\n" + DictWn.getToken("dog"));
    //System.out.println("\n" + DictWn.getToken("crawler"));
    //System.out.println("\n" + DictWn.getToken("dog"));
    //System.out.println("\n" + DictWn.getToken("come back"));
    //System.out.println(Pointer.values()); //List possible Pointer values
    System.out.println("\n" + DictWn.getToken("cats"));
  }
  */
}
