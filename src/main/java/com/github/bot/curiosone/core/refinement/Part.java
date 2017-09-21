package com.github.bot.curiosone.core.refinement;
/**
 * POS wrapper used ad a common interoperability structure.
 * @author Claudio Venanzi
 */

public enum Part {

  ADJECTIVE(
      it.uniroma1.lcl.babelnet.data.BabelPOS.ADJECTIVE,
      it.uniroma1.lcl.babelmorph.POS.ADJECTIVE,
      edu.mit.jwi.item.POS.ADJECTIVE),

  ADVERB(
      it.uniroma1.lcl.babelnet.data.BabelPOS.ADVERB,
      it.uniroma1.lcl.babelmorph.POS.ADVERB,
      edu.mit.jwi.item.POS.ADVERB),

  NOUN(
      it.uniroma1.lcl.babelnet.data.BabelPOS.NOUN,
      it.uniroma1.lcl.babelmorph.POS.NOUN,
      edu.mit.jwi.item.POS.NOUN),
  
  VERB(
      it.uniroma1.lcl.babelnet.data.BabelPOS.VERB,
      it.uniroma1.lcl.babelmorph.POS.VERB,
      edu.mit.jwi.item.POS.VERB),
  
  NONE(null, null, null);
  
  private final it.uniroma1.lcl.babelnet.data.BabelPOS bn;
  private final it.uniroma1.lcl.babelmorph.POS bm;
  private final edu.mit.jwi.item.POS wn;

  private Part(
      it.uniroma1.lcl.babelnet.data.BabelPOS bn,
      it.uniroma1.lcl.babelmorph.POS bm,
      edu.mit.jwi.item.POS wn) {
    
    this.bn = bn;
    this.bm = bm;
    this.wn = wn;
  }

  //===============================================================================================

  /**
   * Returns the corresponding BabelNet POS.
   * @return pos
   */
  public it.uniroma1.lcl.babelnet.data.BabelPOS forBabelNet() {
    return bn;
  }
  
  //-----------------------------------------------------------------------------------------------

  /**
   * Returns the corresponding BabelMorph POS.
   * @return pos
   */
  public it.uniroma1.lcl.babelmorph.POS forBabelMorph() {
    return bm;
  }
  
  //-----------------------------------------------------------------------------------------------

  /**
   * Returns the corresponding WordNet POS.
   * @return pos
   */
  public edu.mit.jwi.item.POS forWordNet() {
    return wn;
  }

  //-----------------------------------------------------------------------------------------------

  /**
   * Returns Part from BabelNet POS.
   * @param pos source pos
   * @return part
   */
  public static Part from(it.uniroma1.lcl.babelnet.data.BabelPOS pos) {
    for (Part p : Part.values()) {
      if (pos.equals(p.forBabelNet())) {
        return p;
      }
    }
    return Part.NONE;
  }

  //-----------------------------------------------------------------------------------------------

  /**
   * Returns Part from BabelMorph POS.
   * @param pos source pos
   * @return part
   */
  public static Part from(it.uniroma1.lcl.babelmorph.POS pos) {
    for (Part p : Part.values()) {
      if (pos.equals(p.forBabelMorph())) {
        return p;
      }
    }
    return Part.NONE;
  }

  //-----------------------------------------------------------------------------------------------
  
  /**
   * Returns Part from WordNet POS.
   * @param pos source pos
   * @return part
   */
  public static Part from(edu.mit.jwi.item.POS pos) {
    for (Part p : Part.values()) {
      if (pos.equals(p.forWordNet())) {
        return p;
      }
    }
    return Part.NONE;
  }
  
  //-----------------------------------------------------------------------------------------------
}
