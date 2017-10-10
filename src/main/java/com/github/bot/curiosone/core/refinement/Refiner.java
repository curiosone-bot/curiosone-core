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
    
    NounType st = sp ? NounType.Plural : NounType.Singular;
    VerbType vt = VerbType.Infinitive;
    NounType ot = op ? NounType.Plural : NounType.Singular;
    
    if (!sp && !vp) { vt = VerbType.PresentS1; }
    if ( sp && !vp) { vt = VerbType.PresentP1; }
    if (!sp &&  vp) { vt = VerbType.SimplePastS1; }
    if ( sp &&  vp) { vt = VerbType.SimplePastP1; }    
    
    NounPhrase subjectp = new NounPhrase(new NounWord(subject, st));

    VerbPhrase verbp = new VerbPhrase(
        new VerbWord(verb, vt),
        new NounPhrase(new NounWord(object, ot)));
    
    return new Sentence(SentenceType.Answer, new MainClause(subjectp, verbp)).toString();
  }
  
}
