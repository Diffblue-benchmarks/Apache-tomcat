package org.apache.catalina.tribes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.ChannelException.FaultyMember;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class ChannelExceptionDiffblueTest {
  /**
   * Test FaultyMember {@link FaultyMember#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FaultyMember#equals(Object)}
   */
  @Test
  public void testFaultyMemberEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    FaultyMember faultyMember = new FaultyMember(null, new Exception("foo"));
    MemberImpl mbr = new MemberImpl();

    // Act and Assert
    assertNotEquals(faultyMember, new FaultyMember(mbr, new Exception("foo")));
  }

  /**
   * Test FaultyMember {@link FaultyMember#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FaultyMember#equals(Object)}
   */
  @Test
  public void testFaultyMemberEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    MemberImpl mbr = new MemberImpl();
    FaultyMember faultyMember = new FaultyMember(mbr, new Exception("foo"));

    // Act and Assert
    assertNotEquals(faultyMember, new FaultyMember(null, new Exception("foo")));
  }

  /**
   * Test FaultyMember {@link FaultyMember#equals(Object)}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FaultyMember#equals(Object)}
   */
  @Test
  public void testFaultyMemberEquals_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    MemberImpl mbr = new MemberImpl();
    FaultyMember faultyMember = new FaultyMember(mbr, new Exception("foo"));
    MemberImpl mbr2 = new MemberImpl();

    // Act and Assert
    assertEquals(faultyMember, new FaultyMember(mbr2, new Exception("foo")));
  }

  /**
   * Test FaultyMember {@link FaultyMember#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FaultyMember#equals(Object)}
   */
  @Test
  public void testFaultyMemberEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    MemberImpl mbr = new MemberImpl();

    // Act and Assert
    assertNotEquals(new FaultyMember(mbr, new Exception("foo")), null);
  }

  /**
   * Test FaultyMember {@link FaultyMember#equals(Object)}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FaultyMember#equals(Object)}
   */
  @Test
  public void testFaultyMemberEquals_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    MemberImpl mbr = new MemberImpl();

    // Act and Assert
    assertEquals(new FaultyMember(mbr, new Exception("foo")), new FaultyMember(mbr, new Exception("foo")));
  }

  /**
   * Test FaultyMember {@link FaultyMember#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FaultyMember#equals(Object)}
   */
  @Test
  public void testFaultyMemberEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    MemberImpl mbr = new MemberImpl();

    // Act and Assert
    assertNotEquals(new FaultyMember(mbr, new Exception("foo")), "Different type to FaultyMember");
  }

  /**
   * Test FaultyMember getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FaultyMember#FaultyMember(Member, Exception)}
   *   <li>{@link FaultyMember#toString()}
   *   <li>{@link FaultyMember#getCause()}
   *   <li>{@link FaultyMember#getMember()}
   * </ul>
   */
  @Test
  public void testFaultyMemberGettersAndSetters() {
    // Arrange
    MemberImpl mbr = new MemberImpl();
    Exception x = new Exception("foo");

    // Act
    FaultyMember actualFaultyMember = new FaultyMember(mbr, x);
    String actualToStringResult = actualFaultyMember.toString();
    Exception actualCause = actualFaultyMember.getCause();

    // Assert
    assertEquals(
        "FaultyMember:org.apache.catalina.tribes.membership.MemberImpl[tcp://{}:0,{},0, alive=0, securePort=-1,"
            + " UDP Port=-1, id={0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 }, payload={}, command={}, domain={}]",
        actualToStringResult);
    assertSame(x, actualCause);
    assertSame(mbr, actualFaultyMember.getMember());
  }

  /**
   * Test {@link ChannelException#ChannelException(String)}.
   * <p>
   * Method under test: {@link ChannelException#ChannelException(String)}
   */
  @Test
  public void testNewChannelException() {
    // Arrange and Act
    ChannelException actualChannelException = new ChannelException("An error occurred");

    // Assert
    assertEquals("An error occurred; No faulty members identified.", actualChannelException.getMessage());
    assertNull(actualChannelException.getCause());
    assertEquals(0, actualChannelException.getSuppressed().length);
  }

  /**
   * Test {@link ChannelException#ChannelException(String, Throwable)}.
   * <p>
   * Method under test: {@link ChannelException#ChannelException(String, Throwable)}
   */
  @Test
  public void testNewChannelException2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    ChannelException actualChannelException = new ChannelException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred; No faulty members identified.", actualChannelException.getMessage());
    assertEquals(0, actualChannelException.getSuppressed().length);
    assertSame(cause, actualChannelException.getCause());
  }

  /**
   * Test {@link ChannelException#ChannelException(Throwable)}.
   * <p>
   * Method under test: {@link ChannelException#ChannelException(Throwable)}
   */
  @Test
  public void testNewChannelException3() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    ChannelException actualChannelException = new ChannelException(cause);

    // Assert
    assertEquals("java.lang.Throwable; No faulty members identified.", actualChannelException.getMessage());
    assertEquals(0, actualChannelException.getSuppressed().length);
    assertSame(cause, actualChannelException.getCause());
  }

  /**
   * Test {@link ChannelException#ChannelException()}.
   * <ul>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#ChannelException()}
   */
  @Test
  public void testNewChannelException_thenReturnCauseIsNull() {
    // Arrange and Act
    ChannelException actualChannelException = new ChannelException();

    // Assert
    assertNull(actualChannelException.getCause());
    assertEquals(0, actualChannelException.getSuppressed().length);
  }

  /**
   * Test {@link ChannelException#getMessage()}.
   * <ul>
   *   <li>Then return {@code An error occurred; Faulty members:tcp://{}:0;}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#getMessage()}
   */
  @Test
  public void testGetMessage_thenReturnAnErrorOccurredFaultyMembersTcp0() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");
    MemberImpl mbr = new MemberImpl();
    channelException.addFaultyMember(mbr, new Exception("; No faulty members identified."));

    // Act and Assert
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getMessage());
  }

  /**
   * Test {@link ChannelException#getMessage()}.
   * <ul>
   *   <li>Then return {@code An error occurred; No faulty members identified.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#getMessage()}
   */
  @Test
  public void testGetMessage_thenReturnAnErrorOccurredNoFaultyMembersIdentified() {
    // Arrange, Act and Assert
    assertEquals("An error occurred; No faulty members identified.",
        (new ChannelException("An error occurred")).getMessage());
  }

  /**
   * Test {@link ChannelException#addFaultyMember(Member, Exception)} with {@code mbr}, {@code x}.
   * <ul>
   *   <li>Then first element Member {@link MemberImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#addFaultyMember(Member, Exception)}
   */
  @Test
  public void testAddFaultyMemberWithMbrX_thenFirstElementMemberMemberImpl() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");
    MemberImpl mbr = new MemberImpl();
    Exception x = new Exception("foo");
    channelException.addFaultyMember(mbr, x);
    MemberImpl mbr2 = new MemberImpl();

    // Act
    boolean actualAddFaultyMemberResult = channelException.addFaultyMember(mbr2, new Exception("foo"));

    // Assert
    FaultyMember[] faultyMembers = channelException.getFaultyMembers();
    FaultyMember faultyMember = faultyMembers[0];
    Member member = faultyMember.getMember();
    assertTrue(member instanceof MemberImpl);
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getLocalizedMessage());
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getMessage());
    assertEquals(1, faultyMembers.length);
    assertFalse(actualAddFaultyMemberResult);
    assertSame(x, faultyMember.getCause());
    assertSame(mbr, member);
  }

  /**
   * Test {@link ChannelException#addFaultyMember(Member, Exception)} with {@code mbr}, {@code x}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#addFaultyMember(Member, Exception)}
   */
  @Test
  public void testAddFaultyMemberWithMbrX_thenReturnTrue() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");
    MemberImpl mbr = new MemberImpl();
    Exception x = new Exception("foo");

    // Act
    boolean actualAddFaultyMemberResult = channelException.addFaultyMember(mbr, x);

    // Assert
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getLocalizedMessage());
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getMessage());
    FaultyMember[] faultyMembers = channelException.getFaultyMembers();
    assertEquals(1, faultyMembers.length);
    assertTrue(actualAddFaultyMemberResult);
    FaultyMember faultyMember = faultyMembers[0];
    assertSame(x, faultyMember.getCause());
    assertSame(mbr, faultyMember.getMember());
  }

  /**
   * Test {@link ChannelException#addFaultyMember(FaultyMember)} with {@code mbr}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#addFaultyMember(FaultyMember)}
   */
  @Test
  public void testAddFaultyMemberWithMbr_thenReturnFalse() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");
    MemberImpl mbr = new MemberImpl();
    channelException.addFaultyMember(mbr, new Exception("foo"));
    MemberImpl mbr2 = new MemberImpl();
    FaultyMember mbr3 = new FaultyMember(mbr2, new Exception("foo"));

    // Act
    boolean actualAddFaultyMemberResult = channelException.addFaultyMember(mbr3);

    // Assert
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getLocalizedMessage());
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getMessage());
    FaultyMember[] faultyMembers = channelException.getFaultyMembers();
    assertEquals(1, faultyMembers.length);
    assertFalse(actualAddFaultyMemberResult);
    assertEquals(mbr3, faultyMembers[0]);
  }

  /**
   * Test {@link ChannelException#addFaultyMember(FaultyMember)} with {@code mbr}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#addFaultyMember(FaultyMember)}
   */
  @Test
  public void testAddFaultyMemberWithMbr_thenReturnTrue() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");
    MemberImpl mbr = new MemberImpl();
    FaultyMember mbr2 = new FaultyMember(mbr, new Exception("foo"));

    // Act
    boolean actualAddFaultyMemberResult = channelException.addFaultyMember(mbr2);

    // Assert
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getLocalizedMessage());
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getMessage());
    FaultyMember[] faultyMembers = channelException.getFaultyMembers();
    assertEquals(1, faultyMembers.length);
    assertTrue(actualAddFaultyMemberResult);
    assertSame(mbr2, faultyMembers[0]);
  }

  /**
   * Test {@link ChannelException#addFaultyMember(FaultyMember[])} with {@code mbrs}.
   * <p>
   * Method under test: {@link ChannelException#addFaultyMember(FaultyMember[])}
   */
  @Test
  public void testAddFaultyMemberWithMbrs() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");
    MemberImpl mbr = new MemberImpl();
    channelException.addFaultyMember(mbr, new Exception("foo"));
    MemberImpl mbr2 = new MemberImpl();

    // Act
    int actualAddFaultyMemberResult = channelException
        .addFaultyMember(new FaultyMember[]{new FaultyMember(mbr2, new Exception("foo"))});

    // Assert
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getLocalizedMessage());
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getMessage());
    assertEquals(0, actualAddFaultyMemberResult);
    assertEquals(1, channelException.getFaultyMembers().length);
  }

  /**
   * Test {@link ChannelException#addFaultyMember(FaultyMember[])} with {@code mbrs}.
   * <p>
   * Method under test: {@link ChannelException#addFaultyMember(FaultyMember[])}
   */
  @Test
  public void testAddFaultyMemberWithMbrs2() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");

    // Act
    int actualAddFaultyMemberResult = channelException.addFaultyMember((FaultyMember[]) null);

    // Assert
    assertEquals("An error occurred; No faulty members identified.", channelException.getLocalizedMessage());
    assertEquals("An error occurred; No faulty members identified.", channelException.getMessage());
    assertEquals(0, actualAddFaultyMemberResult);
    assertEquals(0, channelException.getFaultyMembers().length);
  }

  /**
   * Test {@link ChannelException#addFaultyMember(FaultyMember[])} with {@code mbrs}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#addFaultyMember(FaultyMember[])}
   */
  @Test
  public void testAddFaultyMemberWithMbrs_thenReturnOne() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");
    MemberImpl mbr = new MemberImpl();
    FaultyMember faultyMember = new FaultyMember(mbr, new Exception("foo"));

    // Act
    int actualAddFaultyMemberResult = channelException.addFaultyMember(new FaultyMember[]{faultyMember});

    // Assert
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getLocalizedMessage());
    assertEquals("An error occurred; Faulty members:tcp://{}:0; ", channelException.getMessage());
    assertEquals(1, actualAddFaultyMemberResult);
    FaultyMember[] faultyMembers = channelException.getFaultyMembers();
    assertEquals(1, faultyMembers.length);
    assertSame(faultyMember, faultyMembers[0]);
  }

  /**
   * Test {@link ChannelException#getFaultyMembers()}.
   * <ul>
   *   <li>Then first element Member return {@link MemberImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#getFaultyMembers()}
   */
  @Test
  public void testGetFaultyMembers_thenFirstElementMemberReturnMemberImpl() {
    // Arrange
    ChannelException channelException = new ChannelException("An error occurred");
    MemberImpl mbr = new MemberImpl();
    Exception x = new Exception("foo");
    channelException.addFaultyMember(mbr, x);

    // Act
    FaultyMember[] actualFaultyMembers = channelException.getFaultyMembers();

    // Assert
    FaultyMember faultyMember = actualFaultyMembers[0];
    Member member = faultyMember.getMember();
    assertTrue(member instanceof MemberImpl);
    assertEquals(1, actualFaultyMembers.length);
    assertSame(x, faultyMember.getCause());
    assertSame(mbr, member);
  }

  /**
   * Test {@link ChannelException#getFaultyMembers()}.
   * <ul>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChannelException#getFaultyMembers()}
   */
  @Test
  public void testGetFaultyMembers_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new ChannelException("An error occurred")).getFaultyMembers().length);
  }
}
