package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

public class StaticMemberDiffblueTest {
  /**
   * Test {@link StaticMember#StaticMember()}.
   * <p>
   * Method under test: {@link StaticMember#StaticMember()}
   */
  @Test
  public void testNewStaticMember() {
    // Arrange and Act
    StaticMember actualStaticMember = new StaticMember();

    // Assert
    assertEquals("tcp://{}:0", actualStaticMember.getName());
    assertEquals("{}", actualStaticMember.getHostname());
    assertEquals("{}", actualStaticMember.hostname);
    assertEquals(-1, actualStaticMember.getSecurePort());
    assertEquals(-1, actualStaticMember.getUdpPort());
    assertEquals(0, actualStaticMember.getMsgCount());
    assertEquals(0, actualStaticMember.getPort());
    assertEquals(0L, actualStaticMember.getMemberAliveTime());
    assertEquals(0L, actualStaticMember.getServiceStartTime());
    assertEquals(73, actualStaticMember.getDataLength());
    assertEquals(73, actualStaticMember.getData().length);
    assertFalse(actualStaticMember.isLocal());
    assertArrayEquals(new byte[]{}, actualStaticMember.getCommand());
    assertArrayEquals(new byte[]{}, actualStaticMember.getDomain());
    assertArrayEquals(new byte[]{}, actualStaticMember.getHost());
    assertArrayEquals(new byte[]{}, actualStaticMember.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualStaticMember.getUniqueId());
  }

  /**
   * Test {@link StaticMember#StaticMember(String, int, long)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return Hostname is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#StaticMember(String, int, long)}
   */
  @Test
  public void testNewStaticMember_when42_thenReturnHostnameIs42() throws IOException {
    // Arrange and Act
    StaticMember actualStaticMember = new StaticMember("42", 8080, 1L);

    // Assert
    assertEquals("42", actualStaticMember.getHostname());
    assertEquals("42", actualStaticMember.hostname);
    assertEquals("tcp://42:8080", actualStaticMember.getName());
    assertEquals(-1, actualStaticMember.getSecurePort());
    assertEquals(-1, actualStaticMember.getUdpPort());
    assertEquals(0, actualStaticMember.getMsgCount());
    assertEquals(0L, actualStaticMember.getServiceStartTime());
    assertEquals(1L, actualStaticMember.getMemberAliveTime());
    assertEquals(77, actualStaticMember.getDataLength());
    assertEquals(77, actualStaticMember.getData().length);
    assertEquals(8080, actualStaticMember.getPort());
    assertFalse(actualStaticMember.isFailing());
    assertFalse(actualStaticMember.isLocal());
    assertFalse(actualStaticMember.isSuspect());
    assertTrue(actualStaticMember.isReady());
    assertArrayEquals(new byte[]{}, actualStaticMember.getCommand());
    assertArrayEquals(new byte[]{}, actualStaticMember.getDomain());
    assertArrayEquals(new byte[]{}, actualStaticMember.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, actualStaticMember.getHost());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualStaticMember.getUniqueId());
  }

  /**
   * Test {@link StaticMember#StaticMember(String, int, long, byte[])}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return DataLength is eighty-five.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#StaticMember(String, int, long, byte[])}
   */
  @Test
  public void testNewStaticMember_whenAxaxaxaxBytesIsUtf8_thenReturnDataLengthIsEightyFive() throws IOException {
    // Arrange and Act
    StaticMember actualStaticMember = new StaticMember("42", 8080, 1L, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(85, actualStaticMember.getDataLength());
    assertEquals(85, actualStaticMember.getData().length);
    byte[] expectedPayload = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedPayload, actualStaticMember.getPayload());
    assertArrayEquals(new byte[]{}, actualStaticMember.getCommand());
    assertArrayEquals(new byte[]{}, actualStaticMember.getDomain());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, actualStaticMember.getHost());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualStaticMember.getUniqueId());
  }

  /**
   * Test {@link StaticMember#StaticMember(String, int, long, byte[])}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return Payload is empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#StaticMember(String, int, long, byte[])}
   */
  @Test
  public void testNewStaticMember_whenEmptyArrayOfByte_thenReturnPayloadIsEmptyArrayOfByte() throws IOException {
    // Arrange and Act
    StaticMember actualStaticMember = new StaticMember("42", 8080, 1L, new byte[]{});

    // Assert
    assertArrayEquals(new byte[]{}, actualStaticMember.getCommand());
    assertArrayEquals(new byte[]{}, actualStaticMember.getDomain());
    assertArrayEquals(new byte[]{}, actualStaticMember.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, actualStaticMember.getHost());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualStaticMember.getUniqueId());
  }

  /**
   * Test {@link StaticMember#StaticMember(String, int, long, byte[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Payload is empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#StaticMember(String, int, long, byte[])}
   */
  @Test
  public void testNewStaticMember_whenNull_thenReturnPayloadIsEmptyArrayOfByte() throws IOException {
    // Arrange and Act
    StaticMember actualStaticMember = new StaticMember("42", 8080, 1L, null);

    // Assert
    assertArrayEquals(new byte[]{}, actualStaticMember.getCommand());
    assertArrayEquals(new byte[]{}, actualStaticMember.getDomain());
    assertArrayEquals(new byte[]{}, actualStaticMember.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, actualStaticMember.getHost());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualStaticMember.getUniqueId());
  }

  /**
   * Test {@link StaticMember#setHost(String)} with {@code String}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link StaticMember#StaticMember()} Hostname is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setHost(String)}
   */
  @Test
  public void testSetHostWithString_when42_thenStaticMemberHostnameIs42() {
    // Arrange
    StaticMember staticMember = new StaticMember();

    // Act
    staticMember.setHost("42");

    // Assert
    assertEquals("42", staticMember.getHostname());
    assertEquals("42", staticMember.hostname);
    assertEquals("tcp://42:0", staticMember.getName());
    assertEquals(77, staticMember.getDataLength());
    assertEquals(77, staticMember.getData().length);
    assertFalse(staticMember.isFailing());
    assertFalse(staticMember.isSuspect());
    assertTrue(staticMember.isReady());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, staticMember.getHost());
  }

  /**
   * Test {@link StaticMember#setHost(String)} with {@code String}.
   * <ul>
   *   <li>When {@code {}.</li>
   *   <li>Then {@link StaticMember#StaticMember()} Name is {@code tcp://{}:0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setHost(String)}
   */
  @Test
  public void testSetHostWithString_whenLeftCurlyBracket_thenStaticMemberNameIsTcp0() {
    // Arrange
    StaticMember staticMember = new StaticMember();

    // Act
    staticMember.setHost("{");

    // Assert that nothing has changed
    assertEquals("tcp://{}:0", staticMember.getName());
    assertEquals("{}", staticMember.getHostname());
    assertEquals("{}", staticMember.hostname);
    assertEquals(73, staticMember.getDataLength());
    assertEquals(73, staticMember.getData().length);
    assertArrayEquals(new byte[]{}, staticMember.getHost());
  }

  /**
   * Test {@link StaticMember#setHost(String)} with {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link StaticMember#StaticMember()} Name is {@code tcp://{}:0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setHost(String)}
   */
  @Test
  public void testSetHostWithString_whenNull_thenStaticMemberNameIsTcp0() {
    // Arrange
    StaticMember staticMember = new StaticMember();

    // Act
    staticMember.setHost((String) null);

    // Assert that nothing has changed
    assertEquals("tcp://{}:0", staticMember.getName());
    assertEquals("{}", staticMember.getHostname());
    assertEquals("{}", staticMember.hostname);
    assertEquals(73, staticMember.getDataLength());
    assertEquals(73, staticMember.getData().length);
    assertArrayEquals(new byte[]{}, staticMember.getHost());
  }

  /**
   * Test {@link StaticMember#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code {42}.</li>
   *   <li>Then seventy-fourth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_when42_thenSeventyFourthElementIsZero() {
    // Arrange
    StaticMember staticMember = new StaticMember();

    // Act
    staticMember.setDomain("{42");

    // Assert
    byte[] data = staticMember.getData();
    assertEquals((byte) 0, data[73]);
    assertEquals((byte) 1, data[72]);
    assertEquals(74, staticMember.getDataLength());
    assertEquals(74, data.length);
    assertEquals('-', data[70]);
    assertEquals('2', data[13]);
    assertEquals('B', data[67]);
    assertEquals('E', data[68]);
    assertEquals('E', data[71]);
    assertEquals('I', data[66]);
    assertEquals('R', data[65]);
    assertEquals('S', data[69]);
    assertEquals('T', data[Double.SIZE]);
    assertArrayEquals(new byte[]{'*'}, staticMember.getDomain());
  }

  /**
   * Test {@link StaticMember#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code Domain}.</li>
   *   <li>Then sixty-sixth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_whenDomain_thenSixtySixthElementIsZero() throws UnsupportedEncodingException {
    // Arrange
    StaticMember staticMember = new StaticMember();

    // Act
    staticMember.setDomain("Domain");

    // Assert
    byte[] data = staticMember.getData();
    assertEquals((byte) 0, data[65]);
    assertEquals((byte) 0, data[66]);
    assertEquals((byte) 0, data[67]);
    assertEquals((byte) 0, data[68]);
    assertEquals((byte) 0, data[78]);
    assertEquals((byte) 0, data[Double.SIZE]);
    assertEquals((byte) 1, data[77]);
    assertEquals(79, staticMember.getDataLength());
    assertEquals(79, data.length);
    assertEquals('-', data[75]);
    assertEquals('7', data[13]);
    assertEquals('B', data[72]);
    assertEquals('E', data[73]);
    assertEquals('E', data[76]);
    assertEquals('I', data[71]);
    assertEquals('R', data[70]);
    assertEquals('S', data[74]);
    assertEquals('T', data[69]);
    byte[] expectedDomain = "Domain".getBytes("UTF-8");
    assertArrayEquals(expectedDomain, staticMember.getDomain());
  }

  /**
   * Test {@link StaticMember#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code {}.</li>
   *   <li>Then seventy-third element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_whenLeftCurlyBracket_thenSeventyThirdElementIsZero() {
    // Arrange
    StaticMember staticMember = new StaticMember();

    // Act
    staticMember.setDomain("{");

    // Assert that nothing has changed
    byte[] data = staticMember.getData();
    assertEquals((byte) 0, data[72]);
    assertEquals((byte) 1, data[71]);
    assertEquals(73, staticMember.getDataLength());
    assertEquals(73, data.length);
    assertEquals('-', data[69]);
    assertEquals('1', data[13]);
    assertEquals('B', data[66]);
    assertEquals('E', data[67]);
    assertEquals('E', data[70]);
    assertEquals('I', data[65]);
    assertEquals('R', data[Double.SIZE]);
    assertEquals('S', data[68]);
    assertArrayEquals(new byte[]{}, staticMember.getDomain());
  }

  /**
   * Test {@link StaticMember#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then seventy-third element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_whenNull_thenSeventyThirdElementIsZero() {
    // Arrange
    StaticMember staticMember = new StaticMember();

    // Act
    staticMember.setDomain((String) null);

    // Assert that nothing has changed
    byte[] data = staticMember.getData();
    assertEquals((byte) 0, data[72]);
    assertEquals((byte) 1, data[71]);
    assertEquals(73, staticMember.getDataLength());
    assertEquals(73, data.length);
    assertEquals('-', data[69]);
    assertEquals('1', data[13]);
    assertEquals('B', data[66]);
    assertEquals('E', data[67]);
    assertEquals('E', data[70]);
    assertEquals('I', data[65]);
    assertEquals('R', data[Double.SIZE]);
    assertEquals('S', data[68]);
    assertArrayEquals(new byte[]{}, staticMember.getDomain());
  }

  /**
   * Test {@link StaticMember#setUniqueId(String)} with {@code id}.
   * <ul>
   *   <li>When {@code {42}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setUniqueId(String)}
   */
  @Test
  public void testSetUniqueIdWithId_when42_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> (new StaticMember()).setUniqueId("{42"));
  }

  /**
   * Test {@link StaticMember#setUniqueId(String)} with {@code id}.
   * <ul>
   *   <li>When {@code {}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setUniqueId(String)}
   */
  @Test
  public void testSetUniqueIdWithId_whenLeftCurlyBracket_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> (new StaticMember()).setUniqueId("{"));
  }

  /**
   * Test {@link StaticMember#setUniqueId(String)} with {@code id}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMember#setUniqueId(String)}
   */
  @Test
  public void testSetUniqueIdWithId_whenNull_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> (new StaticMember()).setUniqueId((String) null));
  }
}
