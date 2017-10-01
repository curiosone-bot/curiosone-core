package com.github.bot.curiosone.core.refinement.interfaces;

public interface Clause {
  Phrase getSubject();
  Phrase getPredicate();
  @Override String toString();
}
