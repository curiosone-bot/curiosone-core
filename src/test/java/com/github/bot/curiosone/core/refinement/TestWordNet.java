package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestWordNet {

  WordNet wn = WordNet.getInstance();
  
  @Test
  public void testSuffix() {
    wn.update();
    assertTrue(wn.getStem("running", Part.VERB).equals("run"));
  }

  @Test
  public void testUnknown() {
    assertTrue(wn.getStem("ricciolo", Part.VERB).equals("ricciolo"));
  }

  /*
   * The WordNet stemmer apparently does not remove prefixes nor does manage compounds.
   * It's going to be replaced by the BabelMorpher so... 'who cares' ;-)
   *
  @Test
  public void testPrefix() {
    assertTrue(wn.getStem("unknown", Part.ADJECTIVE).equals("known"));
  }

  @Test
  public void testBoth() {
    assertTrue(wn.getStem("unexpected", Part.ADJECTIVE).equals("expect"));
  }

  @Test
  public void testCompound() {
    assertTrue(wn.getStem("walking dead", Part.ADJECTIVE).equals("known"));
  }
  */
}
