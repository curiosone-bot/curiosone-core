package com.github.bot.curiosone.core.wordfetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fetcher {

  public static void fetch(String word) throws IOException {
    
    String stem = Stemmer.toStem(word);
    /*
    //init
    BabelNet bn = BabelNet.getInstance();
    
    //synsets
    List<BabelSynset> syl = bn.getSynsets(w, Language.EN, BabelPOS.VERB, BabelSenseSource.WN);      
    
    //senses
    List<BabelSense>  sel = new ArrayList<>();
    syl.forEach(sy -> sel.add(sy.getMainSense(Language.EN)));
    
    
    sel.forEach(se -> System.out.println(se)); // <- DELETE ME
    */
  }
}
