package com.github.bot.curiosone.core.analysis;

import static java.util.Comparator.comparing;

import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Token;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Finding Sentiment's score.
 *
 */

public class TokenScorer {
  private static DictionaryLoader dl = DictionaryLoader.getInstance();

  /**
   * Calculate the score of the Token.
   *
   * @param t is the Token add t's sentiment's score to scoreSet.
   * 
   * @return a double value of the given token, -2.0 if not present.
   */
  public static double calculateScore(Token t) {
    POS p = t.getMeanings().stream()
        .sorted(comparing(Meaning::getFrequency).reversed())
        .findFirst()
        .get().getPOS();
    return (p == POS.ADJ || p == POS.V || p == POS.ADV || p == POS.N)
        ? dl.getScore(t.getLemma()) : DictionaryLoader.INVALID_SCORE;
  }

  /**
   * Calculate the score of the input token's list.
   *
   * @param tokenList the token list.
   * 
   * @return a double in range -1.0 and 1.0. 
   */
  public static double calculateScore(List<Token> tokenList) {
    OptionalDouble d = tokenList.stream().mapToDouble(TokenScorer::calculateScore)
        .filter(x -> (x != -2.0)).average();
    return d.isPresent() ? d.getAsDouble() : DictionaryLoader.INVALID_SCORE;
  }
}
