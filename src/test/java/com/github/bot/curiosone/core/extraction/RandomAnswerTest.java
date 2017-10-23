package com.github.bot.curiosone.core.extraction;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.nlp.Phrase;

import org.junit.Test;

public class RandomAnswerTest {

  @Test
  public void testRandomAnswer() {
    Phrase p = new Phrase("youdont knowany single token of this very very long sentence");
    assertThat(RandomAnswer.getAnswer(p)).isIn(
        new BrainResponse("What a nice day to talk to a chatbot!", ""),
        new BrainResponse("Can you reformulate your sentence please?", ""),
        new BrainResponse("Tell me something interesting please.", ""));
  }
}
