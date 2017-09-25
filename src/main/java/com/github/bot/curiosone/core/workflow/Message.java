package com.github.bot.curiosone.core.workflow;

import java.util.Objects;

public class Message {
  /** The message. */
  String message;

  /** The scope. */
  String scope;

  /**
   * Constructor of a Message.
   * @param msg the message
   * @param scope the scope
   */
  public Message(String msg, String scope) {
    if (msg == null) {
      msg = "";
    }
    if (scope == null) {
      scope = "";
    }
    this.message = msg;
    this.scope = scope;
  }

  /**
   * Gets the message.
   * @return the message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Gets the scope.
   * @return the scope.
   */
  public String getScope() {
    return scope;
  }

  /**
   * Returns a string representation of this message.
   *
   * @return a string representation of this message.
   */
  @Override
  public String toString() {
    return message + " (" + scope + ")";
  }

  /**
   * Compares this message to the specified object.
   *
   * @param  other the other message
   * @return {@code true} if this message equals the other message;
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
    Message that = (Message) other;
    return this.message.equals(that.message) && this.scope.equals(that.scope);
  }

  /**
   * Returns an integer hash code for this message.
   *
   * @return an integer hash code for this message
   */
  @Override
  public int hashCode() {
    return Objects.hash(message, scope);
  }
}
