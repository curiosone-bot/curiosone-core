package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestWord {

  @Test
  public void testForm() {
    assertTrue(new Word("queens", Part.NOUN).getForm().equals("queens"));
  }

  @Test
  public void testPart() {
    assertTrue(new Word("queens", Part.NOUN).getPart().equals(Part.NOUN));
  }

  @Test
  public void testLemma() {
    assertTrue(new Word("queens", Part.NOUN).getLemma().stream()
        .findFirst().get().equals("queen"));
  }
}
