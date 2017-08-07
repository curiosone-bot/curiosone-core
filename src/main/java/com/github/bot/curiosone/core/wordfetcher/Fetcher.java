package com.github.bot.curiosone.core.wordfetcher;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.data.BabelPOS;
import it.uniroma1.lcl.jlt.util.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fetcher {

  /**
   * Returns a list of sysnsets of a word.
   * @param word input word
   * @return list of synsets
   */
  public static final List<BabelSynset> getSynsets(String word) {
        
    BabelNet bn = BabelNet.getInstance();
        
    try {
      return bn.getSynsets(word, Language.EN);
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
  
  /**
   * Return the possible part of speech for a given word.
   * @param word input
   * @return list of POSs
   */
  public static final List<BabelPOS> getTypes(String word) {
    
    return Fetcher.getSynsets(word).stream()
        .map(BabelSynset::getPOS)
        .distinct()
        .collect(Collectors.toList());
  }
}
