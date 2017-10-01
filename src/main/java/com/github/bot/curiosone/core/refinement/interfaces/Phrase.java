package com.github.bot.curiosone.core.refinement.interfaces;

import java.util.List;

public interface Phrase {
  List<Word> getWords();
  @Override String toString();
}
