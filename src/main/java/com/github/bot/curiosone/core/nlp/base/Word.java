package com.github.bot.curiosone.core.nlp.base;

/**
* This class represents a word. It contains various grammar attributes of a
* word.
*/
public class Word {

  /**
  * The word to be represented.
  */
  String txt;

  /**
   * Part of speech type of the represented word.
   */
  PartOfSpeechType pos;

  /**
   * Lexical type of the represented word.
   */
  LexicalType lex;

  /**
   * Class constructor.
   * @param  txt The word.
   * @param  pos Part of speech type of the new word.
   * @param  lex Lexical type of the new word.
   */
  public Word(String txt, PartOfSpeechType pos, LexicalType lex) {
    this.txt = txt;
    this.pos = pos;
    this.lex = lex;
  }

  /**
   * Returns part of speech type for this Word.
   */
  public PartOfSpeechType getPartOfSpeechType() {
    return pos;
  }

  /**
   * Returns LexicalType for this Word.
   */
  public LexicalType getLexicalType() {
    return lex;
  }

  /**
   * [getLemma description]
   * @return [description]
   */
  public String getLemma() {
    return txt;
  }

  /**
   * [equals description]
   * @param  object [description]
   * @return [description]
   */
  public boolean equals(Object object) {
    if(!(object instanceof Word)) {
      return false;
    }
    Word w = (Word)object;
    return w.lex == this.lex && w.pos == this.pos && w.txt.equals(w.txt);
  }

  /**
   * [toString description]
   * @return [description]
   */
  @Override
  public String toString() {
    return "{text:" + txt + ", pos:" + pos + ", lex:" + lex + "}";
  }
}
