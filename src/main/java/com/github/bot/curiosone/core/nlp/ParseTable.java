package com.github.bot.curiosone.core.nlp;

import com.github.bot.curiosone.core.util.Interval;
import com.github.bot.curiosone.core.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Handles the CYK parsing table, which contains all possible parsing trees for the given Sentence.
 * Provides all the useful methods to create and manage the parsed CYK table.
 * @see  com.github.bot.curiosone.core.nlp.Token The Token Class
 * @see  com.github.bot.curiosone.core.nlp.Rule The Rule Class
 * @see  Class The Cell Class
 */
public class ParseTable {

  /**
   * Stores the CYK table.
   * @see  Cell The Cell Class
   */
  private Cell[][] table;

  /**
   * Lists all the tokens from witch the table was generated.
   * @see  com.github.bot.curiosone.core.nlp.Token The Token Class
   */
  private List<Token> tokens;

  /**
   * Stores the size of the CYK table.
   */
  private int size;

  /**
   * Constructs a CYK table for the given tokens list.
   * @param  tokens
   *         list of tokens to be parsed
   */
  public ParseTable(List<Token> tokens) {
    this.tokens = tokens;
    size = tokens.size();

    table = new Cell[size][];
    for (int x = 0; x < size; x++) {
      table[x] = new Cell[x + 1];
    }
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < y + 1; x++) {
        table[y][x] = new Cell();
      }
    }

    // first cycle to fill base of the tab
    for (int x = 0; x < size; x++) {
      Set<Rule> rules = tokens.get(x).getMeanings().stream()
          .map(m -> new Rule(m.getPOS(), Pair.create(POS.UNKN, POS.UNKN)))
          .collect(Collectors.toSet());

      table[size - 1][x].get().addAll(rules);
    }

    // down-up
    for (int y = size - 2; y >= 0; y--) {
      // left-right
      for (int x = 0; x < y + 1; x++) {
        // depth
        for (int z = 1; z + y < size; z++) {
          // set of first possible values
          Set<Rule> firsts = table[size - z][x].get();
          // set of second possible values
          Set<Rule> seconds = table[y + z][x + z].get();

          for (Rule f : firsts) {
            for (Rule s : seconds) {
              Set<Rule> r = Rule.allTo(Pair.create(f.getFrom(), s.getFrom()));
              table[y][x].get().addAll(r);
            }
          }
        }
      }
    }
  }

  /**
   * Gets the content of a specific cell of the CYK.
   * @param  x
   *         x coordinate of the table
   * @param  y
   *         y coordinate of the table
   * @return  a set of all rules that makes us arrive to that cell
   * @throws IndexOutOfBoundsException if at least one coordinate is outside of the parsed table.
   */
  public Set<Rule> get(int x, int y) {
    if (y < 0 || y > size || x < 0 || x > y) {
      throw new IndexOutOfBoundsException("Position outside the table [" + x + ", " + y + "]");
    }
    return table[y][x].get();
  }

  /**
   * @return  the height of the table.
   */
  public int getHeight() {
    return size;
  }

  /**
   * @return  the width at a specific distance from the top of the table.
   */
  public int getWidthAt(int y) {
    return y + 1;
  }

  /**
   * Visits the table and extracts intervals.
   * @param  meanings
   *         the list of meanings extracted for each token
   * @param  lookup
   *         the map where the intervals are stored
   * @param  x
   *         the x position of the table
   * @param  y
   *         the y position of the table
   * @param  current
   *         the role to use at this position
   */
  public void traverse(List<Set<Meaning>> meanings, Map<POS, TreeSet<Interval>> lookup,
                       int x, int y, Rule current) {
    TreeSet<Interval> list = lookup.getOrDefault(current.getFrom(), new TreeSet<Interval>());
    if (y == size - 1) {
      list.add(new Interval(x, x));
      lookup.put(current.getFrom(), list);
      Token token = tokens.get(x);
      Set<Meaning> means = token.getMeanings().stream()
          .filter(m -> m.getPOS() == current.getFrom())
          .collect(Collectors.toSet());
      meanings.get(x).addAll(means);
      return;
    }
    list.add(new Interval(x, x - 1 + size - y));
    lookup.put(current.getFrom(), list);

    // Search down
    POS left = current.getTo().getFirst();
    for (int by = y + 1; by < size; by++) {
      boolean found = false;
      for (Rule r : table[by][x].get()) {
        if (r.getFrom().equals(left)) {
          found = true;
          traverse(meanings, lookup, x, by, r);
        }
      }
      if (found) {
        break;
      }
    }
    // Search diagonally right
    POS right = current.getTo().getSecond();
    for (int by = y + 1, bx = x + 1; by < size && bx < size; by++, bx++) {
      boolean found = false;
      for (Rule r : table[by][bx].get()) {
        if (r.getFrom().equals(right)) {
          found = true;
          traverse(meanings, lookup, bx, by, r);
        }
      }
      if (found) {
        break;
      }
    }
  }

  /**
   * @return  a String representation for this CYK table.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < x + 1; y++) {
        sb.append(table[x][y]);
      }
      sb.append('\n');
    }
    return sb.toString();
  }

  /**
   * Represents a single Cell of the CYK table.
   * Provides methods to create a Cell and retreive its content.
   */
  private class Cell {

    /**
     * A Set of Rules that makes us arrive to that Cell.
     * @see  com.github.bot.curiosone.core.nlp.Rule The Rule Class
     */
    private Set<Rule> content = new HashSet<Rule>();

    /**
     * Constructs an empty Cell.
     */
    public Cell() {}

    /**
     * @return  the Set of Rules inside the Cell.
     */
    public Set<Rule> get() {
      return content;
    }

    /**
     * @return  a String representation of this Cell.
     */
    @Override
    public String toString() {
      return content.toString();
    }
  }
}
