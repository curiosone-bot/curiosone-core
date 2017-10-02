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
 * Provides a static method to get an answer from a given sentence and a given scope.
 */
public class Question {

  /**
   * Returns an answer for the given sentence and the given scope.
   * @param sentence the sentence to base the answer on
   * @param scope the scope for the given input sentence
   * @return an Optional instance. If an answer has been successfully computed, the value contains
             the computed answer.
   *         Otherwise, an empty Optional instance is returned.
   */
  public static Optional<BrainResponse> getAnswer(Sentence sentence, String scope) {
    Word kind;
    Word verb;
    Word object;

    if (sentence.respect(POS.PRON, POS.V, POS.NP)) {
      List<Word>[] extracted = sentence.parse(POS.PRON, POS.V, POS.NP);
      kind = extracted[0].stream()
          .filter(w -> w.itMeans(POS.PRON))
          .findFirst().get();
      verb = extracted[1].stream()
          .filter(w -> w.itMeans(POS.V))
          .findFirst().get();
      List<Word> nouns = extracted[2].stream()
          .filter(w -> w.itMeans(POS.N))
          .collect(Collectors.toList());
      object = nouns.get(nouns.size() - 1);
    } else if (sentence.respect(POS.ADV, POS.V, POS.NP)) {
      List<Word>[] extracted = sentence.parse(POS.ADV, POS.V, POS.NP);
      kind = extracted[0].stream()
          .filter(w -> w.itMeans(POS.ADV))
          .findFirst().get();
      verb = extracted[1].stream()
          .filter(w -> w.itMeans(POS.V))
          .findFirst().get();
      List<Word> nouns = extracted[2].stream()
          .filter(w -> w.itMeans(POS.N))
          .collect(Collectors.toList());
      object = nouns.get(nouns.size() - 1);
    } else if (sentence.has(POS.PRON) && sentence.has(POS.V) && sentence.has(POS.N)) {
      kind = sentence.get(POS.PRON).get(0);
      verb = sentence.get(POS.V).get(0);
      List<Word> nouns = sentence.get(POS.N);
      object = nouns.get(nouns.size() - 1);
    } else if (sentence.has(POS.ADV) && sentence.has(POS.V) && sentence.has(POS.N)) {
      kind = sentence.get(POS.ADV).get(0);
      verb = sentence.get(POS.V).get(0);
      List<Word> nouns = sentence.get(POS.N);
      object = nouns.get(nouns.size() - 1);
    } else {
      return Optional.empty();
    }

    SemanticNetwork semanticNetwork;
    try {
      semanticNetwork = SemanticNetwork.getInstance();
    } catch (IOException e) {
      e.printStackTrace();
      semanticNetwork = null;
    }
    switch (kind.getText()) {
      case "what": {
        SemanticQuery sq = new SemanticQuery(
            SemanticRelationType.IS_A,
            object.getText(),
            verb.getLemma()
        );
        Optional<Edge> opt = semanticNetwork.query(sq);

        String newMessage;
        String newScope;
        if (!opt.isPresent()) {
          newMessage = "I do not know what is a " + object.getText() + "! Do you?";
          newScope = object.getText() + '?';
        } else {
          String answer = opt.get().getTarget().toString();
          newMessage = "For what I know, " + object.getText() + " is a " + answer + "!";
          newScope = object.getText();
        }

        return Optional.of(new BrainResponse(newMessage, newScope));
      }
      case "who": {
        SemanticQuery sq = new SemanticQuery(
            SemanticRelationType.IS_A,
            object.getText(),
            verb.getLemma()
        );
        Optional<Edge> opt = semanticNetwork.query(sq);

        String newMessage;
        String newScope;
        if (!opt.isPresent()) {
          newMessage = "I do not know who is " + object.getText() + "! Do you?";
          newScope = object.getText() + '?';
        } else {
          String answer = opt.get().getTarget().toString();
          newMessage = "For what I know, " + object.getText() + " is a " + answer + "!";
          newScope = object.getText();
        }

        return Optional.of(new BrainResponse(newMessage, newScope));
      }
      case "where": {
        SemanticQuery sq = new SemanticQuery(
            SemanticRelationType.IS_A,
            object.getText(),
            verb.getLemma()
        );
        Optional<Edge> opt = semanticNetwork.query(sq);

        String newMessage;
        String newScope;
        if (!opt.isPresent()) {
          newMessage = "I do not know where is " + object.getText() + "! Do you?";
          newScope = object.getText() + '?';
        } else {
          String answer = opt.get().getTarget().toString();
          newMessage = "For what I know, " + object.getText() + " is in " + answer + "!";
          newScope = object.getText();
        }

        return Optional.of(new BrainResponse(newMessage, newScope));
      }
      default: {
        return Optional.empty();
      }
    }
  }
}
