package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

import org.junit.Test;

public class WordTest {

  @Test
  public void main() {
    assertTrue(WordPart.from(BabelPOS.ADVERB).equals(WordPart.from(POS.ADVERB)));
    assertTrue(WordPart.from(BabelPOS.NOUN).get().equals(WordPart.Noun));
    assertTrue(WordPart.from(POS.VERB).get().equals(WordPart.Verb));
  }
}
