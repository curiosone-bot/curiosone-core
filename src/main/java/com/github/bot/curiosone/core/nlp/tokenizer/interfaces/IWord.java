package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.Word;

import java.util.List;

/**
 * Word info from Dictionary.
 *
 * @author Andrea Rivetto && Eugenio Schintu
 */
public interface IWord {

  /**
   * Get value.
   *
   * @see Word#getValue()
   */

  String getValue();

  /**
   * Get synsets.
   *
   * @see Word#getSynsets()
   */

  List<ISynset> getSynsets();

  /**
   * Get lemma.
   *
   * @see Word#getLemma()
   */

  String getLemma();

  /**
   * Get known.
   *
   * @see Word#isKnown()
   */

  boolean isKnown();
}
