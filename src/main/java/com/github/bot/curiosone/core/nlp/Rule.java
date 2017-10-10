package com.github.bot.curiosone.core.nlp;

import com.github.bot.curiosone.core.util.Pair;

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
 * Utility class to handle a grammar Rule.
 * A Rule in a context free Grammar is a trio, consisting of a source POS and a pair of target POS.
 * Only Sentences that respect the "Source->Pair of target POS" relation are allowed in the Grammar.
 * Provides useful methods to manage every single aspect of the Rule.
 * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
 * @see  com.github.bot.curiosone.core.util.Pair The Pair Class
 */
public class Rule {

  /**
   * Rules that belong to the used Grammar.
   */
  private static Set<Rule> rules;

  /**
   * Path to the Grammar file.
   */
  private static String rulesPath = "/cyk/grammar.txt";

  /**
   * The source POS of this Grammar Rule.
   * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
   */
  private POS from;

  /**
   * The target POS of this Grammar Rule.
   * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
   * @see  com.github.bot.curiosone.core.util.Pair The Pair Class
   */
  private Pair<POS, POS> to;

  /**
   * Constructs this Rule.
   * @param  from
   *         resulting POS value of joining those in 'to'.
   * @param  to
   *         pair of POS values that match with that in 'from'.
   */
  public Rule(POS from, Pair<POS, POS> to) {
    this.from = from;
    this.to = to;
  }

  /**
   * @return  the resulting POS value of joining those in 'to'.
   */
  public POS getFrom() {
    return from;
  }

  /**
   * @return  a Pair of POS values that match with that in 'from'.
   */
  public Pair<POS, POS> getTo() {
    return to;
  }

  /**
   * @return  a String representation of this Rule in the form F: (T0, T1).
   */
  @Override
  public String toString() {
    return from + ": " + to;
  }

  /**
  * Checks whether this transaction equals to the specified object.
  * @param  other
  *         the other Rule to be compared against
  * @return  {@code true} if this rule equals the other rule;
  *          {@code false} otherwise
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
   * Calculates the HashCode for this Rule.
   * The HashCode depends on all of the POS involved in the Rule.
   * @return  the HashCode of this Rule
   */
  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  /**
   * Extracts all the Rules of the Grammar with the provided POS as source.
   * @param  from
   *         the POS value indicating the desired source
   * @return  a Set containing all the Rules of the Grammar with the given POS as source
   *
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
   * Extracts all the Rules of the Grammar that have the given Pair instance as target.
   * @param  to
   *         the Pair instance containing the desired target
   * @return  a Set containing all the Rules of the Grammar with the given Pair as target.
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
   * Loads the Rules of the Grammar.
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
