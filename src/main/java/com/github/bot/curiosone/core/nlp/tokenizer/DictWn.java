package com.github.bot.curiosone.core.nlp.tokenizer;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.WordnetStemmer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
   * Get Token outside of WordNet Database.
   */
  private static Token getTokenNotWn(String item) {
    return null;
  }

  /**
   * Get Token from WordNet Database.
   * List of Word were ordered descending based on frequency occurrence (getTagCount()).
   * @See https://stackoverflow.com/questions/21264158/how-to-access-frequency-count-in-wordnet-in-any-java-wordnet-interface
   */
  private static Token getTokenWn(String item) {
    Token token = new Token(item);
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
      if (retOrderedWords.size() == 0) {
        token.setKnown(false);
      } else {
        token.setKnown(true);
        token.setWords(retOrderedWords);
      }
    }
    return token;
    // end getToken
  }
}