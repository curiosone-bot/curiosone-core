package com.github.bot.curiosone.core.refinement;

import java.util.Optional;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

public class VerbPhrase implements Phrase {

  private VerbWord main;
  private Optional<NounPhrase> object;

  public VerbPhrase(VerbWord main, NounPhrase dependents) {
    this.main = main;
    this.object = Optional.of(dependents);
  }

  public VerbPhrase(VerbWord main) {
    this.main = main;
    this.object = Optional.empty();
  }
  
  @Override
  public String toString() {
    return main.toString() + (object.isPresent() ? " " + object.get().toString() : "");
  }

}
