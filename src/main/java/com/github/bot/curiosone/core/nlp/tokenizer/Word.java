package com.github.bot.curiosone.core.nlp.tokenizer;

public class Word {
  /** Description */
  String txt;

  /** Description */
  PartOfSpeechType pos;

  /** Description */
  LexicalType lex;

  /**
   * [Word description]
   * @param  txt [description]
   * @param  pos [description]
   * @param  lex [description]
   * @return [description]
   */
  public Word(String txt, PartOfSpeechType pos, LexicalType lex) {
    this.txt = txt;
    this.pos = pos;
    this.lex = lex;
  }

  /**
   * [getPartOfSpeechType description]
   * @return [description]
   */
  public PartOfSpeechType getPartOfSpeechType() {
    return pos;
  }

  /**
   * [getLexicalType description]
   * @return [description]
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
