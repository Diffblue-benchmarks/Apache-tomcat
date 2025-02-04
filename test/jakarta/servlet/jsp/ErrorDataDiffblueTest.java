package jakarta.servlet.jsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class ErrorDataDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code Method}.</li>
   *   <li>Then return {@code Method}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ErrorData#ErrorData(Throwable, int, String, String, String, String)}
   *   <li>{@link ErrorData#getMethod()}
   *   <li>{@link ErrorData#getQueryString()}
   *   <li>{@link ErrorData#getRequestURI()}
   *   <li>{@link ErrorData#getServletName()}
   *   <li>{@link ErrorData#getStatusCode()}
   *   <li>{@link ErrorData#getThrowable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenMethod_thenReturnMethod() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    ErrorData actualErrorData = new ErrorData(throwable, 1, "Method", "Uri", "Servlet Name", "Query String");
    String actualMethod = actualErrorData.getMethod();
    String actualQueryString = actualErrorData.getQueryString();
    String actualRequestURI = actualErrorData.getRequestURI();
    String actualServletName = actualErrorData.getServletName();
    int actualStatusCode = actualErrorData.getStatusCode();

    // Assert
    assertEquals("Method", actualMethod);
    assertEquals("Query String", actualQueryString);
    assertEquals("Servlet Name", actualServletName);
    assertEquals("Uri", actualRequestURI);
    assertEquals(1, actualStatusCode);
    assertSame(throwable, actualErrorData.getThrowable());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Method is {@code null}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ErrorData#ErrorData(Throwable, int, String, String)}
   *   <li>{@link ErrorData#getMethod()}
   *   <li>{@link ErrorData#getQueryString()}
   *   <li>{@link ErrorData#getRequestURI()}
   *   <li>{@link ErrorData#getServletName()}
   *   <li>{@link ErrorData#getStatusCode()}
   *   <li>{@link ErrorData#getThrowable()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenThrowable_thenReturnMethodIsNull() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    ErrorData actualErrorData = new ErrorData(throwable, 1, "Uri", "Servlet Name");
    String actualMethod = actualErrorData.getMethod();
    String actualQueryString = actualErrorData.getQueryString();
    String actualRequestURI = actualErrorData.getRequestURI();
    String actualServletName = actualErrorData.getServletName();
    int actualStatusCode = actualErrorData.getStatusCode();

    // Assert
    assertEquals("Servlet Name", actualServletName);
    assertEquals("Uri", actualRequestURI);
    assertNull(actualMethod);
    assertNull(actualQueryString);
    assertEquals(1, actualStatusCode);
    assertSame(throwable, actualErrorData.getThrowable());
  }
}
