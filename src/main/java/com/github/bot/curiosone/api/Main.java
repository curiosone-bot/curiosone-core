package com.github.bot.curiosone.api;

import com.github.bot.curiosone.core.workflow.Logic;
import com.github.bot.curiosone.core.workflow.Message;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class Main {

  /**
  * Logs Main class.
  */
  private static final Logger log = LoggerFactory.getLogger(Main.class);

  /**
   * Default port value.
   */
  private static final int DEFAULT_PORT = 4567;

  /**
   * The entry point of the APIs.
   * @param args Unused arguments.
   */
  public static void main(String[] args) {

    /**
     * Sets the port where the server will run.
     */
    int port = System.getenv("PORT") != null
        ? Integer.parseInt(System.getenv("PORT")) : DEFAULT_PORT;
    Spark.port(port);

    /**
     * Enables CORS
     */
    Spark.before(CorsUtil::headers);
    Spark.options("/*", CorsUtil::options);

    /**
     * Sets the right content type for the response.
     */
    Spark.after(
        (req, res) -> {
          res.type("application/json");
        });

    /*
     * Prints audit logs.
     */
    Spark.after(
        (req, res) -> {
          log.info(AuditUtil.format(req, res));
        });

    /**
     * Responds with ok.
     */
    Spark.get(
        "/status",
        (req, res) -> {
          Map<String, String> map = new HashMap<String, String>();
          map.put("status", "ok");
          return new Gson().toJson(map);
        });

    /**
     * Routes requests to the Curiosone Core.
     */
    Spark.post(
        "/talk",
        (req, res) -> {
          Message user = null;
          Message bot = null;
          try {
            user = new Gson().fromJson(req.body(), Message.class);
          } catch (JsonSyntaxException e) {
            e.printStackTrace();
          }
          bot = Logic.talk(user);
          return new Gson().toJson(bot);
        });
  }
}
