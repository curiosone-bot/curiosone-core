package com.github.bot.curiosone.core.nlp.extraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.github.bot.curiosone.core.knowledge.SemanticRelationType;
import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Word;

/**
 * A response from the Curiosone yet to be refined.
 *
 */
public class Response {
  
  /**
   * @see SemanticRelationType
   */
  private SemanticRelationType relation;
  
  /**
   * Words composing the response mapped to their POS value.
   */
  private List<Word> words;
  
  /**
   * If {@code true} the response must be refined.
   * If {@code false} the output is ready.
   */
  private boolean raw = true;
  
  /**
   * Constructor for the response.
   * @param relation The response semantic relation between subject and object.
   */
  public Response(SemanticRelationType relation) {
    
    this.relation = relation;
    this.words = new ArrayList<>();
    
  }
  
  /**
   * @see Response
   */
  public Response() {
    this(null);
  }
  
  /**
   * Adds a word in the response.
   * @param word 
   * @param pos
   * @return The response itself
   */
  public Response add(Word word) {
    
    words.add(word);
    
    return this;
    
  }
  
  public List<Word> getWords() {
    return words;
  }
  
  public SemanticRelationType getRelation() {
    return relation;
  }
  
  public boolean isRaw() {
    return raw;
  }

  protected void setRaw(boolean raw) {
    this.raw = raw;
  }
  
  /**
   * If the response doesn't need ro be refined, the output will be ready for the client.
   * @return The response.
   */
  public String getOutput() {
    
    if (!raw) {
    return words.stream()
        .map(Word::getLemma)
        .collect(Collectors.joining());
    }
    
    return null;
    
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    words.stream()
      .forEach(word -> sb.append("< "+word.getLemma()+" : "+word.getMeanings().stream()
          .map(Meaning::getPOS)
          .map(POS::toString)
          .collect(Collectors.joining(","))+" >"));
    return sb.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    Response that = (Response) other;
    return this.words.equals(that.words) && this.relation.equals(that.relation);
  }


  @Override
  public int hashCode() {
    return Objects.hash(relation, words);
  }

}
