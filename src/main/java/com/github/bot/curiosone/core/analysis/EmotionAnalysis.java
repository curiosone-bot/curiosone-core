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
   * @param phrase the Phrase to be analysed.
   * @return a String representation of the calculated emotion. Supports "sad", "happy" and "angry"
   */
  public static String getEmotion(Phrase phrase) {
    double score = TokenScorer.calculateScore(phrase.getTokens());
    if (score <= -0.5) {
      return "angry";
    }
    if (score < 0) {
      return "sad";
    }
    return "happy";
  }
}
