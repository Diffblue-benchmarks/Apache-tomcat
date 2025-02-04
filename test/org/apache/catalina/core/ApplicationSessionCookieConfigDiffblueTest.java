package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ApplicationSessionCookieConfigDiffblueTest {
  /**
   * Test {@link ApplicationSessionCookieConfig#ApplicationSessionCookieConfig(StandardContext)}.
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#ApplicationSessionCookieConfig(StandardContext)}
   */
  @Test
  public void testNewApplicationSessionCookieConfig() {
    // Arrange and Act
    ApplicationSessionCookieConfig actualApplicationSessionCookieConfig = new ApplicationSessionCookieConfig(
        new StandardContext());

    // Assert
    assertNull(actualApplicationSessionCookieConfig.getComment());
    assertNull(actualApplicationSessionCookieConfig.getDomain());
    assertNull(actualApplicationSessionCookieConfig.getName());
    assertNull(actualApplicationSessionCookieConfig.getPath());
    assertEquals(-1, actualApplicationSessionCookieConfig.getMaxAge());
    assertTrue(actualApplicationSessionCookieConfig.getAttributes().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ApplicationSessionCookieConfig#getComment()}
   *   <li>{@link ApplicationSessionCookieConfig#getName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ApplicationSessionCookieConfig applicationSessionCookieConfig = new ApplicationSessionCookieConfig(
        new StandardContext());

    // Act
    String actualComment = applicationSessionCookieConfig.getComment();

    // Assert
    assertNull(actualComment);
    assertNull(applicationSessionCookieConfig.getName());
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#getDomain()}.
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#getDomain()}
   */
  @Test
  public void testGetDomain() {
    // Arrange, Act and Assert
    assertNull((new ApplicationSessionCookieConfig(new StandardContext())).getDomain());
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#getMaxAge()}.
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#getMaxAge()}
   */
  @Test
  public void testGetMaxAge() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ApplicationSessionCookieConfig(new StandardContext())).getMaxAge());
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#getPath()}.
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#getPath()}
   */
  @Test
  public void testGetPath() {
    // Arrange, Act and Assert
    assertNull((new ApplicationSessionCookieConfig(new StandardContext())).getPath());
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#isHttpOnly()}.
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#isHttpOnly()}
   */
  @Test
  public void testIsHttpOnly() {
    // Arrange, Act and Assert
    assertFalse((new ApplicationSessionCookieConfig(new StandardContext())).isHttpOnly());
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#isSecure()}.
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#isSecure()}
   */
  @Test
  public void testIsSecure() {
    // Arrange, Act and Assert
    assertFalse((new ApplicationSessionCookieConfig(new StandardContext())).isSecure());
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#setComment(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#setComment(String)}
   */
  @Test
  public void testSetComment_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ApplicationSessionCookieConfig(new StandardContext())).setComment("Comment"));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#setDomain(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#setDomain(String)}
   */
  @Test
  public void testSetDomain_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ApplicationSessionCookieConfig(new StandardContext())).setDomain("Domain"));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#setHttpOnly(boolean)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#setHttpOnly(boolean)}
   */
  @Test
  public void testSetHttpOnly_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ApplicationSessionCookieConfig(new StandardContext())).setHttpOnly(true));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#setMaxAge(int)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#setMaxAge(int)}
   */
  @Test
  public void testSetMaxAge_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ApplicationSessionCookieConfig(new StandardContext())).setMaxAge(3));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#setName(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#setName(String)}
   */
  @Test
  public void testSetName_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ApplicationSessionCookieConfig(new StandardContext())).setName("Name"));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#setPath(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#setPath(String)}
   */
  @Test
  public void testSetPath_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ApplicationSessionCookieConfig(new StandardContext())).setPath("Path"));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#setSecure(boolean)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#setSecure(boolean)}
   */
  @Test
  public void testSetSecure_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ApplicationSessionCookieConfig(new StandardContext())).setSecure(true));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#setAttribute(String, String)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#setAttribute(String, String)}
   */
  @Test
  public void testSetAttribute_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new ApplicationSessionCookieConfig(new StandardContext())).setAttribute("Name", "42"));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#getAttribute(String)}.
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#getAttribute(String)}
   */
  @Test
  public void testGetAttribute() {
    // Arrange, Act and Assert
    assertNull((new ApplicationSessionCookieConfig(new StandardContext())).getAttribute("Name"));
  }

  /**
   * Test {@link ApplicationSessionCookieConfig#getAttributes()}.
   * <p>
   * Method under test: {@link ApplicationSessionCookieConfig#getAttributes()}
   */
  @Test
  public void testGetAttributes() {
    // Arrange, Act and Assert
    assertTrue((new ApplicationSessionCookieConfig(new StandardContext())).getAttributes().isEmpty());
  }
}
