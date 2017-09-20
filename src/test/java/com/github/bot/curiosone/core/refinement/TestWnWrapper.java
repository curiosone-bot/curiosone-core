package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import edu.mit.jwi.item.POS;

import org.junit.Test;

public class TestWnWrapper {
  
  @Test  
  public void toStemTest() {
        
    System.out.println(WordNet.getStem("running", POS.VERB));
    System.out.println(WordNet.getStem("jumping", POS.VERB));
    System.out.println(WordNet.getStem("swimming", POS.VERB));
    System.out.println(WordNet.getStem("gone", POS.VERB));
    System.out.println(WordNet.getStem("went", POS.VERB));
    System.out.println(WordNet.getStem("shot", POS.VERB));
    System.out.println(WordNet.getStem("is", POS.VERB));
    assertTrue(true);
  }

  //-----------------------------------------------------------------------------------------------
}
