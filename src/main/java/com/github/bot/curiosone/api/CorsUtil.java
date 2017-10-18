package com.github.bot.curiosone.api;

import spark.Request;
import spark.Response;

/**
 * Utility Class to manage CORS mechanism.
 * Provides methods to set headers in Request objects.
 */
public class CorsUtil {

  /**
   * String representation of HTTP Origin header.
   */
  private static final String ORIGINS = "*";

  /**
   * String representation of HTTP Methods for RESTful Services.
   */
  private static final String METHODS = "GET, PUT, POST, DELETE, HEAD";

  /**
   * String representation of HTTP headers.
   */
  private static final String HEADERS = "";

  /**
  * Default Constructor.
  */
  private CorsUtil() {}

  /**
   * Sets headers for CORS options requests.
   * @param  req
   *         The Request Object.
   * @param  res
   *         The Response Object.
   * @return  The response body.
   * @see  <a href="https://goo.gl/T8LFRm">Spark Request Javadoc</a>
   * @see  <a href="https://goo.gl/nFCekX">Spark Response Javadoc</a>
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
   * Sets headers for CORS options Requests.
   * @param  req
   *         The Request Object.
   * @param  res
   *         The Response Object.
   * @see  <a href="https://goo.gl/T8LFRm">Spark Request Javadoc</a>
   * @see  <a href="https://goo.gl/nFCekX">Spark Response Javadoc</a>
   */
  public static void headers(Request req, Response res) {
    res.header("Access-Control-Allow-Origin", ORIGINS);
    res.header("Access-Control-Request-Method", METHODS);
    res.header("Access-Control-Allow-Headers", HEADERS);
  }
}
