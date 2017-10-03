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

    assertThat(dl.getScore("jostling")).isEqualTo(-0.125);

    assertThat(dl.getScore("vermiculate")).isEqualTo(-0.041666666666666664);

    assertThat(dl.getScore("ashkhabad")).isEqualTo(0.0);

    assertThat(dl.getScore("car")).isEqualTo(0.0);

    assertThat(dl.getScore("happy")).isEqualTo(0.5625);

    assertThat(dl.getScore("dog")).isEqualTo(-0.1875);

    assertThat(dl.getScore("cat")).isEqualTo(-0.0125);
  }
}
