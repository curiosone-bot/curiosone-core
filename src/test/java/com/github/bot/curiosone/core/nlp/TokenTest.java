package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class TokenTest {
  @Test
  public void testInstantiation() {
    Token classUnderTest = Token.tokenize("color").get(0);
    Set<Meaning> means = classUnderTest.getMeanings();
    assertTrue(means.size() > 0);
  }

  @Test
  public void testGetLemma() {
    Token token;

    token = Token.tokenize("color").get(0);
    assertEquals("color", token.getLemma());

    token = Token.tokenize("colors").get(0);
    assertEquals("color", token.getLemma());

    token = Token.tokenize("you").get(0);
    assertEquals("you", token.getLemma());

    token = Token.tokenize("doing").get(0);
    assertEquals("do", token.getLemma());

    token = Token.tokenize("is").get(0);
    assertEquals("be", token.getLemma());

    token = Token.tokenize("held").get(0);
    assertEquals("hold", token.getLemma());
  }

  @Test
  public void testEquals() {
    Token classUnderTest = Token.tokenize("color").get(0);
    Token classUnderTestClone = Token.tokenize("color").get(0);
    assertEquals(classUnderTest, classUnderTestClone);
    assertEquals(classUnderTest, classUnderTest);
    assertNotEquals(classUnderTest, null);
    assertNotEquals(classUnderTest, "Bla bla bla");
  }

  @Test
  public void testHashCode() {
    List<Token> classUnderTest =
        Token.tokenize("Testing out the hashCode function!");
    List<Token> classUnderTestClone =
        Token.tokenize("Testing out the hashCode function!");
    assertEquals(classUnderTestClone.hashCode(), classUnderTest.hashCode());

    classUnderTest = Token.tokenize("I will have a hashCode!");
    classUnderTestClone = Token.tokenize("And I will have another hashCode!!");
    assertNotEquals(classUnderTestClone.hashCode(), classUnderTest.hashCode());
  }

  @Test
  public void testTokenize() {
    List<Token> tokens;

    tokens = Token.tokenize("The cat is on the table!");
    assertEquals(4, tokens.size());
    assertEquals("the", tokens.get(0).getText());
    assertEquals("cat", tokens.get(1).getText());
    assertEquals("is", tokens.get(2).getText());
    assertEquals("on the table", tokens.get(3).getText());

    tokens = Token.tokenize("I love united states of america");
    assertEquals(3, tokens.size());
    assertEquals("i", tokens.get(0).getText());
    assertEquals("love", tokens.get(1).getText());
    assertEquals("united states of america", tokens.get(2).getText());

    tokens = Token.tokenize("I am born in the united states of america");
    assertEquals(5, tokens.size());
    assertEquals("i", tokens.get(0).getText());
    assertEquals("am born", tokens.get(1).getText());
    assertEquals("in", tokens.get(2).getText());
    assertEquals("the", tokens.get(3).getText());
    assertEquals("united states of america", tokens.get(4).getText());

    tokens = Token.tokenize("what is an apple?");
    assertEquals(4, tokens.size());
    assertEquals("what", tokens.get(0).getText());
    assertEquals("is", tokens.get(1).getText());
    assertEquals("an", tokens.get(2).getText());
    assertEquals("apple", tokens.get(3).getText());

    tokens = Token.tokenize("Where is the cat?");
    assertEquals(4, tokens.size());
    assertEquals("where", tokens.get(0).getText());
    assertEquals("is", tokens.get(1).getText());
    assertEquals("the", tokens.get(2).getText());
    assertEquals("cat", tokens.get(3).getText());

    tokens = Token.tokenize("When the cat was created?");
    assertEquals(5, tokens.size());
    assertEquals("when", tokens.get(0).getText());
    assertEquals("the", tokens.get(1).getText());
    assertEquals("cat", tokens.get(2).getText());
    assertEquals("was", tokens.get(3).getText());
    assertEquals("created", tokens.get(4).getText());
  }
}
