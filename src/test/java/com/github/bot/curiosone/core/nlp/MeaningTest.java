package com.github.bot.curiosone.core.nlp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.Test;

public class MeaningTest {
  @Test
  public void testInstantiation() {
    assertThat(new Meaning(POS.AP, LEX.OBJECT) instanceof Meaning).isTrue();

    assertThat(new Meaning(POS.VP, LEX.TIME) instanceof Comparable).isTrue();
  }

  @Test
  public void testGetPos() {
    Meaning m = new Meaning(POS.AP, LEX.OBJECT);
    assertThat(m.getPOS()).isEqualTo(POS.AP);

    m = new Meaning(POS.VPP, LEX.PHENOMENON);
    assertThat(m.getPOS()).isEqualTo(POS.VPP);

    m = new Meaning(POS.ADJ, LEX.QUANTITY);
    assertThat(m.getPOS()).isEqualTo(POS.ADJ);
  }

  @Test
  public void testGetLex() {
    Meaning m = new Meaning(POS.AP, LEX.OBJECT);
    assertThat(m.getLEX()).isEqualTo(LEX.OBJECT);

    m = new Meaning(POS.VPP, LEX.PHENOMENON);
    assertThat(m.getLEX()).isEqualTo(LEX.PHENOMENON);

    m = new Meaning(POS.ADJ, LEX.QUANTITY);
    assertThat(m.getLEX()).isEqualTo(LEX.QUANTITY);
  }

  @Test
  public void testSetFrequencyException() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> {
          new Meaning(POS.AP, LEX.PHENOMENON).setFrequency(-1);
        })
        .withMessage("Frequency must be positive");

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> {
          new Meaning(POS.VPP, LEX.OBJECT).setFrequency(-2);
        })
        .withMessage("Frequency must be positive");

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> {
          new Meaning(POS.VP, LEX.QUANTITY).setFrequency(-42);
        })
        .withMessage("Frequency must be positive");
  }

  @Test
  public void testSetFrequency() {
    Meaning m = new Meaning(POS.AP, LEX.PHENOMENON);
    m.setFrequency(1);
    assertThat(m.getFrequency()).isEqualTo(1);

    m = new Meaning(POS.VPP, LEX.OBJECT);
    m.setFrequency(2);
    assertThat(m.getFrequency()).isEqualTo(2);

    m = new Meaning(POS.VP, LEX.QUANTITY);
    m.setFrequency(42);
    assertThat(m.getFrequency()).isEqualTo(42);
  }

  @Test
  public void testEqualsReflexive() {
    Meaning m  = new Meaning(POS.DET, LEX.TIME);
    assertThat(m).isEqualTo(m);

    m = new Meaning(POS.NEG, LEX.SOCIAL);
    assertThat(m).isEqualTo(m);

    m = new Meaning(POS.INTERJ, LEX.PERSONAL_OBJECTIVE);
    assertThat(m).isEqualTo(m);
  }

  @Test
  public void testEqualsSymmetric() {
    Meaning m = new Meaning(POS.PREP, LEX.RELATIVE);
    Meaning mm = new Meaning(POS.PREP, LEX.RELATIVE);
    assertThat(m.equals(mm) && mm.equals(m)).isTrue();

    m = new Meaning(POS.UNKN, LEX.COORDINATOR);
    mm = new Meaning(POS.UNKN, LEX.COORDINATOR);
    assertThat(m.equals(mm) && mm.equals(m)).isTrue();

    m = new Meaning(POS.CONJ, LEX.SURPRISE);
    mm = new Meaning(POS.CONJ, LEX.SURPRISE);
    assertThat(m.equals(mm) && mm.equals(m)).isTrue();
  }

  @Test
  public void testEqualsTransitive() {
    Meaning m = new Meaning(POS.DET, LEX.REGARDS);
    Meaning mm = new Meaning(POS.DET, LEX.REGARDS);
    Meaning mmm = new Meaning(POS.DET, LEX.REGARDS);
    assertThat(m.equals(mm) && mm.equals(mmm) && mmm.equals(m)).isTrue();

    m = new Meaning(POS.N, LEX.SUBSTANCE);
    mm = new Meaning(POS.N, LEX.SUBSTANCE);
    mmm = new Meaning(POS.N, LEX.SUBSTANCE);
    assertThat(m.equals(mm) && mm.equals(mmm) && mmm.equals(m)).isTrue();

    m = new Meaning(POS.APP, LEX.TIME);
    mm = new Meaning(POS.APP, LEX.TIME);
    mmm = new Meaning(POS.APP, LEX.TIME);
    assertThat(m.equals(mm) && mm.equals(mmm) && mmm.equals(m)).isTrue();
  }

  @Test
  public void testEqualsConsistent() {
    Meaning m = new Meaning(POS.CONJ, LEX.CHANGE);
    Meaning mm = new Meaning(POS.CONJ, LEX.CHANGE);
    assertThat(m).isEqualTo(mm);

    m.setFrequency(42);
    m.setFrequency(424242);
    assertThat(m).isEqualTo(mm);
  }

  @Test
  public void testEqualsNullComparison() {
    Meaning m = new Meaning(POS.NPP, LEX.CONSUMPTION);
    assertThat(m).isNotEqualTo(null);

    m = new Meaning(POS.NUMB, LEX.CONTACT);
    assertThat(m).isNotEqualTo(null);

    m = new Meaning(POS.S, LEX.CREATION);
    assertThat(m).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    Meaning m = new Meaning(POS.CONJ, LEX.PAIN);
    assertThat(m.equals(new Integer(42))).isFalse();
  }

  @Test
  public void testEqualsHashCodeContract() {
    Meaning m = new Meaning(POS.VP, LEX.DISGUST);
    Meaning mm = new Meaning(POS.VP, LEX.DISGUST);
    assertThat(m).isEqualTo(mm);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());

    m = new Meaning(POS.PRON, LEX.APOLOGIZE);
    mm = new Meaning(POS.PRON, LEX.APOLOGIZE);
    assertThat(m).isEqualTo(mm);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());

    m = new Meaning(POS.AP, LEX.GRATITUDE);
    mm = new Meaning(POS.AP, LEX.GRATITUDE);
    assertThat(m).isEqualTo(mm);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());
  }

  @Test
  public void testHashCodeReflexive() {
    Meaning m = new Meaning(POS.ADV, LEX.SURPRISE);
    Meaning mm = new Meaning(POS.ADV, LEX.SURPRISE);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());

    m = new Meaning(POS.PREP, LEX.MAIL);
    mm = new Meaning(POS.PREP, LEX.MAIL);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());

    m = new Meaning(POS.V, LEX.GENERIC);
    mm = new Meaning(POS.V, LEX.GENERIC);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());
  }

  @Test
  public void testHashCodeConsistent() {
    Meaning m = new Meaning(POS.S, LEX.INTERROGATIVE);
    Meaning mm = new Meaning(POS.S, LEX.INTERROGATIVE);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());
    m.setFrequency(130);
    mm.setFrequency(31);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());

    m = new Meaning(POS.INTERJ, LEX.REGARDS);
    mm = new Meaning(POS.INTERJ, LEX.REGARDS);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());
    m.setFrequency(130);
    mm.setFrequency(130);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());

    m = new Meaning(POS.NUMB, LEX.INDEFINITE_ARTICLE);
    mm = new Meaning(POS.NUMB, LEX.INDEFINITE_ARTICLE);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());
    m.setFrequency(90);
    mm.setFrequency(45);
    assertThat(m.hashCode()).isEqualTo(mm.hashCode());
  }

  @Test
  public void testCompareTo() {
    Meaning m = new Meaning(POS.NPP, LEX.INTERROGATIVE);
    Meaning mm = new Meaning(POS.S, LEX.DISGUST);
    assertThat(m.compareTo(mm)).isZero();

    m.setFrequency(1);
    mm.setFrequency(2);
    assertThat(m.compareTo(mm)).isLessThan(0);

    m.setFrequency(3);
    assertThat(m.compareTo(mm)).isGreaterThan(0);
  }
}
