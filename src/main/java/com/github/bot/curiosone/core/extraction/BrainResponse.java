package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Word;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Stores a response from Curiosone's Brain.
 * Provides useful methods to create a BrainResponse and to retrieve its information.
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
   * @param  text
   *         The content of the computed answer
   * @param  scope
   *         The scope of the computed answer
   */
  public BrainResponse(String text, String scope) {
    this.text = text;
    this.scope = scope;
  }

  /**
   * @return  the content of the computed answer.
   */
  public String getMessage() {
    return text;
  }

  /**
   * @return  the scope of the computed answer.
   */
  public String getScope() {
    return scope;
  }

  /**
   * @return  a String representation of this BrainResponse.
   */
  @Override
  public String toString() {
    return text + "(" + scope + ")";
  }

  /**
   * Checks whether this BrainResponse equals to the given Object or not.
   * @param  other
   *         the other BrainResponse to be compared against
   * @return  {@code true} if this BrainResponse equals the other BrainResponse;
   *          {@code false} otherwise
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
   * @see  <a href="https://goo.gl/inr6Ra">The hashCode method of the Object Class</a>
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, scope);
  }
}
