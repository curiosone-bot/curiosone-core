package com.github.bot.curiosone.core.nlp;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.github.bot.curiosone.core.util.Pair;

/**
 * A Rule in a context free grammar has a pair of POS values matching another
 * single POS. This trio describe a syntax procedure.
 */
public class Rule {
  /** Rules that belongs to the grammar. */
  private static Set<Rule> rules;

  /** Grammar file path. */
  private static final Path source = Paths.get("src/main/res/cyk/grammar.txt");

  /** The resulting POS value of joining those in 'to'. */
  private POS from;

  /** Pair of POS values that match with that in 'from'. */
  private Pair<POS, POS> to;

  /**
   * Constructor for a Rule.
   *
   * @param from resulting POS value of joining those in 'to'.
   * @param to pair of POS values that match with that in 'from'.
   * @return [description]
   */
  public Rule(POS from, Pair<POS, POS> to) {
    this.from = from;
    this.to = to;
  }

  /**
   * Returns the resulting POS value of joining those in 'to'.
   *
   * @return Resulting POS
   */
  public POS getFrom() {
    return from;
  }

  /**
   * Returns a pair of POS values that match with that in 'from'.
   *
   * @return a pair of POS values that match with that in 'from'.
   */
  public Pair<POS, POS> getTo() {
    return to;
  }

  /**
   * Returns a string representation of this rule.
   *
   * @return a string representation of this rule in the form F: (T0, T1)
   */
  @Override
  public String toString() {
    return from + ": " + to;
  }

  /**
  * Compares this transaction to the specified object.
  *
  * @param  other the other rule
  * @return {@code true} if this rule equals the other rule;
  *         {@code false} otherwise
  */
  public boolean equals(Object other) {
    if (other == this) return true;
    if (other == null) return false;
    if (other.getClass() != this.getClass()) return false;
    Rule that = (Rule) other;
    return this.from.equals(that.from) && this.to.equals(that.to);
  }

  /**
   * Compute a hash code using the hash codes of the underlying objects
   *
   * @return a hashcode of the Rule
   */
   @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  /**
   * Extracts all rules that has the same 'from' value as the one provided.
   *
   * @param from a POS value that has to match with that in 'from' of a rule
   * @return a set of rules that matches
   */
  public static Set<Rule> allFrom(POS from) {
    load(); // Make sure that rules has been loaded

    Set<Rule> matches = new HashSet<>();
    for (Rule r : rules) {
      if (r.getFrom().equals(from)) {
        matches.add(r);
      }
    }
    return matches;
  }

  /**
   * Extracts all rules that has the same 'to' value as the one provided.
   *
   * @param to a pair of POS values that has to match with that in 'to' of a rule
   * @return a set of rules that matches
   */
  public static Set<Rule> allTo(Pair<POS, POS> to) {
    load(); // Make sure that rules has been loaded

    Set<Rule> matches = new HashSet<>();
    for (Rule r : rules) {
      if (r.getTo().equals(to)) {
        matches.add(r);
      }
    }
    return matches;
  }

  /**
   * Loads the rules of the grammar.
   */
  private static void load() {
    if (rules != null) {
      return;
    }
    rules = new HashSet<Rule>();
    try (Stream<String> stream = Files.lines(source)) {
      stream.forEach(line -> {
        String[] values = line.split(" ");
        rules.add(
          new Rule(
            POS.valueOf(values[0]),
            Pair.create(POS.valueOf(values[1]), POS.valueOf(values[2]))
          )
        );
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
