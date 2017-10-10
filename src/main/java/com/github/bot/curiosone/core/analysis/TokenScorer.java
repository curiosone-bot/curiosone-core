package com.github.bot.curiosone.core.analysis;

import static java.util.Comparator.comparing;

import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Token;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Calculates the Sentiment score of a Sentence.
 * Provides methods to calculate the sentiment score from a single Token or a Token List.
 * @see  com.github.bot.curiosone.core.nlp.Token
 */

public class TokenScorer {

  /**
   * Stores the dictionary instance, used to compute the Sentiment index value.
   */
  private static DictionaryLoader dict = DictionaryLoader.getInstance();

  /**
   * Calculates the sentiment value for a given Token.
   * This value ranges between -1.0 and 1.0.
   * @param  token
   *         The Token to be analysed.
   * @return  a Double value, representing the sentiment index for the given Token.
   *          This index ranges between -1.00 (sad) and 1.00 (happy), 0.00 is a neutral score
   * @see  com.github.bot.curiosone.core.nlp.Token
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
   * Calculates the sentiment value for a given Token List.
   * This value ranges between -1.0 and 1.0.
   * @param  tokenList
   *         The Token List to be analysed.
   * @return  a Double value, representing the sentiment index for the given Token List.
   *          This index ranges between -1.00 (sad) and 1.00 (happy), 0.00 is a neutral score
   * @see  com.github.bot.curiosone.core.nlp.Token
   */
  public static double calculateScore(List<Token> tokenList) {
    OptionalDouble score = tokenList.stream()
        .mapToDouble(TokenScorer::calculateScore)
        .filter(x -> x != 0.0)
        .average();
    return score.isPresent() ? score.getAsDouble() : 0.0;
  }
}
