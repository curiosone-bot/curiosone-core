package com.github.bot.curiosone.core.wordfetcher;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class Main {
  
  @Test  
  public void testStemmer() throws IOException {
    
    assertTrue("run".equals(Stemmer.toStem("running")));
    assertTrue("woman".equals(Stemmer.toStem("women")));
    assertTrue("goose".equals(Stemmer.toStem("geese")));
  }

}
