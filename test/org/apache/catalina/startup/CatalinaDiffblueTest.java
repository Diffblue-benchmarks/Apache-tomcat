package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.nio.file.Paths;
import java.util.Hashtable;
import javax.management.ObjectName;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Server;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.startup.Catalina.CatalinaShutdownHook;
import org.apache.catalina.startup.Catalina.SetParentClassLoaderRule;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.apache.naming.NamingContext;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.RulesBase;
import org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor;
import org.junit.Test;

public class CatalinaDiffblueTest {
  /**
   * Test new {@link Catalina} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link Catalina}
   */
  @Test
  public void testNewCatalina() {
    // Arrange and Act
    Catalina actualCatalina = new Catalina();

    // Assert
    assertEquals("catalinaembedded", actualCatalina.getGeneratedCodePackage());
    assertNull(actualCatalina.getGeneratedCodeLocation());
    assertNull(actualCatalina.generatedCodeLocationParameter);
    assertNull(actualCatalina.shutdownHook);
    assertNull(actualCatalina.getServer());
    assertFalse(actualCatalina.getGenerateCode());
    assertFalse(actualCatalina.getThrowOnInitFailure());
    assertFalse(actualCatalina.getUseGeneratedCode());
    assertFalse(actualCatalina.isAwait());
    assertFalse(actualCatalina.loaded);
    assertTrue(actualCatalina.getUseShutdownHook());
    assertTrue(actualCatalina.isUseNaming());
    assertEquals(Catalina.SERVER_XML, actualCatalina.getConfigFile());
    ClassLoader expectedParentClassLoader = actualCatalina.parentClassLoader;
    assertSame(expectedParentClassLoader, actualCatalina.getParentClassLoader());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Catalina#setAwait(boolean)}
   *   <li>{@link Catalina#setConfigFile(String)}
   *   <li>{@link Catalina#setGenerateCode(boolean)}
   *   <li>{@link Catalina#setGeneratedCodeLocation(File)}
   *   <li>{@link Catalina#setGeneratedCodePackage(String)}
   *   <li>{@link Catalina#setParentClassLoader(ClassLoader)}
   *   <li>{@link Catalina#setServer(Server)}
   *   <li>{@link Catalina#setThrowOnInitFailure(boolean)}
   *   <li>{@link Catalina#setUseGeneratedCode(boolean)}
   *   <li>{@link Catalina#setUseNaming(boolean)}
   *   <li>{@link Catalina#setUseShutdownHook(boolean)}
   *   <li>{@link Catalina#getConfigFile()}
   *   <li>{@link Catalina#getGenerateCode()}
   *   <li>{@link Catalina#getGeneratedCodeLocation()}
   *   <li>{@link Catalina#getGeneratedCodePackage()}
   *   <li>{@link Catalina#getServer()}
   *   <li>{@link Catalina#getThrowOnInitFailure()}
   *   <li>{@link Catalina#getUseGeneratedCode()}
   *   <li>{@link Catalina#getUseShutdownHook()}
   *   <li>{@link Catalina#isAwait()}
   *   <li>{@link Catalina#isUseNaming()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.setAwait(true);
    catalina.setConfigFile("File");
    catalina.setGenerateCode(true);
    File generatedCodeLocation = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    catalina.setGeneratedCodeLocation(generatedCodeLocation);
    catalina.setGeneratedCodePackage("java.text");
    catalina.setParentClassLoader(new ParallelWebappClassLoader());
    StandardServer server = new StandardServer();
    catalina.setServer(server);
    catalina.setThrowOnInitFailure(true);
    catalina.setUseGeneratedCode(true);
    catalina.setUseNaming(true);
    catalina.setUseShutdownHook(true);
    String actualConfigFile = catalina.getConfigFile();
    boolean actualGenerateCode = catalina.getGenerateCode();
    File actualGeneratedCodeLocation = catalina.getGeneratedCodeLocation();
    String actualGeneratedCodePackage = catalina.getGeneratedCodePackage();
    Server actualServer = catalina.getServer();
    boolean actualThrowOnInitFailure = catalina.getThrowOnInitFailure();
    boolean actualUseGeneratedCode = catalina.getUseGeneratedCode();
    boolean actualUseShutdownHook = catalina.getUseShutdownHook();
    boolean actualIsAwaitResult = catalina.isAwait();

    // Assert
    assertEquals("File", actualConfigFile);
    assertEquals("java.text", actualGeneratedCodePackage);
    assertTrue(actualGenerateCode);
    assertTrue(actualThrowOnInitFailure);
    assertTrue(actualUseGeneratedCode);
    assertTrue(actualUseShutdownHook);
    assertTrue(actualIsAwaitResult);
    assertTrue(catalina.isUseNaming());
    assertSame(server, actualServer);
    assertSame(generatedCodeLocation, actualGeneratedCodeLocation);
  }

  /**
   * Test {@link Catalina#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor) ParentClassLoader is {@code null}.</li>
   *   <li>Then return not {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenCatalinaParentClassLoaderIsNull_thenReturnNotNull() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setParentClassLoader(null);

    // Act and Assert
    assertNotNull(catalina.getParentClassLoader());
  }

  /**
   * Test {@link Catalina#getParentClassLoader()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>Then return {@link Catalina} (default constructor) {@link Catalina#parentClassLoader}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#getParentClassLoader()}
   */
  @Test
  public void testGetParentClassLoader_givenCatalina_thenReturnCatalinaParentClassLoader() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act and Assert
    assertSame(catalina.parentClassLoader, catalina.getParentClassLoader());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) configFile Name is {@code Args}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_thenCatalinaConfigFileNameIsArgs() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"-config", "Args"});

    // Assert
    assertEquals("Args", catalina.configFile().getName());
    assertEquals("Args", catalina.getConfigFile());
    assertFalse(catalina.getGenerateCode());
    assertTrue(actualArgumentsResult);
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) {@link Catalina#generatedCodeLocationParameter} is {@code Args}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_thenCatalinaGeneratedCodeLocationParameterIsArgs() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"-generateCode", "Args"});

    // Assert
    assertEquals("Args", catalina.generatedCodeLocationParameter);
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(actualArgumentsResult);
    assertTrue(catalina.getGenerateCode());
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code Args}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithArgs_thenReturnFalse() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act and Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.arguments(new String[]{"Args"}));
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code -config}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithConfig_thenReturnTrue() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"-config"});

    // Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(actualArgumentsResult);
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code configtest}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithConfigtest_thenReturnTrue() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"configtest"});

    // Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(actualArgumentsResult);
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code -generateCode}.</li>
   *   <li>Then {@link Catalina} (default constructor) GenerateCode.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithGenerateCode_thenCatalinaGenerateCode() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"-generateCode"});

    // Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(actualArgumentsResult);
    assertTrue(catalina.getGenerateCode());
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code -help}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithHelp_thenReturnFalse() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act and Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.arguments(new String[]{"-help"}));
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code -nonaming}.</li>
   *   <li>Then not {@link Catalina} (default constructor) UseNaming.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithNonaming_thenNotCatalinaUseNaming() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"-nonaming"});

    // Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.getUseGeneratedCode());
    assertFalse(catalina.isUseNaming());
    assertTrue(actualArgumentsResult);
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code start}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithStart_thenReturnTrue() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"start"});

    // Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(actualArgumentsResult);
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code stop}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithStop_thenReturnTrue() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"stop"});

    // Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(actualArgumentsResult);
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code -useGeneratedCode}.</li>
   *   <li>Then {@link Catalina} (default constructor) UseGeneratedCode.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenArrayOfStringWithUseGeneratedCode_thenCatalinaUseGeneratedCode() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    boolean actualArgumentsResult = catalina.arguments(new String[]{"-useGeneratedCode"});

    // Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.getGenerateCode());
    assertTrue(actualArgumentsResult);
    assertTrue(catalina.getUseGeneratedCode());
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#arguments(String[])}.
   * <ul>
   *   <li>When empty array of {@link String}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#arguments(String[])}
   */
  @Test
  public void testArguments_whenEmptyArrayOfString_thenReturnFalse() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act and Assert
    assertNull(catalina.generatedCodeLocationParameter);
    assertFalse(catalina.arguments(new String[]{}));
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.isUseNaming());
  }

  /**
   * Test {@link Catalina#configFile()}.
   * <p>
   * Method under test: {@link Catalina#configFile()}
   */
  @Test
  public void testConfigFile() {
    // Arrange and Act
    File actualConfigFileResult = (new Catalina()).configFile();

    // Assert
    assertEquals("server.xml", actualConfigFileResult.getName());
    assertTrue(actualConfigFileResult.isAbsolute());
  }

  /**
   * Test {@link Catalina#createStopDigester()}.
   * <p>
   * Method under test: {@link Catalina#createStopDigester()}
   */
  @Test
  public void testCreateStopDigester() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    Digester actualCreateStopDigesterResult = catalina.createStopDigester();

    // Assert
    assertTrue(actualCreateStopDigesterResult.getRules() instanceof RulesBase);
    assertEquals("", actualCreateStopDigesterResult.getCurrentElementName());
    assertEquals("", actualCreateStopDigesterResult.getMatch());
    assertNull(actualCreateStopDigesterResult.getRoot());
    assertNull(actualCreateStopDigesterResult.getPublicId());
    assertNull(actualCreateStopDigesterResult.getGeneratedCode());
    assertNull(actualCreateStopDigesterResult.getFakeAttributes());
    assertNull(actualCreateStopDigesterResult.getEntityResolver());
    assertNull(actualCreateStopDigesterResult.getErrorHandler());
    assertNull(actualCreateStopDigesterResult.getDocumentLocator());
    assertEquals(0, actualCreateStopDigesterResult.getCount());
    assertFalse(actualCreateStopDigesterResult.getNamespaceAware());
    assertFalse(actualCreateStopDigesterResult.getRulesValidation());
    assertFalse(actualCreateStopDigesterResult.getValidating());
    assertTrue(actualCreateStopDigesterResult.getUseContextClassLoader());
    ClassLoader expectedClassLoader = catalina.parentClassLoader;
    assertSame(expectedClassLoader, actualCreateStopDigesterResult.getClassLoader());
  }

  /**
   * Test {@link Catalina#parseServerXml(boolean)}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor) UseGeneratedCode is {@code true}.</li>
   *   <li>When {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#parseServerXml(boolean)}
   */
  @Test
  public void testParseServerXml_givenCatalinaUseGeneratedCodeIsTrue_whenTrue() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setUseGeneratedCode(true);

    // Act
    catalina.parseServerXml(true);

    // Assert
    assertFalse(catalina.getUseGeneratedCode());
  }

  /**
   * Test {@link Catalina#parseServerXml(boolean)}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#parseServerXml(boolean)}
   */
  @Test
  public void testParseServerXml_givenCatalina_whenFalse() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.parseServerXml(false);

    // Assert that nothing has changed
    assertFalse(catalina.getUseGeneratedCode());
  }

  /**
   * Test {@link Catalina#parseServerXml(boolean)}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>When {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#parseServerXml(boolean)}
   */
  @Test
  public void testParseServerXml_givenCatalina_whenTrue() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.parseServerXml(true);

    // Assert that nothing has changed
    assertFalse(catalina.getUseGeneratedCode());
  }

  /**
   * Test {@link Catalina#parseServerXml(boolean)}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) GeneratedCodeLocation Name is {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#parseServerXml(boolean)}
   */
  @Test
  public void testParseServerXml_thenCatalinaGeneratedCodeLocationNameIsTestTxt() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setGeneratedCodeLocation(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    catalina.setGenerateCode(true);

    // Act
    catalina.parseServerXml(false);

    // Assert that nothing has changed
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("test.txt", generatedCodeLocation.getName());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(generatedCodeLocation.isAbsolute());
  }

  /**
   * Test {@link Catalina#parseServerXml(boolean)}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) GeneratedCodeLocation Name is {@code work}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#parseServerXml(boolean)}
   */
  @Test
  public void testParseServerXml_thenCatalinaGeneratedCodeLocationNameIsWork() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setGenerateCode(true);

    // Act
    catalina.parseServerXml(true);

    // Assert
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("work", generatedCodeLocation.getName());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(generatedCodeLocation.isAbsolute());
  }

  /**
   * Test {@link Catalina#parseServerXml(boolean)}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) GeneratedCodeLocation Name is {@code work}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#parseServerXml(boolean)}
   */
  @Test
  public void testParseServerXml_thenCatalinaGeneratedCodeLocationNameIsWork2() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setGenerateCode(true);

    // Act
    catalina.parseServerXml(false);

    // Assert
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("work", generatedCodeLocation.getName());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(generatedCodeLocation.isAbsolute());
  }

  /**
   * Test SetParentClassLoaderRule {@link SetParentClassLoaderRule#SetParentClassLoaderRule(Catalina, ClassLoader)}.
   * <p>
   * Method under test: {@link SetParentClassLoaderRule#SetParentClassLoaderRule(Catalina, ClassLoader)}
   */
  @Test
  public void testSetParentClassLoaderRuleNewSetParentClassLoaderRule() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    SetParentClassLoaderRule actualSetParentClassLoaderRule = catalina.new SetParentClassLoaderRule(
        new ParallelWebappClassLoader());

    // Assert
    assertNull(actualSetParentClassLoaderRule.getNamespaceURI());
    assertNull(actualSetParentClassLoaderRule.getDigester());
  }

  /**
   * Test {@link Catalina#stopServer(String[])} with {@code String[]}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) Server {@link StandardServer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#stopServer(String[])}
   */
  @Test
  public void testStopServerWithString_thenCatalinaServerStandardServer() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setServer(new StandardServer());

    // Act
    catalina.stopServer(new String[]{"Arguments"});

    // Assert
    Server server = catalina.getServer();
    assertTrue(server instanceof StandardServer);
    assertEquals("DESTROYED", server.getStateName());
    NamingResourcesImpl globalNamingResources = server.getGlobalNamingResources();
    assertEquals("DESTROYED", globalNamingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, server.getState());
    assertEquals(LifecycleState.DESTROYED, globalNamingResources.getState());
  }

  /**
   * Test {@link Catalina#stopServer()}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) Server {@link StandardServer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#stopServer()}
   */
  @Test
  public void testStopServer_thenCatalinaServerStandardServer() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setServer(new StandardServer());

    // Act
    catalina.stopServer();

    // Assert
    Server server = catalina.getServer();
    assertTrue(server instanceof StandardServer);
    assertEquals("DESTROYED", server.getStateName());
    NamingResourcesImpl globalNamingResources = server.getGlobalNamingResources();
    assertEquals("DESTROYED", globalNamingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, server.getState());
    assertEquals(LifecycleState.DESTROYED, globalNamingResources.getState());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setGeneratedCodeLocation(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Act
    catalina.load(new String[]{"-generateCode"});

    // Assert
    assertEquals("server.xml", catalina.configFile().getName());
    assertTrue(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor) GeneratedCodePackage is {@code java.text}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_givenCatalinaGeneratedCodePackageIsJavaText() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setGeneratedCodePackage("java.text");

    // Act
    catalina.load(new String[]{"-generateCode"});

    // Assert
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("work", generatedCodeLocation.getName());
    assertTrue(generatedCodeLocation.isAbsolute());
    assertTrue(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>When array of {@link String} with {@code Args}.</li>
   *   <li>Then not {@link Catalina} (default constructor) {@link Catalina#loaded}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_givenCatalina_whenArrayOfStringWithArgs_thenNotCatalinaLoaded() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"Args"});

    // Assert that nothing has changed
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>When array of {@link String} with {@code -help}.</li>
   *   <li>Then not {@link Catalina} (default constructor) {@link Catalina#loaded}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_givenCatalina_whenArrayOfStringWithHelp_thenNotCatalinaLoaded() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-help"});

    // Assert that nothing has changed
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>When array of {@link String} with {@code null}.</li>
   *   <li>Then not {@link Catalina} (default constructor) {@link Catalina#loaded}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_givenCatalina_whenArrayOfStringWithNull_thenNotCatalinaLoaded() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{null});

    // Assert that nothing has changed
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>When empty array of {@link String}.</li>
   *   <li>Then not {@link Catalina} (default constructor) {@link Catalina#loaded}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_givenCatalina_whenEmptyArrayOfString_thenNotCatalinaLoaded() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{});

    // Assert that nothing has changed
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertFalse(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) ConfigFile is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_thenCatalinaConfigFileIsNull() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-config", null});

    // Assert
    assertNull(catalina.getConfigFile());
    assertNull(catalina.generatedCodeLocationParameter);
    assertTrue(catalina.isUseNaming());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) configFile Name is {@code Args}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_thenCatalinaConfigFileNameIsArgs() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-config", "Args"});

    // Assert
    assertEquals("Args", catalina.configFile().getName());
    assertEquals("Args", catalina.getConfigFile());
    assertFalse(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) configFile Name is {@code -config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_thenCatalinaConfigFileNameIsConfig() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-config", "-config"});

    // Assert
    assertEquals("-config", catalina.configFile().getName());
    assertEquals("-config", catalina.getConfigFile());
    assertFalse(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) GeneratedCodeLocation Name is {@code Args}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_thenCatalinaGeneratedCodeLocationNameIsArgs() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-generateCode", "Args"});

    // Assert
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("Args", generatedCodeLocation.getName());
    assertEquals("Args", catalina.generatedCodeLocationParameter);
    assertTrue(generatedCodeLocation.isAbsolute());
    assertTrue(catalina.getGenerateCode());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) GeneratedCodeLocation Name is {@code work}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_thenCatalinaGeneratedCodeLocationNameIsWork() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-generateCode"});

    // Assert
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("work", generatedCodeLocation.getName());
    assertTrue(generatedCodeLocation.isAbsolute());
    assertTrue(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) Server {@link StandardServer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_thenCatalinaServerStandardServer() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setServer(new StandardServer());

    // Act
    catalina.load(new String[]{"-config"});

    // Assert
    Server server = catalina.getServer();
    assertTrue(server instanceof StandardServer);
    assertEquals("INITIALIZED", server.getStateName());
    assertEquals(LifecycleState.INITIALIZED, server.getState());
    assertSame(catalina, server.getCatalina());
    ClassLoader expectedParentClassLoader = catalina.parentClassLoader;
    assertSame(expectedParentClassLoader, server.getParentClassLoader());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>When array of {@link String} with {@code -config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_whenArrayOfStringWithConfig() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-config"});

    // Assert
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>When array of {@link String} with {@code configtest}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_whenArrayOfStringWithConfigtest() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"configtest"});

    // Assert
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>When array of {@link String} with {@code -nonaming}.</li>
   *   <li>Then not {@link Catalina} (default constructor) UseNaming.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_whenArrayOfStringWithNonaming_thenNotCatalinaUseNaming() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-nonaming"});

    // Assert
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.isUseNaming());
    assertTrue(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>When array of {@link String} with {@code start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_whenArrayOfStringWithStart() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"start"});

    // Assert
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>When array of {@link String} with {@code stop}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_whenArrayOfStringWithStop() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"stop"});

    // Assert
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load(String[])} with {@code String[]}.
   * <ul>
   *   <li>When array of {@link String} with {@code -useGeneratedCode}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load(String[])}
   */
  @Test
  public void testLoadWithString_whenArrayOfStringWithUseGeneratedCode() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load(new String[]{"-useGeneratedCode"});

    // Assert
    assertEquals("server.xml", catalina.configFile().getName());
    assertFalse(catalina.getGenerateCode());
    assertTrue(catalina.loaded);
    assertEquals(Catalina.SERVER_XML, catalina.getConfigFile());
  }

  /**
   * Test {@link Catalina#load()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor) ConfigFile is {@code catalina.useNaming}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load()}
   */
  @Test
  public void testLoad_givenCatalinaConfigFileIsCatalinaUseNaming() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setConfigFile("catalina.useNaming");

    // Act
    catalina.load();

    // Assert
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor) Server is {@link StandardServer} (default constructor).</li>
   *   <li>Then {@link Catalina} (default constructor) Server {@link StandardServer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load()}
   */
  @Test
  public void testLoad_givenCatalinaServerIsStandardServer_thenCatalinaServerStandardServer() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setServer(new StandardServer());

    // Act
    catalina.load();

    // Assert
    Server server = catalina.getServer();
    assertTrue(server instanceof StandardServer);
    assertEquals("INITIALIZED", server.getStateName());
    assertEquals(LifecycleState.INITIALIZED, server.getState());
    assertSame(catalina, server.getCatalina());
    ClassLoader expectedParentClassLoader = catalina.parentClassLoader;
    assertSame(expectedParentClassLoader, server.getParentClassLoader());
  }

  /**
   * Test {@link Catalina#load()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor) UseGeneratedCode is {@code true}.</li>
   *   <li>Then not {@link Catalina} (default constructor) UseGeneratedCode.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load()}
   */
  @Test
  public void testLoad_givenCatalinaUseGeneratedCodeIsTrue_thenNotCatalinaUseGeneratedCode() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setUseGeneratedCode(true);

    // Act
    catalina.load();

    // Assert
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>Then not {@link Catalina} (default constructor) UseGeneratedCode.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load()}
   */
  @Test
  public void testLoad_givenCatalina_thenNotCatalinaUseGeneratedCode() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.load();

    // Assert
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load()}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) GeneratedCodeLocation Name is {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load()}
   */
  @Test
  public void testLoad_thenCatalinaGeneratedCodeLocationNameIsTestTxt() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setGeneratedCodeLocation(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    catalina.setGenerateCode(true);

    // Act
    catalina.load();

    // Assert
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("test.txt", generatedCodeLocation.getName());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(generatedCodeLocation.isAbsolute());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#load()}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) GeneratedCodeLocation Name is {@code work}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#load()}
   */
  @Test
  public void testLoad_thenCatalinaGeneratedCodeLocationNameIsWork() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setGenerateCode(true);

    // Act
    catalina.load();

    // Assert
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("work", generatedCodeLocation.getName());
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(generatedCodeLocation.isAbsolute());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#start()}.
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart() {
    // Arrange
    StandardService service = new StandardService();
    service.addConnector(new Connector(new AjpNio2Protocol()));

    StandardServer server = new StandardServer();
    server.addService(service);

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.start();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    ObjectName[] serviceNames = ((StandardServer) server2).getServiceNames();
    ObjectName objectName = serviceNames[0];
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Service", objectName.getCanonicalName());
    assertEquals("type=Service", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Service", objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals(1, serviceNames.length);
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor) ConfigFile is {@code catalina.useNaming}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_givenCatalinaConfigFileIsCatalinaUseNaming() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setConfigFile("catalina.useNaming");

    // Act
    catalina.start();

    // Assert
    assertNull(catalina.shutdownHook);
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor) UseGeneratedCode is {@code true}.</li>
   *   <li>Then not {@link Catalina} (default constructor) UseGeneratedCode.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_givenCatalinaUseGeneratedCodeIsTrue_thenNotCatalinaUseGeneratedCode() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setUseGeneratedCode(true);

    // Act
    catalina.start();

    // Assert
    assertNull(catalina.shutdownHook);
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Given {@link Catalina} (default constructor).</li>
   *   <li>Then not {@link Catalina} (default constructor) UseGeneratedCode.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_givenCatalina_thenNotCatalinaUseGeneratedCode() {
    // Arrange
    Catalina catalina = new Catalina();

    // Act
    catalina.start();

    // Assert
    assertNull(catalina.shutdownHook);
    assertFalse(catalina.getUseGeneratedCode());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) addConnector {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_givenStandardServiceAddConnectorConnector() {
    // Arrange
    StandardService service = new StandardService();
    service.addConnector(new Connector());

    StandardServer server = new StandardServer();
    server.addService(service);

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.start();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    ObjectName[] serviceNames = ((StandardServer) server2).getServiceNames();
    ObjectName objectName = serviceNames[0];
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Service", objectName.getCanonicalName());
    assertEquals("type=Service", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Service", objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals(1, serviceNames.length);
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) addLifecycleListener {@link AprLifecycleListener} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_givenStandardServiceAddLifecycleListenerAprLifecycleListener() {
    // Arrange
    StandardService service = new StandardService();
    service.addLifecycleListener(new AprLifecycleListener());
    service.addConnector(new Connector());

    StandardServer server = new StandardServer();
    server.addService(service);

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.start();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    ObjectName[] serviceNames = ((StandardServer) server2).getServiceNames();
    ObjectName objectName = serviceNames[0];
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Service", objectName.getCanonicalName());
    assertEquals("type=Service", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Service", objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals(1, serviceNames.length);
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) Container is {@link StandardEngine} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_givenStandardServiceContainerIsStandardEngine() {
    // Arrange
    StandardService service = new StandardService();
    service.setContainer(new StandardEngine());
    service.addConnector(new Connector());

    StandardServer server = new StandardServer();
    server.addService(service);

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.start();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    ObjectName[] serviceNames = ((StandardServer) server2).getServiceNames();
    ObjectName objectName = serviceNames[0];
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Service", objectName.getCanonicalName());
    assertEquals("type=Service", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Service", objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals(1, serviceNames.length);
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) GeneratedCodeLocation Name is {@code work}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_thenCatalinaGeneratedCodeLocationNameIsWork() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setGenerateCode(true);

    // Act
    catalina.start();

    // Assert
    File generatedCodeLocation = catalina.getGeneratedCodeLocation();
    assertEquals("work", generatedCodeLocation.getName());
    assertNull(catalina.shutdownHook);
    assertTrue(generatedCodeLocation.isAbsolute());
    assertTrue(catalina.loaded);
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) {@link Catalina#shutdownHook} {@link CatalinaShutdownHook}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_thenCatalinaShutdownHookCatalinaShutdownHook() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setServer(new StandardServer());

    // Act
    catalina.start();

    // Assert
    Server server = catalina.getServer();
    assertTrue(server instanceof StandardServer);
    assertTrue(catalina.shutdownHook instanceof CatalinaShutdownHook);
    assertTrue(server.getGlobalNamingContext() instanceof NamingContext);
    assertTrue(server.getUtilityExecutor() instanceof ScheduledThreadPoolExecutor);
    assertEquals("STARTED", server.getStateName());
    assertEquals(LifecycleState.STARTED, server.getState());
    assertFalse(catalina.loaded);
  }

  /**
   * Test {@link Catalina#start()}.
   * <ul>
   *   <li>Then first element Domain is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#start()}
   */
  @Test
  public void testStart_thenFirstElementDomainIsCatalina() {
    // Arrange
    StandardServer server = new StandardServer();
    server.addService(new StandardService());

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.start();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    ObjectName[] serviceNames = ((StandardServer) server2).getServiceNames();
    ObjectName objectName = serviceNames[0];
    assertEquals("Catalina", objectName.getDomain());
    assertEquals("Catalina:type=Service", objectName.getCanonicalName());
    assertEquals("type=Service", objectName.getCanonicalKeyPropertyListString());
    assertEquals("type=Service", objectName.getKeyPropertyListString());
    Hashtable<String, String> keyPropertyList = objectName.getKeyPropertyList();
    assertEquals(1, keyPropertyList.size());
    assertEquals(1, serviceNames.length);
    assertFalse(objectName.isDomainPattern());
    assertFalse(objectName.isPattern());
    assertFalse(objectName.isPropertyListPattern());
    assertFalse(objectName.isPropertyPattern());
    assertFalse(objectName.isPropertyValuePattern());
    assertTrue(keyPropertyList.containsKey("type"));
  }

  /**
   * Test {@link Catalina#stop()}.
   * <ul>
   *   <li>Given {@link NamingResourcesImpl} (default constructor) addResource {@link ContextResource} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#stop()}
   */
  @Test
  public void testStop_givenNamingResourcesImplAddResourceContextResource() {
    // Arrange
    NamingResourcesImpl globalNamingResources = new NamingResourcesImpl();
    globalNamingResources.addResource(new ContextResource());

    StandardServer server = new StandardServer();
    server.setGlobalNamingResources(globalNamingResources);

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.stop();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    assertEquals("DESTROYED", server2.getStateName());
    NamingResourcesImpl globalNamingResources2 = server2.getGlobalNamingResources();
    assertEquals("DESTROYED", globalNamingResources2.getStateName());
    assertEquals(LifecycleState.DESTROYED, server2.getState());
    assertEquals(LifecycleState.DESTROYED, globalNamingResources2.getState());
  }

  /**
   * Test {@link Catalina#stop()}.
   * <ul>
   *   <li>Given {@link StandardServer} (default constructor) addLifecycleListener {@link ContextConfig} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#stop()}
   */
  @Test
  public void testStop_givenStandardServerAddLifecycleListenerContextConfig() {
    // Arrange
    StandardServer server = new StandardServer();
    server.addLifecycleListener(new ContextConfig());

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.stop();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    assertEquals("DESTROYED", server2.getStateName());
    NamingResourcesImpl globalNamingResources = server2.getGlobalNamingResources();
    assertEquals("DESTROYED", globalNamingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, server2.getState());
    assertEquals(LifecycleState.DESTROYED, globalNamingResources.getState());
  }

  /**
   * Test {@link Catalina#stop()}.
   * <ul>
   *   <li>Given {@link StandardService} (default constructor) addConnector {@link Connector#Connector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#stop()}
   */
  @Test
  public void testStop_givenStandardServiceAddConnectorConnector() {
    // Arrange
    StandardService service = new StandardService();
    service.addConnector(new Connector());

    StandardServer server = new StandardServer();
    server.addService(service);

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.stop();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    assertEquals("DESTROYED", server2.getStateName());
    NamingResourcesImpl globalNamingResources = server2.getGlobalNamingResources();
    assertEquals("DESTROYED", globalNamingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, server2.getState());
    assertEquals(LifecycleState.DESTROYED, globalNamingResources.getState());
  }

  /**
   * Test {@link Catalina#stop()}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) Server StateName is {@code DESTROYED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#stop()}
   */
  @Test
  public void testStop_thenCatalinaServerStateNameIsDestroyed() {
    // Arrange
    Catalina catalina = new Catalina();
    catalina.setServer(new StandardServer());

    // Act
    catalina.stop();

    // Assert
    Server server = catalina.getServer();
    assertTrue(server instanceof StandardServer);
    assertEquals("DESTROYED", server.getStateName());
    NamingResourcesImpl globalNamingResources = server.getGlobalNamingResources();
    assertEquals("DESTROYED", globalNamingResources.getStateName());
    assertEquals(LifecycleState.DESTROYED, server.getState());
    assertEquals(LifecycleState.DESTROYED, globalNamingResources.getState());
  }

  /**
   * Test {@link Catalina#stop()}.
   * <ul>
   *   <li>Then {@link Catalina} (default constructor) Server StateName is {@code FAILED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Catalina#stop()}
   */
  @Test
  public void testStop_thenCatalinaServerStateNameIsFailed() {
    // Arrange
    StandardServer server = new StandardServer();
    server.addLifecycleListener(null);

    Catalina catalina = new Catalina();
    catalina.setServer(server);

    // Act
    catalina.stop();

    // Assert
    Server server2 = catalina.getServer();
    assertTrue(server2 instanceof StandardServer);
    assertEquals("FAILED", server2.getStateName());
    NamingResourcesImpl globalNamingResources = server2.getGlobalNamingResources();
    assertEquals("NEW", globalNamingResources.getStateName());
    assertEquals(LifecycleState.FAILED, server2.getState());
    assertEquals(LifecycleState.NEW, globalNamingResources.getState());
  }
}
