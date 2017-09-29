package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.LexType;
import com.github.bot.curiosone.core.nlp.tokenizer.PosType;
import edu.mit.jwi.item.IWordID;
import java.util.List;
import java.util.Map;

/**
 * Syntact/Semantic attribute of a word.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see https://projects.csail.mit.edu/jwi/api/index.html
 */
public interface IWord {

  /**
   * Get pos.
   *
   * @see Word#getPos()
   */

  PosType getPos();

  /**
   * Get lexType.
   *
   * @see Word#getLexType()
   */

  LexType getLexType();

  /**
   * Get lemma.
   *
   * @see Word#getLemma()
   */

  String getLemma();

  /**
   * Get WordID.
   *
   * @see IWordID()
   */

  IWordID getWordId();

  /**
   * Get gloss.
   *
   * @see Word#getGloss()
   */

  String getGloss();

  /**
   * Get number of occurence.
   *
   */

  int getNum();

  /**
   * Get relations.
   *
   * @see Word#getRelations()
   */

  Map<String, List<String>> getRelations();

  /**
   * Get relations by PointerT.
   *
   * @see Word#getRelationsByPointerT()
   */
  List<String> getRelationsByPointer(String pointer);

  /**
   * Set a new {@link #wordId} value that is provided in input.
   *
   * @see #wordId
   */

  void setWordId(IWordID wordId);

  /**
   * Set a new {@link #pos} value that is provided in input.
   *
   * @see #pos
   */

  void setPos(PosType pos);

  /**
   * Set a new {@link #lexType} value that is provided in input.
   *
   * @see #lexType
   */

  void setLexType(LexType lexType);

  /**
   * Set a new {@link #lemma} value that is provided in input.
   *
   * @see #lemma
   */

  void setLemma(String lemma);

  /**
   * Set a new {@link #gloss} value that is provided in input.
   *
   * @see #gloss
   */

  void setGloss(String gloss);

  /**
   * Set the number of occurrence of word.
   *
   */
  void setNum(int num);

  /**
   * Add a new element to relations.
   *
   * @see #relations
   */

  void addRelation(String p, String value);

  /**
   * Set the map of relations.
   *
   * @see #relations
   */

  void setRelations(Map<String, List<String>> relations);

}
