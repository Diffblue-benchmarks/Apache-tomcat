package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.HostConfig.DeployedApplication;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.RulesBase;
import org.junit.Test;

public class HostConfigDiffblueTest {
  /**
   * Test DeployedApplication {@link DeployedApplication#DeployedApplication(String, boolean)}.
   * <p>
   * Method under test: {@link DeployedApplication#DeployedApplication(String, boolean)}
   */
  @Test
  public void testDeployedApplicationNewDeployedApplication() {
    // Arrange and Act
    DeployedApplication actualDeployedApplication = new DeployedApplication("Name", true);

    // Assert
    assertEquals("Name", actualDeployedApplication.name);
    assertFalse(actualDeployedApplication.loggedDirWarning);
    assertTrue(actualDeployedApplication.redeployResources.isEmpty());
    assertTrue(actualDeployedApplication.reloadResources.isEmpty());
    assertTrue(actualDeployedApplication.hasDescriptor);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link HostConfig#setCopyXML(boolean)}
   *   <li>{@link HostConfig#setDeployXML(boolean)}
   *   <li>{@link HostConfig#setUnpackWARs(boolean)}
   *   <li>{@link HostConfig#getContextClass()}
   *   <li>{@link HostConfig#isCopyXML()}
   *   <li>{@link HostConfig#isDeployXML()}
   *   <li>{@link HostConfig#isUnpackWARs()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    HostConfig hostConfig = new HostConfig();

    // Act
    hostConfig.setCopyXML(true);
    hostConfig.setDeployXML(true);
    hostConfig.setUnpackWARs(true);
    String actualContextClass = hostConfig.getContextClass();
    boolean actualIsCopyXMLResult = hostConfig.isCopyXML();
    boolean actualIsDeployXMLResult = hostConfig.isDeployXML();

    // Assert
    assertEquals("org.apache.catalina.core.StandardContext", actualContextClass);
    assertTrue(actualIsCopyXMLResult);
    assertTrue(actualIsDeployXMLResult);
    assertTrue(hostConfig.isUnpackWARs());
  }

  /**
   * Test {@link HostConfig#setContextClass(String)}.
   * <p>
   * Method under test: {@link HostConfig#setContextClass(String)}
   */
  @Test
  public void testSetContextClass() {
    // Arrange
    HostConfig hostConfig = new HostConfig();

    // Act
    hostConfig.setContextClass("org.apache.catalina.core.StandardContext");

    // Assert that nothing has changed
    assertEquals("org.apache.catalina.core.StandardContext", hostConfig.getContextClass());
  }

  /**
   * Test {@link HostConfig#setContextClass(String)}.
   * <ul>
   *   <li>When {@code Context Class}.</li>
   *   <li>Then {@link HostConfig} (default constructor) ContextClass is {@code Context Class}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostConfig#setContextClass(String)}
   */
  @Test
  public void testSetContextClass_whenContextClass_thenHostConfigContextClassIsContextClass() {
    // Arrange
    HostConfig hostConfig = new HostConfig();

    // Act
    hostConfig.setContextClass("Context Class");

    // Assert
    assertEquals("Context Class", hostConfig.getContextClass());
  }

  /**
   * Test {@link HostConfig#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link HostConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent() {
    // Arrange
    HostConfig hostConfig = new HostConfig();

    // Act
    hostConfig.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Assert that nothing has changed
    assertNull(hostConfig.host);
  }

  /**
   * Test {@link HostConfig#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link HostConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent2() {
    // Arrange
    HostConfig hostConfig = new HostConfig();

    // Act
    hostConfig.lifecycleEvent(new LifecycleEvent(new StandardHost(), "stop", "Data"));

    // Assert
    assertTrue(hostConfig.host instanceof StandardHost);
  }

  /**
   * Test {@link HostConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>When {@link LifecycleEvent#LifecycleEvent(Lifecycle, String, Object)} with lifecycle is {@link Connector#Connector()} and {@code Type} and {@code Data}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_whenLifecycleEventWithLifecycleIsConnectorAndTypeAndData() {
    // Arrange
    HostConfig hostConfig = new HostConfig();

    // Act
    hostConfig.lifecycleEvent(new LifecycleEvent(new Connector(), "Type", "Data"));

    // Assert that nothing has changed
    assertNull(hostConfig.host);
  }

  /**
   * Test {@link HostConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>When {@link LifecycleEvent#LifecycleEvent(Lifecycle, String, Object)} with lifecycle is {@link FailedContext} (default constructor) and {@code Type} and {@code Data}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_whenLifecycleEventWithLifecycleIsFailedContextAndTypeAndData() {
    // Arrange
    HostConfig hostConfig = new HostConfig();

    // Act
    hostConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "Type", "Data"));

    // Assert that nothing has changed
    assertNull(hostConfig.host);
  }

  /**
   * Test {@link HostConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>When {@link LifecycleEvent#LifecycleEvent(Lifecycle, String, Object)} with lifecycle is {@link StandardHost} (default constructor) and {@code Type} and {@code Data}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_whenLifecycleEventWithLifecycleIsStandardHostAndTypeAndData() {
    // Arrange
    HostConfig hostConfig = new HostConfig();

    // Act
    hostConfig.lifecycleEvent(new LifecycleEvent(new StandardHost(), "Type", "Data"));

    // Assert
    assertTrue(hostConfig.host instanceof StandardHost);
  }

  /**
   * Test {@link HostConfig#tryAddServiced(String)}.
   * <p>
   * Method under test: {@link HostConfig#tryAddServiced(String)}
   */
  @Test
  public void testTryAddServiced() {
    // Arrange, Act and Assert
    assertTrue((new HostConfig()).tryAddServiced("Name"));
  }

  /**
   * Test {@link HostConfig#isDeployed(String)}.
   * <p>
   * Method under test: {@link HostConfig#isDeployed(String)}
   */
  @Test
  public void testIsDeployed() {
    // Arrange, Act and Assert
    assertFalse((new HostConfig()).isDeployed("Name"));
  }

  /**
   * Test {@link HostConfig#createDigester(String)}.
   * <p>
   * Method under test: {@link HostConfig#createDigester(String)}
   */
  @Test
  public void testCreateDigester() {
    // Arrange and Act
    Digester actualCreateDigesterResult = HostConfig.createDigester("Context Class Name");

    // Assert
    assertTrue(actualCreateDigesterResult.getRules() instanceof RulesBase);
    assertEquals("", actualCreateDigesterResult.getCurrentElementName());
    assertEquals("", actualCreateDigesterResult.getMatch());
    assertNull(actualCreateDigesterResult.getRoot());
    assertNull(actualCreateDigesterResult.getPublicId());
    assertNull(actualCreateDigesterResult.getGeneratedCode());
    assertNull(actualCreateDigesterResult.getFakeAttributes());
    assertNull(actualCreateDigesterResult.getEntityResolver());
    assertNull(actualCreateDigesterResult.getErrorHandler());
    assertNull(actualCreateDigesterResult.getDocumentLocator());
    assertEquals(0, actualCreateDigesterResult.getCount());
    assertFalse(actualCreateDigesterResult.getNamespaceAware());
    assertFalse(actualCreateDigesterResult.getRulesValidation());
    assertFalse(actualCreateDigesterResult.getUseContextClassLoader());
    assertFalse(actualCreateDigesterResult.getValidating());
  }

  /**
   * Test {@link HostConfig#getConfigBaseName()}.
   * <p>
   * Method under test: {@link HostConfig#getConfigBaseName()}
   */
  @Test
  public void testGetConfigBaseName() {
    // Arrange
    HostConfig hostConfig = new HostConfig();
    hostConfig.lifecycleEvent(new LifecycleEvent(new StandardHost(), "Type", "Data"));

    // Act
    String actualConfigBaseName = hostConfig.getConfigBaseName();

    // Assert
    assertEquals(Paths.get(System.getProperty("user.dir"), "conf", "null").toString(), actualConfigBaseName);
  }

  /**
   * Test {@link HostConfig#addGlobalRedeployResources(DeployedApplication)}.
   * <p>
   * Method under test: {@link HostConfig#addGlobalRedeployResources(DeployedApplication)}
   */
  @Test
  public void testAddGlobalRedeployResources() {
    // Arrange
    HostConfig hostConfig = new HostConfig();
    hostConfig.lifecycleEvent(new LifecycleEvent(new StandardHost(), "Type", "Data"));
    DeployedApplication app = new DeployedApplication("Name", true);

    // Act
    hostConfig.addGlobalRedeployResources(app);

    // Assert
    LinkedHashMap<String, Long> stringResultLongMap = app.redeployResources;
    assertEquals(1, stringResultLongMap.size());
    assertEquals(1738173548505L,
        stringResultLongMap.get(Paths.get(System.getProperty("user.dir"), "conf", "context.xml").toString())
            .longValue());
  }

  /**
   * Test new {@link HostConfig} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link HostConfig}
   */
  @Test
  public void testNewHostConfig() {
    // Arrange and Act
    HostConfig actualHostConfig = new HostConfig();

    // Assert
    assertEquals("org.apache.catalina.core.StandardContext", actualHostConfig.getContextClass());
    assertNull(actualHostConfig.oname);
    assertNull(actualHostConfig.host);
    assertFalse(actualHostConfig.isCopyXML());
    assertFalse(actualHostConfig.isDeployXML());
    assertFalse(actualHostConfig.isUnpackWARs());
    assertTrue(actualHostConfig.deployed.isEmpty());
    assertTrue(actualHostConfig.invalidWars.isEmpty());
  }
}
