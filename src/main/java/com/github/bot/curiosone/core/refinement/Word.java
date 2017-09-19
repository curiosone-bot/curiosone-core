package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import it.uniroma1.lcl.babelnet.data.BabelPOS;

public class Word {
  
  private String form;
  private BabelPOS pos;
  
  //===============================================================================================
  
  public Word(String form, BabelPOS pos) {
    this.form = form;
    this.pos = pos;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  public String getForm() {
    return form;
  }

  //-----------------------------------------------------------------------------------------------
    
  public BabelPOS getPOS() {
    return pos;
  }
  
  //-----------------------------------------------------------------------------------------------
}
