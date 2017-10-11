package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

public class PhraseNoun implements Phrase {

  private WordNoun main;
  
  public PhraseNoun(WordNoun main) {
    this.main = main;
  }
  
  @Override
  public String toString() {
    return main.toString();
  }

}
