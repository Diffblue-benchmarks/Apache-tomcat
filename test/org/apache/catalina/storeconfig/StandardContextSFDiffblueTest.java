package org.apache.catalina.storeconfig;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import org.apache.catalina.Context;
import org.apache.catalina.core.StandardContext;
import org.junit.Test;

public class StandardContextSFDiffblueTest {
  /**
   * Test {@link StandardContextSF#storeContextSeparate(PrintWriter, int, StandardContext)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContextSF#storeContextSeparate(PrintWriter, int, StandardContext)}
   */
  @Test
  public void testStoreContextSeparate_thenThrowIOException() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();
    PrintWriter aWriter = new PrintWriter(new StringWriter());

    StandardContext aContext = new StandardContext();
    aContext.setConfigFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act and Assert
    assertThrows(IOException.class, () -> standardContextSF.storeContextSeparate(aWriter, 1, aContext));
  }

  /**
   * Test {@link StandardContextSF#storeWithBackup(StandardContext)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContextSF#storeWithBackup(StandardContext)}
   */
  @Test
  public void testStoreWithBackup_thenThrowIOException() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();
    standardContextSF.setRegistry(new StoreRegistry());

    StandardContext aContext = new StandardContext();
    aContext.setConfigFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act and Assert
    assertThrows(IOException.class, () -> standardContextSF.storeWithBackup(aContext));
  }

  /**
   * Test {@link StandardContextSF#getConfigFileWriter(Context)}.
   * <ul>
   *   <li>Then return Basename is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContextSF#getConfigFileWriter(Context)}
   */
  @Test
  public void testGetConfigFileWriter_thenReturnBasenameIsEmptyString() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();
    standardContextSF.setRegistry(new StoreRegistry());

    StandardContext context = new StandardContext();
    context.setConfigFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act
    StoreFileMover actualConfigFileWriter = standardContextSF.getConfigFileWriter(context);

    // Assert
    assertEquals("", actualConfigFileWriter.getBasename());
    assertEquals("UTF-8", actualConfigFileWriter.getEncoding());
    File configOld = actualConfigFileWriter.getConfigOld();
    assertEquals("test.txt", configOld.getName());
    File configNew = actualConfigFileWriter.getConfigNew();
    assertEquals("test.txt.new", configNew.getName());
    assertTrue(configNew.isAbsolute());
    assertTrue(configOld.isAbsolute());
    assertTrue(actualConfigFileWriter.getConfigSave().isAbsolute());
    String expectedFilename = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString();
    assertEquals(expectedFilename, actualConfigFileWriter.getFilename());
  }

  /**
   * Test {@link StandardContextSF#getConfigFileWriter(Context)}.
   * <ul>
   *   <li>When {@link StandardContext} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContextSF#getConfigFileWriter(Context)}
   */
  @Test
  public void testGetConfigFileWriter_whenStandardContext_thenReturnNull() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();

    // Act and Assert
    assertNull(standardContextSF.getConfigFileWriter(new StandardContext()));
  }

  /**
   * Test {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}.
   * <p>
   * Method under test: {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}
   */
  @Test
  public void testFilterWatchedResources() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();
    StandardContext context = new StandardContext();

    // Act and Assert
    assertEquals(0, standardContextSF.filterWatchedResources(context,
        new String[]{Paths.get(System.getProperty("user.dir"), "conf", "context.xml").toString()}).length);
  }

  /**
   * Test {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}.
   * <p>
   * Method under test: {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}
   */
  @Test
  public void testFilterWatchedResources2() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();

    StandardContext context = new StandardContext();
    context.setConfigFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act and Assert
    assertArrayEquals(new String[]{"Wresources"},
        standardContextSF.filterWatchedResources(context, new String[]{"Wresources"}));
  }

  /**
   * Test {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}.
   * <p>
   * Method under test: {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}
   */
  @Test
  public void testFilterWatchedResources3() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();
    StandardContext context = new StandardContext();

    // Act and Assert
    assertEquals(0, standardContextSF.filterWatchedResources(context,
        new String[]{Paths.get(System.getProperty("user.dir"), "conf", "web.xml").toString()}).length);
  }

  /**
   * Test {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}.
   * <ul>
   *   <li>Then return array of {@link String} with {@code Wresources}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}
   */
  @Test
  public void testFilterWatchedResources_thenReturnArrayOfStringWithWresources() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();

    // Act and Assert
    assertArrayEquals(new String[]{"Wresources"},
        standardContextSF.filterWatchedResources(new StandardContext(), new String[]{"Wresources"}));
  }

  /**
   * Test {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code WEB-INF/tomcat-web.xml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}
   */
  @Test
  public void testFilterWatchedResources_whenArrayOfStringWithWebInfTomcatWebXml() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();

    // Act and Assert
    assertEquals(0,
        standardContextSF.filterWatchedResources(new StandardContext(), new String[]{"WEB-INF/tomcat-web.xml"}).length);
  }

  /**
   * Test {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}.
   * <ul>
   *   <li>When array of {@link String} with {@code WEB-INF/web.xml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardContextSF#filterWatchedResources(StandardContext, String[])}
   */
  @Test
  public void testFilterWatchedResources_whenArrayOfStringWithWebInfWebXml() throws Exception {
    // Arrange
    StandardContextSF standardContextSF = new StandardContextSF();

    // Act and Assert
    assertEquals(0,
        standardContextSF.filterWatchedResources(new StandardContext(), new String[]{"WEB-INF/web.xml"}).length);
  }

  /**
   * Test new {@link StandardContextSF} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StandardContextSF}
   */
  @Test
  public void testNewStandardContextSF() {
    // Arrange and Act
    StandardContextSF actualStandardContextSF = new StandardContextSF();

    // Assert
    assertEquals("org.apache.catalina.config.StoreFactoryBase/1.0", actualStandardContextSF.getInfo());
    assertNull(actualStandardContextSF.getRegistry());
  }
}
