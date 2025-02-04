package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.StaticMembershipProvider.MemberMessage;
import org.junit.Test;

public class StaticMembershipProviderDiffblueTest {
  /**
   * Test MemberMessage {@link MemberMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_PING}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberMessage#getTypeDesc()}
   */
  @Test
  public void testMemberMessageGetTypeDesc_thenReturnMsgPing() throws UnsupportedEncodingException {
    // Arrange
    byte[] membershipId = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals("MSG_PING", (new MemberMessage(membershipId, 3, new MemberImpl())).getTypeDesc());
  }

  /**
   * Test MemberMessage {@link MemberMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_START}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberMessage#getTypeDesc()}
   */
  @Test
  public void testMemberMessageGetTypeDesc_thenReturnMsgStart() throws UnsupportedEncodingException {
    // Arrange
    byte[] membershipId = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals("MSG_START", (new MemberMessage(membershipId, 1, new MemberImpl())).getTypeDesc());
  }

  /**
   * Test MemberMessage {@link MemberMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_STOP}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberMessage#getTypeDesc()}
   */
  @Test
  public void testMemberMessageGetTypeDesc_thenReturnMsgStop() throws UnsupportedEncodingException {
    // Arrange
    byte[] membershipId = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals("MSG_STOP", (new MemberMessage(membershipId, 2, new MemberImpl())).getTypeDesc());
  }

  /**
   * Test MemberMessage {@link MemberMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code UNKNOWN}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberMessage#getTypeDesc()}
   */
  @Test
  public void testMemberMessageGetTypeDesc_thenReturnUnknown() throws UnsupportedEncodingException {
    // Arrange
    byte[] membershipId = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals("UNKNOWN", (new MemberMessage(membershipId, 0, new MemberImpl())).getTypeDesc());
  }

  /**
   * Test MemberMessage getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MemberMessage#MemberMessage(byte[], int, Member)}
   *   <li>{@link MemberMessage#setMember(Member)}
   *   <li>{@link MemberMessage#toString()}
   *   <li>{@link MemberMessage#getMember()}
   *   <li>{@link MemberMessage#getMembershipId()}
   *   <li>{@link MemberMessage#getMsgtype()}
   * </ul>
   */
  @Test
  public void testMemberMessageGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    byte[] membershipId = "AXAXAXAX".getBytes("UTF-8");

    // Act
    MemberMessage actualMemberMessage = new MemberMessage(membershipId, 1, new MemberImpl());
    MemberImpl local = new MemberImpl();
    actualMemberMessage.setMember(local);
    String actualToStringResult = actualMemberMessage.toString();
    Member actualMember = actualMemberMessage.getMember();
    byte[] actualMembershipId = actualMemberMessage.getMembershipId();

    // Assert
    assertEquals("MemberMessage[name=AXAXAXAX; type=MSG_START; member=org.apache.catalina.tribes.membership.MemberImpl"
        + "[tcp://{}:0,{},0, alive=0, securePort=-1, UDP Port=-1, id={0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 }, payload={},"
        + " command={}, domain={}]]", actualToStringResult);
    assertEquals(1, actualMemberMessage.getMsgtype());
    assertSame(local, actualMember);
    assertSame(membershipId, actualMembershipId);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMembershipId);
  }

  /**
   * Test {@link StaticMembershipProvider#start(int)}.
   * <p>
   * Method under test: {@link StaticMembershipProvider#start(int)}
   */
  @Test
  public void testStart() throws Exception {
    // Arrange
    ArrayList<StaticMember> staticMembers = new ArrayList<>();
    staticMembers.add(new StaticMember("42", 8080, 4L));

    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    staticMembershipProvider.setStaticMembers(staticMembers);

    // Act
    staticMembershipProvider.start(12);

    // Assert
    assertEquals(12, staticMembershipProvider.startLevel);
  }

  /**
   * Test {@link StaticMembershipProvider#start(int)}.
   * <p>
   * Method under test: {@link StaticMembershipProvider#start(int)}
   */
  @Test
  public void testStart2() throws Exception {
    // Arrange
    ArrayList<StaticMember> staticMembers = new ArrayList<>();
    staticMembers.add(new StaticMember("42", -1, 4L));

    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    staticMembershipProvider.setStaticMembers(staticMembers);

    // Act
    staticMembershipProvider.start(12);

    // Assert
    assertEquals(12, staticMembershipProvider.startLevel);
  }

  /**
   * Test {@link StaticMembershipProvider#start(int)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code null}.</li>
   *   <li>Then {@link StaticMembershipProvider} (default constructor) {@link StaticMembershipProvider#startLevel} is twelve.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipProvider#start(int)}
   */
  @Test
  public void testStart_givenArrayListAddNull_thenStaticMembershipProviderStartLevelIsTwelve() throws Exception {
    // Arrange
    ArrayList<StaticMember> staticMembers = new ArrayList<>();
    staticMembers.add(null);

    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    staticMembershipProvider.setStaticMembers(staticMembers);

    // Act
    staticMembershipProvider.start(12);

    // Assert
    assertEquals(12, staticMembershipProvider.startLevel);
  }

  /**
   * Test {@link StaticMembershipProvider#start(int)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link StaticMember#StaticMember()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipProvider#start(int)}
   */
  @Test
  public void testStart_givenArrayListAddStaticMember() throws Exception {
    // Arrange
    ArrayList<StaticMember> staticMembers = new ArrayList<>();
    staticMembers.add(new StaticMember());

    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    staticMembershipProvider.setStaticMembers(staticMembers);

    // Act
    staticMembershipProvider.start(12);

    // Assert
    assertEquals(12, staticMembershipProvider.startLevel);
  }

  /**
   * Test {@link StaticMembershipProvider#start(int)}.
   * <ul>
   *   <li>Then {@link StaticMembershipProvider} (default constructor) {@link StaticMembershipProvider#startLevel} is twelve.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipProvider#start(int)}
   */
  @Test
  public void testStart_thenStaticMembershipProviderStartLevelIsTwelve() throws Exception {
    // Arrange
    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    staticMembershipProvider.setStaticMembers(new ArrayList<>());

    // Act
    staticMembershipProvider.start(12);

    // Assert
    assertEquals(12, staticMembershipProvider.startLevel);
  }

  /**
   * Test {@link StaticMembershipProvider#start(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then {@link StaticMembershipProvider} (default constructor) {@link StaticMembershipProvider#startLevel} is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipProvider#start(int)}
   */
  @Test
  public void testStart_whenOne_thenStaticMembershipProviderStartLevelIsOne() throws Exception {
    // Arrange
    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();

    // Act
    staticMembershipProvider.start(1);

    // Assert
    assertEquals(1, staticMembershipProvider.startLevel);
  }

  /**
   * Test {@link StaticMembershipProvider#setupMember(Member)}.
   * <p>
   * Method under test: {@link StaticMembershipProvider#setupMember(Member)}
   */
  @Test
  public void testSetupMember() {
    // Arrange
    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    MemberImpl mbr = new MemberImpl();

    // Act and Assert
    assertSame(mbr, staticMembershipProvider.setupMember(mbr));
  }

  /**
   * Test {@link StaticMembershipProvider#accept(Serializable, Member)}.
   * <p>
   * Method under test: {@link StaticMembershipProvider#accept(Serializable, Member)}
   */
  @Test
  public void testAccept() throws UnsupportedEncodingException {
    // Arrange
    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    byte[] membershipId = "AXAXAXAX".getBytes("UTF-8");
    MemberMessage msg = new MemberMessage(membershipId, 1, new MemberImpl());

    // Act and Assert
    assertFalse(staticMembershipProvider.accept(msg, new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipProvider#accept(Serializable, Member)}.
   * <ul>
   *   <li>When {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy/mm/dd}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StaticMembershipProvider#accept(Serializable, Member)}
   */
  @Test
  public void testAccept_whenSimpleDateFormatWithYyyyMmDd() {
    // Arrange
    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    SimpleDateFormat msg = new SimpleDateFormat("yyyy/mm/dd");

    // Act and Assert
    assertFalse(staticMembershipProvider.accept(msg, new MemberImpl()));
  }

  /**
   * Test {@link StaticMembershipProvider#replyRequest(Serializable, Member)}.
   * <p>
   * Method under test: {@link StaticMembershipProvider#replyRequest(Serializable, Member)}
   */
  @Test
  public void testReplyRequest() {
    // Arrange
    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();
    SimpleDateFormat msg = new SimpleDateFormat("yyyy/mm/dd");

    // Act and Assert
    assertNull(staticMembershipProvider.replyRequest(msg, new MemberImpl()));
  }
}
