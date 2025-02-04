package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

public class DigestCredentialHandlerBaseDiffblueTest {
  /**
   * Test {@link DigestCredentialHandlerBase#getIterations()}.
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#getIterations()}
   */
  @Test
  public void testGetIterations() {
    // Arrange, Act and Assert
    assertEquals(1, (new MessageDigestCredentialHandler()).getIterations());
  }

  /**
   * Test {@link DigestCredentialHandlerBase#getSaltLength()}.
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#getSaltLength()}
   */
  @Test
  public void testGetSaltLength() {
    // Arrange, Act and Assert
    assertEquals(DigestCredentialHandlerBase.DEFAULT_SALT_LENGTH,
        (new MessageDigestCredentialHandler()).getSaltLength());
  }

  /**
   * Test {@link DigestCredentialHandlerBase#setSaltLength(int)}.
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#setSaltLength(int)}
   */
  @Test
  public void testSetSaltLength() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();

    // Act
    messageDigestCredentialHandler.setSaltLength(3);

    // Assert
    assertEquals(3, messageDigestCredentialHandler.getSaltLength());
  }

  /**
   * Test {@link DigestCredentialHandlerBase#getLogInvalidStoredCredentials()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#getLogInvalidStoredCredentials()}
   */
  @Test
  public void testGetLogInvalidStoredCredentials_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new MessageDigestCredentialHandler()).getLogInvalidStoredCredentials());
  }

  /**
   * Test {@link DigestCredentialHandlerBase#getLogInvalidStoredCredentials()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#getLogInvalidStoredCredentials()}
   */
  @Test
  public void testGetLogInvalidStoredCredentials_thenReturnTrue() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();
    messageDigestCredentialHandler.setLogInvalidStoredCredentials(true);

    // Act and Assert
    assertTrue(messageDigestCredentialHandler.getLogInvalidStoredCredentials());
  }

  /**
   * Test {@link DigestCredentialHandlerBase#setLogInvalidStoredCredentials(boolean)}.
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#setLogInvalidStoredCredentials(boolean)}
   */
  @Test
  public void testSetLogInvalidStoredCredentials() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();

    // Act
    messageDigestCredentialHandler.setLogInvalidStoredCredentials(true);

    // Assert
    assertTrue(messageDigestCredentialHandler.getLogInvalidStoredCredentials());
  }

  /**
   * Test {@link DigestCredentialHandlerBase#mutate(String, byte[], int, int)} with {@code inputCredentials}, {@code salt}, {@code iterations}, {@code keyLength}.
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#mutate(String, byte[], int, int)}
   */
  @Test
  public void testMutateWithInputCredentialsSaltIterationsKeyLength() throws UnsupportedEncodingException {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();

    // Act and Assert
    assertEquals("Input Credentials",
        messageDigestCredentialHandler.mutate("Input Credentials", "AXAXAXAX".getBytes("UTF-8"), 1, 3));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#mutate(String)} with {@code userCredential}.
   * <ul>
   *   <li>Then return {@code $0$User Credential}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#mutate(String)}
   */
  @Test
  public void testMutateWithUserCredential_thenReturn0UserCredential() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();
    messageDigestCredentialHandler.setSaltLength(0);
    messageDigestCredentialHandler.mutate(null);
    messageDigestCredentialHandler.setIterations(0);

    // Act and Assert
    assertEquals("$0$User Credential", messageDigestCredentialHandler.mutate("User Credential"));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#mutate(String)} with {@code userCredential}.
   * <ul>
   *   <li>Then return {@code null$1$User Credential}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#mutate(String)}
   */
  @Test
  public void testMutateWithUserCredential_thenReturnNull1UserCredential() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();
    messageDigestCredentialHandler.setSaltLength(-1);
    messageDigestCredentialHandler.mutate(null);
    messageDigestCredentialHandler.setIterations(1);

    // Act and Assert
    assertEquals("null$1$User Credential", messageDigestCredentialHandler.mutate("User Credential"));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#mutate(String)} with {@code userCredential}.
   * <ul>
   *   <li>Then return {@code User Credential}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#mutate(String)}
   */
  @Test
  public void testMutateWithUserCredential_thenReturnUserCredential() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();
    messageDigestCredentialHandler.setSaltLength(0);
    messageDigestCredentialHandler.mutate(null);
    messageDigestCredentialHandler.setIterations(1);

    // Act and Assert
    assertEquals("User Credential", messageDigestCredentialHandler.mutate("User Credential"));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#mutate(String)} with {@code userCredential}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#mutate(String)}
   */
  @Test
  public void testMutateWithUserCredential_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new MessageDigestCredentialHandler()).mutate(null));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#matchesSaltIterationsEncoded(String, String)}.
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#matchesSaltIterationsEncoded(String, String)}
   */
  @Test
  public void testMatchesSaltIterationsEncoded() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();
    messageDigestCredentialHandler.setLogInvalidStoredCredentials(false);

    // Act and Assert
    assertFalse(messageDigestCredentialHandler.matchesSaltIterationsEncoded("Input Credentials", null));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#matchesSaltIterationsEncoded(String, String)}.
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#matchesSaltIterationsEncoded(String, String)}
   */
  @Test
  public void testMatchesSaltIterationsEncoded2() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();
    messageDigestCredentialHandler.setLogInvalidStoredCredentials(true);

    // Act and Assert
    assertFalse(messageDigestCredentialHandler.matchesSaltIterationsEncoded("Input Credentials", "Stored Credentials"));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#matchesSaltIterationsEncoded(String, String)}.
   * <ul>
   *   <li>Given {@link MessageDigestCredentialHandler} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#matchesSaltIterationsEncoded(String, String)}
   */
  @Test
  public void testMatchesSaltIterationsEncoded_givenMessageDigestCredentialHandler() {
    // Arrange, Act and Assert
    assertFalse(
        (new MessageDigestCredentialHandler()).matchesSaltIterationsEncoded("Input Credentials", "Stored Credentials"));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#getDefaultSaltLength()}.
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#getDefaultSaltLength()}
   */
  @Test
  public void testGetDefaultSaltLength() {
    // Arrange, Act and Assert
    assertEquals(DigestCredentialHandlerBase.DEFAULT_SALT_LENGTH,
        (new MessageDigestCredentialHandler()).getDefaultSaltLength());
  }

  /**
   * Test {@link DigestCredentialHandlerBase#equals(byte[], byte[])} with {@code b1}, {@code b2}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#equals(byte[], byte[])}
   */
  @Test
  public void testEqualsWithB1B2_whenA_thenReturnFalse() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(DigestCredentialHandlerBase.equals(new byte[]{4, 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
        "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#equals(byte[], byte[])} with {@code b1}, {@code b2}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#equals(byte[], byte[])}
   */
  @Test
  public void testEqualsWithB1B2_whenAxaxaxaxBytesIsUtf8_thenReturnTrue() throws UnsupportedEncodingException {
    // Arrange
    byte[] b1 = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertTrue(DigestCredentialHandlerBase.equals(b1, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#equals(String, String, boolean)} with {@code s1}, {@code s2}, {@code ignoreCase}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#equals(String, String, boolean)}
   */
  @Test
  public void testEqualsWithS1S2IgnoreCase_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DigestCredentialHandlerBase.equals("S1", "", true));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#equals(String, String, boolean)} with {@code s1}, {@code s2}, {@code ignoreCase}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#equals(String, String, boolean)}
   */
  @Test
  public void testEqualsWithS1S2IgnoreCase_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DigestCredentialHandlerBase.equals(null, "S2", false));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#equals(String, String, boolean)} with {@code s1}, {@code s2}, {@code ignoreCase}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#equals(String, String, boolean)}
   */
  @Test
  public void testEqualsWithS1S2IgnoreCase_whenNull_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(DigestCredentialHandlerBase.equals("S1", null, false));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#equals(String, String, boolean)} with {@code s1}, {@code s2}, {@code ignoreCase}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#equals(String, String, boolean)}
   */
  @Test
  public void testEqualsWithS1S2IgnoreCase_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DigestCredentialHandlerBase.equals(null, null, false));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#equals(String, String, boolean)} with {@code s1}, {@code s2}, {@code ignoreCase}.
   * <ul>
   *   <li>When {@code S2}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#equals(String, String, boolean)}
   */
  @Test
  public void testEqualsWithS1S2IgnoreCase_whenS2_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DigestCredentialHandlerBase.equals("S1", "S2", false));
  }

  /**
   * Test {@link DigestCredentialHandlerBase#equals(String, String, boolean)} with {@code s1}, {@code s2}, {@code ignoreCase}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DigestCredentialHandlerBase#equals(String, String, boolean)}
   */
  @Test
  public void testEqualsWithS1S2IgnoreCase_whenTrue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DigestCredentialHandlerBase.equals("S1", "S2", true));
  }
}
