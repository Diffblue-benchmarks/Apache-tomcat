package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.nio.charset.Charset;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.junit.Test;

public class ErrorPageSupportDiffblueTest {
  /**
   * Test {@link ErrorPageSupport#add(ErrorPage)}.
   * <ul>
   *   <li>Given {@code Exception Type}.</li>
   *   <li>When {@link ErrorPage} (default constructor) ExceptionType is {@code Exception Type}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ErrorPageSupport#add(ErrorPage)}
   */
  @Test
  public void testAdd_givenExceptionType_whenErrorPageExceptionTypeIsExceptionType() {
    // Arrange
    ErrorPageSupport errorPageSupport = new ErrorPageSupport();

    ErrorPage errorPage = new ErrorPage();
    errorPage.setCharset(Charset.forName("UTF-8"));
    errorPage.setErrorCode(-1);
    errorPage.setExceptionType("Exception Type");
    errorPage.setLocation("Location");

    // Act
    errorPageSupport.add(errorPage);

    // Assert
    ErrorPage[] findAllResult = errorPageSupport.findAll();
    assertEquals(1, findAllResult.length);
    assertSame(errorPage, findAllResult[0]);
  }

  /**
   * Test {@link ErrorPageSupport#add(ErrorPage)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ErrorPage} (default constructor) ExceptionType is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ErrorPageSupport#add(ErrorPage)}
   */
  @Test
  public void testAdd_givenNull_whenErrorPageExceptionTypeIsNull() {
    // Arrange
    ErrorPageSupport errorPageSupport = new ErrorPageSupport();

    ErrorPage errorPage = new ErrorPage();
    errorPage.setCharset(Charset.forName("UTF-8"));
    errorPage.setErrorCode(-1);
    errorPage.setLocation("Location");
    errorPage.setExceptionType(null);

    // Act
    errorPageSupport.add(errorPage);

    // Assert
    ErrorPage[] findAllResult = errorPageSupport.findAll();
    assertEquals(1, findAllResult.length);
    assertSame(errorPage, findAllResult[0]);
  }

  /**
   * Test {@link ErrorPageSupport#find(int)} with {@code int}.
   * <p>
   * Method under test: {@link ErrorPageSupport#find(int)}
   */
  @Test
  public void testFindWithInt() {
    // Arrange, Act and Assert
    assertNull((new ErrorPageSupport()).find(1));
  }

  /**
   * Test {@link ErrorPageSupport#find(String)} with {@code String}.
   * <p>
   * Method under test: {@link ErrorPageSupport#find(String)}
   */
  @Test
  public void testFindWithString() {
    // Arrange, Act and Assert
    assertNull((new ErrorPageSupport()).find("Exception Type"));
  }

  /**
   * Test {@link ErrorPageSupport#find(Throwable)} with {@code Throwable}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ErrorPageSupport#find(Throwable)}
   */
  @Test
  public void testFindWithThrowable_whenNull() {
    // Arrange, Act and Assert
    assertNull((new ErrorPageSupport()).find((Throwable) null));
  }

  /**
   * Test {@link ErrorPageSupport#find(Throwable)} with {@code Throwable}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ErrorPageSupport#find(Throwable)}
   */
  @Test
  public void testFindWithThrowable_whenThrowable() {
    // Arrange
    ErrorPageSupport errorPageSupport = new ErrorPageSupport();

    // Act and Assert
    assertNull(errorPageSupport.find(new Throwable()));
  }

  /**
   * Test {@link ErrorPageSupport#findAll()}.
   * <p>
   * Method under test: {@link ErrorPageSupport#findAll()}
   */
  @Test
  public void testFindAll() {
    // Arrange, Act and Assert
    assertEquals(0, (new ErrorPageSupport()).findAll().length);
  }
}
