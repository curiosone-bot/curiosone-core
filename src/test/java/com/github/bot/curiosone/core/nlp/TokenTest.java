package com.github.bot.curiosone.core.nlp;

import static org.assertj.core.api.Assertions.assertThat;
// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class TokenTest {

  @Test
  public void testGetText() {
    List<Token> tokens = Token.tokenize("Happy birthday to you!");

    Token t = tokens.get(0);
    assertThat(t.getText()).isEqualTo("happy");

    t = tokens.get(1);
    assertThat(t.getText()).isEqualTo("birthday");

    t = tokens.get(2);
    assertThat(t.getText()).isEqualTo("to");

    t = tokens.get(3);
    assertThat(t.getText()).isEqualTo("you");
  }

  @Test
  public void testIsKnown() {
    List<Token> tokens = Token.tokenize("The sixth token is unknown, doyoubelieveme?");

    Token t = tokens.get(0);
    assertThat(t.isKnown()).isTrue();

    t = tokens.get(1);
    assertThat(t.isKnown()).isTrue();

    t = tokens.get(2);
    assertThat(t.isKnown()).isTrue();

    t = tokens.get(3);
    assertThat(t.isKnown()).isTrue();

    t = tokens.get(4);
    assertThat(t.isKnown()).isTrue();

    t = tokens.get(5);
    assertThat(t.isKnown()).isFalse();
  }

  @Test
  public void testGetMeanings() {
    Token t = Token.tokenize("color").get(0);
    Set<Meaning> means = t.getMeanings();
    assertThat(means).isNotEmpty();

    t = Token.tokenize("eat").get(0);
    means = t.getMeanings();
    assertThat(means).isNotEmpty();

    t = Token.tokenize("place").get(0);
    means = t.getMeanings();
    assertThat(means).isNotEmpty();
  }

  @Test
  public void testGetLemma() {
    Token token = Token.tokenize("color").get(0);
    assertThat(token.getLemma()).isEqualTo("color");

    token = Token.tokenize("colors").get(0);
    assertThat(token.getLemma()).isEqualTo("color");

    token = Token.tokenize("you").get(0);
    assertThat(token.getLemma()).isEqualTo("you");

    token = Token.tokenize("doing").get(0);
    assertThat(token.getLemma()).isEqualTo("do");

    token = Token.tokenize("is").get(0);
    assertThat(token.getLemma()).isEqualTo("be");

    token = Token.tokenize("held").get(0);
    assertThat(token.getLemma()).isEqualTo("hold");

    token = Token.tokenize("hold up").get(0);
    assertThat(token.getLemma()).isEqualTo("hold_up");

    token = Token.tokenize("hang on").get(0);
    assertThat(token.getLemma()).isEqualTo("hang_on");

    token = Token.tokenize("hang in").get(0);
    assertThat(token.getLemma()).isEqualTo("hang_in");

    token = Token.tokenize("check out").get(0);
    assertThat(token.getLemma()).isEqualTo("check_out");

    token = Token.tokenize("united kingdom").get(0);
    assertThat(token.getLemma()).isEqualTo("united_kingdom");

    token = Token.tokenize("42").get(0);
    assertThat(token.getLemma()).isEqualTo("42");
  }


  @Test
  public void testTokenize() {
    List<Token> tokens = Token.tokenize("The cat is on the table!");
    assertThat(tokens).hasSize(4);
    assertThat(tokens.get(0).getText()).isEqualTo("the");
    assertThat(tokens.get(1).getText()).isEqualTo("cat");
    assertThat(tokens.get(2).getText()).isEqualTo("is");
    assertThat(tokens.get(3).getText()).isEqualTo("on the table");

    tokens = Token.tokenize("I love united states of america");
    assertThat(tokens).hasSize(3);
    assertThat(tokens.get(0).getText()).isEqualTo("i");
    assertThat(tokens.get(1).getText()).isEqualTo("love");
    assertThat(tokens.get(2).getText()).isEqualTo("united states of america");

    tokens = Token.tokenize("I was born in the united states of america");
    assertThat(tokens).hasSize(5);
    assertThat(tokens.get(0).getText()).isEqualTo("i");
    assertThat(tokens.get(1).getText()).isEqualTo("was born");
    assertThat(tokens.get(2).getText()).isEqualTo("in");
    assertThat(tokens.get(3).getText()).isEqualTo("the");
    assertThat(tokens.get(4).getText()).isEqualTo("united states of america");


    tokens = Token.tokenize("what is an apple?");
    assertThat(tokens).hasSize(4);
    assertThat(tokens.get(0).getText()).isEqualTo("what");
    assertThat(tokens.get(1).getText()).isEqualTo("is");
    assertThat(tokens.get(2).getText()).isEqualTo("an");
    assertThat(tokens.get(3).getText()).isEqualTo("apple");

    tokens = Token.tokenize("Where is the cat?");
    assertThat(tokens).hasSize(4);
    assertThat(tokens.get(0).getText()).isEqualTo("where");
    assertThat(tokens.get(1).getText()).isEqualTo("is");
    assertThat(tokens.get(2).getText()).isEqualTo("the");
    assertThat(tokens.get(3).getText()).isEqualTo("cat");

    tokens = Token.tokenize("When was the cat created?");
    assertThat(tokens).hasSize(5);
    assertThat(tokens.get(0).getText()).isEqualTo("when");
    assertThat(tokens.get(1).getText()).isEqualTo("was");
    assertThat(tokens.get(2).getText()).isEqualTo("the");
    assertThat(tokens.get(3).getText()).isEqualTo("cat");
    assertThat(tokens.get(4).getText()).isEqualTo("created");

    tokens = Token.tokenize("TokenizeMe!");
    assertThat(tokens).hasSize(1);
    assertThat(tokens.get(0).getText()).isEqualTo("tokenizeme");

    tokens = Token.tokenize("Hey!You");
    assertThat(tokens).hasSize(1);
    assertThat(tokens.get(0).getText()).isEqualTo("heyyou");
  }

  @Test
  public void testEqualsReflexive() {
    Token t = Token.tokenize("color").get(0);
    assertThat(t).isEqualTo(t);

    t = Token.tokenize("united kingdom").get(0);
    assertThat(t).isEqualTo(t);

    t = Token.tokenize("42").get(0);
    assertThat(t).isEqualTo(t);
  }

  @Test
  public void testEqualsSymmetric() {
    List<Token> tokens = Token.tokenize("color color");
    Token t = tokens.get(0);
    Token tt = tokens.get(1);
    assertThat(t).isEqualTo(tt);
    assertThat(tt).isEqualTo(t);

    tokens = Token.tokenize("42 42.");
    t = tokens.get(0);
    tt = tokens.get(1);
    assertThat(t).isEqualTo(tt);
    assertThat(tt).isEqualTo(t);

    tokens = Token.tokenize("COLOR color");
    t = tokens.get(0);
    tt = tokens.get(1);
    assertThat(t).isEqualTo(tt);
    assertThat(tt).isEqualTo(t);
  }

  @Test
  public void testEqualsTransitive() {
    List<Token> tokens = Token.tokenize("ColoR color COLOR");
    Token t = tokens.get(0);
    Token tt = tokens.get(1);
    Token ttt = tokens.get(2);
    assertThat(t).isEqualTo(tt);
    assertThat(tt).isEqualTo(ttt);
    assertThat(ttt).isEqualTo(t);

    tokens = Token.tokenize("number number. number!");
    t = tokens.get(0);
    tt = tokens.get(1);
    ttt = tokens.get(2);
    assertThat(t).isEqualTo(tt);
    assertThat(tt).isEqualTo(ttt);
    assertThat(ttt).isEqualTo(t);
  }

  @Test
  public void testEqualsNullComparison() {
    List<Token> tokens = Token.tokenize("Please, tokenize me!");
    assertThat(tokens.get(0)).isNotEqualTo(null);
    assertThat(tokens.get(1)).isNotEqualTo(null);
    assertThat(tokens.get(2)).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    List<Token> tokens = Token.tokenize("Please, tokenize me!");
    assertThat(tokens.get(0)).isNotEqualTo("This is a simple string");
    assertThat(tokens.get(1)).isNotEqualTo(new Float(10.20));
    assertThat(tokens.get(1)).isNotEqualTo(new Object());
  }

  @Test
  public void testNotEquals() {
    List<Token> tokens = Token.tokenize("Please, tokenize me!");
    assertThat(tokens.get(0)).isNotEqualTo(tokens.get(1));
    assertThat(tokens.get(2)).isNotEqualTo(tokens.get(1));
    assertThat(tokens.get(2)).isNotEqualTo(tokens.get(0));
  }

  @Test
  public void testHashCodeReflexive() {
    List<Token> tokens = Token.tokenize("Please, tokenize me!");
    Token t = tokens.get(0);
    assertThat(t.hashCode()).isEqualTo(t.hashCode());
  }

  @Test
  public void testHashCodeEqualsContract() {
    List<Token> tokens = Token.tokenize("TokenizE, tokenize me!");
    Token t = tokens.get(0);
    Token tt = tokens.get(1);
    assertThat(t).isEqualTo(tt);
    assertThat(t.hashCode()).isEqualTo(tt.hashCode());

    tt = tokens.get(2);
    assertThat(t).isNotEqualTo(tt);
    assertThat(t.hashCode()).isNotEqualTo(tt.hashCode());
  }
}
