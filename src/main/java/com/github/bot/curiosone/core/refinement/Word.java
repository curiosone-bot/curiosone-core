package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.Set;
import java.util.stream.Collectors;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

public class Word {
  
  private String form;
  private POS pos;
  
  //===============================================================================================
  
  public Word(String form, POS pos) {
    this.form = form;
    this.pos = pos;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  public String getForm() {
    return form;
  }

  //-----------------------------------------------------------------------------------------------
    
  public POS getPOS() {
    return pos;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  public Set<String> getLemma() {
    return new EnglishMorpher().getLexemes(form, pos).stream()
    .map(Lexeme::getLemma).collect(Collectors.toSet());
  }
}
/*

do (VERB): 
[PRESENT]:[do];
[PRESENT, THIRD_PERSON, SINGULAR]:[does]; 
[PARTICIPLE, PAST]:[done]; 
[SIMPLE_PAST]:[did]; 
[PARTICIPLE, PRESENT]:[doing]

getLexemes
getInflections

*/    