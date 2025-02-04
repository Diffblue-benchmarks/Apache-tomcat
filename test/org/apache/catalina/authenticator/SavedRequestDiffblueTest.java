package org.apache.catalina.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import jakarta.servlet.http.Cookie;
import java.util.Iterator;
import java.util.Locale;
import org.apache.tomcat.util.buf.ByteChunk;
import org.junit.Test;

public class SavedRequestDiffblueTest {
  /**
   * Test {@link SavedRequest#addCookie(Cookie)}.
   * <ul>
   *   <li>Then not {@link SavedRequest} (default constructor) Cookies hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link SavedRequest#addCookie(Cookie)}
   */
  @Test
  public void testAddCookie_thenNotSavedRequestCookiesHasNext() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    SavedRequest savedRequest = new SavedRequest();

    // Act
    savedRequest.addCookie(cookie);

    // Assert
    Iterator<Cookie> cookies = savedRequest.getCookies();
    Cookie actualNextResult = cookies.next();
    assertFalse(cookies.hasNext());
    assertSame(cookie, actualNextResult);
  }

  /**
   * Test {@link SavedRequest#getCookies()}.
   * <p>
   * Method under test: {@link SavedRequest#getCookies()}
   */
  @Test
  public void testGetCookies() {
    // Arrange, Act and Assert
    assertFalse((new SavedRequest()).getCookies().hasNext());
  }

  /**
   * Test {@link SavedRequest#addHeader(String, String)}.
   * <ul>
   *   <li>Given {@link SavedRequest} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SavedRequest#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_givenSavedRequest() {
    // Arrange
    SavedRequest savedRequest = new SavedRequest();

    // Act
    savedRequest.addHeader("Name", "42");

    // Assert
    Iterator<String> headerNames = savedRequest.getHeaderNames();
    assertEquals("Name", headerNames.next());
    assertFalse(headerNames.hasNext());
  }

  /**
   * Test {@link SavedRequest#addHeader(String, String)}.
   * <ul>
   *   <li>Given {@link SavedRequest} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SavedRequest#addHeader(String, String)}
   */
  @Test
  public void testAddHeader_givenSavedRequestAddHeaderNameAnd42() {
    // Arrange
    SavedRequest savedRequest = new SavedRequest();
    savedRequest.addHeader("Name", "42");

    // Act
    savedRequest.addHeader("Name", "42");

    // Assert that nothing has changed
    Iterator<String> headerNames = savedRequest.getHeaderNames();
    assertEquals("Name", headerNames.next());
    assertFalse(headerNames.hasNext());
  }

  /**
   * Test {@link SavedRequest#getHeaderNames()}.
   * <p>
   * Method under test: {@link SavedRequest#getHeaderNames()}
   */
  @Test
  public void testGetHeaderNames() {
    // Arrange, Act and Assert
    assertFalse((new SavedRequest()).getHeaderNames().hasNext());
  }

  /**
   * Test {@link SavedRequest#getHeaderValues(String)}.
   * <ul>
   *   <li>Given {@link SavedRequest} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SavedRequest#getHeaderValues(String)}
   */
  @Test
  public void testGetHeaderValues_givenSavedRequest() {
    // Arrange, Act and Assert
    assertFalse((new SavedRequest()).getHeaderValues("Name").hasNext());
  }

  /**
   * Test {@link SavedRequest#getHeaderValues(String)}.
   * <ul>
   *   <li>Given {@link SavedRequest} (default constructor) addHeader {@code Name} and {@code 42}.</li>
   *   <li>Then return next is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SavedRequest#getHeaderValues(String)}
   */
  @Test
  public void testGetHeaderValues_givenSavedRequestAddHeaderNameAnd42_thenReturnNextIs42() {
    // Arrange
    SavedRequest savedRequest = new SavedRequest();
    savedRequest.addHeader("Name", "42");

    // Act
    Iterator<String> actualHeaderValues = savedRequest.getHeaderValues("Name");

    // Assert
    assertEquals("42", actualHeaderValues.next());
    assertFalse(actualHeaderValues.hasNext());
  }

  /**
   * Test {@link SavedRequest#addLocale(Locale)}.
   * <p>
   * Method under test: {@link SavedRequest#addLocale(Locale)}
   */
  @Test
  public void testAddLocale() {
    // Arrange
    SavedRequest savedRequest = new SavedRequest();
    Locale locale = Locale.getDefault();

    // Act
    savedRequest.addLocale(locale);

    // Assert
    Locale expectedNextResult = locale.ENGLISH;
    Iterator<Locale> locales = savedRequest.getLocales();
    Locale actualNextResult = locales.next();
    assertFalse(locales.hasNext());
    assertSame(expectedNextResult, actualNextResult);
  }

  /**
   * Test {@link SavedRequest#getLocales()}.
   * <p>
   * Method under test: {@link SavedRequest#getLocales()}
   */
  @Test
  public void testGetLocales() {
    // Arrange, Act and Assert
    assertFalse((new SavedRequest()).getLocales().hasNext());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link SavedRequest}
   *   <li>{@link SavedRequest#setBody(ByteChunk)}
   *   <li>{@link SavedRequest#setContentType(String)}
   *   <li>{@link SavedRequest#setDecodedRequestURI(String)}
   *   <li>{@link SavedRequest#setMethod(String)}
   *   <li>{@link SavedRequest#setOriginalMaxInactiveInterval(int)}
   *   <li>{@link SavedRequest#setQueryString(String)}
   *   <li>{@link SavedRequest#setRequestURI(String)}
   *   <li>{@link SavedRequest#getBody()}
   *   <li>{@link SavedRequest#getContentType()}
   *   <li>{@link SavedRequest#getDecodedRequestURI()}
   *   <li>{@link SavedRequest#getMethod()}
   *   <li>{@link SavedRequest#getOriginalMaxInactiveInterval()}
   *   <li>{@link SavedRequest#getQueryString()}
   *   <li>{@link SavedRequest#getRequestURI()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    SavedRequest actualSavedRequest = new SavedRequest();
    ByteChunk body = new ByteChunk(1);
    actualSavedRequest.setBody(body);
    actualSavedRequest.setContentType("application/json");
    actualSavedRequest.setDecodedRequestURI("Decoded Request URI");
    actualSavedRequest.setMethod("Method");
    actualSavedRequest.setOriginalMaxInactiveInterval(42);
    actualSavedRequest.setQueryString("Query String");
    actualSavedRequest.setRequestURI("Request URI");
    ByteChunk actualBody = actualSavedRequest.getBody();
    String actualContentType = actualSavedRequest.getContentType();
    String actualDecodedRequestURI = actualSavedRequest.getDecodedRequestURI();
    String actualMethod = actualSavedRequest.getMethod();
    int actualOriginalMaxInactiveInterval = actualSavedRequest.getOriginalMaxInactiveInterval();
    String actualQueryString = actualSavedRequest.getQueryString();

    // Assert
    assertEquals("Decoded Request URI", actualDecodedRequestURI);
    assertEquals("Method", actualMethod);
    assertEquals("Query String", actualQueryString);
    assertEquals("Request URI", actualSavedRequest.getRequestURI());
    assertEquals("application/json", actualContentType);
    assertEquals(42, actualOriginalMaxInactiveInterval);
    assertSame(body, actualBody);
  }
}
