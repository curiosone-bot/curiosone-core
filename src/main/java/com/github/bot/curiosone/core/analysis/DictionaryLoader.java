package com.github.bot.curiosone.core.analysis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Loading the dictionary from a file.
 *
 * @author Cosmo Pugliese && Francesco Natale.
 *
 */

public class DictionaryLoader {
  /**
   *The Uri containing the .properties file.
   */
  private static final String uri = "resources/Dictionary/DataDict.properties";
  
  /**
   * The map which contains words and their sentiment's score.
   */
  private static Map<String, Double> dict = new HashMap<>();
  
  /**
   * Initializing of the properties.
   */
  private static Properties properties = new Properties();
  

  /**
   * Loading the .properties file in the dictionary.
   * @Return the loaded dictionary
   */
  public static Map<String, Double> loadDict() {
    try {
      properties.load(new FileInputStream(uri));
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (String key : properties.stringPropertyNames()) {
      dict.put(key, Double.parseDouble(properties.get(key).toString()));
    }
    return dict;
  }
  
}
