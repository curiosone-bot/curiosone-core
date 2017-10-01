package com.github.bot.curiosone.core.nlp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.github.bot.curiosone.core.util.Pair;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ParseTableTest {

  @Test
  public void testGetHeight() {
    List<Token> lt = Token.tokenize("what is an apple?");
    ParseTable pt = new ParseTable(lt);
    assertThat(pt.getHeight()).isEqualTo(4);

    lt = Token.tokenize("where is Rome?");
    pt = new ParseTable(lt);
    assertThat(pt.getHeight()).isEqualTo(3);

    lt = Token.tokenize("who is Roberto Navigli?");
    pt = new ParseTable(lt);
    assertThat(pt.getHeight()).isEqualTo(4);

    lt = Token.tokenize("SingleTokenParty!");
    pt = new ParseTable(lt);
    assertThat(pt.getHeight()).isEqualTo(1);

    lt = Token.tokenize("I live!");
    pt = new ParseTable(lt);
    assertThat(pt.getHeight()).isEqualTo(2);

    lt = Token.tokenize("Let's try a more complex sentence. What do you think?");
    pt = new ParseTable(lt);
    assertThat(pt.getHeight()).isEqualTo(10);

    lt = Token.tokenize("Which came first, the chicken or the egg? Are We Alone? Where is Santa?");
    pt = new ParseTable(lt);
    assertThat(pt.getHeight()).isEqualTo(14);
  }

  @Test
  public void testGet() {
    List<Token> lt = Token.tokenize("what is an apple?");
    ParseTable pt = new ParseTable(lt);
    assertThat(pt.get(0, 0)).isNotNull().isNotEmpty();

    assertThat(pt.get(0, 1)).isNotNull().isNotEmpty();

    assertThat(pt.get(0, 1)).isNotNull().isNotEmpty();

    assertThat(pt.get(1, 1)).isNotNull().isNotEmpty();

    assertThat(pt.get(1, 2)).isNotNull().isNotEmpty();
  }

  @Test
  public void testGetIndexOutOfBoundsException() {
    List<Token> lt = Token.tokenize("A picture is worth a thousand words.");
    ParseTable pt = new ParseTable(lt);

    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> pt.get(1, -42))
        .withMessage("Position outside the table [1, -42]");

    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> pt.get(1, 42))
        .withMessage("Position outside the table [1, 42]");

    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> pt.get(-42, 42))
        .withMessage("Position outside the table [-42, 42]");

    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> pt.get(42, 10))
        .withMessage("Position outside the table [42, 10]");
  }

  @Test
  public void testGetWidthAt() {
    List<Token> lt = Token.tokenize("what is an apple?");
    ParseTable pt = new ParseTable(lt);
    assertThat(pt.getWidthAt(0)).isEqualTo(1);

    lt = Token.tokenize("I'm eating a golden apple!");
    pt = new ParseTable(lt);
    assertThat(pt.getWidthAt(1)).isEqualTo(2);

    lt = Token.tokenize("Good news! A new version of the game is available.");
    pt = new ParseTable(lt);
    assertThat(pt.getWidthAt(2)).isEqualTo(3);
  }
}
