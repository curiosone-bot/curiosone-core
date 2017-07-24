package com.github.bot.curiosone.core.nlp.interfaces;

import java.util.List;

import com.github.bot.curiosone.core.nlp.processing.NonTerminalValue;

public interface ParsingNode
{
  NonTerminalValue getValue();

  List<ParsingNode> getSons();

  boolean isRadix();

  boolean isLeaf();
}
