package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.List;
import java.util.ArrayList;

import com.github.bot.curiosone.core.nlp.tokenizer.raw.RawDict;
import com.github.bot.curiosone.core.nlp.tokenizer.raw.RawToken;
// import com.github.bot.curiosone.core.nlp.tokenizer.raw.RawWord;

public class Token {

  /** Description */
  String text;

  /** Description */
  boolean known;

  /** Description */
  int preferred;

  /** Description */
  List<Word> words = new ArrayList<>();

  /**
   * [Token description]
   * @param  text [description]
   * @return [description]
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
   * [isKnown description]
   * @return [description]
   */
  public boolean isKnown() {
    return known;
  }

  /**
   * [getText description]
   * @return [description]
   */
  public String getText() {
    return text;
  }

  /**
   * [getWords description]
   * @return [description]
   */
  public ArrayList<Word> getWords() {
    return new ArrayList<Word>(words);
  }

  /**
   * [getLemma description]
   * @return [description]
   */
  public String getLemma() {
    if (!isKnown()) {
      return null;
    }
    return words.get(preferred).getLemma();
  }

  /**
   * [getPartOfSpeechType description]
   * @return [description]
   */
  public PartOfSpeechType getPartOfSpeechType() {
    if (!isKnown()) {
      return null;
    }
    return words.get(preferred).getPartOfSpeechType();
  }

  /**
   * [getLexicalType description]
   * @return [description]
   */
  public LexicalType getLexicalType() {
    if (!isKnown()) {
      return null;
    }
    return words.get(preferred).getLexicalType();
  }

  /**
   * [getPreferredIndex description]
   * @return [description]
   */
  public int getPreferredIndex() {
    return preferred;
  }

  /**
   * [setPreferredIndex description]
   * @param idx [description]
   */
  public void setPreferredIndex(int idx) {
    preferred = idx;
  }

  /**
   * [tokenize description]
   * @param  str [description]
   * @return [description]
   */
  public static List<Token> tokenize(String str) {
    List<Token> tks = new ArrayList<>();
    createTokens(str.split(" "), tks, 0, 4);
    return tks;
  }

  /**
   * [createTokens description]
   * @param words  [description]
   * @param tokens [description]
   * @param index  [description]
   * @param aheah  [description]
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
      if (i + 1 < bound) buff.append(' ');
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
   * [equals description]
   * @param  object [description]
   * @return [description]
   */
  @Override
  public boolean equals(Object object) {
    if(!(object instanceof Token)) {
      return false;
    }
    Token t = (Token)object;
    return t.text.equals(this.text);
  }

  /**
   * [toString description]
   * @return [description]
   */
  @Override
  public String toString() {
    return "{text:" + text + ", preferred:{text:" + getLemma() + ", pos:" + getPartOfSpeechType() + ", lex:" + getLexicalType() + "} words:" + words + "}";
  }
}
