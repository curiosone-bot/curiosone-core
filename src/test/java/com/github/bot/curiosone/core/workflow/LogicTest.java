package com.github.bot.curiosone.core.workflow;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class LogicTest {

  @Test
  public void testAnswer() {
    Message msg;

    msg = Logic.talk(new Message("How old are you?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("What is an apple?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("Is a fruit", "apple"));
    System.out.println(msg);
  }
}
