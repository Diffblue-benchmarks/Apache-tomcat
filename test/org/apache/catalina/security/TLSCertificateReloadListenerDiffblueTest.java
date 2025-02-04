package org.apache.catalina.security;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TLSCertificateReloadListenerDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TLSCertificateReloadListener#setCheckPeriod(int)}
   *   <li>{@link TLSCertificateReloadListener#setDaysBefore(int)}
   *   <li>{@link TLSCertificateReloadListener#getCheckPeriod()}
   *   <li>{@link TLSCertificateReloadListener#getDaysBefore()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    TLSCertificateReloadListener tlsCertificateReloadListener = new TLSCertificateReloadListener();

    // Act
    tlsCertificateReloadListener.setCheckPeriod(1);
    tlsCertificateReloadListener.setDaysBefore(1);
    int actualCheckPeriod = tlsCertificateReloadListener.getCheckPeriod();

    // Assert
    assertEquals(1, actualCheckPeriod);
    assertEquals(1, tlsCertificateReloadListener.getDaysBefore());
  }

  /**
   * Test new {@link TLSCertificateReloadListener} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link TLSCertificateReloadListener}
   */
  @Test
  public void testNewTLSCertificateReloadListener() {
    // Arrange and Act
    TLSCertificateReloadListener actualTlsCertificateReloadListener = new TLSCertificateReloadListener();

    // Assert
    assertEquals(14, actualTlsCertificateReloadListener.getDaysBefore());
    assertEquals(86400, actualTlsCertificateReloadListener.getCheckPeriod());
  }
}
