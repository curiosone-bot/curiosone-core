package com.github.bot.curiosone.core.refinement.zzz;
/**
 * Element of a sentence.
 * @author Claudio Venanzi
 */

import java.util.stream.Stream;

public interface Token {
  
  @Override
  public String toString();
  
  public Stream<Token> refine();
}
