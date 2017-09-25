package com.github.bot.curiosone.api;

import spark.Request;
import spark.Response;

/**
 * Utility class to convert requests and responses into audit logs.
 */
public class CorsUtil {
  private static final String ORIGINS = "*";
  private static final String METHODS = "GET, PUT, POST, DELETE, HEAD";
  private static final String HEADERS = "";

  /**
  * Default Constructor.
  */
  private CorsUtil() {}

  /**
   * Set headers for CORS options requests.
   * @param req The request object.
   * @param res The response object.
   * @return The response body.
   */
  public static String options(Request req, Response res) {
    String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
    if (accessControlRequestHeaders != null) {
      res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
    }

    String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
    if (accessControlRequestMethod != null) {
      res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
    }

    return "OK";
  }

  /**
   * Set headers for CORS options requests.
   * @param req The request object.
   * @param res The response object.
   */
  public static void headers(Request req, Response res) {
    res.header("Access-Control-Allow-Origin", ORIGINS);
    res.header("Access-Control-Request-Method", METHODS);
    res.header("Access-Control-Allow-Headers", HEADERS);
  }
}
