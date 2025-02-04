package org.apache.catalina.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SetCharacterEncodingFilterDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SetCharacterEncodingFilter#setEncoding(String)}
   *   <li>{@link SetCharacterEncodingFilter#setIgnore(boolean)}
   *   <li>{@link SetCharacterEncodingFilter#getEncoding()}
   *   <li>{@link SetCharacterEncodingFilter#getLogger()}
   *   <li>{@link SetCharacterEncodingFilter#isIgnore()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    SetCharacterEncodingFilter setCharacterEncodingFilter = new SetCharacterEncodingFilter();

    // Act
    setCharacterEncodingFilter.setEncoding("UTF-8");
    setCharacterEncodingFilter.setIgnore(true);
    String actualEncoding = setCharacterEncodingFilter.getEncoding();
    setCharacterEncodingFilter.getLogger();

    // Assert
    assertEquals("UTF-8", actualEncoding);
    assertTrue(setCharacterEncodingFilter.isIgnore());
  }

  /**
   * Test new {@link SetCharacterEncodingFilter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SetCharacterEncodingFilter}
   */
  @Test
  public void testNewSetCharacterEncodingFilter() {
    // Arrange and Act
    SetCharacterEncodingFilter actualSetCharacterEncodingFilter = new SetCharacterEncodingFilter();

    // Assert
    assertNull(actualSetCharacterEncodingFilter.getEncoding());
    assertFalse(actualSetCharacterEncodingFilter.isConfigProblemFatal());
    assertFalse(actualSetCharacterEncodingFilter.isIgnore());
  }
}
