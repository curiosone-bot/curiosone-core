package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import edu.mit.jwi.item.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;
import java.util.Optional;

public class Verb extends Word {
    
  public Verb(String form) {
    super(form, BabelPOS.VERB);
  }
  
  //-----------------------------------------------------------------------------------------------
  
  public String getBaseForm() {
    Optional<String> stem = WnWrapper.toStem(this.getForm(), POS.VERB);
    return stem.isPresent() ? stem.get() : this.getForm();
  }
  
  //-----------------------------------------------------------------------------------------------
}
