package jakarta.servlet.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.BitSet;
import org.junit.Test;

public class CookieNameValidatorDiffblueTest {
  /**
   * Test {@link CookieNameValidator#CookieNameValidator(String)}.
   * <p>
   * Method under test: {@link CookieNameValidator#CookieNameValidator(String)}
   */
  @Test
  public void testNewCookieNameValidator() {
    // Arrange, Act and Assert
    BitSet bitSet = (new CookieNameValidator("https://example.org/example")).allowed;
    assertEquals(128, bitSet.size());
    assertFalse(bitSet.isEmpty());
  }

  /**
   * Test {@link CookieNameValidator#validate(String)}.
   * <ul>
   *   <li>Given {@link CookieNameValidator#CookieNameValidator(String)} with separators is {@code err.cookie_name_is_token}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieNameValidator#validate(String)}
   */
  @Test
  public void testValidate_givenCookieNameValidatorWithSeparatorsIsErrCookieNameIsToken() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new CookieNameValidator("err.cookie_name_is_token")).validate("https://example.org/example"));
  }

  /**
   * Test {@link CookieNameValidator#validate(String)}.
   * <ul>
   *   <li>Given {@link CookieNameValidator#CookieNameValidator(String)} with separators is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieNameValidator#validate(String)}
   */
  @Test
  public void testValidate_givenCookieNameValidatorWithSeparatorsIsHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new CookieNameValidator("https://example.org/example")).validate("https://example.org/example"));
  }

  /**
   * Test {@link CookieNameValidator#validate(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieNameValidator#validate(String)}
   */
  @Test
  public void testValidate_whenEmptyString() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new CookieNameValidator("https://example.org/example")).validate(""));
  }

  /**
   * Test {@link CookieNameValidator#validate(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieNameValidator#validate(String)}
   */
  @Test
  public void testValidate_whenNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new CookieNameValidator("https://example.org/example")).validate(null));
  }

  /**
   * Test {@link CookieNameValidator#isToken(String)}.
   * <ul>
   *   <li>Given {@link CookieNameValidator#CookieNameValidator(String)} with separators is {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieNameValidator#isToken(String)}
   */
  @Test
  public void testIsToken_givenCookieNameValidatorWithSeparatorsIs42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new CookieNameValidator("42")).isToken("https://example.org/example"));
  }

  /**
   * Test {@link CookieNameValidator#isToken(String)}.
   * <ul>
   *   <li>Given {@link CookieNameValidator#CookieNameValidator(String)} with separators is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieNameValidator#isToken(String)}
   */
  @Test
  public void testIsToken_givenCookieNameValidatorWithSeparatorsIsHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertFalse((new CookieNameValidator("https://example.org/example")).isToken("https://example.org/example"));
  }

  /**
   * Test {@link CookieNameValidator#isToken(String)}.
   * <ul>
   *   <li>Given {@link CookieNameValidator#CookieNameValidator(String)} with {@code Separators}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CookieNameValidator#isToken(String)}
   */
  @Test
  public void testIsToken_givenCookieNameValidatorWithSeparators_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new CookieNameValidator("Separators")).isToken("https://example.org/example"));
  }
}
