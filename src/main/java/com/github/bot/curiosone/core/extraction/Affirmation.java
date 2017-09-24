package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description.
 */
public class Affirmation {
  /**
   * getAnswer description.
   * @param  sentence [description]
   * @param  scope [description]
   * @return [description]
   */
  public static Optional<BrainResponse> getAnswer(Sentence sentence, String scope) {
    boolean answer = false;
    if (scope.length() > 0 && scope.charAt(scope.length() - 1) == '?') {
      answer = true;
    }
    if (answer) {
      scope = scope.substring(0, scope.length() - 1);
      if (sentence.respect(POS.V, POS.NP)) {
        // System.out.println("RESPECTED");
        List<Word>[] extracted = sentence.parse(POS.V, POS.NP);
        Word verb = extracted[0].stream().filter(w -> w.itMeans(POS.V)).findFirst().get();
        Word object = extracted[1].stream().filter(w -> w.itMeans(POS.N)).findFirst().get();

        //TODO: Get a real response from the semantic network.
        boolean present = Math.random() >= 0.5;

        if (present) {
          List<Word> words = new ArrayList<>();
          words.add(new Word("I", "I", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("already", "already", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("knew", "know", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("that", "that", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word(scope, scope, new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("is", "is", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("a", "a", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(object);

          return Optional.of(new BrainResponse(words, object.getText()));
        } else {
          List<Word> words = new ArrayList<>();
          words.add(new Word("now", "now", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("I", "I", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("know", "know", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("that", "that", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word(scope, scope, new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("is", "is", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(new Word("a", "a", new Meaning(POS.UNKN, LEX.UNKN)));
          words.add(object);

          return Optional.of(new BrainResponse(words, object.getText()));
        }
      }
    }
    List<Word> objects = sentence.get(POS.N);
    if (objects.size() > 0) {
      Word object = objects.get(0);
      List<Word> words = new ArrayList<>();
      words.add(new Word("what", "what", new Meaning(POS.UNKN, LEX.UNKN)));
      words.add(new Word("is", "is", new Meaning(POS.UNKN, LEX.UNKN)));
      words.add(new Word("a", "a", new Meaning(POS.UNKN, LEX.UNKN)));
      words.add(object);

      return Optional.of(new BrainResponse(words, object.getText() + "?"));
    }

    //TODO: Add real implementation.
    return Optional.empty();
  }
}
