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
}
