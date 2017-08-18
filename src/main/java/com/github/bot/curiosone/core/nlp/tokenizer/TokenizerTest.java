package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Provvisory class.
 *
 * @author Eugenio
 */
public class TokenizerTest {
  /**
   * temporary.
   */
  public static void main(String[] args) throws Exception {
    Tokenizer t = new Tokenizer("hi how is U.S.A., car me what house?");
    //    System.out.println(t.getType());
    t.getSentence();
    //    System.out.println(t.getType());
    //      System.out.println(t.getInputUser());
    //    System.out.println(t.checkPunct());
    //    System.out.println(t.getStringToken());
    System.out.println(t.getTokens());
  }
}
