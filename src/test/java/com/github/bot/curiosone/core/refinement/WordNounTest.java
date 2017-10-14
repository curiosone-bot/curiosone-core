package com.github.bot.curiosone.core.refinement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.github.bot.curiosone.core.refinement.interfaces.Word;

import org.junit.Test;

public class WordNounTest {

  @Test
  public void testInstantiation() {
    WordNoun wn  = new WordNoun("word", TypeNoun.Name);
    assertThat(wn instanceof Word).isTrue();
  }

  @Test
  public void testToString() {
    WordNoun wn = new WordNoun("LEMMA", TypeNoun.Name);
    assertThat(wn.toString()).isEqualTo("LEMMA");

    wn = new WordNoun("SingularName", TypeNoun.Singular);
    assertThat(wn.toString()).isNotNull().isNotEmpty();

    wn = new WordNoun("PLURALname", TypeNoun.Plural);
    assertThat(wn.toString()).isNotNull().isNotEmpty();

    wn = new WordNoun("", TypeNoun.Singular);
    assertThat(wn.toString()).isNotNull().isNotEmpty();

    wn = new WordNoun("", TypeNoun.Name);
    assertThat(wn.toString()).isNotNull().isEmpty();

    assertThatCode(() -> new WordNoun("", TypeNoun.Name).toString()).doesNotThrowAnyException();
  }
}
