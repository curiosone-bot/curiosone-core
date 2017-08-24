package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetIDRelation;
import it.uniroma1.lcl.babelnet.BabelSynsetSource;
import it.uniroma1.lcl.babelnet.iterators.BabelSynsetIterator;
import it.uniroma1.lcl.jlt.util.Language;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is used to create first basic semantic network
 * for curiosone based on WordNet Concept from BabelNet.
 * @author Christian Sordi
 */
public class BasicSemanticNetworkExporter {
  private static void export() throws IOException {
    /* FIRST VERSION
    BabelSynsetIterator bni = BabelNet.getInstance().getSynsetIterator();
    while(bni.hasNext())
    {
      BabelSynset synset = bni.next();
      BabelSense source = synset.getMainSense((Language.EN));
      if(source.getSource()==BabelSenseSource.WN)
      {
        List<BabelSynsetIDRelation> edges = synset.getEdges();
        for ( BabelSynsetIDRelation relazione : edges)
        {
          if (relazione.getLanguage()==Language.EN)
          {
            BabelSense target = BabelNet.getInstance()
              .getSynset(relazione.getBabelSynsetIDTarget())
              .getMainSense(Language.EN);
            if (target.getSource()== BabelSenseSource.WN)
            {
              SemanticRelationType relation = null;
              BabelPointer pointer = relazione.getPointer();
              Set<String> relazioni = Arrays.stream(SemanticRelationType
                .values())
                .map(SemanticRelationType::toString)
                .collect(Collectors.toSet());
              if (relazioni.contains(pointer.toString().toUpperCase()))
              {
                Vertex v1 = new Concept(source.getLemma());
                Vertex v2 = new Concept(target.getLemma());
                this.addEdge(v1, v2, SemanticRelationType.valueOf(pointer
                  .toString().toUpperCase()));
              }
            }
          }
        }
      }
    }
    */
    /*   SECOND VERSION   */
    BabelNet bn = BabelNet.getInstance();
    StringBuffer exporter = new StringBuffer();
    Set<String> ourSemanticRType = Arrays.stream(SemanticRelationType.values())
          .map(SemanticRelationType::toString)
          .collect(Collectors.toSet());
    BabelSynsetIterator bsi = bn.getSynsetIterator();
    while (bsi.hasNext()) {
      BabelSynset bs = bsi.next();
      if (bs.getSynsetSource() == BabelSynsetSource.WN) {
        BabelSense source = bs.getMainSense(Language.EN);
        List<BabelSynsetIDRelation> edges = bs.getEdges();
        for (BabelSynsetIDRelation edge : edges) {
          BabelSynset bst = bn.getSynset(edge.getBabelSynsetIDTarget());
          if (edge.getLanguage() == Language.EN && bst.getSynsetSource() == BabelSynsetSource.WN) {
            BabelSense target = bst.getMainSense(Language.EN);
            String pointer = edge.getPointer().toString().toUpperCase();
            if (ourSemanticRType.contains(pointer.toString().toUpperCase())) {
              Vertex v1 = new Concept(source.getLemma());
              Vertex v2 = new Concept(target.getLemma());
              SemanticRelationType relation;
              relation = SemanticRelationType.valueOf(pointer.toString().toUpperCase());
              exporter.append(v1 + "," + relation + "," + v2);
              exporter.append(System.getProperty("line.separator")); 
            }
          }
        }
      }
    }
    int lastnewline = exporter.lastIndexOf("\n");
    if (lastnewline >= 0) {
      exporter.delete(lastnewline, exporter.length());
    }
    PrintWriter writer = 
        new PrintWriter("BasicSemanticNetwork/CuriosoneSemanticNetwork.txt", "UTF-8");
    writer.println(exporter.toString());
    writer.close();
    System.out.println("Rete Semantica di WordNet creata con successo");
  }
}
