package com.github.bot.curiosone.core.nlp.interfaces;

import com.github.bot.curiosone.core.nlp.processing.Sentence;

public interface ExtractionRoute
{
  ModularExtractor decode(Sentence s);
}
