package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

public class KeepWord implements Word {

  private String stuff;
  
  public KeepWord(String stuff) {
    this.stuff = stuff;
  }
  
  @Override
  public String toString() {
    return stuff;
  }
  
}
