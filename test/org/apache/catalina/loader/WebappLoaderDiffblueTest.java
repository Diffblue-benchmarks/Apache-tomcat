package org.apache.catalina.loader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.NamingContextListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.webresources.TesterWebResourceRoot;
import org.apache.tomcat.unittest.TesterContext;
import org.junit.Test;

public class WebappLoaderDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link WebappLoader#setLoaderClass(String)}
   *   <li>{@link WebappLoader#toString()}
   *   <li>{@link WebappLoader#getClassLoader()}
   *   <li>{@link WebappLoader#getClasspath()}
   *   <li>{@link WebappLoader#getContext()}
   *   <li>{@link WebappLoader#getDelegate()}
   *   <li>{@link WebappLoader#getJakartaConverter()}
   *   <li>{@link WebappLoader#getLoaderClass()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();

    // Act
    webappLoader.setLoaderClass("Loader Class");
    String actualToStringResult = webappLoader.toString();
    ClassLoader actualClassLoader = webappLoader.getClassLoader();
    String actualClasspath = webappLoader.getClasspath();
    Context actualContext = webappLoader.getContext();
    boolean actualDelegate = webappLoader.getDelegate();
    String actualJakartaConverter = webappLoader.getJakartaConverter();

    // Assert
    assertEquals("Loader Class", webappLoader.getLoaderClass());
    assertEquals("WebappLoader[Container is null]", actualToStringResult);
    assertNull(actualClassLoader);
    assertNull(actualClasspath);
    assertNull(actualJakartaConverter);
    assertNull(actualContext);
    assertFalse(actualDelegate);
  }

  /**
   * Test {@link WebappLoader#setContext(Context)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link WebappLoader} (default constructor) Context is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#setContext(Context)}
   */
  @Test
  public void testSetContext_whenNull_thenWebappLoaderContextIsNull() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();

    // Act
    webappLoader.setContext(null);

    // Assert that nothing has changed
    assertNull(webappLoader.getContext());
  }

  /**
   * Test {@link WebappLoader#setContext(Context)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then {@link WebappLoader} (default constructor) DomainInternal is {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#setContext(Context)}
   */
  @Test
  public void testSetContext_whenStandardContext_thenWebappLoaderDomainInternalIsCatalina() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();
    StandardContext context = new StandardContext();

    // Act
    webappLoader.setContext(context);

    // Assert
    assertEquals("Catalina", webappLoader.getDomainInternal());
    assertEquals("Catalina", webappLoader.getDomain());
    assertSame(context, webappLoader.getContext());
  }

  /**
   * Test {@link WebappLoader#setDelegate(boolean)}.
   * <p>
   * Method under test: {@link WebappLoader#setDelegate(boolean)}
   */
  @Test
  public void testSetDelegate() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();

    // Act
    webappLoader.setDelegate(true);

    // Assert
    assertTrue(webappLoader.getDelegate());
  }

  /**
   * Test {@link WebappLoader#setJakartaConverter(String)}.
   * <p>
   * Method under test: {@link WebappLoader#setJakartaConverter(String)}
   */
  @Test
  public void testSetJakartaConverter() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();

    // Act
    webappLoader.setJakartaConverter("Jakarta Converter");

    // Assert
    assertEquals("Jakarta Converter", webappLoader.getJakartaConverter());
  }

  /**
   * Test {@link WebappLoader#setLoaderInstance(WebappClassLoaderBase)}.
   * <ul>
   *   <li>Then {@link WebappLoader} (default constructor) ClassLoader is {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#setLoaderInstance(WebappClassLoaderBase)}
   */
  @Test
  public void testSetLoaderInstance_thenWebappLoaderClassLoaderIsParallelWebappClassLoader() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();
    ParallelWebappClassLoader loaderInstance = new ParallelWebappClassLoader();

    // Act
    webappLoader.setLoaderInstance(loaderInstance);

    // Assert
    assertSame(loaderInstance, webappLoader.getClassLoader());
  }

  /**
   * Test {@link WebappLoader#addPropertyChangeListener(PropertyChangeListener)}.
   * <p>
   * Method under test: {@link WebappLoader#addPropertyChangeListener(PropertyChangeListener)}
   */
  @Test
  public void testAddPropertyChangeListener() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();
    NamingContextListener listener = new NamingContextListener();

    // Act
    webappLoader.addPropertyChangeListener(listener);

    // Assert
    PropertyChangeListener[] propertyChangeListeners = webappLoader.support.getPropertyChangeListeners();
    assertEquals(1, propertyChangeListeners.length);
    assertSame(listener, propertyChangeListeners[0]);
  }

  /**
   * Test {@link WebappLoader#getLoaderRepositories()}.
   * <p>
   * Method under test: {@link WebappLoader#getLoaderRepositories()}
   */
  @Test
  public void testGetLoaderRepositories() throws MalformedURLException {
    // Arrange
    ParallelWebappClassLoader loaderInstance = new ParallelWebappClassLoader();
    loaderInstance.addURL(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setLoaderInstance(loaderInstance);

    // Act
    String[] actualLoaderRepositories = webappLoader.getLoaderRepositories();

    // Assert
    assertArrayEquals(
        new String[]{String.join("", "file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator))},
        actualLoaderRepositories);
  }

  /**
   * Test {@link WebappLoader#getLoaderRepositories()}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor).</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getLoaderRepositories()}
   */
  @Test
  public void testGetLoaderRepositories_givenWebappLoader_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new WebappLoader()).getLoaderRepositories().length);
  }

  /**
   * Test {@link WebappLoader#getLoaderRepositories()}.
   * <ul>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getLoaderRepositories()}
   */
  @Test
  public void testGetLoaderRepositories_thenReturnArrayLengthIsZero() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setLoaderInstance(new ParallelWebappClassLoader());

    // Act and Assert
    assertEquals(0, webappLoader.getLoaderRepositories().length);
  }

  /**
   * Test {@link WebappLoader#getLoaderRepositoriesString()}.
   * <p>
   * Method under test: {@link WebappLoader#getLoaderRepositoriesString()}
   */
  @Test
  public void testGetLoaderRepositoriesString() throws MalformedURLException {
    // Arrange
    ParallelWebappClassLoader loaderInstance = new ParallelWebappClassLoader();
    loaderInstance.addURL(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setLoaderInstance(loaderInstance);

    // Act
    String actualLoaderRepositoriesString = webappLoader.getLoaderRepositoriesString();

    // Assert
    assertEquals(
        String.join("", "file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator), ":"),
        actualLoaderRepositoriesString);
  }

  /**
   * Test {@link WebappLoader#getLoaderRepositoriesString()}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor).</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getLoaderRepositoriesString()}
   */
  @Test
  public void testGetLoaderRepositoriesString_givenWebappLoader_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new WebappLoader()).getLoaderRepositoriesString());
  }

  /**
   * Test {@link WebappLoader#getLoaderRepositoriesString()}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getLoaderRepositoriesString()}
   */
  @Test
  public void testGetLoaderRepositoriesString_thenReturnEmptyString() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setLoaderInstance(new ParallelWebappClassLoader());

    // Act and Assert
    assertEquals("", webappLoader.getLoaderRepositoriesString());
  }

  /**
   * Test {@link WebappLoader#modified()}.
   * <ul>
   *   <li>Given {@link ParallelWebappClassLoader#ParallelWebappClassLoader()} Resources is {@link TesterWebResourceRoot} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#modified()}
   */
  @Test
  public void testModified_givenParallelWebappClassLoaderResourcesIsTesterWebResourceRoot() {
    // Arrange
    ParallelWebappClassLoader loaderInstance = new ParallelWebappClassLoader();
    loaderInstance.setResources(new TesterWebResourceRoot());

    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setLoaderInstance(loaderInstance);

    // Act and Assert
    assertFalse(webappLoader.modified());
  }

  /**
   * Test {@link WebappLoader#modified()}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#modified()}
   */
  @Test
  public void testModified_givenWebappLoader_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new WebappLoader()).modified());
  }

  /**
   * Test {@link WebappLoader#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Domain is {@code Catalina}.</li>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContextDomainIsCatalina_thenReturnCatalina() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setDomain("Catalina");

    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setContext(context);

    // Act and Assert
    assertEquals("Catalina", webappLoader.getDomainInternal());
  }

  /**
   * Test {@link WebappLoader#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link StandardContext} (default constructor) Parent is {@link StandardContext} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenStandardContextParentIsStandardContext() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());

    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setContext(context);

    // Act and Assert
    assertEquals("Catalina", webappLoader.getDomainInternal());
  }

  /**
   * Test {@link WebappLoader#getDomainInternal()}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor) Context is {@link TesterContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_givenWebappLoaderContextIsTesterContext_thenReturnNull() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setContext(new TesterContext());

    // Act and Assert
    assertNull(webappLoader.getDomainInternal());
  }

  /**
   * Test {@link WebappLoader#getDomainInternal()}.
   * <ul>
   *   <li>Then return {@code Catalina}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getDomainInternal()}
   */
  @Test
  public void testGetDomainInternal_thenReturnCatalina() {
    // Arrange
    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setContext(new StandardContext());

    // Act and Assert
    assertEquals("Catalina", webappLoader.getDomainInternal());
  }

  /**
   * Test {@link WebappLoader#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Then return {@code type=Loader,host=null,context=/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_thenReturnTypeLoaderHostNullContext() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());
    context.setName("/");

    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setContext(context);

    // Act and Assert
    assertEquals("type=Loader,host=null,context=/", webappLoader.getObjectNameKeyProperties());
  }

  /**
   * Test {@link WebappLoader#getObjectNameKeyProperties()}.
   * <ul>
   *   <li>Then return {@code type=Loader,host=null,context=/type=Loader}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappLoader#getObjectNameKeyProperties()}
   */
  @Test
  public void testGetObjectNameKeyProperties_thenReturnTypeLoaderHostNullContextTypeLoader() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setParent(new StandardContext());
    context.setName("type=Loader");

    WebappLoader webappLoader = new WebappLoader();
    webappLoader.setContext(context);

    // Act and Assert
    assertEquals("type=Loader,host=null,context=/type=Loader", webappLoader.getObjectNameKeyProperties());
  }

  /**
   * Test new {@link WebappLoader} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link WebappLoader}
   */
  @Test
  public void testNewWebappLoader() {
    // Arrange and Act
    WebappLoader actualWebappLoader = new WebappLoader();

    // Assert
    assertEquals("", actualWebappLoader.getLoaderRepositoriesString());
    assertEquals("NEW", actualWebappLoader.getStateName());
    assertEquals("org.apache.catalina.loader.ParallelWebappClassLoader", actualWebappLoader.getLoaderClass());
    assertNull(actualWebappLoader.getClassLoader());
    assertNull(actualWebappLoader.getClasspath());
    assertNull(actualWebappLoader.getJakartaConverter());
    assertNull(actualWebappLoader.getObjectName());
    assertNull(actualWebappLoader.getContext());
    assertEquals(0, actualWebappLoader.getLoaderRepositories().length);
    assertEquals(0, actualWebappLoader.findLifecycleListeners().length);
    assertEquals(LifecycleState.NEW, actualWebappLoader.getState());
    assertFalse(actualWebappLoader.getDelegate());
    assertTrue(actualWebappLoader.getThrowOnFailure());
  }
}
