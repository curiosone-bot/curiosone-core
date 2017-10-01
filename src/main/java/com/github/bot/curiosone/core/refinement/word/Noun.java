package com.github.bot.curiosone.core.refinement.word;
/**
 * @author Claudio Venanzi
 */

import java.util.Optional;

import com.github.bot.curiosone.core.refinement.Word;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;


public class Noun extends Word {
  
  boolean plural;
  
  //===============================================================================================
  
  public Noun(String lemma) {
    super(lemma, Part.Noun);
  }
  
  //-----------------------------------------------------------------------------------------------
  
  public void setPlural(boolean p) {
    plural = p;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  public Optional<String> hasPlural() {
    return new EnglishMorpher().getInflection(getForm(), "PLURAL", getPart().forBabelMorph())
        .stream().map(Lexeme::toString).findAny();
  }
}
