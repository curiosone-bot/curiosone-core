package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

import java.util.Arrays;
import java.util.List;

public class VerbWord implements Word {

  private String lemma;
  private VerbType type;
  
  public VerbWord(String lemma, VerbType type) {
    this.lemma = lemma;
    this.type = type;
  }
  
  @Override
  public String toString() {
    
    try {
      String lexs = new EnglishMorpher()
          .getInflection(lemma, type.toCategory(), WordPart.Verb.forBabelMorph())
          .stream().map(Lexeme::toString).findAny().get();
      
      List<String> lexla = Arrays.asList(lexs.split("[\\[\\]]"));
      List<String> lexlb = Arrays.asList(lexla.get(lexla.size() - 1).split(", "));
      return lexlb.get(0);

    } catch (Exception e) {
      return lemma;
    }
  }
  
}
