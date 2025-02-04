package jakarta.security.auth.message.callback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.security.auth.message.callback.SecretKeyCallback.AliasRequest;
import jakarta.security.auth.message.callback.SecretKeyCallback.Request;
import java.io.UnsupportedEncodingException;
import javax.crypto.SecretKey;
import javax.security.auth.kerberos.EncryptionKey;
import org.junit.Test;

public class SecretKeyCallbackDiffblueTest {
  /**
   * Test AliasRequest getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AliasRequest#AliasRequest(String)}
   *   <li>{@link AliasRequest#getAlias()}
   * </ul>
   */
  @Test
  public void testAliasRequestGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Alias", (new AliasRequest("Alias")).getAlias());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SecretKeyCallback#SecretKeyCallback(Request)}
   *   <li>{@link SecretKeyCallback#setKey(SecretKey)}
   *   <li>{@link SecretKeyCallback#getKey()}
   *   <li>{@link SecretKeyCallback#getRequest()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    AliasRequest request = new AliasRequest("Alias");

    // Act
    SecretKeyCallback actualSecretKeyCallback = new SecretKeyCallback(request);
    EncryptionKey key = new EncryptionKey("AXAXAXAX".getBytes("UTF-8"), 1);

    actualSecretKeyCallback.setKey(key);
    SecretKey actualKey = actualSecretKeyCallback.getKey();
    Request actualRequest = actualSecretKeyCallback.getRequest();

    // Assert
    assertTrue(actualRequest instanceof AliasRequest);
    assertSame(request, actualRequest);
    assertSame(key, actualKey);
  }
}
