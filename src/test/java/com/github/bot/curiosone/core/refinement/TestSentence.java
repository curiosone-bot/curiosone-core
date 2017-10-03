package com.github.bot.curiosone.core.refinement;

import org.junit.Test;

public class TestSentence {

  @Test
  public void main() {
    
    Sentence s = new Sentence(
        SentenceType.Answer,
        new MainClause(
            new NounPhrase(new NounWord(NounType.Name, "Emily")),
            new VerbPhrase(new VerbWord("go"))
            )
        );
    
    System.out.println(s);
  }
  
}
