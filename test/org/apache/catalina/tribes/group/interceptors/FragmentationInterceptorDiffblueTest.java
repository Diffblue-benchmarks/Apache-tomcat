package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.InterceptorPayload;
import org.apache.catalina.tribes.group.interceptors.FragmentationInterceptor.FragCollection;
import org.apache.catalina.tribes.group.interceptors.FragmentationInterceptor.FragKey;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class FragmentationInterceptorDiffblueTest {
  /**
   * Test FragCollection {@link FragCollection#FragCollection(ChannelMessage)}.
   * <ul>
   *   <li>Then return not complete.</li>
   * </ul>
   * <p>
   * Method under test: {@link FragCollection#FragCollection(ChannelMessage)}
   */
  @Test
  public void testFragCollectionNewFragCollection_thenReturnNotComplete() throws UnsupportedEncodingException {
    // Arrange
    byte[] uniqueId = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertFalse((new FragCollection(
        new ChannelData(uniqueId, new XByteBuffer(new byte[]{'A', 4, 'A', 4, 'A', 4, 'A', 4}, true), 10L))).complete());
  }

  /**
   * Test FragKey {@link FragKey#equals(Object)}, and {@link FragKey#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FragKey#equals(Object)}
   *   <li>{@link FragKey#hashCode()}
   * </ul>
   */
  @Test
  public void testFragKeyEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() throws UnsupportedEncodingException {
    // Arrange
    FragKey fragKey = new FragKey("AXAXAXAX".getBytes("UTF-8"));
    FragKey fragKey2 = new FragKey("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(fragKey, fragKey2);
    int expectedHashCodeResult = fragKey.hashCode();
    assertEquals(expectedHashCodeResult, fragKey2.hashCode());
  }

  /**
   * Test FragKey {@link FragKey#equals(Object)}, and {@link FragKey#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FragKey#equals(Object)}
   *   <li>{@link FragKey#hashCode()}
   * </ul>
   */
  @Test
  public void testFragKeyEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() throws UnsupportedEncodingException {
    // Arrange
    FragKey fragKey = new FragKey("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(fragKey, fragKey);
    int expectedHashCodeResult = fragKey.hashCode();
    assertEquals(expectedHashCodeResult, fragKey.hashCode());
  }

  /**
   * Test FragKey {@link FragKey#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FragKey#equals(Object)}
   */
  @Test
  public void testFragKeyEquals_whenOtherIsDifferent_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange
    FragKey fragKey = new FragKey(new byte[]{1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act and Assert
    assertNotEquals(fragKey, new FragKey("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test FragKey {@link FragKey#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FragKey#equals(Object)}
   */
  @Test
  public void testFragKeyEquals_whenOtherIsNull_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNotEquals(new FragKey("AXAXAXAX".getBytes("UTF-8")), null);
  }

  /**
   * Test FragKey {@link FragKey#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FragKey#equals(Object)}
   */
  @Test
  public void testFragKeyEquals_whenOtherIsWrongType_thenReturnNotEqual() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNotEquals(new FragKey("AXAXAXAX".getBytes("UTF-8")), "Different type to FragKey");
  }

  /**
   * Test FragKey {@link FragKey#expired(long)}.
   * <ul>
   *   <li>When minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FragKey#expired(long)}
   */
  @Test
  public void testFragKeyExpired_whenMinusOne() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertTrue((new FragKey("AXAXAXAX".getBytes("UTF-8"))).expired(-1L));
  }

  /**
   * Test {@link FragmentationInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <p>
   * Method under test: {@link FragmentationInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage() throws UnsupportedEncodingException, ChannelException {
    // Arrange
    FragmentationInterceptor fragmentationInterceptor = new FragmentationInterceptor();
    byte[] uniqueId = "AXAXAXAX".getBytes("UTF-8");
    ChannelData msg = new ChannelData(uniqueId, new XByteBuffer(3, true), 10L);

    // Act
    fragmentationInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    XByteBuffer message = msg.getMessage();
    assertEquals(1, message.getLength());
    assertEquals(3, message.getCapacity());
    assertArrayEquals(new byte[]{0}, message.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0}, message.getBytesDirect());
  }

  /**
   * Test {@link FragmentationInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <p>
   * Method under test: {@link FragmentationInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage2() throws UnsupportedEncodingException, ChannelException {
    // Arrange
    FragmentationInterceptor fragmentationInterceptor = new FragmentationInterceptor();
    byte[] uniqueId = "AXAXAXAX".getBytes("UTF-8");
    ChannelData msg = new ChannelData(uniqueId, new XByteBuffer(0, true), 10L);

    // Act
    fragmentationInterceptor.sendMessage(new Member[]{new MemberImpl()}, msg, new InterceptorPayload());

    // Assert
    XByteBuffer message = msg.getMessage();
    assertEquals(1, message.getCapacity());
    assertEquals(1, message.getLength());
    assertArrayEquals(new byte[]{0}, message.getBytes());
    assertArrayEquals(new byte[]{0}, message.getBytesDirect());
  }

  /**
   * Test {@link FragmentationInterceptor#getFragCollection(FragKey, ChannelMessage)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return not complete.</li>
   * </ul>
   * <p>
   * Method under test: {@link FragmentationInterceptor#getFragCollection(FragKey, ChannelMessage)}
   */
  @Test
  public void testGetFragCollection_whenA_thenReturnNotComplete() throws UnsupportedEncodingException {
    // Arrange
    FragmentationInterceptor fragmentationInterceptor = new FragmentationInterceptor();
    FragKey key = new FragKey("AXAXAXAX".getBytes("UTF-8"));
    byte[] uniqueId = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertFalse(fragmentationInterceptor
        .getFragCollection(key,
            new ChannelData(uniqueId, new XByteBuffer(new byte[]{'A', 4, 'A', 4, 'A', 4, 'A', 4}, true), 10L))
        .complete());
  }

  /**
   * Test {@link FragmentationInterceptor#heartbeat()}.
   * <ul>
   *   <li>Then {@link FragmentationInterceptor} (default constructor) Next Next {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FragmentationInterceptor#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenFragmentationInterceptorNextNextDomainFilterInterceptor() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    DomainFilterInterceptor next2 = new DomainFilterInterceptor();
    next.setNext(next2);

    FragmentationInterceptor fragmentationInterceptor = new FragmentationInterceptor();
    fragmentationInterceptor.setNext(next);

    // Act
    fragmentationInterceptor.heartbeat();

    // Assert
    ChannelInterceptor next3 = fragmentationInterceptor.getNext();
    ChannelInterceptor next4 = next3.getNext();
    assertTrue(next4 instanceof DomainFilterInterceptor);
    assertTrue(next3 instanceof TcpFailureDetector);
    assertSame(next2.membership, ((DomainFilterInterceptor) next4).membership);
  }

  /**
   * Test {@link FragmentationInterceptor#heartbeat()}.
   * <ul>
   *   <li>Then {@link FragmentationInterceptor} (default constructor) Next Next {@link NonBlockingCoordinator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FragmentationInterceptor#heartbeat()}
   */
  @Test
  public void testHeartbeat_thenFragmentationInterceptorNextNextNonBlockingCoordinator() {
    // Arrange
    TcpFailureDetector next = new TcpFailureDetector();
    next.setNext(new NonBlockingCoordinator());

    FragmentationInterceptor fragmentationInterceptor = new FragmentationInterceptor();
    fragmentationInterceptor.setNext(next);

    // Act
    fragmentationInterceptor.heartbeat();

    // Assert that nothing has changed
    ChannelInterceptor next2 = fragmentationInterceptor.getNext();
    assertTrue(next2.getNext() instanceof NonBlockingCoordinator);
    assertTrue(next2 instanceof TcpFailureDetector);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link FragmentationInterceptor}
   *   <li>{@link FragmentationInterceptor#setExpire(long)}
   *   <li>{@link FragmentationInterceptor#setMaxSize(int)}
   *   <li>{@link FragmentationInterceptor#getExpire()}
   *   <li>{@link FragmentationInterceptor#getMaxSize()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    FragmentationInterceptor actualFragmentationInterceptor = new FragmentationInterceptor();
    actualFragmentationInterceptor.setExpire(1L);
    actualFragmentationInterceptor.setMaxSize(3);
    long actualExpire = actualFragmentationInterceptor.getExpire();
    int actualMaxSize = actualFragmentationInterceptor.getMaxSize();

    // Assert
    assertNull(actualFragmentationInterceptor.getChannel());
    assertNull(actualFragmentationInterceptor.getNext());
    assertNull(actualFragmentationInterceptor.getPrevious());
    assertEquals(0, actualFragmentationInterceptor.getOptionFlag());
    assertEquals(1L, actualExpire);
    assertEquals(3, actualMaxSize);
  }
}
