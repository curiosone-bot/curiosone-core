package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;
import com.github.bot.curiosone.core.refinement.interfaces.Word;

public class NounPhrase implements Phrase {

  private NounWord main;
  
  public NounPhrase(NounWord main) {
    this.main = main;
  }
  
  @Override
  public String toString() {
    return main.toString();
  }

}
