package com.github.bot.curiosone.core.analysis;

import com.github.bot.curiosone.core.nlp.tokenizer.PosT;
import com.github.bot.curiosone.core.nlp.tokenizer.Token;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Finding Sentiment's score.
 *
 * @author Cosmo Pugliese && Francesco Natale
 */

public class TokenScorer {
  
  /**
   * HashSet containing tokens' score.
   */
  private static HashSet<Double> scoreSet = new HashSet<>();

  /**
   * Load SentiWordNet's dictionary.
   */
  private static Map<String, Double> dictionary = DictionaryLoader.loadDict();

  /**
   * Calculate the score of the Token.
   * 
   * @param t is the Token add t's sentiment's score to scoreSet.
   */
  public static void calculateScore(Token t) {
    if (t.getPos() == PosT.ADJ || t.getPos() == PosT.V || t.getPos() == PosT.ADV
        || t.getPos() == PosT.N) {
      if (dictionary.containsKey(t.getLemma())) {
        scoreSet.add(dictionary.get(t.getLemma()));
      }
    }
  }
  
  /**
   * Calculate the score of the input token's list.
   * 
   * @param tokenList the token list.
   */
  public static void calculateScore(List<Token> tokenList) {
    for (Token x : tokenList) {
      calculateScore(x);
    }
  }
  
  /**
   * Calculate the score of the token analyzed until this method call.
   * 
   * @return  the score of analyzed tokens.
   */
  public static Double getScore() {
    HashSet<Double> temp = new HashSet<>(scoreSet);
    scoreSet = new HashSet<>();
    return temp.stream().reduce(0.0, Double::sum) / temp.size();
  }
  
}
