package com.github.bot.curiosone.api;

import com.github.bot.curiosone.core.workflow.Logic;
import com.github.bot.curiosone.core.workflow.Message;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class Main {
  /**
  * Logger for Main class.
  */
  private static final Logger log = LoggerFactory.getLogger(Main.class);

  /**
   * The entry point of the APIs.
   * @param args Unused arguments.
   */
  public static void main(String[] args) {

    /**
     * Set the port where it will run.
     */
    int port = System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 4567;
    Spark.port(port);

    /**
     * Enable CORS
     */
    Spark.before(CorsUtil::headers);
    Spark.options("/*", CorsUtil::options);

    /**
     * Set the right content type for the response.
     */
    Spark.after(
        (req, res) -> {
          res.type("application/json");
        });

    /*
     * Print audit logs.
     */
    Spark.after(
        (req, res) -> {
          log.info(AuditUtil.format(req, res));
        });

    /**
     * Respond with ok.
     */
    Spark.get(
        "/status",
        (req, res) -> {
          Map<String, String> map = new HashMap<String, String>();
          map.put("status", "ok");
          return new Gson().toJson(map);
        });

    /**
     * Route requests to the curiosone core
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