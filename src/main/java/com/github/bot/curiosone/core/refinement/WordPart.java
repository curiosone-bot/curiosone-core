package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

import java.util.Optional;
import java.util.stream.Stream;

public enum WordPart {
  
  Adjective(BabelPOS.ADJECTIVE, POS.ADJECTIVE),
  Adverb(BabelPOS.ADVERB, POS.ADVERB),
  Noun(BabelPOS.NOUN, POS.NOUN),
  Verb(BabelPOS.VERB, POS.VERB);
  
  //===============================================================================================
  
  private final BabelPOS bn;
  private final POS      bm;

  private WordPart(BabelPOS bn, POS bm) {
    this.bn = bn;
    this.bm = bm;
  }

  //===============================================================================================

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
  
  //-----------------------------------------------------------------------------------------------

  /**
   * Returns Part from BabelNet POS.
   * @param pos source pos
   * @return part
   */
  public static Optional<WordPart> from(BabelPOS pos) {
    return Stream.of(WordPart.values())
        .filter(value -> value.forBabelNet().equals(pos))
        .findAny();
  }

  //-----------------------------------------------------------------------------------------------

  /**
   * Returns Part from BabelMorph POS.
   * @param pos source pos
   * @return part
   */
  public static Optional<WordPart> from(POS pos) {
    return Stream.of(WordPart.values())
        .filter(value -> value.forBabelMorph().equals(pos))
        .findAny();
  }
  
  //-----------------------------------------------------------------------------------------------
}
