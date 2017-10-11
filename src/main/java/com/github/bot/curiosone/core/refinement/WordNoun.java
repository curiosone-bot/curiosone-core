package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

import java.util.Arrays;
import java.util.List;

public class WordNoun implements Word {

  private String lemma;
  private TypeNoun type;
  
  public WordNoun(String lemma, TypeNoun type) {
    this.lemma = lemma;
    this.type = type;
  }
  
  @Override
  public String toString() {
    
    if (type.equals(TypeNoun.Name)) {
      return lemma;
    }
    
    if (type.equals(TypeNoun.Singular)) {
      if (lemma.matches("h?[aeiou].*")) {return "an " + lemma;}
      else                              {return  "a " + lemma;}
    }
    
    try {
      String lexs = new EnglishMorpher()
          .getInflection(lemma, type.toCategory(), WordPart.Noun.forBabelMorph())
          .stream().map(Lexeme::toString).findAny().get();

      List<String> lexl = Arrays.asList(lexs.split("[\\[\\]]"));
      return lexl.get(lexl.size() - 1);
      
    } catch (Exception e) {
      return lemma;
    }
  }
  
}
