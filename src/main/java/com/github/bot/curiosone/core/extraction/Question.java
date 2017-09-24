package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Sentence;
import com.github.bot.curiosone.core.nlp.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description.
 */
public class Question {
  /**
   * getAnswer description.
   *
   * @param  sentence [description]
   * @param  scope [description]
   * @return [description]
   */
  public static Optional<BrainResponse> getAnswer(Sentence sentence, String scope) {
    if (sentence.respect(POS.PRON, POS.VP, POS.NP)) {
      List<Word>[] extracted = sentence.parse(POS.PRON, POS.V, POS.NP);
      Word pron = extracted[0].stream().filter(w -> w.itMeans(POS.PRON)).findFirst().get();
      Word verb = extracted[1].stream().filter(w -> w.itMeans(POS.V)).findFirst().get();
      Word object = extracted[2].stream().filter(w -> w.itMeans(POS.N)).findFirst().get();

      switch (pron.getText()) {
        case "what": {
          //TODO: Get a real response from the semantic network.
          Optional<String> opt = Optional.of("something");

          if (!opt.isPresent()) {
            return Optional.empty();
          }
          String text = opt.get();
          Word answer = new Word(text, text, new Meaning(POS.N, LEX.UNKN));

          List<Word> words = new ArrayList<>();
          words.add(object);
          words.add(new Word("is", "be", new Meaning(POS.V, LEX.STATIVE)));
          words.add(new Word("a", "a", new Meaning(POS.DET, LEX.INDEFINITE_ARTICLE)));
          words.add(answer);

          return Optional.of(new BrainResponse(words, object.getText()));
        }
        default: { }
      }
    }
    return Optional.empty();
  }
}
