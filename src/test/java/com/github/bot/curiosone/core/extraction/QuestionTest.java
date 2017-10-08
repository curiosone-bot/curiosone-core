package com.github.bot.curiosone.core.extraction;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

import org.junit.Test;

public class QuestionTest {

  @Test
  public void testGetAnswer() {
    Sentence s = Sentence.extract(new Phrase("Who is Gogol?")).get(0);
    assertThat(Question.getAnswer(s, "")).isPresent();

    s = Sentence.extract(new Phrase("Where is Gogol?")).get(0);
    assertThat(Question.getAnswer(s, "")).isPresent();

    s = Sentence.extract(new Phrase("What is Gogol?")).get(0);
    assertThat(Question.getAnswer(s, "")).isPresent();

    s = Sentence.extract(new Phrase("Why is Gogol?")).get(0);
    assertThat(Question.getAnswer(s, "")).isNotPresent();

    s = Sentence.extract(new Phrase("When is Gogol?")).get(0);
    assertThat(Question.getAnswer(s, "")).isNotPresent();
  }
}
