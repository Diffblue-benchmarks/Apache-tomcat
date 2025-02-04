package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

public class MessageDigestCredentialHandlerDiffblueTest {
  /**
   * Test {@link MessageDigestCredentialHandler#getEncoding()}.
   * <p>
   * Method under test: {@link MessageDigestCredentialHandler#getEncoding()}
   */
  @Test
  public void testGetEncoding() {
    // Arrange, Act and Assert
    assertEquals("UTF-8", (new MessageDigestCredentialHandler()).getEncoding());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MessageDigestCredentialHandler#getAlgorithm()}
   *   <li>{@link MessageDigestCredentialHandler#getDefaultIterations()}
   *   <li>{@link MessageDigestCredentialHandler#getLog()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();

    // Act
    String actualAlgorithm = messageDigestCredentialHandler.getAlgorithm();
    int actualDefaultIterations = messageDigestCredentialHandler.getDefaultIterations();
    messageDigestCredentialHandler.getLog();

    // Assert
    assertNull(actualAlgorithm);
    assertEquals(1, actualDefaultIterations);
  }

  /**
   * Test {@link MessageDigestCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDigestCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new MessageDigestCredentialHandler()).matches("Input Credentials", ""));
  }

  /**
   * Test {@link MessageDigestCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDigestCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new MessageDigestCredentialHandler()).matches("", ""));
  }

  /**
   * Test {@link MessageDigestCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDigestCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new MessageDigestCredentialHandler()).matches(null, "Stored Credentials"));
  }

  /**
   * Test {@link MessageDigestCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDigestCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_whenNull_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse((new MessageDigestCredentialHandler()).matches("Input Credentials", null));
  }

  /**
   * Test {@link MessageDigestCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>When {@code Stored Credentials}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDigestCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_whenStoredCredentials_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new MessageDigestCredentialHandler()).matches("Input Credentials", "Stored Credentials"));
  }

  /**
   * Test {@link MessageDigestCredentialHandler#mutate(String, byte[], int)} with {@code inputCredentials}, {@code salt}, {@code iterations}.
   * <p>
   * Method under test: {@link MessageDigestCredentialHandler#mutate(String, byte[], int)}
   */
  @Test
  public void testMutateWithInputCredentialsSaltIterations() throws UnsupportedEncodingException {
    // Arrange
    MessageDigestCredentialHandler messageDigestCredentialHandler = new MessageDigestCredentialHandler();

    // Act and Assert
    assertEquals("Input Credentials",
        messageDigestCredentialHandler.mutate("Input Credentials", "AXAXAXAX".getBytes("UTF-8"), 1));
  }

  /**
   * Test new {@link MessageDigestCredentialHandler} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link MessageDigestCredentialHandler}
   */
  @Test
  public void testNewMessageDigestCredentialHandler() {
    // Arrange and Act
    MessageDigestCredentialHandler actualMessageDigestCredentialHandler = new MessageDigestCredentialHandler();

    // Assert
    assertEquals("UTF-8", actualMessageDigestCredentialHandler.getEncoding());
    assertNull(actualMessageDigestCredentialHandler.getAlgorithm());
    assertEquals(1, actualMessageDigestCredentialHandler.getIterations());
    assertEquals(1, actualMessageDigestCredentialHandler.getDefaultIterations());
    assertFalse(actualMessageDigestCredentialHandler.getLogInvalidStoredCredentials());
    assertEquals(DigestCredentialHandlerBase.DEFAULT_SALT_LENGTH,
        actualMessageDigestCredentialHandler.getDefaultSaltLength());
    assertEquals(DigestCredentialHandlerBase.DEFAULT_SALT_LENGTH, actualMessageDigestCredentialHandler.getSaltLength());
  }
}
