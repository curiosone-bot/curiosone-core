package com.github.bot.curiosone.core.nlp.interfaces;

import com.github.bot.curiosone.core.nlp.processing.Sentence;

public interface GrammarProcessingUnit
{
  Sentence parse(String input);

  String generate(Sentence input);
}
