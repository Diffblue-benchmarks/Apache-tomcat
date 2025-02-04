package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.MembershipProvider;
import org.apache.catalina.tribes.MessageListener;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.io.ChannelData;
import org.junit.Test;

public class McastServiceDiffblueTest {
  /**
   * Test new {@link McastService} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link McastService}
   */
  @Test
  public void testNewMcastService() {
    // Arrange and Act
    McastService actualMcastService = new McastService();

    // Assert
    assertEquals("228.0.0.4", actualMcastService.getAddress());
    assertNull(actualMcastService.getDomain());
    assertNull(actualMcastService.getPayload());
    assertNull(actualMcastService.getBind());
    assertNull(actualMcastService.getChannel());
    assertNull(actualMcastService.listener);
    assertNull(actualMcastService.getMembershipProvider());
    assertNull(actualMcastService.msglistener);
    assertNull(actualMcastService.localMember);
    assertEquals(0, actualMcastService.getSoTimeout());
    assertEquals(0, actualMcastService.getTtl());
    assertEquals(0, actualMcastService.getMembers().length);
    assertEquals(0, actualMcastService.getMembersByName().length);
    assertEquals(10, actualMcastService.getRecoveryCounter());
    assertEquals(3000L, actualMcastService.getDropTime());
    assertEquals(45564, actualMcastService.getPort());
    assertEquals(5000L, actualMcastService.getRecoverySleepTime());
    assertEquals(500L, actualMcastService.getFrequency());
    Properties properties = actualMcastService.getProperties();
    assertEquals(8, properties.size());
    assertFalse(actualMcastService.getLocalLoopbackDisabled());
    assertFalse(actualMcastService.hasMembers());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
    assertTrue(actualMcastService.getRecoveryEnabled());
  }

  /**
   * Test {@link McastService#setProperties(Properties)}.
   * <p>
   * Method under test: {@link McastService#setProperties(Properties)}
   */
  @Test
  public void testSetProperties() {
    // Arrange
    McastService mcastService = new McastService();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> mcastService.setProperties(new Properties()));
  }

  /**
   * Test {@link McastService#getLocalMember(boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_whenFalse() {
    // Arrange, Act and Assert
    assertNull((new McastService()).getLocalMember(false));
  }

  /**
   * Test {@link McastService#getLocalMember(boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_whenTrue() {
    // Arrange, Act and Assert
    assertNull((new McastService()).getLocalMember(true));
  }

  /**
   * Test {@link McastService#setLocalMemberProperties(String, int, int, int)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link McastService} (default constructor) Properties size is twelve.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#setLocalMemberProperties(String, int, int, int)}
   */
  @Test
  public void testSetLocalMemberProperties_when42_thenMcastServicePropertiesSizeIsTwelve() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setLocalMemberProperties("42", 8080, 8080, 8080);

    // Assert
    Properties properties = mcastService.getProperties();
    assertEquals(12, properties.size());
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
    assertTrue(properties.containsKey("tcpListenPort"));
    assertTrue(properties.containsKey("tcpSecurePort"));
  }

  /**
   * Test {@link McastService#setAddress(String)}.
   * <p>
   * Method under test: {@link McastService#setAddress(String)}
   */
  @Test
  public void testSetAddress() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setAddress("42 Main St");

    // Assert
    assertEquals("42 Main St", mcastService.getAddress());
    Properties properties = mcastService.getProperties();
    assertEquals(8, properties.size());
    assertEquals("42 Main St", properties.get("mcastAddress"));
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test {@link McastService#getAddress()}.
   * <p>
   * Method under test: {@link McastService#getAddress()}
   */
  @Test
  public void testGetAddress() {
    // Arrange, Act and Assert
    assertEquals("228.0.0.4", (new McastService()).getAddress());
  }

  /**
   * Test {@link McastService#setMcastBindAddress(String)}.
   * <p>
   * Method under test: {@link McastService#setMcastBindAddress(String)}
   */
  @Test
  public void testSetMcastBindAddress() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setMcastBindAddress("42 Main St");

    // Assert
    assertEquals("42 Main St", mcastService.getBind());
    Properties properties = mcastService.getProperties();
    assertEquals(9, properties.size());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test {@link McastService#setBind(String)}.
   * <p>
   * Method under test: {@link McastService#setBind(String)}
   */
  @Test
  public void testSetBind() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setBind("42 Main St");

    // Assert
    assertEquals("42 Main St", mcastService.getBind());
    Properties properties = mcastService.getProperties();
    assertEquals(9, properties.size());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test {@link McastService#getBind()}.
   * <p>
   * Method under test: {@link McastService#getBind()}
   */
  @Test
  public void testGetBind() {
    // Arrange, Act and Assert
    assertNull((new McastService()).getBind());
  }

  /**
   * Test {@link McastService#setPort(int)}.
   * <p>
   * Method under test: {@link McastService#setPort(int)}
   */
  @Test
  public void testSetPort() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setPort(8080);

    // Assert
    assertEquals(8080, mcastService.getPort());
  }

  /**
   * Test {@link McastService#setRecoveryCounter(int)}.
   * <p>
   * Method under test: {@link McastService#setRecoveryCounter(int)}
   */
  @Test
  public void testSetRecoveryCounter() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setRecoveryCounter(3);

    // Assert
    Properties properties = mcastService.getProperties();
    assertEquals(8, properties.size());
    assertEquals("3", properties.get("recoveryCounter"));
    assertEquals(3, mcastService.getRecoveryCounter());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test {@link McastService#getRecoveryCounter()}.
   * <p>
   * Method under test: {@link McastService#getRecoveryCounter()}
   */
  @Test
  public void testGetRecoveryCounter() {
    // Arrange, Act and Assert
    assertEquals(10, (new McastService()).getRecoveryCounter());
  }

  /**
   * Test {@link McastService#getRecoveryEnabled()}.
   * <ul>
   *   <li>Given {@link McastService} (default constructor) RecoveryEnabled is {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#getRecoveryEnabled()}
   */
  @Test
  public void testGetRecoveryEnabled_givenMcastServiceRecoveryEnabledIsFalse_thenReturnFalse() {
    // Arrange
    McastService mcastService = new McastService();
    mcastService.setRecoveryEnabled(false);

    // Act and Assert
    assertFalse(mcastService.getRecoveryEnabled());
  }

  /**
   * Test {@link McastService#getRecoveryEnabled()}.
   * <ul>
   *   <li>Given {@link McastService} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#getRecoveryEnabled()}
   */
  @Test
  public void testGetRecoveryEnabled_givenMcastService_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new McastService()).getRecoveryEnabled());
  }

  /**
   * Test {@link McastService#setRecoverySleepTime(long)}.
   * <p>
   * Method under test: {@link McastService#setRecoverySleepTime(long)}
   */
  @Test
  public void testSetRecoverySleepTime() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setRecoverySleepTime(1L);

    // Assert
    Properties properties = mcastService.getProperties();
    assertEquals(8, properties.size());
    assertEquals("1", properties.get("recoverySleepTime"));
    assertEquals(1L, mcastService.getRecoverySleepTime());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
  }

  /**
   * Test {@link McastService#getRecoverySleepTime()}.
   * <p>
   * Method under test: {@link McastService#getRecoverySleepTime()}
   */
  @Test
  public void testGetRecoverySleepTime() {
    // Arrange, Act and Assert
    assertEquals(5000L, (new McastService()).getRecoverySleepTime());
  }

  /**
   * Test {@link McastService#setLocalLoopbackDisabled(boolean)}.
   * <p>
   * Method under test: {@link McastService#setLocalLoopbackDisabled(boolean)}
   */
  @Test
  public void testSetLocalLoopbackDisabled() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setLocalLoopbackDisabled(true);

    // Assert
    Properties properties = mcastService.getProperties();
    assertEquals(8, properties.size());
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
    assertTrue(mcastService.getLocalLoopbackDisabled());
    String expectedString = Boolean.TRUE.toString();
    assertEquals(expectedString, properties.get("localLoopbackDisabled"));
  }

  /**
   * Test {@link McastService#getLocalLoopbackDisabled()}.
   * <ul>
   *   <li>Given {@link McastService} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#getLocalLoopbackDisabled()}
   */
  @Test
  public void testGetLocalLoopbackDisabled_givenMcastService_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new McastService()).getLocalLoopbackDisabled());
  }

  /**
   * Test {@link McastService#getLocalLoopbackDisabled()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#getLocalLoopbackDisabled()}
   */
  @Test
  public void testGetLocalLoopbackDisabled_thenReturnTrue() {
    // Arrange
    McastService mcastService = new McastService();
    mcastService.setLocalLoopbackDisabled(true);

    // Act and Assert
    assertTrue(mcastService.getLocalLoopbackDisabled());
  }

  /**
   * Test {@link McastService#getPort()}.
   * <p>
   * Method under test: {@link McastService#getPort()}
   */
  @Test
  public void testGetPort() {
    // Arrange, Act and Assert
    assertEquals(45564, (new McastService()).getPort());
  }

  /**
   * Test {@link McastService#setFrequency(long)}.
   * <p>
   * Method under test: {@link McastService#setFrequency(long)}
   */
  @Test
  public void testSetFrequency() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setFrequency(10L);

    // Assert
    Properties properties = mcastService.getProperties();
    assertEquals(8, properties.size());
    assertEquals("10", properties.get("mcastFrequency"));
    assertEquals(10L, mcastService.getFrequency());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test {@link McastService#getFrequency()}.
   * <p>
   * Method under test: {@link McastService#getFrequency()}
   */
  @Test
  public void testGetFrequency() {
    // Arrange, Act and Assert
    assertEquals(500L, (new McastService()).getFrequency());
  }

  /**
   * Test {@link McastService#setMcastDropTime(long)}.
   * <p>
   * Method under test: {@link McastService#setMcastDropTime(long)}
   */
  @Test
  public void testSetMcastDropTime() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setMcastDropTime(10L);

    // Assert
    Properties properties = mcastService.getProperties();
    assertEquals(8, properties.size());
    assertEquals("10", properties.get("memberDropTime"));
    assertEquals(10L, mcastService.getDropTime());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test {@link McastService#setDropTime(long)}.
   * <p>
   * Method under test: {@link McastService#setDropTime(long)}
   */
  @Test
  public void testSetDropTime() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setDropTime(10L);

    // Assert
    Properties properties = mcastService.getProperties();
    assertEquals(8, properties.size());
    assertEquals("10", properties.get("memberDropTime"));
    assertEquals(10L, mcastService.getDropTime());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test {@link McastService#getDropTime()}.
   * <p>
   * Method under test: {@link McastService#getDropTime()}
   */
  @Test
  public void testGetDropTime() {
    // Arrange, Act and Assert
    assertEquals(3000L, (new McastService()).getDropTime());
  }

  /**
   * Test {@link McastService#hasProperty(Properties, String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#hasProperty(Properties, String)}
   */
  @Test
  public void testHasProperty_whenName_thenThrowIllegalArgumentException() {
    // Arrange
    McastService mcastService = new McastService();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> mcastService.hasProperty(new Properties(), "Name"));
  }

  /**
   * Test {@link McastService#start(int)} with {@code int}.
   * <p>
   * Method under test: {@link McastService#start(int)}
   */
  @Test
  public void testStartWithInt() throws Exception {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new McastService()).start(1));
  }

  /**
   * Test {@link McastService#accept(ChannelMessage)}.
   * <p>
   * Method under test: {@link McastService#accept(ChannelMessage)}
   */
  @Test
  public void testAccept() {
    // Arrange
    McastService mcastService = new McastService();

    // Act and Assert
    assertTrue(mcastService.accept(new ChannelData()));
  }

  /**
   * Test {@link McastService#broadcast(ChannelMessage)}.
   * <p>
   * Method under test: {@link McastService#broadcast(ChannelMessage)}
   */
  @Test
  public void testBroadcast() throws ChannelException {
    // Arrange
    McastService mcastService = new McastService();

    // Act and Assert
    assertThrows(ChannelException.class, () -> mcastService.broadcast(new ChannelData()));
  }

  /**
   * Test {@link McastService#setSoTimeout(int)}.
   * <p>
   * Method under test: {@link McastService#setSoTimeout(int)}
   */
  @Test
  public void testSetSoTimeout() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setSoTimeout(10);

    // Assert
    assertEquals(10, mcastService.getSoTimeout());
    Properties properties = mcastService.getProperties();
    assertEquals(9, properties.size());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link McastService#setMessageListener(MessageListener)}
   *   <li>{@link McastService#removeMessageListener()}
   *   <li>{@link McastService#getDomain()}
   *   <li>{@link McastService#getMembershipProvider()}
   *   <li>{@link McastService#getPayload()}
   *   <li>{@link McastService#getSoTimeout()}
   *   <li>{@link McastService#getTtl()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setMessageListener(new ChannelCoordinator());
    mcastService.removeMessageListener();
    byte[] actualDomain = mcastService.getDomain();
    MembershipProvider actualMembershipProvider = mcastService.getMembershipProvider();
    byte[] actualPayload = mcastService.getPayload();
    int actualSoTimeout = mcastService.getSoTimeout();

    // Assert
    assertNull(actualDomain);
    assertNull(actualPayload);
    assertNull(actualMembershipProvider);
    assertEquals(0, actualSoTimeout);
    assertEquals(0, mcastService.getTtl());
  }

  /**
   * Test {@link McastService#setTtl(int)}.
   * <p>
   * Method under test: {@link McastService#setTtl(int)}
   */
  @Test
  public void testSetTtl() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setTtl(1);

    // Assert
    assertEquals(1, mcastService.getTtl());
    Properties properties = mcastService.getProperties();
    assertEquals(9, properties.size());
    assertTrue(properties.containsKey("localLoopbackDisabled"));
    assertTrue(properties.containsKey("mcastAddress"));
    assertTrue(properties.containsKey("mcastFrequency"));
    assertTrue(properties.containsKey("memberDropTime"));
    assertTrue(properties.containsKey("recoveryCounter"));
    assertTrue(properties.containsKey("recoverySleepTime"));
  }

  /**
   * Test {@link McastService#setPayload(byte[])}.
   * <p>
   * Method under test: {@link McastService#setPayload(byte[])}
   */
  @Test
  public void testSetPayload() throws UnsupportedEncodingException {
    // Arrange
    McastService mcastService = new McastService();
    byte[] payload = "AXAXAXAX".getBytes("UTF-8");

    // Act
    mcastService.setPayload(payload);

    // Assert
    assertSame(payload, mcastService.getPayload());
  }

  /**
   * Test {@link McastService#setDomain(byte[])} with {@code byte[]}.
   * <p>
   * Method under test: {@link McastService#setDomain(byte[])}
   */
  @Test
  public void testSetDomainWithByte() throws UnsupportedEncodingException {
    // Arrange
    McastService mcastService = new McastService();
    byte[] domain = "AXAXAXAX".getBytes("UTF-8");

    // Act
    mcastService.setDomain(domain);

    // Assert
    assertSame(domain, mcastService.getDomain());
  }

  /**
   * Test {@link McastService#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>Then {@link McastService} (default constructor) Domain is empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_thenMcastServiceDomainIsEmptyArrayOfByte() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setDomain("{");

    // Assert
    assertArrayEquals(new byte[]{}, mcastService.getDomain());
  }

  /**
   * Test {@link McastService#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code {42}.</li>
   *   <li>Then {@link McastService} (default constructor) Domain is array of {@code byte} with {@code *}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_when42_thenMcastServiceDomainIsArrayOfByteWithAsterisk() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setDomain("{42");

    // Assert
    assertArrayEquals(new byte[]{'*'}, mcastService.getDomain());
  }

  /**
   * Test {@link McastService#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code Domain}.</li>
   *   <li>Then {@link McastService} (default constructor) Domain is {@code Domain} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_whenDomain_thenMcastServiceDomainIsDomainBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setDomain("Domain");

    // Assert
    byte[] expectedDomain = "Domain".getBytes("UTF-8");
    assertArrayEquals(expectedDomain, mcastService.getDomain());
  }

  /**
   * Test {@link McastService#setDomain(String)} with {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link McastService} (default constructor) Domain is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link McastService#setDomain(String)}
   */
  @Test
  public void testSetDomainWithString_whenNull_thenMcastServiceDomainIsNull() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setDomain((String) null);

    // Assert that nothing has changed
    assertNull(mcastService.getDomain());
  }

  /**
   * Test {@link McastService#setDefaults(Properties)}.
   * <p>
   * Method under test: {@link McastService#setDefaults(Properties)}
   */
  @Test
  public void testSetDefaults() {
    // Arrange
    McastService mcastService = new McastService();
    Properties properties = new Properties();

    // Act
    mcastService.setDefaults(properties);

    // Assert
    assertEquals(8, properties.size());
    assertEquals("10", properties.get("recoveryCounter"));
    assertEquals("228.0.0.4", properties.get("mcastAddress"));
    assertEquals("3000", properties.get("memberDropTime"));
    assertEquals("45564", properties.get("mcastPort"));
    assertEquals("500", properties.get("mcastFrequency"));
    assertEquals("5000", properties.get("recoverySleepTime"));
    assertEquals(properties, mcastService.getProperties());
    String expectedString = Boolean.FALSE.toString();
    assertEquals(expectedString, properties.get("localLoopbackDisabled"));
    String expectedString2 = Boolean.TRUE.toString();
    assertEquals(expectedString2, properties.get("recoveryEnabled"));
  }
}
