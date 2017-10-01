package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Token;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Precomputed answers to some common conversational phrases.
 */
public class Conversation {
  private static String conversationsPath = "/conversation/conversation.txt";
  /** Path of the database containing known answers. */


  /** Map from recognized tokens to possible phrases given in output. */
  private static LinkedHashMap<String, String[]> knownQuestions;

  /**
   * Private constructor.
   */
  private Conversation() { }

  /**
   * Loads the known answers in memory.
   */
  private static void loadSentences() {
    knownQuestions = new LinkedHashMap<>();
    Path path = null;
    try {
      URL resource = Conversation.class.getResource(conversationsPath);
      path = Paths.get(resource.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    try (Stream<String> stream = Files.lines(path)) {
      stream.forEach(line -> {
        int splitIndex = line.indexOf(":");
        String key = line.substring(0, splitIndex);
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

    for (Map.Entry<String, String[]> entry : knownQuestions.entrySet()) {
      boolean isKnown = true;

      for (String s : entry.getKey().split("\t")) {
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
