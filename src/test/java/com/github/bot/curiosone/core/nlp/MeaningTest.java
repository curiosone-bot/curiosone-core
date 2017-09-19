package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class MeaningTest {
  @Test
  public void testInstantiation() {
    Meaning m = new Meaning(POS.AP, LEX.OBJECT);
    assertTrue(m instanceof Meaning);
    assertTrue(m instanceof Comparable);
  }

  @Test
  public void testGetPos() {
    Meaning m = new Meaning(POS.AP, LEX.OBJECT);
    assertEquals(POS.AP, m.getPOS());

    m = new Meaning(POS.VPP, LEX.PHENOMENON);
    assertEquals(POS.VPP, m.getPOS());

    m = new Meaning(POS.ADJ, LEX.QUANTITY);
    assertEquals(POS.ADJ, m.getPOS());
  }

  @Test
  public void testGetLex() {
    Meaning m = new Meaning(POS.AP, LEX.OBJECT);
    assertEquals(LEX.OBJECT, m.getLEX());

    m = new Meaning(POS.VPP, LEX.PHENOMENON);
    assertEquals(LEX.PHENOMENON, m.getLEX());

    m = new Meaning(POS.ADJ, LEX.QUANTITY);
    assertEquals(LEX.QUANTITY, m.getLEX());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFrequencyException() {
    Meaning m = new Meaning(POS.AP, LEX.PHENOMENON);
    m.setFrequency(-1);

    m = new Meaning(POS.VPP, LEX.OBJECT);
    m.setFrequency(-2);

    m = new Meaning(POS.VP, LEX.QUANTITY);
    m.setFrequency(-42);
  }

  @Test
  public void testSetFrequency() {
    Meaning m = new Meaning(POS.AP, LEX.PHENOMENON);
    m.setFrequency(1);
    assertEquals(1, m.getFrequency());

    m = new Meaning(POS.VPP, LEX.OBJECT);
    m.setFrequency(2);
    assertEquals(2, m.getFrequency());

    m = new Meaning(POS.VP, LEX.QUANTITY);
    m.setFrequency(42);
    assertEquals(42, m.getFrequency());
  }

  @Test
  public void testEqualsReflexive() {
    Meaning m  = new Meaning(POS.DET, LEX.TIME);
    assertTrue(m.equals(m));

    m  = new Meaning(POS.NEG, LEX.SOCIAL);
    assertTrue(m.equals(m));

    m  = new Meaning(POS.INTERJ, LEX.PERSONAL_OBJECTIVE);
    assertTrue(m.equals(m));
  }

  @Test
  public void testEqualsSymmetric() {
    Meaning m = new Meaning(POS.PREP, LEX.RELATIVE);
    Meaning mm = new Meaning(POS.PREP, LEX.RELATIVE);
    assertTrue(m.equals(mm) && mm.equals(m));

    m = new Meaning(POS.UNKN, LEX.COORDINATOR);
    mm = new Meaning(POS.UNKN, LEX.COORDINATOR);
    assertTrue(m.equals(mm) && mm.equals(m));

    m = new Meaning(POS.CONJ, LEX.SURPRISE);
    mm = new Meaning(POS.CONJ, LEX.SURPRISE);
    assertTrue(m.equals(mm) && mm.equals(m));
  }

  @Test
  public void testEqualsTransitive() {
    Meaning m = new Meaning(POS.DET, LEX.REGARDS);
    Meaning mm = new Meaning(POS.DET, LEX.REGARDS);
    Meaning mmm = new Meaning(POS.DET, LEX.REGARDS);
    assertTrue(m.equals(mm) && mm.equals(mmm) && mmm.equals(m));

    m = new Meaning(POS.N, LEX.SUBSTANCE);
    mm = new Meaning(POS.N, LEX.SUBSTANCE);
    mmm = new Meaning(POS.N, LEX.SUBSTANCE);
    assertTrue(m.equals(mm) && mm.equals(mmm) && mmm.equals(m));

    m = new Meaning(POS.APP, LEX.TIME);
    mm = new Meaning(POS.APP, LEX.TIME);
    mmm = new Meaning(POS.APP, LEX.TIME);
    assertTrue(m.equals(mm) && mm.equals(mmm) && mmm.equals(m));
  }

  @Test
  public void testEqualsConsistent() {
    Meaning m = new Meaning(POS.CONJ, LEX.CHANGE);
    Meaning mm = new Meaning(POS.CONJ, LEX.CHANGE);
    assertTrue(m.equals(mm));

    m.setFrequency(42);
    m.setFrequency(424242);
    assertTrue(m.equals(mm));
  }

  @Test
  public void testEqualsNullComparison() {
    Meaning m = new Meaning(POS.NPP, LEX.CONSUMPTION);
    assertFalse(m.equals(null));

    m = new Meaning(POS.NUMB, LEX.CONTACT);
    assertFalse(m.equals(null));

    m = new Meaning(POS.S, LEX.CREATION);
    assertFalse(m.equals(null));
  }

  @Test
  public void testEqualsOtherObj() {
    Meaning m = new Meaning(POS.CONJ, LEX.PAIN);
    assertFalse(m.equals(new Integer(42)));
  }

  @Test
  public void testEqualsHashCodeContract() {
    Meaning m = new Meaning(POS.VP, LEX.DISGUST);
    Meaning mm = new Meaning(POS.VP, LEX.DISGUST);
    assertEquals(m, mm);
    assertEquals(m.hashCode(), mm.hashCode());

    m = new Meaning(POS.PRON, LEX.APOLOGIZE);
    mm = new Meaning(POS.PRON, LEX.APOLOGIZE);
    assertTrue(m.equals(mm));
    assertEquals(m.hashCode(), mm.hashCode());

    m = new Meaning(POS.AP, LEX.GRATITUDE);
    mm = new Meaning(POS.AP, LEX.GRATITUDE);
    assertTrue(m.equals(mm));
    assertEquals(m.hashCode(), mm.hashCode());
  }

  @Test
  public void testHashCodeReflexive() {
    Meaning m = new Meaning(POS.ADV, LEX.SURPRISE);
    Meaning mm = new Meaning(POS.ADV, LEX.SURPRISE);
    assertEquals(m.hashCode(), mm.hashCode());

    m = new Meaning(POS.PREP, LEX.MAIL);
    mm = new Meaning(POS.PREP, LEX.MAIL);
    assertEquals(m.hashCode(), mm.hashCode());

    m = new Meaning(POS.V, LEX.GENERIC);
    mm = new Meaning(POS.V, LEX.GENERIC);
    assertEquals(m.hashCode(), mm.hashCode());
  }

  @Test
  public void testHashCodeTransitive() {
    Meaning m = new Meaning(POS.ADV, LEX.SURPRISE);
    Meaning mm = new Meaning(POS.ADV, LEX.SURPRISE);
    Meaning mmm = new Meaning(POS.ADV, LEX.SURPRISE);
    assertTrue(m.hashCode() == mm.hashCode() && mm.hashCode() == mmm.hashCode()
        && mmm.hashCode() == m.hashCode());

    m = new Meaning(POS.PREP, LEX.MAIL);
    mm = new Meaning(POS.PREP, LEX.MAIL);
    mmm = new Meaning(POS.PREP, LEX.MAIL);
    assertTrue(m.hashCode() == mm.hashCode() && mm.hashCode() == mmm.hashCode()
        && mmm.hashCode() == m.hashCode());

    m = new Meaning(POS.V, LEX.GENERIC);
    mm = new Meaning(POS.V, LEX.GENERIC);
    mmm = new Meaning(POS.V, LEX.GENERIC);
    assertTrue(m.hashCode() == mm.hashCode() && mm.hashCode() == mmm.hashCode()
        && mmm.hashCode() == m.hashCode());
  }

  @Test
  public void testHashCodeConsistent() {
    Meaning m = new Meaning(POS.S, LEX.INTERROGATIVE);
    Meaning mm = new Meaning(POS.S, LEX.INTERROGATIVE);
    assertEquals(m.hashCode(), mm.hashCode());
    m.setFrequency(130);
    mm.setFrequency(31);
    assertEquals(m.hashCode(), mm.hashCode());

    m = new Meaning(POS.INTERJ, LEX.REGARDS);
    mm = new Meaning(POS.INTERJ, LEX.REGARDS);
    assertEquals(m.hashCode(), mm.hashCode());
    m.setFrequency(130);
    mm.setFrequency(130);
    assertEquals(m.hashCode(), mm.hashCode());

    m = new Meaning(POS.NUMB, LEX.INDEFINITE_ARTICLE);
    mm = new Meaning(POS.NUMB, LEX.INDEFINITE_ARTICLE);
    assertEquals(m.hashCode(), mm.hashCode());
    m.setFrequency(90);
    mm.setFrequency(45);
    assertEquals(m.hashCode(), mm.hashCode());
  }

  @Test
  public void testCompareTo() {
    Meaning m = new Meaning(POS.NPP, LEX.INTERROGATIVE);
    Meaning mm = new Meaning(POS.S, LEX.DISGUST);
    assertEquals(0, m.compareTo(mm));

    m.setFrequency(1);
    mm.setFrequency(2);
    assertTrue(m.compareTo(mm) < 0);

    m.setFrequency(3);
    assertTrue(m.compareTo(mm) > 0);
  }
}
