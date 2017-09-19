package com.github.bot.curiosone.core.nlp.extraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.github.bot.curiosone.core.knowledge.SemanticNetwork;
import com.github.bot.curiosone.core.knowledge.SemanticRelationType;
import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.cyk.POS;
import com.github.bot.curiosone.core.nlp.cyk.Sentence;
import com.github.bot.curiosone.core.nlp.raw.RawDict;
import java.util.stream.Collectors;

/**
 * Sentences labeled as questions go through this extraction route.
 * 
 * @see ExtractionRoute
 *
 */
public class QuestionExtractor implements ExtractionRoute {

  @Override
  public Response extract(Sentence s) {
        
    String object = s.get(POS.NP)[0].entrySet().stream()
        .filter(entry -> entry.getValue().contains(POS.N))
        .map(Map.Entry::getKey)
        .findFirst()
        .get();
    
    String verb = s.get(POS.V)[0].entrySet().stream()
        .map(Map.Entry::getKey)
        .findFirst()
        .get();
    
    ArrayList<String> adj = s.get(POS.NP)[0].entrySet().stream()
        .filter(entry -> entry.getValue().contains(POS.ADJ))
        .map(Map.Entry::getKey)
        .collect(Collectors.toCollection(ArrayList::new));
    
    if (s.respect(new POS[]{POS.PRON,POS.V,POS.NP})) {
      
      if (isWhat(s)) {
        
        return search(new SemanticQuery(SemanticRelationType.HYPERNYM, object, adj, verb));
     }
      
      else if (isWho(s)) {
                
        return search(new SemanticQuery(SemanticRelationType.IS_PERSON, object, adj, verb));
      }
         
    }
    
    else if (s.respect(new POS[]{POS.ADV,POS.V,POS.NP})) {
      
      if (isWhere(s)) {
        
        return search(new SemanticQuery(SemanticRelationType.PLACE, object, adj, verb));

      }
      
      else if (isWhen(s)) {
       
        return search(new SemanticQuery(SemanticRelationType.TIME, object, adj, verb));   
        
      }
      
    }
    
    else {    
      
      for (LEX lex: s.getWords().get(object).stream()
          .map(Meaning::getLEX)
          .collect(Collectors.toList()) ) {
            
            switch(lex) {
              
              case PERSON: return search(new SemanticQuery(SemanticRelationType.IS_PERSON, object, adj, verb));          
              case TIME: return search(new SemanticQuery(SemanticRelationType.TIME, object, adj, verb));      
              case LOCATION: return search(new SemanticQuery(SemanticRelationType.PLACE, object, adj, verb));           
              default: return search(new SemanticQuery(SemanticRelationType.HYPERNYM, object, adj, verb));           
              
            }
      }
          
      return search(new SemanticQuery(SemanticRelationType.HYPERNYM, object, adj, verb));
    }
  
  }


  private boolean isWhat(Sentence s) {
      
      return s.get(POS.PRON)[0].entrySet().stream()
          .map(Map.Entry::getKey)
          .findFirst()
          .get()
          .equals("what");     
    }

  private boolean isWho(Sentence s) {
      
      return s.get(POS.PRON)[0].entrySet().stream()
          .map(Map.Entry::getKey)
          .findFirst()
          .get()
          .equals("who");    
    }   
   
  private boolean isWhere(Sentence s) {
      
      return s.get(POS.ADV)[0].entrySet().stream()
          .map(Map.Entry::getKey)
          .findFirst()
          .get()
          .equals("where");     
    }
      
  private boolean isWhen(Sentence s) {
      
      return s.get(POS.ADV)[0].entrySet().stream()
          .map(Map.Entry::getKey)
          .findFirst()
          .get()
          .equals("when");     
    }
       
  private Response search(SemanticQuery q) {
    
    Edge e = SemanticNetwork.getInstance().getAnswer(q.getRelation(), q.getObject()).get().stream()
        .findFirst()
        .get();
    
    String target = e.getTarget().getId();
    
    Response r = new Response(e.getType());
    
    q.getAdjectives().forEach(adj -> r.add(adj, POS.ADJ));
    
    return r.add(target, RawDict.getInstance().getRawToken(target).getPos())
        .add(q.getVerb(), POS.V)
        .add(q.getObject(), POS.N);       
  }
  
}
