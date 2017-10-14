package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

import java.util.Arrays;
import java.util.List;

/**
 * Stores a verbal word.
 * Contains its lemma and its type.
 * Provides methods to create this verbal Word and retrieve its String representation.
 */
public class WordVerb implements Word {

  /**
   * Stores the lemma.
   */
  private String lemma;

  /**
   * Stores the type of the verb.
   */
  private TypeVerb type;

  /**
   * Constructs the Word with a given lemma and its type.
   * @param  lemma
   *         the lemma of this Word
   * @param  type
   *         the type of this Word
   */
  public WordVerb(String lemma, TypeVerb type) {
    this.lemma = lemma;
    this.type = type;
  }

  /**
   * Returns a String representation of this Word.
   * Tries to return the correct verb conjugation, according to the lemma and the type of this Word.
   * @return  String representation of this Word
   */
  @Override
  public String toString() {

    if (lemma.equals("be")) {
      if (type.equals(TypeVerb.PresentS1)) {
        return "am";
      }
      if (type.equals(TypeVerb.PresentS3)) {
        return "is";
      }
    }

    try {
      String lexs = new EnglishMorpher()
          .getInflection(lemma, type.toCategory(), WordPart.Verb.forBabelMorph())
          .stream().map(Lexeme::toString).findAny().get();

      List<String> lexla = Arrays.asList(lexs.split("[\\[\\]]"));
      List<String> lexlb = Arrays.asList(lexla.get(lexla.size() - 1).split(", "));
      return lexlb.get(0);

    } catch (Exception e) {
      return lemma;
    }
  }
}
