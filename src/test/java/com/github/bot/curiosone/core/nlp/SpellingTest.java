package com.github.bot.curiosone.core.nlp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SpellingTest {

  @Test
  public void testGetInstance() {
    Spelling s = Spelling.getInstance();
    assertThat(s instanceof Spelling).isTrue();
  }

  @Test
  public void testCorrect() {
    Spelling s = Spelling.getInstance();
    assertThat(s.correct("hello")).isEqualTo("hello");

    assertThat(s.correct("hel lo")).isEqualTo("hello");

    assertThat(s.correct("hello,")).isEqualTo("hello");

    assertThat(s.correct(",hello")).isEqualTo("hello");

    assertThat(s.correct("helllo")).isEqualTo("hello");

    assertThat(s.correct("cann")).isEqualTo("can");

    assertThat(s.correct("ca.n")).isEqualTo("can");

    assertThat(s.correct("ca3n")).isEqualTo("can");

    assertThat(s.correct("c4n")).isEqualTo("can");

    assertThat(s.correct("can1")).isEqualTo("can");

    assertThat(s.correct("!can")).isEqualTo("can");

    assertThat(s.correct("united kingdom")).isEqualTo("united kingdom");
  }
}
