package com.github.bot.curiosone.core.analysis;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Handles the dictionary used to score a sentiment index.
 * Provides methods to load the dictionary in memory and get data from it.
 */

public class DictionaryLoader {

  /**
   * Singleton instance of this class.
   */
  private static DictionaryLoader instance;

  /**
   * Stores the path to the dictionary file.
   */
  private static final String uri = "/dictionary/DataDict.properties";

  /**
   * Stores the Map with the word-sentiment score association.
   */
  private static Map<String, Double> dict;

  /**
   * Stores an utility Properties object, used during the loading process.
   */
  private static Properties properties = new Properties();

  /**
   * Constructs the Singleton instance.
   * Loads dictionary data into memory.
   */
  private DictionaryLoader() {
    dict = new HashMap<>();
    loadDict();
  }

  /**
   * Gets the Singleton instance.
   * @return  the Singleton instance.
   */
  public static DictionaryLoader getInstance() {
    if (instance == null) {
      instance = new DictionaryLoader();
    }
    return instance;
  }

  /**
   * Loads in memory the data from the dictionary file.
   */
  private static void loadDict() {
    Path path = null;
    try {
      URL resource = DictionaryLoader.class.getResource(uri);
      path = Paths.get(resource.toURI());
      properties.load(new FileInputStream(path.toString()));
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    for (String key : properties.stringPropertyNames()) {
      dict.put(key, Double.parseDouble(properties.get(key).toString()));
    }
  }

  /**
   * Returns a Double value, representing the sentiment score for the given Word.
   * If the given Word is unknown, a neutral score (0.00) is returned.
   * @param  word
   *         the Word to be analysed
   */
  public double getScore(String word) {
    return dict.getOrDefault(word, 0.0);
  }

}
