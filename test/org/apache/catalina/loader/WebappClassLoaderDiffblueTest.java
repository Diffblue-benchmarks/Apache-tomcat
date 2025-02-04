package org.apache.catalina.loader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class WebappClassLoaderDiffblueTest {
  /**
   * Test {@link WebappClassLoader#WebappClassLoader()}.
   * <p>
   * Method under test: {@link WebappClassLoader#WebappClassLoader()}
   */
  @Test
  public void testNewWebappClassLoader() {
    // Arrange, Act and Assert
    assertNotNull(new WebappClassLoader());
  }

  /**
   * Test {@link WebappClassLoader#WebappClassLoader(ClassLoader)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoader#WebappClassLoader(ClassLoader)}
   */
  @Test
  public void testNewWebappClassLoader_whenNull() {
    // Arrange, Act and Assert
    assertNotNull(new WebappClassLoader(null));
  }

  /**
   * Test {@link WebappClassLoader#WebappClassLoader(ClassLoader)}.
   * <ul>
   *   <li>When {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappClassLoader#WebappClassLoader(ClassLoader)}
   */
  @Test
  public void testNewWebappClassLoader_whenParallelWebappClassLoader() {
    // Arrange, Act and Assert
    assertNotNull(new WebappClassLoader(new ParallelWebappClassLoader()));
  }

  /**
   * Test {@link WebappClassLoader#getClassLoadingLock(String)}.
   * <p>
   * Method under test: {@link WebappClassLoader#getClassLoadingLock(String)}
   */
  @Test
  public void testGetClassLoadingLock() {
    // Arrange
    WebappClassLoader webappClassLoader = new WebappClassLoader();

    // Act and Assert
    assertSame(webappClassLoader, webappClassLoader.getClassLoadingLock("Class Name"));
  }
}
