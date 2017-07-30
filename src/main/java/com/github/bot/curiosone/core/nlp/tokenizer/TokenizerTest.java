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
  public static void main(String[] args) {
    Tokenizer t = new Tokenizer("ciao::co_me've .2.. va!");
    System.out.println(t.getType());
    t.getSentence();
    System.out.println(t.getType());
    System.out.println(t.getInputUser());
    System.out.println(t.getModifiedInputUser());
  }
}
