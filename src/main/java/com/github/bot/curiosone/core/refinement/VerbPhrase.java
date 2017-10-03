package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

public class VerbPhrase implements Phrase {

  private Verb main;
  
  public VerbPhrase(Verb main) {
    this.main = main;
  }
  
  @Override
  public String toString() {
    return main.toString();
  }

}
