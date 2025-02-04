package org.apache.catalina;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.io.IOException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.FailedContext;
import org.apache.tomcat.util.buf.EncodedSolidusHandling;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.junit.Test;

public class ContextDiffblueTest {
  /**
   * Test {@link Context#findConfigFileResource(String)}.
   * <ul>
   *   <li>When {@code file:/}.</li>
   *   <li>Then return URI toString is {@code file:/}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Context#findConfigFileResource(String)}
   */
  @Test
  public void testFindConfigFileResource_whenFile_thenReturnUriToStringIsFile() throws IOException {
    // Arrange and Act
    Resource actualFindConfigFileResourceResult = (new StandardContext()).findConfigFileResource("file:/");

    // Assert
    assertEquals("file:/", actualFindConfigFileResourceResult.getURI().toString());
    assertEquals(1738232189423L, actualFindConfigFileResourceResult.getLastModified());
    byte[] byteArray = new byte[51];
    assertEquals(51, actualFindConfigFileResourceResult.getInputStream().read(byteArray));
    assertArrayEquals("bin\nboot\ndev\netc\nhome\nlib\nlib64\nlost+found\nmedia\nmn".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link Context#getEncodedReverseSolidusHandling()}.
   * <p>
   * Method under test: {@link Context#getEncodedReverseSolidusHandling()}
   */
  @Test
  public void testGetEncodedReverseSolidusHandling() {
    // Arrange, Act and Assert
    assertEquals("decode", (new FailedContext()).getEncodedReverseSolidusHandling());
  }

  /**
   * Test {@link Context#setEncodedReverseSolidusHandling(String)}.
   * <p>
   * Method under test: {@link Context#setEncodedReverseSolidusHandling(String)}
   */
  @Test
  public void testSetEncodedReverseSolidusHandling() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> (new FailedContext()).setEncodedReverseSolidusHandling("secret"));
  }

  /**
   * Test {@link Context#getEncodedReverseSolidusHandlingEnum()}.
   * <p>
   * Method under test: {@link Context#getEncodedReverseSolidusHandlingEnum()}
   */
  @Test
  public void testGetEncodedReverseSolidusHandlingEnum() {
    // Arrange, Act and Assert
    assertEquals(EncodedSolidusHandling.DECODE, (new FailedContext()).getEncodedReverseSolidusHandlingEnum());
  }

  /**
   * Test {@link Context#getEncodedSolidusHandling()}.
   * <p>
   * Method under test: {@link Context#getEncodedSolidusHandling()}
   */
  @Test
  public void testGetEncodedSolidusHandling() {
    // Arrange, Act and Assert
    assertEquals("reject", (new FailedContext()).getEncodedSolidusHandling());
  }

  /**
   * Test {@link Context#setEncodedSolidusHandling(String)}.
   * <p>
   * Method under test: {@link Context#setEncodedSolidusHandling(String)}
   */
  @Test
  public void testSetEncodedSolidusHandling() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new FailedContext()).setEncodedSolidusHandling("secret"));
  }

  /**
   * Test {@link Context#getEncodedSolidusHandlingEnum()}.
   * <p>
   * Method under test: {@link Context#getEncodedSolidusHandlingEnum()}
   */
  @Test
  public void testGetEncodedSolidusHandlingEnum() {
    // Arrange, Act and Assert
    assertEquals(EncodedSolidusHandling.REJECT, (new FailedContext()).getEncodedSolidusHandlingEnum());
  }
}
