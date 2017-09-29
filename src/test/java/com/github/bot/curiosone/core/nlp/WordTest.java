package com.github.bot.curiosone.core.nlp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class WordTest {
  @Test
  public void testInstantiation() {
    Word w = new Word("colors", "color", new HashSet<>());
    assertThat(w instanceof Word).isTrue();
  }

  @Test
  public void testToString() {
    Word w = new Word("colors", "color", new HashSet<>());
    assertThat(w.toString()).isEqualTo("[colors (color), []]");

    w = new Word("YEARS!", "year", new HashSet<>(Arrays.asList(new Meaning(POS.N, LEX.TIME))));
    assertThat(w.toString()).isEqualTo("[YEARS! (year), [[N, TIME, 0]]]");
  }

  @Test
  public void testGetText() {
    Word w = new Word("colors", "color", new HashSet<>());
    assertThat(w.getText()).isEqualTo("colors");

    w = new Word("United States", "united_states", new HashSet<>());
    assertThat(w.getText()).isEqualTo("United States");

    w = new Word("This is definitely not a word! But it works!", "42", new HashSet<>());
    assertThat(w.getText()).isEqualTo("This is definitely not a word! But it works!");

    w = new Word("", "", new HashSet<>());
    assertThat(w.getText()).isEmpty();

    w = new Word(" ", " ", new HashSet<>());
    assertThat(w.getText()).isEqualTo(" ");
  }

  @Test
  public void testGetLemma() {
    Word w = new Word("music", "music", new HashSet<>());
    assertThat(w.getLemma()).isEqualTo("music");

    w = new Word("United Kingdom", "united_kingdom", new HashSet<>());
    assertThat(w.getLemma()).isEqualTo("united_kingdom");

    w = new Word("This is definitely not a word! But it works!", "42", new HashSet<>());
    assertThat(w.getLemma()).isEqualTo("42");

    w = new Word("", "", new HashSet<>());
    assertThat(w.getLemma()).isEmpty();

    w = new Word(" ", " ", new HashSet<>());
    assertThat(w.getLemma()).isEqualTo(" ");
  }

  @Test
  public void testGetMeanings() {
    Word w = new Word("totallyRANDOM", "totRand", new HashSet<>());
    assertThat(w.getMeanings()).isEmpty();

    Meaning m = new Meaning(POS.N, LEX.PLANT);
    Meaning mm = new Meaning(POS.N, LEX.FOOD);
    Set<Meaning> sm = new HashSet<>(Arrays.asList(m, mm));
    w = new Word("flower", "flower", sm);
    assertThat(w.getMeanings()).containsOnly(m, mm);

    m = new Meaning(POS.N, LEX.LOCATION);
    mm = new Meaning(POS.N, LEX.OBJECT);
    Meaning mmm = new Meaning(POS.N, LEX.SHAPE);
    sm = new HashSet<>(Arrays.asList(m, mm, mmm));
    w = new Word("sun", "sun", sm);
    assertThat(w.getMeanings()).containsOnly(m, mm, mmm);
  }

  @Test
  public void testEqualsReflexive() {
    Word w = new Word("hi", "hi",
        new HashSet<>(Arrays.asList(new Meaning(POS.INTERJ, LEX.GENERIC))));
    assertThat(w).isEqualTo(w);

    w = new Word("The", "the",
        new HashSet<>(Arrays.asList(new Meaning(POS.DET, LEX.DEFINITE_ARTICLE),
            new Meaning(POS.AP, LEX.CONTACT))));
    assertThat(w).isEqualTo(w);

    w = new Word("42_The_Answer!", "42",
        new HashSet<>(Arrays.asList(new Meaning(POS.UNKN, LEX.CREATION),
            new Meaning(POS.APP, LEX.EMOTION),
            new Meaning(POS.VPP, LEX.MOTION))));
    assertThat(w).isEqualTo(w);
  }

  @Test
  public void testEqualsSymmetric() {
    Word w = new Word("symmetry", "symmetry", new HashSet<>());
    Word ww = new Word("symmetry", "symmetry", new HashSet<>());
    assertThat(w).isEqualTo(ww);
    assertThat(ww).isEqualTo(w);

    w = new Word("watch", "watch", new HashSet<>(Arrays.asList(
        new Meaning(POS.V, LEX.PERCEPTION),
        new Meaning(POS.V, LEX.SOCIAL),
        new Meaning(POS.N, LEX.TIME))));
    ww = new Word("watch", "watch", new HashSet<>(Arrays.asList(
        new Meaning(POS.V, LEX.PERCEPTION),
        new Meaning(POS.V, LEX.SOCIAL),
        new Meaning(POS.N, LEX.TIME))));
    assertThat(w).isEqualTo(ww);
    assertThat(ww).isEqualTo(w);

    w = new Word("car", "car", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.OBJECT))));
    ww = new Word("car", "car", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.OBJECT))));
    assertThat(w).isEqualTo(ww);
    assertThat(ww).isEqualTo(w);
  }

  @Test
  public void testEqualsTransitive() {
    Word w = new Word("symmetry", "symmetry", new HashSet<>());
    Word ww = new Word("symmetry", "symmetry", new HashSet<>());
    Word www = new Word("symmetry", "symmetry", new HashSet<>());
    assertThat(w).isEqualTo(ww);
    assertThat(ww).isEqualTo(www);
    assertThat(www).isEqualTo(w);

    w = new Word("each other", "each_other", new HashSet<>(
        Arrays.asList(new Meaning(POS.PRON, LEX.RECIPROCAL))));
    ww = new Word("each other", "each_other", new HashSet<>(
        Arrays.asList(new Meaning(POS.PRON, LEX.RECIPROCAL))));
    www = new Word("each other", "each_other", new HashSet<>(
        Arrays.asList(new Meaning(POS.PRON, LEX.RECIPROCAL))));
    assertThat(w).isEqualTo(ww);
    assertThat(ww).isEqualTo(www);
    assertThat(www).isEqualTo(w);

    w = new Word("YOU", "you", new HashSet<>(Arrays.asList(
        new Meaning(POS.PRON, LEX.RECIPROCAL),
        new Meaning(POS.PREP, LEX.GENERIC))));
    ww = new Word("YOU", "you", new HashSet<>(Arrays.asList(
        new Meaning(POS.PRON, LEX.RECIPROCAL),
        new Meaning(POS.PREP, LEX.GENERIC))));
    www = new Word("YOU", "you", new HashSet<>(Arrays.asList(
        new Meaning(POS.PRON, LEX.RECIPROCAL),
        new Meaning(POS.PREP, LEX.GENERIC))));
    assertThat(w).isEqualTo(ww);
    assertThat(ww).isEqualTo(www);
    assertThat(www).isEqualTo(w);
  }

  @Test
  public void testEqualsConsistent() {
    Word w = new Word("consistent", "consistent", new HashSet<>());
    Word ww = new Word("consistent", "consistent", new HashSet<>());
    assertThat(w).isEqualTo(ww);
    ww = new Word("CONSISTENT", "consistent", new HashSet<>());
    assertThat(w).isNotEqualTo(ww);

    w = new Word("testMeOut!", "testMeOut!", new HashSet<>());
    ww = new Word("testMeOut!", "testMeOut!", new HashSet<>());
    assertThat(w).isEqualTo(ww);
    ww = new Word("testMeOut!", "testMeOut!", new HashSet<>(Arrays.asList(
        new Meaning(POS.NEG, LEX.CREATION))));
    assertThat(w).isEqualTo(ww);

    w = new Word("testMeOut!", "testMeOut!", new HashSet<>());
    ww = new Word("testMeOut!", "testMeOut!", new HashSet<>());
    assertThat(w).isEqualTo(ww);
    ww = new Word("TEST ME OUTT!!!", "testMeOut!", new HashSet<>(Arrays.asList(
        new Meaning(POS.NEG, LEX.CREATION))));
    assertThat(w).isNotEqualTo(ww);
  }

  @Test
  public void testEqualsNullComparison() {
    Word w = new Word("null value", "null", new HashSet<>());
    assertThat(w).isNotEqualTo(null);

    w = new Word("test", "test", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.PROCESS))));
    assertThat(w).isNotEqualTo(null);


    w = new Word("BOTH", "both", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.PROCESS),
        new Meaning(POS.N, LEX.QUANTITY))));
    assertThat(w).isNotEqualTo(null);

  }

  @Test
  public void testEqualsOtherObj() {
    Word w = new Word("object", "OBJ", new HashSet<>());
    assertThat(w).isNotEqualTo(Arrays.asList("ob", "je", "ct"));

    w = new Word("gold", "AU", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.SUBSTANCE))));
    assertThat(w).isNotEqualTo(new StringBuffer("GOLD"));

    w = new Word("gold", "AU", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.SUBSTANCE), new Meaning(POS.V, LEX.TIME))));
    assertThat(w).isNotEqualTo(new Double(42.42));
  }

  @Test
  public void testEqualsHashCodeContract() {
    Word w = new Word("contract", "contract", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.MOTIVE))));
    Word ww = new Word("contract", "contract", new HashSet<>());
    assertThat(w).isEqualTo(ww);
    assertThat(w.hashCode()).isEqualTo(ww.hashCode());

    w = new Word("hello", "hello", new HashSet<>());
    ww = new Word("hello", "hello", new HashSet<>());
    assertThat(w).isEqualTo(ww);
    assertThat(w.hashCode()).isEqualTo(ww.hashCode());

    w = new Word("HELLO", "hello", new HashSet<>());
    ww = new Word("hello", "hello", new HashSet<>());
    assertThat(w).isNotEqualTo(ww);
    assertThat(w.hashCode()).isNotEqualTo(ww.hashCode());

    w = new Word("BEAutiFUL", "beautiful", new HashSet<>(Arrays.asList(
        new Meaning(POS.AP, LEX.PERSONAL_SUBJECTIVE))));
    ww = new Word("yo!", "yo", new HashSet<>());
    assertThat(w).isNotEqualTo(ww);
    assertThat(w.hashCode()).isNotEqualTo(ww.hashCode());
  }

  @Test
  public void testHashCodeConsistent() {
    Word w = new Word("color", "color", new HashSet<>());
    Word ww = new Word("color", "color", new HashSet<>());
    assertThat(w.hashCode()).isEqualTo(ww.hashCode());

    ww = new Word("color", "color",
        new HashSet<>(Arrays.asList(new Meaning(POS.N, LEX.SUBSTANCE))));
    assertThat(w.hashCode()).isEqualTo(ww.hashCode());

    ww = new Word("COLOR!", "color", new HashSet<>());
    assertThat(w.hashCode()).isNotEqualTo(ww.hashCode());
  }

  @Test
  public void testHashCodeReflexive() {
    Word w = new Word("color", "color", new HashSet<>());
    assertThat(w.hashCode()).isEqualTo(w.hashCode());

    w = new Word("YEARS!", "year", new HashSet<>(Arrays.asList(new Meaning(POS.N, LEX.TIME))));
    assertThat(w.hashCode()).isEqualTo(w.hashCode());
  }
}
