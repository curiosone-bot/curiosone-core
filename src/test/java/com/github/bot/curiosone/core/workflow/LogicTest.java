package com.github.bot.curiosone.core.workflow;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class LogicTest {

  @Test
  public void testAnswer() throws IOException {
    Message msg = Logic.talk(new Message("", ""));
    assertThat(msg.getMessage()).isEqualTo("Sorry my head hurts, what were we talking about?");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("How old are you?", ""));
    assertThat(msg.getMessage())
        .isNotEmpty()
        .isNotEqualTo("Sorry my head hurts, what were we talking about?");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("What is apple?", ""));
    assertThat(msg.getMessage()).contains("For what I know, apple is ");
    assertThat(msg.getScope()).isEqualTo("apple");

    msg = Logic.talk(new Message("What is a red apple?", ""));
    assertThat(msg.getMessage()).contains("For what I know, apple is ");
    assertThat(msg.getScope()).isEqualTo("apple");

    msg = Logic.talk(new Message("Is a fruit", "apple?"));
    assertThat(msg.getMessage()).isEqualTo("I already knew that apple is a fruit.");
    assertThat(msg.getScope()).isEqualTo("fruit");

    msg = Logic.talk(new Message("It is a fruit", "apple"));
    assertThat(msg.getMessage()).isEqualTo("Mhh! What is a fruit?");
    assertThat(msg.getScope()).isEqualTo("fruit?");

    msg = Logic.talk(new Message("The apple is a fruit", "apple"));
    assertThat(msg.getMessage()).isEqualTo("Mhh! What is a fruit?");
    assertThat(msg.getScope()).isEqualTo("fruit?");

    msg = Logic.talk(new Message("Who is Roberto Navigli?", ""));
    assertThat(msg.getMessage()).isEqualTo("I do not know who is roberto! Do you?");
    assertThat(msg.getScope()).isEqualTo("roberto?");

    msg = Logic.talk(new Message("Who is Roberto?", ""));
    assertThat(msg.getMessage()).isEqualTo("I do not know who is roberto! Do you?");
    assertThat(msg.getScope()).isEqualTo("roberto?");

    msg = Logic.talk(new Message("Who is Navigli?", ""));
    assertThat(msg.getMessage()).isEqualTo("I do not know who is navigli! Do you?");
    assertThat(msg.getScope()).isEqualTo("navigli?");

    msg = Logic.talk(new Message("Where is Europe?", ""));
    assertThat(msg.getMessage()).isEqualTo("I do not know where is europe! Do you?");
    assertThat(msg.getScope()).isEqualTo("europe?");

    msg = Logic.talk(new Message("Where is Rome?", ""));
    assertThat(msg.getMessage()).isEqualTo("I do not know where is rome! Do you?");
    assertThat(msg.getScope()).isEqualTo("rome?");

    msg = Logic.talk(new Message("Where are United States of America?", ""));
    assertThat(msg.getMessage())
        .isEqualTo("I do not know where is united states of america! Do you?");
    assertThat(msg.getScope()).isEqualTo("united states of america?");

    msg = Logic.talk(new Message("Hi", ""));
    assertThat(msg.getMessage()).isIn("Hi there!", "Hi.", "Hello!");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("Hello", ""));
    assertThat(msg.getMessage()).isIn("Hi there!", "Hi.", "Hello!");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("Are we friends?", ""));
    assertThat(msg.getMessage())
        .isIn("Facebook teached me what a friend is", "I really want a friend.");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("Are you busy?", ""));
    assertThat(msg.getMessage())
        .isIn("I am not busy.", "I can not be busy, I am always ready to answer your questions.");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("Are you a human?", ""));
    assertThat(msg.getMessage())
        .isIn("No I am not human.", "Humans have blood, I have 101010101011011.");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("hsxzk vhdskjv,mcjzklk", ""));
    assertThat(msg.getMessage())
        .isIn("I think you should speak english", "PLEASE, speak english!", "Are you a robot too?");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("Nel mezzo del cammin di nostra vita...", ""));
    assertThat(msg.getMessage())
        .isIn("I think you should speak english", "PLEASE, speak english!", "Are you a robot too?");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("ImFoolingYou!", ""));
    assertThat(msg.getMessage()).contains("ImFoolingYou!");
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("Robots are weirdo", ""));
    assertThat(msg.getMessage())
        .isIn(
        "I do not like your way of talking.", "You should speak more politely.",
        "Why are you talking like this? Are you a little kid?","Come back when you grow up."
      );
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("What is dog?", ""));
    assertThat(msg.getMessage()).contains("For what I know, dog is a");
    assertThat(msg.getScope()).isEqualTo("dog");

    msg = Logic.talk(new Message("What is a dog?", ""));
    assertThat(msg.getMessage()).contains("For what I know, dog is a");
    assertThat(msg.getScope()).isEqualTo("dog");
  }
}
