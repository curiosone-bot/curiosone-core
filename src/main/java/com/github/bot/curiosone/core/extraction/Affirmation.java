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
 * Description.
 */
public class Affirmation {
  /**
   * getAnswer description.
   *
   * @param sentence [description]
   * @param scope [description]
   * @return [description]
   */
  public static Optional<BrainResponse> getAnswer(Sentence sentence, String scope) {
    boolean answer = false;
    if (scope.length() > 0 && scope.charAt(scope.length() - 1) == '?') {
      answer = true;
    }

    if (answer) {
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
        // System.out.println("V, NP");
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
      String newMessage;
      String newScope;

      newMessage = "Mhh! What is a " + object.getText() + "?";
      newScope = object.getText() + "?";
      return Optional.of(new BrainResponse(newMessage, newScope));
    }

    return Optional.empty();
  }
}