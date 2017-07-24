package com.github.bot.curiosone.core.nlp.processing;

import com.github.bot.curiosone.core.nlp.interfaces.ParsingNode;

public enum NonTerminalValue
{
  S, // sentence
  QNP, // question nominal part
  ANP, // answer nominal part
  VP, // verbal part
  NP, // nominal part
  WHNP, // 5W nominal part

  N, // noun
  V, // verb
  ADJ, // adjective
  PRO, // pronoun
  PRON, // proper noun
  DET, // determinative
  WH, // 5wh
  YN, // yes no
  GRE, // hello hi bye
  AUX; // auxiliary
}
