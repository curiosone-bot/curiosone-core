package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import edu.mit.jwi.item.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

public class Verb extends Word {
    
  public Verb(String form) {
    super(form, BabelPOS.VERB);
  }
  
  //-----------------------------------------------------------------------------------------------
  
  public String getStem() {
    return WnWrapper.getStem(getForm(), POS.VERB);
  }
  
  //-----------------------------------------------------------------------------------------------
}
