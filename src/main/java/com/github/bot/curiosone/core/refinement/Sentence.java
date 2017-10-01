package com.github.bot.curiosone.core.refinement;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.bot.curiosone.core.refinement.interfaces.Clause;

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
