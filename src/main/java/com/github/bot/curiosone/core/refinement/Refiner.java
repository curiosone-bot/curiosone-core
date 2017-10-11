package com.github.bot.curiosone.core.refinement;

public abstract class Refiner {

  /**
   * Quick'n'dirty wrapper.
   * @param subject subject
   * @param verb verb
   * @param object object
   * @param sp subject is plural
   * @param vp verb is past
   * @param op object is plural
   * @return refined sentence
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
