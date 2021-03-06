package com.github.bot.curiosone.core.nlp;

import static com.github.bot.curiosone.core.nlp.Rule.allFrom;
import static com.github.bot.curiosone.core.nlp.Rule.allTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.bot.curiosone.core.util.Pair;

import java.util.Set;

import org.junit.Test;

public class RuleTest {

  @Test
  public void testToString() {
    Rule r = new Rule(POS.CONJ, new Pair(POS.AP, POS.ADJ));
    assertThat(r.toString()).isEqualTo("CONJ: (AP, ADJ)");

    r = new Rule(POS.VPP, new Pair(POS.V, POS.V));
    assertThat(r.toString()).isEqualTo("VPP: (V, V)");
  }

  @Test
  public void testAllFrom() {
    assertThat(Rule.allFrom(POS.NP)).isNotNull().isNotEmpty();
    assertThat(Rule.allFrom(POS.S)).isNotNull().isNotEmpty();
    assertThat(Rule.allFrom(POS.VP)).isNotNull().isNotEmpty();
    assertThat(Rule.allFrom(POS.CONJ)).isNotNull().isNotEmpty();
    assertThat(Rule.allFrom(POS.AP)).isNotNull().isEmpty();
    assertThat(Rule.allFrom(POS.UNKN)).isNotNull().isEmpty();
  }

  @Test
  public void testAllTo() {
    Pair p = new Pair(POS.VP, POS.NP);
    assertThat(Rule.allTo(p)).isNotNull().isNotEmpty();

    p = new Pair(POS.DET, POS.N);
    assertThat(Rule.allTo(p)).isNotNull().isNotEmpty();

    p = new Pair(POS.UNKN, POS.NP);
    assertThat(Rule.allTo(p)).isNotNull().isEmpty();

    p = new Pair(POS.ADJ, POS.AP);
    assertThat(Rule.allTo(p)).isNotNull().isEmpty();
  }

  @Test
  public void testEquals() {
    Rule r = new Rule(POS.UNKN, new Pair(POS.UNKN, POS.UNKN));
    Rule rr = new Rule(POS.UNKN, new Pair(POS.UNKN, POS.UNKN));
    assertThat(r).isEqualTo(rr);

    rr = new Rule(POS.S, new Pair(POS.UNKN, POS.VP));
    assertThat(r).isNotEqualTo(rr);
  }

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
  public void testEqualsTransitive() {
    Rule r = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    Rule rr = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    Rule rrr = new Rule(POS.AP, new Pair(POS.NEG, POS.ADJ));
    assertThat(r).isEqualTo(rr);
    assertThat(rr).isEqualTo(rrr);
    assertThat(rrr).isEqualTo(r);
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
  public void testHashCodeEqualsContract() {
    Rule r = new Rule(POS.NEG, new Pair(POS.APP, POS.AP));
    Rule rr = new Rule(POS.NEG, new Pair(POS.APP, POS.AP));
    assertThat(r.hashCode()).isEqualTo(rr.hashCode());
    assertThat(r).isEqualTo(rr);

    r = new Rule(POS.VP, new Pair(POS.APP, POS.AP));
    rr = new Rule(POS.VPP, new Pair(POS.CONJ, POS.VP));
    assertThat(r.hashCode()).isNotEqualTo(rr.hashCode());
    assertThat(r).isNotEqualTo(rr);
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
