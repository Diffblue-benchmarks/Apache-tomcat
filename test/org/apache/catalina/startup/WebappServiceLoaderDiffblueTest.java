package org.apache.catalina.startup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import org.junit.Test;

public class WebappServiceLoaderDiffblueTest {
  /**
   * Test {@link WebappServiceLoader#parseConfigFile(LinkedHashSet, URL)}.
   * <ul>
   *   <li>Then {@link LinkedHashSet#LinkedHashSet()} size is seven.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappServiceLoader#parseConfigFile(LinkedHashSet, URL)}
   */
  @Test
  public void testParseConfigFile_thenLinkedHashSetSizeIsSeven() throws IOException {
    // Arrange
    WebappServiceLoader<Object> webappServiceLoader = new WebappServiceLoader<>(new FailedContext());
    LinkedHashSet<String> servicesFound = new LinkedHashSet<>();

    // Act
    webappServiceLoader.parseConfigFile(servicesFound,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Assert
    assertEquals(7, servicesFound.size());
    assertTrue(servicesFound.contains("-config"));
    assertTrue(servicesFound.contains("-generateCode"));
    assertTrue(servicesFound.contains("-help"));
    assertTrue(servicesFound.contains("Generated Code Package"));
    assertTrue(servicesFound.contains("catalinaembedded"));
    assertTrue(servicesFound.contains("java.text"));
  }

  /**
   * Test {@link WebappServiceLoader#parseConfigFile(LinkedHashSet, URL)}.
   * <ul>
   *   <li>Then {@link LinkedHashSet#LinkedHashSet()} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebappServiceLoader#parseConfigFile(LinkedHashSet, URL)}
   */
  @Test
  public void testParseConfigFile_thenLinkedHashSetSizeIsTwo() throws IOException {
    // Arrange
    WebappServiceLoader<Object> webappServiceLoader = new WebappServiceLoader<>(new FailedContext());
    LinkedHashSet<String> servicesFound = new LinkedHashSet<>();

    // Act
    webappServiceLoader.parseConfigFile(servicesFound,
        Paths.get(System.getProperty("java.io.tmpdir"), "foo").toUri().toURL());

    // Assert
    assertEquals(2, servicesFound.size());
    assertTrue(servicesFound.contains("42"));
    assertTrue(servicesFound.contains("foo"));
  }
}
