package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import it.uniroma1.lcl.babelmorph.POS;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

import org.junit.Test;

public class WordTest {

  @Test
  public void main() {
    assertTrue(WordPart.from(BabelPOS.ADVERB).equals(WordPart.from(POS.ADVERB)));
    assertTrue(WordPart.from(BabelPOS.NOUN).equals(WordPart.Noun));
    assertTrue(WordPart.from(POS.VERB).equals(WordPart.Verb));
    
    new Word("unknown", WordPart.Noun).getLemma().forEach(System.out::println);
    new Word("queens", WordPart.Noun).getLemma().forEach(System.out::println);
    new Word("unbound", WordPart.Noun).getLemma().forEach(System.out::println);
    new Word("greatest", WordPart.Noun).getLemma().forEach(System.out::println);
    new Word("knew", WordPart.Noun).getLemma().forEach(System.out::println);


  }
}
