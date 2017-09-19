package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class Main {
  
  @Test  
  public void testStemmer() throws IOException {
    
    assertTrue("run".equals(WnWrapper.toStem("running", null).get()));
    assertTrue("woman".equals(WnWrapper.toStem("women", null).get()));
    assertTrue("goose".equals(WnWrapper.toStem("geese", null).get()));
    /*
    assertTrue("run".equals(WnWrapper.toStem("running", POS.VERB).get()));
    assertTrue("woman".equals(WnWrapper.toStem("women", POS.NOUN).get()));
    assertTrue("goose".equals(WnWrapper.toStem("geese", POS.NOUN).get()));
*/
  }

  //-----------------------------------------------------------------------------------------------
}
