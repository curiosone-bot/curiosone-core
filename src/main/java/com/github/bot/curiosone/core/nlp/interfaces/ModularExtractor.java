package com.github.bot.curiosone.core.nlp.interfaces;

import java.util.ArrayList;

import com.github.bot.curiosone.core.nlp.processing.Sentence;

public interface ModularExtractor
{
  ArrayList<String> getParams(Sentence s);
}
