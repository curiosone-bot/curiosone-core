package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Phrase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Precomputed answers to some common bad words.
 */
public class BadWords {
  private static String badWordsPath = "/conversation/bad_words.txt";
  /** Path of the database containing known insults. */

  /** List of different insults that the bot knows. */
  private static List<String> knownBadWords;

  /** List of different answers that the bot gives in output. */
  private static String[] readyAnswers;

  /**
   * Private constructor.
   */
  private BadWords() { }

  /**
   * Loads the known bad words in memory.
   */
  private static void loadSentences() {
    readyAnswers = new String[] {
      "I do not like your way of talking.",
      "You should speak more politely.",
      "Why are you talking like this? Are you a little kid?",
      "Come back when you grow up."
    };
    knownBadWords = new ArrayList<>();
    Path path = null;

    try {
      URL resource = Conversation.class.getResource(badWordsPath);
      path = Paths.get(resource.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    try (Stream<String> stream = Files.lines(path)) {
      stream.forEach(line -> knownBadWords.add(line));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check if the phrase contains one of the known bad words.
   * @param phrase to check
   * @return answer if the input is known
   */
  public static Optional<BrainResponse> getAnswer(Phrase phrase) {
    if (knownBadWords == null) {
      loadSentences();
    }
    int randpos = (int)(Math.random() * readyAnswers.length);
    boolean bad = phrase.getTokens().stream()
        .map(word -> word.getText())
        .anyMatch(word -> knownBadWords.contains(word));
    if (bad) {
      return Optional.of(new BrainResponse(readyAnswers[randpos], ""));
    }
    return Optional.empty();
  }
}
