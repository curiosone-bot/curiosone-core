package com.github.bot.curiosone.core.workflow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.Test;

public class MessageTest {

  @Test
  public void testNullConstructor() {
    assertThatCode(() -> new Message(null, "null", "value")).doesNotThrowAnyException();

    assertThatCode(() -> new Message(null, null, "value")).doesNotThrowAnyException();

    assertThatCode(() -> new Message(null, null, null)).doesNotThrowAnyException();

    Message m = new Message(null, null, null);
    assertThat(m.getMessage()).isEmpty();
    assertThat(m.getScope()).isEmpty();
    assertThat(m.getEmotion()).isEmpty();
  }

  @Test
  public void testGetMessage() {
    Message m = new Message("This is the message!", "This is the scope", "");
    assertThat(m.getMessage()).isEqualTo("This is the message!");

    m = new Message("", "This is the scope", "");
    assertThat(m.getMessage()).isEmpty();

    m = new Message("  ..  ", "This is the scope", "");
    assertThat(m.getMessage()).isEqualTo("  ..  ");
  }

  @Test
  public void testGetScope() {
    Message m = new Message("This is the message!", "This is the scope", "");
    assertThat(m.getScope()).isEqualTo("This is the scope");

    m = new Message("This is the message!", "  ", "");
    assertThat(m.getScope()).isEqualTo("  ");

    m = new Message("This is the message!", "", "");
    assertThat(m.getScope()).isEmpty();
  }

  @Test
  public void testGetEmotion() {
    Message m  = new Message("I am a message with an emotion!", "emotion", "happy");
    assertThat(m.getEmotion()).isEqualTo("happy");

    m = new Message("Just a little sad :(", "sad", "sad");
    assertThat(m.getEmotion()).isEqualTo("sad");

    m = new Message("ANGRY, ANGRY!", ":/", "angry");
    assertThat(m.getEmotion()).isEqualTo("angry");

    m = new Message("ANGRY, ANGRY!", ":/", "");
    assertThat(m.getEmotion()).isEmpty();
  }

  @Test
  public void testToString() {
    Message m = new Message("Text", "Scope", "Emotion");
    assertThat(m.toString()).isEqualTo("Text (Scope)[Emotion]");

    m = new Message("I am a human", "human", "");
    assertThat(m.toString()).isEqualTo("I am a human (human)[]");

    m = new Message("Do you live here?", "here?", "");
    assertThat(m.toString()).isEqualTo("Do you live here? (here?)[]");

    m = new Message("", "", "");
    assertThat(m.toString()).isEqualTo(" ()[]");
  }

  @Test
  public void testEqualsReflexive() {
    Message m = new Message("Text", "Scope", "");
    assertThat(m).isEqualTo(m);

    m = new Message("", "", "");
    assertThat(m).isEqualTo(m);

    m = new Message(" ", " ", "");
    assertThat(m).isEqualTo(m);
  }

  @Test
  public void testEqualsSymmetric() {
    Message m = new Message("Text", "Scope", "Emotion");
    Message mm = new Message("Text", "Scope", "Emotion");
    assertThat(m).isEqualTo(mm);
    assertThat(mm).isEqualTo(m);

    m = new Message(" ", " ", "");
    mm = new Message(" ", " ", "");
    assertThat(m).isEqualTo(mm);
    assertThat(mm).isEqualTo(m);

    m = new Message("a", "b", "");
    mm = new Message("c", "d", "");
    assertThat(m).isNotEqualTo(mm);
    assertThat(mm).isNotEqualTo(m);
  }

  @Test
  public void testEqualsTransitive() {
    Message m = new Message("Text", "Scope", "Emotion");
    Message mm = new Message("Text", "Scope", "Emotion");
    Message mmm = new Message("Text", "Scope", "Emotion");
    assertThat(m).isEqualTo(mm);
    assertThat(mm).isEqualTo(mmm);
    assertThat(mmm).isEqualTo(m);

    m = new Message("", "", "");
    mm = new Message("", "", "");
    mmm = new Message("", "", "");
    assertThat(m).isEqualTo(mm);
    assertThat(mm).isEqualTo(mmm);
    assertThat(mmm).isEqualTo(m);

    m = new Message("1234", "numbers", "");
    mm = new Message("1234", "numbers", "");
    mmm = new Message("1234", "numbers", "");
    assertThat(m).isEqualTo(mm);
    assertThat(mm).isEqualTo(mmm);
    assertThat(mmm).isEqualTo(m);
  }

  @Test
  public void testEqualsOtherObj() {
    Message m = new Message("Text", "Scope", "Emotion");

    assertThat(m).isNotEqualTo("TEXT - SCOPE");

    assertThat(m).isNotEqualTo(new StringBuffer("  , , "));

    assertThat(m).isNotEqualTo(42);
  }

  @Test
  public void testHashCodeReflexive() {
    Message m = new Message("Text", "Scope", "");
    assertThat(m.hashCode()).isEqualTo(m.hashCode());

    m = new Message("I am eating an apple", "apple", "");
    assertThat(m.hashCode()).isEqualTo(m.hashCode());
  }

  @Test
  public void testEqualsNullComparison() {
    Message m = new Message("Text", "Scope", "");
    assertThat(m).isNotEqualTo(null);

    m = new Message("", "", "");
    assertThat(m).isNotEqualTo(null);

    m = new Message("This is my smartphone", "smartphone", "");
    assertThat(m).isNotEqualTo(null);
  }

  @Test
  public void testHashCodeEqualsContract() {
    Message m = new Message("Text", "Scope", "");
    Message mm = new Message("Text", "Scope", "");
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());
    assertThat(m).isEqualTo(mm);

    m = new Message("I am eating an apple", "apple", "");
    mm = new Message("I am EATING an APPLE", "APPLE", "");
    assertThat(m.hashCode()).isNotEqualTo(mm.hashCode());
    assertThat(m).isNotEqualTo(mm);
  }
}
