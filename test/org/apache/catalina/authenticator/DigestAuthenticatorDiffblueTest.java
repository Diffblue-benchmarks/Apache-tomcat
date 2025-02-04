package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.authenticator.DigestAuthenticator.AuthDigest;
import org.apache.catalina.authenticator.DigestAuthenticator.DigestInfo;
import org.apache.catalina.authenticator.DigestAuthenticator.NonceInfo;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.junit.Test;

public class DigestAuthenticatorDiffblueTest {
  /**
   * Test AuthDigest getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AuthDigest#getJavaName()}
   *   <li>{@link AuthDigest#getRfcName()}
   * </ul>
   */
  @Test
  public void testAuthDigestGettersAndSetters() {
    // Arrange
    AuthDigest valueOfResult = AuthDigest.valueOf("MD5");

    // Act
    String actualJavaName = valueOfResult.getJavaName();

    // Assert
    assertEquals("MD5", actualJavaName);
    assertEquals("MD5", valueOfResult.getRfcName());
  }

  /**
   * Test DigestInfo getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DigestInfo#getUsername()}
   *   <li>{@link DigestInfo#isNonceStale()}
   * </ul>
   */
  @Test
  public void testDigestInfoGettersAndSetters() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);

    // Act
    String actualUsername = digestInfo.getUsername();

    // Assert
    assertNull(actualUsername);
    assertFalse(digestInfo.isNonceStale());
  }

  /**
   * Test DigestInfo {@link DigestInfo#DigestInfo(String, long, String, Map, boolean)}.
   * <p>
   * Method under test: {@link DigestInfo#DigestInfo(String, long, String, Map, boolean)}
   */
  @Test
  public void testDigestInfoNewDigestInfo() {
    // Arrange and Act
    DigestInfo actualDigestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);

    // Assert
    assertNull(actualDigestInfo.getUsername());
    assertFalse(actualDigestInfo.isNonceStale());
  }

  /**
   * Test DigestInfo {@link DigestInfo#parse(Request, String)}.
   * <p>
   * Method under test: {@link DigestInfo#parse(Request, String)}
   */
  @Test
  public void testDigestInfoParse() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Digest", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(digestInfo.parse(new Request(connector, new org.apache.coyote.Request()), "JaneDoe"));
  }

  /**
   * Test DigestInfo {@link DigestInfo#parse(Request, String)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestInfo#parse(Request, String)}
   */
  @Test
  public void testDigestInfoParse_thenReturnTrue() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Digest", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();

    // Act and Assert
    assertTrue(digestInfo.parse(new Request(connector, new org.apache.coyote.Request()), "Digest"));
  }

  /**
   * Test DigestInfo {@link DigestInfo#parse(Request, String)}.
   * <ul>
   *   <li>When {@code Digest}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestInfo#parse(Request, String)}
   */
  @Test
  public void testDigestInfoParse_whenDigest_thenReturnTrue() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();

    // Act and Assert
    assertTrue(digestInfo.parse(new Request(connector, new org.apache.coyote.Request()), "Digest"));
  }

  /**
   * Test DigestInfo {@link DigestInfo#parse(Request, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestInfo#parse(Request, String)}
   */
  @Test
  public void testDigestInfoParse_whenEmptyString_thenReturnFalse() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(digestInfo.parse(new Request(connector, new org.apache.coyote.Request()), ""));
  }

  /**
   * Test DigestInfo {@link DigestInfo#parse(Request, String)}.
   * <ul>
   *   <li>When {@code JaneDoe}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestInfo#parse(Request, String)}
   */
  @Test
  public void testDigestInfoParse_whenJaneDoe_thenReturnFalse() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(digestInfo.parse(new Request(connector, new org.apache.coyote.Request()), "JaneDoe"));
  }

  /**
   * Test DigestInfo {@link DigestInfo#parse(Request, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestInfo#parse(Request, String)}
   */
  @Test
  public void testDigestInfoParse_whenNull_thenReturnFalse() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();

    // Act and Assert
    assertFalse(digestInfo.parse(new Request(connector, new org.apache.coyote.Request()), null));
  }

  /**
   * Test DigestInfo {@link DigestInfo#validate(Request, List)}.
   * <ul>
   *   <li>Given {@code MD5}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code MD5}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestInfo#validate(Request, List)}
   */
  @Test
  public void testDigestInfoValidate_givenMd5_whenArrayListAddMd5() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    ArrayList<AuthDigest> algorithms = new ArrayList<>();
    algorithms.add(AuthDigest.MD5);

    // Act and Assert
    assertFalse(digestInfo.validate(request, algorithms));
  }

  /**
   * Test DigestInfo {@link DigestInfo#validate(Request, List)}.
   * <ul>
   *   <li>Given {@code SHA_256}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code SHA_256}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestInfo#validate(Request, List)}
   */
  @Test
  public void testDigestInfoValidate_givenSha256_whenArrayListAddSha256() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    ArrayList<AuthDigest> algorithms = new ArrayList<>();
    algorithms.add(AuthDigest.SHA_256);
    algorithms.add(AuthDigest.MD5);

    // Act and Assert
    assertFalse(digestInfo.validate(request, algorithms));
  }

  /**
   * Test DigestInfo {@link DigestInfo#validate(Request, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestInfo#validate(Request, List)}
   */
  @Test
  public void testDigestInfoValidate_whenArrayList() {
    // Arrange
    DigestInfo digestInfo = new DigestInfo("Opaque", 1L, "Key", new HashMap<>(), true);
    Connector connector = new Connector();
    Request request = new Request(connector, new org.apache.coyote.Request());

    // Act and Assert
    assertFalse(digestInfo.validate(request, new ArrayList<>()));
  }

  /**
   * Test new {@link DigestAuthenticator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DigestAuthenticator}
   */
  @Test
  public void testNewDigestAuthenticator() {
    // Arrange and Act
    DigestAuthenticator actualDigestAuthenticator = new DigestAuthenticator();

    // Assert
    assertEquals("Catalina", actualDigestAuthenticator.getDomain());
    assertEquals("DIGEST", actualDigestAuthenticator.getAuthMethod());
    assertEquals("NEW", actualDigestAuthenticator.getStateName());
    assertEquals("SHA-256,MD5", actualDigestAuthenticator.getAlgorithms());
    assertEquals("SHA1PRNG", actualDigestAuthenticator.getSecureRandomAlgorithm());
    assertEquals("never", actualDigestAuthenticator.getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        actualDigestAuthenticator.getJaspicCallbackHandlerClass());
    assertNull(actualDigestAuthenticator.getSecureRandomClass());
    assertNull(actualDigestAuthenticator.getSecureRandomProvider());
    assertNull(actualDigestAuthenticator.getKey());
    assertNull(actualDigestAuthenticator.getOpaque());
    assertNull(actualDigestAuthenticator.getDomainInternal());
    assertNull(actualDigestAuthenticator.nonces);
    assertNull(actualDigestAuthenticator.getObjectName());
    assertNull(actualDigestAuthenticator.getContainer());
    assertNull(actualDigestAuthenticator.getNext());
    assertNull(actualDigestAuthenticator.sso);
    assertNull(actualDigestAuthenticator.sessionIdGenerator);
    assertEquals(0, actualDigestAuthenticator.findLifecycleListeners().length);
    assertEquals(0L, actualDigestAuthenticator.lastTimestamp);
    assertEquals(100, actualDigestAuthenticator.getNonceCountWindowSize());
    assertEquals(1000, actualDigestAuthenticator.getNonceCacheSize());
    assertEquals(300000L, actualDigestAuthenticator.getNonceValidity());
    assertEquals(LifecycleState.NEW, actualDigestAuthenticator.getState());
    assertFalse(actualDigestAuthenticator.getAlwaysUseSession());
    assertFalse(actualDigestAuthenticator.getCache());
    assertFalse(actualDigestAuthenticator.getSecurePagesWithPragma());
    assertFalse(actualDigestAuthenticator.isSendAuthInfoResponseHeaders());
    assertTrue(actualDigestAuthenticator.getChangeSessionIdOnAuthentication());
    assertTrue(actualDigestAuthenticator.getDisableProxyCaching());
    assertTrue(actualDigestAuthenticator.isValidateUri());
    assertTrue(actualDigestAuthenticator.getThrowOnFailure());
    assertTrue(actualDigestAuthenticator.isAsyncSupported());
  }

  /**
   * Test new {@link DigestAuthenticator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DigestAuthenticator}
   */
  @Test
  public void testNewDigestAuthenticator2() {
    // Arrange and Act
    DigestAuthenticator actualDigestAuthenticator = new DigestAuthenticator();

    // Assert
    assertEquals("Catalina", actualDigestAuthenticator.getDomain());
    assertEquals("DIGEST", actualDigestAuthenticator.getAuthMethod());
    assertEquals("NEW", actualDigestAuthenticator.getStateName());
    assertEquals("SHA-256,MD5", actualDigestAuthenticator.getAlgorithms());
    assertEquals("SHA1PRNG", actualDigestAuthenticator.getSecureRandomAlgorithm());
    assertEquals("never", actualDigestAuthenticator.getAllowCorsPreflight());
    assertEquals("org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl",
        actualDigestAuthenticator.getJaspicCallbackHandlerClass());
    assertNull(actualDigestAuthenticator.getSecureRandomClass());
    assertNull(actualDigestAuthenticator.getSecureRandomProvider());
    assertNull(actualDigestAuthenticator.getKey());
    assertNull(actualDigestAuthenticator.getOpaque());
    assertNull(actualDigestAuthenticator.getDomainInternal());
    assertNull(actualDigestAuthenticator.nonces);
    assertNull(actualDigestAuthenticator.getObjectName());
    assertNull(actualDigestAuthenticator.getContainer());
    assertNull(actualDigestAuthenticator.getNext());
    assertNull(actualDigestAuthenticator.sso);
    assertNull(actualDigestAuthenticator.sessionIdGenerator);
    assertEquals(0, actualDigestAuthenticator.findLifecycleListeners().length);
    assertEquals(0L, actualDigestAuthenticator.lastTimestamp);
    assertEquals(100, actualDigestAuthenticator.getNonceCountWindowSize());
    assertEquals(1000, actualDigestAuthenticator.getNonceCacheSize());
    assertEquals(300000L, actualDigestAuthenticator.getNonceValidity());
    assertEquals(LifecycleState.NEW, actualDigestAuthenticator.getState());
    assertFalse(actualDigestAuthenticator.getAlwaysUseSession());
    assertFalse(actualDigestAuthenticator.getCache());
    assertFalse(actualDigestAuthenticator.getSecurePagesWithPragma());
    assertFalse(actualDigestAuthenticator.isSendAuthInfoResponseHeaders());
    assertTrue(actualDigestAuthenticator.getChangeSessionIdOnAuthentication());
    assertTrue(actualDigestAuthenticator.getDisableProxyCaching());
    assertTrue(actualDigestAuthenticator.isValidateUri());
    assertTrue(actualDigestAuthenticator.getThrowOnFailure());
    assertTrue(actualDigestAuthenticator.isAsyncSupported());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DigestAuthenticator#setKey(String)}
   *   <li>{@link DigestAuthenticator#setNonceCacheSize(int)}
   *   <li>{@link DigestAuthenticator#setNonceCountWindowSize(int)}
   *   <li>{@link DigestAuthenticator#setNonceValidity(long)}
   *   <li>{@link DigestAuthenticator#setOpaque(String)}
   *   <li>{@link DigestAuthenticator#setValidateUri(boolean)}
   *   <li>{@link DigestAuthenticator#getAuthMethod()}
   *   <li>{@link DigestAuthenticator#getKey()}
   *   <li>{@link DigestAuthenticator#getNonceCacheSize()}
   *   <li>{@link DigestAuthenticator#getNonceCountWindowSize()}
   *   <li>{@link DigestAuthenticator#getNonceValidity()}
   *   <li>{@link DigestAuthenticator#getOpaque()}
   *   <li>{@link DigestAuthenticator#isValidateUri()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    DigestAuthenticator digestAuthenticator = new DigestAuthenticator();

    // Act
    digestAuthenticator.setKey("Key");
    digestAuthenticator.setNonceCacheSize(3);
    digestAuthenticator.setNonceCountWindowSize(3);
    digestAuthenticator.setNonceValidity(1L);
    digestAuthenticator.setOpaque("Opaque");
    digestAuthenticator.setValidateUri(true);
    String actualAuthMethod = digestAuthenticator.getAuthMethod();
    String actualKey = digestAuthenticator.getKey();
    int actualNonceCacheSize = digestAuthenticator.getNonceCacheSize();
    int actualNonceCountWindowSize = digestAuthenticator.getNonceCountWindowSize();
    long actualNonceValidity = digestAuthenticator.getNonceValidity();
    String actualOpaque = digestAuthenticator.getOpaque();

    // Assert
    assertEquals("DIGEST", actualAuthMethod);
    assertEquals("Key", actualKey);
    assertEquals("Opaque", actualOpaque);
    assertEquals(1L, actualNonceValidity);
    assertEquals(3, actualNonceCacheSize);
    assertEquals(3, actualNonceCountWindowSize);
    assertTrue(digestAuthenticator.isValidateUri());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DigestAuthenticator#setKey(String)}
   *   <li>{@link DigestAuthenticator#setNonceCacheSize(int)}
   *   <li>{@link DigestAuthenticator#setNonceCountWindowSize(int)}
   *   <li>{@link DigestAuthenticator#setNonceValidity(long)}
   *   <li>{@link DigestAuthenticator#setOpaque(String)}
   *   <li>{@link DigestAuthenticator#setValidateUri(boolean)}
   *   <li>{@link DigestAuthenticator#getAuthMethod()}
   *   <li>{@link DigestAuthenticator#getKey()}
   *   <li>{@link DigestAuthenticator#getNonceCacheSize()}
   *   <li>{@link DigestAuthenticator#getNonceCountWindowSize()}
   *   <li>{@link DigestAuthenticator#getNonceValidity()}
   *   <li>{@link DigestAuthenticator#getOpaque()}
   *   <li>{@link DigestAuthenticator#isValidateUri()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters2() {
    // Arrange
    DigestAuthenticator digestAuthenticator = new DigestAuthenticator();

    // Act
    digestAuthenticator.setKey("Key");
    digestAuthenticator.setNonceCacheSize(3);
    digestAuthenticator.setNonceCountWindowSize(3);
    digestAuthenticator.setNonceValidity(1L);
    digestAuthenticator.setOpaque("Opaque");
    digestAuthenticator.setValidateUri(true);
    String actualAuthMethod = digestAuthenticator.getAuthMethod();
    String actualKey = digestAuthenticator.getKey();
    int actualNonceCacheSize = digestAuthenticator.getNonceCacheSize();
    int actualNonceCountWindowSize = digestAuthenticator.getNonceCountWindowSize();
    long actualNonceValidity = digestAuthenticator.getNonceValidity();
    String actualOpaque = digestAuthenticator.getOpaque();

    // Assert
    assertEquals("DIGEST", actualAuthMethod);
    assertEquals("Key", actualKey);
    assertEquals("Opaque", actualOpaque);
    assertEquals(1L, actualNonceValidity);
    assertEquals(3, actualNonceCacheSize);
    assertEquals(3, actualNonceCountWindowSize);
    assertTrue(digestAuthenticator.isValidateUri());
  }

  /**
   * Test NonceInfo {@link NonceInfo#getTimestamp()}.
   * <p>
   * Method under test: {@link NonceInfo#getTimestamp()}
   */
  @Test
  public void testNonceInfoGetTimestamp() {
    // Arrange, Act and Assert
    assertEquals(1L, (new NonceInfo(1L, 3)).getTimestamp());
  }

  /**
   * Test NonceInfo {@link NonceInfo#NonceInfo(long, int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return Timestamp is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonceInfo#NonceInfo(long, int)}
   */
  @Test
  public void testNonceInfoNewNonceInfo_whenThree_thenReturnTimestampIsOne() {
    // Arrange, Act and Assert
    assertEquals(1L, (new NonceInfo(1L, 3)).getTimestamp());
  }

  /**
   * Test NonceInfo {@link NonceInfo#nonceCountValid(long)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonceInfo#nonceCountValid(long)}
   */
  @Test
  public void testNonceInfoNonceCountValid_whenMinusOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NonceInfo(1L, 3)).nonceCountValid(-1L));
  }

  /**
   * Test NonceInfo {@link NonceInfo#nonceCountValid(long)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonceInfo#nonceCountValid(long)}
   */
  @Test
  public void testNonceInfoNonceCountValid_whenThree_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NonceInfo(1L, 3)).nonceCountValid(3L));
  }

  /**
   * Test NonceInfo {@link NonceInfo#nonceCountValid(long)}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonceInfo#nonceCountValid(long)}
   */
  @Test
  public void testNonceInfoNonceCountValid_whenTwo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NonceInfo(1L, 3)).nonceCountValid(2L));
  }
}
