package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class CYKTest {
  @Test
  public void testInstantiation() {
    CYK classUnderTest = new CYK(
        Token.tokenize("what is an apple")
    );
    System.out.println(classUnderTest);

    classUnderTest = new CYK(
        Token.tokenize("what is a golden apple")
    );
    System.out.println(classUnderTest);
  }
}
