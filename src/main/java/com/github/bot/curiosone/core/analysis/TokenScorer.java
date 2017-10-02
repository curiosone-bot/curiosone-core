package com.github.bot.curiosone.core.analysis;

import static java.util.Comparator.comparing;

import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Token;
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

  private static DictionaryLoader dl = DictionaryLoader.getInstance();

  /**
   * Calculate the score of the Token.
   *
   * @param t is the Token add t's sentiment's score to scoreSet.
   */
  public static void calculateScore(Token t) {
    POS p = t.getMeanings().stream()
        .sorted(comparing(Meaning::getFrequency).reversed())
        .findFirst()
        .get().getPOS();
    if (p == POS.ADJ || p == POS.V || p == POS.ADV || p == POS.N) {
        scoreSet.add(dl.getScore(t.getLemma()));
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
