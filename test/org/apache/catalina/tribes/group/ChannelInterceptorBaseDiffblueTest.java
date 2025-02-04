package org.apache.catalina.tribes.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelReceiver;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.TestGroupChannelOptionFlag.TestInterceptor;
import org.apache.catalina.tribes.group.interceptors.DomainFilterInterceptor;
import org.apache.catalina.tribes.group.interceptors.EncryptInterceptor;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.junit.Test;

public class ChannelInterceptorBaseDiffblueTest {
  /**
   * Test {@link ChannelInterceptorBase#okToProcess(int)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()} OptionFlag is zero.</li>
   *   <li>When one.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#okToProcess(int)}
   */
  @Test
  public void testOkToProcess_givenChannelCoordinatorOptionFlagIsZero_whenOne_thenReturnTrue() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setOptionFlag(0);

    // Act and Assert
    assertTrue(channelCoordinator.okToProcess(1));
  }

  /**
   * Test {@link ChannelInterceptorBase#okToProcess(int)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   *   <li>When one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#okToProcess(int)}
   */
  @Test
  public void testOkToProcess_givenChannelCoordinator_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ChannelCoordinator()).okToProcess(1));
  }

  /**
   * Test {@link ChannelInterceptorBase#okToProcess(int)}.
   * <ul>
   *   <li>Given {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   *   <li>When seven.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#okToProcess(int)}
   */
  @Test
  public void testOkToProcess_givenChannelCoordinator_whenSeven_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ChannelCoordinator()).okToProcess(7));
  }

  /**
   * Test {@link ChannelInterceptorBase#getNext()}.
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getNext()}
   */
  @Test
  public void testGetNext() {
    // Arrange, Act and Assert
    assertNull((new ChannelCoordinator()).getNext());
  }

  /**
   * Test {@link ChannelInterceptorBase#setOptionFlag(int)}.
   * <p>
   * Method under test: {@link ChannelInterceptorBase#setOptionFlag(int)}
   */
  @Test
  public void testSetOptionFlag() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();

    // Act
    channelCoordinator.setOptionFlag(1);

    // Assert
    assertEquals(1, channelCoordinator.getOptionFlag());
  }

  /**
   * Test {@link ChannelInterceptorBase#getPrevious()}.
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getPrevious()}
   */
  @Test
  public void testGetPrevious() {
    // Arrange, Act and Assert
    assertNull((new ChannelCoordinator()).getPrevious());
  }

  /**
   * Test {@link ChannelInterceptorBase#getOptionFlag()}.
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getOptionFlag()}
   */
  @Test
  public void testGetOptionFlag() {
    // Arrange, Act and Assert
    assertEquals(7, (new ChannelCoordinator()).getOptionFlag());
  }

  /**
   * Test {@link ChannelInterceptorBase#hasMembers()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#hasMembers()}
   */
  @Test
  public void testHasMembers_givenGroupChannel() {
    // Arrange, Act and Assert
    assertFalse((new GroupChannel()).hasMembers());
  }

  /**
   * Test {@link ChannelInterceptorBase#hasMembers()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addInterceptor {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#hasMembers()}
   */
  @Test
  public void testHasMembers_givenGroupChannelAddInterceptorDomainFilterInterceptor() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addInterceptor(new DomainFilterInterceptor());

    // Act and Assert
    assertFalse(groupChannel.hasMembers());
  }

  /**
   * Test {@link ChannelInterceptorBase#hasMembers()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addInterceptor {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#hasMembers()}
   */
  @Test
  public void testHasMembers_givenGroupChannelAddInterceptorGroupChannel() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addInterceptor(new GroupChannel());

    // Act and Assert
    assertFalse(groupChannel.hasMembers());
  }

  /**
   * Test {@link ChannelInterceptorBase#getMembers()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getMembers()}
   */
  @Test
  public void testGetMembers_givenGroupChannel() {
    // Arrange, Act and Assert
    assertEquals(0, (new GroupChannel()).getMembers().length);
  }

  /**
   * Test {@link ChannelInterceptorBase#getMembers()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addInterceptor {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getMembers()}
   */
  @Test
  public void testGetMembers_givenGroupChannelAddInterceptorDomainFilterInterceptor() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addInterceptor(new DomainFilterInterceptor());

    // Act and Assert
    assertEquals(0, groupChannel.getMembers().length);
  }

  /**
   * Test {@link ChannelInterceptorBase#getMembers()}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addInterceptor {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getMembers()}
   */
  @Test
  public void testGetMembers_givenGroupChannelAddInterceptorGroupChannel() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addInterceptor(new GroupChannel());

    // Act and Assert
    assertEquals(0, groupChannel.getMembers().length);
  }

  /**
   * Test {@link ChannelInterceptorBase#getMember(Member)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getMember(Member)}
   */
  @Test
  public void testGetMember_givenGroupChannel() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();

    // Act and Assert
    assertNull(groupChannel.getMember(new MemberImpl()));
  }

  /**
   * Test {@link ChannelInterceptorBase#getMember(Member)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addInterceptor {@link DomainFilterInterceptor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getMember(Member)}
   */
  @Test
  public void testGetMember_givenGroupChannelAddInterceptorDomainFilterInterceptor() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addInterceptor(new DomainFilterInterceptor());

    // Act and Assert
    assertNull(groupChannel.getMember(new MemberImpl()));
  }

  /**
   * Test {@link ChannelInterceptorBase#getMember(Member)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addInterceptor {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getMember(Member)}
   */
  @Test
  public void testGetMember_givenGroupChannelAddInterceptorGroupChannel() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addInterceptor(new GroupChannel());

    // Act and Assert
    assertNull(groupChannel.getMember(new MemberImpl()));
  }

  /**
   * Test {@link ChannelInterceptorBase#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenGroupChannel() {
    // Arrange, Act and Assert
    assertNull((new GroupChannel()).getLocalMember(true));
  }

  /**
   * Test {@link ChannelInterceptorBase#getLocalMember(boolean)}.
   * <ul>
   *   <li>Given {@link GroupChannel} (default constructor) addInterceptor {@link GroupChannel} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_givenGroupChannelAddInterceptorGroupChannel() {
    // Arrange
    GroupChannel groupChannel = new GroupChannel();
    groupChannel.addInterceptor(new GroupChannel());

    // Act and Assert
    assertNull(groupChannel.getLocalMember(true));
  }

  /**
   * Test {@link ChannelInterceptorBase#start(int)}.
   * <ul>
   *   <li>Given {@link EncryptInterceptor} (default constructor) Previous is {@link ChannelCoordinator#ChannelCoordinator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#start(int)}
   */
  @Test
  public void testStart_givenEncryptInterceptorPreviousIsChannelCoordinator() throws ChannelException {
    // Arrange
    EncryptInterceptor next = new EncryptInterceptor();
    next.setPrevious(new ChannelCoordinator());

    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(next);

    // Act
    domainFilterInterceptor.start(1);

    // Assert that nothing has changed
    assertTrue(domainFilterInterceptor.getNext() instanceof EncryptInterceptor);
  }

  /**
   * Test {@link ChannelInterceptorBase#start(int)}.
   * <ul>
   *   <li>Then {@link DomainFilterInterceptor} (default constructor) Next {@link EncryptInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#start(int)}
   */
  @Test
  public void testStart_thenDomainFilterInterceptorNextEncryptInterceptor() throws ChannelException {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new EncryptInterceptor());

    // Act
    domainFilterInterceptor.start(1);

    // Assert that nothing has changed
    assertTrue(domainFilterInterceptor.getNext() instanceof EncryptInterceptor);
  }

  /**
   * Test {@link ChannelInterceptorBase#start(int)}.
   * <ul>
   *   <li>Then {@link DomainFilterInterceptor} (default constructor) Next {@link TestInterceptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#start(int)}
   */
  @Test
  public void testStart_thenDomainFilterInterceptorNextTestInterceptor() throws ChannelException {
    // Arrange
    DomainFilterInterceptor domainFilterInterceptor = new DomainFilterInterceptor();
    domainFilterInterceptor.setNext(new TestInterceptor());

    // Act
    domainFilterInterceptor.start(1);

    // Assert that nothing has changed
    assertTrue(domainFilterInterceptor.getNext() instanceof TestInterceptor);
  }

  /**
   * Test {@link ChannelInterceptorBase#stop(int)}.
   * <ul>
   *   <li>Then {@link ChannelCoordinator#ChannelCoordinator()} ClusterReceiver {@link NioReceiver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelInterceptorBase#stop(int)}
   */
  @Test
  public void testStop_thenChannelCoordinatorClusterReceiverNioReceiver() throws ChannelException {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    channelCoordinator.setNext(null);
    channelCoordinator.start(2);
    channelCoordinator.setChannel(null);

    // Act
    channelCoordinator.stop(1);

    // Assert
    ChannelReceiver clusterReceiver = channelCoordinator.getClusterReceiver();
    assertTrue(clusterReceiver instanceof NioReceiver);
    assertNull(clusterReceiver.getMessageListener());
    assertNull(((NioReceiver) clusterReceiver).getListener());
  }

  /**
   * Test {@link ChannelInterceptorBase#getChannel()}.
   * <p>
   * Method under test: {@link ChannelInterceptorBase#getChannel()}
   */
  @Test
  public void testGetChannel() {
    // Arrange, Act and Assert
    assertNull((new ChannelCoordinator()).getChannel());
  }

  /**
   * Test {@link ChannelInterceptorBase#setChannel(Channel)}.
   * <p>
   * Method under test: {@link ChannelInterceptorBase#setChannel(Channel)}
   */
  @Test
  public void testSetChannel() {
    // Arrange
    ChannelCoordinator channelCoordinator = new ChannelCoordinator();
    GroupChannel channel = new GroupChannel();

    // Act
    channelCoordinator.setChannel(channel);

    // Assert
    ChannelInterceptor expectedChannel = channel.interceptors;
    assertSame(expectedChannel, channelCoordinator.getChannel());
  }
}
