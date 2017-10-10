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
 * Handles precomputed answers to some common bad words.
 * Contains an utility method to get an answer to a Phrase containig one known bad word.
 * @see  com.github.bot.curiosone.core.nlp.Phrase The Phrase Class
 */
public class BadWords {

  /**
   * Path to known insults database.
   */
  private static String badWordsPath = "/conversation/bad_words.txt";

  /**
   * Lists different insults known by the Bot.
   */
  private static List<String> knownBadWords;

  /**
   * Array of different answers that the bot gives in output.
   */
  private static String[] readyAnswers = {
    "I do not like your way of talking.",
    "You should speak more politely.",
    "Why are you talking like this? Are you a little kid?",
    "Come back when you grow up."
  };

  /**
   * Private constructor.
   */
  private BadWords() { }

  /**
   * Loads the known bad words in memory.
   */
  private static void loadSentences() {
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
   * Returns an answer for the given Phrase.
   * @param  phrase
   *         the original Phrase to be answered
   * @return  an Optional instance.
   *          The returned instance is empty, if no bad word or insult has been found in the
   *          original Phrase, contains a value (an instance of BrainResponse) otherwise.
   * @see  com.github.bot.curiosone.core.nlp.Phrase The Phrase Class
   * @see  <a href="https://goo.gl/sWfXyh">The Optional Class</a>
   * @see com.github.bot.curiosone.core.extraction.BrainResponse The BrainResponse Class
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
