package com.github.bot.curiosone.core.workflow;

import java.util.Objects;

/**
 * Manages a Message.
 * A Message consists of two String: one to store the content of the Message and the other one to
 * store its scope.
 * Provides all the utility methods to create, access and compare a Message.
 */
public class Message {

  /**
   * Stores the content of this Message.
   */
  String message;

  /**
   * Stores the scope of this Message.
   */
  String scope;

  /**
   * Stores the scope of this Message.
   */
  String emotion;

  /**
   * Constructs this Message from a text/content and its scope.
   * @param  message
   *         Message content. Can be null.
   * @param  scope
   *         Message scope. Can be null.
   * @param  emotion
   *         Message emotion. Can be null.
   */
  public Message(String message, String scope, String emotion) {
    this.message = (message == null) ? "" : message;
    this.scope = (scope == null) ? "" : scope;
    this.emotion = (emotion == null) ? "" : emotion;
  }

  /**
   * @return  the content of this Message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * @return  the scope of this Message.
   */
  public String getScope() {
    return scope;
  }

  /**
   * @return  the emotion of this Message.
   */
  public String getEmotion() {
    return emotion;
  }

  /**
   * @return  a String representation of this Message.
   */
  @Override
  public String toString() {
    return message + " (" + scope + ")[" + emotion + "]";
  }

  /**
   * Checks whether this Message equals to the given Object.
   * @param  other
   *         the other Message to be compared against.
   * @return  {@code true} if this Message equals the other Message;
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
    Message that = (Message) other;
    return this.message.equals(that.message)
      && this.scope.equals(that.scope)
      && this.emotion.equals(that.emotion);
  }

  /**
   * Calculates the HashCode of this Message.
   * The HashCode is based on the HashCodes of the Message content and its scope.
   * @return  the HashCode of this Messagess
   */
  @Override
  public int hashCode() {
    return Objects.hash(message, scope, emotion);
  }
}
