package com.github.bot.curiosone.core.nlp.interfaces;

import java.util.List;

import com.github.bot.curiosone.core.nlp.processing.ParseTree;
import com.github.bot.curiosone.core.nlp.processing.Token;

@FunctionalInterface
public interface ContextFreeGrammar
{
  ParseTree getParseTree(List<Token> tokens) throws ParsingException;
}
