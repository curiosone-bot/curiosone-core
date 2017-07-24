package com.github.bot.curiosone.core.nlp.processing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.bot.curiosone.core.nlp.interfaces.ContextFreeGrammar;
import com.github.bot.curiosone.core.nlp.interfaces.GrammarProcessingUnit;
import com.github.bot.curiosone.core.nlp.interfaces.ParsingException;
import com.github.bot.curiosone.core.nlp.processing.Sentence;

public class GrammarProcessor implements GrammarProcessingUnit {
  private SessionEnvironment session;
  private String input;

  GrammarProcessor(SessionEnvironment session) {
    this.session = session;
  }

  private void setInput(String input) {
    this.input = input;
  }

  @Override
  public Sentence parse(String input) {
    setInput(input);

    Sentence sen = Tokenizer.getSentence(input); // modulo raffinamento e
                                                 // tokenaggio

    try {
      sen.setParseTree(Grammar.get().getParseTree(sen.getTokens()));
    } catch (ParsingException e) {
      // risponde "non ho capito"
    }

    return analyze(sen);
  }

  @Override
  public String generate(Sentence input) {
    return null;
  }

  private Sentence analyze(Sentence s) {
    s.setType(s.getParseTree().getRoute());

    if (s.getParseTree().getRoute() == Typology.QUESTION) {
      s.setParams(new QuestionExtractor().decode(s).getParams(s));
    }

    if (s.getParseTree().getRoute() == Typology.AFFIRMATION) {
      s.setParams(new AffirmationExtractor().decode(s).getParams(s));
    }

    return s;
  }

}
