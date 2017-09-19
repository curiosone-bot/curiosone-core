package com.github.bot.curiosone.core.nlp;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * class which returns an answer to an input already present in memory
 * @author rufini
 */

public class Conversation {

  /**
   * txt containing known answers
   */
  private static final String KNOWN_QUESTIONS = "src\\main\\res\\conversation\\conversation.txt";
  
  /**
   * map from recognized tokens to possible phrases given in output
   */
  private static final Map<String[],String[]> knownQuestions = new HashMap<>();

  /**
   * private constructor
   */
  private Conversation() { }

  /**
   * loads the known answers in memory
   * @param line readed from the txt
   * @param splitIndex where tokens and answers to be recognized are splitted
   * @param key list of tokens to be recognized
   * @param values list of phrases that could be given in output
   * @throws IOException if file is written uncorrectly
   */
  private static void loadSentences(String line, int splitIndex, String[] key, String[] values) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(KNOWN_QUESTIONS));

    while (br.ready()) {
      line = br.readLine();
      splitIndex = line.indexOf(":");
      key = line.substring(0,splitIndex).split("\t");
      values = line.substring(splitIndex + 1,line.length()).split("\t");

      knownQuestions.put(key, values);
    }

    br.close();
  }

  /**
   * check if the given input is present in our known answers
   * @param p phrase given by user's input
   * @return answer if the input is known, else an empty string
   * @throws IOException if file is written uncorrectly
   */
  public static String getAnswer(Phrase p) throws IOException {
    if (knownQuestions.isEmpty()) {
      loadSentences("", 0, new String[] {}, new String[] {});
    }
    List<String> tokenList = p.getTokens()
        .stream()
        .map(x -> x.getText()) // DA CAMBIARE CON .getLemma()
        .collect(Collectors.toList());

    for (Map.Entry<String[], String[]> entry : knownQuestions.entrySet()) {
      boolean isKnown = true;

      for (String s : entry.getKey()) {
        if (!tokenList.contains(s)) {
          isKnown = false;
          break;
        }
      }
      if (isKnown) {
        return entry.getValue()[(int)(Math.random() * entry.getValue().length)];
      }
    }
    return "";
  }

  public static void main(String[] args) throws IOException {
    
    System.out.println(Conversation.getAnswer(new Phrase("i am Franco")));
    System.out.println(Conversation.getAnswer(new Phrase("how are you?")));
    System.out.println(Conversation.getAnswer(new Phrase("where do you live?")));
    System.out.println(Conversation.getAnswer(new Phrase("tell me a joke")));
    System.out.println(Conversation.getAnswer(new Phrase("are you stupid?")));
    System.out.println(Conversation.getAnswer(new Phrase("are you a human?")));
    System.out.println(Conversation.getAnswer(new Phrase("are we friends?")));
    System.out.println(Conversation.getAnswer(new Phrase("i want to play a game")));
    System.out.println(Conversation.getAnswer(new Phrase("can you dance?")));
    System.out.println(Conversation.getAnswer(new Phrase("how it is going?")));
     
    /**
     *ANSWERS:
      This information is not new to me..
      I do not know, do robots have sentiments? What about you?
      Right in front of you
      Can a kangaroo jump higher than a house? Of course, a house does not jump at all.
      Siri more than me
      No I am not
      Facebook teached me what a friend is
      I have different games in my brain, if you want to play, just click on the top-left side button on the screen
      No I am sorry
      Not so bad, what about you?
     */
  }
}
