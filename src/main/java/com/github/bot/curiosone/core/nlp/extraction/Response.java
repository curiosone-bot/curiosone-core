package com.github.bot.curiosone.core.nlp.extraction;

import java.util.LinkedHashMap;
import java.util.Objects;
import com.github.bot.curiosone.core.knowledge.SemanticRelationType;
import com.github.bot.curiosone.core.nlp.cyk.POS;

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
  private LinkedHashMap<String, POS> words;
  
  /**
   * Constructor for the response.
   * @param relation The response semantic relation between subject and object.
   */
  public Response(SemanticRelationType relation) {
    
    this.relation = relation;
    
  }
  
  /**
   * Adds a word in the response.
   * @param word The word   
   * @param pos Word's POS value
   * @return The response itself
   */
  public Response add(String word, POS pos) {
    
    words.put(word, pos);
    
    return this;
    
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    words.entrySet().stream()
      .forEach(entry -> sb.append("< "+entry.getKey()+" : "+entry.getValue()+" >"));
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
