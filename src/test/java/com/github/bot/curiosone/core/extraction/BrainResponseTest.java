package com.github.bot.curiosone.core.extraction;

import static org.assertj.core.api.Assertions.assertThat;
// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class BrainResponseTest {

  @Test
  public void testGetMessage() {
    BrainResponse br = new BrainResponse("This is the message!", "This is the scope");
    assertThat(br.getMessage()).isEqualTo("This is the message!");

    br = new BrainResponse("", "This is the scope");
    assertThat(br.getMessage()).isEmpty();

    br = new BrainResponse("  ..  ", "This is the scope");
    assertThat(br.getMessage()).isEqualTo("  ..  ");
  }

  @Test
  public void testGetScope() {
    BrainResponse br = new BrainResponse("This is the message!", "This is the scope");
    assertThat(br.getScope()).isEqualTo("This is the scope");

    br = new BrainResponse("This is the message!", "  ");
    assertThat(br.getScope()).isEqualTo("  ");

    br = new BrainResponse("This is the message!", "");
    assertThat(br.getScope()).isEmpty();
  }

  @Test
  public void testToString() {
    BrainResponse br = new BrainResponse("Text", "Scope");
    assertThat(br.toString()).isEqualTo("Text(Scope)");

    br = new BrainResponse("I am a human", "human");
    assertThat(br.toString()).isEqualTo("I am a human(human)");

    br = new BrainResponse("Do you live here?", "here?");
    assertThat(br.toString()).isEqualTo("Do you live here?(here?)");

    br = new BrainResponse("", "");
    assertThat(br.toString()).isEqualTo("()");
  }

  @Test
  public void testEqualsReflexive() {
    BrainResponse br = new BrainResponse("Text", "Scope");
    assertThat(br).isEqualTo(br);

    br = new BrainResponse("", "");
    assertThat(br).isEqualTo(br);

    br = new BrainResponse(" ", " ");
    assertThat(br).isEqualTo(br);
  }

  @Test
  public void testEqualsSymmetric() {
    BrainResponse br = new BrainResponse("Text", "Scope");
    BrainResponse brr = new BrainResponse("Text", "Scope");
    assertThat(br).isEqualTo(brr);
    assertThat(brr).isEqualTo(br);

    br = new BrainResponse(" ", " ");
    brr = new BrainResponse(" ", " ");
    assertThat(br).isEqualTo(brr);
    assertThat(brr).isEqualTo(br);

    br = new BrainResponse("a", "b");
    brr = new BrainResponse("c", "d");
    assertThat(br).isNotEqualTo(brr);
    assertThat(brr).isNotEqualTo(br);
  }

  @Test
  public void testEqualsTransitive() {
    BrainResponse br = new BrainResponse("Text", "Scope");
    BrainResponse brr = new BrainResponse("Text", "Scope");
    BrainResponse brrr = new BrainResponse("Text", "Scope");
    assertThat(br).isEqualTo(brr);
    assertThat(brr).isEqualTo(brrr);
    assertThat(brrr).isEqualTo(br);

    br = new BrainResponse("", "");
    brr = new BrainResponse("", "");
    brrr = new BrainResponse("", "");
    assertThat(br).isEqualTo(brr);
    assertThat(brr).isEqualTo(brrr);
    assertThat(brrr).isEqualTo(br);

    br = new BrainResponse("1234", "numbers");
    brr = new BrainResponse("1234", "numbers");
    brrr = new BrainResponse("1234", "numbers");
    assertThat(br).isEqualTo(brr);
    assertThat(brr).isEqualTo(brrr);
    assertThat(brrr).isEqualTo(br);
  }

  @Test
  public void testEqualsNullComparison() {
    BrainResponse br = new BrainResponse("Text", "Scope");
    assertThat(br).isNotEqualTo(null);

    br = new BrainResponse("", "");
    assertThat(br).isNotEqualTo(null);

    br = new BrainResponse("This is my smartphone", "smartphone");
    assertThat(br).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    BrainResponse br = new BrainResponse("Text", "Scope");

    assertThat(br).isNotEqualTo("TEXT - SCOPE");

    assertThat(br).isNotEqualTo(new StringBuffer("  , , "));

    assertThat(br).isNotEqualTo(42);
  }

  @Test
  public void testHashCodeReflexive() {
    BrainResponse br = new BrainResponse("Text", "Scope");
    assertThat(br.hashCode()).isEqualTo(br.hashCode());

    br = new BrainResponse("I am eating an apple", "apple");
    assertThat(br.hashCode()).isEqualTo(br.hashCode());
  }

  @Test
  public void testHashCodeEqualsContract() {
    BrainResponse br = new BrainResponse("Text", "Scope");
    BrainResponse brr = new BrainResponse("Text", "Scope");
    assertThat(br.hashCode()).isEqualTo(brr.hashCode());
    assertThat(br).isEqualTo(brr);

    br = new BrainResponse("I am eating an apple", "apple");
    brr = new BrainResponse("I am EATING an APPLE", "APPLE");
    assertThat(br.hashCode()).isNotEqualTo(brr.hashCode());
    assertThat(br).isNotEqualTo(brr);
  }
}
