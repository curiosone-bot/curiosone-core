package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.Optional;

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
  
  public String getBaseForm() {
    Optional<String> stem = WnWrapper.toStem(form, null);
    return stem.isPresent() ? stem.get() : form;
  }

  //-----------------------------------------------------------------------------------------------
  
  public BabelPOS getPOS() {
    return pos;
  }
  
  //-----------------------------------------------------------------------------------------------
}
