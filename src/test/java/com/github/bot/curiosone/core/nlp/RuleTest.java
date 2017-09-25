package com.github.bot.curiosone.core.nlp;

import static com.github.bot.curiosone.core.nlp.Rule.allFrom;
import static com.github.bot.curiosone.core.nlp.Rule.allTo;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import com.github.bot.curiosone.core.util.Pair;

import java.util.Set;

import org.junit.Test;

public class RuleTest {

  @Test
  public void testEqualsReflexive() {
    Rule r = new Rule(POS.AP, new Pair(POS.NP, POS.NPP));
    assertTrue(r.equals(r));
  }

  @Test
  public void testEqualsSymmetric() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    Rule rr = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertTrue(r.equals(rr) && rr.equals(r));
  }

  @Test
  public void testEqualsNullComparison() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertFalse(r.equals(null));
  }

  @Test
  public void testEqualsOtherObj() {
    Rule r = new Rule(POS.AP, new Pair(POS.APP, POS.APP));
    assertFalse(r.equals(new StringBuffer("APP")));

    r = new Rule(POS.APP, new Pair(POS.ADV, POS.CONJ));
    assertFalse(r.equals(new Double(42.42)));

    r = new Rule(POS.S, new Pair(POS.S, POS.DET));
    assertFalse(r.equals(new Object()));
  }

  @Test
  public void testHashCodeReflexive() {
    Rule r = new Rule(POS.PREP, new Pair(POS.VPP, POS.UNKN));
    assertEquals(r.hashCode(), r.hashCode());
  }

  @Test
  public void testHashCodeSymmetric() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    Rule rr = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertEquals(r.hashCode(), rr.hashCode());
  }

  @Test
  public void testGetFrom() {
    Rule r = new Rule(POS.AP, new Pair(POS.AP, POS.AP));
    assertEquals(POS.AP, r.getFrom());

    r = new Rule(POS.CONJ, new Pair(POS.AP, POS.CONJ));
    assertEquals(POS.CONJ, r.getFrom());

    r = new Rule(POS.NPP, new Pair(POS.NP, POS.VP));
    assertEquals(POS.NPP, r.getFrom());
  }

  @Test
  public void testGetTo() {
    Rule r = new Rule(POS.APP, new Pair(POS.AP, POS.AP));
    assertEquals(new Pair(POS.AP, POS.AP), r.getTo());

    r = new Rule(POS.NUMB, new Pair(POS.PREP, POS.NUMB));
    assertEquals(new Pair(POS.PREP, POS.NUMB), r.getTo());

    r = new Rule(POS.APP, new Pair(POS.S, POS.V));
    assertEquals(new Pair(POS.S, POS.V), r.getTo());
  }
}
