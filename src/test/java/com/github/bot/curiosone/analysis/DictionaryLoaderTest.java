package com.github.bot.curiosone.core.analysis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.Test;

public class DictionaryLoaderTest {

  @Test
  public void testLoadDict() {
    assertThatCode(() -> DictionaryLoader.getInstance()).doesNotThrowAnyException();
  }

  @Test
  public void testGetScore() {
    DictionaryLoader dl = DictionaryLoader.getInstance();

    assertThat(dl.getScore("jostling")).isNegative();

    assertThat(dl.getScore("vermiculate")).isNegative();

    assertThat(dl.getScore("ashkhabad")).isZero();

    assertThat(dl.getScore("car")).isZero();

    assertThat(dl.getScore("happy")).isPositive();

    assertThat(dl.getScore("dog")).isNegative();

    assertThat(dl.getScore("cat")).isNegative();

    assertThat(dl.getScore("happy")).isPositive();
  }
}
