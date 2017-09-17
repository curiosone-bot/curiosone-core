package com.github.bot.curiosone.core.nlp;

import com.github.bot.curiosone.core.util.Interval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Semantically complete sentence.
 */
public class Sentence {
  /** The list of words of the sentence. */
  private List<String> words;

  /** The lookup table used to check the syntax. */
  private Map<POS, TreeSet<Interval>> lookup;

  /**
   * Constructor of a Sentence.
   * @param phrase the original phrase from where the sentence was extracted
   * @param lookup the lookup table to use to check syntax
   */
  private Sentence(Phrase phrase, Map<POS, TreeSet<Interval>> lookup) {
    this.lookup = lookup;
    words = new ArrayList<>();
    Interval start = lookup.get(POS.S).first();
    words = phrase.getTokens()
        .subList(start.min(), start.max() + 1)
        .stream()
        .map(Token::getText)
        .collect(Collectors.toList());
  }

  /**
   * Gets the list of words of the sentence.
   * @return the list of words of the sentence
   */
  public List<String> getWords() {
    return words;
  }

  /**
   * Checks whether the sentence respect the grammar structure provided.
   * @param posl an array of POS to check against
   * @return {@code true} if all POS can be found in the given order;
   *         {@code false} otherwise
   */
  public boolean respect(POS[] posl) {
    int done = 0;
    int idx = 0;
    for (POS pos : posl) {
      int oidx = idx;
      for (Interval intr : lookup.get(pos)) {
        if (intr.min() == idx) {
          idx = intr.max() + 1;
          break;
        }
      }
      if (oidx == idx) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets parameters from the sentence
   * @param posl an array of POS to extract against
   * @return an array of list of strings, one per each POS in posl
   */
  public List<String>[] get(POS[] posl) {
    int idx = 0;
    List<String>[] l = (ArrayList<String>[])new ArrayList[posl.length];
    for (int i = 0; i < posl.length; i++) {
      l[i] = new ArrayList<String>();
    }

    for (int i = 0; i < posl.length; i++) {
      int oidx = idx;
      for (Interval intr : lookup.get(posl[i])) {
        if (intr.min() == idx) {
          for (int j = idx; j <= intr.max(); j++) {
            l[i].add(words.get(j));
          }
          idx = intr.max() + 1;
          break;
        }
      }
      if (oidx == idx) {
        return l;
      }
    }
    return l;
  }

  /**
   * Returns a string representation of this sentence.
   *
   * @return a string representation of this sentence in the form [text, tokens]
   */
  @Override
  public String toString() {
    return "<" + words + ", " + lookup + ">";
  }

  /**
   * Compares this sentence to the specified object.
   *
   * @param  other the other sentence
   * @return {@code true} if this sentence equals the other sentence;
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
    Sentence that = (Sentence) other;
    return this.words.equals(that.words);
  }

  /**
   * Returns an integer hash code for this sentence.
   *
   * @return an integer hash code for this sentence
   */
  @Override
  public int hashCode() {
    return Objects.hash(words, lookup);
  }

  /**
   * Extracts semantically complete sentences from a phrase using the CYK table.
   *
   * @param phrase the phrase to be splitted in sentences
   * @return the sentences of the given phrase
   */
  public static List<Sentence> extract(Phrase phrase) {
    CYK table = new CYK(phrase.getTokens());
    // System.out.println(table);
    List<Sentence> l = new ArrayList<>();

    for (int y = 0; y < table.getHeight(); y++) {
      for (int x = 0; x < table.getWidthAt(y); x++) {
        Set<Rule> rules = table.get(x, y);
        for (Rule r : rules) {
          if (r.getFrom().equals(POS.S)) {
            Map<POS, TreeSet<Interval>> kpt = new HashMap<>();
            table.intervals(kpt, x, y, r);
            l.add(new Sentence(phrase, kpt));
          }
        }
      }
    }
    // System.out.println(l);
    return l;
  }
}
