package com.github.bot.curiosone.core.nlp;

import static org.assertj.core.api.Assertions.assertThat;
import static com.github.bot.curiosone.core.nlp.LangUtils.removeDuplicatedSpaces;
import static com.github.bot.curiosone.core.nlp.LangUtils.splitByPuntaction;
import static com.github.bot.curiosone.core.nlp.LangUtils.expandVerbs;
// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LangUtilsTest {
  @Test
  public void testSplitByPuntaction() {
    List<String> l;

    assertThat(splitByPuntaction("test string")).hasSize(1).containsOnly("test string.");

    assertThat(splitByPuntaction("Unicorns are awesome! Isn't it?")).hasSize(2)
        .containsOnly("Unicorns are awesome!", " Isn't it?");

    assertThat(splitByPuntaction("!!!!!!asd!!!!! dasda.!!!!???!")).hasSize(2)
        .containsOnly("asd!", " dasda.");

    assertThat(splitByPuntaction("?!!!!!asd!!!!! dasda.!!!!???!")).hasSize(2)
        .containsOnly("asd!", " dasda.");

    assertThat(splitByPuntaction(".!!!!!asd!!!!! dasda.!!!!???!")).hasSize(2)
        .containsOnly("asd!", " dasda.");

    assertThat(splitByPuntaction("!!!!!!")).isEmpty();

    assertThat(splitByPuntaction("!!! !!!")).hasSize(1).containsOnly(" !");

    assertThat(splitByPuntaction("Hello! Visit my website: http://evil.com/pwn.you pls"))
        .hasSize(2).containsOnly("Hello!", " Visit my website: http://evil.com/pwn.you pls.");

    assertThat(splitByPuntaction("Hello! Visit my website: http://evil?.com/pwn.you pls"))
        .hasSize(2).containsOnly("Hello!", " Visit my website: http://evil?.com/pwn.you pls.");

    assertThat(splitByPuntaction("Hello! Visit my website: http://evil!.com/pwn.you pls"))
        .hasSize(2).containsOnly("Hello!", " Visit my website: http://evil!.com/pwn.you pls.");

    assertThat(splitByPuntaction("Hello!! Contact me at spam@bot.com to get more spam! ;)"))
        .hasSize(3).containsOnly("Hello!", " Contact me at spam@bot.com to get more spam!", " ;).");

    assertThat(splitByPuntaction("Fortytwo.is.the.answer")).hasSize(4).containsOnly("Fortytwo.",
        "is.", "the.", "answer.");

    assertThat(splitByPuntaction("Fortytwo!is!the!answer")).hasSize(4).containsOnly("Fortytwo!",
        "is!", "the!", "answer.");

    assertThat(splitByPuntaction("Fortytwo?is?the?answer")).hasSize(4).containsOnly("Fortytwo?",
        "is?", "the?", "answer.");

    assertThat(splitByPuntaction("Hello!! Contact me at spam?@bot.com to get more spam! ;)"))
        .hasSize(3)
        .containsOnly("Hello!", " Contact me at spam?@bot.com to get more spam!"," ;).");

    assertThat(splitByPuntaction("Hello!! Contact me at spam!@bot.com to get more spam! ;)"))
        .hasSize(3)
        .containsOnly("Hello!", " Contact me at spam!@bot.com to get more spam!", " ;).");

    assertThat(splitByPuntaction("")).isEmpty();

    assertThat(splitByPuntaction("$plit me")).hasSize(1).containsOnly("$plit me.");

    assertThat(splitByPuntaction("42 is a number")).hasSize(1).containsOnly("42 is a number.");

    assertThat(splitByPuntaction("42.0 is a double")).hasSize(1).containsOnly("42.0 is a double.");

    assertThat(splitByPuntaction("This ://cel@.com is an invalid uri")).hasSize(1)
        .containsOnly("This ://cel@.com is an invalid uri.");
  }

  @Test
  public void testRemoveDuplicatedSpaces() {
    assertThat(removeDuplicatedSpaces("Hello   world!    !")).isEqualTo("Hello world! !");

    assertThat(removeDuplicatedSpaces("           !  ")).isEqualTo("!");

    assertThat(removeDuplicatedSpaces("   he llo ")).isEqualTo("he llo");

    assertThat(removeDuplicatedSpaces("")).isEqualTo("");

    assertThat(removeDuplicatedSpaces("Hello")).isEqualTo("Hello");
  }

  @Test
  public void testExpandVerbs() {
    String s;

    assertThat(expandVerbs("I'm in!")).isEqualTo("I am in!");

    assertThat(expandVerbs("i'm done")).isEqualTo("i am done");

    assertThat(expandVerbs("i've done it")).isEqualTo("i have done it");

    assertThat(expandVerbs("I'll kill you")).isEqualTo("I will kill you");

    assertThat(expandVerbs("I won't kill you!")).isEqualTo("I will not kill you!");

    assertThat(expandVerbs("i'm done! i'm here!")).isEqualTo("i am done! i am here!");

    assertThat(expandVerbs("I couldn't resist...")).isEqualTo("I could not resist...");

    assertThat(expandVerbs("I could've gotten more!")).isEqualTo("I could have gotten more!");

    assertThat(expandVerbs("I shouldn't be here!")).isEqualTo("I should not be here!");

    assertThat(expandVerbs("Mike's a good boy, just as you're!"))
        .isEqualTo("Mike is a good boy, just as you are!");

    assertThat(expandVerbs("I didn't touch your phone! You're a liar! I didn't do anything!!!"))
        .isEqualTo("I did not touch your phone! You are a liar! I did not do anything!!!");
  }
}
