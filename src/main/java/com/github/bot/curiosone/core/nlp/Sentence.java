package com.github.bot.curiosone.core.nlp;

import com.github.bot.curiosone.core.util.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
  private Map<String,Set<Meaning>> words;

  /** The lookup table used to check the syntax. */
  private Map<POS, TreeSet<Interval>> lookup;

  /**
   * Constructor of a Sentence.
   * @param phrase the original phrase from where the sentence was extracted
   * @param lookup the lookup table to use to check syntax
   */
  private Sentence(Phrase phrase, Map<POS, TreeSet<Interval>> lookup) {
    this.lookup = lookup;
    words = new LinkedHashMap<>();
    Interval start = lookup.get(POS.S).first();
    words = phrase.getTokens()
        .subList(start.min(), start.max() + 1)
        .stream()
        .collect(Collectors.toMap(Token::getText, Token::getMeanings) );
  }

  /**
   * Gets the list of words of the sentence.
   * @return the list of words of the sentence
   */
  public Map<String, Set<Meaning>> getWords() {
    return words;
  }

  /**
   * Checks whether the sentence respect the grammar structure provided.
   * @param posl an array of POS to check against
   * @return {@code true} if all POS can be found in the given order;
   *         {@code false} otherwise
   */
  public boolean respect(POS... posl) {
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
  public Map<String, List<POS>>[] get(POS... posl) {
    int idx = 0;
    Map<String,List<POS>>[] l = (Map<String,List<POS>>[]) new Map[posl.length];
    for (int i = 0; i < posl.length; i++) {
      l[i] = new LinkedHashMap<String,List<POS>>();
    }

    for (int i = 0; i < posl.length; i++) {
      int oidx = idx;
      for (Interval intr : lookup.get(posl[i])) {
        if (intr.min() == idx) {
          for (int j = idx; j <= intr.max(); j++) {

            String word = new ArrayList<String>(words.keySet()).get(j);

            List<POS> pos = new ArrayList<>();
            words.get(word).forEach(m -> pos.add(m.getPOS()));

            l[i].putIfAbsent( word , pos );

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
    ParseTable table = new ParseTable(phrase.getTokens());
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
