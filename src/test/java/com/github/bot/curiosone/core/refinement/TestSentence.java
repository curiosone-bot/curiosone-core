package com.github.bot.curiosone.core.refinement;

import org.junit.Test;

public class TestSentence {

  @Test
  public void main() {
        
    System.out.println(Refiner.refine("cat", "be", "animal", false, false, false));
    System.out.println(Refiner.refine("cat", "be", "animal", false, false, true));
    System.out.println(Refiner.refine("cat", "be", "animal", false, true, false));
    System.out.println(Refiner.refine("cat", "be", "animal", false, true, true));

    System.out.println(Refiner.refine("cat", "be", "animal", true, false, false));
    System.out.println(Refiner.refine("cat", "be", "animal", true, false, true));
    System.out.println(Refiner.refine("cat", "be", "animal", true, true, false));
    System.out.println(Refiner.refine("cat", "be", "animal", true, true, true));

  }
  
}
