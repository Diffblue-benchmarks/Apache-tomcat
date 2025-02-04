package org.apache.catalina.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TLSUtilDiffblueTest {
  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("org.apache.tomcat.util.net.secure_requested_protocol_versions"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.cipher_suite}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenJakartaServletRequestCipherSuite_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("jakarta.servlet.request.cipher_suite"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.key_size}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenJakartaServletRequestKeySize_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("jakarta.servlet.request.key_size"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.secure_protocol}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenJakartaServletRequestSecureProtocol_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("jakarta.servlet.request.secure_protocol"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.ssl_session_id}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenJakartaServletRequestSslSessionId_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("jakarta.servlet.request.ssl_session_id"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.ssl_session_mgr}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenJakartaServletRequestSslSessionMgr_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("jakarta.servlet.request.ssl_session_mgr"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code jakarta.servlet.request.X509Certificate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenJakartaServletRequestX509Certificate() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("jakarta.servlet.request.X509Certificate"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(TLSUtil.isTLSRequestAttribute("Name"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code org.apache.tomcat.util.net.secure_protocol_version}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenOrgApacheTomcatUtilNetSecureProtocolVersion() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("org.apache.tomcat.util.net.secure_protocol_version"));
  }

  /**
   * Test {@link TLSUtil#isTLSRequestAttribute(String)}.
   * <ul>
   *   <li>When {@code org.apache.tomcat.util.net.secure_requested_ciphers}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TLSUtil#isTLSRequestAttribute(String)}
   */
  @Test
  public void testIsTLSRequestAttribute_whenOrgApacheTomcatUtilNetSecureRequestedCiphers() {
    // Arrange, Act and Assert
    assertTrue(TLSUtil.isTLSRequestAttribute("org.apache.tomcat.util.net.secure_requested_ciphers"));
  }
}
