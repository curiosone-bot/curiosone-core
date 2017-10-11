package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

import java.util.Arrays;
import java.util.List;

public class WordVerb implements Word {

  private String lemma;
  private TypeVerb type;
  
  public WordVerb(String lemma, TypeVerb type) {
    this.lemma = lemma;
    this.type = type;
  }
  
  @Override
  public String toString() {
    
    if (lemma.equals("be")) {
      if (type.equals(TypeVerb.PresentS1)) {
        return "am";
      }
      if (type.equals(TypeVerb.PresentS3)) {
        return "is";
      }
    }
    
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
