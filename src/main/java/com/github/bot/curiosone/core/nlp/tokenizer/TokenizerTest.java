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
    Tokenizer t = new Tokenizer("ciao::co_me's going casa@gmail.com 9.02... va!");
    System.out.println(t.getType());
    t.getSentence();
    System.out.println(t.getType());
    System.out.println(t.getInputUser());
    System.out.println(t.getModifiedInputUser());
    t.createListOfTokens();
    t.getTokens().stream().forEach(it -> System.out.println(it.getWord().getValue()));
  }
}
