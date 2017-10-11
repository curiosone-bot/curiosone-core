package com.github.bot.curiosone.core.refinement;

import java.util.Optional;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

public class PhraseVerb implements Phrase {

  private WordVerb main;
  private Optional<PhraseNoun> object;

  public PhraseVerb(WordVerb main, PhraseNoun dependents) {
    this.main = main;
    this.object = Optional.of(dependents);
  }

  public PhraseVerb(WordVerb main) {
    this.main = main;
    this.object = Optional.empty();
  }
  
  @Override
  public String toString() {
    return main.toString() + (object.isPresent() ? " " + object.get().toString() : "");
  }

}
