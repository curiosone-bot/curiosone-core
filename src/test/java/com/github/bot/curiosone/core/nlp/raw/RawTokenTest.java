package com.github.bot.curiosone.core.nlp.raw;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.POS;

import edu.mit.jwi.item.WordID;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RawTokenTest {

  @Test
  public void testInstantiation() {
    RawToken rt = new RawToken("Original");
    assertTrue(rt instanceof RawToken);
  }

  @Test
  public void testGetOriginalValue() {
    RawToken rt = new RawToken("getter");
    assertEquals("getter", rt.getOValue());

    rt = new RawToken("");
    assertEquals("", rt.getOValue());

    rt = new RawToken("42? 42!!");
    assertEquals("42? 42!!", rt.getOValue());
  }

  @Test
  public void testSetOriginalValue() {
    RawToken rt = new RawToken("beforeSet");
    rt.setOValue("afterSet");
    assertEquals("afterSet", rt.getOValue());

    rt = new RawToken("123");
    rt.setOValue("4224");
    assertEquals("4224", rt.getOValue());

    rt = new RawToken("removeMe!");
    rt.setOValue("");
    assertEquals("", rt.getOValue());
  }

  @Test
  public void testIsCorrected() {
    RawToken rt = new RawToken("uncorrected");
    assertFalse(rt.isCorrected());

    rt = new RawToken("locker");
    assertFalse(rt.isCorrected());

    rt = new RawToken("GYM");
    assertFalse(rt.isCorrected());
  }

  @Test
  public void testSetCorrected() {
    RawToken rt = new RawToken("CorrectMe!");
    assertFalse(rt.isCorrected());
    rt.setCorrected(true);
    assertTrue(rt.isCorrected());

    rt = new RawToken("");
    assertFalse(rt.isCorrected());
    rt.setCorrected(true);
    assertTrue(rt.isCorrected());

    rt = new RawToken("42");
    assertFalse(rt.isCorrected());
    rt.setCorrected(true);
    assertTrue(rt.isCorrected());
  }

  @Test
  public void testGetValue() {
    RawToken rt = new RawToken("value");
    assertEquals("value", rt.getValue());

    rt = new RawToken("");
    assertEquals("", rt.getValue());

    rt = new RawToken("__");
    assertEquals("__", rt.getValue());
  }

  @Test
  public void testSetValue() {
    RawToken rt = new RawToken("SetMeWithSomethingElse!");
    assertEquals("SetMeWithSomethingElse!", rt.getValue());
    rt.setValue("Done!");
    assertEquals("Done!", rt.getValue());

    rt = new RawToken("IllBeGone!");
    assertEquals("IllBeGone!", rt.getValue());
    rt.setValue("");
    assertEquals("", rt.getValue());
  }

  @Test
  public void testIsKnown() {
    RawToken rt = new RawToken("unknown");
    assertFalse(rt.isKnown());

    rt = new RawToken("?_!-2..5");
    assertFalse(rt.isKnown());
  }

  @Test
  public void testSetKnown() {
    RawToken rt = new RawToken("unknown");
    assertFalse(rt.isKnown());
    rt.setKnown(true);
    assertTrue(rt.isKnown());

    rt = new RawToken("?_!-2..5");
    assertFalse(rt.isKnown());
    rt.setKnown(true);
    assertTrue(rt.isKnown());
  }

  @Test
  public void testGetWords() {
    RawToken rt = new RawToken("List");
    assertEquals(0, rt.getWords().size());

    rt = new RawToken("");
    assertEquals(0, rt.getWords().size());
  }

  @Test
  public void testAddWord() {
    RawToken rt = new RawToken("speaking");
    rt.addWord(new RawWord());
    assertEquals(1, rt.getWords().size());

    rt = new RawToken("twoAdd");
    rt.addWord(new RawWord());
    rt.addWord(new RawWord());
    assertEquals(2, rt.getWords().size());

    rt = new RawToken("threeAdd");
    rt.addWord(new RawWord());
    rt.addWord(new RawWord());
    rt.addWord(new RawWord());
    assertEquals(3, rt.getWords().size());
  }

  public void testAddAllWords() {
    RawToken rt = new RawToken("all");
    rt.addAllWords(new ArrayList<>());
    assertEquals(0, rt.getWords().size());

    rt = new RawToken("rawT");
    rt.addAllWords(new ArrayList<>(asList(
        new RawWord(), new RawWord(), new RawWord())));
    assertEquals(3, rt.getWords().size());
  }

  @Test
  public void testGetLemma() {
    RawToken rt = new RawToken("thisWordDoesNotExist!");
    assertNull(rt.getLemma());

    rt = new RawToken("car");
    rt.setKnown(true);
    RawWord rw = new RawWord();
    rw.setLemma("car");
    rt.addWord(rw);
    assertEquals("car", rt.getLemma());

    rt = new RawToken("iHaveMultipleLemmas");
    rt.setKnown(true);
    rw = new RawWord();
    RawWord rww = new RawWord();
    rw.setLemma("firstLemma");
    rww.setLemma("secodnLemma");
    rt.addAllWords(asList(rw, rww));
    assertEquals("firstLemma", rt.getLemma());
  }

  @Test
  public void testGetPos() {
    RawToken rt = new RawToken("iWantNullValue");
    assertNull(rt.getPos());

    /*
    rt = new RawToken("iWantNullValueToo");
    rt.setKnown(true);
    assertNull(rt.getPos()); //Expcted null, got IndexOutOfBound exception
    */

    rt = new RawToken("car");
    rt.setKnown(true);
    RawWord rw = new RawWord();
    rw.setPos(POS.N);
    rt.addWord(rw);
    assertEquals(POS.N, rt.getPos());

    rt = new RawToken("day");
    rt.setKnown(true);
    rw = new RawWord();
    rw.setPos(POS.N);
    RawWord rww = new RawWord();
    rww.setPos(POS.AP);
    rt.addAllWords(asList(rww, rw));
    assertEquals(POS.AP, rt.getPos());
  }

  @Test
  public void testGetLexT() {
    RawToken rt = new RawToken("expNullValue");
    assertNull(rt.getLexT());

    /*
    rt = new RawToken("iWantNullValueToo");
    rt.setKnown(true);
    assertNull(rt.getLexT()); //Expcted null, got IndexOutOfBound exception
    */

    rt = new RawToken("car");
    rt.setKnown(true);
    RawWord rw = new RawWord();
    rw.setLexType(LEX.OBJECT);
    rt.addWord(rw);
    assertEquals(LEX.OBJECT, rt.getLexT());

    rt = new RawToken("day");
    rt.setKnown(true);
    rw = new RawWord();
    rw.setLexType(LEX.GENERIC);
    RawWord rww = new RawWord();
    rww.setLexType(LEX.TIME);
    rt.addAllWords(asList(rww, rw));
    assertEquals(LEX.TIME, rt.getLexT());
  }

  @Test
  public void testToString() {
    RawToken rt = new RawToken("token");
    assertEquals("RawToken - OValue = token Value = token Corrected = false Known = false",
        rt.toString());

    rt = new RawToken("isKnown!");
    rt.setKnown(true);
    RawWord rw = new RawWord();
    rw.setWordId(new WordID(1, edu.mit.jwi.item.POS.VERB, 1));
    rw.setLemma("unk");
    rw.setPos(POS.N);
    rw.setLexType(LEX.GENERIC);
    rw.setGloss("unknown");
    rt.addWord(rw);
    assertEquals("RawToken - OValue = isKnown! Value = isKnown! Corrected = false Known = true\n Main Word (with more occurrence):WordId = WID-00000001-V-01-? Lemma = unk POS = N LextT = GENERIC Gloss = unknown Occurrence = 0 \n All words:\n->WordId = WID-00000001-V-01-? Lemma = unk POS = N LextT = GENERIC Gloss = unknown Occurrence = 0\n[]", rt.toString());
  }
}
