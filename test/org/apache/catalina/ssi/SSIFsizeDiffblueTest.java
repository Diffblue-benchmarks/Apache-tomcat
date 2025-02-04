package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class SSIFsizeDiffblueTest {
  /**
   * Test {@link SSIFsize#repeat(char, int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#repeat(char, int)}
   */
  @Test
  public void testRepeat_whenMinusOne_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new SSIFsize()).repeat('A', -1));
  }

  /**
   * Test {@link SSIFsize#repeat(char, int)}.
   * <ul>
   *   <li>When {@link ExpressionTokenizer#TOKEN_GT}.</li>
   *   <li>Then return {@code AAAAAAAAAA}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#repeat(char, int)}
   */
  @Test
  public void testRepeat_whenToken_gt_thenReturnAaaaaaaaaa() {
    // Arrange, Act and Assert
    assertEquals("AAAAAAAAAA", (new SSIFsize()).repeat('A', ExpressionTokenizer.TOKEN_GT));
  }

  /**
   * Test {@link SSIFsize#padLeft(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return space space space.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#padLeft(String, int)}
   */
  @Test
  public void testPadLeft_whenEmptyString_thenReturnSpaceSpaceSpace() {
    // Arrange, Act and Assert
    assertEquals("   ", (new SSIFsize()).padLeft("", 3));
  }

  /**
   * Test {@link SSIFsize#padLeft(String, int)}.
   * <ul>
   *   <li>When {@code Str}.</li>
   *   <li>Then return {@code Str}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#padLeft(String, int)}
   */
  @Test
  public void testPadLeft_whenStr_thenReturnStr() {
    // Arrange, Act and Assert
    assertEquals("Str", (new SSIFsize()).padLeft("Str", 3));
  }

  /**
   * Test {@link SSIFsize#formatSize(long, String)}.
   * <ul>
   *   <li>When {@code 1024}.</li>
   *   <li>Then return {@code 1k}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#formatSize(long, String)}
   */
  @Test
  public void testFormatSize_when1024_thenReturn1k() {
    // Arrange, Act and Assert
    assertEquals("   1k", (new SSIFsize()).formatSize(1024L, "Format"));
  }

  /**
   * Test {@link SSIFsize#formatSize(long, String)}.
   * <ul>
   *   <li>When {@code 1048575}.</li>
   *   <li>Then return {@code 1024k}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#formatSize(long, String)}
   */
  @Test
  public void testFormatSize_when1048575_thenReturn1024k() {
    // Arrange, Act and Assert
    assertEquals("1024k", (new SSIFsize()).formatSize(1048575L, "Format"));
  }

  /**
   * Test {@link SSIFsize#formatSize(long, String)}.
   * <ul>
   *   <li>When {@code 1048576}.</li>
   *   <li>Then return {@code 1.0M}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#formatSize(long, String)}
   */
  @Test
  public void testFormatSize_when1048576_thenReturn10m() {
    // Arrange, Act and Assert
    assertEquals(" 1.0M", (new SSIFsize()).formatSize(1048576L, "Format"));
  }

  /**
   * Test {@link SSIFsize#formatSize(long, String)}.
   * <ul>
   *   <li>When {@code 103809024}.</li>
   *   <li>Then return {@code 99M}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#formatSize(long, String)}
   */
  @Test
  public void testFormatSize_when103809024_thenReturn99m() {
    // Arrange, Act and Assert
    assertEquals("  99M", (new SSIFsize()).formatSize(103809024L, "Format"));
  }

  /**
   * Test {@link SSIFsize#formatSize(long, String)}.
   * <ul>
   *   <li>When {@code bytes}.</li>
   *   <li>Then return {@code 0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#formatSize(long, String)}
   */
  @Test
  public void testFormatSize_whenBytes_thenReturn0() {
    // Arrange, Act and Assert
    assertEquals("0", (new SSIFsize()).formatSize(0L, "bytes"));
  }

  /**
   * Test {@link SSIFsize#formatSize(long, String)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code -}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#formatSize(long, String)}
   */
  @Test
  public void testFormatSize_whenMinusOne_thenReturnDash() {
    // Arrange, Act and Assert
    assertEquals("    -", (new SSIFsize()).formatSize(-1L, "Format"));
  }

  /**
   * Test {@link SSIFsize#formatSize(long, String)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code 1k}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#formatSize(long, String)}
   */
  @Test
  public void testFormatSize_whenThree_thenReturn1k() {
    // Arrange, Act and Assert
    assertEquals("   1k", (new SSIFsize()).formatSize(3L, "Format"));
  }

  /**
   * Test {@link SSIFsize#formatSize(long, String)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return {@code 0k}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIFsize#formatSize(long, String)}
   */
  @Test
  public void testFormatSize_whenZero_thenReturn0k() {
    // Arrange, Act and Assert
    assertEquals("   0k", (new SSIFsize()).formatSize(0L, "Format"));
  }
}
