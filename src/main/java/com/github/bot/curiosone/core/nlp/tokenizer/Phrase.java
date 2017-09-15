package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.List;
import java.util.ArrayList;

public class Phrase {

  /** Description */
  private String text;

  /** Description */
  private List<Token> tokens = new ArrayList<>();

  /** Description */
  private PhraseType type;

  /**
   * [Phrase description]
   * @param  str [description]
   * @return [description]
   */
  public Phrase(String str) {
    text = str;
    type = PhraseType.get(str);
    tokens = Token.tokenize(str);
  }

  /**
   * [getText description]
   * @return [description]
   */
  public String getText() {
    return text;
  }

  /**
   * [getTokens description]
   * @return [description]
   */
  public List<Token> getTokens() {
    return new ArrayList<Token>(tokens);
  }

  /**
   * [getType description]
   * @return [description]
   */
  public PhraseType getType() {
    return type;
  }

  /**
   * [extract description]
   * @param  input [description]
   * @return [description]
   */
  public static List<Phrase> extract(String input) {
    List<String> phr = LangUtils.splitByPuntaction(input);
    for (int i = 0; i < phr.size(); i++) {
      phr.set(i, LangUtils.removeDuplicatedSpaces(phr.get(i)));
      phr.set(i, LangUtils.expandVerbs(phr.get(i)));
    }

    List<Phrase> phrases = new ArrayList<>(phr.size());
    for (int i = 0; i < phr.size(); i++) {
      Phrase p = new Phrase(phr.get(i));
      phrases.add(p);
    }
    return phrases;
  }

  /**
   * [equals description]
   * @param  object [description]
   * @return [description]
   */
  @Override
  public boolean equals(Object object) {
    if(!(object instanceof Phrase)) {
      return false;
    }
    Phrase t = (Phrase)object;
    return t.text.equals(this.text);
  }

  /**
   * [toString description]
   * @return [description]
   */
  @Override
  public String toString() {
    return "{text:" + text + ", type:" + type + ", tokens:" + tokens + "}";
  }
}
