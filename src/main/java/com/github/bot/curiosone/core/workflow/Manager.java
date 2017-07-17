package workflow;

import com.github.bot.curiosone.core.nlp.GrammarProcessor;
import com.github.bot.curiosone.core.nlp.Author;
import com.github.bot.curiosone.core.nlp.SentenceType;
import com.github.bot.curiosone.core.knowledge.Elaborator;

public class Manager {
  private Automa robot;
  private GrammarProcessor processor;
  private Session session;

  public Manager() {
    robot = new Automa();
    processor = robot.getGrammarProcessor();
    session = robot.getSession();
  }

  public void elaborate(String text) {
    Sentece parsed = processor.parse(text, session.getLastBy(robot.getAuthor()));
    robot.nextState(parsed);
  }

  public static void main(String[] args) {

  }
}
