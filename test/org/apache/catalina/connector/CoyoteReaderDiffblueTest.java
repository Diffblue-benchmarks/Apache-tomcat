package org.apache.catalina.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.junit.Test;

public class CoyoteReaderDiffblueTest {
  /**
   * Test {@link CoyoteReader#clone()}.
   * <p>
   * Method under test: {@link CoyoteReader#clone()}
   */
  @Test
  public void testClone() throws CloneNotSupportedException {
    // Arrange, Act and Assert
    assertThrows(CloneNotSupportedException.class, () -> (new CoyoteReader(new InputBuffer(new Request()))).clone());
  }

  /**
   * Test {@link CoyoteReader#close()}.
   * <p>
   * Method under test: {@link CoyoteReader#close()}
   */
  @Test
  public void testClose() throws IOException {
    // Arrange
    CoyoteReader coyoteReader = new CoyoteReader(new InputBuffer(new Request()));

    // Act
    coyoteReader.close();

    // Assert
    assertNull(coyoteReader.ib.conv);
  }

  /**
   * Test {@link CoyoteReader#skip(long)}.
   * <ul>
   *   <li>Given {@link Response} (default constructor) addHeader {@code content-type} and {@code 42}.</li>
   *   <li>When zero.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteReader#skip(long)}
   */
  @Test
  public void testSkip_givenResponseAddHeaderContentTypeAnd42_whenZero_thenReturnZero() throws IOException {
    // Arrange
    Response response = new Response();
    response.addHeader("content-type", "42");

    Request coyoteRequest = new Request();
    coyoteRequest.setResponse(response);

    // Act and Assert
    assertEquals(0L, (new CoyoteReader(new InputBuffer(coyoteRequest))).skip(0L));
  }

  /**
   * Test {@link CoyoteReader#ready()}.
   * <ul>
   *   <li>Given {@link InputBuffer#InputBuffer(Request)} with coyoteRequest is {@link Request} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteReader#ready()}
   */
  @Test
  public void testReady_givenInputBufferWithCoyoteRequestIsRequest_thenReturnFalse() throws IOException {
    // Arrange, Act and Assert
    assertFalse((new CoyoteReader(new InputBuffer(new Request()))).ready());
  }

  /**
   * Test {@link CoyoteReader#ready()}.
   * <ul>
   *   <li>Given {@link Request} (default constructor) Available is one.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CoyoteReader#ready()}
   */
  @Test
  public void testReady_givenRequestAvailableIsOne_thenReturnTrue() throws IOException {
    // Arrange
    Request coyoteRequest = new Request();
    coyoteRequest.setAvailable(1);

    // Act and Assert
    assertTrue((new CoyoteReader(new InputBuffer(coyoteRequest))).ready());
  }

  /**
   * Test {@link CoyoteReader#markSupported()}.
   * <p>
   * Method under test: {@link CoyoteReader#markSupported()}
   */
  @Test
  public void testMarkSupported() {
    // Arrange, Act and Assert
    assertTrue((new CoyoteReader(new InputBuffer(new Request()))).markSupported());
  }
}
