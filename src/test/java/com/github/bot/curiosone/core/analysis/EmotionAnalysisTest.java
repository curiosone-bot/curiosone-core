package com.github.bot.curiosone.core.analysis;

import static com.github.bot.curiosone.core.analysis.EmotionAnalysis.getEmotion;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.nlp.Phrase;

import org.junit.Test;

public class EmotionAnalysisTest {

  @Test
  public void testGetEmotion() {
    Phrase p = new Phrase("Today, I am very happy!");
    assertThat(getEmotion(p)).isEqualTo("happy");

    p = new Phrase("This sky is beautiful");
    assertThat(getEmotion(p)).isEqualTo("happy");

    p = new Phrase("I love gifts, they give joy and love...");
    assertThat(getEmotion(p)).isEqualTo("happy");

    p = new Phrase("I am angry, I hate cruelty");
    assertThat(getEmotion(p)).isEqualTo("sad");

    p = new Phrase("hate the extant bad");
    assertThat(getEmotion(p)).isEqualTo("angry");

    p = new Phrase("Are you on vacation?");
    assertThat(getEmotion(p)).isEqualTo("happy");

    p = new Phrase("Yes, this is my yacht");
    assertThat(getEmotion(p)).isEqualTo("happy");

    p = new Phrase("Do not eat that pizza...");
    assertThat(getEmotion(p)).isEqualTo("sad");
  }
}
