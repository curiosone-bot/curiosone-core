package com.github.bot.curiosone.core.nlp;

import static com.github.bot.curiosone.core.nlp.Rule.allFrom;
import static com.github.bot.curiosone.core.nlp.Rule.allTo;
import static org.assertj.core.api.Assertions.assertThat;
// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import com.github.bot.curiosone.core.util.Pair;

import java.util.Set;

import org.junit.Test;

public class RuleTest {

  @Test
  public void testEqualsReflexive() {
    Rule r = new Rule(POS.AP, new Pair(POS.NP, POS.NPP));
    assertThat(r).isEqualTo(r);
  }

  @Test
  public void testEqualsSymmetric() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    Rule rr = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertThat(r.equals(rr) && rr.equals(r)).isTrue();
  }

  @Test
  public void testEqualsNullComparison() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertThat(r).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    Rule r = new Rule(POS.AP, new Pair(POS.APP, POS.APP));
    assertThat(r).isNotEqualTo(new StringBuffer("APP"));

    r = new Rule(POS.APP, new Pair(POS.ADV, POS.CONJ));
    assertThat(r).isNotEqualTo(new Double(42.42));

    r = new Rule(POS.S, new Pair(POS.S, POS.DET));
    assertThat(r).isNotEqualTo(new Object());
  }

  @Test
  public void testHashCodeReflexive() {
    Rule r = new Rule(POS.PREP, new Pair(POS.VPP, POS.UNKN));
    assertThat(r.hashCode()).isEqualTo(r.hashCode());
  }

  @Test
  public void testHashCodeSymmetric() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    Rule rr = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertThat(r.hashCode()).isEqualTo(rr.hashCode());
  }

  @Test
  public void testGetFrom() {
    Rule r = new Rule(POS.AP, new Pair(POS.AP, POS.AP));
    assertThat(r.getFrom()).isEqualTo(POS.AP);

    r = new Rule(POS.CONJ, new Pair(POS.AP, POS.CONJ));
    assertThat(r.getFrom()).isEqualTo(POS.CONJ);

    r = new Rule(POS.NPP, new Pair(POS.NP, POS.VP));
    assertThat(r.getFrom()).isEqualTo(POS.NPP);
  }

  @Test
  public void testGetTo() {
    Rule r = new Rule(POS.APP, new Pair(POS.AP, POS.AP));
    assertThat(r.getTo()).isEqualTo(new Pair(POS.AP, POS.AP));

    r = new Rule(POS.NUMB, new Pair(POS.PREP, POS.NUMB));
    assertThat(r.getTo()).isEqualTo(new Pair(POS.PREP, POS.NUMB));

    r = new Rule(POS.APP, new Pair(POS.S, POS.V));
    assertThat(r.getTo()).isEqualTo(new Pair(POS.S, POS.V));
  }
}
