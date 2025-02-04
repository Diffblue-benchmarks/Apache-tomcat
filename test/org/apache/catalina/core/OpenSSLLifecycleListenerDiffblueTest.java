package org.apache.catalina.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class OpenSSLLifecycleListenerDiffblueTest {
  /**
   * Test {@link OpenSSLLifecycleListener#isAvailable()}.
   * <p>
   * Method under test: {@link OpenSSLLifecycleListener#isAvailable()}
   */
  @Test
  public void testIsAvailable() {
    // Arrange, Act and Assert
    assertFalse(OpenSSLLifecycleListener.isAvailable());
  }

  /**
   * Test new {@link OpenSSLLifecycleListener} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link OpenSSLLifecycleListener}
   */
  @Test
  public void testNewOpenSSLLifecycleListener() {
    // Arrange and Act
    OpenSSLLifecycleListener actualOpenSSLLifecycleListener = new OpenSSLLifecycleListener();

    // Assert
    assertNull(actualOpenSSLLifecycleListener.getFIPSMode());
    assertNull(actualOpenSSLLifecycleListener.getSSLEngine());
    assertNull(actualOpenSSLLifecycleListener.getSSLRandomSeed());
  }

  /**
   * Test {@link OpenSSLLifecycleListener#getSSLEngine()}.
   * <p>
   * Method under test: {@link OpenSSLLifecycleListener#getSSLEngine()}
   */
  @Test
  public void testGetSSLEngine() {
    // Arrange, Act and Assert
    assertNull((new OpenSSLLifecycleListener()).getSSLEngine());
  }

  /**
   * Test {@link OpenSSLLifecycleListener#getSSLRandomSeed()}.
   * <p>
   * Method under test: {@link OpenSSLLifecycleListener#getSSLRandomSeed()}
   */
  @Test
  public void testGetSSLRandomSeed() {
    // Arrange, Act and Assert
    assertNull((new OpenSSLLifecycleListener()).getSSLRandomSeed());
  }

  /**
   * Test {@link OpenSSLLifecycleListener#getFIPSMode()}.
   * <p>
   * Method under test: {@link OpenSSLLifecycleListener#getFIPSMode()}
   */
  @Test
  public void testGetFIPSMode() {
    // Arrange, Act and Assert
    assertNull((new OpenSSLLifecycleListener()).getFIPSMode());
  }

  /**
   * Test {@link OpenSSLLifecycleListener#isFIPSModeActive()}.
   * <p>
   * Method under test: {@link OpenSSLLifecycleListener#isFIPSModeActive()}
   */
  @Test
  public void testIsFIPSModeActive() {
    // Arrange, Act and Assert
    assertFalse((new OpenSSLLifecycleListener()).isFIPSModeActive());
  }

  /**
   * Test {@link OpenSSLLifecycleListener#getUseOpenSSL()}.
   * <p>
   * Method under test: {@link OpenSSLLifecycleListener#getUseOpenSSL()}
   */
  @Test
  public void testGetUseOpenSSL() {
    // Arrange, Act and Assert
    assertTrue(OpenSSLLifecycleListener.getUseOpenSSL());
  }
}
