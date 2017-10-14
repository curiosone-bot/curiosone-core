package com.github.bot.curiosone.core.refinement;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

import org.junit.Test;

public class PhraseVerbTest {

  @Test
  public void testInstantiation() {
    WordVerb wv = new WordVerb("eat", TypeVerb.ConditionalP2);
    WordNoun wn = new WordNoun("cat", TypeNoun.Singular);
    PhraseNoun pn = new PhraseNoun(wn);
    PhraseVerb pv = new PhraseVerb(wv, pn);
    assertThat(pv instanceof Phrase).isTrue();

    pv = new PhraseVerb(wv);
    assertThat(pv instanceof Phrase).isTrue();
  }

  @Test
  public void testToString() {
    WordVerb wv = new WordVerb("distinguish", TypeVerb.PresentS1);
    WordNoun wn = new WordNoun("man", TypeNoun.Singular);
    PhraseNoun pn = new PhraseNoun(wn);
    PhraseVerb pv = new PhraseVerb(wv, pn);
    assertThat(pv.toString()).isNotNull().isNotEmpty().contains("distinguish").contains("man");

    wv = new WordVerb("", TypeVerb.PresentS3);
    wn = new WordNoun("cars", TypeNoun.Plural);
    pn = new PhraseNoun(wn);
    pv = new PhraseVerb(wv, pn);
    assertThat(pv.toString()).isNotNull().isNotEmpty().contains("cars");

    wv = new WordVerb("", TypeVerb.PresentS3);
    pv = new PhraseVerb(wv);
    assertThat(pv.toString()).isNotNull().isEmpty();
  }
}
