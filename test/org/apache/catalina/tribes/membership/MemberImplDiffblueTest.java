package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

public class MemberImplDiffblueTest {
  /**
   * Test {@link MemberImpl#MemberImpl()}.
   * <p>
   * Method under test: {@link MemberImpl#MemberImpl()}
   */
  @Test
  public void testNewMemberImpl() {
    // Arrange and Act
    MemberImpl actualMemberImpl = new MemberImpl();

    // Assert
    assertEquals("tcp://{}:0", actualMemberImpl.getName());
    assertEquals("{}", actualMemberImpl.getHostname());
    assertEquals("{}", actualMemberImpl.hostname);
    assertEquals(-1, actualMemberImpl.getSecurePort());
    assertEquals(-1, actualMemberImpl.getUdpPort());
    assertEquals(0, actualMemberImpl.getMsgCount());
    assertEquals(0, actualMemberImpl.getPort());
    assertEquals(0L, actualMemberImpl.getMemberAliveTime());
    assertEquals(0L, actualMemberImpl.getServiceStartTime());
    assertEquals(73, actualMemberImpl.getDataLength());
    assertEquals(73, actualMemberImpl.getData().length);
    assertFalse(actualMemberImpl.isLocal());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getCommand());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getDomain());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getHost());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualMemberImpl.getUniqueId());
  }

  /**
   * Test {@link MemberImpl#MemberImpl(String, int, long, byte[])}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return DataLength is eighty-five.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#MemberImpl(String, int, long, byte[])}
   */
  @Test
  public void testNewMemberImpl_when42_thenReturnDataLengthIsEightyFive() throws IOException {
    // Arrange and Act
    MemberImpl actualMemberImpl = new MemberImpl("42", 8080, 1L, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(85, actualMemberImpl.getDataLength());
    assertEquals(85, actualMemberImpl.getData().length);
    byte[] expectedPayload = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedPayload, actualMemberImpl.getPayload());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getCommand());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getDomain());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, actualMemberImpl.getHost());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualMemberImpl.getUniqueId());
  }

  /**
   * Test {@link MemberImpl#MemberImpl(String, int, long)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return Hostname is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#MemberImpl(String, int, long)}
   */
  @Test
  public void testNewMemberImpl_when42_thenReturnHostnameIs42() throws IOException {
    // Arrange and Act
    MemberImpl actualMemberImpl = new MemberImpl("42", 8080, 1L);

    // Assert
    assertEquals("42", actualMemberImpl.getHostname());
    assertEquals("42", actualMemberImpl.hostname);
    assertEquals("tcp://42:8080", actualMemberImpl.getName());
    assertEquals(-1, actualMemberImpl.getSecurePort());
    assertEquals(-1, actualMemberImpl.getUdpPort());
    assertEquals(0, actualMemberImpl.getMsgCount());
    assertEquals(0L, actualMemberImpl.getServiceStartTime());
    assertEquals(1L, actualMemberImpl.getMemberAliveTime());
    assertEquals(77, actualMemberImpl.getDataLength());
    assertEquals(77, actualMemberImpl.getData().length);
    assertEquals(8080, actualMemberImpl.getPort());
    assertFalse(actualMemberImpl.isFailing());
    assertFalse(actualMemberImpl.isLocal());
    assertFalse(actualMemberImpl.isSuspect());
    assertTrue(actualMemberImpl.isReady());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getCommand());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getDomain());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, actualMemberImpl.getHost());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualMemberImpl.getUniqueId());
  }

  /**
   * Test {@link MemberImpl#MemberImpl(String, int, long, byte[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return DataLength is seventy-seven.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#MemberImpl(String, int, long, byte[])}
   */
  @Test
  public void testNewMemberImpl_whenNull_thenReturnDataLengthIsSeventySeven() throws IOException {
    // Arrange and Act
    MemberImpl actualMemberImpl = new MemberImpl("42", 8080, 1L, null);

    // Assert
    assertEquals(77, actualMemberImpl.getDataLength());
    assertEquals(77, actualMemberImpl.getData().length);
    assertArrayEquals(new byte[]{}, actualMemberImpl.getCommand());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getDomain());
    assertArrayEquals(new byte[]{}, actualMemberImpl.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, actualMemberImpl.getHost());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualMemberImpl.getUniqueId());
  }

  /**
   * Test {@link MemberImpl#isReady()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with {@code A} and one.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#isReady()}
   */
  @Test
  public void testIsReady_givenMemberImplHostIsArrayOfByteWithAAndOne_thenReturnTrue() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertTrue(memberImpl.isReady());
  }

  /**
   * Test {@link MemberImpl#isReady()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with one and one.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#isReady()}
   */
  @Test
  public void testIsReady_givenMemberImplHostIsArrayOfByteWithOneAndOne_thenReturnTrue() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{1, 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertTrue(memberImpl.isReady());
  }

  /**
   * Test {@link MemberImpl#isSuspect()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with {@code A} and one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#isSuspect()}
   */
  @Test
  public void testIsSuspect_givenMemberImplHostIsArrayOfByteWithAAndOne_thenReturnFalse() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertFalse(memberImpl.isSuspect());
  }

  /**
   * Test {@link MemberImpl#isSuspect()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with one and one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#isSuspect()}
   */
  @Test
  public void testIsSuspect_givenMemberImplHostIsArrayOfByteWithOneAndOne_thenReturnFalse() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{1, 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertFalse(memberImpl.isSuspect());
  }

  /**
   * Test {@link MemberImpl#isFailing()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with {@code A} and one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#isFailing()}
   */
  @Test
  public void testIsFailing_givenMemberImplHostIsArrayOfByteWithAAndOne_thenReturnFalse() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertFalse(memberImpl.isFailing());
  }

  /**
   * Test {@link MemberImpl#isFailing()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with {@code A} and one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#isFailing()}
   */
  @Test
  public void testIsFailing_givenMemberImplHostIsArrayOfByteWithAAndOne_thenReturnFalse2() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 1, 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertFalse(memberImpl.isFailing());
  }

  /**
   * Test {@link MemberImpl#isFailing()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with one and one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#isFailing()}
   */
  @Test
  public void testIsFailing_givenMemberImplHostIsArrayOfByteWithOneAndOne_thenReturnFalse() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{1, 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertFalse(memberImpl.isFailing());
  }

  /**
   * Test {@link MemberImpl#isFailing()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} UniqueId is array of {@code byte} with {@code A} and one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#isFailing()}
   */
  @Test
  public void testIsFailing_givenMemberImplUniqueIdIsArrayOfByteWithAAndOne_thenReturnFalse() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    memberImpl.setHost(new byte[]{1, 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertFalse(memberImpl.isFailing());
  }

  /**
   * Test {@link MemberImpl#getData(boolean, boolean)} with {@code getalive}, {@code reset}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When {@code false}.</li>
   *   <li>Then return sixty-sixth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData(boolean, boolean)}
   */
  @Test
  public void testGetDataWithGetaliveReset_givenA_whenFalse_thenReturnSixtySixthElementIsZero() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    byte[] actualData = memberImpl.getData(false, false);

    // Assert
    assertEquals((byte) 0, actualData[65]);
    assertEquals((byte) 0, actualData[66]);
    assertEquals((byte) 0, actualData[67]);
    assertEquals((byte) 0, actualData[68]);
    assertEquals((byte) 0, actualData[69]);
    assertEquals((byte) 0, actualData[70]);
    assertEquals((byte) 0, actualData[71]);
    assertEquals((byte) 0, actualData[Double.SIZE]);
    assertEquals((byte) 1, actualData[48]);
    assertEquals((byte) 1, actualData[50]);
    assertEquals((byte) 1, actualData[63]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[61]);
    assertEquals('A', actualData[49]);
    assertEquals('B', actualData[58]);
    assertEquals('E', actualData[59]);
    assertEquals('E', actualData[62]);
    assertEquals('I', actualData[57]);
    assertEquals('R', actualData[56]);
    assertEquals('S', actualData[60]);
    assertEquals('T', actualData[55]);
  }

  /**
   * Test {@link MemberImpl#getData(boolean, boolean)} with {@code getalive}, {@code reset}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When {@code true}.</li>
   *   <li>Then return sixty-sixth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData(boolean, boolean)}
   */
  @Test
  public void testGetDataWithGetaliveReset_givenA_whenTrue_thenReturnSixtySixthElementIsZero() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    byte[] actualData = memberImpl.getData(true, false);

    // Assert
    assertEquals((byte) 0, actualData[65]);
    assertEquals((byte) 0, actualData[66]);
    assertEquals((byte) 0, actualData[67]);
    assertEquals((byte) 0, actualData[68]);
    assertEquals((byte) 0, actualData[69]);
    assertEquals((byte) 0, actualData[70]);
    assertEquals((byte) 0, actualData[71]);
    assertEquals((byte) 0, actualData[Double.SIZE]);
    assertEquals((byte) 1, actualData[48]);
    assertEquals((byte) 1, actualData[50]);
    assertEquals((byte) 1, actualData[63]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[61]);
    assertEquals('A', actualData[49]);
    assertEquals('B', actualData[58]);
    assertEquals('E', actualData[59]);
    assertEquals('E', actualData[62]);
    assertEquals('I', actualData[57]);
    assertEquals('R', actualData[56]);
    assertEquals('S', actualData[60]);
    assertEquals('T', actualData[55]);
  }

  /**
   * Test {@link MemberImpl#getData(boolean, boolean)} with {@code getalive}, {@code reset}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return forty-ninth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData(boolean, boolean)}
   */
  @Test
  public void testGetDataWithGetaliveReset_givenMemberImpl_thenReturnFortyNinthElementIsZero() {
    // Arrange and Act
    byte[] actualData = (new MemberImpl()).getData(true, true);

    // Assert
    assertEquals((byte) 0, actualData[48]);
    assertEquals((byte) 0, actualData[49]);
    assertEquals((byte) 0, actualData[50]);
    assertEquals((byte) 0, actualData[55]);
    assertEquals((byte) 0, actualData[56]);
    assertEquals((byte) 0, actualData[57]);
    assertEquals((byte) 0, actualData[58]);
    assertEquals((byte) 0, actualData[59]);
    assertEquals((byte) 0, actualData[60]);
    assertEquals((byte) 0, actualData[61]);
    assertEquals((byte) 0, actualData[62]);
    assertEquals((byte) 1, actualData[71]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[69]);
    assertEquals('B', actualData[66]);
    assertEquals('E', actualData[67]);
    assertEquals('E', actualData[70]);
    assertEquals('I', actualData[65]);
    assertEquals('R', actualData[Double.SIZE]);
    assertEquals('S', actualData[68]);
    assertEquals('T', actualData[63]);
  }

  /**
   * Test {@link MemberImpl#getData(boolean, boolean)} with {@code getalive}, {@code reset}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return forty-ninth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData(boolean, boolean)}
   */
  @Test
  public void testGetDataWithGetaliveReset_givenMemberImpl_thenReturnFortyNinthElementIsZero2() {
    // Arrange and Act
    byte[] actualData = (new MemberImpl()).getData(true, false);

    // Assert
    assertEquals((byte) 0, actualData[48]);
    assertEquals((byte) 0, actualData[49]);
    assertEquals((byte) 0, actualData[50]);
    assertEquals((byte) 0, actualData[55]);
    assertEquals((byte) 0, actualData[56]);
    assertEquals((byte) 0, actualData[57]);
    assertEquals((byte) 0, actualData[58]);
    assertEquals((byte) 0, actualData[59]);
    assertEquals((byte) 0, actualData[60]);
    assertEquals((byte) 0, actualData[61]);
    assertEquals((byte) 0, actualData[62]);
    assertEquals((byte) 1, actualData[71]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[69]);
    assertEquals('B', actualData[66]);
    assertEquals('E', actualData[67]);
    assertEquals('E', actualData[70]);
    assertEquals('I', actualData[65]);
    assertEquals('R', actualData[Double.SIZE]);
    assertEquals('S', actualData[68]);
    assertEquals('T', actualData[63]);
  }

  /**
   * Test {@link MemberImpl#getData(boolean)} with {@code getalive}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When {@code false}.</li>
   *   <li>Then return sixty-sixth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData(boolean)}
   */
  @Test
  public void testGetDataWithGetalive_givenA_whenFalse_thenReturnSixtySixthElementIsZero() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 4, 'A', 4, 'A', 4, 'A', 4});

    // Act
    byte[] actualData = memberImpl.getData(false);

    // Assert
    assertEquals((byte) 0, actualData[65]);
    assertEquals((byte) 0, actualData[66]);
    assertEquals((byte) 0, actualData[67]);
    assertEquals((byte) 0, actualData[68]);
    assertEquals((byte) 0, actualData[69]);
    assertEquals((byte) 0, actualData[70]);
    assertEquals((byte) 0, actualData[71]);
    assertEquals((byte) 0, actualData[Double.SIZE]);
    assertEquals((byte) 1, actualData[63]);
    assertEquals((byte) 4, actualData[48]);
    assertEquals((byte) 4, actualData[50]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[61]);
    assertEquals('A', actualData[49]);
    assertEquals('B', actualData[58]);
    assertEquals('E', actualData[59]);
    assertEquals('E', actualData[62]);
    assertEquals('I', actualData[57]);
    assertEquals('R', actualData[56]);
    assertEquals('S', actualData[60]);
    assertEquals('T', actualData[55]);
  }

  /**
   * Test {@link MemberImpl#getData(boolean)} with {@code getalive}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When {@code true}.</li>
   *   <li>Then return sixty-sixth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData(boolean)}
   */
  @Test
  public void testGetDataWithGetalive_givenA_whenTrue_thenReturnSixtySixthElementIsZero() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 4, 'A', 4, 'A', 4, 'A', 4});

    // Act
    byte[] actualData = memberImpl.getData(true);

    // Assert
    assertEquals((byte) 0, actualData[65]);
    assertEquals((byte) 0, actualData[66]);
    assertEquals((byte) 0, actualData[67]);
    assertEquals((byte) 0, actualData[68]);
    assertEquals((byte) 0, actualData[69]);
    assertEquals((byte) 0, actualData[70]);
    assertEquals((byte) 0, actualData[71]);
    assertEquals((byte) 0, actualData[Double.SIZE]);
    assertEquals((byte) 1, actualData[63]);
    assertEquals((byte) 4, actualData[48]);
    assertEquals((byte) 4, actualData[50]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[61]);
    assertEquals('A', actualData[49]);
    assertEquals('B', actualData[58]);
    assertEquals('E', actualData[59]);
    assertEquals('E', actualData[62]);
    assertEquals('I', actualData[57]);
    assertEquals('R', actualData[56]);
    assertEquals('S', actualData[60]);
    assertEquals('T', actualData[55]);
  }

  /**
   * Test {@link MemberImpl#getData(boolean)} with {@code getalive}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return forty-ninth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData(boolean)}
   */
  @Test
  public void testGetDataWithGetalive_givenMemberImpl_thenReturnFortyNinthElementIsZero() {
    // Arrange and Act
    byte[] actualData = (new MemberImpl()).getData(true);

    // Assert
    assertEquals((byte) 0, actualData[48]);
    assertEquals((byte) 0, actualData[49]);
    assertEquals((byte) 0, actualData[50]);
    assertEquals((byte) 0, actualData[55]);
    assertEquals((byte) 0, actualData[56]);
    assertEquals((byte) 0, actualData[57]);
    assertEquals((byte) 0, actualData[58]);
    assertEquals((byte) 0, actualData[59]);
    assertEquals((byte) 0, actualData[60]);
    assertEquals((byte) 0, actualData[61]);
    assertEquals((byte) 0, actualData[62]);
    assertEquals((byte) 1, actualData[71]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[69]);
    assertEquals('B', actualData[66]);
    assertEquals('E', actualData[67]);
    assertEquals('E', actualData[70]);
    assertEquals('I', actualData[65]);
    assertEquals('R', actualData[Double.SIZE]);
    assertEquals('S', actualData[68]);
    assertEquals('T', actualData[63]);
  }

  /**
   * Test {@link MemberImpl#getData()}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return sixty-sixth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData()}
   */
  @Test
  public void testGetData_givenA_thenReturnSixtySixthElementIsZero() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    byte[] actualData = memberImpl.getData();

    // Assert
    assertEquals((byte) 0, actualData[65]);
    assertEquals((byte) 0, actualData[66]);
    assertEquals((byte) 0, actualData[67]);
    assertEquals((byte) 0, actualData[68]);
    assertEquals((byte) 0, actualData[69]);
    assertEquals((byte) 0, actualData[70]);
    assertEquals((byte) 0, actualData[71]);
    assertEquals((byte) 0, actualData[Double.SIZE]);
    assertEquals((byte) 1, actualData[48]);
    assertEquals((byte) 1, actualData[50]);
    assertEquals((byte) 1, actualData[63]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[61]);
    assertEquals('A', actualData[49]);
    assertEquals('B', actualData[58]);
    assertEquals('E', actualData[59]);
    assertEquals('E', actualData[62]);
    assertEquals('I', actualData[57]);
    assertEquals('R', actualData[56]);
    assertEquals('S', actualData[60]);
    assertEquals('T', actualData[55]);
  }

  /**
   * Test {@link MemberImpl#getData()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return forty-ninth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getData()}
   */
  @Test
  public void testGetData_givenMemberImpl_thenReturnFortyNinthElementIsZero() {
    // Arrange and Act
    byte[] actualData = (new MemberImpl()).getData();

    // Assert
    assertEquals((byte) 0, actualData[48]);
    assertEquals((byte) 0, actualData[49]);
    assertEquals((byte) 0, actualData[50]);
    assertEquals((byte) 0, actualData[55]);
    assertEquals((byte) 0, actualData[56]);
    assertEquals((byte) 0, actualData[57]);
    assertEquals((byte) 0, actualData[58]);
    assertEquals((byte) 0, actualData[59]);
    assertEquals((byte) 0, actualData[60]);
    assertEquals((byte) 0, actualData[61]);
    assertEquals((byte) 0, actualData[62]);
    assertEquals((byte) 1, actualData[71]);
    assertEquals(73, actualData.length);
    assertEquals('-', actualData[69]);
    assertEquals('B', actualData[66]);
    assertEquals('E', actualData[67]);
    assertEquals('E', actualData[70]);
    assertEquals('I', actualData[65]);
    assertEquals('R', actualData[Double.SIZE]);
    assertEquals('S', actualData[68]);
    assertEquals('T', actualData[63]);
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], MemberImpl)} with {@code data}, {@code member}.
   * <ul>
   *   <li>When array of {@code byte} with {@code A} and minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataMember_whenArrayOfByteWithAAndMinusOne() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1},
            new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], MemberImpl)} with {@code data}, {@code member}.
   * <ul>
   *   <li>When array of {@code byte} with {@code T} and minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataMember_whenArrayOfByteWithTAndMinusOne() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'T', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1},
            new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], MemberImpl)} with {@code data}, {@code member}.
   * <ul>
   *   <li>When array of {@code byte} with {@code T} and {@code R}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataMember_whenArrayOfByteWithTAndR() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'T', 'R', 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1},
            new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], MemberImpl)} with {@code data}, {@code member}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataMember_whenAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    byte[] data = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl.getMember(data, new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], MemberImpl)} with {@code data}, {@code member}.
   * <ul>
   *   <li>When {@code T}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataMember_whenT() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', 'T', 'A', -1, 'A', -1, 'A', -1, 'A', -1},
            new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int, MemberImpl)} with {@code data}, {@code offset}, {@code length}, {@code member}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int, MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataOffsetLengthMember_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1},
            2, 3, new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int, MemberImpl)} with {@code data}, {@code offset}, {@code length}, {@code member}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int, MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataOffsetLengthMember_whenAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    byte[] data = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl.getMember(data, 2, 3, new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int, MemberImpl)} with {@code data}, {@code offset}, {@code length}, {@code member}.
   * <ul>
   *   <li>When {@code R}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int, MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataOffsetLengthMember_whenR_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'A', -1, 'T', 'R', 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1},
            2, 3, new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int, MemberImpl)} with {@code data}, {@code offset}, {@code length}, {@code member}.
   * <ul>
   *   <li>When {@code T}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int, MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataOffsetLengthMember_whenT_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'A', -1, 'T', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1},
            2, 3, new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int, MemberImpl)} with {@code data}, {@code offset}, {@code length}, {@code member}.
   * <ul>
   *   <li>When {@code T}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int, MemberImpl)}
   */
  @Test
  public void testGetMemberWithDataOffsetLengthMember_whenT_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', 'T', 'A', -1, 'A', -1, 'A', -1, 'A', -1},
            2, 3, new MemberImpl()));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int)}
   */
  @Test
  public void testGetMemberWithDataOffsetLength_whenAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl.getMember("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When {@code R}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int)}
   */
  @Test
  public void testGetMemberWithDataOffsetLength_whenR_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl
        .getMember(new byte[]{'A', -1, 'T', 'R', 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1}, 2, 3));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When {@code T}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int)}
   */
  @Test
  public void testGetMemberWithDataOffsetLength_whenT_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl
        .getMember(new byte[]{'A', -1, 'T', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1}, 2, 3));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When {@code T}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int)}
   */
  @Test
  public void testGetMemberWithDataOffsetLength_whenT_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl
        .getMember(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', 'T', 'A', -1, 'A', -1, 'A', -1, 'A', -1}, 2, 3));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[], int, int)} with {@code data}, {@code offset}, {@code length}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[], int, int)}
   */
  @Test
  public void testGetMemberWithDataOffsetLength_whenTwo_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl
        .getMember(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1}, 2, 3));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[])} with {@code data}.
   * <ul>
   *   <li>When array of {@code byte} with {@code A} and minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[])}
   */
  @Test
  public void testGetMemberWithData_whenArrayOfByteWithAAndMinusOne() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1}));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[])} with {@code data}.
   * <ul>
   *   <li>When array of {@code byte} with {@code T} and minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[])}
   */
  @Test
  public void testGetMemberWithData_whenArrayOfByteWithTAndMinusOne() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MemberImpl.getMember(new byte[]{'T', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1}));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[])} with {@code data}.
   * <ul>
   *   <li>When array of {@code byte} with {@code T} and {@code R}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[])}
   */
  @Test
  public void testGetMemberWithData_whenArrayOfByteWithTAndR() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl
        .getMember(new byte[]{'T', 'R', 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1, 'A', -1}));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[])} with {@code data}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[])}
   */
  @Test
  public void testGetMemberWithData_whenAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl.getMember("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link MemberImpl#getMember(byte[])} with {@code data}.
   * <ul>
   *   <li>When {@code T}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getMember(byte[])}
   */
  @Test
  public void testGetMemberWithData_whenT() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> MemberImpl
        .getMember(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', 'T', 'A', -1, 'A', -1, 'A', -1, 'A', -1}));
  }

  /**
   * Test {@link MemberImpl#getName()}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return {@code tcp://{65, 1, 65, 1, 65, 1, 65, 1}:0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getName()}
   */
  @Test
  public void testGetName_givenA_thenReturnTcp6516516516510() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertEquals("tcp://{65, 1, 65, 1, 65, 1, 65, 1}:0", memberImpl.getName());
  }

  /**
   * Test {@link MemberImpl#getName()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code tcp://{}:0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getName()}
   */
  @Test
  public void testGetName_givenMemberImpl_thenReturnTcp0() {
    // Arrange, Act and Assert
    assertEquals("tcp://{}:0", (new MemberImpl()).getName());
  }

  /**
   * Test {@link MemberImpl#getName()}.
   * <ul>
   *   <li>Then return {@code tcp://42:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getName()}
   */
  @Test
  public void testGetName_thenReturnTcp428080() throws IOException {
    // Arrange, Act and Assert
    assertEquals("tcp://42:8080", (new MemberImpl("42", 8080, 1L)).getName());
  }

  /**
   * Test {@link MemberImpl#getHostname()}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return {@code {65, 1, 65, 1, 65, 1, 65, 1}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getHostname()}
   */
  @Test
  public void testGetHostname_givenA_thenReturn651651651651() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertEquals("{65, 1, 65, 1, 65, 1, 65, 1}", memberImpl.getHostname());
  }

  /**
   * Test {@link MemberImpl#getHostname()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getHostname()}
   */
  @Test
  public void testGetHostname_givenMemberImpl_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", (new MemberImpl()).getHostname());
  }

  /**
   * Test {@link MemberImpl#getHostname()}.
   * <ul>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#getHostname()}
   */
  @Test
  public void testGetHostname_thenReturn42() throws IOException {
    // Arrange, Act and Assert
    assertEquals("42", (new MemberImpl("42", 8080, 1L)).getHostname());
  }

  /**
   * Test {@link MemberImpl#getMsgCount()}.
   * <p>
   * Method under test: {@link MemberImpl#getMsgCount()}
   */
  @Test
  public void testGetMsgCount() {
    // Arrange, Act and Assert
    assertEquals(0, (new MemberImpl()).getMsgCount());
  }

  /**
   * Test {@link MemberImpl#getDataLength()}.
   * <p>
   * Method under test: {@link MemberImpl#getDataLength()}
   */
  @Test
  public void testGetDataLength() {
    // Arrange, Act and Assert
    assertEquals(73, (new MemberImpl()).getDataLength());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MemberImpl#setHost(byte[])}
   *   <li>{@link MemberImpl#setLocal(boolean)}
   *   <li>{@link MemberImpl#setMemberAliveTime(long)}
   *   <li>{@link MemberImpl#setServiceStartTime(long)}
   *   <li>{@link MemberImpl#toString()}
   *   <li>{@link MemberImpl#getCommand()}
   *   <li>{@link MemberImpl#getDomain()}
   *   <li>{@link MemberImpl#getHost()}
   *   <li>{@link MemberImpl#getMemberAliveTime()}
   *   <li>{@link MemberImpl#getPayload()}
   *   <li>{@link MemberImpl#getPort()}
   *   <li>{@link MemberImpl#getSecurePort()}
   *   <li>{@link MemberImpl#getServiceStartTime()}
   *   <li>{@link MemberImpl#getUdpPort()}
   *   <li>{@link MemberImpl#getUniqueId()}
   *   <li>{@link MemberImpl#isLocal()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    byte[] host = "AXAXAXAX".getBytes("UTF-8");

    // Act
    memberImpl.setHost(host);
    memberImpl.setLocal(true);
    memberImpl.setMemberAliveTime(10L);
    memberImpl.setServiceStartTime(1L);
    String actualToStringResult = memberImpl.toString();
    byte[] actualCommand = memberImpl.getCommand();
    byte[] actualDomain = memberImpl.getDomain();
    byte[] actualHost = memberImpl.getHost();
    long actualMemberAliveTime = memberImpl.getMemberAliveTime();
    byte[] actualPayload = memberImpl.getPayload();
    int actualPort = memberImpl.getPort();
    int actualSecurePort = memberImpl.getSecurePort();
    long actualServiceStartTime = memberImpl.getServiceStartTime();
    int actualUdpPort = memberImpl.getUdpPort();
    byte[] actualUniqueId = memberImpl.getUniqueId();

    // Assert
    assertEquals(
        "org.apache.catalina.tribes.membership.MemberImpl[tcp://{65, 88, 65, 88, 65, 88, 65, 88}:0,{65, 88, 65,"
            + " 88, 65, 88, 65, 88},0, alive=10, securePort=-1, UDP Port=-1, id={0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 },"
            + " payload={}, command={}, domain={}]",
        actualToStringResult);
    assertEquals(-1, actualSecurePort);
    assertEquals(-1, actualUdpPort);
    assertEquals(0, actualPort);
    assertEquals(10L, actualMemberAliveTime);
    assertEquals(1L, actualServiceStartTime);
    assertTrue(memberImpl.isLocal());
    assertSame(host, actualHost);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualHost);
    assertArrayEquals(new byte[]{}, actualCommand);
    assertArrayEquals(new byte[]{}, actualDomain);
    assertArrayEquals(new byte[]{}, actualPayload);
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, actualUniqueId);
  }

  /**
   * Test {@link MemberImpl#bToS(byte[], int)} with {@code data}, {@code max}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@code {65 88 65 88 65 88 65 88 }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#bToS(byte[], int)}
   */
  @Test
  public void testBToSWithDataMax_whenA_thenReturn6588658865886588() {
    // Arrange, Act and Assert
    assertEquals("{65 88 65 88 65 88 65 88 }", MemberImpl.bToS(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 8));
  }

  /**
   * Test {@link MemberImpl#bToS(byte[], int)} with {@code data}, {@code max}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code {65 88 65 88 ...(8)}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#bToS(byte[], int)}
   */
  @Test
  public void testBToSWithDataMax_whenAxaxaxaxBytesIsUtf8_thenReturn658865888() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{65 88 65 88 ...(8)}", MemberImpl.bToS("AXAXAXAX".getBytes("UTF-8"), 3));
  }

  /**
   * Test {@link MemberImpl#bToS(byte[], int)} with {@code data}, {@code max}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#bToS(byte[], int)}
   */
  @Test
  public void testBToSWithDataMax_whenNull_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", MemberImpl.bToS(null, 0));
  }

  /**
   * Test {@link MemberImpl#bToS(byte[])} with {@code data}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code {65 88 65 88 65 88 65 88 }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#bToS(byte[])}
   */
  @Test
  public void testBToSWithData_whenAxaxaxaxBytesIsUtf8_thenReturn6588658865886588()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("{65 88 65 88 65 88 65 88 }", MemberImpl.bToS("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link MemberImpl#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new MemberImpl(), 1);
  }

  /**
   * Test {@link MemberImpl#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertNotEquals(memberImpl, new MemberImpl());
  }

  /**
   * Test {@link MemberImpl#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act and Assert
    assertNotEquals(memberImpl, new MemberImpl());
  }

  /**
   * Test {@link MemberImpl#equals(Object)}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act and Assert
    assertEquals(memberImpl, new MemberImpl());
  }

  /**
   * Test {@link MemberImpl#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new MemberImpl(), null);
  }

  /**
   * Test {@link MemberImpl#equals(Object)}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsSame_thenReturnEqual() {
    // Arrange, Act and Assert
    assertEquals(new MemberImpl(), new MemberImpl());
  }

  /**
   * Test {@link MemberImpl#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#equals(Object)}
   */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new MemberImpl(), "Different type to MemberImpl");
  }

  /**
   * Test {@link MemberImpl#setHostname(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link MemberImpl#MemberImpl()} Hostname is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setHostname(String)}
   */
  @Test
  public void testSetHostname_when42_thenMemberImplHostnameIs42() throws IOException {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setHostname("42");

    // Assert
    assertEquals("42", memberImpl.getHostname());
    assertEquals("42", memberImpl.hostname);
    assertEquals("tcp://42:0", memberImpl.getName());
    assertEquals(77, memberImpl.getDataLength());
    assertEquals(77, memberImpl.getData().length);
    assertFalse(memberImpl.isFailing());
    assertFalse(memberImpl.isSuspect());
    assertTrue(memberImpl.isReady());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, memberImpl.getHost());
  }

  /**
   * Test {@link MemberImpl#setPort(int)}.
   * <p>
   * Method under test: {@link MemberImpl#setPort(int)}
   */
  @Test
  public void testSetPort() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setPort(8080);

    // Assert
    assertEquals("tcp://{}:8080", memberImpl.getName());
    byte[] data = memberImpl.getData();
    assertEquals((byte) 31, data[Float.PRECISION]);
    assertEquals(73, data.length);
    assertEquals(8080, memberImpl.getPort());
  }

  /**
   * Test {@link MemberImpl#setUniqueId(byte[])}.
   * <ul>
   *   <li>Then {@link MemberImpl#MemberImpl()} UniqueId is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setUniqueId(byte[])}
   */
  @Test
  public void testSetUniqueId_thenMemberImplUniqueIdIsAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    byte[] uniqueId = "AXAXAXAX".getBytes("UTF-8");

    // Act
    memberImpl.setUniqueId(uniqueId);

    // Assert
    assertSame(uniqueId, memberImpl.getUniqueId());
  }

  /**
   * Test {@link MemberImpl#setUniqueId(byte[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link MemberImpl#MemberImpl()} UniqueId is array of {@code byte} with zero and zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setUniqueId(byte[])}
   */
  @Test
  public void testSetUniqueId_whenNull_thenMemberImplUniqueIdIsArrayOfByteWithZeroAndZero() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setUniqueId(null);

    // Assert that nothing has changed
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, memberImpl.getUniqueId());
  }

  /**
   * Test {@link MemberImpl#setPayload(byte[])}.
   * <p>
   * Method under test: {@link MemberImpl#setPayload(byte[])}
   */
  @Test
  public void testSetPayload() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    memberImpl.setUniqueId("A\bA\bA\bA\b".getBytes("UTF-8"));

    // Act
    memberImpl.setPayload("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(81, memberImpl.getData().length);
  }

  /**
   * Test {@link MemberImpl#setPayload(byte[])}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()}.</li>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then eightieth element is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setPayload(byte[])}
   */
  @Test
  public void testSetPayload_givenMemberImpl_whenAxaxaxaxBytesIsUtf8_thenEightiethElementIsOne()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setPayload("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    byte[] data = memberImpl.getData();
    assertEquals((byte) 1, data[79]);
    assertEquals(81, data.length);
    assertEquals('-', data[77]);
    assertEquals('A', data[63]);
    assertEquals('A', data[65]);
    assertEquals('A', data[67]);
    assertEquals('A', data[69]);
    assertEquals('B', data[74]);
    assertEquals('E', data[75]);
    assertEquals('E', data[78]);
    assertEquals('I', data[73]);
    assertEquals('R', data[72]);
    assertEquals('S', data[76]);
    assertEquals('T', data[71]);
    assertEquals('X', data[66]);
    assertEquals('X', data[68]);
    assertEquals('X', data[70]);
    assertEquals('X', data[Double.SIZE]);
    assertEquals('\b', data[62]);
  }

  /**
   * Test {@link MemberImpl#setPayload(byte[])}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then sixty-third element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setPayload(byte[])}
   */
  @Test
  public void testSetPayload_givenMemberImpl_whenNull_thenSixtyThirdElementIsZero() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setPayload(null);

    // Assert that nothing has changed
    byte[] data = memberImpl.getData();
    assertEquals((byte) 0, data[62]);
    assertEquals((byte) 1, data[71]);
    assertEquals(73, data.length);
    assertEquals('-', data[69]);
    assertEquals('B', data[66]);
    assertEquals('E', data[67]);
    assertEquals('E', data[70]);
    assertEquals('I', data[65]);
    assertEquals('R', data[Double.SIZE]);
    assertEquals('S', data[68]);
    assertEquals('T', data[63]);
  }

  /**
   * Test {@link MemberImpl#setCommand(byte[])}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then {@link MemberImpl#MemberImpl()} DataLength is eighty-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setCommand(byte[])}
   */
  @Test
  public void testSetCommand_whenAxaxaxaxBytesIsUtf8_thenMemberImplDataLengthIsEightyOne()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    byte[] command = "AXAXAXAX".getBytes("UTF-8");

    // Act
    memberImpl.setCommand(command);

    // Assert
    assertEquals(81, memberImpl.getDataLength());
    assertEquals(81, memberImpl.getData().length);
    assertSame(command, memberImpl.getCommand());
  }

  /**
   * Test {@link MemberImpl#setCommand(byte[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link MemberImpl#MemberImpl()} DataLength is seventy-three.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setCommand(byte[])}
   */
  @Test
  public void testSetCommand_whenNull_thenMemberImplDataLengthIsSeventyThree() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setCommand(null);

    // Assert that nothing has changed
    assertEquals(73, memberImpl.getDataLength());
    assertEquals(73, memberImpl.getData().length);
    assertArrayEquals(new byte[]{}, memberImpl.getCommand());
  }

  /**
   * Test {@link MemberImpl#setDomain(byte[])}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then {@link MemberImpl#MemberImpl()} DataLength is eighty-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setDomain(byte[])}
   */
  @Test
  public void testSetDomain_whenAxaxaxaxBytesIsUtf8_thenMemberImplDataLengthIsEightyOne()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();
    byte[] domain = "AXAXAXAX".getBytes("UTF-8");

    // Act
    memberImpl.setDomain(domain);

    // Assert
    assertEquals(81, memberImpl.getDataLength());
    assertEquals(81, memberImpl.getData().length);
    assertSame(domain, memberImpl.getDomain());
  }

  /**
   * Test {@link MemberImpl#setDomain(byte[])}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link MemberImpl#MemberImpl()} DataLength is seventy-three.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberImpl#setDomain(byte[])}
   */
  @Test
  public void testSetDomain_whenNull_thenMemberImplDataLengthIsSeventyThree() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setDomain(null);

    // Assert that nothing has changed
    assertEquals(73, memberImpl.getDataLength());
    assertEquals(73, memberImpl.getData().length);
    assertArrayEquals(new byte[]{}, memberImpl.getDomain());
  }

  /**
   * Test {@link MemberImpl#setSecurePort(int)}.
   * <p>
   * Method under test: {@link MemberImpl#setSecurePort(int)}
   */
  @Test
  public void testSetSecurePort() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setSecurePort(8080);

    // Assert
    assertEquals(8080, memberImpl.getSecurePort());
  }

  /**
   * Test {@link MemberImpl#setUdpPort(int)}.
   * <p>
   * Method under test: {@link MemberImpl#setUdpPort(int)}
   */
  @Test
  public void testSetUdpPort() {
    // Arrange
    MemberImpl memberImpl = new MemberImpl();

    // Act
    memberImpl.setUdpPort(8080);

    // Assert
    assertEquals(8080, memberImpl.getUdpPort());
  }
}
