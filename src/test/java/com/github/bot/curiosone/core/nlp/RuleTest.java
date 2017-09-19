package com.github.bot.curiosone.core.nlp;

import static com.github.bot.curiosone.core.nlp.Rule.allFrom;
import static com.github.bot.curiosone.core.nlp.Rule.allTo;
// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import com.github.bot.curiosone.core.util.Pair;

import org.junit.Test;

import java.util.Set;

public class RuleTest {

  @Test
  public void testEqualsReflexive() {
    Rule r = new Rule(POS.AP, new Pair(POS.NP, POS.NPP));
    assertTrue(r.equals(r));

    r = new Rule(POS.NUMB, new Pair(POS.VP, POS.VPP));
    assertTrue(r.equals(r));

    r = new Rule(POS.PRON, new Pair(POS.V, POS.PREP));
    assertTrue(r.equals(r));
  }

  @Test
  public void testEqualsTransitive() {
    Rule r = new Rule(POS.VPP, new Pair(POS.UNKN, POS.NEG));
    Rule rr = new Rule(POS.VPP, new Pair(POS.UNKN, POS.NEG));
    Rule rrr = new Rule(POS.VPP, new Pair(POS.UNKN, POS.NEG));
    assertTrue(r.equals(rr) && rr.equals(rrr) && rrr.equals(r));

    r = new Rule(POS.ADV, new Pair(POS.DET, POS.N));
    rr = new Rule(POS.ADV, new Pair(POS.DET, POS.N));
    rrr = new Rule(POS.ADV, new Pair(POS.DET, POS.N));
    assertTrue(r.equals(rr) && rr.equals(rrr) && rrr.equals(r));

    r = new Rule(POS.INTERJ, new Pair(POS.NEG, POS.NUMB));
    rr = new Rule(POS.INTERJ, new Pair(POS.NEG, POS.NUMB));
    rrr = new Rule(POS.INTERJ, new Pair(POS.NEG, POS.NUMB));
    assertTrue(r.equals(rr) && rr.equals(rrr) && rrr.equals(r));
  }

  @Test
  public void testEqualsSymmetric() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    Rule rr = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertTrue(r.equals(rr) && rr.equals(r));

    r = new Rule(POS.PRON, new Pair(POS.PRON, POS.PRON));
    rr = new Rule(POS.PRON, new Pair(POS.PRON, POS.PRON));
    assertTrue(r.equals(rr) && rr.equals(r));

    r = new Rule(POS.CONJ, new Pair(POS.INTERJ, POS.N));
    rr = new Rule(POS.CONJ, new Pair(POS.INTERJ, POS.N));
    assertTrue(r.equals(rr) && rr.equals(r));
  }

  @Test
  public void testEqualsNullComparison() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertFalse(r.equals(null));

    r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertFalse(r.equals(null));

    r = new Rule(POS.PRON, new Pair(POS.PRON, POS.PRON));
    assertFalse(r.equals(null));

    r = new Rule(POS.PRON, new Pair(POS.PRON, POS.PRON));
    assertFalse(r.equals(null));

    r = new Rule(POS.CONJ, new Pair(POS.INTERJ, POS.N));
    assertFalse(r.equals(null));
    r = new Rule(POS.CONJ, new Pair(POS.INTERJ, POS.N));
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

    r = new Rule(POS.VPP, new Pair(POS.S, POS.INTERJ));
    assertEquals(r.hashCode(), r.hashCode());

    r = new Rule(POS.CONJ, new Pair(POS.CONJ, POS.CONJ));
    assertEquals(r.hashCode(), r.hashCode());
  }

  @Test
  public void testHashCodeTransitive() {
    Rule r = new Rule(POS.ADV, new Pair(POS.ADV, POS.ADJ));
    Rule rr = new Rule(POS.ADV, new Pair(POS.ADV, POS.ADJ));
    Rule rrr = new Rule(POS.ADV, new Pair(POS.ADV, POS.ADJ));
    assertTrue(r.hashCode() == rr.hashCode() && rr.hashCode() == rrr.hashCode()
        && rrr.hashCode() == r.hashCode());

    r = new Rule(POS.NPP, new Pair(POS.AP, POS.NEG));
    rr = new Rule(POS.NPP, new Pair(POS.AP, POS.NEG));
    rrr = new Rule(POS.NPP, new Pair(POS.AP, POS.NEG));
    assertTrue(r.hashCode() == rr.hashCode() && rr.hashCode() == rrr.hashCode()
        && rrr.hashCode() == r.hashCode());

    r = new Rule(POS.CONJ, new Pair(POS.ADV, POS.CONJ));
    rr = new Rule(POS.CONJ, new Pair(POS.ADV, POS.CONJ));
    rrr = new Rule(POS.CONJ, new Pair(POS.ADV, POS.CONJ));
    assertTrue(r.hashCode() == rr.hashCode() && rr.hashCode() == rrr.hashCode()
        && rrr.hashCode() == r.hashCode());
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

  @Test
  public void testAllTo() {
    Set<Rule> at = allTo(new Pair(POS.V, POS.V));
    assertTrue(at.contains(new Rule(POS.S, new Pair(POS.V, POS.V))));
    assertTrue(at.contains(new Rule(POS.VP, new Pair(POS.V, POS.V))));

    at = allTo(new Pair(POS.V, POS.ADV));
    assertTrue(at.contains(new Rule(POS.VP, new Pair(POS.V, POS.ADV))));
    assertTrue(at.contains(new Rule(POS.S, new Pair(POS.V, POS.ADV))));

    at = allTo(new Pair(POS.V, POS.NP));
    assertTrue(at.contains(new Rule(POS.S, new Pair(POS.V, POS.NP))));
    assertTrue(at.contains(new Rule(POS.VP, new Pair(POS.V, POS.NP))));
  }

  @Test
  public void testAllFrom() {
    Set<Rule> af = allFrom(POS.VP);
    assertTrue(af.contains(new Rule(POS.VP, new Pair(POS.ADV, POS.NEG))));
    assertTrue(af.contains(new Rule(POS.VP, new Pair(POS.V, POS.VPP))));
    assertTrue(af.contains(new Rule(POS.VP, new Pair(POS.ADV, POS.V))));

    af = allFrom(POS.S);
    assertTrue(af.contains(new Rule(POS.S, new Pair(POS.V, POS.NPP))));
    assertTrue(af.contains(new Rule(POS.S, new Pair(POS.V, POS.VPP))));
    assertTrue(af.contains(new Rule(POS.S, new Pair(POS.NPP, POS.NP))));

    af = allFrom(POS.NP);
    assertTrue(af.contains(new Rule(POS.NP, new Pair(POS.PRON, POS.ADJ))));
    assertTrue(af.contains(new Rule(POS.NP, new Pair(POS.ADJ, POS.PRON))));
    assertTrue(af.contains(new Rule(POS.NP, new Pair(POS.ADJ, POS.N))));
  }
}
