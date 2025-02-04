package jakarta.security.auth.message.callback;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.security.auth.message.callback.PrivateKeyCallback.AliasRequest;
import jakarta.security.auth.message.callback.PrivateKeyCallback.DigestRequest;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.security.auth.message.callback.PrivateKeyCallback.SubjectKeyIDRequest;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import org.junit.Test;

public class PrivateKeyCallbackDiffblueTest {
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
   * Test DigestRequest getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DigestRequest#DigestRequest(byte[], String)}
   *   <li>{@link DigestRequest#getAlgorithm()}
   *   <li>{@link DigestRequest#getDigest()}
   * </ul>
   */
  @Test
  public void testDigestRequestGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    byte[] digest = "AXAXAXAX".getBytes("UTF-8");

    // Act
    DigestRequest actualDigestRequest = new DigestRequest(digest, "Algorithm");
    String actualAlgorithm = actualDigestRequest.getAlgorithm();
    byte[] actualDigest = actualDigestRequest.getDigest();

    // Assert
    assertEquals("Algorithm", actualAlgorithm);
    assertSame(digest, actualDigest);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualDigest);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PrivateKeyCallback#PrivateKeyCallback(Request)}
   *   <li>{@link PrivateKeyCallback#getChain()}
   *   <li>{@link PrivateKeyCallback#getKey()}
   *   <li>{@link PrivateKeyCallback#getRequest()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    SubjectKeyIDRequest request = new SubjectKeyIDRequest("AXAXAXAX".getBytes("UTF-8"));

    // Act
    PrivateKeyCallback actualPrivateKeyCallback = new PrivateKeyCallback(request);
    Certificate[] actualChain = actualPrivateKeyCallback.getChain();
    PrivateKey actualKey = actualPrivateKeyCallback.getKey();
    Request actualRequest = actualPrivateKeyCallback.getRequest();

    // Assert
    assertTrue(actualRequest instanceof SubjectKeyIDRequest);
    assertNull(actualChain);
    assertNull(actualKey);
    assertSame(request, actualRequest);
  }

  /**
   * Test {@link PrivateKeyCallback#setKey(PrivateKey, Certificate[])}.
   * <p>
   * Method under test: {@link PrivateKeyCallback#setKey(PrivateKey, Certificate[])}
   */
  @Test
  public void testSetKey() throws UnsupportedEncodingException {
    // Arrange
    PrivateKeyCallback privateKeyCallback = new PrivateKeyCallback(
        new SubjectKeyIDRequest("AXAXAXAX".getBytes("UTF-8")));
    Certificate[] chain = new Certificate[]{null};

    // Act
    privateKeyCallback.setKey(null, chain);

    // Assert
    assertSame(chain, privateKeyCallback.getChain());
  }

  /**
   * Test SubjectKeyIDRequest getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SubjectKeyIDRequest#SubjectKeyIDRequest(byte[])}
   *   <li>{@link SubjectKeyIDRequest#getSubjectKeyID()}
   * </ul>
   */
  @Test
  public void testSubjectKeyIDRequestGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    byte[] subjectKeyID = "AXAXAXAX".getBytes("UTF-8");

    // Act
    byte[] actualSubjectKeyID = (new SubjectKeyIDRequest(subjectKeyID)).getSubjectKeyID();

    // Assert
    assertSame(subjectKeyID, actualSubjectKeyID);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualSubjectKeyID);
  }
}
