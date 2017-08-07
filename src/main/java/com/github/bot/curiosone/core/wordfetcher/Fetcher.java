package com.github.bot.curiosone.core.wordfetcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.jlt.util.Language;

public class Fetcher {

  /**
   * Returns a list of sysnsets of a word.
   * @param word input surface word
   * @return list of synsets
   */
  public static final List<BabelSynset> getSynsets(String word) {
    
    // search for the root word, exit on failure
    
    Optional<String> stem = Stemmer.search(word);
    
    if (!stem.isPresent()) {
      return new ArrayList<>();
    }
    
    // interrogate BabelNet
    
    BabelNet bn = BabelNet.getInstance();
        
    try {
      return bn.getSynsets(stem.get(), Language.EN);
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
}
