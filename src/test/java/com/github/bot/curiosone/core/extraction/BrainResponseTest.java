package com.github.bot.curiosone.core.extraction;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class BrainResponseTest {

  @Test
  public void testInstantiation() {
    assertTrue(new BrainResponse("Text", "Scope") instanceof BrainResponse);
  }

  @Test
  public void testGetMessage() {
    assertEquals("This is the message!",
        new BrainResponse("This is the message!", "This is the scope")
            .getMessage());
  }

  @Test
  public void testGetScope() {
    assertEquals("This is the scope",
        new BrainResponse("This is the message!", "This is the scope")
            .getScope());
  }

  @Test
  public void testToString() {
    assertEquals("Text(Scope)", new BrainResponse("Text", "Scope").toString());
  }

  @Test
  public void testEquals() {
    BrainResponse br = new BrainResponse("Text", "Scope");
    assertTrue(br.equals(br));
    assertFalse(br.equals(null));
    assertFalse(br.equals(new Integer(42)));
    BrainResponse brr = new BrainResponse("Text", "Scope");
    assertTrue(br.equals(brr));
    brr = new BrainResponse("text", "scope");
    assertFalse(br.equals(brr));
  }

  @Test
  public void testHashCode() {
    assertEquals(new BrainResponse("Happy", "Scope.of(Happy)").hashCode(),
        new BrainResponse("Happy", "Scope.of(Happy)").hashCode());

    assertNotEquals(new BrainResponse("Different", "BrainResponse").hashCode(),
        new BrainResponse("Test", "hashCode Scope"));
  }
}
