package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Word;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Stores a response from Curiosone's Brain.
 */
public class BrainResponse {

  /**
  * Stores the content of the computed answer.
  */
  private String text;

  /**
   * Stores the scope of the computed answer.
   */
  private String scope;

  /**
   * Constructs a BrainResponse instance, containing a text and a scope.
   * @param text The content of the computed answer
   * @param scope The scope of the computed answer
   */
  public BrainResponse(String text, String scope) {
    this.text = text;
    this.scope = scope;
  }

  /**
   * Returns the content of the computed answer.
   */
  public String getMessage() {
    return text;
  }

  /**
   * Returns the scope of the computed answer.
   */
  public String getScope() {
    return scope;
  }

  /**
   * Returns a String representation of this BrainResponse.
   */
  @Override
  public String toString() {
    return text + "(" + scope + ")";
  }

  /**
   * Checks whether this BrainResponse equals to the given Object or not.
   * @param other the other response to be compared against
   * @return {@code true} if this response equals the other response;
   *         {@code false} otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    BrainResponse that = (BrainResponse) other;
    return this.text.equals(that.text) && this.scope.equals(that.scope);
  }

  /**
   * Returns the HashCode for this BrainResponse.
   * The HashCode depends on the content and the scope of this BrainResponse.
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, scope);
  }
}
