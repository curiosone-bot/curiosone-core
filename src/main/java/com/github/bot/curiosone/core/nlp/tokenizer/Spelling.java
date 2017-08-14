package com.github.bot.curiosone.core.nlp.tokenizer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java 8 Spelling Corrector.
 *
 * @author Copyright 2016 Peter Kuhar.
 * @see Open source code under MIT license: http://www.opensource.org/licenses/mit-license.php
 */

public class Spelling {

  /**
   * Dictionary.
   */

  private Map<String, Integer> dict = new HashMap<>();

  /**
   * It spells.
   *
   * @param dictionaryFile from a specific path
   * @throws Exception if something goes wrong while spelling
   */

  public Spelling(Path dictionaryFile) throws Exception {
    Stream.of(new String(Files.readAllBytes(dictionaryFile)).toLowerCase().replaceAll("[^a-z ]", "")
        .split(" ")).forEach((word) -> {
          dict.compute(word, (k, v) -> v == null ? 1 : v + 1);
        });
  }

  /**
   * Edit using streams.
   *
   * @param word as a final string
   * @return a map
   */
  Stream<String> edits1(final String word) {
    Stream<String> deletes = IntStream.range(0, word.length())
        .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1));
    Stream<String> replaces = IntStream.range(0, word.length()).mapToObj((i) -> i).flatMap(
        (i) -> "abcdefghijklmnopqrstuvwxyz".chars()
        .mapToObj((c) -> word.substring(0, i) + (char) c + word.substring(i + 1)));
    Stream<String> inserts = IntStream.range(0, word.length() + 1).mapToObj((i) -> i).flatMap(
        (i) -> "abcdefghijklmnopqrstuvwxyz".chars()
        .mapToObj((c) -> word.substring(0, i) + (char) c + word.substring(i)));
    Stream<String> transposes = IntStream.range(0, word.length() - 1).mapToObj(
        (i) -> word.substring(0, i) + word.substring(i + 1, i + 2) + word.charAt(i) + word
        .substring(i + 2));
    return Stream.of(deletes, replaces, inserts, transposes).flatMap((x) -> x);
  }

  /**
   * Verify if the stream of strings is in {@link #dict}.
   *
   * @param words stream of strings
   * @return words that contains only string in {@link #dict} too
   */

  Stream<String> known(Stream<String> words) {
    return words.filter((word) -> dict.containsKey(word));
  }

  /**
   * Verify the correctness.
   *
   * @param word is a string
   * @return boolean true or false
   */

  String correct(String word) {
    Optional<String> e1 = known(edits1(word)).max((a, b) -> dict.get(a) - dict.get(b));
    Optional<String> e2 = known(edits1(word).map((w2) -> edits1(w2)).flatMap((x) -> x))
        .max((a, b) -> dict.get(a) - dict.get(b));
    return dict.containsKey(word)
      ? word : (e1.isPresent() ? e1.get() : (e2.isPresent() ? e2.get() : word));
  }
}