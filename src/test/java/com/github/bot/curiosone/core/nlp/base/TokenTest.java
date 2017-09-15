package com.github.bot.curiosone.core.nlp.base;

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
    Token classUnderTest = new Token("color");
    assertTrue(classUnderTest instanceof Token);

    assertEquals("color", classUnderTest.getLemma());
    assertEquals(PartOfSpeechType.N, classUnderTest.getPartOfSpeechType());
    assertEquals(LexicalType.ATTRIBUTE, classUnderTest.getLexicalType());
  }

  @Test
  public void testEquals() {
    Token classUnderTest = new Token("color");
    Token classUnderTestClone = new Token("color");
    assertEquals(classUnderTest, classUnderTestClone);

    List<Word> lw = classUnderTest.getWords();
    Set<Word> hw = new HashSet<>(lw);
    assertEquals(lw.size(), hw.size());
  }

  @Test
  public void testWordsUniquness() {
    Token classUnderTest = new Token("color");

    List<Word> lw = classUnderTest.getWords();
    Set<Word> hw = new HashSet<>(lw);
    assertEquals(lw.size(), hw.size());
  }

  @Test
  public void testTokenize() {
    List<Token> tokens;

    tokens = Token.tokenize("The cat is on the table!");
    assertEquals(5, tokens.size());
    assertEquals("the", tokens.get(0).getText());
    assertEquals("cat", tokens.get(1).getText());
    assertEquals("is on", tokens.get(2).getText());
    assertEquals("the", tokens.get(3).getText());
    assertEquals("table", tokens.get(4).getText());

    tokens = Token.tokenize("I love united states of america");
    assertEquals(3, tokens.size());
    assertEquals("I", tokens.get(0).getText());
    assertEquals("love", tokens.get(1).getText());
    assertEquals("united states of america", tokens.get(2).getText());
  }
}
