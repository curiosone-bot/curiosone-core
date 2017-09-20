package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.uniroma1.lcl.babelnet.data.BabelPOS;

public class TestPart {

  @Test
  public void testPA2BM() {
    assertTrue(Part.NOUN.forBabelMorph().equals(it.uniroma1.lcl.babelmorph.POS.NOUN));
  }

  @Test
  public void testPA2WN() {
    assertTrue(Part.NOUN.forWordNet().equals(edu.mit.jwi.item.POS.NOUN));
  }
  
  @Test
  public void testPA2BN() {
    assertTrue(Part.NOUN.forBabelNet().equals(BabelPOS.NOUN));
  }

  @Test
  public void testBN2BM() {
    assertTrue(
        Part.from(BabelPOS.VERB).forBabelMorph()
        .equals(it.uniroma1.lcl.babelmorph.POS.VERB));
  }
}
