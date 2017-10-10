package com.github.bot.curiosone.core.nlp;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Provides a method to correct spelling errors in a Sentence.
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
  private static String dictionaryPath = "/spelling/dictionary.txt";

  /**
   * Dictionary used in spelling and correction processes.
   */
  private Map<String, Integer> dict = new HashMap<>();

  /**
   * Constructs a Spelling Dictionary.
   */
  private Spelling() {
    Path path = null;
    try {
      URL resource = Rule.class.getResource(dictionaryPath);
      path = Paths.get(resource.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    try {
      String dictStr = new String(Files.readAllBytes(path))
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
   * Gets the Singleton instance.
   * @return  the instance of the spelling dictionary
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
   * @param  word
   *         the word to be corrected. It should be lowercased.
   * @return  the corrected word if the world can be corrected
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
   * Applies all possible single character modifications to a String.
   * @param  word
   *         the word to edit
   * @return  a stream containing all the modified words, generated from the original word
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
   * Checks whether the provided Stream of Strings is in {@link #dict} or not.
   * @param  words
   *         Stream of Strings containing the Word to be checked
   * @return  a Stream containing only the Words represented by a String in {@link #dict}
   */
  private Stream<String> known(Stream<String> words) {
    return words.filter((word) -> dict.containsKey(word));
  }
}
