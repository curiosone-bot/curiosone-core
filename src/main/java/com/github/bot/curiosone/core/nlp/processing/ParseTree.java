package com.github.bot.curiosone.core.nlp.processing;

import java.util.ArrayList;
import java.util.List;

import com.github.bot.curiosone.core.nlp.interfaces.ParsingException;
import com.github.bot.curiosone.core.nlp.interfaces.ParsingNode;

public class ParseTree {
  List<Token> tokens;
  ParsingNode radix;

  ParseTree(List<Token> tokens) throws ParsingException {
    this.tokens = tokens;

    parse();
  }

  @SuppressWarnings("unused")
  private void parse() throws ParsingException {
    ArrayList<ParsingNode> parsed = new ArrayList<>(tokens);

    ParsingNode node = null;

    while (parsed.size() > 1) {
      for (int x = 0; x <= parsed.size() - 2; x++) {
        node = verifyRule(parsed.get(x), parsed.get(x + 1));

        if (node == null)
          throw new ParsingException("Input string is not contained in Grammar");

        parsed.remove(parsed.get(x));
        parsed.remove(parsed.get(x + 1));

        parsed.add(x, node);

        break;
      }

    }

    this.radix = node;
  }

  private ParsingNode verifyRule(ParsingNode node1, ParsingNode node2) {
    for (Rule r : Grammar.get().getProcedures()) {
      if (r.getOut().contains(node1) && r.getOut().contains(node2))
        return new NonTerminal(r.getIn(), node1, node2);
    }

    return null;
  }

  public Typology getRoute() {
    return getRoute(radix);
  }

  private Typology getRoute(ParsingNode node) {
    for (ParsingNode n : node.getSons()) {
      switch (n.getValue()) {
      case WH:
        return Typology.QUESTION;
      case GRE:
        return Typology.AFFIRMATION;
      case YN:
        return Typology.AFFIRMATION;
      default:
        getRoute(n);
      }
    }

    return null;

  }

}
