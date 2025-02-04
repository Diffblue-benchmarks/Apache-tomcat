package org.apache.catalina.startup;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.junit.Test;

public class CatalinaBaseConfigurationSourceDiffblueTest {
  /**
   * Test {@link CatalinaBaseConfigurationSource#getServerXml()}.
   * <ul>
   *   <li>Then return InputStream read is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getServerXml()}
   */
  @Test
  public void testGetServerXml_thenReturnInputStreamReadIsMinusOne() throws IOException {
    // Arrange and Act
    Resource actualServerXml = (new CatalinaBaseConfigurationSource(
        Paths.get(System.getProperty("java.io.tmpdir"), CatalinaBaseConfigurationSource.LEGACY_SERVER_EMBED_XML)
            .toFile(),
        Catalina.SERVER_XML)).getServerXml();

    // Assert
    assertEquals(-1, actualServerXml.getInputStream().read(new byte[]{}));
    assertEquals(1738358541121L, actualServerXml.getLastModified());
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "conf", "server.xml").toString());
    assertEquals(expectedToStringResult, actualServerXml.getURI().toString());
  }

  /**
   * Test {@link CatalinaBaseConfigurationSource#getServerXml()}.
   * <ul>
   *   <li>Then return URI toString is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getServerXml()}
   */
  @Test
  public void testGetServerXml_thenReturnUriToStringIsFile() throws IOException {
    // Arrange and Act
    Resource actualServerXml = (new CatalinaBaseConfigurationSource(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "file:/")).getServerXml();

    // Assert
    assertEquals("file:/", actualServerXml.getURI().toString());
    assertEquals(1738232189423L, actualServerXml.getLastModified());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualServerXml.getInputStream().read(byteArray));
    assertArrayEquals("bin\nboot\ndev\netc\nhome\nlib\nlib64\nlost+found\nmedia\nmn".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link CatalinaBaseConfigurationSource#getServerXml()}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getServerXml()}
   */
  @Test
  public void testGetServerXml_thenThrowIOException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class,
        () -> (new CatalinaBaseConfigurationSource(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
            "Server Xml Path")).getServerXml());
  }

  /**
   * Test {@link CatalinaBaseConfigurationSource#getResource(String)}.
   * <ul>
   *   <li>When {@code file:/}.</li>
   *   <li>Then return URI toString is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getResource(String)}
   */
  @Test
  public void testGetResource_whenFile_thenReturnUriToStringIsFile() throws IOException {
    // Arrange and Act
    Resource actualResource = (new CatalinaBaseConfigurationSource(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "Server Xml Path")).getResource("file:/");

    // Assert
    assertEquals("file:/", actualResource.getURI().toString());
    assertEquals(1738232189423L, actualResource.getLastModified());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualResource.getInputStream().read(byteArray));
    assertArrayEquals("bin\nboot\ndev\netc\nhome\nlib\nlib64\nlost+found\nmedia\nmn".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link CatalinaBaseConfigurationSource#getURI(String)}.
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getURI(String)}
   */
  @Test
  public void testGetURI() {
    // Arrange and Act
    URI actualURI = (new CatalinaBaseConfigurationSource(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "Server Xml Path")).getURI("Name");

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt", "Name").toString());
    assertEquals(expectedToStringResult, actualURI.toString());
  }

  /**
   * Test {@link CatalinaBaseConfigurationSource#getURI(String)}.
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getURI(String)}
   */
  @Test
  public void testGetURI2() {
    // Arrange and Act
    URI actualURI = (new CatalinaBaseConfigurationSource(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "Server Xml Path"))
        .getURI("catalinaConfigurationSource.cannotObtainURL");

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt", "catalinaConfigurationSource.cannotObtainURL")
            .toString());
    assertEquals(expectedToStringResult, actualURI.toString());
  }

  /**
   * Test {@link CatalinaBaseConfigurationSource#getURI(String)}.
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getURI(String)}
   */
  @Test
  public void testGetURI3() {
    // Arrange and Act
    URI actualURI = (new CatalinaBaseConfigurationSource(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "Server Xml Path"))
        .getURI(Catalina.SERVER_XML);

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt", "conf", "server.xml").toString());
    assertEquals(expectedToStringResult, actualURI.toString());
  }

  /**
   * Test {@link CatalinaBaseConfigurationSource#getURI(String)}.
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getURI(String)}
   */
  @Test
  public void testGetURI4() {
    // Arrange and Act
    URI actualURI = (new CatalinaBaseConfigurationSource(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "Server Xml Path"))
        .getURI(CatalinaBaseConfigurationSource.LEGACY_SERVER_EMBED_XML);

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths
            .get(System.getProperty("java.io.tmpdir"), "test.txt",
                CatalinaBaseConfigurationSource.LEGACY_SERVER_EMBED_XML)
            .toString());
    assertEquals(expectedToStringResult, actualURI.toString());
  }

  /**
   * Test {@link CatalinaBaseConfigurationSource#getURI(String)}.
   * <ul>
   *   <li>When {@code file:/}.</li>
   *   <li>Then return toString is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CatalinaBaseConfigurationSource#getURI(String)}
   */
  @Test
  public void testGetURI_whenFile_thenReturnToStringIsFile() {
    // Arrange, Act and Assert
    assertEquals("file:/",
        (new CatalinaBaseConfigurationSource(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
            "Server Xml Path")).getURI("file:/").toString());
  }
}
