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
 * A semantically complete Sentence is a list of Words with a lookup check syntax table and a
 * boolean flag, stating whether the Sentence is a question or not.
 * Contains utility method to easily manage, check and get semantically information about the
 * Sentence.
 * @see  com.github.bot.curiosone.core.nlp.Word The Word Class
 */
public class Sentence {

  /**
   * Lists the Words of this Sentence.
   * @see  com.github.bot.curiosone.core.nlp.Word The Word Class
   */
  private List<Word> words;

  /**
   * Whether this Sentence is a question or not.
   */
  private boolean question;

  /**
   * The lookup table used to check the syntax.
   * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
   * @see  com.github.bot.curiosone.core.util.Interval The Interval Class
   */
  private Map<POS, TreeSet<Interval>> lookup;

  /**
   * Constructs this Sentence.
   * @param  words
   *         the List of Words of this Sentence
   * @param  lookup
   *         the lookup table used to check syntax of this Sentence
   * @see  com.github.bot.curiosone.core.nlp.Word The Word Class
   * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
   * @see  com.github.bot.curiosone.core.util.Interval The Interval Class
   */
  private Sentence(List<Word> words, Map<POS, TreeSet<Interval>> lookup, boolean question) {
    this.words = words;
    this.lookup = lookup;
    this.question = question;
  }

  /**
   * Gets whether this Sentence is a question or not.
   * @return  {@code true} if the original phrase from where the sentence was extracted ends with a
   *          question mark;
   *          {@code false} otherwise
   */
  public boolean isQuestion() {
    return question;
  }

  /**
   * Gets the Words of this Sentence.
   * @return  the List of Words of the Sentence
   * @see  com.github.bot.curiosone.core.nlp.Word The Word Class
   */
  public List<Word> getWords() {
    return words;
  }

  /**
   * Checks whether this Sentence contains the given POS or not.
   * @param  pos
   *         the desired POS
   * @return  {@code true} if this Sentence contains the given POS;
   *          {@code false} otherwise.
   * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
   */
  public boolean has(POS pos) {
    return lookup.getOrDefault(pos, new TreeSet<>()).size() > 0;
  }

  /**
   * Gets a List of Words with the given POS type.
   * @param  pos
   *         the desired POS
   * @return  a List containing all the Words of this Phrase with the given POS.
   * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
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
   * Checks whether the Sentence respects the provided Grammar structure.
   * A Sentence respects the provided Grammar structure if and only if it contains all the given POS
   * in the given order.
   * @param  posl
   *         An array of POS. This array represents the Grammar structure that this Sentence should
   *         respect.
   * @return  {@code true} if this Sentence respects the given Grammar structure;
   *          {@code false} otherwise
   * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
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
   * Gets the Words of this Sentence that respect the given Grammar structure.
   * Could return a partition of this Sentence, if the whole Sentence does not respect the given
   * Grammar structure but the partition does.
   * @param  posl
   *         An array of POS. This Array holds the Grammar structure to be respected.
   * @return  a List of Words.
   *          Theese are the Words that respect the provided Grammar structure
   * @see  com.github.bot.curiosone.core.nlp.POS The POS Enum
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
   * Returns a String representation of this Sentence.
   * @return  a String representation of this Sentence, formatted as: [text, tokens]
   */
  @Override
  public String toString() {
    return "<" + words + ", " + lookup + ">";
  }

  /**
   * Checks whether this Sentence equals to the specified object.
   * @param  other
   *         the other Sentence to be compared against
   * @return  {@code true} if this Sentence equals the other Sentence;
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
    Sentence that = (Sentence) other;
    return this.words.equals(that.words);
  }

  /**
   * Calculates the HashCode for this Sentence.
   * The HashCode depends on the list of words and the lookup table of this Sentence.
   * @return  the HashCode of this Sentence
   */
  @Override
  public int hashCode() {
    return Objects.hash(words, lookup);
  }

  /**
   * Extracts semantically complete Sentences from a Phrase, using the CYK table.
   * @param  phrase
   *         the Phrase to be splitted into Sentences
   * @return  the Sentences extracted from the given Phrase, according to the CYK table.
   * @see  com.github.bot.curiosone.core.nlp.Phrase The Phrase Class
   * @see  com.github.bot.curiosone.core.nlp.ParseTable The ParseTable Class
   */
  public static List<Sentence> extract(Phrase phrase) {
    List<Token> tokens = phrase.getTokens();
    ParseTable table = new ParseTable(tokens);
    // System.out.println(table);
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
            // System.out.println(table);
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
    // System.out.println(l);
    return l;
  }
}
