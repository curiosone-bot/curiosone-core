package com.github.bot.curiosone.core.analysis;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * Loading the dictionary from a file.
 */

public class DictionaryLoader {

  private static DictionaryLoader instance;

  /**
   *The Uri containing the .properties file.
   */
  private static final String uri = "/dictionary/DataDict.properties";

  /**
   * The map which contains words and their sentiment's score.
   */
  private static Map<String, Double> dict;

  /**
   * Initializing of the properties.
   */
  private static Properties properties = new Properties();

  private DictionaryLoader() {
    dict = new HashMap<>();
    loadDict();
  }

  public static DictionaryLoader getInstance() {
    if (instance == null) {
      instance = new DictionaryLoader();
    }
    return instance;
  }

  /**
   * Loading the .properties file in the dictionary.
   * @Return the loaded dictionary
   */
  private static void loadDict() {
    Path path = null;
    try {
      URL resource = DictionaryLoader.class.getResource(uri);
      path = Paths.get(resource.toURI());
      properties.load(new FileInputStream(path.toString()));
    } catch (IOException|URISyntaxException e) {
      e.printStackTrace();
    }
    for (String key : properties.stringPropertyNames()) {
      dict.put(key, Double.parseDouble(properties.get(key).toString()));
    }
  }

  public Double getScore(String word) {
    return dict.containsKey(word) ? dict.get(word) : 0.0;
  }

}
