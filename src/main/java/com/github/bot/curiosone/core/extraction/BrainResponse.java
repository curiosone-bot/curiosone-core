package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Word;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A response from the Curiosone yet to be refined.
 */
public class BrainResponse {

  /** Description. */
  private String text;

  /** Description. */
  private String scope;

  /**
   * Constructor of a BrainResponse.
   *
   * @param text [description]
   * @param scope [description]
   */
  public BrainResponse(String text, String scope) {
    this.text = text;
    this.scope = scope;
  }

  /**
   * getMessage description.
   *
   * @return [description]
   */
  public String getMessage() {
    return text;
  }

  /**
   * getScope description.
   *
   * @return [description]
   */
  public String getScope() {
    return scope;
  }

  /**
   * Returns a string representation of this response.
   *
   * @return a string representation of this response.
   */
  @Override
  public String toString() {
    return text + "(" + scope + ")";
  }

  /**
   * Compares this response to the specified object.
   *
   * @param  other the other response
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
   * Returns an integer hash code for this response.
   *
   * @return an integer hash code for this response
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, scope);
  }
}
