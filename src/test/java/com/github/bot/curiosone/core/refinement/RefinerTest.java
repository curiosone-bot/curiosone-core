package com.github.bot.curiosone.core.refinement;

import static com.github.bot.curiosone.core.refinement.Refiner.refine;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RefinerTest {

  @Test
  public void testRefine() {
    String subj = "Cat";
    String verb = "is";
    String obj = "animal";
    boolean subjIsPlural = false;
    boolean verbIsPast = false;
    boolean objIsPlural = false;
    String r = refine(subj, verb, obj, subjIsPlural, verbIsPast, objIsPlural);
    assertThat(r).isEqualToIgnoringCase("a cat is an animal.");

    subj = "Cat";
    verb = "are";
    obj = "animals";
    subjIsPlural = true;
    verbIsPast = false;
    objIsPlural = false;
    r = refine(subj, verb, obj, subjIsPlural, verbIsPast, objIsPlural);
    assertThat(r).containsIgnoringCase("cats").containsIgnoringCase("animals.");

    subj = "Cat";
    verb = "is";
    obj = "animal";
    subjIsPlural = false;
    verbIsPast = true;
    objIsPlural = false;
    r = refine(subj, verb, obj, subjIsPlural, verbIsPast, objIsPlural);
    assertThat(r).containsIgnoringCase("cat"). containsIgnoringCase("animal.");

    subj = "Cat";
    verb = "is";
    obj = "animal";
    subjIsPlural = true;
    verbIsPast = true;
    objIsPlural = false;
    r = refine(subj, verb, obj, subjIsPlural, verbIsPast, objIsPlural);
    assertThat(r).containsIgnoringCase("cats").containsIgnoringCase("animal.");

    subj = "I";
    verb = "love";
    obj = "car";
    subjIsPlural = false;
    verbIsPast = false;
    objIsPlural = true;
    r = refine(subj, verb, obj, subjIsPlural, verbIsPast, objIsPlural);
    assertThat(r).contains("I").containsIgnoringCase("love").containsIgnoringCase("cars.");
  }
}
