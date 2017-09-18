package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LangUtilsTest {
  @Test
  public void testSplitByPuntaction() {
    List<String> l;

    l = LangUtils.splitByPuntaction("test string");
    assertEquals(1, l.size());
    assertEquals("test string.", l.get(0));

    l = LangUtils.splitByPuntaction("Unicorns are awesome! Isn't it?");
    assertEquals(2, l.size());
    assertEquals("Unicorns are awesome!", l.get(0));
    assertEquals(" Isn't it?", l.get(1));

    l = LangUtils.splitByPuntaction("!!!!!!asd!!!!! dasda.!!!!???!");
    assertEquals(2, l.size());
    assertEquals("asd!", l.get(0));
    assertEquals(" dasda.", l.get(1));

    l = LangUtils.splitByPuntaction("!!!!!!");
    assertEquals(0, l.size());

    l = LangUtils.splitByPuntaction("!!! !!!");
    assertEquals(1, l.size());
    assertEquals(" !", l.get(0));

    l = LangUtils.splitByPuntaction("Hello! Visit my website: http://evil.com/pwn.you pls");
    assertEquals(2, l.size());
    assertEquals("Hello!", l.get(0));
    assertEquals(" Visit my website: http://evil.com/pwn.you pls.", l.get(1));

    l = LangUtils.splitByPuntaction("Hello!! Contact me at spam@bot.com to get more spam! ;)");
    assertEquals(3, l.size());
    assertEquals("Hello!", l.get(0));
    assertEquals(" Contact me at spam@bot.com to get more spam!", l.get(1));
    assertEquals(" ;).", l.get(2));

    l = LangUtils.splitByPuntaction("");
    assertEquals(0, l.size());

    l = LangUtils.splitByPuntaction("$plit me");
    assertEquals(1, l.size());
    assertEquals("$plit me.", l.get(0));

    l = LangUtils.splitByPuntaction("42 is a number");
    assertEquals(1, l.size());
    assertEquals("42 is a number.", l.get(0));

    l = LangUtils.splitByPuntaction("42.0 is a double");
    assertEquals(1, l.size());
    assertEquals("42.0 is a double.", l.get(0));

    l = LangUtils.splitByPuntaction("This ://cel@.com is an invalid uri");
    assertEquals(1, l.size());
    assertEquals("This ://cel@.com is an invalid uri.", l.get(0));
  }

  @Test
  public void testRemoveDuplicatedSpaces() {
    String s;

    s = LangUtils.removeDuplicatedSpaces("Hello   world!    !");
    assertEquals("Hello world! !", s);

    s = LangUtils.removeDuplicatedSpaces("           !  ");
    assertEquals("!", s);

    s = LangUtils.removeDuplicatedSpaces("   he llo ");
    assertEquals("he llo", s);

    s = LangUtils.removeDuplicatedSpaces("");
    assertEquals("", s);

    s = LangUtils.removeDuplicatedSpaces("Hello");
    assertEquals("Hello", s);
  }

  @Test
  public void testExpandVerbs() {
    String s;

    s = LangUtils.expandVerbs("I'm in!");
    assertEquals("I am in!", s);

    s = LangUtils.expandVerbs("i'm done");
    assertEquals("i am done", s);

    s = LangUtils.expandVerbs("i've done it");
    assertEquals("i have done it", s);

    s = LangUtils.expandVerbs("I'll kill you");
    assertEquals("I will kill you", s);

    s = LangUtils.expandVerbs("I won't kill you!");
    assertEquals("I will not kill you!", s);

    s = LangUtils.expandVerbs("i'm done! i'm here!");
    assertEquals("i am done! i am here!", s);

    s = LangUtils.expandVerbs("I couldn't resist...");
    assertEquals("I could not resist...", s);

    s = LangUtils.expandVerbs("I could've gotten more!");
    assertEquals("I could have gotten more!", s);

    s = LangUtils.expandVerbs("I shouldn't be here!");
    assertEquals("I should not be here!", s);

    s = LangUtils.expandVerbs("Mike's a good boy, just as you're!");
    assertEquals("Mike is a good boy, just as you are!", s);

    s = LangUtils.expandVerbs(
        "I didn't touch your phone! You're a liar! I didn't do anything!!!");
    assertEquals(
        "I did not touch your phone! You are a liar! I did not do anything!!!",
            s);
  }
}
