package org.apache.catalina.tribes.group.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.ChannelCoordinator;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.group.InterceptorPayload;
import org.apache.catalina.tribes.group.interceptors.OrderInterceptor.Counter;
import org.apache.catalina.tribes.group.interceptors.OrderInterceptor.MessageOrder;
import org.apache.catalina.tribes.io.ChannelData;
import org.apache.catalina.tribes.io.XByteBuffer;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class OrderInterceptorDiffblueTest {
  /**
   * Test Counter {@link Counter#getCounter()}.
   * <p>
   * Method under test: {@link Counter#getCounter()}
   */
  @Test
  public void testCounterGetCounter() {
    // Arrange, Act and Assert
    assertEquals(0, (new Counter()).getCounter());
  }

  /**
   * Test Counter {@link Counter#inc()}.
   * <p>
   * Method under test: {@link Counter#inc()}
   */
  @Test
  public void testCounterInc() {
    // Arrange
    Counter counter = new Counter();

    // Act
    int actualIncResult = counter.inc();

    // Assert
    assertEquals(1, counter.getCounter());
    assertEquals(1, actualIncResult);
  }

  /**
   * Test Counter new {@link Counter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link Counter}
   */
  @Test
  public void testCounterNewCounter() {
    // Arrange, Act and Assert
    assertEquals(0, (new Counter()).getCounter());
  }

  /**
   * Test Counter {@link Counter#setCounter(int)}.
   * <p>
   * Method under test: {@link Counter#setCounter(int)}
   */
  @Test
  public void testCounterSetCounter() {
    // Arrange
    Counter counter = new Counter();

    // Act
    counter.setCounter(3);

    // Assert
    assertEquals(3, counter.getCounter());
  }

  /**
   * Test MessageOrder {@link MessageOrder#add(MessageOrder, MessageOrder)}.
   * <ul>
   *   <li>Then return MsgNr is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageOrder#add(MessageOrder, MessageOrder)}
   */
  @Test
  public void testMessageOrderAdd_thenReturnMsgNrIsZero() {
    // Arrange
    MessageOrder head = new MessageOrder(0, new ChannelData());

    MessageOrder add = new MessageOrder(1, new ChannelData());

    // Act
    MessageOrder actualAddResult = MessageOrder.add(head, add);

    // Assert
    assertEquals(0, actualAddResult.getMsgNr());
    assertEquals(2, head.getCount());
    assertEquals(2, actualAddResult.getCount());
    assertSame(add, head.getNext());
    assertSame(add, actualAddResult.getNext());
  }

  /**
   * Test MessageOrder {@link MessageOrder#add(MessageOrder, MessageOrder)}.
   * <ul>
   *   <li>Then return MsgNr is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageOrder#add(MessageOrder, MessageOrder)}
   */
  @Test
  public void testMessageOrderAdd_thenReturnMsgNrIsZero2() {
    // Arrange
    MessageOrder head = new MessageOrder(1, new ChannelData());

    MessageOrder add = new MessageOrder(0, new ChannelData());

    // Act
    MessageOrder actualAddResult = MessageOrder.add(head, add);

    // Assert
    assertEquals(0, actualAddResult.getMsgNr());
    assertEquals(2, add.getCount());
    assertEquals(2, actualAddResult.getCount());
    assertSame(head, add.getNext());
    assertSame(head, actualAddResult.getNext());
  }

  /**
   * Test MessageOrder {@link MessageOrder#add(MessageOrder, MessageOrder)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Next is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageOrder#add(MessageOrder, MessageOrder)}
   */
  @Test
  public void testMessageOrderAdd_whenNull_thenReturnNextIsNull() {
    // Arrange
    ChannelData msg = new ChannelData();

    // Act
    MessageOrder actualAddResult = MessageOrder.add(new MessageOrder(1, msg), null);

    // Assert
    assertNull(actualAddResult.getNext());
    assertEquals(1, actualAddResult.getCount());
    assertEquals(1, actualAddResult.getMsgNr());
    assertSame(msg, actualAddResult.getMessage());
  }

  /**
   * Test MessageOrder {@link MessageOrder#add(MessageOrder, MessageOrder)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageOrder#add(MessageOrder, MessageOrder)}
   */
  @Test
  public void testMessageOrderAdd_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(MessageOrder.add(null, null));
  }

  /**
   * Test MessageOrder {@link MessageOrder#getCount()}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageOrder#getCount()}
   */
  @Test
  public void testMessageOrderGetCount_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, (new MessageOrder(1, new ChannelData())).getCount());
  }

  /**
   * Test MessageOrder {@link MessageOrder#getCount()}.
   * <ul>
   *   <li>Then return two.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageOrder#getCount()}
   */
  @Test
  public void testMessageOrderGetCount_thenReturnTwo() {
    // Arrange
    MessageOrder messageOrder = new MessageOrder(1, new ChannelData());
    messageOrder.setNext(new MessageOrder(1, new ChannelData()));

    // Act and Assert
    assertEquals(2, messageOrder.getCount());
  }

  /**
   * Test MessageOrder getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MessageOrder#setMessage(ChannelMessage)}
   *   <li>{@link MessageOrder#setNext(MessageOrder)}
   *   <li>{@link MessageOrder#getMessage()}
   *   <li>{@link MessageOrder#getMsgNr()}
   *   <li>{@link MessageOrder#getNext()}
   * </ul>
   */
  @Test
  public void testMessageOrderGettersAndSetters() {
    // Arrange
    MessageOrder messageOrder = new MessageOrder(1, new ChannelData());
    ChannelData msg = new ChannelData();

    // Act
    messageOrder.setMessage(msg);
    MessageOrder order = new MessageOrder(1, new ChannelData());

    messageOrder.setNext(order);
    ChannelMessage actualMessage = messageOrder.getMessage();
    int actualMsgNr = messageOrder.getMsgNr();
    MessageOrder actualNext = messageOrder.getNext();

    // Assert
    assertEquals(1, actualNext.getCount());
    assertEquals(1, actualMsgNr);
    assertSame(order, actualNext);
    assertSame(msg, actualMessage);
  }

  /**
   * Test MessageOrder {@link MessageOrder#isExpired(long)}.
   * <ul>
   *   <li>When minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageOrder#isExpired(long)}
   */
  @Test
  public void testMessageOrderIsExpired_whenMinusOne() {
    // Arrange, Act and Assert
    assertTrue((new MessageOrder(1, new ChannelData())).isExpired(-1L));
  }

  /**
   * Test MessageOrder {@link MessageOrder#MessageOrder(int, ChannelMessage)}.
   * <p>
   * Method under test: {@link MessageOrder#MessageOrder(int, ChannelMessage)}
   */
  @Test
  public void testMessageOrderNewMessageOrder() {
    // Arrange
    ChannelData msg = new ChannelData();

    // Act
    MessageOrder actualMessageOrder = new MessageOrder(1, msg);

    // Assert
    assertNull(actualMessageOrder.getNext());
    assertEquals(1, actualMessageOrder.getCount());
    assertEquals(1, actualMessageOrder.getMsgNr());
    assertSame(msg, actualMessageOrder.getMessage());
  }

  /**
   * Test {@link OrderInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <p>
   * Method under test: {@link OrderInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage() throws ChannelException {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setNext(new DomainFilterInterceptor());
    ChannelData msg = new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L);

    // Act
    orderInterceptor.sendMessage(new Member[]{null}, msg, new InterceptorPayload());

    // Assert
    XByteBuffer message = msg.getMessage();
    assertEquals(0, message.getLength());
    assertEquals(6, message.getCapacity());
    assertArrayEquals(new byte[]{}, message.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0, 1, 0, 0}, message.getBytesDirect());
  }

  /**
   * Test {@link OrderInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}.
   * <p>
   * Method under test: {@link OrderInterceptor#sendMessage(Member[], ChannelMessage, InterceptorPayload)}
   */
  @Test
  public void testSendMessage2() throws ChannelException {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setNext(new FragmentationInterceptor());
    ChannelData msg = new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L);

    // Act
    orderInterceptor.sendMessage(new Member[]{null}, msg, new InterceptorPayload());

    // Assert
    XByteBuffer message = msg.getMessage();
    assertEquals(1, message.getLength());
    assertEquals(6, message.getCapacity());
    assertArrayEquals(new byte[]{0}, message.getBytes());
    assertArrayEquals(new byte[]{0, 0, 0, 1, 0, 0}, message.getBytesDirect());
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    MessageOrder order = new MessageOrder(1, new ChannelData());

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    assertNull(order.getMessage());
    assertFalse(actualProcessIncomingResult);
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming2() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    MessageOrder order = new MessageOrder(0, new ChannelData());

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    assertNull(order.getMessage());
    assertFalse(actualProcessIncomingResult);
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming3() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setPrevious(new ChannelCoordinator());
    MessageOrder order = new MessageOrder(1, new ChannelData());

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    assertNull(order.getMessage());
    assertFalse(actualProcessIncomingResult);
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming4() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setPrevious(new GroupChannel());
    MessageOrder order = new MessageOrder(1, new ChannelData());

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    assertNull(order.getMessage());
    assertFalse(actualProcessIncomingResult);
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming5() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setPrevious(new GroupChannel());
    MessageOrder order = new MessageOrder(1,
        new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, new XByteBuffer(3, true), 10L));

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    assertNull(order.getMessage());
    assertFalse(actualProcessIncomingResult);
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming6() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setPrevious(new GroupChannel());
    MessageOrder order = new MessageOrder(1, new ChannelData(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1},
        new XByteBuffer(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, true), 10L));

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    assertNull(order.getMessage());
    assertFalse(actualProcessIncomingResult);
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming7() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setPrevious(new GroupChannel());
    MessageOrder order = new MessageOrder(1,
        new ChannelData(null, new XByteBuffer(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}, true), 10L));

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    assertNull(order.getMessage());
    assertFalse(actualProcessIncomingResult);
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <ul>
   *   <li>Given {@link MessageOrder#MessageOrder(int, ChannelMessage)} with msgNr is one and msg is {@link ChannelData#ChannelData()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming_givenMessageOrderWithMsgNrIsOneAndMsgIsChannelData() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setPrevious(new ChannelCoordinator());
    ChannelData msg = new ChannelData();

    MessageOrder order = new MessageOrder(5, msg);
    order.setNext(new MessageOrder(1, new ChannelData()));

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    ChannelMessage message = order.getMessage();
    assertTrue(message instanceof ChannelData);
    assertFalse(actualProcessIncomingResult);
    assertSame(msg, message);
  }

  /**
   * Test {@link OrderInterceptor#processIncoming(MessageOrder)}.
   * <ul>
   *   <li>When {@link MessageOrder#MessageOrder(int, ChannelMessage)} with msgNr is five and msg is {@link ChannelData#ChannelData()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OrderInterceptor#processIncoming(MessageOrder)}
   */
  @Test
  public void testProcessIncoming_whenMessageOrderWithMsgNrIsFiveAndMsgIsChannelData() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setPrevious(new ChannelCoordinator());
    ChannelData msg = new ChannelData();
    MessageOrder order = new MessageOrder(5, msg);

    // Act
    boolean actualProcessIncomingResult = orderInterceptor.processIncoming(order);

    // Assert
    ChannelMessage message = order.getMessage();
    assertTrue(message instanceof ChannelData);
    assertFalse(actualProcessIncomingResult);
    assertSame(msg, message);
  }

  /**
   * Test {@link OrderInterceptor#memberAdded(Member)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then {@link OrderInterceptor} (default constructor) Previous {@link DomainFilterInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OrderInterceptor#memberAdded(Member)}
   */
  @Test
  public void testMemberAdded_givenA_thenOrderInterceptorPreviousDomainFilterInterceptor() {
    // Arrange
    DomainFilterInterceptor previous = new DomainFilterInterceptor();
    previous.setDomain(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.setPrevious(previous);

    // Act
    orderInterceptor.memberAdded(new MemberImpl());

    // Assert
    ChannelInterceptor previous2 = orderInterceptor.getPrevious();
    assertTrue(previous2 instanceof DomainFilterInterceptor);
    assertSame(previous.membership, ((DomainFilterInterceptor) previous2).membership);
  }

  /**
   * Test {@link OrderInterceptor#incCounter(Member)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link OrderInterceptor#incCounter(Member)}
   */
  @Test
  public void testIncCounter_whenNull_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, (new OrderInterceptor()).incCounter(null));
  }

  /**
   * Test {@link OrderInterceptor#getInCounter(Member)}.
   * <ul>
   *   <li>Given {@link OrderInterceptor} (default constructor).</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return Counter is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link OrderInterceptor#getInCounter(Member)}
   */
  @Test
  public void testGetInCounter_givenOrderInterceptor_whenNull_thenReturnCounterIsOne() {
    // Arrange, Act and Assert
    assertEquals(1, (new OrderInterceptor()).getInCounter(null).getCounter());
  }

  /**
   * Test {@link OrderInterceptor#getInCounter(Member)}.
   * <ul>
   *   <li>Then return Counter is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link OrderInterceptor#getInCounter(Member)}
   */
  @Test
  public void testGetInCounter_thenReturnCounterIsTwo() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();
    orderInterceptor.processIncoming(new MessageOrder(1, new ChannelData()));

    // Act and Assert
    assertEquals(2, orderInterceptor.getInCounter(null).getCounter());
  }

  /**
   * Test {@link OrderInterceptor#getOutCounter(Member)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Counter is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link OrderInterceptor#getOutCounter(Member)}
   */
  @Test
  public void testGetOutCounter_whenNull_thenReturnCounterIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new OrderInterceptor()).getOutCounter(null).getCounter());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link OrderInterceptor#setExpire(long)}
   *   <li>{@link OrderInterceptor#setForwardExpired(boolean)}
   *   <li>{@link OrderInterceptor#setMaxQueue(int)}
   *   <li>{@link OrderInterceptor#getExpire()}
   *   <li>{@link OrderInterceptor#getForwardExpired()}
   *   <li>{@link OrderInterceptor#getMaxQueue()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    OrderInterceptor orderInterceptor = new OrderInterceptor();

    // Act
    orderInterceptor.setExpire(1L);
    orderInterceptor.setForwardExpired(true);
    orderInterceptor.setMaxQueue(3);
    long actualExpire = orderInterceptor.getExpire();
    boolean actualForwardExpired = orderInterceptor.getForwardExpired();

    // Assert
    assertEquals(1L, actualExpire);
    assertEquals(3, orderInterceptor.getMaxQueue());
    assertTrue(actualForwardExpired);
  }

  /**
   * Test new {@link OrderInterceptor} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link OrderInterceptor}
   */
  @Test
  public void testNewOrderInterceptor() {
    // Arrange and Act
    OrderInterceptor actualOrderInterceptor = new OrderInterceptor();

    // Assert
    assertNull(actualOrderInterceptor.getMembers());
    assertNull(actualOrderInterceptor.getChannel());
    assertNull(actualOrderInterceptor.getNext());
    assertNull(actualOrderInterceptor.getPrevious());
    assertEquals(0, actualOrderInterceptor.getOptionFlag());
    assertEquals(3000L, actualOrderInterceptor.getExpire());
    assertFalse(actualOrderInterceptor.hasMembers());
    assertTrue(actualOrderInterceptor.getForwardExpired());
    assertEquals(Integer.MAX_VALUE, actualOrderInterceptor.getMaxQueue());
  }
}
