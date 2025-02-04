package org.apache.catalina.tribes.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import org.junit.Test;

public class StringManagerDiffblueTest {
  /**
   * Test {@link StringManager#getString(String, Object[])} with {@code key}, {@code args}.
   * <ul>
   *   <li>Given Manager {@code java.text} is Default.</li>
   *   <li>When {@code Key}.</li>
   *   <li>Then return {@code Key}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getString(String, Object[])}
   */
  @Test
  public void testGetStringWithKeyArgs_givenManagerJavaTextIsDefault_whenKey_thenReturnKey() {
    // Arrange, Act and Assert
    assertEquals("Key", StringManager.getManager("java.text", Locale.getDefault()).getString("Key", "Args"));
  }

  /**
   * Test {@link StringManager#getString(String, Object[])} with {@code key}, {@code args}.
   * <ul>
   *   <li>Given {@link Arrays#sm}.</li>
   *   <li>When {@code Key}.</li>
   *   <li>Then return {@code Key}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getString(String, Object[])}
   */
  @Test
  public void testGetStringWithKeyArgs_givenSm_whenKey_thenReturnKey() {
    // Arrange, Act and Assert
    assertEquals("Key", Arrays.sm.getString("Key", "Args"));
  }

  /**
   * Test {@link StringManager#getString(String, Object[])} with {@code key}, {@code args}.
   * <ul>
   *   <li>Given {@link Arrays#sm}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getString(String, Object[])}
   */
  @Test
  public void testGetStringWithKeyArgs_givenSm_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Arrays.sm.getString(null, "Args"));
  }

  /**
   * Test {@link StringManager#getString(String)} with {@code key}.
   * <ul>
   *   <li>Given Manager {@code java.text} is Default.</li>
   *   <li>When {@code Key}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getString(String)}
   */
  @Test
  public void testGetStringWithKey_givenManagerJavaTextIsDefault_whenKey_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(StringManager.getManager("java.text", Locale.getDefault()).getString("Key"));
  }

  /**
   * Test {@link StringManager#getString(String)} with {@code key}.
   * <ul>
   *   <li>Given {@link Arrays#sm}.</li>
   *   <li>When {@code Key}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getString(String)}
   */
  @Test
  public void testGetStringWithKey_givenSm_whenKey_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(Arrays.sm.getString("Key"));
  }

  /**
   * Test {@link StringManager#getString(String)} with {@code key}.
   * <ul>
   *   <li>Given {@link Arrays#sm}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getString(String)}
   */
  @Test
  public void testGetStringWithKey_givenSm_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Arrays.sm.getString(null));
  }

  /**
   * Test {@link StringManager#getLocale()}.
   * <p>
   * Method under test: {@link StringManager#getLocale()}
   */
  @Test
  public void testGetLocale() {
    // Arrange, Act and Assert
    assertNull(StringManager.getManager("java.text", Locale.getDefault()).getLocale());
  }

  /**
   * Test {@link StringManager#getManager(Class)} with {@code clazz}.
   * <ul>
   *   <li>Then return Locale Country is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getManager(Class)}
   */
  @Test
  public void testGetManagerWithClazz_thenReturnLocaleCountryIsEmptyString() throws MissingResourceException {
    // Arrange
    Class<StringManager> clazz = StringManager.class;

    // Act and Assert
    Locale locale = StringManager.getManager(clazz).getLocale();
    assertEquals("", locale.getCountry());
    assertEquals("", locale.getDisplayCountry());
    assertEquals("", locale.getDisplayScript());
    assertEquals("", locale.getDisplayVariant());
    assertEquals("", locale.getISO3Country());
    assertEquals("", locale.getScript());
    assertEquals("", locale.getVariant());
    assertEquals("English", locale.getDisplayLanguage());
    assertEquals("English", locale.getDisplayName());
    assertEquals("en", locale.getLanguage());
    assertEquals("eng", locale.getISO3Language());
    assertFalse(locale.hasExtensions());
    Set<Character> extensionKeys = locale.getExtensionKeys();
    assertTrue(extensionKeys.isEmpty());
    assertSame(extensionKeys, locale.getUnicodeLocaleAttributes());
    assertSame(extensionKeys, locale.getUnicodeLocaleKeys());
  }

  /**
   * Test {@link StringManager#getManager(Class)} with {@code clazz}.
   * <ul>
   *   <li>When {@code Object}.</li>
   *   <li>Then return Locale is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getManager(Class)}
   */
  @Test
  public void testGetManagerWithClazz_whenJavaLangObject_thenReturnLocaleIsNull() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertNull(StringManager.getManager(clazz).getLocale());
  }

  /**
   * Test {@link StringManager#getManager(String, Locale)} with {@code packageName}, {@code locale}.
   * <ul>
   *   <li>When Default.</li>
   *   <li>Then return Locale is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getManager(String, Locale)}
   */
  @Test
  public void testGetManagerWithPackageNameLocale_whenDefault_thenReturnLocaleIsNull() {
    // Arrange, Act and Assert
    assertNull(StringManager.getManager("java.text", Locale.getDefault()).getLocale());
  }

  /**
   * Test {@link StringManager#getManager(String, Locale)} with {@code packageName}, {@code locale}.
   * <ul>
   *   <li>When {@link Locale#Locale(String)} with {@code foo42}.</li>
   *   <li>Then return Locale is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getManager(String, Locale)}
   */
  @Test
  public void testGetManagerWithPackageNameLocale_whenLocaleWithFoo42_thenReturnLocaleIsNull() {
    // Arrange, Act and Assert
    assertNull(StringManager.getManager("java.text", new Locale("foo42")).getLocale());
  }

  /**
   * Test {@link StringManager#getManager(String, Locale)} with {@code packageName}, {@code locale}.
   * <ul>
   *   <li>When {@link Locale#Locale(String)} with {@code foo}.</li>
   *   <li>Then return Locale is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getManager(String, Locale)}
   */
  @Test
  public void testGetManagerWithPackageNameLocale_whenLocaleWithFoo_thenReturnLocaleIsNull() {
    // Arrange, Act and Assert
    assertNull(StringManager.getManager("java.text", new Locale("foo")).getLocale());
  }

  /**
   * Test {@link StringManager#getManager(String)} with {@code packageName}.
   * <ul>
   *   <li>When {@code java.text}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getManager(String)}
   */
  @Test
  public void testGetManagerWithPackageName_whenJavaText() {
    // Arrange, Act and Assert
    assertNull(StringManager.getManager("java.text").getLocale());
  }

  /**
   * Test {@link StringManager#getManager(String)} with {@code packageName}.
   * <ul>
   *   <li>When {@code Package Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringManager#getManager(String)}
   */
  @Test
  public void testGetManagerWithPackageName_whenPackageName() {
    // Arrange, Act and Assert
    assertNull(StringManager.getManager("Package Name").getLocale());
  }
}
