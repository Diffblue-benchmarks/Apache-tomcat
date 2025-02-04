package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ContextNameDiffblueTest {
  /**
   * Test {@link ContextName#ContextName(String, boolean)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then return DisplayName is {@code /Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, boolean)}
   */
  @Test
  public void testNewContextName_whenName_thenReturnDisplayNameIsName() {
    // Arrange and Act
    ContextName actualContextName = new ContextName("Name", true);

    // Assert
    assertEquals("/Name", actualContextName.getDisplayName());
    assertEquals("/Name", actualContextName.getName());
    assertEquals("/Name", actualContextName.getPath());
    assertEquals("Name", actualContextName.getBaseName());
  }

  /**
   * Test {@link ContextName#ContextName(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, String)}
   */
  @Test
  public void testNewContextName_whenNull_thenReturnNameIsEmptyString() {
    // Arrange and Act
    ContextName actualContextName = new ContextName(null, null);

    // Assert
    assertEquals("", actualContextName.getName());
    assertEquals("", actualContextName.getPath());
    assertEquals("", actualContextName.getVersion());
    assertEquals("/", actualContextName.getDisplayName());
    assertEquals(ContextName.ROOT_NAME, actualContextName.getBaseName());
  }

  /**
   * Test {@link ContextName#ContextName(String, boolean)}.
   * <ul>
   *   <li>When {@code ##}.</li>
   *   <li>Then return Name is {@code ##}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, boolean)}
   */
  @Test
  public void testNewContextName_whenNumberSignNumberSign_thenReturnNameIsNumberSignNumberSign() {
    // Arrange and Act
    ContextName actualContextName = new ContextName("##", true);

    // Assert
    assertEquals("", actualContextName.getPath());
    assertEquals("##", actualContextName.getName());
    assertEquals("/", actualContextName.getDisplayName());
    assertEquals("ROOT##", actualContextName.getBaseName());
  }

  /**
   * Test {@link ContextName#ContextName(String, String)}.
   * <ul>
   *   <li>When {@code Path}.</li>
   *   <li>Then return Version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, String)}
   */
  @Test
  public void testNewContextName_whenPath_thenReturnVersionIs102() {
    // Arrange and Act
    ContextName actualContextName = new ContextName("Path", "1.0.2");

    // Assert
    assertEquals("1.0.2", actualContextName.getVersion());
    assertEquals("Path", actualContextName.getPath());
    assertEquals("Path##1.0.2", actualContextName.getDisplayName());
    assertEquals("Path##1.0.2", actualContextName.getName());
    assertEquals("ath##1.0.2", actualContextName.getBaseName());
  }

  /**
   * Test {@link ContextName#ContextName(String, String)}.
   * <ul>
   *   <li>When {@code /ROOT}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, String)}
   */
  @Test
  public void testNewContextName_whenRoot_thenReturnNameIsEmptyString() {
    // Arrange and Act
    ContextName actualContextName = new ContextName("/ROOT", null);

    // Assert
    assertEquals("", actualContextName.getName());
    assertEquals("", actualContextName.getPath());
    assertEquals("", actualContextName.getVersion());
    assertEquals("/", actualContextName.getDisplayName());
    assertEquals(ContextName.ROOT_NAME, actualContextName.getBaseName());
  }

  /**
   * Test {@link ContextName#ContextName(String, String)}.
   * <ul>
   *   <li>When {@code /}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, String)}
   */
  @Test
  public void testNewContextName_whenSlash_thenReturnNameIsEmptyString() {
    // Arrange and Act
    ContextName actualContextName = new ContextName("/", null);

    // Assert
    assertEquals("", actualContextName.getName());
    assertEquals("", actualContextName.getPath());
    assertEquals("", actualContextName.getVersion());
    assertEquals("/", actualContextName.getDisplayName());
    assertEquals(ContextName.ROOT_NAME, actualContextName.getBaseName());
  }

  /**
   * Test {@link ContextName#ContextName(String, boolean)}.
   * <ul>
   *   <li>When {@code /}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, boolean)}
   */
  @Test
  public void testNewContextName_whenSlash_thenReturnNameIsEmptyString2() {
    // Arrange and Act
    ContextName actualContextName = new ContextName("/", false);

    // Assert
    assertEquals("", actualContextName.getName());
    assertEquals("", actualContextName.getPath());
    assertEquals("/", actualContextName.getDisplayName());
    assertEquals(ContextName.ROOT_NAME, actualContextName.getBaseName());
  }

  /**
   * Test {@link ContextName#ContextName(String, boolean)}.
   * <ul>
   *   <li>When {@code .war}.</li>
   *   <li>Then return BaseName is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, boolean)}
   */
  @Test
  public void testNewContextName_whenWar_thenReturnBaseNameIsEmptyString() {
    // Arrange and Act
    ContextName actualContextName = new ContextName(".war", true);

    // Assert
    assertEquals("", actualContextName.getBaseName());
    assertEquals("/", actualContextName.getDisplayName());
    assertEquals("/", actualContextName.getName());
    assertEquals("/", actualContextName.getPath());
  }

  /**
   * Test {@link ContextName#ContextName(String, boolean)}.
   * <ul>
   *   <li>When {@code .xml}.</li>
   *   <li>Then return BaseName is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#ContextName(String, boolean)}
   */
  @Test
  public void testNewContextName_whenXml_thenReturnBaseNameIsEmptyString() {
    // Arrange and Act
    ContextName actualContextName = new ContextName(".xml", true);

    // Assert
    assertEquals("", actualContextName.getBaseName());
    assertEquals("/", actualContextName.getDisplayName());
    assertEquals("/", actualContextName.getName());
    assertEquals("/", actualContextName.getPath());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ContextName#toString()}
   *   <li>{@link ContextName#getBaseName()}
   *   <li>{@link ContextName#getName()}
   *   <li>{@link ContextName#getPath()}
   *   <li>{@link ContextName#getVersion()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    ContextName extractFromPathResult = ContextName.extractFromPath("Path");

    // Act
    String actualToStringResult = extractFromPathResult.toString();
    String actualBaseName = extractFromPathResult.getBaseName();
    String actualName = extractFromPathResult.getName();
    String actualPath = extractFromPathResult.getPath();

    // Assert
    assertEquals("", extractFromPathResult.getVersion());
    assertEquals("/Path", actualName);
    assertEquals("/Path", actualPath);
    assertEquals("/Path", actualToStringResult);
    assertEquals("Path", actualBaseName);
  }

  /**
   * Test {@link ContextName#getDisplayName()}.
   * <ul>
   *   <li>Given {@link ContextName#ContextName(String, String)} with {@code /Path} and version is {@code 1.0.2}.</li>
   *   <li>Then return {@code /Path##1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#getDisplayName()}
   */
  @Test
  public void testGetDisplayName_givenContextNameWithPathAndVersionIs102_thenReturnPath102() {
    // Arrange, Act and Assert
    assertEquals("/Path##1.0.2", (new ContextName("/Path", "1.0.2")).getDisplayName());
  }

  /**
   * Test {@link ContextName#getDisplayName()}.
   * <ul>
   *   <li>Given extractFromPath empty string.</li>
   *   <li>Then return {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#getDisplayName()}
   */
  @Test
  public void testGetDisplayName_givenExtractFromPathEmptyString_thenReturnSlash() {
    // Arrange, Act and Assert
    assertEquals("/", ContextName.extractFromPath("").getDisplayName());
  }

  /**
   * Test {@link ContextName#getDisplayName()}.
   * <ul>
   *   <li>Given extractFromPath {@code Path}.</li>
   *   <li>Then return {@code /Path}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#getDisplayName()}
   */
  @Test
  public void testGetDisplayName_givenExtractFromPathPath_thenReturnPath() {
    // Arrange, Act and Assert
    assertEquals("/Path", ContextName.extractFromPath("Path").getDisplayName());
  }

  /**
   * Test {@link ContextName#extractFromPath(String)}.
   * <ul>
   *   <li>Then return Name is {@code ##}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#extractFromPath(String)}
   */
  @Test
  public void testExtractFromPath_thenReturnNameIsNumberSignNumberSign() {
    // Arrange and Act
    ContextName actualExtractFromPathResult = ContextName.extractFromPath("##");

    // Assert
    assertEquals("", actualExtractFromPathResult.getPath());
    assertEquals("##", actualExtractFromPathResult.getName());
    assertEquals("/", actualExtractFromPathResult.getDisplayName());
    assertEquals("ROOT##", actualExtractFromPathResult.getBaseName());
  }

  /**
   * Test {@link ContextName#extractFromPath(String)}.
   * <ul>
   *   <li>When {@code \}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#extractFromPath(String)}
   */
  @Test
  public void testExtractFromPath_whenBackslash_thenReturnNameIsEmptyString() {
    // Arrange and Act
    ContextName actualExtractFromPathResult = ContextName.extractFromPath("\\");

    // Assert
    assertEquals("", actualExtractFromPathResult.getName());
    assertEquals("", actualExtractFromPathResult.getPath());
    assertEquals("/", actualExtractFromPathResult.getDisplayName());
    assertEquals(ContextName.ROOT_NAME, actualExtractFromPathResult.getBaseName());
  }

  /**
   * Test {@link ContextName#extractFromPath(String)}.
   * <ul>
   *   <li>When {@code Path}.</li>
   *   <li>Then return DisplayName is {@code /Path}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#extractFromPath(String)}
   */
  @Test
  public void testExtractFromPath_whenPath_thenReturnDisplayNameIsPath() {
    // Arrange and Act
    ContextName actualExtractFromPathResult = ContextName.extractFromPath("Path");

    // Assert
    assertEquals("/Path", actualExtractFromPathResult.getDisplayName());
    assertEquals("/Path", actualExtractFromPathResult.getName());
    assertEquals("/Path", actualExtractFromPathResult.getPath());
    assertEquals("Path", actualExtractFromPathResult.getBaseName());
  }

  /**
   * Test {@link ContextName#extractFromPath(String)}.
   * <ul>
   *   <li>When {@code /ROOT}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#extractFromPath(String)}
   */
  @Test
  public void testExtractFromPath_whenRoot_thenReturnNameIsEmptyString() {
    // Arrange and Act
    ContextName actualExtractFromPathResult = ContextName.extractFromPath("/ROOT");

    // Assert
    assertEquals("", actualExtractFromPathResult.getName());
    assertEquals("", actualExtractFromPathResult.getPath());
    assertEquals("/", actualExtractFromPathResult.getDisplayName());
    assertEquals(ContextName.ROOT_NAME, actualExtractFromPathResult.getBaseName());
  }

  /**
   * Test {@link ContextName#extractFromPath(String)}.
   * <ul>
   *   <li>When {@code \/ROOT}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#extractFromPath(String)}
   */
  @Test
  public void testExtractFromPath_whenRoot_thenReturnNameIsEmptyString2() {
    // Arrange and Act
    ContextName actualExtractFromPathResult = ContextName.extractFromPath("\\/ROOT");

    // Assert
    assertEquals("", actualExtractFromPathResult.getName());
    assertEquals("", actualExtractFromPathResult.getPath());
    assertEquals("/", actualExtractFromPathResult.getDisplayName());
    assertEquals(ContextName.ROOT_NAME, actualExtractFromPathResult.getBaseName());
  }

  /**
   * Test {@link ContextName#extractFromPath(String)}.
   * <ul>
   *   <li>When {@code .war}.</li>
   *   <li>Then return BaseName is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#extractFromPath(String)}
   */
  @Test
  public void testExtractFromPath_whenWar_thenReturnBaseNameIsEmptyString() {
    // Arrange and Act
    ContextName actualExtractFromPathResult = ContextName.extractFromPath(".war");

    // Assert
    assertEquals("", actualExtractFromPathResult.getBaseName());
    assertEquals("/", actualExtractFromPathResult.getDisplayName());
    assertEquals("/", actualExtractFromPathResult.getName());
    assertEquals("/", actualExtractFromPathResult.getPath());
  }

  /**
   * Test {@link ContextName#extractFromPath(String)}.
   * <ul>
   *   <li>When {@code .xml}.</li>
   *   <li>Then return BaseName is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContextName#extractFromPath(String)}
   */
  @Test
  public void testExtractFromPath_whenXml_thenReturnBaseNameIsEmptyString() {
    // Arrange and Act
    ContextName actualExtractFromPathResult = ContextName.extractFromPath(".xml");

    // Assert
    assertEquals("", actualExtractFromPathResult.getBaseName());
    assertEquals("/", actualExtractFromPathResult.getDisplayName());
    assertEquals("/", actualExtractFromPathResult.getName());
    assertEquals("/", actualExtractFromPathResult.getPath());
  }
}
