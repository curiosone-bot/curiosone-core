package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.Test;

public class ParseTableTest {
  @Test
  public void testInstantiation() {
    ParseTable classUnderTest = new ParseTable(
        Token.tokenize("what is an apple")
    );
    System.out.println(classUnderTest);

    classUnderTest = new ParseTable(
        Token.tokenize("what is a golden apple")
    );
    System.out.println(classUnderTest);
  }

  @Test
  public void testGetCykTableTestOutOfBounds() {
    ParseTable pt = new ParseTable(Token.tokenize("out of bound"));

    // y < 0
    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> pt.get(1, -1))
        .withMessage("Position outside the table [1, -1]");

    // y > size
    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> pt.get(1, 42))
        .withMessage("Position outside the table [1, 42]");

    // x < 0
    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> pt.get(-1, 2))
        .withMessage("Position outside the table [-1, 2]");

    // x > y
    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> pt.get(42, 2))
        .withMessage("Position outside the table [42, 2]");
  }
}
