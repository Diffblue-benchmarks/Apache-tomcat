package org.apache.catalina.ssi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SSIEchoDiffblueTest {
  /**
   * Test {@link SSIEcho#isValidEncoding(String)}.
   * <ul>
   *   <li>When {@code entity}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIEcho#isValidEncoding(String)}
   */
  @Test
  public void testIsValidEncoding_whenEntity_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new SSIEcho()).isValidEncoding("entity"));
  }

  /**
   * Test {@link SSIEcho#isValidEncoding(String)}.
   * <ul>
   *   <li>When {@code none}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIEcho#isValidEncoding(String)}
   */
  @Test
  public void testIsValidEncoding_whenNone_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new SSIEcho()).isValidEncoding("none"));
  }

  /**
   * Test {@link SSIEcho#isValidEncoding(String)}.
   * <ul>
   *   <li>When {@code url}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIEcho#isValidEncoding(String)}
   */
  @Test
  public void testIsValidEncoding_whenUrl_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new SSIEcho()).isValidEncoding("url"));
  }

  /**
   * Test {@link SSIEcho#isValidEncoding(String)}.
   * <ul>
   *   <li>When {@code UTF-8}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIEcho#isValidEncoding(String)}
   */
  @Test
  public void testIsValidEncoding_whenUtf8_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new SSIEcho()).isValidEncoding("UTF-8"));
  }
}
