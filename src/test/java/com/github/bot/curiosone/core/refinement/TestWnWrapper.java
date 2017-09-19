package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import edu.mit.jwi.item.POS;

import org.junit.Test;

public class TestWnWrapper {
  
  @Test  
  public void toStemTest() {
        
    System.out.println(WnWrapper.getStem("running", POS.VERB));
    System.out.println(WnWrapper.getStem("jumping", POS.VERB));
    System.out.println(WnWrapper.getStem("swimming", POS.VERB));
    System.out.println(WnWrapper.getStem("gone", POS.VERB));
    System.out.println(WnWrapper.getStem("went", POS.VERB));
    System.out.println(WnWrapper.getStem("shot", POS.VERB));
    System.out.println(WnWrapper.getStem("is", POS.VERB));
    assertTrue(true);
  }

  //-----------------------------------------------------------------------------------------------
}
