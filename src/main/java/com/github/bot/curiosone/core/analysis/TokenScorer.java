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
  private static DictionaryLoader dict = DictionaryLoader.getInstance();

  /**
   * Calculate the score of the Token.
   *
   * @param token [description]
   *
   * @return a double value of the given token.
   */
  public static double calculateScore(Token token) {
    POS pos = token.getMeanings().stream()
        .sorted(comparing(Meaning::getFrequency).reversed())
        .findFirst()
        .get().getPOS();
    return (pos == POS.ADJ || pos == POS.V || pos == POS.ADV || pos == POS.N)
        ? dict.getScore(token.getLemma()) : 0.0;
  }

  /**
   * Calculate the score of the input token's list.
   *
   * @param tokenList the token list.
   *
   * @return a double in range -1.0 and 1.0(Saddest to Happiest, 0.0 is a neutral score).
   */
  public static double calculateScore(List<Token> tokenList) {
    OptionalDouble score = tokenList.stream()
        .mapToDouble(TokenScorer::calculateScore)
        .filter(x -> x != 0.0)
        .average();
    return score.isPresent() ? score.getAsDouble() : 0.0;
  }
}
