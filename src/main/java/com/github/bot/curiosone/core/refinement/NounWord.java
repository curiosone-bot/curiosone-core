package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

public class NounWord implements Word {

  private String lemma;
  private NounType type;
  
  public NounWord(NounType type, String lemma) {
    this.lemma = lemma;
    this.type = type;
  }
  
  @Override
  public String toString() {
    return lemma;
  }
}
