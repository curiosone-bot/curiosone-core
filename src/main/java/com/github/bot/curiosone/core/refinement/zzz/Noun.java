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
    
    String s = new EnglishMorpher()
        .getInflection(this.lemma, "PLURAL", Part.Noun.forBabelMorph())
        .stream().map(Lexeme::toString).findAny().orElse("");
    
    List<String> l = Arrays.asList(s.split("[\\[\\]]"));
    
    plural = Optional.ofNullable(l.get(l.size() - 1));
  }
  
  //-----------------------------------------------------------------------------------------------
  
  public boolean hasPlural() {
    System.out.println(plural.get());
    return plural.isPresent();
  }
  
}
