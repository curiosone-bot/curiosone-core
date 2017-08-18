package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.interfaces.Graph;
import com.github.bot.curiosone.core.knowledge.interfaces.GraphExporter;
import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

import java.util.Random;

/**
 * Class to create a JSON from a Semantic Network.
 * @author Christian Sordi
 */
public class JsonGraphExporter implements GraphExporter {

  /**
   * JSON creation method.
   * @param g the Semantic Network to be converted in JSON
   * @return String representation of the JSON
   */
  @Override
  public String export(Graph g) {
    StringBuffer returner = new StringBuffer();
    returner.append("elements: [ " + "\n");
    String colore = "";
    for (Vertex node : g.vertexSet()) {
      Random rand = new Random();
      int n = rand.nextInt(5);
      switch (n) {
        case 0: default: colore = "bg-red";
          break;
        case 1: colore = "bg-green";
          break;
        case 2: colore = "bg-purple";
          break;
        case 3: colore = "bg-blue";
          break;
        case 4: colore = "bg-salmon";
          break;
        case 5: colore = "bg-grey";
          break;
      }
      returner.append("{" + "\n" + "  " + "data : { id: '" + node.getId()
          + "' }, classes : '" + colore + "' \n}, \n");
    }
    int count = 1;
    for (Edge arco : g.edgeSet()) {
      returner.append("{" + "\n" + "  " + "data : { source: '"
          + arco.getSource() + "', target: '" + arco.getTarget()
          + "', type: '" + arco.getType() + "'}, classes : '"
          + colore + "' \n}");
      if (count < g.edgeSet().size()) {
        returner.append(",");
      }
      returner.append("\n");
      count++;
    }
    returner.append("]");
    return returner.toString();
  }
}