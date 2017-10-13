package com.github.bot.curiosone.core.refinement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SentenceTest {

  @Test
  public void testGetMainClause() {
    WordNoun wn = new WordNoun("word", TypeNoun.Singular);
    PhraseNoun pn = new PhraseNoun(wn);
    WordVerb wv = new WordVerb("is", TypeVerb.PresentS3);
    PhraseVerb pv = new PhraseVerb(wv);
    ClauseMain cm = new ClauseMain(pn, pv);
    Sentence s = new Sentence(SentenceType.Question, cm);
    assertThat(s.getMainClause().toString()).isEqualTo(cm.toString());

    wn = new WordNoun("rings", TypeNoun.Plural);
    pn = new PhraseNoun(wn);
    wv = new WordVerb("are", TypeVerb.PresentP3);
    pv = new PhraseVerb(wv);
    cm = new ClauseMain(pn, pv);
    s = new Sentence(SentenceType.Question, cm);
    assertThat(s.getMainClause()).isNotNull();
  }

  @Test
  public void testToString() {
    WordNoun wn = new WordNoun("planets", TypeNoun.Singular);
    PhraseNoun pn = new PhraseNoun(wn);
    WordVerb wv = new WordVerb("were", TypeVerb.SimplePastP3);
    PhraseVerb pv = new PhraseVerb(wv);
    ClauseMain cm = new ClauseMain(pn, pv);
    Sentence s = new Sentence(SentenceType.Question, cm);
    assertThat(s.toString()).isNotNull().contains("planets").contains("were").contains("?");

    wn = new WordNoun("John", TypeNoun.Singular);
    pn = new PhraseNoun(wn);
    wv = new WordVerb("should", TypeVerb.ConditionalS3);
    pv = new PhraseVerb(wv);
    cm = new ClauseMain(pn, pv);
    s = new Sentence(SentenceType.Answer, cm);
    assertThat(s.toString()).isNotNull().contains("John").contains("should").contains(".");

    wn = new WordNoun("", TypeNoun.Singular);
    pn = new PhraseNoun(wn);
    wv = new WordVerb("", TypeVerb.ConditionalS3);
    pv = new PhraseVerb(wv);
    cm = new ClauseMain(pn, pv);
    s = new Sentence(SentenceType.Answer, cm);
    assertThat(s.toString()).isNotNull().isNotEmpty();
  }
}
