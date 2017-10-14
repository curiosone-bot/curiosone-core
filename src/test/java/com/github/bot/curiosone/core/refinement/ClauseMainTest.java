package com.github.bot.curiosone.core.refinement;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.refinement.interfaces.Clause;

import org.junit.Test;

public class ClauseMainTest {

  @Test
  public void testInstantiation() {
    WordNoun wn = new WordNoun("word", TypeNoun.Singular);
    PhraseNoun pn = new PhraseNoun(wn);
    WordVerb wv = new WordVerb("is", TypeVerb.PresentS3);
    PhraseVerb pv = new PhraseVerb(wv);
    ClauseMain cm = new ClauseMain(pn, pv);
    assertThat(cm instanceof Clause).isTrue();
  }

  @Test
  public void testGetSubject() {
    WordNoun wn = new WordNoun("word", TypeNoun.Singular);
    PhraseNoun pn = new PhraseNoun(wn);
    WordVerb wv = new WordVerb("is", TypeVerb.PresentS3);
    PhraseVerb pv = new PhraseVerb(wv);
    ClauseMain cm = new ClauseMain(pn, pv);
    assertThat(cm.getSubject().toString()).isEqualTo("a word");

    wn = new WordNoun("WORD", TypeNoun.Singular);
    pn = new PhraseNoun(wn);
    wv = new WordVerb("is", TypeVerb.PresentS3);
    pv = new PhraseVerb(wv);
    cm = new ClauseMain(pn, pv);
    assertThat(cm.getSubject().toString()).isEqualTo("a WORD");
  }

  @Test
  public void testGetPredicate() {
    WordNoun wn = new WordNoun("word", TypeNoun.Singular);
    PhraseNoun pn = new PhraseNoun(wn);
    WordVerb wv = new WordVerb("is", TypeVerb.PresentS3);
    PhraseVerb pv = new PhraseVerb(wv);
    ClauseMain cm = new ClauseMain(pn, pv);
    assertThat(cm.getPredicate().toString()).isEqualTo("is");

    wn = new WordNoun("WORD", TypeNoun.Singular);
    pn = new PhraseNoun(wn);
    wv = new WordVerb("IS", TypeVerb.PresentS3);
    pv = new PhraseVerb(wv);
    cm = new ClauseMain(pn, pv);
    assertThat(cm.getPredicate().toString()).isEqualTo("IS");
  }

  @Test
  public void testToString() {
    WordNoun wn = new WordNoun("Animal", TypeNoun.Plural);
    PhraseNoun pn = new PhraseNoun(wn);
    WordVerb wv = new WordVerb("have", TypeVerb.PresentP3);
    PhraseVerb pv = new PhraseVerb(wv);
    ClauseMain cm = new ClauseMain(pn, pv);
    assertThat(cm.toString()).isEqualTo("Animals have");
  }
}
