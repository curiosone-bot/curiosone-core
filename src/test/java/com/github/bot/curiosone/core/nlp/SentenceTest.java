package com.github.bot.curiosone.core.nlp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SentenceTest {

  @Test
  public void testExtraction() {
    List<Phrase> phrases = Phrase.extract("What is an apple?");
    Phrase phrase = phrases.get(0);
    List<Sentence> sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();

    phrases = Phrase.extract("What is an apple? Where is Rome?");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();
    phrase = phrases.get(1);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();

    phrases = Phrase.extract("where is rome? Rome is in ITALY! Where is Italy?");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();
    phrase = phrases.get(1);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();
    phrase = phrases.get(2);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();

    phrases = Phrase.extract("what is your name?@@");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();

    phrases = Phrase.extract("@#@what is your name?");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();

    phrases = Phrase.extract("@#@what _is_ -your1 42name_?21");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();

    phrases = Phrase.extract("This, is a single sentence!");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();

    phrases = Phrase.extract("  42");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isEmpty();

    phrases = Phrase.extract(" this is a sentence ");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    assertThat(sentences).isNotEmpty();
  }

  @Test
  public void testRespects() {
    List<Phrase> phrases = Phrase.extract("What is an apple?");
    Phrase phrase = phrases.get(0);
    List<Sentence> sentences = Sentence.extract(phrase);
    Sentence sentence = sentences.get(0);
    assertThat(sentence.respect(POS.VP)).isTrue();
    assertThat(sentence.respect(POS.PRON, POS.V, POS.NP)).isTrue();
    assertThat(sentence.respect(POS.PRON, POS.V, POS.DET, POS.N)).isTrue();

    phrases = Phrase.extract("I live in Rome.");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    sentence = sentences.get(0);
    assertThat(sentence.respect(POS.VP)).isTrue();
    assertThat(sentence.respect(POS.PRON, POS.V)).isTrue();
    assertThat(sentence.respect(POS.PRON, POS.V, POS.N)).isTrue();

    phrases = Phrase.extract("This is a sentence.");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    sentence = sentences.get(0);
    assertThat(sentence.respect(POS.S)).isTrue();

    List<Word> words = sentence.get(POS.V);
    assertThat(words.size()).isGreaterThan(0);
  }

  @Test
  public void testParse() {
    List<Phrase> phrases = Phrase.extract("What is an apple?");
    Phrase phrase = phrases.get(0);
    List<Sentence> sentences = Sentence.extract(phrase);
    Sentence sentence = sentences.get(0);
    List<Word>[] parameters = sentence.parse(POS.PRON, POS.V, POS.NP);
    assertThat(parameters.length).isEqualTo(3);
    assertThat(parameters[0]).isNotEmpty();
    assertThat(parameters[1]).isNotEmpty();
    assertThat(parameters[2]).isNotEmpty();

    phrases = Phrase.extract("Where is an Rome?");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    sentence = sentences.get(0);
    parameters = sentence.parse(POS.PRON, POS.V, POS.NP);
    assertThat(parameters.length).isEqualTo(3);
    assertThat(parameters[0]).isNotEmpty();
    assertThat(parameters[1]).isNotEmpty();
    assertThat(parameters[2]).isNotEmpty();
  }

  @Test
  public void testDoesNotRespect() {
    List<Phrase> phrases = Phrase.extract("What is an apple?");
    Phrase phrase = phrases.get(0);
    List<Sentence> sentences = Sentence.extract(phrase);
    Sentence sentence = sentences.get(0);
    assertThat(sentence.respect(POS.ADJ)).isFalse();
    assertThat(sentence.respect(POS.PRON, POS.N)).isFalse();
    assertThat(sentence.respect(POS.PRON, POS.VP)).isFalse();

    phrases = Phrase.extract("I love pizza.");
    phrase = phrases.get(0);
    sentences = Sentence.extract(phrase);
    sentence = sentences.get(0);
    assertThat(sentence.respect(POS.UNKN)).isFalse();
  }

  @Test
  public void testEqualsReflexive() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple?"));
    Sentence s = ls.get(0);
    assertThat(s).isEqualTo(s);

    ls = Sentence.extract(new Phrase("An apple is a fruit."));
    s = ls.get(0);
    assertThat(s).isEqualTo(s);

    ls = Sentence.extract(new Phrase("Where is the sun?"));
    s = ls.get(0);
    assertThat(s).isEqualTo(s);
  }

  @Test
  public void testEqualsSymmetric() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple? What is an apple?"));
    Sentence s = ls.get(0);
    Sentence ss = ls.get(1);
    assertThat(s).isEqualTo(ss);
    assertThat(ss).isEqualTo(s);

    ls = Sentence.extract(new Phrase("I am a human. I am a human."));
    s = ls.get(0);
    ss = ls.get(1);
    assertThat(s).isEqualTo(ss);
    assertThat(ss).isEqualTo(s);

    ls = Sentence.extract(new Phrase("What is your name? What is your name?"));
    s = ls.get(0);
    ss = ls.get(1);
    assertThat(s).isEqualTo(ss);
    assertThat(ss).isEqualTo(s);
  }

  @Test
  public void testEqualsTransitive() {
    List<Sentence> ls = Sentence.extract(
        new Phrase("What is an apple? What is an apple? What is an apple?"));
    Sentence s = ls.get(0);
    Sentence ss = ls.get(1);
    Sentence sss = ls.get(2);
    assertThat(s).isEqualTo(ss);
    assertThat(ss).isEqualTo(sss);
    assertThat(sss).isEqualTo(s);

    ls = Sentence.extract(new Phrase("I am Dan. I am Dan. I am Dan."));
    s = ls.get(0);
    ss = ls.get(1);
    sss = ls.get(2);
    assertThat(s).isEqualTo(ss);
    assertThat(ss).isEqualTo(sss);
    assertThat(sss).isEqualTo(s);

    ls = Sentence.extract(new Phrase("I love swimming. I love swimming. I love swimming."));
    s = ls.get(0);
    ss = ls.get(1);
    sss = ls.get(2);
    assertThat(s).isEqualTo(ss);
    assertThat(ss).isEqualTo(sss);
    assertThat(sss).isEqualTo(s);
  }

  @Test
  public void testEqualsNullComparison() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple?"));
    Sentence s = ls.get(0);
    assertThat(s).isNotEqualTo(null);

    ls = Sentence.extract(new Phrase("Where is London?"));
    s = ls.get(0);
    assertThat(s).isNotEqualTo(null);

    ls = Sentence.extract(new Phrase("Who is here?"));
    s = ls.get(0);
    assertThat(s).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple?"));
    Sentence s = ls.get(0);
    assertThat(s).isNotEqualTo("Bla bla bla");

    ls = Sentence.extract(new Phrase("What is an apple?"));
    s = ls.get(0);
    assertThat(s).isNotEqualTo(new StringBuffer("Bla bla bla"));

    ls = Sentence.extract(new Phrase("What is an apple?"));
    s = ls.get(0);
    assertThat(s).isNotEqualTo(new Integer(42));
  }

  @Test
  public void testDoesNotEqual() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple?"));
    List<Sentence> lls = Sentence.extract(new Phrase("Where is an apple?"));
    Sentence s = ls.get(0);
    Sentence ss = lls.get(0);
    assertThat(s).isNotEqualTo(ss);

    ls = Sentence.extract(new Phrase("What is an apple?"));
    lls = Sentence.extract(new Phrase("An apple is a fruit"));
    s = ls.get(0);
    ss = lls.get(0);
    assertThat(s).isNotEqualTo(ss);
  }

  @Test
  public void testEquals() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple?"));
    List<Sentence> lls = Sentence.extract(new Phrase("what IS an apple?"));
    Sentence s = ls.get(0);
    Sentence ss = lls.get(0);
    assertThat(s).isEqualTo(ss);

    ls = Sentence.extract(new Phrase("where is New York?"));
    lls = Sentence.extract(new Phrase("where      is new York?"));
    s = ls.get(0);
    ss = lls.get(0);
    assertThat(s).isEqualTo(ss);

    ls = Sentence.extract(new Phrase("The Colosseum is in Rome"));
    lls = Sentence.extract(new Phrase("the COLOSSEUM is in ROME"));
    s = ls.get(0);
    ss = lls.get(0);
    assertThat(s).isEqualTo(ss);
  }

  @Test
  public void testHashCodeReflexive() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple?"));
    Sentence s = ls.get(0);
    assertThat(s.hashCode()).isEqualTo(s.hashCode());

    ls = Sentence.extract(new Phrase("Where is the cat?"));
    s = ls.get(0);
    assertThat(s.hashCode()).isEqualTo(s.hashCode());

    ls = Sentence.extract(new Phrase("Who is Robert?"));
    s = ls.get(0);
    assertThat(s.hashCode()).isEqualTo(s.hashCode());
  }

  @Test
  public void testHashCodeEqualsContract() {
    List<Sentence> ls = Sentence.extract(new Phrase("I was born in Rome"));
    List<Sentence> lss = Sentence.extract(new Phrase("I was BORN in ROME"));
    Sentence s = ls.get(0);
    Sentence ss = lss.get(0);
    assertThat(s.hashCode()).isEqualTo(s.hashCode());
    assertThat(s).isEqualTo(ss);

    ls = Sentence.extract(new Phrase("I was born in Rome! Rome is a city."));
    s = ls.get(0);
    ss = ls.get(1);
    assertThat(s.hashCode()).isEqualTo(s.hashCode());
    assertThat(s).isEqualTo(ss);
  }

  @Test
  public void testGetWord() {
    List<Word> lw = Sentence.extract(new Phrase("What is an apple?")).get(0).getWords();
    assertThat(lw.get(0).getText()).isEqualTo("what");
    assertThat(lw.get(1).getText()).isEqualTo("is");
    assertThat(lw.get(2).getText()).isEqualTo("an");
    assertThat(lw.get(3).getText()).isEqualTo("apple");

    lw = Sentence.extract(new Phrase("Rome is in Italy, Milan is in Italy too.")).get(0).getWords();
    assertThat(lw).hasSize(9);

    lw = Sentence.extract(new Phrase("London is in United Kingdom")).get(0).getWords();
    assertThat(lw).hasSize(4);
    assertThat(lw.get(3).getText()).isEqualTo("united kingdom");
  }

  @Test
  public void testIsQuestion() {
    List<Sentence> sentences = Sentence.extract(new Phrase("What is an apple?"));
    Sentence sentence = sentences.get(0);
    assertThat(sentence.isQuestion()).isTrue();

    sentences = Sentence.extract(new Phrase("What is an apple ?"));
    sentence = sentences.get(0);
    assertThat(sentence.isQuestion()).isTrue();

    sentences = Sentence.extract(new Phrase("where is Milan?"));
    sentence = sentences.get(0);
    assertThat(sentence.isQuestion()).isTrue();
  }

  @Test
  public void testIsNotQuestion() {
    List<Sentence> sentences = Sentence.extract(new Phrase("What is an apple?!"));
    Sentence sentence = sentences.get(0);
    assertThat(sentence.isQuestion()).isFalse();

    sentences = Sentence.extract(new Phrase("I love lasagna!"));
    sentence = sentences.get(0);
    assertThat(sentence.isQuestion()).isFalse();

    sentences = Sentence.extract(new Phrase("Geneva is in Switzerland"));
    sentence = sentences.get(0);
    assertThat(sentence.isQuestion()).isFalse();
  }
}
