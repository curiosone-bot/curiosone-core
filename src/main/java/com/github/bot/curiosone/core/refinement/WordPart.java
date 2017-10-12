package com.github.bot.curiosone.core.refinement;

import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

/**
 * Handles easily interaction between BabelPOS and BabelMorph POS.
 * @see  <a href="https://goo.gl/tjM6pT">The BabelPOS Enum</a>
 */
public enum WordPart {
  Adjective(BabelPOS.ADJECTIVE, POS.ADJECTIVE),
  Adverb(BabelPOS.ADVERB, POS.ADVERB),
  Noun(BabelPOS.NOUN, POS.NOUN),
  Verb(BabelPOS.VERB, POS.VERB);

  /**
   * Stores the Parts of Speech used in BabelNet.
   */
  private final BabelPOS bn;

  /**
   * Stores the Parts of Speech used in BabelMorph.
   */
  private final POS bm;

  /**
   * Sets the POS.
   * @param  bn
             the BabelNet Parts of Speech
   * @param  bm
   *         the BabelMorph Parts of Speech
   */
  private WordPart(BabelPOS bn, POS bm) {
    this.bn = bn;
    this.bm = bm;
  }

  /**
   * Gets the corresponding BabelNet POS.
   * @return the corresponding BabelNet POS.
   */
  public BabelPOS forBabelNet() {
    return bn;
  }

  /**
   * Gets the corresponding BabelMorph POS.
   * @return the corresponding BabelNet POS.
   */
  public POS forBabelMorph() {
    return bm;
  }
}
