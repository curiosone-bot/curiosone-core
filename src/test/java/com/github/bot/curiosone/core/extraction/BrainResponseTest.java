package com.github.bot.curiosone.core.extraction;

public class BrainResponseTest {

  @Test
  public void testInstantiation() {
    assertTrue(new BrainResponse("Text", "Scope") instanceof BrainResponse);
  }
}
