package com.github.bot.curiosone.core.wordfetcher;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class Main {
  
  @Test  
  public void testStemmer() throws IOException {
    
    assertTrue("run".equals(Stemmer.search("running").get()));
    assertTrue("woman".equals(Stemmer.search("women").get()));
    assertTrue("goose".equals(Stemmer.search("geese").get()));
    
    /* REQUIRES WORKING KEY
      
    System.out.println("\nrun\n");
    Fetcher.getTypes("run").forEach(System.out::println);
    System.out.println("\nwoman\n");
    Fetcher.getTypes("woman").forEach(System.out::println);
    System.out.println("\ngoose\n");
    Fetcher.getTypes("goose").forEach(System.out::println);
    
    */
  }
  
  //-----------------------------------------------------------------------------------------------
  
  @Test  
  public void testContains() throws IOException {
    
    assertTrue(Stemmer.contains("run"));
    assertFalse(Stemmer.contains("supercalifragilisticoespiralidoso"));
  }
}
