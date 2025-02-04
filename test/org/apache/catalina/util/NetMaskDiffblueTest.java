package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import java.net.InetAddress;
import org.junit.Test;

public class NetMaskDiffblueTest {
  /**
   * Test {@link NetMask#NetMask(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return toString is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMask#NetMask(String)}
   */
  @Test
  public void testNewNetMask_when42_thenReturnToStringIs42() {
    // Arrange, Act and Assert
    assertEquals("42", (new NetMask("42")).toString());
  }

  /**
   * Test {@link NetMask#matches(InetAddress, int)} with {@code addr}, {@code port}.
   * <ul>
   *   <li>Given {@link NetMask#NetMask(String)} with input is {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMask#matches(InetAddress, int)}
   */
  @Test
  public void testMatchesWithAddrPort_givenNetMaskWithInputIs42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NetMask("42")).matches(null, 8080));
  }

  /**
   * Test {@link NetMask#equals(Object)}, and {@link NetMask#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NetMask#equals(Object)}
   *   <li>{@link NetMask#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    NetMask netMask = new NetMask("42");
    NetMask netMask2 = new NetMask("42");

    // Act and Assert
    assertEquals(netMask, netMask2);
    int expectedHashCodeResult = netMask.hashCode();
    assertEquals(expectedHashCodeResult, netMask2.hashCode());
  }

  /**
   * Test {@link NetMask#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMask#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    NetMask netMask = new NetMask("");

    // Act and Assert
    assertNotEquals(netMask, new NetMask("42"));
  }

  /**
   * Test {@link NetMask#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMask#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange, Act and Assert
    assertNotEquals(new NetMask("42"), 4);
  }

  /**
   * Test {@link NetMask#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMask#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange, Act and Assert
    assertNotEquals(new NetMask("42"), null);
  }
}
