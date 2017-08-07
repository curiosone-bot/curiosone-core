package com.github.bot.curiosone.core.wordfetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.data.BabelPOS;
import it.uniroma1.lcl.babelnet.data.BabelSenseSource;
import it.uniroma1.lcl.jlt.util.Language;

public class Fetcher {

  /**
   * wip.
   * @param word aaa
   * @throws IOException 
   */
  public static void fetch(String word) throws IOException {
    
    String w = Stemmer.search(word).get();
    
    BabelNet bn = BabelNet.getInstance();
    
    List<BabelSynset> syl = bn.getSynsets(w, Language.EN, BabelPOS.VERB, BabelSenseSource.WN);      
    
    List<BabelSense>  sel = new ArrayList<>();
    syl.forEach(sy -> sel.add(sy.getMainSense(Language.EN)));
    
    sel.forEach(se -> System.out.println(se)); // <- DELETE ME
  }
}
