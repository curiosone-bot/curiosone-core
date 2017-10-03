package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

public class NounWord implements Word {

  private String lemma;
  
  public NounWord(String lemma) {
    this.lemma = lemma;
  }
  
  @Override
  public String toString() {
    return lemma;
  }
}
