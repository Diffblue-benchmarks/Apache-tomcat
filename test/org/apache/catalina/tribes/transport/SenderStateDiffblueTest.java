package org.apache.catalina.tribes.transport;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.membership.StaticMember;
import org.junit.Test;

public class SenderStateDiffblueTest {
  /**
   * Test {@link SenderState#getSenderState(Member, boolean)} with {@code member}, {@code create}.
   * <ul>
   *   <li>Given {@code A}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SenderState#getSenderState(Member, boolean)}
   */
  @Test
  public void testGetSenderStateWithMemberCreate_givenA() {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    SenderState actualSenderState = SenderState.getSenderState(member, true);

    // Assert
    assertFalse(actualSenderState.isFailing());
    assertFalse(actualSenderState.isSuspect());
    assertTrue(actualSenderState.isReady());
  }

  /**
   * Test {@link SenderState#getSenderState(Member, boolean)} with {@code member}, {@code create}.
   * <ul>
   *   <li>Given {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SenderState#getSenderState(Member, boolean)}
   */
  @Test
  public void testGetSenderStateWithMemberCreate_givenAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act
    SenderState actualSenderState = SenderState.getSenderState(member, true);

    // Assert
    assertFalse(actualSenderState.isFailing());
    assertFalse(actualSenderState.isSuspect());
    assertTrue(actualSenderState.isReady());
  }

  /**
   * Test {@link SenderState#getSenderState(Member)} with {@code member}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()} Host is array of {@code byte} with {@code A} and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SenderState#getSenderState(Member)}
   */
  @Test
  public void testGetSenderStateWithMember_whenMemberImplHostIsArrayOfByteWithAAndOne() {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    SenderState actualSenderState = SenderState.getSenderState(member);

    // Assert
    assertFalse(actualSenderState.isFailing());
    assertFalse(actualSenderState.isSuspect());
    assertTrue(actualSenderState.isReady());
  }

  /**
   * Test {@link SenderState#getSenderState(Member)} with {@code member}.
   * <ul>
   *   <li>When {@link StaticMember#StaticMember()} Host is array of {@code byte} with {@code A} and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SenderState#getSenderState(Member)}
   */
  @Test
  public void testGetSenderStateWithMember_whenStaticMemberHostIsArrayOfByteWithAAndOne() {
    // Arrange
    StaticMember member = new StaticMember();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    SenderState actualSenderState = SenderState.getSenderState(member);

    // Assert
    assertFalse(actualSenderState.isFailing());
    assertFalse(actualSenderState.isSuspect());
    assertTrue(actualSenderState.isReady());
  }

  /**
   * Test {@link SenderState#isSuspect()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SenderState#isSuspect()}
   */
  @Test
  public void testIsSuspect_givenMemberImplHostIsAxaxaxaxBytesIsUtf8_thenReturnFalse()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertFalse(SenderState.getSenderState(member, true).isSuspect());
  }

  /**
   * Test {@link SenderState#isReady()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SenderState#isReady()}
   */
  @Test
  public void testIsReady_givenMemberImplHostIsAxaxaxaxBytesIsUtf8_thenReturnTrue()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(SenderState.getSenderState(member, true).isReady());
  }

  /**
   * Test {@link SenderState#isFailing()}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Host is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SenderState#isFailing()}
   */
  @Test
  public void testIsFailing_givenMemberImplHostIsAxaxaxaxBytesIsUtf8_thenReturnFalse()
      throws UnsupportedEncodingException {
    // Arrange
    MemberImpl member = new MemberImpl();
    member.setHost("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertFalse(SenderState.getSenderState(member, true).isFailing());
  }
}
