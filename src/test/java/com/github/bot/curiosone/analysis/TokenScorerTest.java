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

    assertThat(ts.calculateScore(t)).isEqualTo(0.5625);

    t = lt.get(1);
    assertThat(ts.calculateScore(t)).isEqualTo(-0.0125);

    t = lt.get(5);
    assertThat(ts.calculateScore(t)).isEqualTo(0.0);

    t = lt.get(9);
    assertThat(ts.calculateScore(t)).isEqualTo(0.0);

    lt = Token.tokenize("Watch your mouth! Speak politely! None should be emotionally upset!");
    t = lt.get(0);
    assertThat(ts.calculateScore(t)).isEqualTo(0.057692307692307696);

    t = lt.get(2);
    assertThat(ts.calculateScore(t)).isEqualTo(0.0);

    t = lt.get(3);
    assertThat(ts.calculateScore(t)).isEqualTo(0.0);

    t = lt.get(4);
    assertThat(ts.calculateScore(t)).isEqualTo(0.125);

    t = lt.get(5);
    assertThat(ts.calculateScore(t)).isEqualTo(-0.28125);

    t = lt.get(8);
    assertThat(ts.calculateScore(t)).isEqualTo(0.0625);

    t = lt.get(9);
    assertThat(ts.calculateScore(t)).isEqualTo(-0.23529411764705882);
  }

  @Test
  public void testCalculateScoreListToken() {
    TokenScorer ts = new TokenScorer();
    List<Token> lt = Token.tokenize("I am very happy today!");
    assertThat(ts.calculateScore(lt)).isGreaterThan(0.0);

    lt = Token.tokenize("I am very happy today! I feel ok.");
    assertThat(ts.calculateScore(lt)).isGreaterThan(0.0);

    lt = Token.tokenize("I am very happy today! I feel ok. Happiness is crucial for peace of mind");
    assertThat(ts.calculateScore(lt)).isGreaterThan(0.0);

    lt = Token.tokenize("Let's go negative, down down down negative negative score");
    assertThat(ts.calculateScore(lt)).isLessThan(0.0);

    lt = Token.tokenize("That's a sad story :(");
    assertThat(ts.calculateScore(lt)).isLessThan(0.0);

    lt = Token.tokenize("Microfossil curbstone");
    assertThat(ts.calculateScore(lt)).isEqualTo(0.0);
  }
}
