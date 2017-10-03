package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;
import com.github.bot.curiosone.core.refinement.interfaces.Word;

public class NounPhrase implements Phrase {

  private Noun main;
  
  public NounPhrase(Noun main) {
    this.main = main;
  }
  
  @Override
  public String toString() {
    return main.toString();
  }

}
