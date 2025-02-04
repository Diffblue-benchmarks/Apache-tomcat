package org.apache.catalina.realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;

public class SecretKeyCredentialHandlerDiffblueTest {
  /**
   * Test {@link SecretKeyCredentialHandler#SecretKeyCredentialHandler()}.
   * <p>
   * Method under test: default or parameterless constructor of {@link SecretKeyCredentialHandler}
   */
  @Test
  public void testNewSecretKeyCredentialHandler() throws NoSuchAlgorithmException {
    // Arrange and Act
    SecretKeyCredentialHandler actualSecretKeyCredentialHandler = new SecretKeyCredentialHandler();

    // Assert
    assertFalse(actualSecretKeyCredentialHandler.getLogInvalidStoredCredentials());
    assertEquals(DigestCredentialHandlerBase.DEFAULT_SALT_LENGTH,
        actualSecretKeyCredentialHandler.getDefaultSaltLength());
    assertEquals(DigestCredentialHandlerBase.DEFAULT_SALT_LENGTH, actualSecretKeyCredentialHandler.getSaltLength());
    assertEquals(SecretKeyCredentialHandler.DEFAULT_ALGORITHM, actualSecretKeyCredentialHandler.getAlgorithm());
    assertEquals(SecretKeyCredentialHandler.DEFAULT_ITERATIONS, actualSecretKeyCredentialHandler.getIterations());
    assertEquals(SecretKeyCredentialHandler.DEFAULT_ITERATIONS,
        actualSecretKeyCredentialHandler.getDefaultIterations());
    assertEquals(SecretKeyCredentialHandler.DEFAULT_KEY_LENGTH, actualSecretKeyCredentialHandler.getKeyLength());
  }

  /**
   * Test {@link SecretKeyCredentialHandler#getAlgorithm()}.
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#getAlgorithm()}
   */
  @Test
  public void testGetAlgorithm() throws NoSuchAlgorithmException {
    // Arrange, Act and Assert
    assertEquals(SecretKeyCredentialHandler.DEFAULT_ALGORITHM, (new SecretKeyCredentialHandler()).getAlgorithm());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SecretKeyCredentialHandler#setKeyLength(int)}
   *   <li>{@link SecretKeyCredentialHandler#getKeyLength()}
   *   <li>{@link SecretKeyCredentialHandler#getLog()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws NoSuchAlgorithmException {
    // Arrange
    SecretKeyCredentialHandler secretKeyCredentialHandler = new SecretKeyCredentialHandler();

    // Act
    secretKeyCredentialHandler.setKeyLength(3);
    int actualKeyLength = secretKeyCredentialHandler.getKeyLength();
    secretKeyCredentialHandler.getLog();

    // Assert
    assertEquals(3, actualKeyLength);
  }

  /**
   * Test {@link SecretKeyCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>Given {@link SecretKeyCredentialHandler#SecretKeyCredentialHandler()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_givenSecretKeyCredentialHandler() throws NoSuchAlgorithmException {
    // Arrange, Act and Assert
    assertFalse((new SecretKeyCredentialHandler()).matches("Input Credentials", "Stored Credentials"));
  }

  /**
   * Test {@link SecretKeyCredentialHandler#matches(String, String)}.
   * <ul>
   *   <li>Given {@link SecretKeyCredentialHandler#SecretKeyCredentialHandler()} LogInvalidStoredCredentials is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#matches(String, String)}
   */
  @Test
  public void testMatches_givenSecretKeyCredentialHandlerLogInvalidStoredCredentialsIsTrue()
      throws NoSuchAlgorithmException {
    // Arrange
    SecretKeyCredentialHandler secretKeyCredentialHandler = new SecretKeyCredentialHandler();
    secretKeyCredentialHandler.setLogInvalidStoredCredentials(true);

    // Act and Assert
    assertFalse(secretKeyCredentialHandler.matches("Input Credentials", "Stored Credentials"));
  }

  /**
   * Test {@link SecretKeyCredentialHandler#mutate(String, byte[], int)} with {@code inputCredentials}, {@code salt}, {@code iterations}.
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#mutate(String, byte[], int)}
   */
  @Test
  public void testMutateWithInputCredentialsSaltIterations()
      throws UnsupportedEncodingException, NoSuchAlgorithmException {
    // Arrange
    SecretKeyCredentialHandler secretKeyCredentialHandler = new SecretKeyCredentialHandler();

    // Act and Assert
    assertEquals("5e46aff0b10a3e9915874165cc3acc78bd277052",
        secretKeyCredentialHandler.mutate("Input Credentials", "AXAXAXAX".getBytes("UTF-8"), 1));
  }

  /**
   * Test {@link SecretKeyCredentialHandler#mutate(String, byte[], int, int)} with {@code inputCredentials}, {@code salt}, {@code iterations}, {@code keyLength}.
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#mutate(String, byte[], int, int)}
   */
  @Test
  public void testMutateWithInputCredentialsSaltIterationsKeyLength()
      throws UnsupportedEncodingException, NoSuchAlgorithmException {
    // Arrange
    SecretKeyCredentialHandler secretKeyCredentialHandler = new SecretKeyCredentialHandler();

    // Act and Assert
    assertEquals("5e46aff0b10a3e9915874165cc3acc78bd277052", secretKeyCredentialHandler.mutate("Input Credentials",
        "AXAXAXAX".getBytes("UTF-8"), 1, SecretKeyCredentialHandler.DEFAULT_KEY_LENGTH));
  }

  /**
   * Test {@link SecretKeyCredentialHandler#mutate(String, byte[], int, int)} with {@code inputCredentials}, {@code salt}, {@code iterations}, {@code keyLength}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#mutate(String, byte[], int, int)}
   */
  @Test
  public void testMutateWithInputCredentialsSaltIterationsKeyLength_thenReturnEmptyString()
      throws UnsupportedEncodingException, NoSuchAlgorithmException {
    // Arrange
    SecretKeyCredentialHandler secretKeyCredentialHandler = new SecretKeyCredentialHandler();

    // Act and Assert
    assertEquals("", secretKeyCredentialHandler.mutate("Input Credentials", "AXAXAXAX".getBytes("UTF-8"), 1, 3));
  }

  /**
   * Test {@link SecretKeyCredentialHandler#mutate(String, byte[], int, int)} with {@code inputCredentials}, {@code salt}, {@code iterations}, {@code keyLength}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#mutate(String, byte[], int, int)}
   */
  @Test
  public void testMutateWithInputCredentialsSaltIterationsKeyLength_thenReturnNull() throws NoSuchAlgorithmException {
    // Arrange, Act and Assert
    assertNull((new SecretKeyCredentialHandler()).mutate("Input Credentials", new byte[]{}, 1, 3));
  }

  /**
   * Test {@link SecretKeyCredentialHandler#mutate(String, byte[], int)} with {@code inputCredentials}, {@code salt}, {@code iterations}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#mutate(String, byte[], int)}
   */
  @Test
  public void testMutateWithInputCredentialsSaltIterations_whenEmptyArrayOfByte_thenReturnNull()
      throws NoSuchAlgorithmException {
    // Arrange, Act and Assert
    assertNull((new SecretKeyCredentialHandler()).mutate("Input Credentials", new byte[]{}, 1));
  }

  /**
   * Test {@link SecretKeyCredentialHandler#getDefaultIterations()}.
   * <p>
   * Method under test: {@link SecretKeyCredentialHandler#getDefaultIterations()}
   */
  @Test
  public void testGetDefaultIterations() throws NoSuchAlgorithmException {
    // Arrange, Act and Assert
    assertEquals(SecretKeyCredentialHandler.DEFAULT_ITERATIONS,
        (new SecretKeyCredentialHandler()).getDefaultIterations());
  }
}
