package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.Sentence.Type;
import com.github.bot.curiosone.core.refinement.interfaces.Clause;
import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

import org.junit.Test;

public class TestSentence {

  @Test
  public void main() {
    
    Sentence s = new Sentence(
        Type.Answer,
        new MainClause(
            new NounPhrase(),
            new VerbPhrase()
            )
        );
    
    System.out.println(s);
  }
  
}
