package com.github.bot.curiosone.core.refinement.zzz;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Noun {

  private String           lemma;
  private Optional<String> plural;
  
  //===============================================================================================
  
  /**
   * Noun constructor.
   * @param lemma lemma
   */
  public Noun(String lemma) {
    this.lemma = lemma;
    
  }
  
}
