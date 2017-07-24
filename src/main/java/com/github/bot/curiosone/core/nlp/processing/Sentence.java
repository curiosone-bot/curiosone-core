package com.github.bot.curiosone.core.nlp.processing;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
  String str;
  Language lang;
  Typology type;
  Relation relation;
  Scope scope;
  Author author;
  ArrayList<String> params;

  ParseTree tree;
  List<Token> tokens;

  Sentence(String str, Author author, Language lang) {
    this.str = str;
    this.author = author;
    this.lang = lang;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public Language getLang() {
    return lang;
  }

  public void setLang(Language lang) {
    this.lang = lang;
  }

  public Typology getType() {
    return type;
  }

  public void setType(Typology type) {
    this.type = type;
  }

  public Relation getRelation() {
    return relation;
  }

  public void setRelation(Relation relation) {
    this.relation = relation;
  }

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
    this.scope = scope;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public ArrayList<String> getParams() {
    return params;
  }

  public void setParams(ArrayList<String> params) {
    this.params = params;
  }

  public List<Token> getTokens() {
    return tokens;
  }

  public void setParseTree(ParseTree tree) {
    this.tree = tree;
  }

  public ParseTree getParseTree() {
    return tree;
  }
}
