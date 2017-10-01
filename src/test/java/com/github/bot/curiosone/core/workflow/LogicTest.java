package com.github.bot.curiosone.core.workflow;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

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
    assertThat(msg.getMessage()).containsIgnoringCase("apple");
    assertThat(msg.getScope()).containsIgnoringCase("apple");

    msg = Logic.talk(new Message("What is a red apple?", ""));
    assertThat(msg.getMessage()).containsIgnoringCase("apple");
    assertThat(msg.getScope()).containsIgnoringCase("apple");

    msg = Logic.talk(new Message("Is a fruit", "apple?"));
    assertThat(msg.getMessage()).containsIgnoringCase("fruit");
    assertThat(msg.getScope()).containsIgnoringCase("fruit");

    msg = Logic.talk(new Message("It is a fruit", "apple"));
    assertThat(msg.getMessage()).containsIgnoringCase("fruit");
    assertThat(msg.getScope()).containsIgnoringCase("fruit");

    msg = Logic.talk(new Message("The apple is a fruit", "apple"));
    assertThat(msg.getMessage()).containsIgnoringCase("fruit");
    assertThat(msg.getScope()).containsIgnoringCase("fruit");

    msg = Logic.talk(new Message("Who is Roberto Navigli?", ""));
    assertThat(msg.getMessage()).isNotNull().isNotEmpty();
    assertThat(msg.getScope()).isNotNull();

    msg = Logic.talk(new Message("Who is Roberto?", ""));
    assertThat(msg.getMessage()).containsIgnoringCase("roberto");
    assertThat(msg.getScope()).containsIgnoringCase("roberto");

    msg = Logic.talk(new Message("Who is Navigli?", ""));
    assertThat(msg.getMessage()).containsIgnoringCase("Navigli");
    assertThat(msg.getScope()).containsIgnoringCase("Navigli");

    msg = Logic.talk(new Message("Where is Europe?", ""));
    assertThat(msg.getMessage()).containsIgnoringCase("europe");
    assertThat(msg.getScope()).containsIgnoringCase("europe");

    msg = Logic.talk(new Message("Where is Rome?", ""));
    assertThat(msg.getMessage()).containsIgnoringCase("Rome");
    assertThat(msg.getScope()).containsIgnoringCase("Rome");

    msg = Logic.talk(new Message("Where are United States of America?", ""));
    assertThat(msg.getMessage()).isNotNull().isNotEmpty();
    assertThat(msg.getScope()).isNotNull();

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
    assertThat(msg.getMessage()).isNotNull().isNotEmpty();
    assertThat(msg.getScope()).isNotNull();

    msg = Logic.talk(new Message("Robots are weirdo", ""));
    assertThat(msg.getMessage())
        .isIn(
        "I do not like your way of talking.", "You should speak more politely.",
        "Why are you talking like this? Are you a little kid?","Come back when you grow up."
      );
    assertThat(msg.getScope()).isEmpty();

    msg = Logic.talk(new Message("What is dog?", ""));
    assertThat(msg.getMessage()).containsIgnoringCase("dog");
    assertThat(msg.getScope()).containsIgnoringCase("dog");

    msg = Logic.talk(new Message("What is a dog?", ""));
    assertThat(msg.getMessage()).containsIgnoringCase("dog");
    assertThat(msg.getScope()).containsIgnoringCase("dog");
  }
}
