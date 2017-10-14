package com.github.bot.curiosone.core.refinement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

import org.junit.Test;

public class WordVerbTest {

  @Test
  public void testInstantiation() {
    WordVerb wv = new WordVerb("Eat", TypeVerb.PresentP2);
    assertThat(wv instanceof Word).isTrue();
  }

  @Test
  public void testToString() {
    WordVerb wv = new WordVerb("Eaten", TypeVerb.ParticiplePastP1);
    assertThat(wv.toString()).isNotNull().isNotEmpty().isNotEqualTo("is").isNotEqualTo("am");
    assertThatCode(() -> new WordVerb("Eaten", TypeVerb.ParticiplePastP1).toString())
        .doesNotThrowAnyException();

    wv = new WordVerb("be", TypeVerb.PresentS1);
    assertThat(wv.toString()).isNotNull().isNotEmpty().isEqualTo("am");

    wv = new WordVerb("be", TypeVerb.PresentS3);
    assertThat(wv.toString()).isNotNull().isNotEmpty().isEqualTo("is");

    wv = new WordVerb("be", TypeVerb.PresentP3);
    assertThat(wv.toString()).isNotNull().isNotEmpty().isEqualTo("are");

    wv = new WordVerb("djshckjsdhckjsdck", TypeVerb.PresentS2);
    assertThat(wv.toString()).isNotNull().isNotEmpty().isEqualTo("djshckjsdhckjsdck");

    assertThatCode(() -> new WordVerb("thinkable", TypeVerb.Infinitive).toString())
        .doesNotThrowAnyException();
  }
}
