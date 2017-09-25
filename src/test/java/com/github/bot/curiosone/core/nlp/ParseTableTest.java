package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ParseTableTest {
  @Test
  public void testInstantiation() {
    ParseTable classUnderTest = new ParseTable(
        Token.tokenize("what is an apple")
    );
    // System.out.println(classUnderTest);

    classUnderTest = new ParseTable(
        Token.tokenize("what is a golden apple")
    );
    // System.out.println(classUnderTest);
  }
}
