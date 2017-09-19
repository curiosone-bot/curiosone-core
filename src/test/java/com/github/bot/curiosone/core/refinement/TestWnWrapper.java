package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.mit.jwi.item.POS;

public class TestWnWrapper {
  
  @Test  
  public void toStemTest() {
        
    assertTrue("run".equals(WnWrapper.toStem("running", POS.VERB).get()));
    assertTrue("woman".equals(WnWrapper.toStem("women", POS.NOUN).get()));
    assertTrue("goose".equals(WnWrapper.toStem("geese", POS.NOUN).get()));

  }

  //-----------------------------------------------------------------------------------------------
}
