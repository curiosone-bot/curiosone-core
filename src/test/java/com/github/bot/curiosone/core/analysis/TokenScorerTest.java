package com.github.bot.curiosone.core.analysis;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.nlp.Token;

import java.util.List;

import org.junit.Test;

public class TokenScorerTest {

  @Test
  public void testCalculateScoreSingleToken() {
    TokenScorer ts = new TokenScorer();
    List<Token> lt = Token.tokenize("The cat is on the car and the dog is on the table.");
    Token t = Token.tokenize("I am happy").get(2);

    assertThat(ts.calculateScore(t)).isPositive();

    t = lt.get(1);
    assertThat(ts.calculateScore(t)).isNegative();

    t = lt.get(5);
    assertThat(ts.calculateScore(t)).isZero();

    t = lt.get(9);
    assertThat(ts.calculateScore(t)).isZero();

    lt = Token.tokenize("Watch your mouth! Speak politely! None should be emotionally upset!");
    t = lt.get(0);
    assertThat(ts.calculateScore(t)).isPositive();

    t = lt.get(2);
    assertThat(ts.calculateScore(t)).isZero();

    t = lt.get(3);
    assertThat(ts.calculateScore(t)).isZero();

    t = lt.get(4);
    assertThat(ts.calculateScore(t)).isPositive();

    t = lt.get(5);
    assertThat(ts.calculateScore(t)).isNegative();

    t = lt.get(8);
    assertThat(ts.calculateScore(t)).isPositive();

    t = lt.get(9);
    assertThat(ts.calculateScore(t)).isNegative();
  }

  @Test
  public void testCalculateScoreListToken() {
    TokenScorer ts = new TokenScorer();
    List<Token> lt = Token.tokenize("I am very happy today!");
    assertThat(ts.calculateScore(lt)).isPositive();

    lt = Token.tokenize("I am very happy today! I feel ok.");
    assertThat(ts.calculateScore(lt)).isPositive();

    lt = Token.tokenize("I am very happy today! I feel ok. Happiness is crucial for peace of mind");
    assertThat(ts.calculateScore(lt)).isPositive();

    lt = Token.tokenize("Let's go negative, down down down negative negative score");
    assertThat(ts.calculateScore(lt)).isNegative();

    lt = Token.tokenize("That's a sad story :(");
    assertThat(ts.calculateScore(lt)).isNegative();

    lt = Token.tokenize("Microfossil curbstone");
    assertThat(ts.calculateScore(lt)).isZero();
  }
}
