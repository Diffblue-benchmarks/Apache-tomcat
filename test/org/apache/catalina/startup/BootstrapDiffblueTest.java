package org.apache.catalina.startup;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;

public class BootstrapDiffblueTest {
  /**
   * Test {@link Bootstrap#replace(String)}.
   * <ul>
   *   <li>When {@code ${}.</li>
   *   <li>Then return {@code ${}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Bootstrap#replace(String)}
   */
  @Test
  public void testReplace_whenDollarSignLeftCurlyBracket_thenReturnDollarSignLeftCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("${", (new Bootstrap()).replace("${"));
  }

  /**
   * Test {@link Bootstrap#replace(String)}.
   * <ul>
   *   <li>When {@code Str}.</li>
   *   <li>Then return {@code Str}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Bootstrap#replace(String)}
   */
  @Test
  public void testReplace_whenStr_thenReturnStr() {
    // Arrange, Act and Assert
    assertEquals("Str", (new Bootstrap()).replace("Str"));
  }

  /**
   * Test {@link Bootstrap#getCatalinaHome()}.
   * <p>
   * Method under test: {@link Bootstrap#getCatalinaHome()}
   */
  @Test
  public void testGetCatalinaHome() {
    // Arrange, Act and Assert
    assertEquals(System.getProperty("user.dir"), Bootstrap.getCatalinaHome());
  }

  /**
   * Test {@link Bootstrap#getCatalinaBase()}.
   * <p>
   * Method under test: {@link Bootstrap#getCatalinaBase()}
   */
  @Test
  public void testGetCatalinaBase() {
    // Arrange, Act and Assert
    assertEquals(System.getProperty("user.dir"), Bootstrap.getCatalinaBase());
  }

  /**
   * Test {@link Bootstrap#unwrapInvocationTargetException(Throwable)}.
   * <p>
   * Method under test: {@link Bootstrap#unwrapInvocationTargetException(Throwable)}
   */
  @Test
  public void testUnwrapInvocationTargetException() {
    // Arrange and Act
    Throwable actualUnwrapInvocationTargetExceptionResult = Bootstrap.unwrapInvocationTargetException(new Throwable());

    // Assert
    assertNull(actualUnwrapInvocationTargetExceptionResult.getLocalizedMessage());
    assertNull(actualUnwrapInvocationTargetExceptionResult.getMessage());
    assertNull(actualUnwrapInvocationTargetExceptionResult.getCause());
    assertEquals(0, actualUnwrapInvocationTargetExceptionResult.getSuppressed().length);
  }

  /**
   * Test {@link Bootstrap#getPaths(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return array of {@link String} with {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Bootstrap#getPaths(String)}
   */
  @Test
  public void testGetPaths_when42_thenReturnArrayOfStringWith42() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[]{"42"}, Bootstrap.getPaths("42"));
  }

  /**
   * Test {@link Bootstrap#getPaths(String)}.
   * <ul>
   *   <li>When {@code "42}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Bootstrap#getPaths(String)}
   */
  @Test
  public void testGetPaths_when42_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Bootstrap.getPaths("\"42"));
  }

  /**
   * Test {@link Bootstrap#getPaths(String)}.
   * <ul>
   *   <li>When {@code "}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Bootstrap#getPaths(String)}
   */
  @Test
  public void testGetPaths_whenQuotationMark_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Bootstrap.getPaths("\""));
  }

  /**
   * Test {@link Bootstrap#getPaths(String)}.
   * <ul>
   *   <li>When {@code "U"}.</li>
   *   <li>Then return array of {@link String} with {@code U}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Bootstrap#getPaths(String)}
   */
  @Test
  public void testGetPaths_whenU_thenReturnArrayOfStringWithU() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[]{"U"}, Bootstrap.getPaths("\"U\""));
  }

  /**
   * Test {@link Bootstrap#getPaths(String)}.
   * <ul>
   *   <li>When {@code ""U"}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Bootstrap#getPaths(String)}
   */
  @Test
  public void testGetPaths_whenU_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Bootstrap.getPaths("\"\"U\""));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Bootstrap}
   *   <li>{@link Bootstrap#destroy()}
   *   <li>{@link Bootstrap#getCatalinaBaseFile()}
   *   <li>{@link Bootstrap#getCatalinaHomeFile()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    Bootstrap actualBootstrap = new Bootstrap();
    actualBootstrap.destroy();
    File actualCatalinaBaseFile = actualBootstrap.getCatalinaBaseFile();
    File actualCatalinaHomeFile = actualBootstrap.getCatalinaHomeFile();

    // Assert
    assertEquals("Apache-tomcat", actualCatalinaBaseFile.getName());
    assertTrue(actualCatalinaBaseFile.isAbsolute());
    assertSame(actualCatalinaBaseFile, actualCatalinaHomeFile);
  }
}
