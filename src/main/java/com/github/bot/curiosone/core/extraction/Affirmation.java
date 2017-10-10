package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.knowledge.SemanticNetwork;
import com.github.bot.curiosone.core.knowledge.SemanticQuery;
import com.github.bot.curiosone.core.knowledge.SemanticRelationType;
import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Sentence;
import com.github.bot.curiosone.core.nlp.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Contains an utility method to get an answer from a given Sentence and its scope.
 * @see  com.github.bot.curiosone.core.nlp.Sentence The Sentence Class
 */
public class Affirmation {

  /**
   * Gets a coherent answer from an input sentence and scope as Optional instance.
   * @param  sentence
   *         The quesiton to be answered
   * @param  scope
   *         The subject of the given quesiton
   * @return  an Optional instance.
   *          If the answer has not been found, an empty Optional will be returned.
   *          Otherwise, the Optional instance will contain the answer.
   * @see  com.github.bot.curiosone.core.nlp.Sentence The Sentence Class
   */
  public static Optional<BrainResponse> getAnswer(Sentence sentence, String scope) {
    boolean answer = false;
    if (scope.length() > 0 && scope.charAt(scope.length() - 1) == '?') {
      answer = true;
    }

    SemanticNetwork semanticNetwork;
    try {
      semanticNetwork = SemanticNetwork.getInstance();
    } catch (IOException e) {
      e.printStackTrace();
      semanticNetwork = null;
    }

    if (answer) {
      Word verb;
      Word object;
      scope = scope.substring(0, scope.length() - 1);

      if (sentence.respect(POS.V, POS.NP)) {
        List<Word>[] extracted = sentence.parse(POS.V, POS.NP);
        verb = extracted[0].stream().filter(w -> w.itMeans(POS.V)).findFirst().get();
        List<Word> nouns =
            extracted[1].stream().filter(w -> w.itMeans(POS.N)).collect(Collectors.toList());
        object = nouns.get(nouns.size() - 1);
      }
      if (sentence.has(POS.V) && sentence.has(POS.N)) {
        verb = sentence.get(POS.V).get(0);
        List<Word> nouns = sentence.get(POS.N);
        object = nouns.get(nouns.size() - 1);
      } else {
        return Optional.empty();
      }

      SemanticQuery sq = new SemanticQuery(
          SemanticRelationType.IS_A,
          scope,
          object.getText(),
          null
      );
      Optional<Edge> opt = semanticNetwork.query(sq);

      String newMessage;
      String newScope;
      if (opt.isPresent()) {
        newMessage = "I already knew that " + scope + " is a " + object.getText() + ".";
        newScope = object.getText();
      } else {
        semanticNetwork.learn(scope, SemanticRelationType.IS_A, object.getText());
        newMessage = "Wow really interesting! Now I know that a " + scope + " is a "
            + object.getText() + ".";
        newScope = object.getText();
      }

      return Optional.of(new BrainResponse(newMessage, newScope));
    }

    if (sentence.has(POS.N)) {
      List<Word> nouns = sentence.get(POS.N);
      Word object = nouns.get(nouns.size() - 1);

      SemanticQuery sq = new SemanticQuery(
          SemanticRelationType.IS_A,
          null,
          object.getText(),
          null
      );
      Optional<Edge> opt = semanticNetwork.query(sq);

      String newMessage;
      String newScope;
      if (opt.isPresent()) {
        String answ = opt.get().getTarget().toString();
        newMessage = "For what I know, " + object.getText() + " is " + answ + "!";
        newScope = object.getText();
      } else {
        newMessage = "Mhh! What is a " + object.getText() + "?";
        newScope = object.getText() + "?";
      }
      return Optional.of(new BrainResponse(newMessage, newScope));
    }

    return Optional.empty();
  }
}
