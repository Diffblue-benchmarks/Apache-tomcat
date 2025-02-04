package org.apache.catalina.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringUtilDiffblueTest {
  /**
   * Test {@link StringUtil#splitCommaSeparated(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringUtil#splitCommaSeparated(String)}
   */
  @Test
  public void testSplitCommaSeparated_whenEmptyString_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, StringUtil.splitCommaSeparated("").length);
  }

  /**
   * Test {@link StringUtil#splitCommaSeparated(String)}.
   * <ul>
   *   <li>When {@code foo}.</li>
   *   <li>Then return array of {@link String} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringUtil#splitCommaSeparated(String)}
   */
  @Test
  public void testSplitCommaSeparated_whenFoo_thenReturnArrayOfStringWithFoo() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[]{"foo"}, StringUtil.splitCommaSeparated("foo"));
  }

  /**
   * Test {@link StringUtil#splitCommaSeparated(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringUtil#splitCommaSeparated(String)}
   */
  @Test
  public void testSplitCommaSeparated_whenNull_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, StringUtil.splitCommaSeparated(null).length);
  }
}
