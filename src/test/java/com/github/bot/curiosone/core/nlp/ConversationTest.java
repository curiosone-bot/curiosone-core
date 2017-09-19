package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class ConversationTest {
  @Test
  public void testGetAnswer() {
    String s;

    s = Conversation.getAnswer(new Phrase("i am Franco"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("how are you?"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("where do you live?"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("tell me a joke"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("are you stupid?"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("are you a human?"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("are we friends?"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("i want to play a game"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("can you dance?"));
    assertTrue(s.length() > 0);
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("how it is going?"));
    assertTrue(s.length() > 0);
    System.out.println(s);
  }
}
