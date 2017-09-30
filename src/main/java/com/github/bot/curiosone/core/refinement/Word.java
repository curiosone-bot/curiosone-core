package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Word {

  private Optional<String> shape = Optional.empty();
  
  private String  lemma;
  private WordPart part;

  //===============================================================================================
  
  /**
   * Word constructor.
   * @param form superficial word
   * @param part part of sentence
   */
  public Word(String lemma, WordPart part) {
    this.lemma = lemma;
    this.part  =  part;
  }
  
  //-----------------------------------------------------------------------------------------------

  /**
   * Returns the lemma.
   * @return string
   */
  public String getLemma() {
    return lemma;
  }

  //-----------------------------------------------------------------------------------------------

  /**
   * Returns the part.
   * @return WordPart
   */
  public WordPart getPart() {
    return part;
  }

  //-----------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return shape.orElse(lemma);
  }
  
  //-----------------------------------------------------------------------------------------------  
}

/*
return new EnglishMorpher().getLexemes(shape, part.forBabelMorph()).stream()
.map(Lexeme::getLemma).collect(Collectors.toSet());
*/
