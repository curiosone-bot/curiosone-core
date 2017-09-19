package com.github.bot.curiosone.core.nlp.extraction;

import com.github.bot.curiosone.core.knowledge.SemanticNetwork;
import com.github.bot.curiosone.core.nlp.cyk.Sentence;

/**
 * Parsed sentences go through these routes for their 
 * parameters to be extracted, so to generate a new {@link SemanticQuery} in the knowledge net
 * and finally building a response.
 *
 */
public interface ExtractionRoute {
  
  /**
   * Parsed sentences get most significant parameters extracted and sent to the {@link SemanticNetwork} 
   * in order to generate a response.
   * @param s The <em>parsed</em> sentence
   * @return A response phrase ready to be refined.
   */
  Response extract(Sentence s);


}
