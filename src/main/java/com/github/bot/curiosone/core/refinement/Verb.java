package com.github.bot.curiosone.core.refinement;

import it.uniroma1.lcl.babelmorph.POS;

/**
 * @author Claudio Venanzi
 */

public class Verb extends Word {
    
  public Verb(String form) {
    super(form, POS.VERB);
  }
  
  //-----------------------------------------------------------------------------------------------
  /*
  public String getStem() {
    return WnWrapper.getStem(getForm(), POS.VERB);
  }
  */
  //-----------------------------------------------------------------------------------------------
}
