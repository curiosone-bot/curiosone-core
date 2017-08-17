package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Provvisory class.
 *
 * @author Eugenio
 */
public class TokenizerTest  {
  /**
   * temporary.
   */
  public static void main(String[] args) throws TokenNotFound {
    Tokenizer t = new Tokenizer("Hi, how's going U.S.A., http://hfjdi/deifi0/cnd fee.cas/a@gm.ail.com 163.100.98.7 go!");
    System.out.println(t.getType());
    t.getSentence();
    System.out.println(t.getInputUser());
    //    System.out.println(t.checkPunct());
    System.out.println(t.getStringToken());
    System.out.println(t.getType());

  }
}
