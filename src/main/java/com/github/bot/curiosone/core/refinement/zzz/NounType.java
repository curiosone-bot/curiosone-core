package com.github.bot.curiosone.core.refinement.zzz;

public enum NounType {
  Singular("SINGULAR"),
  Plural("PLURAL");
  
  private String type;
  private NounType(String type) {
    this.type = type;
  }
  
  public String get() {
    return type;
  }
}
