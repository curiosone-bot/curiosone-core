package com.github.bot.curiosone.api;

import static org.hamcrest.CoreMatchers.containsString;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import spark.Spark;
import spark.utils.IOUtils;

/**
 * JUnit API test class.
 */
public class MainTest {

  /**
   * Set the port where it will run.
   */
  private static final String port = System.getenv("PORT") != null
      ? System.getenv("PORT") : "4567";

  /**
   * Set server URL.
   */
  private static final String server = "http://localhost:";

  /**
   * Set server URL Authority.
   */
  private static final String urlAuthority = server + port;

  /**
   * Set the expected exception thrown in test methods.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Set the expected error message on faulty requests.
   */
  private static String EXPECTED_ERR_REQ_MSG = "Sending request failed: "
        + urlAuthority;

  /**
  * Start the server and wait for its initialization.
  */
  @BeforeClass
  public static void before() {
    Main.main(null);
    Spark.awaitInitialization();
  }

  /**
  * Stop the server.
  */
  @AfterClass
  public static void after() {
    Spark.stop();
  }

  /**
   * Test GET request on /status path.
   * @result The request will be replied with the right body and status code.
   */
  @Test
  public void testStatusGetRequest() {
    TestResponse res = request("GET", "/status").get();
    Map<String, String> json = res.json();
    Assert.assertEquals(200, res.status);
    Assert.assertEquals("ok", json.get("status"));
  }

  /**
  * Test POST request on /status path.
  * @result The request should throw an IOException, triggering an AssertionError.
  */
  @Test
  public void testStatusPostRequest() {
    thrown.expect(AssertionError.class);
    thrown.expectMessage(containsString(EXPECTED_ERR_REQ_MSG));
    TestResponse res = request("POST", "/status").get();
  }

  /**
  * Test GET request on /talk path.
  * @result The request should throw an IOException, triggering an AssertionError.
  */
  @Test
  public void testTalkGetRequest() {
    thrown.expect(AssertionError.class);
    thrown.expectMessage(containsString(EXPECTED_ERR_REQ_MSG));
    TestResponse res = request("GET", "/talk").get();
  }

  /**
   * Test POST request on /talk path.
   * @result The request should receive a response, containing the right body and status code.
   */
  @Test
  public void testTalkPostRequest() {
    TestResponse res = request("POST", "/talk", "{message: \"Hello\", scope: \"\"}").get();
    Map<String, String> json = res.json();
    Assert.assertEquals(200, res.status);
    Assert.assertTrue(json.get("message") != null);
    Assert.assertTrue(json.get("scope") != null);
    System.out.println(json.get("message"));
    System.out.println(json.get("scope"));
  }

  /**
   * Execute a server request.
   * @param method a String identifier of the desired HTTP request.
   * @param path a String containing the server path for the HTTP request.
   * @return an Optional instance.
   *         If the request fails, the Optional instance is empty, otherwise,
   *         it contains a TestResponse instance.
   */
  private Optional<TestResponse> request(String method, String path) {
    try {
      URL url = new URL(urlAuthority + path);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(method);
      connection.setDoOutput(true);
      connection.connect();
      String body = IOUtils.toString(connection.getInputStream());
      return Optional.of(new TestResponse(connection.getResponseCode(), body));
    } catch (IOException e) {
      Assert.fail("Sending request failed: " + e.getMessage());
      return Optional.empty();
    }
  }

  /**
   * Execute a server request.
   * @param method a String identifier of the desired HTTP request.
   * @param path a String containing the server path for the HTTP request.
   * @return an Optional instance.
   *         If the request fails, the Optional instance is empty, otherwise,
   *         it contains a TestResponse instance.
   */
  private Optional<TestResponse> request(String method, String path, String body) {
    try {
      URL url = new URL(urlAuthority + path);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(method);
      connection.setDoOutput(true);
      connection.setDoInput(true);
      OutputStream os = connection.getOutputStream();
      os.write(body.getBytes("UTF-8"));
      connection.connect();
      String res = IOUtils.toString(connection.getInputStream());
      return Optional.of(new TestResponse(connection.getResponseCode(), res));
    } catch (IOException e) {
      Assert.fail("Sending request failed: " + e.getMessage());
      return Optional.empty();
    }
  }

  /**
   * This class represents an implementation of a HTTP response.
   */
  private static class TestResponse {

    /**
     * Body of the response.
     */
    public final String body;

    /**
     * Status of the response.
     */
    public final int status;

    /**
     * Default class constructor.
     * @param status the status of the response.
     * @param body the body of the response.
     */
    public TestResponse(int status, String body) {
      this.status = status;
      this.body = body;
    }

    /**
     * returns a Map instance, in order to mimic a JSON representation of the
     * obtained response.
     */
    public Map<String,String> json() {
      return new Gson().fromJson(body, HashMap.class);
    }
  }
}
