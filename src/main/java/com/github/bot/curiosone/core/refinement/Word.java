package com.github.bot.curiosone.core.refinement;

import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

import java.util.stream.Stream;

public class Word {
  
  private String form;
  private WordPart   part;
  
  protected Word(String form, WordPart part) {
    this.form = form;
    this.part = part;
  }

  //===============================================================================================
  
  /**
   * Word constructor.
   */
  public Word(String form) {
    this(form, WordPart.None);
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
  public WordPart getPart() {
    return part;
  }
      
  //===============================================================================================
  

}
