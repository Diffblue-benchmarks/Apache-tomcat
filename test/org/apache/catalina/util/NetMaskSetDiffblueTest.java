package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import org.junit.Test;

public class NetMaskSetDiffblueTest {
  /**
   * Test {@link NetMaskSet#contains(InetAddress)} with {@code inetAddress}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#contains(InetAddress)}
   */
  @Test
  public void testContainsWithInetAddress_givenNetMaskSet_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new NetMaskSet()).contains((InetAddress) null));
  }

  /**
   * Test {@link NetMaskSet#contains(String)} with {@code ipAddress}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor) add {@link NetMask#NetMask(String)} with input is {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#contains(String)}
   */
  @Test
  public void testContainsWithIpAddress_givenNetMaskSetAddNetMaskWithInputIs42_thenReturnTrue()
      throws UnknownHostException {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();
    netMaskSet.add(new NetMask("42"));

    // Act and Assert
    assertTrue(netMaskSet.contains("42"));
  }

  /**
   * Test {@link NetMaskSet#contains(String)} with {@code ipAddress}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor) add {@link NetMask#NetMask(String)} with input is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#contains(String)}
   */
  @Test
  public void testContainsWithIpAddress_givenNetMaskSetAddNetMaskWithInputIsEmptyString() throws UnknownHostException {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();
    netMaskSet.add(new NetMask(""));

    // Act and Assert
    assertFalse(netMaskSet.contains("42"));
  }

  /**
   * Test {@link NetMaskSet#contains(String)} with {@code ipAddress}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#contains(String)}
   */
  @Test
  public void testContainsWithIpAddress_givenNetMaskSet_when42_thenReturnFalse() throws UnknownHostException {
    // Arrange, Act and Assert
    assertFalse((new NetMaskSet()).contains("42"));
  }

  /**
   * Test {@link NetMaskSet#add(String)} with {@code input}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor) add {@code 42}.</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#add(String)}
   */
  @Test
  public void testAddWithInput_givenNetMaskSetAdd42_when42_thenReturnFalse() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();
    netMaskSet.add("42");

    // Act and Assert
    assertFalse(netMaskSet.add("42"));
    assertFalse(netMaskSet.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#add(String)} with {@code input}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#add(String)}
   */
  @Test
  public void testAddWithInput_givenNetMaskSet_when42_thenReturnTrue() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();

    // Act
    boolean actualAddResult = netMaskSet.add("42");

    // Assert
    assertFalse(netMaskSet.isEmpty());
    assertTrue(actualAddResult);
  }

  /**
   * Test {@link NetMaskSet#add(NetMask)} with {@code netmask}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor) add {@link NetMask#NetMask(String)} with input is {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#add(NetMask)}
   */
  @Test
  public void testAddWithNetmask_givenNetMaskSetAddNetMaskWithInputIs42_thenReturnFalse() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();
    netMaskSet.add(new NetMask("42"));

    // Act and Assert
    assertFalse(netMaskSet.add(new NetMask("42")));
    assertFalse(netMaskSet.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#add(NetMask)} with {@code netmask}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When {@link NetMask#NetMask(String)} with input is {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#add(NetMask)}
   */
  @Test
  public void testAddWithNetmask_givenNetMaskSet_whenNetMaskWithInputIs42_thenReturnTrue() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();

    // Act
    boolean actualAddResult = netMaskSet.add(new NetMask("42"));

    // Assert
    assertFalse(netMaskSet.isEmpty());
    assertTrue(actualAddResult);
  }

  /**
   * Test {@link NetMaskSet#add(NetMask)} with {@code netmask}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#add(NetMask)}
   */
  @Test
  public void testAddWithNetmask_givenNetMaskSet_whenNull_thenReturnTrue() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();

    // Act
    boolean actualAddResult = netMaskSet.add((NetMask) null);

    // Assert
    assertFalse(netMaskSet.isEmpty());
    assertTrue(actualAddResult);
  }

  /**
   * Test {@link NetMaskSet#isEmpty()}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor) add {@link NetMask#NetMask(String)} with input is {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#isEmpty()}
   */
  @Test
  public void testIsEmpty_givenNetMaskSetAddNetMaskWithInputIs42_thenReturnFalse() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();
    netMaskSet.add(new NetMask("42"));

    // Act and Assert
    assertFalse(netMaskSet.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#isEmpty()}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#isEmpty()}
   */
  @Test
  public void testIsEmpty_givenNetMaskSet_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new NetMaskSet()).isEmpty());
  }

  /**
   * Test {@link NetMaskSet#addAll(String)}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor) add {@link NetMask#NetMask(String)} with input is {@code 42}.</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then not {@link NetMaskSet} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#addAll(String)}
   */
  @Test
  public void testAddAll_givenNetMaskSetAddNetMaskWithInputIs42_when42_thenNotNetMaskSetEmpty() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();
    netMaskSet.add(new NetMask("42"));

    // Act
    List<String> actualAddAllResult = netMaskSet.addAll("42");

    // Assert
    assertFalse(netMaskSet.isEmpty());
    assertTrue(actualAddAllResult.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#addAll(String)}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then not {@link NetMaskSet} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#addAll(String)}
   */
  @Test
  public void testAddAll_givenNetMaskSet_when42_thenNotNetMaskSetEmpty() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();

    // Act
    List<String> actualAddAllResult = netMaskSet.addAll("42");

    // Assert
    assertFalse(netMaskSet.isEmpty());
    assertTrue(actualAddAllResult.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#addAll(String)}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When {@code ,}.</li>
   *   <li>Then {@link NetMaskSet} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#addAll(String)}
   */
  @Test
  public void testAddAll_givenNetMaskSet_whenComma_thenNetMaskSetEmpty() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();

    // Act and Assert
    assertTrue(netMaskSet.addAll(",").isEmpty());
    assertTrue(netMaskSet.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#addAll(String)}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When empty string.</li>
   *   <li>Then {@link NetMaskSet} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#addAll(String)}
   */
  @Test
  public void testAddAll_givenNetMaskSet_whenEmptyString_thenNetMaskSetEmpty() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();

    // Act and Assert
    assertTrue(netMaskSet.addAll("").isEmpty());
    assertTrue(netMaskSet.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#addAll(String)}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When {@code [}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#addAll(String)}
   */
  @Test
  public void testAddAll_givenNetMaskSet_whenLeftSquareBracket_thenReturnSizeIsOne() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();

    // Act
    List<String> actualAddAllResult = netMaskSet.addAll("[");

    // Assert
    assertEquals(1, actualAddAllResult.size());
    assertEquals("[: The address [[] is not valid", actualAddAllResult.get(0));
    assertTrue(netMaskSet.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#addAll(String)}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link NetMaskSet} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#addAll(String)}
   */
  @Test
  public void testAddAll_givenNetMaskSet_whenNull_thenNetMaskSetEmpty() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();

    // Act and Assert
    assertTrue(netMaskSet.addAll(null).isEmpty());
    assertTrue(netMaskSet.isEmpty());
  }

  /**
   * Test {@link NetMaskSet#toString()}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor) add {@link NetMask#NetMask(String)} with input is {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#toString()}
   */
  @Test
  public void testToString_givenNetMaskSetAddNetMaskWithInputIs42_thenReturn42() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();
    netMaskSet.add(new NetMask("42"));

    // Act and Assert
    assertEquals("42", netMaskSet.toString());
  }

  /**
   * Test {@link NetMaskSet#toString()}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor) add {@link NetMask#NetMask(String)} with input is empty string.</li>
   *   <li>Then return {@code 42,}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#toString()}
   */
  @Test
  public void testToString_givenNetMaskSetAddNetMaskWithInputIsEmptyString_thenReturn42() {
    // Arrange
    NetMaskSet netMaskSet = new NetMaskSet();
    netMaskSet.add(new NetMask(""));
    netMaskSet.add(new NetMask("42"));

    // Act and Assert
    assertEquals("42, ", netMaskSet.toString());
  }

  /**
   * Test {@link NetMaskSet#toString()}.
   * <ul>
   *   <li>Given {@link NetMaskSet} (default constructor).</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NetMaskSet#toString()}
   */
  @Test
  public void testToString_givenNetMaskSet_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", (new NetMaskSet()).toString());
  }

  /**
   * Test new {@link NetMaskSet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NetMaskSet}
   */
  @Test
  public void testNewNetMaskSet() {
    // Arrange, Act and Assert
    assertTrue((new NetMaskSet()).isEmpty());
  }
}
