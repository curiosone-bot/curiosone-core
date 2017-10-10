package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

import it.uniroma1.lcl.babelmorph.Lexeme;
import it.uniroma1.lcl.babelmorph.en.EnglishMorpher;

import java.util.Arrays;
import java.util.List;

public class NounWord implements Word {

  private String lemma;
  private NounType type;
  
  public NounWord(NounType type, String lemma) {
    this.lemma = lemma;
    this.type = type;
  }
  
  @Override
  public String toString() {
    
    /*
     * noun is a named entity or singular
     */
    if (!type.equals(NounType.Plural)) {
      return lemma;
    }
    
    /*
     * noun is plural
     */
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
