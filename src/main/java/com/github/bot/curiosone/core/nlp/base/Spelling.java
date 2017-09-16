package com.github.bot.curiosone.core.nlp.base;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Spelling Corrector.
 */

public class Spelling {

  /**
   * String representation of the path to the dictionary file.
   */
  private static final String DICT_PATH
      = "src/main/res/spelling/dictionary.txt";

  /**
   * String representation of the alphabet.
   */
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  /**
   * Regex to match all letters from 'a' to 'z' in a String.
   */
  private static final String REGEX_A_Z = "[^a-z ]";

  /**
   * The instance of this singleton class.
   */
  private static Spelling instance;

  /**
   * Path to the dictionary file.
   */
  private static Path dictionaryFile = Paths.get(DICT_PATH);

  /**
   * Dictionary used in spelling and correction processes.
   */
  private Map<String, Integer> dict = new HashMap<>();

  /**
   * Spellig.
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
   * Returns a speller.
   */
  public static Spelling getInstance() {
    if  (instance != null) {
      return instance;
    }
    instance = new Spelling();
    return instance;
  }

  /**
   * This method tries to correct a mispelled word. If no correction happens,
   * then the original input value is returned.
   * @param word the word to be corrected.
   * @return the correct word.
   */
  String correct(String word) {
    if (dict.containsKey(word)) {
      return word;
    }
    Optional<String> e1 = known(edits1(word))
        .max((a, b) -> dict.get(a) - dict.get(b));

    if (e1.isPresent()) {
      return e1.get();
    }
    return word;
  }

  /**
   * Edit using streams.
   *
   * @param word as a final string
   * @return a map
   */
  private Stream<String> edits1(final String word) {
    Stream<String> deletes = IntStream.range(0, word.length())
        .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1));
    Stream<String> replaces =
        IntStream.range(0, word.length()).mapToObj((i) -> i)
        .flatMap((i) -> ALPHABET.chars()
        .mapToObj((c) -> word.substring(0, i)
            + (char) c + word.substring(i + 1)));
    Stream<String> inserts =
        IntStream.range(0, word.length() + 1)
        .mapToObj((i) -> i)
        .flatMap((i) -> ALPHABET.chars()
        .mapToObj((c) -> word.substring(0, i) + (char) c + word.substring(i)));
    Stream<String> transposes
        = IntStream.range(0, word.length() - 1)
        .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1, i + 2)
            + word.charAt(i) + word.substring(i + 2));
    return Stream.of(deletes, replaces, inserts, transposes).flatMap((x) -> x);
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
