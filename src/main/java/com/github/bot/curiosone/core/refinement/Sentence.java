package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Clause;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Sentence {

  private List<Clause> clauses;
  private Type type;
  
  /**
   * Type of sentence.
   */
  public enum Type {
    Question,
    Answer;
  }
  
  /**
   * Sentence constructor.
   */
  public Sentence(Type type) {
    clauses = new LinkedList<Clause>();
    this.type = type;
  }
  
  /**
   * Add a clause.
   * @param clause clause
   */
  public void addClause(Clause clause) {
    clauses.add(clause);
  }
  
  /**
   * Returns the sentence type.
   * @return type
   */
  public Type getSentenceType() {
    return type;
  }
  
  /**
   * Refinement entry point.
   */
  @Override
  public String toString() {
    String temp = clauses.stream()
        .map(Clause::toString)
        .collect(Collectors.joining(" ", "", type.equals(Type.Question) ? "?" : "."));
    return temp.substring(0, 1).toUpperCase() + temp.substring(1);
  }
  
}
