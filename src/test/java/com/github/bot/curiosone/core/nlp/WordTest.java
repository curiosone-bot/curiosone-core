package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

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
    assertTrue(w instanceof Word);
  }

  @Test
  public void testGetText() {
    Word w = new Word("colors", "color", new HashSet<>());
    assertEquals("colors", w.getText());

    w = new Word("United States", "united_states", new HashSet<>());
    assertEquals("United States", w.getText());

    w = new Word("This is definitely not a word! But it works!", "42", new HashSet<>());
    assertEquals("This is definitely not a word! But it works!", w.getText());
  }

  @Test
  public void testGetLemma() {
    Word w = new Word("music", "music", new HashSet<>());
    assertEquals("music", w.getLemma());

    w = new Word("United Kingdom", "united_kingdom", new HashSet<>());
    assertEquals("united_kingdom", w.getLemma());

    w = new Word("This is definitely not a word! But it works!", "42", new HashSet<>());
    assertEquals("42", w.getLemma());
  }

  @Test
  public void testGetMeanings() {
    Word w = new Word("totallyRANDOM", "totRand", new HashSet<>());
    assertEquals(0, w.getMeanings().size());

    Set<Meaning> m = new HashSet<>(
        Arrays.asList(
            new Meaning(POS.N, LEX.PLANT),
            new Meaning(POS.N, LEX.FOOD)));
    w = new Word("flower", "flower", m);
    assertTrue(m.contains(new Meaning(POS.N, LEX.PLANT)));
    assertTrue(m.contains(new Meaning(POS.N, LEX.FOOD)));

    m = new HashSet<>(
        Arrays.asList(
            new Meaning(POS.N, LEX.LOCATION),
            new Meaning(POS.N, LEX.OBJECT),
            new Meaning(POS.N, LEX.SHAPE)));
    w = new Word("sun", "sun", m);
    assertTrue(m.contains(new Meaning(POS.N, LEX.OBJECT)));
    assertTrue(m.contains(new Meaning(POS.N, LEX.LOCATION)));
    assertTrue(m.contains(new Meaning(POS.N, LEX.SHAPE)));
  }

  @Test
  public void testEqualsReflexive() {
    Word w = new Word("hi", "hi",
        new HashSet<>(Arrays.asList(new Meaning(POS.INTERJ, LEX.GENERIC))));
    assertEquals(w, w);

    w = new Word("The", "the",
        new HashSet<>(Arrays.asList(new Meaning(POS.DET, LEX.DEFINITE_ARTICLE),
            new Meaning(POS.AP, LEX.CONTACT))));
    assertEquals(w, w);

    w = new Word("42_The_Answer!", "42",
        new HashSet<>(Arrays.asList(new Meaning(POS.UNKN, LEX.CREATION),
            new Meaning(POS.APP, LEX.EMOTION),
            new Meaning(POS.VPP, LEX.MOTION))));
    assertEquals(w, w);
  }

  @Test
  public void testEqualsSymmetric() {
    Word w = new Word("symmetry", "symmetry", new HashSet<>());
    Word ww = new Word("symmetry", "symmetry", new HashSet<>());
    assertTrue(w.equals(ww) && ww.equals(w));

    w = new Word("watch", "watch", new HashSet<>(Arrays.asList(
        new Meaning(POS.V, LEX.PERCEPTION),
        new Meaning(POS.V, LEX.SOCIAL),
        new Meaning(POS.N, LEX.TIME))));
    ww = new Word("watch", "watch", new HashSet<>(Arrays.asList(
        new Meaning(POS.V, LEX.PERCEPTION),
        new Meaning(POS.V, LEX.SOCIAL),
        new Meaning(POS.N, LEX.TIME))));
    assertTrue(w.equals(ww) && ww.equals(w));

    w = new Word("car", "car", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.OBJECT))));
    ww = new Word("car", "car", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.OBJECT))));
    assertTrue(w.equals(ww) && ww.equals(w));
  }

  @Test
  public void testEqualsTransitive() {
    Word w = new Word("symmetry", "symmetry", new HashSet<>());
    Word ww = new Word("symmetry", "symmetry", new HashSet<>());
    Word www = new Word("symmetry", "symmetry", new HashSet<>());
    assertTrue(w.equals(ww) && ww.equals(www) && www.equals(w));

    w = new Word("each other", "each_other", new HashSet<>(
        Arrays.asList(new Meaning(POS.PRON, LEX.RECIPROCAL))));
    ww = new Word("each other", "each_other", new HashSet<>(
        Arrays.asList(new Meaning(POS.PRON, LEX.RECIPROCAL))));
    www = new Word("each other", "each_other", new HashSet<>(
        Arrays.asList(new Meaning(POS.PRON, LEX.RECIPROCAL))));
    assertTrue(w.equals(ww) && ww.equals(www) && www.equals(w));

    w = new Word("YOU", "you", new HashSet<>(Arrays.asList(
        new Meaning(POS.PRON, LEX.RECIPROCAL),
        new Meaning(POS.PREP, LEX.GENERIC))));
    ww = new Word("YOU", "you", new HashSet<>(Arrays.asList(
        new Meaning(POS.PRON, LEX.RECIPROCAL),
        new Meaning(POS.PREP, LEX.GENERIC))));
    www = new Word("YOU", "you", new HashSet<>(Arrays.asList(
        new Meaning(POS.PRON, LEX.RECIPROCAL),
        new Meaning(POS.PREP, LEX.GENERIC))));
    assertTrue(w.equals(ww) && ww.equals(www) && www.equals(w));
  }

  @Test
  public void testEqualsConsistent() {
    Word w = new Word("consistent", "consistent", new HashSet<>());
    Word ww = new Word("consistent", "consistent", new HashSet<>());
    assertEquals(w, ww);
    ww = new Word("CONSISTENT", "consistent", new HashSet<>());
    assertNotEquals(w, ww);

    w = new Word("testMeOut!", "testMeOut!", new HashSet<>());
    ww = new Word("testMeOut!", "testMeOut!", new HashSet<>());
    assertEquals(w, ww);
    ww = new Word("testMeOut!", "testMeOut!", new HashSet<>(Arrays.asList(
        new Meaning(POS.NEG, LEX.CREATION))));
    assertEquals(w, ww);

    w = new Word("testMeOut!", "testMeOut!", new HashSet<>());
    ww = new Word("testMeOut!", "testMeOut!", new HashSet<>());
    assertEquals(w, ww);
    ww = new Word("TEST ME OUTT!!!", "testMeOut!", new HashSet<>(Arrays.asList(
        new Meaning(POS.NEG, LEX.CREATION))));
    assertNotEquals(w, ww);
  }

  @Test
  public void testEqualsNullComparison() {
    Word w = new Word("null value", "null", new HashSet<>());
    assertNotEquals(null, w);

    w = new Word("test", "test", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.PROCESS))));
    assertNotEquals(null, w);

    w = new Word("BOTH", "both", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.PROCESS),
        new Meaning(POS.N, LEX.QUANTITY))));
    assertNotEquals(null, w);
  }

  @Test
  public void testEqualsOtherObj() {
    Word w = new Word("object", "OBJ", new HashSet<>());
    assertNotEquals(w, Arrays.asList("ob", "je", "ct"));

    w = new Word("gold", "AU", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.SUBSTANCE))));
    assertNotEquals(w, new StringBuffer("gold"));

    w = new Word("gold", "AU", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.SUBSTANCE), new Meaning(POS.V, LEX.TIME))));
    assertNotEquals(w, new Double(42.42));
  }

  @Test
  public void testEqualsHashCodeContract() {
    Word w = new Word("contract", "contract", new HashSet<>(Arrays.asList(
        new Meaning(POS.N, LEX.MOTIVE))));
    Word ww = new Word("contract", "contract", new HashSet<>());
    assertEquals(w, ww);
    assertEquals(w.hashCode(), ww.hashCode());

    w = new Word("hello", "hello", new HashSet<>());
    ww = new Word("hello", "hello", new HashSet<>());
    assertEquals(w, ww);
    assertEquals(w.hashCode(), ww.hashCode());

    w = new Word("HELLO", "hello", new HashSet<>());
    ww = new Word("hello", "hello", new HashSet<>());
    assertNotEquals(w, ww);
    assertNotEquals(w.hashCode(), ww.hashCode());

    w = new Word("BEAutiFUL", "beautiful", new HashSet<>(Arrays.asList(
        new Meaning(POS.AP, LEX.PERSONAL_SUBJECTIVE))));
    ww = new Word("yo!", "yo", new HashSet<>());
    assertNotEquals(w, ww);
    assertNotEquals(w.hashCode(), ww.hashCode());
  }

  @Test
  public void testHashCodeTransitive() {
    Word w = new Word("Mark", "mark",
        new HashSet<>(Arrays.asList(new Meaning(POS.N, LEX.PERSON))));
    Word ww = new Word("Mark", "mark",
        new HashSet<>(Arrays.asList(new Meaning(POS.N, LEX.PERSON))));
    Word www = new Word("Mark", "mark",
        new HashSet<>(Arrays.asList(new Meaning(POS.N, LEX.PERSON))));
    assertTrue(w.hashCode() == ww.hashCode() && ww.hashCode() == www.hashCode()
        && www.hashCode() == w.hashCode());

    w = new Word("42", "fortytwo", new HashSet<>());
    ww = new Word("42", "fortytwo", new HashSet<>());
    www = new Word("42", "fortytwo", new HashSet<>());
    assertTrue(w.hashCode() == ww.hashCode() && ww.hashCode() == www.hashCode()
        && www.hashCode() == w.hashCode());

    w = new Word("", "",
        new HashSet<>(Arrays.asList(new Meaning(POS.NP, LEX.ACT))));
    ww = new Word("", "",
        new HashSet<>(Arrays.asList(new Meaning(POS.NP, LEX.ACT))));
    www = new Word("", "",
        new HashSet<>(Arrays.asList(new Meaning(POS.NP, LEX.ACT))));
    assertTrue(w.hashCode() == ww.hashCode() && ww.hashCode() == www.hashCode()
        && www.hashCode() == w.hashCode());
  }

  @Test
  public void testHashCodConsistent() {
    Word w = new Word("color", "color", new HashSet<>());
    Word ww = new Word("color", "color", new HashSet<>());
    assertEquals(w.hashCode(), ww.hashCode());
    ww = new Word("color", "color",
        new HashSet<>(Arrays.asList(new Meaning(POS.N, LEX.SUBSTANCE))));
    assertEquals(w.hashCode(), ww.hashCode());
    ww = new Word("COLOR!", "color", new HashSet<>());
    assertNotEquals(w, ww);
  }
}
