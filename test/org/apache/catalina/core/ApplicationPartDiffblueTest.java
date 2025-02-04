package org.apache.catalina.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.util.FileItemHeadersImpl;
import org.junit.Test;

public class ApplicationPartDiffblueTest {
  /**
   * Test {@link ApplicationPart#getContentType()}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getContentType()}
   */
  @Test
  public void testGetContentType_thenReturnHttpsExampleOrgExample() {
    // Arrange
    DiskFileItem fileItem = new DiskFileItem("https://example.org/example", "https://example.org/example", true,
        "https://example.org/example", 3, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Act and Assert
    assertEquals("https://example.org/example",
        (new ApplicationPart(fileItem, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))
            .getContentType());
  }

  /**
   * Test {@link ApplicationPart#getHeader(String)}.
   * <p>
   * Method under test: {@link ApplicationPart#getHeader(String)}
   */
  @Test
  public void testGetHeader() {
    // Arrange
    DiskFileItem fileItem = new DiskFileItem("https://example.org/example", "https://example.org/example", true,
        "https://example.org/example", 3, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    fileItem.setHeaders(new FileItemHeadersImpl());

    // Act and Assert
    assertNull((new ApplicationPart(fileItem, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))
        .getHeader("Name"));
  }

  /**
   * Test {@link ApplicationPart#getHeader(String)}.
   * <ul>
   *   <li>Given Property is {@code java.io.tmpdir} is array of {@link String} with {@code test.txt} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getHeader(String)}
   */
  @Test
  public void testGetHeader_givenPropertyIsJavaIoTmpdirIsArrayOfStringWithTestTxtToFile() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertNull((new ApplicationPart(null, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))
        .getHeader("Name"));
  }

  /**
   * Test {@link ApplicationPart#getHeaderNames()}.
   * <ul>
   *   <li>Then return {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_thenReturnList() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    Collection<String> actualHeaderNames = (new ApplicationPart(null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())).getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof List);
    assertTrue(actualHeaderNames.isEmpty());
  }

  /**
   * Test {@link ApplicationPart#getHeaderNames()}.
   * <ul>
   *   <li>Then return {@link Set}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_thenReturnSet() {
    // Arrange
    DiskFileItem fileItem = new DiskFileItem("https://example.org/example", "https://example.org/example", true,
        "https://example.org/example", 3, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    fileItem.setHeaders(new FileItemHeadersImpl());

    // Act
    Collection<String> actualHeaderNames = (new ApplicationPart(fileItem,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())).getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof Set);
    assertTrue(actualHeaderNames.isEmpty());
  }

  /**
   * Test {@link ApplicationPart#getHeaderNames()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames_thenReturnSizeIsOne() {
    // Arrange
    FileItemHeadersImpl pHeaders = new FileItemHeadersImpl();
    pHeaders.addHeader("https://example.org/example", "https://example.org/example");

    DiskFileItem fileItem = new DiskFileItem("https://example.org/example", "https://example.org/example", true,
        "https://example.org/example", 3, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    fileItem.setHeaders(pHeaders);

    // Act
    Collection<String> actualHeaderNames = (new ApplicationPart(fileItem,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())).getHeaderNames();

    // Assert
    assertTrue(actualHeaderNames instanceof Set);
    assertEquals(1, actualHeaderNames.size());
    assertTrue(actualHeaderNames.contains("https://example.org/example"));
  }

  /**
   * Test {@link ApplicationPart#getHeaders(String)}.
   * <ul>
   *   <li>Then return {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_thenReturnList() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    Collection<String> actualHeaders = (new ApplicationPart(null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())).getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof List);
    assertTrue(actualHeaders.isEmpty());
  }

  /**
   * Test {@link ApplicationPart#getHeaders(String)}.
   * <ul>
   *   <li>Then return {@link Set}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_thenReturnSet() {
    // Arrange
    DiskFileItem fileItem = new DiskFileItem("https://example.org/example", "https://example.org/example", true,
        "https://example.org/example", 3, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    fileItem.setHeaders(new FileItemHeadersImpl());

    // Act
    Collection<String> actualHeaders = (new ApplicationPart(fileItem,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())).getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertTrue(actualHeaders.isEmpty());
  }

  /**
   * Test {@link ApplicationPart#getHeaders(String)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getHeaders(String)}
   */
  @Test
  public void testGetHeaders_thenReturnSizeIsOne() {
    // Arrange
    FileItemHeadersImpl pHeaders = new FileItemHeadersImpl();
    pHeaders.addHeader("Name", "https://example.org/example");

    DiskFileItem fileItem = new DiskFileItem("https://example.org/example", "https://example.org/example", true,
        "https://example.org/example", 3, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    fileItem.setHeaders(pHeaders);

    // Act
    Collection<String> actualHeaders = (new ApplicationPart(fileItem,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())).getHeaders("Name");

    // Assert
    assertTrue(actualHeaders instanceof Set);
    assertEquals(1, actualHeaders.size());
    assertTrue(actualHeaders.contains("https://example.org/example"));
  }

  /**
   * Test {@link ApplicationPart#getName()}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ApplicationPart#getName()}
   */
  @Test
  public void testGetName_thenReturnHttpsExampleOrgExample() {
    // Arrange
    DiskFileItem fileItem = new DiskFileItem("https://example.org/example", "https://example.org/example", true,
        "https://example.org/example", 3, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Act and Assert
    assertEquals("https://example.org/example",
        (new ApplicationPart(fileItem, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))
            .getName());
  }

  /**
   * Test {@link ApplicationPart#getSubmittedFileName()}.
   * <p>
   * Method under test: {@link ApplicationPart#getSubmittedFileName()}
   */
  @Test
  public void testGetSubmittedFileName() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertNull((new ApplicationPart(null, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))
        .getSubmittedFileName());
  }

  /**
   * Test {@link ApplicationPart#getSubmittedFileName()}.
   * <p>
   * Method under test: {@link ApplicationPart#getSubmittedFileName()}
   */
  @Test
  public void testGetSubmittedFileName2() {
    // Arrange
    DiskFileItem fileItem = new DiskFileItem("https://example.org/example", "https://example.org/example", true,
        "https://example.org/example", 3, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    fileItem.setHeaders(new FileItemHeadersImpl());

    // Act and Assert
    assertNull((new ApplicationPart(fileItem, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))
        .getSubmittedFileName());
  }
}
