package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISynset;

import java.util.List;
import java.util.Map;

/**
 * Syntax/Semantic information of a Word.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see ISynset
 */
public class Synset implements ISynset {

  /**
   * Part of speech.
   *
   * @see POST
   */

  private Post pos;

  /**
   * Lexicographic file name.
   *
   * @see LexT
   */

  private LexT lexType;

  /**
   * Lemma of word.
   *
   * @see Word
   */

  private String lemma;

  /**
   * Glossary information.
   */
  private String gloss;

  /**
   * Semantic relation with other word.
   *
   * @see PointerT
   */

  private Map<PointerT, List<String>> relations;

  /**
   * Get pos.
   *
   * @return the pos
   * @see #pos
   */

  @Override public Post getPost() {
    return pos;
  }

  /**
   * Set a new {@link #pos} value that is provided in input.
   *
   * @see #pos
   */

  public void setPos(Post pos) {
    this.pos = pos;
  }

  /**
   * Get lexType.
   *
   * @return lexType
   * @see #lexType
   */

  @Override public LexT getLexType() {
    return lexType;
  }

  /**
   * Set a new {@link #lexType} value that is provided in input.
   *
   * @see #lexType
   */

  public void setLexType(LexT lexType) {
    this.lexType = lexType;
  }

  /**
   * Get lemma.
   *
   * @return the lemma
   * @see #lemma
   */

  @Override public String getLemma() {
    return lemma;
  }

  /**
   * Set a new {@link #lemma} value that is provided in input.
   *
   * @see #lemma
   */

  public void setLemma(String lemma) {
    this.lemma = lemma;
  }

  /**
   * Get gloss.
   *
   * @return the gloss
   * @see #gloss
   */
  @Override public String getGloss() {
    return gloss;
  }

  /**
   * Set a new {@link #gloss} value that is provided in input.
   *
   * @see #gloss
   */
  public void setGloss(String gloss) {
    this.gloss = gloss;
  }

  /**
   * Get relations.
   *
   * @return the relations
   * @see #relations
   */
  public Map<PointerT, List<String>> getRelations() {
    return relations;
  }

  /**
   * Add a new element to reletions.
   *
   * @see #relations
   */

  public void addRelation(PointerT p, List<String> value) {
    this.relations.put(p, value);
  }
}
