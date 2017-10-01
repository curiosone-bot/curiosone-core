package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

import org.junit.Test;

public class WordTest {

  @Test
  public void main() {
    assertTrue(Word.Part.from(BabelPOS.ADVERB).equals(Word.Part.from(POS.ADVERB)));
    assertTrue(Word.Part.from(BabelPOS.NOUN).equals(Word.Part.Noun));
    assertTrue(Word.Part.from(POS.VERB).equals(Word.Part.Verb));
  }
}
