package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.Loader;
import org.apache.catalina.loader.WebappLoader;
import org.junit.Test;

public class LoaderSFDiffblueTest {
  /**
   * Test {@link LoaderSF#isDefaultLoader(Loader)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link WebappLoader} (default constructor) Delegate is {@code false}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoaderSF#isDefaultLoader(Loader)}
   */
  @Test
  public void testIsDefaultLoader_givenFalse_whenWebappLoaderDelegateIsFalse_thenReturnTrue() {
    // Arrange
    LoaderSF loaderSF = new LoaderSF();

    WebappLoader loader = new WebappLoader();
    loader.setDelegate(false);
    loader.setLoaderClass("org.apache.catalina.loader.WebappClassLoader");

    // Act and Assert
    assertTrue(loaderSF.isDefaultLoader(loader));
  }

  /**
   * Test {@link LoaderSF#isDefaultLoader(Loader)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link WebappLoader} (default constructor) Delegate is {@code true}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoaderSF#isDefaultLoader(Loader)}
   */
  @Test
  public void testIsDefaultLoader_givenTrue_whenWebappLoaderDelegateIsTrue_thenReturnFalse() {
    // Arrange
    LoaderSF loaderSF = new LoaderSF();

    WebappLoader loader = new WebappLoader();
    loader.setDelegate(true);

    // Act and Assert
    assertFalse(loaderSF.isDefaultLoader(loader));
  }

  /**
   * Test {@link LoaderSF#isDefaultLoader(Loader)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoaderSF#isDefaultLoader(Loader)}
   */
  @Test
  public void testIsDefaultLoader_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new LoaderSF()).isDefaultLoader(null));
  }

  /**
   * Test {@link LoaderSF#isDefaultLoader(Loader)}.
   * <ul>
   *   <li>When {@link WebappLoader} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoaderSF#isDefaultLoader(Loader)}
   */
  @Test
  public void testIsDefaultLoader_whenWebappLoader_thenReturnFalse() {
    // Arrange
    LoaderSF loaderSF = new LoaderSF();

    // Act and Assert
    assertFalse(loaderSF.isDefaultLoader(new WebappLoader()));
  }

  /**
   * Test new {@link LoaderSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link LoaderSF}
   */
  @Test
  public void testNewLoaderSF() {
    // Arrange and Act
    LoaderSF actualLoaderSF = new LoaderSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualLoaderSF.getInfo());
    assertNull(actualLoaderSF.getRegistry());
  }
}
