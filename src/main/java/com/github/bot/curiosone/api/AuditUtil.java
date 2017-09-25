package com.github.bot.curiosone.api;

import spark.Request;
import spark.Response;

/**
 * Utility class to convert requests and responses into audit logs.
 */
public class AuditUtil {
  /**
  * Default Constructor.
  */
  private AuditUtil() {}

  /**
   * Format an audit log record.
   * @param req The request object.
   * @param res The response object.
   * @return The auti log object.
   */
  public static String format(Request req, Response res) {
    StringBuilder sb = new StringBuilder();
    sb.append(req.requestMethod());
    sb.append(" " + req.url());
    sb.append(" " + req.body());
    return sb.toString();
  }
}
