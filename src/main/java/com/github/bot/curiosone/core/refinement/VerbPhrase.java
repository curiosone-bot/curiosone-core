package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

public class VerbPhrase implements Phrase {

  private VerbWord main;
  
  public VerbPhrase(VerbWord main) {
    this.main = main;
  }
  
  @Override
  public String toString() {
    return main.toString();
  }

}
