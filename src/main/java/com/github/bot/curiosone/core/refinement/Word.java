package com.github.bot.curiosone.core.refinement;

import java.util.stream.Stream;

import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

public class Word {
  
  private String form;
  private Part   part;
  
  protected Word(String form, Part part) {
    this.form = form;
    this.part = part;
  }

  //===============================================================================================
  
  /**
   * Word constructor.
   */
  public Word(String form) {
    this(form, Part.None);
  }

  //-----------------------------------------------------------------------------------------------
  
  /**
   * Get the word's form.
   * @return form
   */
  public String getForm() {
    return form;
  }

  //-----------------------------------------------------------------------------------------------
  
  /**
   * Get the word's part.
   * @return part
   */
  public Part getPart() {
    return part;
  }
      
  //===============================================================================================
  
  /**
   * Word POS interoperability structure.
   */
  public enum Part {  
    Adjective(BabelPOS.ADJECTIVE, POS.ADJECTIVE),
    Adverb(BabelPOS.ADVERB, POS.ADVERB),
    Noun(BabelPOS.NOUN, POS.NOUN),
    Verb(BabelPOS.VERB, POS.VERB),
    None(null, null);
        
    private final BabelPOS bn;
    private final POS      bm;

    private Part(BabelPOS bn, POS bm) {
      this.bn = bn;
      this.bm = bm;
    }

    /**
     * Returns the corresponding BabelNet POS.
     * @return pos
     */
    public BabelPOS forBabelNet() {
      return bn;
    }
    
    /**
     * Returns the corresponding BabelMorph POS.
     * @return pos
     */
    public POS forBabelMorph() {
      return bm;
    }
    
    /**
     * Returns Part from BabelNet POS.
     * @param pos source pos
     * @return part
     */
    public static Part from(BabelPOS pos) {
      return Stream.of(Part.values())
          .filter(value -> value.forBabelNet().equals(pos))
          .findAny().orElse(None);
    }

    /**
     * Returns Part from BabelMorph POS.
     * @param pos source pos
     * @return part
     */
    public static Part from(POS pos) {
      return Stream.of(Part.values())
          .filter(value -> value.forBabelMorph().equals(pos))
          .findAny().orElse(None);
    }
  }
}
