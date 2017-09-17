package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;


public class CYKTest {
  @Test
  public void testInstantiation() {
    CYK classUnderTest = new CYK(
      Token.extract("what is an apple").getTokens()
    );
    System.out.println(classUnderTest);

    classUnderTest = new CYK(Arrays.asList(
      Token.extract("what is a golden apple").getTokens()
    ));
    System.out.println(classUnderTest);
  }
}
