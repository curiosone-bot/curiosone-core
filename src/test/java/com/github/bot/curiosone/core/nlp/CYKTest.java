package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;


public class CYKTest {
  @Test
  public void testInstantiation() {
    CYK classUnderTest = new CYK(Arrays.asList(
      new Token("what"),
      new Token("is"),
      new Token("an"),
      new Token("apple")
    ));
    System.out.println(classUnderTest);

    classUnderTest = new CYK(Arrays.asList(
      new Token("what"),
      new Token("is"),
      new Token("a"),
      new Token("golden"),
      new Token("apple")
    ));
    System.out.println(classUnderTest);
  }
}
