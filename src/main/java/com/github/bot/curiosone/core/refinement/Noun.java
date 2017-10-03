package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

public class Noun implements Word {

  private String lemma;
  
  public Noun(String lemma) {
    this.lemma = lemma;
  }
  
  @Override
  public String toString() {
    return lemma;
  }
}
