package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Graph;
import com.github.bot.curiosone.core.knowledge.interfaces.GraphExporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class to export a Semantic Network, in order to provide a HTML representation.
 * @author Christian Sordi.
 *
 */
public class HtmlGraphExporter implements GraphExporter {
  /**
   * Convert a Semantic Network into a HTML Graph.
   * @param g the Graph to be converted
   * @return a String containing the HTML code of the HTML representation.
   */
  @Override
  public String export(Graph g) {
    StringBuffer finale = new StringBuffer();
    try {
      Scanner s = new Scanner(new File("resources/test.html"));
      while (s.hasNextLine()) {
        finale.append(s.nextLine() + "\n");
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    String[] pezzi = finale.toString().split("#END");
    finale.delete(0, finale.length());
    finale.append(pezzi[0] + "\n");
    JsonGraphExporter textExporter = new JsonGraphExporter();
    finale.append(textExporter.export(g) + "\n");
    finale.append(pezzi[2]);
    try {
      PrintWriter writer = new PrintWriter("resources/text3.html", "UTF-8");
      writer.println(finale.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //return finale.toString();
    return "ciao";
  }
}
