package com.github.bot.curiosone.core.nlp;

import com.github.bot.curiosone.core.util.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Represents a semantically complete Sentence.
 * A semantically complete Sentence is a list of words with a lookup check syntax table and a
 * boolean flag, stating whether the phrase is a question or not.
 * Contains utility method to easily manage, check and get semantically information about the
 * Sentence.
 */
public class Sentence {
  /**
   * The list of words of this Sentence.
   */
  private List<Word> words;

  /**
   * Whether this Sentence is a question or not.
   */
  private boolean question;

  /**
   * The lookup table used to check the syntax.
   */
  private Map<POS, TreeSet<Interval>> lookup;

  /**
   * Constructs this Sentence.
   * @param words the list of words to create this Sentence from
   * @param lookup the lookup table used to check syntax
   */
  private Sentence(List<Word> words, Map<POS, TreeSet<Interval>> lookup, boolean question) {
    this.words = words;
    this.lookup = lookup;
    this.question = question;
  }

  /**
   * Returns {@code true} if the original phrase from where the sentence was extracted ends with a
   * question mark.
   * {@code false} otherwise
   */
  public boolean isQuestion() {
    return question;
  }

  /**
   * Gets the List of words of the sentence.
   */
  public List<Word> getWords() {
    return words;
  }

  /**
   * Checks if a sentence contains a certain POS type.
   * @param pos the pos type to check
   * @return {@code true} if contained, {@code false} otherwise.
   */
  public boolean has(POS pos) {
    return lookup.getOrDefault(pos, new TreeSet<>()).size() > 0;
  }

  /**
   * Gets a List of Words of a certains POS type.
   * @param pos the pos type to extract
   * @return the list of words
   */
  public List<Word> get(POS pos) {
    List<Word> l = new ArrayList<>();
    for (Interval intr : lookup.getOrDefault(pos, new TreeSet<>())) {
      for (int j = intr.min(); j <= intr.max(); j++) {
        l.add(words.get(j));
      }
    }
    return l;
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
      for (Interval intr : lookup.getOrDefault(pos, new TreeSet<>())) {
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
   * Gets parameters from the sentence respecting the structure provided.
   * @param posl an array of POS to extract against
   * @return an array of list of strings, one per each POS in posl
   */
  public List<Word>[] parse(POS... posl) {
    int idx = 0;
    List<Word>[] l = (ArrayList<Word>[])new ArrayList[posl.length];
    for (int i = 0; i < posl.length; i++) {
      l[i] = new ArrayList<Word>();
    }

    for (int i = 0; i < posl.length; i++) {
      int oidx = idx;
      for (Interval intr : lookup.getOrDefault(posl[i], new TreeSet<>())) {
        if (intr.min() == idx) {
          for (int j = idx; j <= intr.max(); j++) {
            l[i].add(words.get(j));
          }
          idx = intr.max() + 1;
          break;
        }
      }
      if (oidx == idx) {
        // The whole structure is not respected. Returns the initial part that
        // matchs, if available.
        return l;
      }
    }
    return l;
  }

  /**
   * Returns a string representation of this sentence in the form [text, tokens].
   */
  @Override
  public String toString() {
    return "<" + words + ", " + lookup + ">";
  }

  /**
   * Compares this sentence to the specified object.
   * @param  other the other sentence to be compared against
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
   * Returns the HashCode for this Sentence.
   */
  @Override
  public int hashCode() {
    return Objects.hash(words, lookup);
  }

  /**
   * Extracts semantically complete sentences from a phrase using the CYK table.
   * @param phrase the Phrase to be splitted into Sentences
   * @return the Sentences of the given Phrase
   */
  public static List<Sentence> extract(Phrase phrase) {
    List<Token> tokens = phrase.getTokens();
    ParseTable table = new ParseTable(tokens);
    List<Sentence> l = new ArrayList<>();
    for (int y = 0; y < table.getHeight(); y++) {
      for (int x = 0; x < table.getWidthAt(y); x++) {
        Set<Rule> rules = table.get(x, y);
        for (Rule r : rules) {
          if (r.getFrom().equals(POS.S)) {
            Map<POS, TreeSet<Interval>> lookt = new HashMap<>();
            List<Set<Meaning>> means = new ArrayList<>(table.getHeight());
            for (int i = 0; i < table.getHeight(); i++) {
              means.add(new HashSet<>());
            }
            table.traverse(means, lookt, x, y, r);
            List<Word> words = new ArrayList<>(tokens.size());
            for (int i = 0; i < tokens.size(); i++) {
              Token token = tokens.get(i);
              words.add(new Word(token.getText(), token.getLemma(), means.get(i)));
            }
            l.add(new Sentence(words, lookt, phrase.isQuestion()));
          }
        }
      }
    }
    return l;
  }
}
