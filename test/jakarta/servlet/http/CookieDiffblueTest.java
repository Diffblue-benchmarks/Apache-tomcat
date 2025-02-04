package jakarta.servlet.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.Map;
import org.junit.Test;

public class CookieDiffblueTest {
  /**
   * Test {@link Cookie#Cookie(String, String)}.
   * <ul>
   *   <li>When {@code err.cookie_name_is_token}.</li>
   *   <li>Then return Name is {@code err.cookie_name_is_token}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#Cookie(String, String)}
   */
  @Test
  public void testNewCookie_whenErrCookieNameIsToken_thenReturnNameIsErrCookieNameIsToken() {
    // Arrange and Act
    Cookie actualCookie = new Cookie("err.cookie_name_is_token", "https://example.org/example");

    // Assert
    assertEquals("err.cookie_name_is_token", actualCookie.getName());
    assertEquals("https://example.org/example", actualCookie.getValue());
    assertNull(actualCookie.getComment());
    assertNull(actualCookie.getDomain());
    assertNull(actualCookie.getPath());
    assertEquals(-1, actualCookie.getMaxAge());
    assertEquals(0, actualCookie.getVersion());
    assertFalse(actualCookie.getSecure());
    assertFalse(actualCookie.isHttpOnly());
    assertTrue(actualCookie.getAttributes().isEmpty());
  }

  /**
   * Test {@link Cookie#setDomain(String)}.
   * <p>
   * Method under test: {@link Cookie#setDomain(String)}
   */
  @Test
  public void testSetDomain() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setDomain("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", cookie.getDomain());
    Map<String, String> attributes = cookie.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("https://example.org/example", attributes.get("Domain"));
  }

  /**
   * Test {@link Cookie#setDomain(String)}.
   * <ul>
   *   <li>Then {@link Cookie#Cookie(String, String)} with {@code Name} and value is {@code https://example.org/example} Attributes Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#setDomain(String)}
   */
  @Test
  public void testSetDomain_thenCookieWithNameAndValueIsHttpsExampleOrgExampleAttributesEmpty() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setDomain(null);

    // Assert that nothing has changed
    assertTrue(cookie.getAttributes().isEmpty());
  }

  /**
   * Test {@link Cookie#getDomain()}.
   * <ul>
   *   <li>Given {@link Cookie#Cookie(String, String)} with {@code Name} and value is {@code https://example.org/example}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#getDomain()}
   */
  @Test
  public void testGetDomain_givenCookieWithNameAndValueIsHttpsExampleOrgExample_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Cookie("Name", "https://example.org/example")).getDomain());
  }

  /**
   * Test {@link Cookie#setMaxAge(int)}.
   * <p>
   * Method under test: {@link Cookie#setMaxAge(int)}
   */
  @Test
  public void testSetMaxAge() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setMaxAge(1);

    // Assert
    Map<String, String> attributes = cookie.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("1", attributes.get("Max-Age"));
    assertEquals(1, cookie.getMaxAge());
  }

  /**
   * Test {@link Cookie#getMaxAge()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#getMaxAge()}
   */
  @Test
  public void testGetMaxAge_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Cookie("Name", "https://example.org/example")).getMaxAge());
  }

  /**
   * Test {@link Cookie#setPath(String)}.
   * <p>
   * Method under test: {@link Cookie#setPath(String)}
   */
  @Test
  public void testSetPath() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setPath("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", cookie.getPath());
    Map<String, String> attributes = cookie.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("https://example.org/example", attributes.get("Path"));
  }

  /**
   * Test {@link Cookie#setPath(String)}.
   * <ul>
   *   <li>Then {@link Cookie#Cookie(String, String)} with {@code Name} and value is {@code https://example.org/example} Attributes Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#setPath(String)}
   */
  @Test
  public void testSetPath_thenCookieWithNameAndValueIsHttpsExampleOrgExampleAttributesEmpty() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setPath(null);

    // Assert that nothing has changed
    assertTrue(cookie.getAttributes().isEmpty());
  }

  /**
   * Test {@link Cookie#getPath()}.
   * <ul>
   *   <li>Given {@link Cookie#Cookie(String, String)} with {@code Name} and value is {@code https://example.org/example}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#getPath()}
   */
  @Test
  public void testGetPath_givenCookieWithNameAndValueIsHttpsExampleOrgExample_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Cookie("Name", "https://example.org/example")).getPath());
  }

  /**
   * Test {@link Cookie#setSecure(boolean)}.
   * <p>
   * Method under test: {@link Cookie#setSecure(boolean)}
   */
  @Test
  public void testSetSecure() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setSecure(true);

    // Assert
    Map<String, String> attributes = cookie.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("", attributes.get("Secure"));
    assertTrue(cookie.getSecure());
  }

  /**
   * Test {@link Cookie#setSecure(boolean)}.
   * <ul>
   *   <li>Then not {@link Cookie#Cookie(String, String)} with {@code Name} and value is {@code https://example.org/example} Secure.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#setSecure(boolean)}
   */
  @Test
  public void testSetSecure_thenNotCookieWithNameAndValueIsHttpsExampleOrgExampleSecure() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setSecure(false);

    // Assert that nothing has changed
    assertFalse(cookie.getSecure());
    assertTrue(cookie.getAttributes().isEmpty());
  }

  /**
   * Test {@link Cookie#getSecure()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#getSecure()}
   */
  @Test
  public void testGetSecure_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Cookie("Name", "https://example.org/example")).getSecure());
  }

  /**
   * Test {@link Cookie#clone()}.
   * <ul>
   *   <li>Given {@link Cookie#Cookie(String, String)} with {@code Name} and value is {@code https://example.org/example}.</li>
   *   <li>Then return {@link Cookie}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#clone()}
   */
  @Test
  public void testClone_givenCookieWithNameAndValueIsHttpsExampleOrgExample_thenReturnCookie() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    Object actualCloneResult = cookie.clone();

    // Assert
    assertTrue(actualCloneResult instanceof Cookie);
    assertEquals(cookie, actualCloneResult);
  }

  /**
   * Test {@link Cookie#setHttpOnly(boolean)}.
   * <p>
   * Method under test: {@link Cookie#setHttpOnly(boolean)}
   */
  @Test
  public void testSetHttpOnly() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setHttpOnly(true);

    // Assert
    Map<String, String> attributes = cookie.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("", attributes.get("HttpOnly"));
    assertTrue(cookie.isHttpOnly());
  }

  /**
   * Test {@link Cookie#setHttpOnly(boolean)}.
   * <ul>
   *   <li>Then not {@link Cookie#Cookie(String, String)} with {@code Name} and value is {@code https://example.org/example} HttpOnly.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#setHttpOnly(boolean)}
   */
  @Test
  public void testSetHttpOnly_thenNotCookieWithNameAndValueIsHttpsExampleOrgExampleHttpOnly() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setHttpOnly(false);

    // Assert that nothing has changed
    assertFalse(cookie.isHttpOnly());
    assertTrue(cookie.getAttributes().isEmpty());
  }

  /**
   * Test {@link Cookie#isHttpOnly()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#isHttpOnly()}
   */
  @Test
  public void testIsHttpOnly_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Cookie("Name", "https://example.org/example")).isHttpOnly());
  }

  /**
   * Test {@link Cookie#setAttribute(String, String)}.
   * <p>
   * Method under test: {@link Cookie#setAttribute(String, String)}
   */
  @Test
  public void testSetAttribute() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setAttribute("cookie.attribute.invalidName.notToken", "https://example.org/example");

    // Assert
    Map<String, String> attributes = cookie.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("https://example.org/example", attributes.get("cookie.attribute.invalidName.notToken"));
    assertEquals(-1, cookie.getMaxAge());
  }

  /**
   * Test {@link Cookie#setAttribute(String, String)}.
   * <p>
   * Method under test: {@link Cookie#setAttribute(String, String)}
   */
  @Test
  public void testSetAttribute2() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setAttribute("cookie.attribute.invalidName.notToken", null);

    // Assert that nothing has changed
    assertEquals(-1, cookie.getMaxAge());
    assertTrue(cookie.getAttributes().isEmpty());
  }

  /**
   * Test {@link Cookie#setAttribute(String, String)}.
   * <p>
   * Method under test: {@link Cookie#setAttribute(String, String)}
   */
  @Test
  public void testSetAttribute3() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setAttribute("Max-Age", "42");

    // Assert
    Map<String, String> attributes = cookie.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals("42", attributes.get("Max-Age"));
    assertEquals(42, cookie.getMaxAge());
  }

  /**
   * Test {@link Cookie#setAttribute(String, String)}.
   * <p>
   * Method under test: {@link Cookie#setAttribute(String, String)}
   */
  @Test
  public void testSetAttribute4() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");

    // Act
    cookie.setAttribute("Max-Age", null);

    // Assert that nothing has changed
    assertEquals(-1, cookie.getMaxAge());
    assertTrue(cookie.getAttributes().isEmpty());
  }

  /**
   * Test {@link Cookie#setAttribute(String, String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#setAttribute(String, String)}
   */
  @Test
  public void testSetAttribute_whenHttpsExampleOrgExample_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new Cookie("Name", "https://example.org/example"))
        .setAttribute("https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Cookie#setAttribute(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#setAttribute(String, String)}
   */
  @Test
  public void testSetAttribute_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new Cookie("Name", "https://example.org/example")).setAttribute(null, "https://example.org/example"));
  }

  /**
   * Test {@link Cookie#getAttribute(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#getAttribute(String)}
   */
  @Test
  public void testGetAttribute_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new Cookie("Name", "https://example.org/example")).getAttribute("https://example.org/example"));
  }

  /**
   * Test {@link Cookie#getAttributes()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#getAttributes()}
   */
  @Test
  public void testGetAttributes_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new Cookie("Name", "https://example.org/example")).getAttributes().isEmpty());
  }

  /**
   * Test {@link Cookie#equals(Object)}, and {@link Cookie#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Cookie#equals(Object)}
   *   <li>{@link Cookie#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Cookie cookie = new Cookie("Name", "https://example.org/example");
    Cookie cookie2 = new Cookie("Name", "https://example.org/example");

    // Act and Assert
    assertEquals(cookie, cookie2);
    int expectedHashCodeResult = cookie.hashCode();
    assertEquals(expectedHashCodeResult, cookie2.hashCode());
  }

  /**
   * Test {@link Cookie#equals(Object)}, and {@link Cookie#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Cookie#equals(Object)}
   *   <li>{@link Cookie#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    Cookie cookie = new Cookie("Name", null);
    Cookie cookie2 = new Cookie("Name", null);

    // Act and Assert
    assertEquals(cookie, cookie2);
    int expectedHashCodeResult = cookie.hashCode();
    assertEquals(expectedHashCodeResult, cookie2.hashCode());
  }

  /**
   * Test {@link Cookie#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Cookie cookie = new Cookie("42", "https://example.org/example");

    // Act and Assert
    assertNotEquals(cookie, new Cookie("Name", "https://example.org/example"));
  }

  /**
   * Test {@link Cookie#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Cookie cookie = new Cookie("Name", "42");

    // Act and Assert
    assertNotEquals(cookie, new Cookie("Name", "https://example.org/example"));
  }

  /**
   * Test {@link Cookie#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Cookie cookie = new Cookie("Name", null);

    // Act and Assert
    assertNotEquals(cookie, new Cookie("Name", "https://example.org/example"));
  }

  /**
   * Test {@link Cookie#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange, Act and Assert
    assertNotEquals(new Cookie("Name", "https://example.org/example"), "https://example.org/example");
  }

  /**
   * Test {@link Cookie#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Cookie#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange, Act and Assert
    assertNotEquals(new Cookie("Name", "https://example.org/example"), null);
  }
}
