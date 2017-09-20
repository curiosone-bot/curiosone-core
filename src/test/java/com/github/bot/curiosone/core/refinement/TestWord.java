package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestWord {

  Word w = new Word("queens", Part.NOUN);

  @Test
  public void testForm() {
    assertTrue(w.getForm().equals("queens"));
  }

  @Test
  public void testPart() {
    assertTrue(w.getPart().equals(Part.NOUN));
  }

  @Test
  public void testLemma() {
    assertTrue(w.getLemma().stream().findFirst().get().equals("queen"));
  }
}
