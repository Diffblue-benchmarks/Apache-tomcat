package jakarta.servlet;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MultipartConfigElementDiffblueTest {
  /**
   * Test {@link MultipartConfigElement#MultipartConfigElement(String)}.
   * <ul>
   *   <li>When {@code Location}.</li>
   *   <li>Then return {@code Location}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartConfigElement#MultipartConfigElement(String)}
   */
  @Test
  public void testNewMultipartConfigElement_whenLocation_thenReturnLocation() {
    // Arrange and Act
    MultipartConfigElement actualMultipartConfigElement = new MultipartConfigElement("Location");

    // Assert
    assertEquals("Location", actualMultipartConfigElement.getLocation());
    assertEquals(-1L, actualMultipartConfigElement.getMaxFileSize());
    assertEquals(-1L, actualMultipartConfigElement.getMaxRequestSize());
    assertEquals(0, actualMultipartConfigElement.getFileSizeThreshold());
  }

  /**
   * Test {@link MultipartConfigElement#MultipartConfigElement(String, long, long, int)}.
   * <ul>
   *   <li>When {@code Location}.</li>
   *   <li>Then return {@code Location}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartConfigElement#MultipartConfigElement(String, long, long, int)}
   */
  @Test
  public void testNewMultipartConfigElement_whenLocation_thenReturnLocation2() {
    // Arrange and Act
    MultipartConfigElement actualMultipartConfigElement = new MultipartConfigElement("Location", 3L, 3L, 3);

    // Assert
    assertEquals("Location", actualMultipartConfigElement.getLocation());
    assertEquals(3, actualMultipartConfigElement.getFileSizeThreshold());
    assertEquals(3L, actualMultipartConfigElement.getMaxFileSize());
    assertEquals(3L, actualMultipartConfigElement.getMaxRequestSize());
  }

  /**
   * Test {@link MultipartConfigElement#MultipartConfigElement(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Location is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartConfigElement#MultipartConfigElement(String)}
   */
  @Test
  public void testNewMultipartConfigElement_whenNull_thenReturnLocationIsEmptyString() {
    // Arrange and Act
    MultipartConfigElement actualMultipartConfigElement = new MultipartConfigElement((String) null);

    // Assert
    assertEquals("", actualMultipartConfigElement.getLocation());
    assertEquals(-1L, actualMultipartConfigElement.getMaxFileSize());
    assertEquals(-1L, actualMultipartConfigElement.getMaxRequestSize());
    assertEquals(0, actualMultipartConfigElement.getFileSizeThreshold());
  }

  /**
   * Test {@link MultipartConfigElement#MultipartConfigElement(String, long, long, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Location is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartConfigElement#MultipartConfigElement(String, long, long, int)}
   */
  @Test
  public void testNewMultipartConfigElement_whenNull_thenReturnLocationIsEmptyString2() {
    // Arrange and Act
    MultipartConfigElement actualMultipartConfigElement = new MultipartConfigElement(null, 3L, 3L, 0);

    // Assert
    assertEquals("", actualMultipartConfigElement.getLocation());
    assertEquals(0, actualMultipartConfigElement.getFileSizeThreshold());
    assertEquals(3L, actualMultipartConfigElement.getMaxFileSize());
    assertEquals(3L, actualMultipartConfigElement.getMaxRequestSize());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MultipartConfigElement#getFileSizeThreshold()}
   *   <li>{@link MultipartConfigElement#getLocation()}
   *   <li>{@link MultipartConfigElement#getMaxFileSize()}
   *   <li>{@link MultipartConfigElement#getMaxRequestSize()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    MultipartConfigElement multipartConfigElement = new MultipartConfigElement("Location");

    // Act
    int actualFileSizeThreshold = multipartConfigElement.getFileSizeThreshold();
    String actualLocation = multipartConfigElement.getLocation();
    long actualMaxFileSize = multipartConfigElement.getMaxFileSize();

    // Assert
    assertEquals("Location", actualLocation);
    assertEquals(-1L, actualMaxFileSize);
    assertEquals(-1L, multipartConfigElement.getMaxRequestSize());
    assertEquals(0, actualFileSizeThreshold);
  }
}
