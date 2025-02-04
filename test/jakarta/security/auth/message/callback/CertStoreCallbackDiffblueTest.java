package jakarta.security.auth.message.callback;

import static org.junit.Assert.assertNull;
import java.security.cert.CertStore;
import org.junit.Test;

public class CertStoreCallbackDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link CertStoreCallback}
   *   <li>{@link CertStoreCallback#setCertStore(CertStore)}
   *   <li>{@link CertStoreCallback#getCertStore()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    CertStoreCallback actualCertStoreCallback = new CertStoreCallback();
    actualCertStoreCallback.setCertStore(null);

    // Assert
    assertNull(actualCertStoreCallback.getCertStore());
  }
}
