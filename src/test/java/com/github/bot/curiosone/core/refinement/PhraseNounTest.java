package com.github.bot.curiosone.core.refinement;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

import org.junit.Test;

public class PhraseNounTest {

  @Test
  public void testInstantiation() {
    WordNoun wn = new WordNoun("love", TypeNoun.Name);
    PhraseNoun pn = new PhraseNoun(wn);
    assertThat(pn instanceof Phrase).isTrue();
  }

  @Test
  public void testToString() {
    WordNoun wn = new WordNoun("joy", TypeNoun.Name);
    PhraseNoun pn = new PhraseNoun(wn);
    assertThat(pn.toString()).isNotNull().isNotEmpty().containsIgnoringCase("joy");

    wn = new WordNoun("", TypeNoun.Name);
    pn = new PhraseNoun(wn);
    assertThat(pn.toString()).isNotNull().isEmpty();

    wn = new WordNoun("42", TypeNoun.Singular);
    pn = new PhraseNoun(wn);
    assertThat(pn.toString()).isNotNull().isNotEmpty().contains("42");

    wn = new WordNoun("ANSWER", TypeNoun.Plural);
    pn = new PhraseNoun(wn);
    assertThat(pn.toString()).isNotNull().isNotEmpty().contains("ANSWER");
  }
}
