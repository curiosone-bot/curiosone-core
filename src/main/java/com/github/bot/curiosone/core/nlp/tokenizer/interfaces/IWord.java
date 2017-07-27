package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.Word;
import java.util.List;

/**
 * Word info from Dictionary.
 * @author Andrea Rivetto && Eugenio Schintu
 */
public interface IWord {

  /**
   * Get value.
   * @see Word#getValue()
   */

  public String getValue();

  /**
   * Get synsets.
   * @see Word#getSynsets()
   */

  public List<ISynset> getSynsets();

  /**
   * Get lemma.
   * @see Word#getLemma()
   */

  public String getLemma();

  /**
   * Get known.
   * @see Word#isKnown()
   */

  public boolean isKnown();
}