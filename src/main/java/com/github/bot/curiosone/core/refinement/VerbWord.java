package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

public class VerbWord implements Word {

  private String lemma;
  
  public VerbWord(String lemma) {
    this.lemma = lemma;
  }
  
  @Override
  public String toString() {
    return lemma;
  }
  
}
