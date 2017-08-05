package com.github.bot.curiosone.core.wordfetcher;

public class Fetcher {

  /**
   * wip.
   * @param word aaa
   */
  public static void fetch(String word) {
    
    String stem = Stemmer.search(word).get();
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
