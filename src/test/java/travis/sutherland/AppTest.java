package travis.sutherland;

import org.junit.Test;

/**
 * @author jooby generator
 */
public class AppTest extends BaseTest {

  @Test
  public void index() throws Exception {
    server.get("/")
        .expect(200)
        .header("Content-Type", "application/json;charset=UTF-8");
  }

}
