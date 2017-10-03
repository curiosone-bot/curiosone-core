package com.github.bot.curiosone.core.analysis;

import com.github.bot.curiosone.core.nlp.Phrase;

/**
 * Handles emotion analysis for a Phrase.
 * Provides a single public static method to perform to calculate the emotion of a given Phrase.
 */
public class EmotionAnalysis {

  /**
   * Private constructor.
   */
  private EmotionAnalysis() {}

  /**
   * Performs an emotion analysis of the provided Phrase.
   * @param p the Phrase to be analysed.
   * @return a String representation of the calculated emotion.
   * Currently, supports "sad", "happy", "angry" and "neutral" feelings.
   */
  public static String getEmotion(Phrase p) {
    //TODO: Add implementation.
    return "";
  }
}
