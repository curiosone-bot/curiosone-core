package com.github.bot.curiosone.core.workflow;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class MessageTest {

  @Test
  public void testInstance() {
    Message msg;

    msg = new Message("How old are you?", "test");
    assertEquals("How old are you?", msg.getMessage());
    assertEquals("test", msg.getScope());
  }

  @Test
  public void testInstantiation() {
    assertTrue(new Message("Text", "Scope") instanceof Message);
  }

  @Test
  public void testGetMessage() {
    assertEquals("This is the message!",
        new Message("This is the message!", "This is the scope")
            .getMessage());
  }

  @Test
  public void testGetScope() {
    assertEquals("This is the scope",
        new Message("This is the message!", "This is the scope")
            .getScope());
  }

  @Test
  public void testToString() {
    assertEquals("Text (Scope)", new Message("Text", "Scope").toString());
  }

  @Test
  public void testEquals() {
    Message br = new Message("Text", "Scope");
    assertTrue(br.equals(br));
    assertFalse(br.equals(null));
    assertFalse(br.equals(new Integer(42)));
    Message brr = new Message("Text", "Scope");
    assertTrue(br.equals(brr));
    brr = new Message("text", "scope");
    assertFalse(br.equals(brr));
  }

  @Test
  public void testHashCode() {
    assertEquals(new Message("Happy", "Scope.of(Happy)").hashCode(),
        new Message("Happy", "Scope.of(Happy)").hashCode());

    assertNotEquals(new Message("Different", "Message").hashCode(),
        new Message("Test", "hashCode Scope"));
  }
}
