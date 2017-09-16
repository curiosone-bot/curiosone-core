package com.github.bot.curiosone.core.nlp.base;

import com.github.bot.curiosone.core.nlp.base.raw.RawDict;
import com.github.bot.curiosone.core.nlp.base.raw.RawToken;
import java.util.ArrayList;
import java.util.List;
// import com.github.bot.curiosone.core.nlp.base.raw.RawWord;

/**
 * Stores Token information and provides utility methods to access its contents,
 * create a new Token and tokenize a sentence.
 */
public class Token {

  /**
   * Original text.
   */
  String text;

  /**
   * Whether this Token is already known.
   */
  boolean known;

  /**
   * Is the numerical ID of the preferred word, when there is an ambiguity.
   */
  int preferred;

  /**
   * Is the list of words taken from the original text.
   */
  List<Word> words = new ArrayList<>();

  /**
   * Constructs a Token startinf from a text.
   * @param text the original text to start from.
   */
  public Token(String text) {
    this.text = text;
    preferred = 0;
    RawToken rt = RawDict.getInstance().getRawToken(text);
    known = rt.isKnown();
    rt.getWords().forEach(rw -> {
      Word word = new Word(rw.getLemma(), rw.getPos(), rw.getLexType());
      words.add(word);
    });
  }

  /**
   * Returns <code>true</code> if this Token is known;
   *         <code>false</code> otherwise.
   */
  public boolean isKnown() {
    return known;
  }

  /**
   * Returns the original text.
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the words of the text.
   */
  public ArrayList<Word> getWords() {
    return new ArrayList<Word>(words);
  }

  /**
   * Returns the lemma of the preferred word.
   */
  public String getLemma() {
    if (!isKnown()) {
      return null;
    }
    return words.get(preferred).getLemma();
  }

  /**
   * Returns the part of speech type of the preferred word.
   */
  public PartOfSpeechType getPartOfSpeechType() {
    if (!isKnown()) {
      return null;
    }
    return words.get(preferred).getPartOfSpeechType();
  }

  /**
   * Returns the Lexycal Type of the preferred word.
   */
  public LexicalType getLexicalType() {
    if (!isKnown()) {
      return null;
    }
    return words.get(preferred).getLexicalType();
  }

  /**
   * Returns the preferred index.
   */
  public int getPreferredIndex() {
    return preferred;
  }

  /**
   * Sets the preferred index.
   * @param idx the preffered index to be set.
   */
  public void setPreferredIndex(int idx) {
    preferred = idx;
  }

  /**
   * Tokenizes the given sentence.
   * @param str the sentence to be tokenized.
   * @return the tokens of the sentence.
   */
  public static List<Token> tokenize(String str) {
    List<Token> tks = new ArrayList<>();
    createTokens(str.split(" "), tks, 0, 4);
    return tks;
  }

  /**
   * Creates Tokens for the given list of words, adding them to an already user
   * defined Token list.
   * @param words Array of words to tokenize.
   * @param tokens List of already created tokens.
   * @param index the index to start the tokenization from.
   * @param aheah the index to.
   */
  private static void createTokens(String[] words, List<Token> tokens, int index, int aheah) {
    int len = words.length;
    // Base Case
    if (index >= len) {
      return;
    }

    // Common case
    int bound = Math.min(index + aheah + 1, len);
    int size = bound - index;

    StringBuffer buff = new StringBuffer();
    for (int i = index; i < bound; i++) {
      buff.append(words[i]);
      if (i + 1 < bound) {
        buff.append(' ');
      }
    }
    String word = buff.toString();

    Token tk = new Token(word);
    if (!tk.isKnown()) {
      word = Spelling.getInstance().correct(word);
      tk = new Token(word);
    }
    if (tk.isKnown()) {
      tokens.add(tk);
      createTokens(words, tokens, index + size, 4);
      return;
    }
    if (aheah > 0) {
      createTokens(words, tokens, index, aheah - 1);
    } else {
      tokens.add(tk); // Should we keep not known tokens?
      createTokens(words, tokens, index + 1, 4);
    }
  }

  /**
   * Compares this Token to the given object.
   * @param object the object to be compared against.
   * @return <code>true</code> if the given object represents the same Token of
             this instance;
             <code>false</code> otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Token)) {
      return false;
    }
    Token t = (Token)object;
    return t.text.equals(this.text);
  }

  /**
   * Returns a String representation of this Token.
   */
  @Override
  public String toString() {
    return "{text:" + text + ", preferred:{text:" + getLemma() + ", pos:"
        + getPartOfSpeechType() + ", lex:" + getLexicalType() + "} words:"
        + words + "}";
  }
}
