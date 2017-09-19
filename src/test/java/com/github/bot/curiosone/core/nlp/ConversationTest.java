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
    System.out.println(s);

    s = Conversation.getAnswer(new Phrase("where do you live?"));
    assertTrue(s.equals("Right in front of you")
        || s.equals("Do I live?"));

    s = Conversation.getAnswer(new Phrase("tell me a joke"));
    assertTrue(
        s.equals("Can a kangaroo jump higher than a house? Of course, "
        + "a house does not jump at all.")
        || s.equals("What do skeletons say before eating?Bone Appetit!"));

    s = Conversation.getAnswer(new Phrase("are you stupid?"));
    assertTrue(s.equals("Not so much") || s.equals("Siri more than me"));

    s = Conversation.getAnswer(new Phrase("are you a human?"));
    assertTrue(s.equals("No I am not")
        || s.equals("Humans have blood, I have 101010101011011"));

    s = Conversation.getAnswer(new Phrase("are we friends?"));
    assertTrue(s.equals("Facebook teached me what a friend is")
        || s.equals("I really want a friend"));

    s = Conversation.getAnswer(new Phrase("i want to play a game"));
    assertTrue(s.equals("I have different games in my brain, if you want to play,"
        + "just click on the top-left side button on the screen"));

    s = Conversation.getAnswer(new Phrase("can you dance?"));
    assertTrue(s.equals("I never learned before, it is very hard for robots")
        || s.equals("No I am sorry"));

    s = Conversation.getAnswer(new Phrase("how it is going?"));
    assertTrue(s.equals("Ow.. I do not know")
        || s.equals("Not so bad, what about you?"));

    s = Conversation.getAnswer(new Phrase("what's the weather like?"));
    assertTrue(s.equals(""));
  }
}
