package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.Optional;

public class Word {
  
  private String lemma;
  private Optional<WordPart> part;

  //===============================================================================================
  
  /**
   * Word constructor.
   * @param lemma citation form
   * @param part part of sentence
   */
  public Word(String lemma, WordPart part) {
    this.lemma = lemma;
    this.part  = Optional.of(part);
  }

  //-----------------------------------------------------------------------------------------------
  
  /**
   * Word constructor.
   * @param lemma citation form
   */
  public Word(String lemma) {
    this.lemma = lemma;
    this.part  = Optional.empty();
  }

  //-----------------------------------------------------------------------------------------------

  /**
   * Returns the part.
   * @return part
   */
  public Optional<WordPart> getPart() {
    return part;
  }

  //-----------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return lemma;
  }
  
  //-----------------------------------------------------------------------------------------------
}

/*
return new EnglishMorpher().getLexemes(shape, part.forBabelMorph()).stream()
.map(Lexeme::getLemma).collect(Collectors.toSet());
*/
