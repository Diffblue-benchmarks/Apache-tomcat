package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.nio.charset.Charset;
import org.junit.Test;

public class URLEncoderDiffblueTest {
  /**
   * Test {@link URLEncoder#encode(String, Charset)}.
   * <ul>
   *   <li>Given {@link URLEncoder#DEFAULT}.</li>
   *   <li>When forName {@code UTF-8}.</li>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link URLEncoder#encode(String, Charset)}
   */
  @Test
  public void testEncode_givenDefault_whenForNameUtf8_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        URLEncoder.DEFAULT.encode("https://example.org/example", Charset.forName("UTF-8")));
  }

  /**
   * Test {@link URLEncoder#encode(String, Charset)}.
   * <ul>
   *   <li>Given {@link URLEncoder#QUERY}.</li>
   *   <li>When forName {@code UTF-8}.</li>
   *   <li>Then return {@code https%3A%2F%2Fexample.org%2Fexample}.</li>
   * </ul>
   * <p>
   * Method under test: {@link URLEncoder#encode(String, Charset)}
   */
  @Test
  public void testEncode_givenQuery_whenForNameUtf8_thenReturnHttps3a2f2FexampleOrg2Fexample() {
    // Arrange, Act and Assert
    assertEquals("https%3A%2F%2Fexample.org%2Fexample",
        URLEncoder.QUERY.encode("https://example.org/example", Charset.forName("UTF-8")));
  }

  /**
   * Test {@link URLEncoder#encode(String, Charset)}.
   * <ul>
   *   <li>Given {@link URLEncoder#URLEncoder()}.</li>
   *   <li>Then return {@code https%3A%2F%2Fexample%2Eorg%2Fexample}.</li>
   * </ul>
   * <p>
   * Method under test: {@link URLEncoder#encode(String, Charset)}
   */
  @Test
  public void testEncode_givenURLEncoder_thenReturnHttps3a2f2Fexample2Eorg2Fexample() {
    // Arrange
    URLEncoder urlEncoder = new URLEncoder();

    // Act and Assert
    assertEquals("https%3A%2F%2Fexample%2Eorg%2Fexample",
        urlEncoder.encode("https://example.org/example", Charset.forName("UTF-8")));
  }

  /**
   * Test {@link URLEncoder#clone()}.
   * <p>
   * Method under test: {@link URLEncoder#clone()}
   */
  @Test
  public void testClone() {
    // Arrange, Act and Assert
    assertTrue(URLEncoder.DEFAULT.clone() instanceof URLEncoder);
  }
}
