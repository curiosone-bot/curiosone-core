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
  public static String refine(String subject, String verb, String object, boolean sp, boolean vp, boolean op) {
    
    TypeNoun st = sp ? TypeNoun.Plural : TypeNoun.Singular;
    TypeVerb vt = TypeVerb.Infinitive;
    TypeNoun ot = op ? TypeNoun.Plural : TypeNoun.Singular;
    
    if (!sp && !vp) { vt = TypeVerb.PresentS1; }
    if ( sp && !vp) { vt = TypeVerb.PresentP1; }
    if (!sp &&  vp) { vt = TypeVerb.SimplePastS1; }
    if ( sp &&  vp) { vt = TypeVerb.SimplePastP1; }    
    
    PhraseNoun subjectp = new PhraseNoun(new WordNoun(subject, st));

    PhraseVerb verbp = new PhraseVerb(
        new WordVerb(verb, vt),
        new PhraseNoun(new WordNoun(object, ot)));
    
    return new SentenceMain(SentenceType.Answer, new ClauseMain(subjectp, verbp)).toString();
  }
  
}
