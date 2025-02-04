package org.apache.catalina.loader;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class ParallelWebappClassLoaderDiffblueTest {
  /**
   * Test {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.
   * <p>
   * Method under test: {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}
   */
  @Test
  public void testNewParallelWebappClassLoader() {
    // Arrange, Act and Assert
    assertNotNull(new ParallelWebappClassLoader());
  }

  /**
   * Test {@link ParallelWebappClassLoader#ParallelWebappClassLoader(ClassLoader)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParallelWebappClassLoader#ParallelWebappClassLoader(ClassLoader)}
   */
  @Test
  public void testNewParallelWebappClassLoader_whenNull() {
    // Arrange, Act and Assert
    assertNotNull(new ParallelWebappClassLoader(null));
  }

  /**
   * Test {@link ParallelWebappClassLoader#ParallelWebappClassLoader(ClassLoader)}.
   * <ul>
   *   <li>When {@link ParallelWebappClassLoader#ParallelWebappClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParallelWebappClassLoader#ParallelWebappClassLoader(ClassLoader)}
   */
  @Test
  public void testNewParallelWebappClassLoader_whenParallelWebappClassLoader() {
    // Arrange, Act and Assert
    assertNotNull(new ParallelWebappClassLoader(new ParallelWebappClassLoader()));
  }
}
