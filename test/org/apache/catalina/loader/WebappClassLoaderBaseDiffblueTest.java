package org.apache.catalina.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.jar.Manifest;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.webresources.ExtractingRoot;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.catalina.webresources.TesterWebResourceRoot;
import org.junit.Test;

public class WebappClassLoaderBaseDiffblueTest {
  /**
   * Test {@link WebappClassLoaderBase#getNotFoundClassResourceCacheSize()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getNotFoundClassResourceCacheSize()}
   */
  @Test
  public void testGetNotFoundClassResourceCacheSize_thenReturnMinusOne() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setNotFoundClassResourceCacheSize(-1);

    // Act and Assert
    assertEquals(-1, parallelWebappClassLoader.getNotFoundClassResourceCacheSize());
  }

  /**
   * Test {@link WebappClassLoaderBase#getNotFoundClassResourceCacheSize()}.
   * <ul>
   *   <li>Then return one thousand.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getNotFoundClassResourceCacheSize()}
   */
  @Test
  public void testGetNotFoundClassResourceCacheSize_thenReturnOneThousand() {
    // Arrange, Act and Assert
    assertEquals(1000, (new ParallelWebappClassLoader()).getNotFoundClassResourceCacheSize());
  }

  /**
   * Test {@link WebappClassLoaderBase#getContextName()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   *   <li>Then return {@code Unknown}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getContextName()}
   */
  @Test
  public void testGetContextName_givenParallelWebappClassLoader_thenReturnUnknown() {
    // Arrange, Act and Assert
    assertEquals("Unknown", (new ParallelWebappClassLoader()).getContextName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getContextName()}.
   * <ul>
   *   <li>Then return {@code ROOT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getContextName()}
   */
  @Test
  public void testGetContextName_thenReturnRoot() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setResources(new StandardRoot(new StandardContext()));

    // Act and Assert
    assertEquals("ROOT", parallelWebappClassLoader.getContextName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getDelegate()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()} Delegate is {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getDelegate()}
   */
  @Test
  public void testGetDelegate_givenParallelWebappClassLoaderDelegateIsTrue_thenReturnTrue() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setDelegate(true);

    // Act and Assert
    assertTrue(parallelWebappClassLoader.getDelegate());
  }

  /**
   * Test {@link WebappClassLoaderBase#getDelegate()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getDelegate()}
   */
  @Test
  public void testGetDelegate_givenParallelWebappClassLoader_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).getDelegate());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesRmiTargets()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesRmiTargets()}
   */
  @Test
  public void testGetClearReferencesRmiTargets_givenParallelWebappClassLoader_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ParallelWebappClassLoader()).getClearReferencesRmiTargets());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesRmiTargets()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesRmiTargets()}
   */
  @Test
  public void testGetClearReferencesRmiTargets_thenReturnFalse() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setClearReferencesRmiTargets(false);

    // Act and Assert
    assertFalse(parallelWebappClassLoader.getClearReferencesRmiTargets());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesStopThreads()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesStopThreads()}
   */
  @Test
  public void testGetClearReferencesStopThreads_givenParallelWebappClassLoader_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).getClearReferencesStopThreads());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesStopThreads()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesStopThreads()}
   */
  @Test
  public void testGetClearReferencesStopThreads_thenReturnTrue() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setClearReferencesStopThreads(true);

    // Act and Assert
    assertTrue(parallelWebappClassLoader.getClearReferencesStopThreads());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesStopTimerThreads()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesStopTimerThreads()}
   */
  @Test
  public void testGetClearReferencesStopTimerThreads_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).getClearReferencesStopTimerThreads());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesStopTimerThreads()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesStopTimerThreads()}
   */
  @Test
  public void testGetClearReferencesStopTimerThreads_thenReturnTrue() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setClearReferencesStopTimerThreads(true);

    // Act and Assert
    assertTrue(parallelWebappClassLoader.getClearReferencesStopTimerThreads());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesLogFactoryRelease()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesLogFactoryRelease()}
   */
  @Test
  public void testGetClearReferencesLogFactoryRelease_thenReturnFalse() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setClearReferencesLogFactoryRelease(false);

    // Act and Assert
    assertFalse(parallelWebappClassLoader.getClearReferencesLogFactoryRelease());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesLogFactoryRelease()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesLogFactoryRelease()}
   */
  @Test
  public void testGetClearReferencesLogFactoryRelease_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ParallelWebappClassLoader()).getClearReferencesLogFactoryRelease());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesHttpClientKeepAliveThread()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesHttpClientKeepAliveThread()}
   */
  @Test
  public void testGetClearReferencesHttpClientKeepAliveThread_thenReturnFalse() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setClearReferencesHttpClientKeepAliveThread(false);

    // Act and Assert
    assertFalse(parallelWebappClassLoader.getClearReferencesHttpClientKeepAliveThread());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesHttpClientKeepAliveThread()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesHttpClientKeepAliveThread()}
   */
  @Test
  public void testGetClearReferencesHttpClientKeepAliveThread_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ParallelWebappClassLoader()).getClearReferencesHttpClientKeepAliveThread());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesThreadLocals()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesThreadLocals()}
   */
  @Test
  public void testGetClearReferencesThreadLocals_givenParallelWebappClassLoader_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ParallelWebappClassLoader()).getClearReferencesThreadLocals());
  }

  /**
   * Test {@link WebappClassLoaderBase#getClearReferencesThreadLocals()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getClearReferencesThreadLocals()}
   */
  @Test
  public void testGetClearReferencesThreadLocals_thenReturnFalse() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setClearReferencesThreadLocals(false);

    // Act and Assert
    assertFalse(parallelWebappClassLoader.getClearReferencesThreadLocals());
  }

  /**
   * Test {@link WebappClassLoaderBase#getSkipMemoryLeakChecksOnJvmShutdown()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getSkipMemoryLeakChecksOnJvmShutdown()}
   */
  @Test
  public void testGetSkipMemoryLeakChecksOnJvmShutdown_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).getSkipMemoryLeakChecksOnJvmShutdown());
  }

  /**
   * Test {@link WebappClassLoaderBase#getSkipMemoryLeakChecksOnJvmShutdown()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getSkipMemoryLeakChecksOnJvmShutdown()}
   */
  @Test
  public void testGetSkipMemoryLeakChecksOnJvmShutdown_thenReturnTrue() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setSkipMemoryLeakChecksOnJvmShutdown(true);

    // Act and Assert
    assertTrue(parallelWebappClassLoader.getSkipMemoryLeakChecksOnJvmShutdown());
  }

  /**
   * Test {@link WebappClassLoaderBase#addTransformer(ClassFileTransformer)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#addTransformer(ClassFileTransformer)}
   */
  @Test
  public void testAddTransformer_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new ParallelWebappClassLoader()).addTransformer(null));
  }

  /**
   * Test {@link WebappClassLoaderBase#modified()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#modified()}
   */
  @Test
  public void testModified_thenReturnFalse() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setResources(new TesterWebResourceRoot());

    // Act and Assert
    assertFalse(parallelWebappClassLoader.modified());
  }

  /**
   * Test {@link WebappClassLoaderBase#findClass(String)} with {@code String}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#findClass(String)}
   */
  @Test
  public void testFindClassWithString() throws ClassNotFoundException {
    // Arrange, Act and Assert
    assertThrows(ClassNotFoundException.class, () -> (new ParallelWebappClassLoader()).findClass("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#findResource(String)} with {@code String}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#findResource(String)}
   */
  @Test
  public void testFindResourceWithString() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ParallelWebappClassLoader()).findResource("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#findResources(String)}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#findResources(String)}
   */
  @Test
  public void testFindResources() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ParallelWebappClassLoader()).findResources("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#getResource(String)}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getResource(String)}
   */
  @Test
  public void testGetResource() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ParallelWebappClassLoader()).getResource("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#getResources(String)}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getResources(String)}
   */
  @Test
  public void testGetResources() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ParallelWebappClassLoader(new ParallelWebappClassLoader())).getResources("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#getResources(String)}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getResources(String)}
   */
  @Test
  public void testGetResources_givenParallelWebappClassLoader() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ParallelWebappClassLoader()).getResources("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#getResourceAsStream(String)}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getResourceAsStream(String)}
   */
  @Test
  public void testGetResourceAsStream() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ParallelWebappClassLoader()).getResourceAsStream("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#loadClass(String)} with {@code name}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#loadClass(String)}
   */
  @Test
  public void testLoadClassWithName() throws ClassNotFoundException {
    // Arrange, Act and Assert
    assertThrows(ClassNotFoundException.class, () -> (new ParallelWebappClassLoader()).loadClass("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#loadClass(String, boolean)} with {@code name}, {@code resolve}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then throw {@link ClassNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#loadClass(String, boolean)}
   */
  @Test
  public void testLoadClassWithNameResolve_whenName_thenThrowClassNotFoundException() throws ClassNotFoundException {
    // Arrange, Act and Assert
    assertThrows(ClassNotFoundException.class, () -> (new ParallelWebappClassLoader()).loadClass("Name", true));
  }

  /**
   * Test {@link WebappClassLoaderBase#checkStateForClassLoading(String)}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#checkStateForClassLoading(String)}
   */
  @Test
  public void testCheckStateForClassLoading() throws ClassNotFoundException {
    // Arrange, Act and Assert
    assertThrows(ClassNotFoundException.class,
        () -> (new ParallelWebappClassLoader()).checkStateForClassLoading("Class Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#checkStateForResourceLoading(String)}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#checkStateForResourceLoading(String)}
   */
  @Test
  public void testCheckStateForResourceLoading() throws IllegalStateException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ParallelWebappClassLoader()).checkStateForResourceLoading("Resource"));
  }

  /**
   * Test {@link WebappClassLoaderBase#getPermissions(CodeSource)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getPermissions(CodeSource)}
   */
  @Test
  public void testGetPermissions_thenReturnNull() throws MalformedURLException {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();

    // Act and Assert
    assertNull(parallelWebappClassLoader.getPermissions(new CodeSource(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(), new CodeSigner[]{null})));
  }

  /**
   * Test {@link WebappClassLoaderBase#getURLs()}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getURLs()}
   */
  @Test
  public void testGetURLs() {
    // Arrange, Act and Assert
    assertEquals(0, (new ParallelWebappClassLoader()).getURLs().length);
  }

  /**
   * Test {@link WebappClassLoaderBase#findLifecycleListeners()}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#findLifecycleListeners()}
   */
  @Test
  public void testFindLifecycleListeners() {
    // Arrange, Act and Assert
    assertEquals(0, (new ParallelWebappClassLoader()).findLifecycleListeners().length);
  }

  /**
   * Test {@link WebappClassLoaderBase#getState()}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getState()}
   */
  @Test
  public void testGetState() {
    // Arrange, Act and Assert
    assertEquals(LifecycleState.NEW, (new ParallelWebappClassLoader()).getState());
  }

  /**
   * Test {@link WebappClassLoaderBase#getStateName()}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getStateName()}
   */
  @Test
  public void testGetStateName() {
    // Arrange, Act and Assert
    assertEquals("NEW", (new ParallelWebappClassLoader()).getStateName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getJavaseClassLoader()}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getJavaseClassLoader()}
   */
  @Test
  public void testGetJavaseClassLoader() {
    // Arrange, Act and Assert
    assertNotNull((new ParallelWebappClassLoader()).getJavaseClassLoader());
  }

  /**
   * Test {@link WebappClassLoaderBase#setJavaseClassLoader(ClassLoader)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#setJavaseClassLoader(ClassLoader)}
   */
  @Test
  public void testSetJavaseClassLoader_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new ParallelWebappClassLoader()).setJavaseClassLoader(null));
  }

  /**
   * Test {@link WebappClassLoaderBase#findClassInternal(String)} with {@code name}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#findClassInternal(String)}
   */
  @Test
  public void testFindClassInternalWithName() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ParallelWebappClassLoader()).findClassInternal("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#isPackageSealed(String, Manifest)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#isPackageSealed(String, Manifest)}
   */
  @Test
  public void testIsPackageSealed_whenName_thenReturnFalse() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();

    // Act and Assert
    assertFalse(parallelWebappClassLoader.isPackageSealed("Name", new Manifest()));
  }

  /**
   * Test {@link WebappClassLoaderBase#findLoadedClass0(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#findLoadedClass0(String)}
   */
  @Test
  public void testFindLoadedClass0_whenName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ParallelWebappClassLoader()).findLoadedClass0("Name"));
  }

  /**
   * Test {@link WebappClassLoaderBase#filter(String, boolean)}.
   * <ul>
   *   <li>When {@code jakarta}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#filter(String, boolean)}
   */
  @Test
  public void testFilter_whenJakarta() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).filter("jakarta", false));
  }

  /**
   * Test {@link WebappClassLoaderBase#filter(String, boolean)}.
   * <ul>
   *   <li>When {@code javax}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#filter(String, boolean)}
   */
  @Test
  public void testFilter_whenJavax() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).filter("javax", false));
  }

  /**
   * Test {@link WebappClassLoaderBase#filter(String, boolean)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#filter(String, boolean)}
   */
  @Test
  public void testFilter_whenName() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).filter("Name", true));
  }

  /**
   * Test {@link WebappClassLoaderBase#filter(String, boolean)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#filter(String, boolean)}
   */
  @Test
  public void testFilter_whenNull() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).filter(null, false));
  }

  /**
   * Test {@link WebappClassLoaderBase#filter(String, boolean)}.
   * <ul>
   *   <li>When {@code org}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#filter(String, boolean)}
   */
  @Test
  public void testFilter_whenOrg() {
    // Arrange, Act and Assert
    assertFalse((new ParallelWebappClassLoader()).filter("org", false));
  }

  /**
   * Test {@link WebappClassLoaderBase#getWebappName()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   *   <li>Then return {@code Unknown}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getWebappName()}
   */
  @Test
  public void testGetWebappName_givenParallelWebappClassLoader_thenReturnUnknown() {
    // Arrange, Act and Assert
    assertEquals("Unknown", (new ParallelWebappClassLoader()).getWebappName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getWebappName()}.
   * <ul>
   *   <li>Then return {@code ROOT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getWebappName()}
   */
  @Test
  public void testGetWebappName_thenReturnRoot() {
    // Arrange
    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setResources(new StandardRoot(new StandardContext()));

    // Act and Assert
    assertEquals("ROOT", parallelWebappClassLoader.getWebappName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getHostName()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getHostName()}
   */
  @Test
  public void testGetHostName_givenParallelWebappClassLoader_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ParallelWebappClassLoader()).getHostName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getHostName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getHostName()}
   */
  @Test
  public void testGetHostName_givenStandardContextParentIsNull_thenReturnNull() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(null);

    ExtractingRoot resources = new ExtractingRoot();
    resources.setContext(context);

    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setResources(resources);

    // Act and Assert
    assertNull(parallelWebappClassLoader.getHostName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getHostName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getHostName()}
   */
  @Test
  public void testGetHostName_givenStandardContextParentIsStandardContext_thenReturnNull() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());

    ExtractingRoot resources = new ExtractingRoot();
    resources.setContext(context);

    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setResources(resources);

    // Act and Assert
    assertNull(parallelWebappClassLoader.getHostName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getServiceName()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getServiceName()}
   */
  @Test
  public void testGetServiceName_givenParallelWebappClassLoader_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ParallelWebappClassLoader()).getServiceName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getServiceName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getServiceName()}
   */
  @Test
  public void testGetServiceName_givenStandardContextParentIsNull_thenReturnNull() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(null);

    ExtractingRoot resources = new ExtractingRoot();
    resources.setContext(context);

    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setResources(resources);

    // Act and Assert
    assertNull(parallelWebappClassLoader.getServiceName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getServiceName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getServiceName()}
   */
  @Test
  public void testGetServiceName_givenStandardContextParentIsStandardContext_thenReturnNull() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());

    ExtractingRoot resources = new ExtractingRoot();
    resources.setContext(context);

    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setResources(resources);

    // Act and Assert
    assertNull(parallelWebappClassLoader.getServiceName());
  }

  /**
   * Test {@link WebappClassLoaderBase#getServiceName()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoaderBase#getServiceName()}
   */
  @Test
  public void testGetServiceName_givenStandardContextParentIsStandardContext_thenReturnNull2() {
    // Arrange
    StandardContext container = new StandardContext();
    container.setParent(new StandardContext());

    StandardContext context = new StandardContext();
    context.setParent(container);

    ExtractingRoot resources = new ExtractingRoot();
    resources.setContext(context);

    ParallelWebappClassLoader parallelWebappClassLoader = new ParallelWebappClassLoader();
    parallelWebappClassLoader.setResources(resources);

    // Act and Assert
    assertNull(parallelWebappClassLoader.getServiceName());
  }

  /**
   * Test {@link WebappClassLoaderBase#hasLoggingConfig()}.
   * <p>
   * Method under test: {@link WebappClassLoaderBase#hasLoggingConfig()}
   */
  @Test
  public void testHasLoggingConfig() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new ParallelWebappClassLoader()).hasLoggingConfig());
  }
}
