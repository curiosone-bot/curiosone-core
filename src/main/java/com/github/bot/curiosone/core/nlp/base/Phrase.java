package com.github.bot.curiosone.core.nlp.base;

import java.util.ArrayList;
import java.util.List;

/**
* This class represents a phrase.
* A phrase has a type (question, answer...) and two different representations:
* as String ad as token list.
*/
public class Phrase {

  /**
  * Original string representation of the phrase, provided in input by the user.
  */
  private String text;

  /**
  * Tokens extracted from the original phrase.
  * @see Token
  */
  private List<Token> tokens = new ArrayList<>();

  /**
  * Sentence typology.
  * @see PhraseType
  */
  private PhraseType type;

  /**
  * Class constructor.
  * @param str The phrase to be represented.
  */
  public Phrase(String str) {
    text = str;
    type = PhraseType.get(str);
    tokens = Token.tokenize(str);
  }

  /**
  * Returns the original phrase, provided in input by the user.
  */
  public String getText() {
    return text;
  }

  /**
  * Returns the tokens of the original phrase.
  */
  public List<Token> getTokens() {
    return new ArrayList<Token>(tokens);
  }

  /**
  * Returns phrase type (answer, question...).
  */
  public PhraseType getType() {
    return type;
  }

  /**
  * Returns a list containing all the phrases tokenized from the inout.
  * @param input input text to be splitted in phrases.
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
  * Compares this Phrase to the specified object. The result is true if and
  * only if the argument is not null and is a Phrase object that has the same
  * text.
  * @param object The object to compare this Phrase against.
  */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Phrase) || object == null) {
      return false;
    }
    Phrase t = (Phrase)object;
    return t.text.equals(this.text);
  }

  /**
  * Returns a String representation of this Phrase.
  */
  @Override
  public String toString() {
    return "{text:" + text + ", type:" + type + ", tokens:" + tokens + "}";
  }
}
