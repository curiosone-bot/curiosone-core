package com.github.bot.curiosone.core.nlp.extraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.github.bot.curiosone.core.knowledge.SemanticNetwork;
import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Sentence;
import com.github.bot.curiosone.core.nlp.Token;
import com.github.bot.curiosone.core.nlp.Word;
import java.util.stream.Collectors;

/**
 * Sentences labeled as questions go through this extraction route.
 * 
 * @see ExtractionRoute
 *
 */
public class QuestionExtractor implements ExtractionRoute {

  private Word object;
  private Word verb;
  // private ArrayList<Word> adj;
  
  @Override
  public Response extract(Sentence s) {
        
    this.object = s.parse(POS.V,POS.NP)[1].stream()
        .filter(word -> word.getMeanings().stream()
            .map(Meaning::getPOS)
            .collect(Collectors.toList()).contains(POS.N))
        .findFirst().get();
        
    this.verb = s.get(POS.V).get(0);
    
    /* adj = s.parse(POS.V,POS.NP)[1].stream()
        .filter(word -> word.getMeanings().stream()
            .map(Meaning::getPOS)
            .collect(Collectors.toList()).contains(POS.ADJ))
        .collect(Collectors.toCollection(ArrayList::new)); */
    
    if (s.respect(new POS[]{POS.PRON,POS.V,POS.NP})) {
      
      if (isWhat(s)) {
        
        return search(new SemanticQuery(Relation.IS_A, object.getLemma(), verb.getLemma()));
     }
      
      else if (isWho(s)) {
                
        return search(new SemanticQuery(Relation.IS_PERSON, object.getLemma(), verb.getLemma()));
      }
         
    }
    
    else if (s.respect(new POS[]{POS.ADV,POS.V,POS.NP})) {
      
      if (isWhere(s)) {
        
        return search(new SemanticQuery(Relation.PLACE, object.getLemma(), verb.getLemma()));

      }
      
      else if (isWhen(s)) {
       
        return search(new SemanticQuery(Relation.TIME, object.getLemma(), verb.getLemma()));   
        
      }
      
    }
    
    else {    
      
      for (LEX lex: object.getMeanings().stream()
          .map(Meaning::getLEX)
          .collect(Collectors.toList())) {
            
            switch(lex) {
              
              case PERSON: return search(new SemanticQuery(Relation.IS_PERSON, object.getLemma(), verb.getLemma()));          
              case TIME: return search(new SemanticQuery(Relation.TIME, object.getLemma(), verb.getLemma()));      
              case LOCATION: return search(new SemanticQuery(Relation.PLACE, object.getLemma(), verb.getLemma()));           
              default: return search(new SemanticQuery(Relation.IS_A, object.getLemma(), verb.getLemma()));           
              
            }
      }
          
    }

    return search(new SemanticQuery(Relation.IS_A, object.getLemma(), verb.getLemma()));

  } 

  private boolean isWhat(Sentence s) {
      
      return s.get(POS.PRON).get(0)
          .getLemma()
          .equals("what");     
    }

  private boolean isWho(Sentence s) {
      
      return s.get(POS.PRON).get(0)
          .getLemma()
          .equals("who");
    }   
   
  private boolean isWhere(Sentence s) {
      
      return s.get(POS.ADV).get(0)
          .getLemma()
          .equals("where");     
    }
      
  private boolean isWhen(Sentence s) {
      
      return s.get(POS.ADV).get(0)
          .getLemma()
          .equals("when");     
    }
       
  private Response search(SemanticQuery q) {
    
    /*
     * Edge e = SemanticNetwork.getInstance().getAnswer(q).get().stream()
        .findFirst().get();
    
    String target = e.getTarget().getId(); 
    
    return new Response(e.getType()).add(new Word(target, target, object.getMeanings()))
        .add(verb)
        .add(object);       */
    
    return new Response().add(new Word("", "la chiamata non compila", null));
  }
  
}
