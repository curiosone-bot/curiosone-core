package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.LexT;
import com.github.bot.curiosone.core.nlp.tokenizer.PointerT;
import com.github.bot.curiosone.core.nlp.tokenizer.Post;
import com.github.bot.curiosone.core.nlp.tokenizer.Synset;

import java.util.List;
import java.util.Map;

/**
 * Syntact/Semantic attribute of a word.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see https://projects.csail.mit.edu/jwi/api/index.html
 */
public interface ISynset {

  /**
   * Get pos.
   *
   * @see Synset#getPost()
   */

  Post getPost();

  /**
   * Get lexType.
   *
   * @see Synset#getLexType()
   */

  LexT getLexType();

  /**
   * Get lemma.
   *
   * @see Synset#getLemma()
   */

  String getLemma();

  /**
   * Get gloss.
   *
   * @see Synset#getGloss()
   */

  String getGloss();

  /**
   * Get relations.
   *
   * @see Synset#getRelations()
   */

  Map<PointerT, List<String>> getRelations();
}
