package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Precomuted answers to some common conversational phrases.
 */
public class Conversation {
  /** Path of the database containing known answers. */
  private static final Path KNOWN_QUESTIONS =
      Paths.get("src/main/res/conversation/conversation.txt");

  /** Map from recognized tokens to possible phrases given in output. */
  private static Map<String[], String[]> knownQuestions;

  /**
   * Private constructor.
   */
  private Conversation() { }

  /**
   * Loads the known answers in memory.
   */
  private static void loadSentences() {
    knownQuestions = new HashMap<>();
    try (Stream<String> stream = Files.lines(KNOWN_QUESTIONS)) {
      stream.forEach(line -> {
        int splitIndex = line.indexOf(":");
        String[] key = line.substring(0, splitIndex).split("\t");
        String[] values = line.substring(splitIndex + 1, line.length()).split("\t");
        knownQuestions.put(key, values);
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks if the given input is present in our known answers.
   *
   * @param  phrase phrase given by user's input
   * @return answer if the input is known
   */
  public static Optional<BrainResponse> getAnswer(Phrase phrase) {
    if (knownQuestions == null) {
      loadSentences();
    }
    List<String> lemmas = phrase.getTokens()
        .stream()
        .map(Token::getLemma)
        .collect(Collectors.toList());

    for (Map.Entry<String[], String[]> entry : knownQuestions.entrySet()) {
      boolean isKnown = true;

      for (String s : entry.getKey()) {
        if (!lemmas.contains(s)) {
          isKnown = false;
          break;
        }
      }
      if (isKnown) {
        int randpos = (int)(Math.random() * entry.getValue().length);
        return Optional.of(new BrainResponse(entry.getValue()[randpos], ""));
      }
    }
    return Optional.empty();
  }
}
