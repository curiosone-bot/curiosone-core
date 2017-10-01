package com.github.bot.curiosone.core.refinement;
/**
 * Word POS interoperability structure.
 * @author Claudio Venanzi
 */

import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

public enum WordPart {
  Adjective(BabelPOS.ADJECTIVE, POS.ADJECTIVE),
  Adverb(BabelPOS.ADVERB, POS.ADVERB),
  Noun(BabelPOS.NOUN, POS.NOUN),
  Verb(BabelPOS.VERB, POS.VERB),
  None(null, null);
  
  //=============================================================================================

  private final BabelPOS bn;
  private final POS      bm;

  private WordPart(BabelPOS bn, POS bm) {
    this.bn = bn;
    this.bm = bm;
  }

  //=============================================================================================
  
  /**
   * Returns the corresponding BabelNet POS.
   * @return pos
   */
  public BabelPOS forBabelNet() {
    return bn;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Returns the corresponding BabelMorph POS.
   * @return pos
   */
  public POS forBabelMorph() {
    return bm;
  }    
}
