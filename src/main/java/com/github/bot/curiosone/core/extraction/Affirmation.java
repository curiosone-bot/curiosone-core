package com.github.bot.curiosone.core.extraction;

import static com.github.bot.curiosone.core.util.TextConstants.ALREADY_KNOW;
import static com.github.bot.curiosone.core.util.TextConstants.DOT;
import static com.github.bot.curiosone.core.util.TextConstants.IS_A;
import static com.github.bot.curiosone.core.util.TextConstants.QUESTION_MARK;
import static com.github.bot.curiosone.core.util.TextConstants.WHAT_IS_A;
import static com.github.bot.curiosone.core.util.TextConstants.WOW_LEARNED;

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
 * Contains utility method to get an answer from a given sentence and a given scope.
 */
public class Affirmation {
  /**
   * Gets a coherent answer from an input sentence and scope as Optional instance.
   * @param sentence The quesiton to be answered
   * @param scope The subject of the given quesiton
   * @return an Optional instance.
   *         If the answer has not been found, an empty Optional will be returned.
   *         Otherwise, the Optional instance will contain the answer.
   */
  public static Optional<BrainResponse> getAnswer(Sentence sentence, String scope) {
    if (scope.length() > 0 && scope.charAt(scope.length() - 1) == QUESTION_MARK) {
      Word verb;
      Word object;
      scope = scope.substring(0, scope.length() - 1);
      SemanticNetwork semanticNetwork;
      try {
        semanticNetwork = SemanticNetwork.getInstance();
      } catch (IOException e) {
        e.printStackTrace();
        semanticNetwork = null;
      }

      if (sentence.respect(POS.V, POS.NP)) {
        List<Word>[] extracted = sentence.parse(POS.V, POS.NP);
        verb = extracted[0].stream().filter(w -> w.itMeans(POS.V)).findFirst().get();
        List<Word> nouns =
            extracted[1].stream().filter(w -> w.itMeans(POS.N)).collect(Collectors.toList());
        object = nouns.get(nouns.size() - 1);
      } else {
        return Optional.empty();
      }

      SemanticQuery sq = new SemanticQuery(
          SemanticRelationType.IS_A,
          scope,
          verb.getLemma()
      );
      Optional<Edge> opt = semanticNetwork.query(sq);

      String newMessage;
      String newScope;
      if (opt.isPresent()) {
        newMessage = ALREADY_KNOW + scope + IS_A + object.getText() + DOT;
        newScope = object.getText();
      } else {
        semanticNetwork.learn(scope, SemanticRelationType.IS_A, object.getText());
        newMessage = WOW_LEARNED + scope + IS_A + object.getText() + DOT;
        newScope = object.getText();
      }

      return Optional.of(new BrainResponse(newMessage, newScope));
    }

    if (sentence.has(POS.N)) {
      List<Word> nouns = sentence.get(POS.N);
      Word object = nouns.get(nouns.size() - 1);
      String newMessage;
      String newScope;

      newMessage = WHAT_IS_A + object.getText() + QUESTION_MARK;
      newScope = object.getText() + QUESTION_MARK;
      return Optional.of(new BrainResponse(newMessage, newScope));
    }

    return Optional.empty();
  }
}
