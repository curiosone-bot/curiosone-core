package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class WordTest {
  @Test
  public void testInstantiation() {
    Word classUnderTest = new Word("colors", "color", new HashSet<>());
    System.out.println(classUnderTest);
  }
}
