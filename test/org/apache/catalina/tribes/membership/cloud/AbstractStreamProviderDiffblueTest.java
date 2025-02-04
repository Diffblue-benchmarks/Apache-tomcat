package org.apache.catalina.tribes.membership.cloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class AbstractStreamProviderDiffblueTest {
  /**
   * Test {@link AbstractStreamProvider#openConnection(String, Map, int, int)}.
   * <ul>
   *   <li>Given {@code Delivered-To}.</li>
   *   <li>Then return RequestProperties size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#openConnection(String, Map, int, int)}
   */
  @Test
  public void testOpenConnection_givenDeliveredTo_thenReturnRequestPropertiesSizeIsOne() throws Exception {
    // Arrange
    InsecureStreamProvider insecureStreamProvider = new InsecureStreamProvider();

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Delivered-To", "alice.liddell@example.org");

    // Act
    URLConnection actualOpenConnectionResult = insecureStreamProvider.openConnection("https://example.org/example",
        headers, 10, 10);

    // Assert
    Map<String, List<String>> requestProperties = actualOpenConnectionResult.getRequestProperties();
    assertEquals(1, requestProperties.size());
    List<String> getResult = requestProperties.get("Delivered-To");
    assertEquals(1, getResult.size());
    assertEquals("alice.liddell@example.org", getResult.get(0));
    assertEquals(10, actualOpenConnectionResult.getConnectTimeout());
    assertEquals(10, actualOpenConnectionResult.getReadTimeout());
  }

  /**
   * Test {@link AbstractStreamProvider#openConnection(String, Map, int, int)}.
   * <ul>
   *   <li>Given {@code Delivered-To}.</li>
   *   <li>When {@code war}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#openConnection(String, Map, int, int)}
   */
  @Test
  public void testOpenConnection_givenDeliveredTo_whenWar_thenThrowIOException() throws Exception {
    // Arrange
    InsecureStreamProvider insecureStreamProvider = new InsecureStreamProvider();

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Delivered-To", "alice.liddell@example.org");

    // Act and Assert
    assertThrows(IOException.class, () -> insecureStreamProvider.openConnection("war", headers, 10, 10));
  }

  /**
   * Test {@link AbstractStreamProvider#openConnection(String, Map, int, int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#openConnection(String, Map, int, int)}
   */
  @Test
  public void testOpenConnection_whenMinusOne_thenThrowIllegalArgumentException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new InsecureStreamProvider()).openConnection("https://example.org/example", null, 0, -1));
  }

  /**
   * Test {@link AbstractStreamProvider#openConnection(String, Map, int, int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#openConnection(String, Map, int, int)}
   */
  @Test
  public void testOpenConnection_whenMinusOne_thenThrowIllegalArgumentException2() throws Exception {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new InsecureStreamProvider()).openConnection("https://example.org/example", null, -1, 0));
  }

  /**
   * Test {@link AbstractStreamProvider#openConnection(String, Map, int, int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return Permission Name is {@code example.org:80}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#openConnection(String, Map, int, int)}
   */
  @Test
  public void testOpenConnection_whenZero_thenReturnPermissionNameIsExampleOrg80() throws Exception {
    // Arrange and Act
    URLConnection actualOpenConnectionResult = (new InsecureStreamProvider())
        .openConnection("https://example.org/example", null, 0, 0);

    // Assert
    assertEquals("example.org:80", actualOpenConnectionResult.getPermission().getName());
    assertEquals("https://example.org/example", actualOpenConnectionResult.getURL().toString());
    assertEquals(0, actualOpenConnectionResult.getConnectTimeout());
    assertEquals(0, actualOpenConnectionResult.getReadTimeout());
    assertEquals(0L, actualOpenConnectionResult.getIfModifiedSince());
    assertFalse(actualOpenConnectionResult.getAllowUserInteraction());
    assertFalse(actualOpenConnectionResult.getDoOutput());
    assertTrue(actualOpenConnectionResult.getDefaultUseCaches());
    assertTrue(actualOpenConnectionResult.getDoInput());
    assertTrue(actualOpenConnectionResult.getUseCaches());
    assertTrue(actualOpenConnectionResult.getRequestProperties().isEmpty());
  }

  /**
   * Test {@link AbstractStreamProvider#openStream(String, Map, int, int)}.
   * <ul>
   *   <li>Given {@code Delivered-To}.</li>
   *   <li>When {@code Url}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#openStream(String, Map, int, int)}
   */
  @Test
  public void testOpenStream_givenDeliveredTo_whenUrl_thenThrowIOException() throws Exception {
    // Arrange
    InsecureStreamProvider insecureStreamProvider = new InsecureStreamProvider();

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Delivered-To", "alice.liddell@example.org");

    // Act and Assert
    assertThrows(IOException.class, () -> insecureStreamProvider.openStream("Url", headers, 10, 10));
  }

  /**
   * Test {@link AbstractStreamProvider#openStream(String, Map, int, int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#openStream(String, Map, int, int)}
   */
  @Test
  public void testOpenStream_whenMinusOne_thenThrowIllegalArgumentException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new InsecureStreamProvider()).openStream("https://example.org/example", null, 0, -1));
  }

  /**
   * Test {@link AbstractStreamProvider#openStream(String, Map, int, int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#openStream(String, Map, int, int)}
   */
  @Test
  public void testOpenStream_whenMinusOne_thenThrowIllegalArgumentException2() throws Exception {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new InsecureStreamProvider()).openStream("https://example.org/example", null, -1, 0));
  }

  /**
   * Test {@link AbstractStreamProvider#configureCaCert(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractStreamProvider#configureCaCert(String)}
   */
  @Test
  public void testConfigureCaCert_whenNull_thenReturnArrayLengthIsOne() throws Exception {
    // Arrange, Act and Assert
    assertEquals(1, AbstractStreamProvider.configureCaCert(null).length);
  }
}
