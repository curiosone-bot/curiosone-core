package com.github.bot.curiosone.core.extraction;

import static com.github.bot.curiosone.core.util.TextConstants.DO_NOT_KNOW;
import static com.github.bot.curiosone.core.util.TextConstants.DO_YOU;
import static com.github.bot.curiosone.core.util.TextConstants.EXCL_MARK;
import static com.github.bot.curiosone.core.util.TextConstants.IS;
import static com.github.bot.curiosone.core.util.TextConstants.IS_A;
import static com.github.bot.curiosone.core.util.TextConstants.IS_IN;
import static com.github.bot.curiosone.core.util.TextConstants.QUESTION_MARK;
import static com.github.bot.curiosone.core.util.TextConstants.WHAT;
import static com.github.bot.curiosone.core.util.TextConstants.WHAT_I_KNOW;
import static com.github.bot.curiosone.core.util.TextConstants.WHERE;
import static com.github.bot.curiosone.core.util.TextConstants.WHO;

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
   * @return an Optional instance. If an answer has been successfully computed,
   *         the value contains the computed answer.
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
      case WHAT: {
        SemanticQuery sq = new SemanticQuery(
            SemanticRelationType.IS_A,
            object.getText(),
            verb.getLemma()
        );
        Optional<Edge> opt = semanticNetwork.query(sq);
        String newMessage;
        String newScope;
        if (!opt.isPresent()) {
          newMessage = DO_NOT_KNOW + WHAT + IS_A + object.getText() + DO_YOU;
          newScope = object.getText() + QUESTION_MARK;
        } else {
          String answer = opt.get().getTarget().toString();
          newMessage = WHAT_I_KNOW + object.getText() + IS_A + answer + EXCL_MARK;
          newScope = object.getText();
        }
        return Optional.of(new BrainResponse(newMessage, newScope));
      }
      case WHO: {
        SemanticQuery sq = new SemanticQuery(
            SemanticRelationType.IS_A,
            object.getText(),
            verb.getLemma()
        );
        Optional<Edge> opt = semanticNetwork.query(sq);
        String newMessage;
        String newScope;
        if (!opt.isPresent()) {
          newMessage = DO_NOT_KNOW + WHO + IS + object.getText() + DO_YOU;
          newScope = object.getText() + QUESTION_MARK;
        } else {
          String answer = opt.get().getTarget().toString();
          newMessage = WHAT_I_KNOW + object.getText() + IS_A + answer + EXCL_MARK;
          newScope = object.getText();
        }
        return Optional.of(new BrainResponse(newMessage, newScope));
      }
      case WHERE: {
        SemanticQuery sq = new SemanticQuery(
            SemanticRelationType.IS_A,
            object.getText(),
            verb.getLemma()
        );
        Optional<Edge> opt = semanticNetwork.query(sq);
        String newMessage;
        String newScope;
        if (!opt.isPresent()) {
          newMessage = DO_NOT_KNOW + WHERE + IS + object.getText() + DO_YOU;
          newScope = object.getText() + QUESTION_MARK;
        } else {
          String answer = opt.get().getTarget().toString();
          newMessage = WHAT_I_KNOW + object.getText() + IS_IN + answer + EXCL_MARK;
          newScope = object.getText();
        }
        return Optional.of(new BrainResponse(newMessage, newScope));
      }
      default: { }
    }
    return Optional.empty();
  }
}
