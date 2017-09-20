package com.github.bot.curiosone.core.knowledge;

import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetID;
import it.uniroma1.lcl.babelnet.BabelSynsetIDRelation;
import it.uniroma1.lcl.babelnet.InvalidBabelSynsetIDException;
import it.uniroma1.lcl.babelnet.data.BabelSenseSource;
import it.uniroma1.lcl.jlt.util.Language;

/**
 * This class is used to create first basic semantic network for curiosone based
 * on WordNet Concept from BabelNet.
 * @author Christian Sordi
 */
public class BasicSemanticNetworkExporter {
  public static void export() throws IOException, InvalidBabelSynsetIDException {
    BabelNet bn = BabelNet.getInstance();
    StringBuffer exporter = new StringBuffer();
    Path wnSynsetsTxt = new File("BasicSemanticNetwork/wn_synsets.txt").toPath();
    List<String> wn_synsets = Files.readAllLines(wnSynsetsTxt);
    Set<String> semanticRelationTypes = Arrays.stream(SemanticRelationType.values())
        .map(SemanticRelationType::toString).collect(toSet());
    for (String synsetID : wn_synsets) {
      Set<String> mainSenses = new HashSet<>();
      BabelSynset bs = bn.getSynset(new BabelSynsetID(synsetID));
      BabelSense source = bs.getMainSense(Language.EN);
      String sourceLemma = source.getSimpleLemma();
      Set<BabelSynsetIDRelation> edges = bs.getEdges().stream()
          .filter(x -> semanticRelationTypes.contains(x.getPointer().toString().toUpperCase()))
          .collect(Collectors.toSet());
      for (BabelSynsetIDRelation relation : edges) {
        BabelSense target = bn.getSynset(relation.getBabelSynsetIDTarget()).getMainSense(Language.EN);
        String targetLemma = target.getSimpleLemma();
        if (!mainSenses.contains(targetLemma)) {
          mainSenses.add(targetLemma);
          if (target.getSource() == BabelSenseSource.WN) {
            String pointer = relation.getPointer().toString().toUpperCase();
            exporter.append(sourceLemma + "," + "0" + ",");
            exporter.append(pointer + ",");
            exporter.append(targetLemma + "," + "0");
            exporter.append("\n");
          }
        }
      }
    }
    int lastNewLine = exporter.lastIndexOf("\n");
    if (lastNewLine >= 0) {
      exporter.delete(lastNewLine, exporter.length());
    }
    PrintWriter writer = new PrintWriter("resources/CuriosoneSemanticNetwork.txt", "UTF-8");
    writer.println(exporter.toString());
    writer.close();
    System.out.println("Rete Semantica di WordNet creata con successo");
  }
}
