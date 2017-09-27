package com.github.bot.curiosone.core.nlp;

import com.github.bot.curiosone.core.util.Pair;

import static com.github.bot.curiosone.core.util.TextConstants.TWO_DOTS_SEP;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;


/**
 * Utility class to handle a grammar rule.
 * A Rule in a context free grammar has a pair of POS values matching another single POS. This trio
 * describes a syntax procedure.
 * Provides useful methods to manage every single aspect of this syntax procedure.
 */
public class Rule {

  /**
   * Rules that belong to the grammar.
   */
  private static Set<Rule> rules;

  /**
   * Path to the Grammar file.
   */
  private static String rulesPath = "/cyk/grammar.txt";

  /**
   * The resulting POS value of joining those in 'to'.
   */
  private POS from;

  /**
   * Pair of POS values that match with that in 'from'.
   */
  private Pair<POS, POS> to;

  /**
   * Constructs this Rule.
   * @param from resulting POS value of joining those in 'to'.
   * @param to pair of POS values that match with that in 'from'.
   */
  public Rule(POS from, Pair<POS, POS> to) {
    this.from = from;
    this.to = to;
  }

  /**
   * Returns the resulting POS value of joining those in 'to'.
   */
  public POS getFrom() {
    return from;
  }

  /**
   * Returns a pair of POS values that match with that in 'from'.
   */
  public Pair<POS, POS> getTo() {
    return to;
  }

  /**
   * Returns a String representation of this Rule in the form F: (T0, T1).
   */
  @Override
  public String toString() {
    return from + TWO_DOTS_SEP + to;
  }

  /**
  * Compares this transaction to the specified object.
  * @param other the other Rule to be compared against
  * @return {@code true} if this rule equals the other rule;
  *         {@code false} otherwise
  */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    Rule that = (Rule) other;
    return this.from.equals(that.from) && this.to.equals(that.to);
  }

  /**
   * Returns the HashCode for this Rule. The HashCode is computed using the HashCodes of the
   * underlying objects.
   */
  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  /**
   * Extracts all rules that has the same 'from' value as the one provided.
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
   * @param to a pair of POS values that has to match with that in 'to' of a rule
   * @return a Set of rules that matches
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
    Path path = null;
    try {
      URL resource = Rule.class.getResource(rulesPath);
      path = Paths.get(resource.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    rules = new HashSet<Rule>();
    try (Stream<String> stream = Files.lines(path)) {
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
