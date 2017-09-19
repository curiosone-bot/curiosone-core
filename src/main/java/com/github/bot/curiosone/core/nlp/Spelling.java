package com.github.bot.curiosone.core.nlp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Corrects spelling errors in sentences.
 */
public class Spelling {
  /**
   * String representation of the alphabet.
   */
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  /**
   * String representation of a Regex to match all letters from 'a' to 'z'.
   */
  private static final String REGEX_A_Z = "[^a-z ]";

  /**
   * The instance of this singleton class.
   */
  private static Spelling instance;

  /**
   * Path to the dictionary file.
   */
  private static Path dictionaryFile = Paths.get("src/main/res/spelling/dictionary.txt");

  /** Dictionary used in spelling and correction processes. */
  private Map<String, Integer> dict = new HashMap<>();

  /**
   * Constructor of a Spelling Dictionary.
   */
  private Spelling() {
    try {
      String dictStr = new String(Files.readAllBytes(dictionaryFile))
          .toLowerCase()
          .replaceAll(REGEX_A_Z, "");
      Stream.of(dictStr.split(" ")).forEach(word -> {
        dict.compute(word, (k, v) -> v == null ? 1 : v + 1);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets an instance of the spelling dictionary.
   *
   * @return the instance of the spelling dictionary
   */
  public static Spelling getInstance() {
    if  (instance != null) {
      return instance;
    }
    instance = new Spelling();
    return instance;
  }

  /**
   * Tries to correct a mispelled word.
   *
   * @param word the word to be corrected. It should be lowercased.
   * @return the corrected word if the world can be corrected
   */
  public String correct(String word) {
    if (dict.containsKey(word)) {
      return word;
    }
    Optional<String> e1 = known(edits(word))
        .max((a, b) -> dict.get(a) - dict.get(b));

    if (e1.isPresent()) {
      return e1.get();
    }
    return word;
  }

  /**
   * Applies all possible 1 character modifications to a string.
   *
   * @param word the word to modify
   * @return a stream of all generated modified words
   */
  private Stream<String> edits(String word) {
    Stream<String> deletes = IntStream.range(0, word.length())
        .mapToObj(i -> word.substring(0, i) + word.substring(i + 1));

    Stream<String> replaces = IntStream.range(0, word.length())
        .mapToObj(i -> i)
        .flatMap(i -> ALPHABET.chars()
        .mapToObj(c -> word.substring(0, i) + (char) c + word.substring(i + 1)));

    Stream<String> inserts = IntStream.range(0, word.length() + 1)
        .mapToObj(i -> i)
        .flatMap(i -> ALPHABET.chars()
        .mapToObj(c -> word.substring(0, i) + (char) c + word.substring(i)));
    Stream<String> transposes = IntStream.range(0, word.length() - 1)
        .mapToObj(i -> {
          return word.substring(0, i) +  word.substring(i + 1, i + 2)
              + word.charAt(i) + word.substring(i + 2);
        });
    return Stream.of(deletes, replaces, inserts, transposes).flatMap(x -> x);
  }

  /**
   * Verify if the stream of strings is in {@link #dict}.
   *
   * @param words stream of strings
   * @return words that contains only string in {@link #dict} too
   */
  private Stream<String> known(Stream<String> words) {
    return words.filter((word) -> dict.containsKey(word));
  }
}
