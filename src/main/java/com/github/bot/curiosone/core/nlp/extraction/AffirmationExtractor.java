package com.github.bot.curiosone.core.nlp.extraction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import com.github.bot.curiosone.core.knowledge.SemanticNetwork;
import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Sentence;
import com.github.bot.curiosone.core.nlp.Word;

public class AffirmationExtractor implements ExtractionRoute {
  
  private static final Path source = Paths.get("src/main/res/conversation/response.txt");
  
  private Word subject;
  private Word object;
  private Word verb;
  
  @Override
  public Response extract(Sentence s) {
    
    if (s.respect(POS.NP, POS.V, POS.NP)) {      
      this.subject = s.get(POS.N).get(0);      
    }
    
    else if (s.respect(POS.PRON, POS.V, POS.NP)) {
      this.subject = s.get(POS.PRON).get(0);    
    }
    
    else {
      this.subject = s.get(POS.N).get(0);      
    }

    this.object = s.parse(POS.V,POS.NP)[1].stream()
        .filter(word -> word.getMeanings().stream()
            .map(Meaning::getPOS)
            .collect(Collectors.toList()).contains(POS.N))
        .findFirst().get();
    
    this.verb = s.get(POS.V).get(0);      
    
    SemanticQuery query = new SemanticQuery(Relation.UNKNOWN, subject.getLemma(), object.getLemma(), verb.getLemma());       
   
    /*
     * chiamata che non compila
     * 
     * Optional<Edge> e = SemanticNetwork.getInstance().getAnswer(query).get().stream().findFirst();
     
    
    if (e.isPresent()) {
      
      // cambia discorso     
      
    }
    
    else {
      
      // memorizza
      
    }
    
    */
    
    Response rsp = new Response();
    rsp.setRaw(false);
    
    try {
      return      
          rsp.add(new Word("", Files.lines(source).findFirst().get().split("$")[0], null))
          .add(new Word(subject.getLemma(), subject.getLemma(), subject.getMeanings()))
          .add(new Word("",Files.lines(source).findFirst().get().split("$")[1], null)) ;
    } catch (IOException e) {
      return rsp.add(new Word("", "Sorry my head hurts, what were we talking about?", null));     
    }
        
    
  }

}
