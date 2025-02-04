package org.apache.catalina.startup;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.authenticator.NonLoginAuthenticator;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.TestStandardContextResources;
import org.apache.catalina.core.TestStandardContextResources.AbsoluteOrderContextConfig;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.catalina.startup.TestHostConfigAutomaticDeploymentA.TesterContext;
import org.apache.tomcat.util.bcel.classfile.ElementValue;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.descriptor.web.WebXml;
import org.junit.Test;
import org.xml.sax.InputSource;

public class ContextConfigDiffblueTest {
  /**
   * Test {@link ContextConfig#getDefaultWebXml()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor) DefaultWebXml is {@code foo}.</li>
   *   <li>Then return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getDefaultWebXml()}
   */
  @Test
  public void testGetDefaultWebXml_givenContextConfigDefaultWebXmlIsFoo_thenReturnFoo() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.setDefaultWebXml("foo");

    // Act and Assert
    assertEquals("foo", contextConfig.getDefaultWebXml());
  }

  /**
   * Test {@link ContextConfig#getDefaultWebXml()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor).</li>
   *   <li>Then return {@link Constants#DefaultWebXml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getDefaultWebXml()}
   */
  @Test
  public void testGetDefaultWebXml_givenContextConfig_thenReturnDefaultWebXml() {
    // Arrange, Act and Assert
    assertEquals(Constants.DefaultWebXml, (new ContextConfig()).getDefaultWebXml());
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "after_start", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof FailedContext);
    assertNull(contextConfig.getContextWebXmlSource());
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent2() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "after_destroy", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof FailedContext);
    assertNull(contextConfig.getContextWebXmlSource());
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent3() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(new TesterContext(), "after_destroy", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@code before_start}.</li>
   *   <li>When {@link TesterContext} (default constructor) addSecurityRole {@code before_start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenBeforeStart_whenTesterContextAddSecurityRoleBeforeStart() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    TesterContext lifecycle = new TesterContext();
    lifecycle.addSecurityRole("before_start");

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(lifecycle, "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@code before_start}.</li>
   *   <li>When {@link TesterContext} (default constructor) addWelcomeFile {@code before_start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenBeforeStart_whenTesterContextAddWelcomeFileBeforeStart() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    TesterContext lifecycle = new TesterContext();
    lifecycle.addWelcomeFile("before_start");

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(lifecycle, "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@code configure_stop}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenConfigureStop() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    TesterContext lifecycle = new TesterContext();
    lifecycle.addParameter("before_start", "configure_stop");

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(lifecycle, "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link FilterDef} (default constructor).</li>
   *   <li>When {@link TesterContext} (default constructor) addFilterDef {@link FilterDef} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenFilterDef_whenTesterContextAddFilterDefFilterDef() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    TesterContext lifecycle = new TesterContext();
    lifecycle.addFilterDef(new FilterDef());

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(lifecycle, "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Given {@link SecurityConstraint} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_givenSecurityConstraint() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    TesterContext lifecycle = new TesterContext();
    lifecycle.addConstraint(new SecurityConstraint());

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(lifecycle, "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Then {@link ContextConfig} (default constructor) {@link ContextConfig#context} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_thenContextConfigContextIsNull() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Assert that nothing has changed
    assertNull(contextConfig.context);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>Then {@link ContextConfig} (default constructor) {@link ContextConfig#ok}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_thenContextConfigOk() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(new TesterContext(), "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>When {@link LifecycleEvent#LifecycleEvent(Lifecycle, String, Object)} with lifecycle is {@link FailedContext} (default constructor) and {@code Type} and {@code Data}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_whenLifecycleEventWithLifecycleIsFailedContextAndTypeAndData() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "Type", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof FailedContext);
    assertNull(contextConfig.getContextWebXmlSource());
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>When {@link TesterContext} (default constructor) addMimeMapping {@code before_start} and {@code before_start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_whenTesterContextAddMimeMappingBeforeStartAndBeforeStart() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    TesterContext lifecycle = new TesterContext();
    lifecycle.addMimeMapping("before_start", "before_start");

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(lifecycle, "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>When {@link TesterContext} (default constructor) addWrapperLifecycle {@code before_start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_whenTesterContextAddWrapperLifecycleBeforeStart() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    TesterContext lifecycle = new TesterContext();
    lifecycle.addWrapperLifecycle("before_start");

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(lifecycle, "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#lifecycleEvent(LifecycleEvent)}.
   * <ul>
   *   <li>When {@link TesterContext} (default constructor) addWrapperListener {@code before_start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#lifecycleEvent(LifecycleEvent)}
   */
  @Test
  public void testLifecycleEvent_whenTesterContextAddWrapperListenerBeforeStart() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();

    TesterContext lifecycle = new TesterContext();
    lifecycle.addWrapperListener("before_start");

    // Act
    contextConfig.lifecycleEvent(new LifecycleEvent(lifecycle, "configure_stop", "Data"));

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
    assertTrue(contextConfig.ok);
  }

  /**
   * Test {@link ContextConfig#applicationAnnotationsConfig()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) Loader is {@link WebappLoader} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#applicationAnnotationsConfig()}
   */
  @Test
  public void testApplicationAnnotationsConfig_givenTesterContextLoaderIsWebappLoader() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.setLoader(new WebappLoader());
    lifecycle.addApplicationListener("Listener");
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.applicationAnnotationsConfig();

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
  }

  /**
   * Test {@link ContextConfig#applicationAnnotationsConfig()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) Loader is {@link WebappLoader} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#applicationAnnotationsConfig()}
   */
  @Test
  public void testApplicationAnnotationsConfig_givenTesterContextLoaderIsWebappLoader2() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.setLoader(new WebappLoader());
    lifecycle.addFilterDef(new FilterDef());
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.applicationAnnotationsConfig();

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
  }

  /**
   * Test {@link ContextConfig#applicationAnnotationsConfig()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) MetadataComplete is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#applicationAnnotationsConfig()}
   */
  @Test
  public void testApplicationAnnotationsConfig_givenTesterContextMetadataCompleteIsTrue() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.setMetadataComplete(true);
    lifecycle.addApplicationListener("Listener");
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.applicationAnnotationsConfig();

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
  }

  /**
   * Test {@link ContextConfig#applicationAnnotationsConfig()}.
   * <ul>
   *   <li>Then {@link ContextConfig} (default constructor) {@link ContextConfig#context} {@link TesterContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#applicationAnnotationsConfig()}
   */
  @Test
  public void testApplicationAnnotationsConfig_thenContextConfigContextTesterContext() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.applicationAnnotationsConfig();

    // Assert
    assertTrue(contextConfig.context instanceof TesterContext);
  }

  /**
   * Test {@link ContextConfig#authenticatorConfig()}.
   * <p>
   * Method under test: {@link ContextConfig#authenticatorConfig()}
   */
  @Test
  public void testAuthenticatorConfig() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.authenticatorConfig();

    // Assert
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    LoginConfig expectedLoginConfig = contextConfig.DUMMY_LOGIN_CONFIG;
    assertSame(expectedLoginConfig, context.getLoginConfig());
  }

  /**
   * Test {@link ContextConfig#authenticatorConfig()}.
   * <p>
   * Method under test: {@link ContextConfig#authenticatorConfig()}
   */
  @Test
  public void testAuthenticatorConfig2() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.setRealm(new AuthenticatedUserRealm());
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.authenticatorConfig();

    // Assert
    Context context = contextConfig.context;
    assertTrue(context.getAuthenticator() instanceof NonLoginAuthenticator);
    assertTrue(context instanceof TesterContext);
  }

  /**
   * Test {@link ContextConfig#authenticatorConfig()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addValve {@link BasicAuthenticator} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#authenticatorConfig()}
   */
  @Test
  public void testAuthenticatorConfig_givenTesterContextAddValveBasicAuthenticator() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addValve(new BasicAuthenticator());
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.authenticatorConfig();

    // Assert
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    LoginConfig expectedLoginConfig = contextConfig.DUMMY_LOGIN_CONFIG;
    assertSame(expectedLoginConfig, context.getLoginConfig());
  }

  /**
   * Test {@link ContextConfig#authenticatorConfig()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) LoginConfig is {@link ContextConfig#DUMMY_LOGIN_CONFIG}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#authenticatorConfig()}
   */
  @Test
  public void testAuthenticatorConfig_givenTesterContextLoginConfigIsDummy_login_config() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.setLoginConfig(ContextConfig.DUMMY_LOGIN_CONFIG);
    lifecycle.addValve(new BasicAuthenticator());
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.authenticatorConfig();

    // Assert that nothing has changed
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    LoginConfig expectedLoginConfig = contextConfig.DUMMY_LOGIN_CONFIG;
    assertSame(expectedLoginConfig, context.getLoginConfig());
  }

  /**
   * Test {@link ContextConfig#getContextXmlPackageName(String, Container)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code java.text}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getContextXmlPackageName(String, Container)}
   */
  @Test
  public void testGetContextXmlPackageName_whenStandardContext_thenReturnJavaText() {
    // Arrange, Act and Assert
    assertEquals("java.text", ContextConfig.getContextXmlPackageName("java.text", new StandardContext()));
  }

  /**
   * Test {@link ContextConfig#getContextXmlPackageName(String, Container)}.
   * <ul>
   *   <li>When {@link StandardEngine} (default constructor).</li>
   *   <li>Then return {@code java.text.null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getContextXmlPackageName(String, Container)}
   */
  @Test
  public void testGetContextXmlPackageName_whenStandardEngine_thenReturnJavaTextNull() {
    // Arrange, Act and Assert
    assertEquals("java.text.null", ContextConfig.getContextXmlPackageName("java.text", new StandardEngine()));
  }

  /**
   * Test {@link ContextConfig#getContextXmlPackageName(String, Container)}.
   * <ul>
   *   <li>When {@link StandardHost} (default constructor).</li>
   *   <li>Then return {@code java.textnull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getContextXmlPackageName(String, Container)}
   */
  @Test
  public void testGetContextXmlPackageName_whenStandardHost_thenReturnJavaTextnull() {
    // Arrange, Act and Assert
    assertEquals("java.textnull", ContextConfig.getContextXmlPackageName("java.text", new StandardHost()));
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addConstraint {@link SecurityConstraint} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_givenTesterContextAddConstraintSecurityConstraint() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addConstraint(new SecurityConstraint());
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addFilterDef {@link FilterDef} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_givenTesterContextAddFilterDefFilterDef() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addFilterDef(new FilterDef());
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert that nothing has changed
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addMimeMapping {@code Extension} and {@code Mime Type}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_givenTesterContextAddMimeMappingExtensionAndMimeType() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addMimeMapping("Extension", "Mime Type");
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert that nothing has changed
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addParameter {@code Name} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_givenTesterContextAddParameterNameAnd42() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addParameter("Name", "42");
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert that nothing has changed
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addSecurityRole {@code Role}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_givenTesterContextAddSecurityRoleRole() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addSecurityRole("Role");
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addWelcomeFile {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_givenTesterContextAddWelcomeFileName() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addWelcomeFile("Name");
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addWrapperLifecycle {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_givenTesterContextAddWrapperLifecycleListener() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addWrapperLifecycle("Listener");
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Given {@link TesterContext} (default constructor) addWrapperListener {@code Listener}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_givenTesterContextAddWrapperListenerListener() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addWrapperListener("Listener");
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#configureStop()}.
   * <ul>
   *   <li>Then {@link ContextConfig} (default constructor) {@link ContextConfig#context} {@link TesterContext}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#configureStop()}
   */
  @Test
  public void testConfigureStop_thenContextConfigContextTesterContext() {
    // Arrange
    TesterContext lifecycle = new TesterContext();
    lifecycle.addLifecycleListener(new AprLifecycleListener());
    LifecycleEvent event = new LifecycleEvent(lifecycle, "Type", "Data");

    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(event);

    // Act
    contextConfig.configureStop();

    // Assert that nothing has changed
    Context context = contextConfig.context;
    assertTrue(context instanceof TesterContext);
    assertEquals(0, context.findConstraints().length);
    assertEquals(0, context.findSecurityRoles().length);
    assertEquals(0, context.findWelcomeFiles().length);
    assertEquals(0, context.findWrapperLifecycles().length);
    assertEquals(0, context.findWrapperListeners().length);
  }

  /**
   * Test {@link ContextConfig#getHostConfigBase()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getHostConfigBase()}
   */
  @Test
  public void testGetHostConfigBase_thenReturnNull() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "Type", "Data"));

    // Act and Assert
    assertNull(contextConfig.getHostConfigBase());
  }

  /**
   * Test {@link ContextConfig#createWebXml()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor).</li>
   *   <li>Then return Version is {@code 6.0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#createWebXml()}
   */
  @Test
  public void testCreateWebXml_givenContextConfig_thenReturnVersionIs60() {
    // Arrange and Act
    WebXml actualCreateWebXmlResult = (new ContextConfig()).createWebXml();

    // Assert
    assertEquals("6.0", actualCreateWebXmlResult.getVersion());
    assertNull(actualCreateWebXmlResult.getJspConfigDescriptor());
    assertNull(actualCreateWebXmlResult.getDisplayName());
    assertNull(actualCreateWebXmlResult.getJarName());
    assertNull(actualCreateWebXmlResult.getName());
    assertNull(actualCreateWebXmlResult.getPublicId());
    assertNull(actualCreateWebXmlResult.getRequestCharacterEncoding());
    assertNull(actualCreateWebXmlResult.getResponseCharacterEncoding());
    assertNull(actualCreateWebXmlResult.getURL());
    assertNull(actualCreateWebXmlResult.getAbsoluteOrdering());
    assertNull(actualCreateWebXmlResult.getLoginConfig());
    assertEquals(0, actualCreateWebXmlResult.getMinorVersion());
    assertEquals(6, actualCreateWebXmlResult.getMajorVersion());
    assertFalse(actualCreateWebXmlResult.getDelegate());
    assertFalse(actualCreateWebXmlResult.getDenyUncoveredHttpMethods());
    assertFalse(actualCreateWebXmlResult.isDistributable());
    assertFalse(actualCreateWebXmlResult.isDuplicated());
    assertFalse(actualCreateWebXmlResult.isMetadataComplete());
    assertFalse(actualCreateWebXmlResult.isOverridable());
    assertTrue(actualCreateWebXmlResult.getDuplicates().isEmpty());
    assertTrue(actualCreateWebXmlResult.getContextParams().isEmpty());
    assertTrue(actualCreateWebXmlResult.getEjbLocalRefs().isEmpty());
    assertTrue(actualCreateWebXmlResult.getEjbRefs().isEmpty());
    assertTrue(actualCreateWebXmlResult.getEnvEntries().isEmpty());
    assertTrue(actualCreateWebXmlResult.getErrorPages().isEmpty());
    assertTrue(actualCreateWebXmlResult.getFilters().isEmpty());
    assertTrue(actualCreateWebXmlResult.getLocaleEncodingMappings().isEmpty());
    assertTrue(actualCreateWebXmlResult.getMessageDestinationRefs().isEmpty());
    assertTrue(actualCreateWebXmlResult.getMessageDestinations().isEmpty());
    assertTrue(actualCreateWebXmlResult.getMimeMappings().isEmpty());
    assertTrue(actualCreateWebXmlResult.getPostConstructMethods().isEmpty());
    assertTrue(actualCreateWebXmlResult.getPreDestroyMethods().isEmpty());
    assertTrue(actualCreateWebXmlResult.getResourceEnvRefs().isEmpty());
    assertTrue(actualCreateWebXmlResult.getResourceRefs().isEmpty());
    assertTrue(actualCreateWebXmlResult.getServiceRefs().isEmpty());
    assertTrue(actualCreateWebXmlResult.getServletMappings().isEmpty());
    assertTrue(actualCreateWebXmlResult.getServlets().isEmpty());
    assertTrue(actualCreateWebXmlResult.getTaglibs().isEmpty());
    assertTrue(actualCreateWebXmlResult.getAfterOrdering().isEmpty());
    assertTrue(actualCreateWebXmlResult.getBeforeOrdering().isEmpty());
    assertTrue(actualCreateWebXmlResult.getFilterMappings().isEmpty());
    assertTrue(actualCreateWebXmlResult.getJspPropertyGroups().isEmpty());
    assertTrue(actualCreateWebXmlResult.getListeners().isEmpty());
    assertTrue(actualCreateWebXmlResult.getSecurityConstraints().isEmpty());
    assertTrue(actualCreateWebXmlResult.getSecurityRoles().isEmpty());
    assertTrue(actualCreateWebXmlResult.getWelcomeFiles().isEmpty());
    assertTrue(actualCreateWebXmlResult.getWebappJar());
  }

  /**
   * Test {@link ContextConfig#createWebXml()}.
   * <ul>
   *   <li>Then return AbsoluteOrdering size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#createWebXml()}
   */
  @Test
  public void testCreateWebXml_thenReturnAbsoluteOrderingSizeIsTwo() {
    // Arrange, Act and Assert
    Set<String> absoluteOrdering = ((ContextConfig) new AbsoluteOrderContextConfig()).createWebXml()
        .getAbsoluteOrdering();
    assertEquals(2, absoluteOrdering.size());
    assertTrue(absoluteOrdering.contains("resources"));
    assertTrue(absoluteOrdering.contains("resources2"));
  }

  /**
   * Test {@link ContextConfig#getGlobalWebXmlSource()}.
   * <p>
   * Method under test: {@link ContextConfig#getGlobalWebXmlSource()}
   */
  @Test
  public void testGetGlobalWebXmlSource() throws IOException {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(new LifecycleEvent(new TesterContext(), Constants.DefaultWebXml, "Data"));

    // Act
    InputSource actualGlobalWebXmlSource = contextConfig.getGlobalWebXmlSource();

    // Assert
    assertNull(actualGlobalWebXmlSource.getCharacterStream());
    assertNull(actualGlobalWebXmlSource.getEncoding());
    assertNull(actualGlobalWebXmlSource.getPublicId());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualGlobalWebXmlSource.getByteStream().read(byteArray));
    assertFalse(actualGlobalWebXmlSource.isEmpty());
    String expectedSystemId = String.join("", "file:",
        Paths.get(System.getProperty("user.dir"), "conf", "web.xml").toString());
    assertEquals(expectedSystemId, actualGlobalWebXmlSource.getSystemId());
    assertArrayEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!--\n  Licen".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link ContextConfig#getGlobalWebXmlSource()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getGlobalWebXmlSource()}
   */
  @Test
  public void testGetGlobalWebXmlSource_givenContextConfig() throws IOException {
    // Arrange and Act
    InputSource actualGlobalWebXmlSource = (new ContextConfig()).getGlobalWebXmlSource();

    // Assert
    assertNull(actualGlobalWebXmlSource.getCharacterStream());
    assertNull(actualGlobalWebXmlSource.getEncoding());
    assertNull(actualGlobalWebXmlSource.getPublicId());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualGlobalWebXmlSource.getByteStream().read(byteArray));
    assertFalse(actualGlobalWebXmlSource.isEmpty());
    String expectedSystemId = String.join("", "file:",
        Paths.get(System.getProperty("user.dir"), "conf", "web.xml").toString());
    assertEquals(expectedSystemId, actualGlobalWebXmlSource.getSystemId());
    assertArrayEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!--\n  Licen".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link ContextConfig#getGlobalWebXmlSource()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor) DefaultWebXml is {@link Constants#DefaultWebXml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getGlobalWebXmlSource()}
   */
  @Test
  public void testGetGlobalWebXmlSource_givenContextConfigDefaultWebXmlIsDefaultWebXml() throws IOException {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.setDefaultWebXml(Constants.DefaultWebXml);

    // Act
    InputSource actualGlobalWebXmlSource = contextConfig.getGlobalWebXmlSource();

    // Assert
    assertNull(actualGlobalWebXmlSource.getCharacterStream());
    assertNull(actualGlobalWebXmlSource.getEncoding());
    assertNull(actualGlobalWebXmlSource.getPublicId());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualGlobalWebXmlSource.getByteStream().read(byteArray));
    assertFalse(actualGlobalWebXmlSource.isEmpty());
    String expectedSystemId = String.join("", "file:",
        Paths.get(System.getProperty("user.dir"), "conf", "web.xml").toString());
    assertEquals(expectedSystemId, actualGlobalWebXmlSource.getSystemId());
    assertArrayEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!--\n  Licen".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link ContextConfig#getGlobalWebXmlSource()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor) DefaultWebXml is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getGlobalWebXmlSource()}
   */
  @Test
  public void testGetGlobalWebXmlSource_givenContextConfigDefaultWebXmlIsFile() throws IOException {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.setDefaultWebXml("file:/");

    // Act
    InputSource actualGlobalWebXmlSource = contextConfig.getGlobalWebXmlSource();

    // Assert
    assertEquals("file:/", actualGlobalWebXmlSource.getSystemId());
    assertNull(actualGlobalWebXmlSource.getCharacterStream());
    assertNull(actualGlobalWebXmlSource.getEncoding());
    assertNull(actualGlobalWebXmlSource.getPublicId());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualGlobalWebXmlSource.getByteStream().read(byteArray));
    assertFalse(actualGlobalWebXmlSource.isEmpty());
    assertArrayEquals("bin\nboot\ndev\netc\nhome\nlib\nlib64\nlost+found\nmedia\nmn".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link ContextConfig#getGlobalWebXmlSource()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor) DefaultWebXml is {@link Constants#NoDefaultWebXml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getGlobalWebXmlSource()}
   */
  @Test
  public void testGetGlobalWebXmlSource_givenContextConfigDefaultWebXmlIsNoDefaultWebXml() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.setDefaultWebXml(Constants.NoDefaultWebXml);

    // Act and Assert
    assertNull(contextConfig.getGlobalWebXmlSource());
  }

  /**
   * Test {@link ContextConfig#getGlobalWebXmlSource()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor) DefaultWebXml is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getGlobalWebXmlSource()}
   */
  @Test
  public void testGetGlobalWebXmlSource_givenContextConfigDefaultWebXmlIsSlash() throws IOException {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.setDefaultWebXml("/");

    // Act
    InputSource actualGlobalWebXmlSource = contextConfig.getGlobalWebXmlSource();

    // Assert
    assertEquals("file:/", actualGlobalWebXmlSource.getSystemId());
    assertNull(actualGlobalWebXmlSource.getCharacterStream());
    assertNull(actualGlobalWebXmlSource.getEncoding());
    assertNull(actualGlobalWebXmlSource.getPublicId());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualGlobalWebXmlSource.getByteStream().read(byteArray));
    assertFalse(actualGlobalWebXmlSource.isEmpty());
    assertArrayEquals("bin\nboot\ndev\netc\nhome\nlib\nlib64\nlost+found\nmedia\nmn".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link ContextConfig#getGlobalWebXmlSource()}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor) DefaultWebXml is {@code web.xml}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getGlobalWebXmlSource()}
   */
  @Test
  public void testGetGlobalWebXmlSource_givenContextConfigDefaultWebXmlIsWebXml_thenReturnNull() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.setDefaultWebXml("web.xml");

    // Act and Assert
    assertNull(contextConfig.getGlobalWebXmlSource());
  }

  /**
   * Test {@link ContextConfig#getHostWebXmlSource()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getHostWebXmlSource()}
   */
  @Test
  public void testGetHostWebXmlSource_thenReturnNull() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "Type", "Data"));

    // Act and Assert
    assertNull(contextConfig.getHostWebXmlSource());
  }

  /**
   * Test {@link ContextConfig#getContextWebXmlSource()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getContextWebXmlSource()}
   */
  @Test
  public void testGetContextWebXmlSource_thenReturnNull() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "Type", "Data"));

    // Act and Assert
    assertNull(contextConfig.getContextWebXmlSource());
  }

  /**
   * Test {@link ContextConfig#getConfigBasePath()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getConfigBasePath()}
   */
  @Test
  public void testGetConfigBasePath_thenReturnNull() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "Type", "Data"));

    // Act and Assert
    assertNull(contextConfig.getConfigBasePath());
  }

  /**
   * Test {@link ContextConfig#getWebXmlSource(String, boolean)}.
   * <p>
   * Method under test: {@link ContextConfig#getWebXmlSource(String, boolean)}
   */
  @Test
  public void testGetWebXmlSource() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(new LifecycleEvent(new BasicAuthenticator(), "Type", "Data"));

    // Act and Assert
    assertNull(contextConfig.getWebXmlSource(Constants.DefaultWebXml, false));
  }

  /**
   * Test {@link ContextConfig#getWebXmlSource(String, boolean)}.
   * <p>
   * Method under test: {@link ContextConfig#getWebXmlSource(String, boolean)}
   */
  @Test
  public void testGetWebXmlSource2() throws IOException {
    // Arrange and Act
    InputSource actualWebXmlSource = (new ContextConfig()).getWebXmlSource(Constants.DefaultWebXml, true);

    // Assert
    assertNull(actualWebXmlSource.getCharacterStream());
    assertNull(actualWebXmlSource.getEncoding());
    assertNull(actualWebXmlSource.getPublicId());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualWebXmlSource.getByteStream().read(byteArray));
    assertFalse(actualWebXmlSource.isEmpty());
    String expectedSystemId = String.join("", "file:",
        Paths.get(System.getProperty("user.dir"), "conf", "web.xml").toString());
    assertEquals(expectedSystemId, actualWebXmlSource.getSystemId());
    assertArrayEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!--\n  Licen".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link ContextConfig#getWebXmlSource(String, boolean)}.
   * <p>
   * Method under test: {@link ContextConfig#getWebXmlSource(String, boolean)}
   */
  @Test
  public void testGetWebXmlSource3() {
    // Arrange
    ContextConfig contextConfig = new ContextConfig();
    contextConfig.lifecycleEvent(new LifecycleEvent(new FailedContext(), "Type", "Data"));

    // Act and Assert
    assertNull(contextConfig.getWebXmlSource(Constants.DefaultWebXml, false));
  }

  /**
   * Test {@link ContextConfig#getWebXmlSource(String, boolean)}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor).</li>
   *   <li>When {@code file:/}.</li>
   *   <li>Then return SystemId is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getWebXmlSource(String, boolean)}
   */
  @Test
  public void testGetWebXmlSource_givenContextConfig_whenFile_thenReturnSystemIdIsFile() throws IOException {
    // Arrange and Act
    InputSource actualWebXmlSource = (new ContextConfig()).getWebXmlSource("file:/", true);

    // Assert
    assertEquals("file:/", actualWebXmlSource.getSystemId());
    assertNull(actualWebXmlSource.getCharacterStream());
    assertNull(actualWebXmlSource.getEncoding());
    assertNull(actualWebXmlSource.getPublicId());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualWebXmlSource.getByteStream().read(byteArray));
    assertFalse(actualWebXmlSource.isEmpty());
    assertArrayEquals("bin\nboot\ndev\netc\nhome\nlib\nlib64\nlost+found\nmedia\nmn".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link ContextConfig#getWebXmlSource(String, boolean)}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor).</li>
   *   <li>When {@code foo.txt}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getWebXmlSource(String, boolean)}
   */
  @Test
  public void testGetWebXmlSource_givenContextConfig_whenFooTxt_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ContextConfig()).getWebXmlSource("foo.txt", true));
  }

  /**
   * Test {@link ContextConfig#getWebXmlSource(String, boolean)}.
   * <ul>
   *   <li>Given {@link ContextConfig} (default constructor).</li>
   *   <li>When {@code /}.</li>
   *   <li>Then return SystemId is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextConfig#getWebXmlSource(String, boolean)}
   */
  @Test
  public void testGetWebXmlSource_givenContextConfig_whenSlash_thenReturnSystemIdIsFile() throws IOException {
    // Arrange and Act
    InputSource actualWebXmlSource = (new ContextConfig()).getWebXmlSource("/", true);

    // Assert
    assertEquals("file:/", actualWebXmlSource.getSystemId());
    assertNull(actualWebXmlSource.getCharacterStream());
    assertNull(actualWebXmlSource.getEncoding());
    assertNull(actualWebXmlSource.getPublicId());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualWebXmlSource.getByteStream().read(byteArray));
    assertFalse(actualWebXmlSource.isEmpty());
    assertArrayEquals("bin\nboot\ndev\netc\nhome\nlib\nlib64\nlost+found\nmedia\nmn".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link ContextConfig#processAnnotationWebInitParams(ElementValue)}.
   * <p>
   * Method under test: {@link ContextConfig#processAnnotationWebInitParams(ElementValue)}
   */
  @Test
  public void testProcessAnnotationWebInitParams() {
    // Arrange, Act and Assert
    assertTrue((new ContextConfig()).processAnnotationWebInitParams(null).isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link ContextConfig}
   *   <li>{@link ContextConfig#setCustomAuthenticators(Map)}
   *   <li>{@link ContextConfig#setDefaultWebXml(String)}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ContextConfig actualContextConfig = new ContextConfig();
    actualContextConfig.setCustomAuthenticators(new HashMap<>());
    actualContextConfig.setDefaultWebXml("Path");

    // Assert
    assertEquals("Path", actualContextConfig.getDefaultWebXml());
    assertTrue(actualContextConfig.customAuthenticators.isEmpty());
    assertTrue(actualContextConfig.initializerClassMap.isEmpty());
    assertTrue(actualContextConfig.typeInitializerMap.isEmpty());
  }
}
