package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelReceiver;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.interceptors.EncryptInterceptor.ChannelConfigException;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.junit.Test;

public class EncryptInterceptorDiffblueTest {
  /**
   * Test ChannelConfigException {@link ChannelConfigException#ChannelConfigException(String)}.
   * <p>
   * Method under test: {@link ChannelConfigException#ChannelConfigException(String)}
   */
  @Test
  public void testChannelConfigExceptionNewChannelConfigException() {
    // Arrange and Act
    ChannelConfigException actualChannelConfigException = new ChannelConfigException("An error occurred");

    // Assert
    assertEquals("An error occurred; No faulty members identified.", actualChannelConfigException.getMessage());
    assertNull(actualChannelConfigException.getCause());
    assertEquals(0, actualChannelConfigException.getSuppressed().length);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link EncryptInterceptor}
   *   <li>{@link EncryptInterceptor#setProviderName(String)}
   *   <li>{@link EncryptInterceptor#getEncryptionAlgorithm()}
   *   <li>{@link EncryptInterceptor#getEncryptionKeyString()}
   *   <li>{@link EncryptInterceptor#getProviderName()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    EncryptInterceptor actualEncryptInterceptor = new EncryptInterceptor();
    actualEncryptInterceptor.setProviderName("Provider");
    String actualEncryptionAlgorithm = actualEncryptInterceptor.getEncryptionAlgorithm();
    String actualEncryptionKeyString = actualEncryptInterceptor.getEncryptionKeyString();

    // Assert
    assertEquals("AES/CBC/PKCS5Padding", actualEncryptionAlgorithm);
    assertEquals("Provider", actualEncryptInterceptor.getProviderName());
    assertNull(actualEncryptionKeyString);
    assertNull(actualEncryptInterceptor.getChannel());
    assertNull(actualEncryptInterceptor.getNext());
    assertNull(actualEncryptInterceptor.getPrevious());
    assertEquals(0, actualEncryptInterceptor.getOptionFlag());
  }

  /**
   * Test {@link EncryptInterceptor#start(int)}.
   * <ul>
   *   <li>Given {@link EncryptInterceptor} (default constructor) ProviderName is {@code GCM}.</li>
   *   <li>Then throw {@link ChannelException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#start(int)}
   */
  @Test
  public void testStart_givenEncryptInterceptorProviderNameIsGcm_thenThrowChannelException() throws ChannelException {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();
    encryptInterceptor.setProviderName("GCM");
    encryptInterceptor.setEncryptionKey(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});

    // Act and Assert
    assertThrows(ChannelException.class, () -> encryptInterceptor.start(2));
  }

  /**
   * Test {@link EncryptInterceptor#start(int)}.
   * <ul>
   *   <li>Given {@link EncryptInterceptor} (default constructor).</li>
   *   <li>When two.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#start(int)}
   */
  @Test
  public void testStart_givenEncryptInterceptor_whenTwo_thenThrowIllegalStateException() throws ChannelException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new EncryptInterceptor()).start(2));
  }

  /**
   * Test {@link EncryptInterceptor#stop(int)}.
   * <p>
   * Method under test: {@link EncryptInterceptor#stop(int)}
   */
  @Test
  public void testStop() throws ChannelException {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();
    ChannelCoordinator next = new ChannelCoordinator();
    encryptInterceptor.setNext(next);

    // Act
    encryptInterceptor.stop(1);

    // Assert that nothing has changed
    ChannelInterceptor next2 = encryptInterceptor.getNext();
    assertTrue(next2 instanceof ChannelCoordinator);
    ChannelReceiver clusterReceiver = ((ChannelCoordinator) next2).getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    assertSame(next, clusterReceiver.getMessageListener());
    assertSame(next, ((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link EncryptInterceptor#stop(int)}.
   * <ul>
   *   <li>Then {@link EncryptInterceptor} (default constructor) Next ClusterReceiver MessageListener is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#stop(int)}
   */
  @Test
  public void testStop_thenEncryptInterceptorNextClusterReceiverMessageListenerIsNull() throws ChannelException {
    // Arrange
    ChannelCoordinator next = new ChannelCoordinator();
    next.start(2);

    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();
    encryptInterceptor.setNext(next);

    // Act
    encryptInterceptor.stop(1);

    // Assert
    ChannelInterceptor next2 = encryptInterceptor.getNext();
    assertTrue(next2 instanceof ChannelCoordinator);
    ChannelReceiver clusterReceiver = ((ChannelCoordinator) next2).getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionAlgorithm(String)}.
   * <ul>
   *   <li>When {@code Algorithm}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionAlgorithm(String)}
   */
  @Test
  public void testSetEncryptionAlgorithm_whenAlgorithm_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new EncryptInterceptor()).setEncryptionAlgorithm("Algorithm"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKey(byte[])} with {@code key}.
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKey(byte[])}
   */
  @Test
  public void testSetEncryptionKeyWithKey() throws UnsupportedEncodingException {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();

    // Act
    encryptInterceptor.setEncryptionKey("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    byte[] expectedEncryptionKey = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedEncryptionKey, encryptInterceptor.getEncryptionKey());
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKey(String)} with {@code keyBytes}.
   * <ul>
   *   <li>Then {@link EncryptInterceptor} (default constructor) EncryptionKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKey(String)}
   */
  @Test
  public void testSetEncryptionKeyWithKeyBytes_thenEncryptInterceptorEncryptionKeyIsNull() {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();

    // Act
    encryptInterceptor.setEncryptionKey((String) null);

    // Assert that nothing has changed
    assertNull(encryptInterceptor.getEncryptionKey());
    assertNull(encryptInterceptor.getEncryptionKeyString());
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKey(String)} with {@code keyBytes}.
   * <ul>
   *   <li>Then {@link EncryptInterceptor} (default constructor) EncryptionKeyString is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKey(String)}
   */
  @Test
  public void testSetEncryptionKeyWithKeyBytes_thenEncryptInterceptorEncryptionKeyStringIs42() {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();

    // Act
    encryptInterceptor.setEncryptionKey("42");

    // Assert
    assertEquals("42", encryptInterceptor.getEncryptionKeyString());
    assertArrayEquals(new byte[]{'B'}, encryptInterceptor.getEncryptionKey());
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKey(String)} with {@code keyBytes}.
   * <ul>
   *   <li>When {@code AES/CBC/PKCS5Padding}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKey(String)}
   */
  @Test
  public void testSetEncryptionKeyWithKeyBytes_whenAesCbcPKCS5Padding() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new EncryptInterceptor()).setEncryptionKey("AES/CBC/PKCS5Padding"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKey(String)} with {@code keyBytes}.
   * <ul>
   *   <li>When {@code byte[]}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKey(String)}
   */
  @Test
  public void testSetEncryptionKeyWithKeyBytes_whenByte_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new EncryptInterceptor()).setEncryptionKey("byte[]"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKey(String)} with {@code keyBytes}.
   * <ul>
   *   <li>When {@code hexUtils.fromHex.oddDigits}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKey(String)}
   */
  @Test
  public void testSetEncryptionKeyWithKeyBytes_whenHexUtilsFromHexOddDigits() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new EncryptInterceptor()).setEncryptionKey("hexUtils.fromHex.oddDigits"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKey(String)} with {@code keyBytes}.
   * <ul>
   *   <li>When {@code Key Bytes}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKey(String)}
   */
  @Test
  public void testSetEncryptionKeyWithKeyBytes_whenKeyBytes_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new EncryptInterceptor()).setEncryptionKey("Key Bytes"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKey(byte[])} with {@code key}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link EncryptInterceptor} (default constructor) EncryptionKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKey(byte[])}
   */
  @Test
  public void testSetEncryptionKeyWithKey_whenNull_thenEncryptInterceptorEncryptionKeyIsNull() {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();

    // Act
    encryptInterceptor.setEncryptionKey((byte[]) null);

    // Assert that nothing has changed
    assertNull(encryptInterceptor.getEncryptionKey());
  }

  /**
   * Test {@link EncryptInterceptor#getEncryptionKey()}.
   * <ul>
   *   <li>Given {@link EncryptInterceptor} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#getEncryptionKey()}
   */
  @Test
  public void testGetEncryptionKey_givenEncryptInterceptor_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new EncryptInterceptor()).getEncryptionKey());
  }

  /**
   * Test {@link EncryptInterceptor#getEncryptionKey()}.
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#getEncryptionKey()}
   */
  @Test
  public void testGetEncryptionKey_thenReturnAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();
    encryptInterceptor.setEncryptionKey("AXAXAXAX".getBytes("UTF-8"));

    // Act
    byte[] actualEncryptionKey = encryptInterceptor.getEncryptionKey();

    // Assert
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualEncryptionKey);
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKeyString(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link EncryptInterceptor} (default constructor) EncryptionKeyString is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKeyString(String)}
   */
  @Test
  public void testSetEncryptionKeyString_when42_thenEncryptInterceptorEncryptionKeyStringIs42() {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();

    // Act
    encryptInterceptor.setEncryptionKeyString("42");

    // Assert
    assertEquals("42", encryptInterceptor.getEncryptionKeyString());
    assertArrayEquals(new byte[]{'B'}, encryptInterceptor.getEncryptionKey());
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKeyString(String)}.
   * <ul>
   *   <li>When {@code AES/CBC/PKCS5Padding}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKeyString(String)}
   */
  @Test
  public void testSetEncryptionKeyString_whenAesCbcPKCS5Padding() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new EncryptInterceptor()).setEncryptionKeyString("AES/CBC/PKCS5Padding"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKeyString(String)}.
   * <ul>
   *   <li>When {@code byte[]}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKeyString(String)}
   */
  @Test
  public void testSetEncryptionKeyString_whenByte_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new EncryptInterceptor()).setEncryptionKeyString("byte[]"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKeyString(String)}.
   * <ul>
   *   <li>When {@code Encryption Key String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKeyString(String)}
   */
  @Test
  public void testSetEncryptionKeyString_whenEncryptionKeyString() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new EncryptInterceptor()).setEncryptionKeyString("Encryption Key String"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKeyString(String)}.
   * <ul>
   *   <li>When {@code hexUtils.fromHex.oddDigits}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKeyString(String)}
   */
  @Test
  public void testSetEncryptionKeyString_whenHexUtilsFromHexOddDigits() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new EncryptInterceptor()).setEncryptionKeyString("hexUtils.fromHex.oddDigits"));
  }

  /**
   * Test {@link EncryptInterceptor#setEncryptionKeyString(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link EncryptInterceptor} (default constructor) EncryptionKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EncryptInterceptor#setEncryptionKeyString(String)}
   */
  @Test
  public void testSetEncryptionKeyString_whenNull_thenEncryptInterceptorEncryptionKeyIsNull() {
    // Arrange
    EncryptInterceptor encryptInterceptor = new EncryptInterceptor();

    // Act
    encryptInterceptor.setEncryptionKeyString(null);

    // Assert that nothing has changed
    assertNull(encryptInterceptor.getEncryptionKey());
    assertNull(encryptInterceptor.getEncryptionKeyString());
  }
}
