package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Word.
 * Stores the lemma and the type of the Word.
 * Provides methods to create the Word and get its String representation.
 * @see  Word The Word Interface
 * @see  TypeNoun The TypeNoun Enum
 */
public class WordNoun implements Word {

  /**
   * Stores the lemma of this Word.
   */
  private String lemma;

  /**
   * Stores the type of this Word.
   */
  private TypeNoun type;

  /**
   * Constructs this Word.
   * @param  lemma
   *         the lemma of this Word
   * @param  type
   *         the type of this Word
   */
  public WordNoun(String lemma, TypeNoun type) {
    this.lemma = lemma;
    this.type = type;
  }

  /**
   * Returns a String representation of this Word.
   * Tries to resolve possible inflections of the Word.
   * @return  a String representation of this Word
   */
  @Override
  public String toString() {

    if (type.equals(TypeNoun.Name)) {
      return lemma;
    }

    if (type.equals(TypeNoun.Singular)) {
      if (lemma.matches("h?[aeiou].*")) {
        return "an " + lemma;
      } else {
        return  "a " + lemma;
      }
    }

    try {
      String lexs = new EnglishMorpher()
          .getInflection(lemma, type.toCategory(), WordPart.Noun.forBabelMorph())
          .stream().map(Lexeme::toString).findAny().get();

      List<String> lexl = Arrays.asList(lexs.split("[\\[\\]]"));
      return lexl.get(lexl.size() - 1);

    } catch (Exception e) {
      return lemma;
    }
  }

}
