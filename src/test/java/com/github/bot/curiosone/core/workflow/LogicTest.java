package com.github.bot.curiosone.core.workflow;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class LogicTest {

  @Test
  public void testAnswer() throws IOException {
    Message msg;

    msg = Logic.talk(new Message("", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("How old are you?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("What is apple?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("What is an apple?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("What is a red apple?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("Is a fruit", "apple?"));
    System.out.println(msg);

    msg = Logic.talk(new Message("It is a fruit", "apple"));
    System.out.println(msg);

    msg = Logic.talk(new Message("The apple is a fruit", "apple"));
    System.out.println(msg);

    msg = Logic.talk(new Message("Who is Roberto Navigli?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("Who is Roberto?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("Who is Navigli?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("Where is Europe?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("Where is Rome?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("Where are United States of America?", ""));
    System.out.println(msg);

    msg = Logic.talk(new Message("What is a bad?+", ""));
    System.out.println(msg);
  }
}
