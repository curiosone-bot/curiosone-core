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
 * The CYK parsing table which contains all possible parsing trees for the given
 * sentence.
 */
public class ParseTable {
  /** CYK table. */
  private Cell[][] table;

  /** Tokens from witch the table was generated. */
  private List<Token> tokens;

  /** Size of the table. */
  private int size;

  /**
   * CYK Constructor.
   *
   * @param tokens list of tokens to parse
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
   * Get the content of a specific cell of the CYK.
   *
   * @param x the x coordinate of the table
   * @param y the y coordinate of the table
   * @return the set of all rules that makes us arrive to that cell.
   */
  public Set<Rule> get(int x, int y) {
    if (y < 0 || y > size || x < 0 || x > y) {
      throw new IndexOutOfBoundsException("Position outside the table [" + x + ", " + y + "]");
    }
    return table[y][x].get();
  }

  /**
   * Get the height of the table.
   *
   * @return the height of the table.
   */
  public int getHeight() {
    return size;
  }

  /**
   * Get the width at a specific distance from the top of the table.
   *
   * @return the width at a specific height.
   */
  public int getWidthAt(int y) {
    return y + 1;
  }

  /**
   * Visits the table and extracts intervals.
   *
   * @param meanings the list of meanings extracted for each token
   * @param lookup the map where the intervals are stored
   * @param x the x position of the table
   * @param y the y position of the table
   * @param current the role to use at this position
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
      if (meanings.get(x) == null) {
        meanings.set(x, new HashSet<>(means));
      } else {
        meanings.get(x).addAll(means);
      }
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
    for (int by = y + 1; by < size; by++) {
      boolean found = false;
      for (Rule r : table[by][by].get()) {
        if (r.getFrom().equals(right)) {
          found = true;
          traverse(meanings, lookup, by, by, r);
        }
      }
      if (found) {
        break;
      }
    }
  }

  /**
   * Returns a string representation of this CYK table.
   *
   * @return a string representation of this CYK table.
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
   * A single Cell of the CYK table.
   */
  private class Cell {
    /** A set of rules that make us arrive to that cell. */
    private Set<Rule> content = new HashSet<Rule>();

    /**
     * Constructor of a Cell.
     */
    public Cell() {}

    /**
     * Gets the set of rules inside the cell.
     * @return the set of rules inside the cell.
     */
    public Set<Rule> get() {
      return content;
    }

    /**
     * Returns a string representation of this cell.
     *
     * @return a string representation of this cell.
     */
    @Override
    public String toString() {
      return content.toString();
    }
  }
}
