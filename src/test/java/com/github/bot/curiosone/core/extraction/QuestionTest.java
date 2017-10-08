package com.github.bot.curiosone.core.extraction;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.knowledge.SemanticNetwork;
import com.github.bot.curiosone.core.knowledge.SemanticRelationType;
import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

import java.io.IOException;

import org.junit.Test;

public class QuestionTest {

  @Test
  public void testGetAnswer() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();
    sn.learn("abcdedfgh", SemanticRelationType.IS_A, "alphabet");

    Sentence s = Sentence.extract(new Phrase("Who is abcdedfgh?")).get(0);
    assertThat(Question.getAnswer(s, "")).isPresent();

    s = Sentence.extract(new Phrase("Where is abcdedfgh?")).get(0);
    assertThat(Question.getAnswer(s, "")).isPresent();

    s = Sentence.extract(new Phrase("Who is Gogol?")).get(0);
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
