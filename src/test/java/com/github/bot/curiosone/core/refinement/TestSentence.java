package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.Sentence.Type;

import org.junit.Test;

public class TestSentence {

  @Test
  public void main() {
    
    Sentence s = new Sentence(
        Type.Answer,
        new MainClause(
            new NounPhrase(new Noun("Emily")),
            new VerbPhrase(new Verb("go"))
            )
        );
    
    System.out.println(s);
  }
  
}
