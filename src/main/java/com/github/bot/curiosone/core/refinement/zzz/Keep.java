package com.github.bot.curiosone.core.refinement.zzz;

import java.util.stream.Stream;

public class Keep implements Token {

  private String s;
  
  public Keep(String s) {
    this.s = s;
  }
  
  @Override
  public String toString() {
    return s;
  }
  
  @Override
  public Stream<Token> refine() {
    return Stream.of(this);
  }
}
