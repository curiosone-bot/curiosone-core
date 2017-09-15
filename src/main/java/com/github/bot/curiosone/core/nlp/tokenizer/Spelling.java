package com.github.bot.curiosone.core.nlp.tokenizer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Spelling Corrector.
 */

public class Spelling {

  /** Description */
  private static Spelling instance;

  /** Description */
  private static Path dictionaryFile = Paths.get("src/main/res/spelling/dictionary.txt");

  /** Dictionary. */
  private Map<String, Integer> dict = new HashMap<>();

  /**
   * [Spelling description]
   * @return [description]
   */
  private Spelling() {
    try {
      String dictStr = new String(Files.readAllBytes(dictionaryFile))
        .toLowerCase()
        .replaceAll("[^a-z ]", "");
      Stream.of(dictStr.split(" ")).forEach(word -> {
        dict.compute(word, (k, v) -> v == null ? 1 : v + 1);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * [getInstance description]
   * @return [description]
   */
  public static Spelling getInstance() {
    if  (instance != null) {
      return instance;
    }
    instance = new Spelling();
    return instance;
  }

  /**
   * [correct description]
   * @param  word [description]
   * @return [description]
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
    Stream<String> replaces = IntStream.range(0, word.length())
      .mapToObj((i) -> i).flatMap(
        (i) -> "abcdefghijklmnopqrstuvwxyz".chars().mapToObj(
          (c) -> word.substring(0, i) + (char) c + word.substring(i + 1)
        )
      );
    Stream<String> inserts = IntStream.range(0, word.length() + 1)
      .mapToObj((i) -> i).flatMap(
        (i) -> "abcdefghijklmnopqrstuvwxyz".chars().mapToObj(
          (c) -> word.substring(0, i) + (char) c + word.substring(i)
        )
      );
    Stream<String> transposes = IntStream.range(0, word.length() - 1)
      .mapToObj(
        (i) -> word.substring(0, i) + word.substring(i + 1, i + 2) + word.charAt(i) + word
        .substring(i + 2)
      );
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
