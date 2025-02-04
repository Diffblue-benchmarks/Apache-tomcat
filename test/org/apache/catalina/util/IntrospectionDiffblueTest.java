package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.apache.catalina.Context;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.junit.Test;

public class IntrospectionDiffblueTest {
  /**
   * Test {@link Introspection#loadClass(Context, String)}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor) LoaderInstance is {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Introspection#loadClass(Context, String)}
   */
  @Test
  public void testLoadClass_givenWebappLoaderLoaderInstanceIsParallelWebappClassLoader() {
    // Arrange
    WebappLoader loader = new WebappLoader();
    loader.setLoaderInstance(new ParallelWebappClassLoader());

    StandardContext context = new StandardContext();
    context.setLoader(loader);

    // Act
    Class<?> actualLoadClassResult = Introspection.loadClass(context, "Class Name");

    // Assert
    assertNull(actualLoadClassResult);
  }

  /**
   * Test {@link Introspection#loadClass(Context, String)}.
   * <ul>
   *   <li>Given {@link WebappLoader} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Introspection#loadClass(Context, String)}
   */
  @Test
  public void testLoadClass_givenWebappLoader_thenReturnNull() {
    // Arrange
    StandardContext context = new StandardContext();
    context.setLoader(new WebappLoader());

    // Act
    Class<?> actualLoadClassResult = Introspection.loadClass(context, "Class Name");

    // Assert
    assertNull(actualLoadClassResult);
  }

  /**
   * Test {@link Introspection#convertPrimitiveType(Class)}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Introspection#convertPrimitiveType(Class)}
   */
  @Test
  public void testConvertPrimitiveType_whenJavaLangObject_thenReturnObject() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act
    Class<?> actualConvertPrimitiveTypeResult = Introspection.convertPrimitiveType(clazz);

    // Assert
    Class<Object> expectedConvertPrimitiveTypeResult = Object.class;
    assertEquals(expectedConvertPrimitiveTypeResult, actualConvertPrimitiveTypeResult);
  }
}
