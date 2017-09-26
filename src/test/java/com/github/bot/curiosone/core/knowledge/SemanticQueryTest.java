package com.github.bot.curiosone.core.knowledge;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class SemanticQueryTest {

  @Test
  public void testInstantiation() {
    assertTrue(new SemanticQuery(SemanticRelationType.IS_A, "dog", "BE") instanceof SemanticQuery);
  }

  @Test
  public void testGetRelation() {
    assertEquals(SemanticRelationType.IS_A,
        new SemanticQuery(SemanticRelationType.IS_A, "dog", "BE")
        .getRelation());
  }

  @Test
  public void testGetSubject() {
    assertEquals("animal",
        new SemanticQuery(SemanticRelationType.IS_A, "animal", "dog", "BE")
        .getSubject());
  }

  @Test
  public void testGetObject() {
    assertEquals("dog",
        new SemanticQuery(SemanticRelationType.IS_A, "dog", "BE")
        .getObject());
  }

  @Test
  public void testEquals() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "dog", "BE");
    assertTrue(sq.equals(sq));
    assertFalse(sq.equals(null));
    assertFalse(sq.equals(new Integer(42)));
    SemanticQuery sqq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "dog", "BE");
    assertTrue(sq.equals(sqq));
    sqq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", "BE");
    assertFalse(sq.equals(sqq));
  }

  @Test
  public void testHashCode() {
    assertEquals(new SemanticQuery(SemanticRelationType.IS_A, "dog", "BE").hashCode(),
        new SemanticQuery(SemanticRelationType.IS_A, "dog", "BE").hashCode());

    assertNotEquals(new SemanticQuery(SemanticRelationType.IS_A, "dog", "BE").hashCode(),
        new SemanticQuery(SemanticRelationType.IS_A, "spaceship", "BE"));
  }
}
