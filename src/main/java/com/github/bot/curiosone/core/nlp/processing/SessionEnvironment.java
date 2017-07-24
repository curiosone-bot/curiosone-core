package com.github.bot.curiosone.core.nlp.processing;

import java.time.LocalDateTime;

public class SessionEnvironment {
  String sessionID;

  String userID;
  LocalDateTime date;
  String location;

  Sentence lastSentence;

}
