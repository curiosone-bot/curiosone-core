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
  private static final String uri = "/dictionary/DataDict.properties";

  /**
   * The map which contains words and their sentiment's score.
   */
  private static Map<String, Double> dict;

  /**
   * Initializing of the properties.
   */
  private static Properties properties = new Properties();


  /**
   * Loading the .properties file in the dictionary.
   * @Return the loaded dictionary
   */
  public static Map<String, Double> loadDict() {
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
    return dict;
  }

}
