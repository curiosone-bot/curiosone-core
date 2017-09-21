package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import it.uniroma1.lcl.babelnet.data.BabelPOS;

import org.junit.Test;

public class TestPart {

  @Test
  public void testPa2Bm() {
    assertTrue(Part.NOUN.forBabelMorph().equals(it.uniroma1.lcl.babelmorph.POS.NOUN));
  }

  @Test
  public void testPa2Wn() {
    assertTrue(Part.NOUN.forWordNet().equals(edu.mit.jwi.item.POS.NOUN));
  }
  
  @Test
  public void testPa2Bn() {
    assertTrue(Part.NOUN.forBabelNet().equals(BabelPOS.NOUN));
  }

  @Test
  public void testBn2Bm() {
    assertTrue(
        Part.from(BabelPOS.VERB).forBabelMorph()
        .equals(it.uniroma1.lcl.babelmorph.POS.VERB));
  }
}
