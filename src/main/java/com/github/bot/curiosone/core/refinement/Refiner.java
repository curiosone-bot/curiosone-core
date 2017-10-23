package com.github.bot.curiosone.core.refinement;

/**
 * Utility wrapper class to easily refine a Sentence.
 * Contains a public static method to refine a Sentence, given its subject, verb, object and their
 * grammar information.
 * The refiner needs to know if a verb is a past form and if the object/subject is singular or
 * plural.
 */
public abstract class Refiner {

  /**
   * Refines a Sentence, given its subject, verb and object.
   * Requires to know whether the subject and the object are singular or not and if the verb is in
   * a past form.
   * @param  subject
   *         String representation of the subject of the Sentence to be refined
   * @param  verb
   *         String representation of the verb of the Sentence to be refined
   * @param  object
   *         String representation of the object of the Sentence to be refined
   * @param  sp
   *         whether the given subject is plural
   * @param  vp
   *         whether the given verb is past
   * @param  op
   *         whether the given object is plural
   * @return  a new String, containing the refined Sentence.
   */
  public static String refine(
      String subject,
      String verb,
      String object,
      boolean sp,
      boolean vp,
      boolean op) {

    TypeVerb vt = TypeVerb.Infinitive;

    if (!sp && !vp) {
      vt = TypeVerb.PresentS3;
    }
    if (sp && !vp) {
      vt = TypeVerb.PresentP3;
    }
    if (!sp &&  vp) {
      vt = TypeVerb.SimplePastS3;
    }
    if (sp &&  vp) {
      vt = TypeVerb.SimplePastP3;
    }

    TypeNoun st = sp ? TypeNoun.Plural : TypeNoun.Singular;
    TypeNoun ot = op ? TypeNoun.Plural : TypeNoun.Singular;

    PhraseNoun subjectp = new PhraseNoun(new WordNoun(subject, st));

    PhraseVerb verbp = new PhraseVerb(
        new WordVerb(verb, vt),
        new PhraseNoun(new WordNoun(object, ot)));

    return new Sentence(SentenceType.Answer, new ClauseMain(subjectp, verbp)).toString();
  }
}
