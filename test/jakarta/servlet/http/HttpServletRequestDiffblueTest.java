package jakarta.servlet.http;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.coyote.Request;
import org.junit.Test;

public class HttpServletRequestDiffblueTest {
  /**
   * Test {@link HttpServletRequest#newPushBuilder()}.
   * <p>
   * Method under test: {@link HttpServletRequest#newPushBuilder()}
   */
  @Test
  public void testNewPushBuilder() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertNull(
        (new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))).newPushBuilder());
  }

  /**
   * Test {@link HttpServletRequest#isTrailerFieldsReady()}.
   * <p>
   * Method under test: {@link HttpServletRequest#isTrailerFieldsReady()}
   */
  @Test
  public void testIsTrailerFieldsReady() {
    // Arrange
    Connector connector = new Connector();

    // Act and Assert
    assertFalse((new HttpServletRequestWrapper(
        new RequestFacade(new org.apache.catalina.connector.Request(connector, new Request()))))
        .isTrailerFieldsReady());
  }
}
