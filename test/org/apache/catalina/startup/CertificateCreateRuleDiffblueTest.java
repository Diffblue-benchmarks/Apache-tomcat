package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.nio.file.Paths;
import org.apache.jasper.util.UniqueAttributesImpl;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SSLHostConfigCertificate;
import org.apache.tomcat.util.net.SSLHostConfigCertificate.Type;
import org.junit.Test;
import org.xml.sax.Attributes;

public class CertificateCreateRuleDiffblueTest {
  /**
   * Test {@link CertificateCreateRule#begin(String, String, Attributes)}.
   * <ul>
   *   <li>Given {@link CertificateCreateRule} (default constructor) Digester is createDigester {@code digester.emptyStack}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CertificateCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin_givenCertificateCreateRuleDigesterIsCreateDigesterDigesterEmptyStack() throws Exception {
    // Arrange
    CertificateCreateRule certificateCreateRule = new CertificateCreateRule();
    certificateCreateRule.setDigester(HostConfig.createDigester("digester.emptyStack"));

    // Act
    certificateCreateRule.begin("Namespace", "Name", new UniqueAttributesImpl(true));

    // Assert
    Digester digester = certificateCreateRule.getDigester();
    Object root = digester.getRoot();
    assertTrue(root instanceof SSLHostConfigCertificate);
    assertEquals("JKS", ((SSLHostConfigCertificate) root).getCertificateKeystoreType());
    assertNull(((SSLHostConfigCertificate) root).getCertificateChainFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyAlias());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyPassword());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyPasswordFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeystorePassword());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeystorePasswordFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeystoreProvider());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeystore());
    assertNull(((SSLHostConfigCertificate) root).getObjectName());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyManager());
    assertNull(((SSLHostConfigCertificate) root).getSslContext());
    assertNull(((SSLHostConfigCertificate) root).getSslContextGenerated());
    assertNull(((SSLHostConfigCertificate) root).getSSLHostConfig());
    assertEquals(1, digester.getCount());
    assertEquals(Type.UNDEFINED, ((SSLHostConfigCertificate) root).getType());
    String expectedCertificateKeystoreFile = Paths.get(System.getProperty("user.home"), ".keystore").toString();
    assertEquals(expectedCertificateKeystoreFile, ((SSLHostConfigCertificate) root).getCertificateKeystoreFile());
  }

  /**
   * Test {@link CertificateCreateRule#begin(String, String, Attributes)}.
   * <ul>
   *   <li>Then {@link CertificateCreateRule} (default constructor) Digester Root {@link SSLHostConfig}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CertificateCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin_thenCertificateCreateRuleDigesterRootSSLHostConfig() throws Exception {
    // Arrange
    Digester digester = new Digester();
    SSLHostConfig sslHostConfig = new SSLHostConfig();
    digester.push(sslHostConfig);

    CertificateCreateRule certificateCreateRule = new CertificateCreateRule();
    certificateCreateRule.setDigester(digester);

    // Act
    certificateCreateRule.begin("Namespace", "Name", new UniqueAttributesImpl(true));

    // Assert
    Digester digester2 = certificateCreateRule.getDigester();
    Object root = digester2.getRoot();
    assertTrue(root instanceof SSLHostConfig);
    assertEquals(2, digester2.getCount());
    assertSame(sslHostConfig, root);
  }

  /**
   * Test {@link CertificateCreateRule#begin(String, String, Attributes)}.
   * <ul>
   *   <li>Then {@link CertificateCreateRule} (default constructor) Digester Root {@link SSLHostConfigCertificate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CertificateCreateRule#begin(String, String, Attributes)}
   */
  @Test
  public void testBegin_thenCertificateCreateRuleDigesterRootSSLHostConfigCertificate() throws Exception {
    // Arrange
    CertificateCreateRule certificateCreateRule = new CertificateCreateRule();
    certificateCreateRule.setDigester(new Digester());

    // Act
    certificateCreateRule.begin("Namespace", "Name", new UniqueAttributesImpl(true));

    // Assert
    Digester digester = certificateCreateRule.getDigester();
    Object root = digester.getRoot();
    assertTrue(root instanceof SSLHostConfigCertificate);
    assertEquals("JKS", ((SSLHostConfigCertificate) root).getCertificateKeystoreType());
    assertNull(((SSLHostConfigCertificate) root).getCertificateChainFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyAlias());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyPassword());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyPasswordFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeystorePassword());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeystorePasswordFile());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeystoreProvider());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeystore());
    assertNull(((SSLHostConfigCertificate) root).getObjectName());
    assertNull(((SSLHostConfigCertificate) root).getCertificateKeyManager());
    assertNull(((SSLHostConfigCertificate) root).getSslContext());
    assertNull(((SSLHostConfigCertificate) root).getSslContextGenerated());
    assertNull(((SSLHostConfigCertificate) root).getSSLHostConfig());
    assertEquals(1, digester.getCount());
    assertEquals(Type.UNDEFINED, ((SSLHostConfigCertificate) root).getType());
    String expectedCertificateKeystoreFile = Paths.get(System.getProperty("user.home"), ".keystore").toString();
    assertEquals(expectedCertificateKeystoreFile, ((SSLHostConfigCertificate) root).getCertificateKeystoreFile());
  }

  /**
   * Test {@link CertificateCreateRule#end(String, String)}.
   * <ul>
   *   <li>Given {@link Digester} (default constructor) push {@code Object}.</li>
   *   <li>Then {@link CertificateCreateRule} (default constructor) Digester Count is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CertificateCreateRule#end(String, String)}
   */
  @Test
  public void testEnd_givenDigesterPushObject_thenCertificateCreateRuleDigesterCountIsZero() throws Exception {
    // Arrange
    Digester digester = new Digester();
    digester.push("Object");

    CertificateCreateRule certificateCreateRule = new CertificateCreateRule();
    certificateCreateRule.setDigester(digester);

    // Act
    certificateCreateRule.end("Namespace", "Name");

    // Assert
    assertEquals(0, certificateCreateRule.getDigester().getCount());
  }

  /**
   * Test {@link CertificateCreateRule#end(String, String)}.
   * <ul>
   *   <li>Then {@link CertificateCreateRule} (default constructor) Digester Count is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CertificateCreateRule#end(String, String)}
   */
  @Test
  public void testEnd_thenCertificateCreateRuleDigesterCountIsZero() throws Exception {
    // Arrange
    CertificateCreateRule certificateCreateRule = new CertificateCreateRule();
    certificateCreateRule.setDigester(new Digester());

    // Act
    certificateCreateRule.end("Namespace", "Name");

    // Assert that nothing has changed
    assertEquals(0, certificateCreateRule.getDigester().getCount());
  }

  /**
   * Test new {@link CertificateCreateRule} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CertificateCreateRule}
   */
  @Test
  public void testNewCertificateCreateRule() {
    // Arrange and Act
    CertificateCreateRule actualCertificateCreateRule = new CertificateCreateRule();

    // Assert
    assertNull(actualCertificateCreateRule.getNamespaceURI());
    assertNull(actualCertificateCreateRule.getDigester());
  }
}
