package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class ConversationTest {
  @Test
  public void testGetAnswer() {
    String s;

    s = Conversation.getAnswer(new Phrase("i am Franco"));
    assertTrue(s.equals("I will set aside this information")
        || s.equals("I am glad to know it")
        || s.equals("This information is not new to me.."));

    s = Conversation.getAnswer(new Phrase("where do you live?"));
    assertTrue(s.equals("Right in front of you")
        || s.equals("Do I live?"));

    s = Conversation.getAnswer(new Phrase("tell me a joke"));
    assertTrue(s.length() > 1);

    s = Conversation.getAnswer(new Phrase("are you stupid?"));
    assertTrue(s.length() > 1);

    s = Conversation.getAnswer(new Phrase("are you a human?"));
    assertTrue(s.length() > 1);

    s = Conversation.getAnswer(new Phrase("are we friends?"));
    assertTrue(s.length() > 1);

    s = Conversation.getAnswer(new Phrase("i want to play a game"));
    assertTrue(s.length() > 1);

    s = Conversation.getAnswer(new Phrase("can you dance?"));
    assertTrue(s.length() > 1);

    s = Conversation.getAnswer(new Phrase("how it is going?"));
    assertTrue(s.length() > 1);

    s = Conversation.getAnswer(new Phrase("what's the weather like?"));
    assertTrue(s.equals(""));

    s = Conversation.getAnswer(new Phrase("The answer is fortytwo!"));
    assertTrue(s.equals(""));

    s = Conversation.getAnswer(new Phrase("123? 456!!"));
    assertTrue(s.equals(""));
  }
}
