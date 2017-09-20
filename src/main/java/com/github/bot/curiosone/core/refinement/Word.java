package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.Set;
import java.util.stream.Collectors;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

public class Word {
  
  private String form;
  private Part   part;
  
  //===============================================================================================
  
  /**
   * Word constructor.
   * @param form superficial word
   * @param part part of sentence
   */
  public Word(String form, Part part) {
    this.form = form;
    this.part = part;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Returns the superficial form of the word.
   * @return word
   */
  public String getForm() {
    return form;
  }

  //-----------------------------------------------------------------------------------------------
  
  /**
   * Returns the part of sentence.
   * @return pos
   */
  public Part getPart() {
    return part;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Returns the lemma of the word.
   * @return lemma
   */
  public Set<String> getLemma() {
    return new EnglishMorpher().getLexemes(form, part.forBabelMorph()).stream()
    .map(Lexeme::getLemma).collect(Collectors.toSet());
  }
  
  //-----------------------------------------------------------------------------------------------
}
