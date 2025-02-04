package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;
import javax.naming.Context;
import javax.naming.NamingException;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.juli.logging.Log;
import org.apache.naming.SelectorContext;
import org.junit.Test;

public class DefaultInstanceManagerDiffblueTest {
  /**
   * Test {@link DefaultInstanceManager#DefaultInstanceManager(Context, Map, Context, ClassLoader)}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor).</li>
   *   <li>Then return {@link DefaultInstanceManager#classLoader} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultInstanceManager#DefaultInstanceManager(Context, Map, org.apache.catalina.Context, ClassLoader)}
   */
  @Test
  public void testNewDefaultInstanceManager_givenWebappLoader_thenReturnClassLoaderIsNull() {
    // Arrange
    SelectorContext context = new SelectorContext(new Hashtable<>());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();

    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());

    // Act
    DefaultInstanceManager actualDefaultInstanceManager = new DefaultInstanceManager(context, injectionMap,
        catalinaContext, new ParallelWebappClassLoader());

    // Assert
    assertNull(actualDefaultInstanceManager.classLoader);
    assertNotNull(actualDefaultInstanceManager.containerClassLoader);
    assertEquals(0, actualDefaultInstanceManager.getAnnotationCacheSize());
    assertFalse(actualDefaultInstanceManager.ignoreAnnotations);
    assertFalse(actualDefaultInstanceManager.metadataComplete);
    assertFalse(actualDefaultInstanceManager.privileged);
    Log expectedLogger = catalinaContext.logger;
    assertSame(expectedLogger, catalinaContext.getLogger());
  }

  /**
   * Test {@link DefaultInstanceManager#newInstance(Class)} with {@code clazz}.
   * <p>
   * Method under test: {@link DefaultInstanceManager#newInstance(Class)}
   */
  @Test
  public void testNewInstanceWithClazz() throws IllegalAccessException, IllegalArgumentException,
      InstantiationException, NoSuchMethodException, SecurityException, InvocationTargetException, NamingException {
    // Arrange
    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();
    DefaultInstanceManager defaultInstanceManager = new DefaultInstanceManager(null, injectionMap, catalinaContext,
        new ParallelWebappClassLoader());
    Class<Object> clazz = Object.class;

    // Act
    defaultInstanceManager.newInstance(clazz);

    // Assert
    assertEquals(1, defaultInstanceManager.getAnnotationCacheSize());
  }

  /**
   * Test {@link DefaultInstanceManager#newInstance(Object)} with {@code o}.
   * <p>
   * Method under test: {@link DefaultInstanceManager#newInstance(Object)}
   */
  @Test
  public void testNewInstanceWithO() throws IllegalAccessException, InvocationTargetException, NamingException {
    // Arrange
    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());
    SelectorContext context = new SelectorContext(new Hashtable<>());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();
    DefaultInstanceManager defaultInstanceManager = new DefaultInstanceManager(context, injectionMap, catalinaContext,
        new ParallelWebappClassLoader());

    // Act
    defaultInstanceManager.newInstance((Object) "42");

    // Assert
    assertEquals(2, defaultInstanceManager.getAnnotationCacheSize());
  }

  /**
   * Test {@link DefaultInstanceManager#newInstance(Object)} with {@code o}.
   * <p>
   * Method under test: {@link DefaultInstanceManager#newInstance(Object)}
   */
  @Test
  public void testNewInstanceWithO2() throws IllegalAccessException, InvocationTargetException, NamingException {
    // Arrange
    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();
    DefaultInstanceManager defaultInstanceManager = new DefaultInstanceManager(null, injectionMap, catalinaContext,
        new ParallelWebappClassLoader());

    // Act
    defaultInstanceManager.newInstance((Object) "42");

    // Assert
    assertEquals(2, defaultInstanceManager.getAnnotationCacheSize());
  }

  /**
   * Test {@link DefaultInstanceManager#newInstance(Object)} with {@code o}.
   * <ul>
   *   <li>When one hundred forty-eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultInstanceManager#newInstance(Object)}
   */
  @Test
  public void testNewInstanceWithO_whenOneHundredFortyEight()
      throws IllegalAccessException, InvocationTargetException, NamingException {
    // Arrange
    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());
    SelectorContext context = new SelectorContext(new Hashtable<>());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();
    DefaultInstanceManager defaultInstanceManager = new DefaultInstanceManager(context, injectionMap, catalinaContext,
        new ParallelWebappClassLoader());

    // Act
    defaultInstanceManager.newInstance(148);

    // Assert
    assertEquals(3, defaultInstanceManager.getAnnotationCacheSize());
  }

  /**
   * Test {@link DefaultInstanceManager#newInstance(Object)} with {@code o}.
   * <ul>
   *   <li>When {@link TreeMap#TreeMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultInstanceManager#newInstance(Object)}
   */
  @Test
  public void testNewInstanceWithO_whenTreeMap()
      throws IllegalAccessException, InvocationTargetException, NamingException {
    // Arrange
    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());
    SelectorContext context = new SelectorContext(new Hashtable<>());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();
    DefaultInstanceManager defaultInstanceManager = new DefaultInstanceManager(context, injectionMap, catalinaContext,
        new ParallelWebappClassLoader());

    // Act
    defaultInstanceManager.newInstance(new TreeMap<>());

    // Assert
    assertEquals(3, defaultInstanceManager.getAnnotationCacheSize());
  }

  /**
   * Test {@link DefaultInstanceManager#populateAnnotationsCache(Class, Map)}.
   * <p>
   * Method under test: {@link DefaultInstanceManager#populateAnnotationsCache(Class, Map)}
   */
  @Test
  public void testPopulateAnnotationsCache() throws IllegalAccessException, InvocationTargetException, NamingException {
    // Arrange
    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());
    SelectorContext context = new SelectorContext(new Hashtable<>());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();
    DefaultInstanceManager defaultInstanceManager = new DefaultInstanceManager(context, injectionMap, catalinaContext,
        new ParallelWebappClassLoader());
    Class<Object> clazz = Object.class;

    // Act
    defaultInstanceManager.populateAnnotationsCache(clazz, new HashMap<>());

    // Assert
    assertEquals(1, defaultInstanceManager.getAnnotationCacheSize());
  }

  /**
   * Test {@link DefaultInstanceManager#populateAnnotationsCache(Class, Map)}.
   * <p>
   * Method under test: {@link DefaultInstanceManager#populateAnnotationsCache(Class, Map)}
   */
  @Test
  public void testPopulateAnnotationsCache2()
      throws IllegalAccessException, InvocationTargetException, NamingException {
    // Arrange
    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();
    DefaultInstanceManager defaultInstanceManager = new DefaultInstanceManager(null, injectionMap, catalinaContext,
        new ParallelWebappClassLoader());
    Class<Object> clazz = Object.class;

    // Act
    defaultInstanceManager.populateAnnotationsCache(clazz, new HashMap<>());

    // Assert
    assertEquals(1, defaultInstanceManager.getAnnotationCacheSize());
  }

  /**
   * Test {@link DefaultInstanceManager#getAnnotationCacheSize()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultInstanceManager#getAnnotationCacheSize()}
   */
  @Test
  public void testGetAnnotationCacheSize_thenReturnZero() {
    // Arrange
    StandardContext catalinaContext = new StandardContext();
    catalinaContext.setLoader(new WebappLoader());
    SelectorContext context = new SelectorContext(new Hashtable<>());
    HashMap<String, Map<String, String>> injectionMap = new HashMap<>();

    // Act and Assert
    assertEquals(0,
        (new DefaultInstanceManager(context, injectionMap, catalinaContext, new ParallelWebappClassLoader()))
            .getAnnotationCacheSize());
  }
}
