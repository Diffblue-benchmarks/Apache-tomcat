package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.Comparator;
import java.util.HashMap;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.group.AbsoluteOrder;
import org.apache.catalina.tribes.group.AbsoluteOrder.AbsoluteComparator;
import org.apache.catalina.tribes.membership.Membership.MbrEntry;
import org.junit.Test;

public class MembershipDiffblueTest {
  /**
   * Test {@link Membership#clone()}.
   * <p>
   * Method under test: {@link Membership#clone()}
   */
  @Test
  public void testClone() {
    // Arrange and Act
    Membership actualCloneResult = (new Membership(new MemberImpl())).clone();

    // Assert
    assertTrue(actualCloneResult.local instanceof MemberImpl);
    assertEquals(0, actualCloneResult.getMembers().length);
    assertFalse(actualCloneResult.hasMembers());
    assertTrue(actualCloneResult.map.isEmpty());
  }

  /**
   * Test MbrEntry getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MbrEntry#MbrEntry(Member)}
   *   <li>{@link MbrEntry#getMember()}
   * </ul>
   */
  @Test
  public void testMbrEntryGettersAndSetters() {
    // Arrange
    MemberImpl mbr = new MemberImpl();

    // Act and Assert
    assertSame(mbr, (new MbrEntry(mbr)).getMember());
  }

  /**
   * Test MbrEntry {@link MbrEntry#hasExpired(long)}.
   * <ul>
   *   <li>Given {@link MemberImpl#MemberImpl()} Local is {@code true}.</li>
   *   <li>When ten.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MbrEntry#hasExpired(long)}
   */
  @Test
  public void testMbrEntryHasExpired_givenMemberImplLocalIsTrue_whenTen_thenReturnFalse() {
    // Arrange
    MemberImpl mbr = new MemberImpl();
    mbr.setLocal(true);

    // Act and Assert
    assertFalse((new MbrEntry(mbr)).hasExpired(10L));
  }

  /**
   * Test MbrEntry {@link MbrEntry#hasExpired(long)}.
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MbrEntry#hasExpired(long)}
   */
  @Test
  public void testMbrEntryHasExpired_whenMax_value_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new MbrEntry(new MemberImpl())).hasExpired(Long.MAX_VALUE));
  }

  /**
   * Test {@link Membership#Membership(Member)}.
   * <p>
   * Method under test: {@link Membership#Membership(Member)}
   */
  @Test
  public void testNewMembership() {
    // Arrange and Act
    Membership actualMembership = new Membership(new MemberImpl());

    // Assert
    assertTrue(actualMembership.local instanceof MemberImpl);
    assertEquals(0, actualMembership.getMembers().length);
    assertFalse(actualMembership.hasMembers());
    assertTrue(actualMembership.map.isEmpty());
  }

  /**
   * Test {@link Membership#Membership(Member, Comparator)}.
   * <p>
   * Method under test: {@link Membership#Membership(Member, Comparator)}
   */
  @Test
  public void testNewMembership2() {
    // Arrange and Act
    Membership actualMembership = new Membership(new MemberImpl(), AbsoluteOrder.comp);

    // Assert
    assertTrue(actualMembership.memberComparator instanceof AbsoluteComparator);
    assertTrue(actualMembership.local instanceof MemberImpl);
    assertEquals(0, actualMembership.getMembers().length);
    assertFalse(actualMembership.hasMembers());
    assertTrue(actualMembership.map.isEmpty());
  }

  /**
   * Test {@link Membership#Membership(Member, Comparator, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link Membership#local} return {@link MemberImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#Membership(Member, Comparator, boolean)}
   */
  @Test
  public void testNewMembership_whenFalse_thenLocalReturnMemberImpl() {
    // Arrange and Act
    Membership actualMembership = new Membership(new MemberImpl(), AbsoluteOrder.comp, false);

    // Assert
    assertTrue(actualMembership.local instanceof MemberImpl);
    assertEquals(0, actualMembership.getMembers().length);
    assertFalse(actualMembership.hasMembers());
    assertTrue(actualMembership.map.isEmpty());
  }

  /**
   * Test {@link Membership#Membership(Member, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link Membership#local} return {@link MemberImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#Membership(Member, boolean)}
   */
  @Test
  public void testNewMembership_whenFalse_thenLocalReturnMemberImpl2() {
    // Arrange and Act
    Membership actualMembership = new Membership(new MemberImpl(), false);

    // Assert
    assertTrue(actualMembership.local instanceof MemberImpl);
    assertEquals(0, actualMembership.getMembers().length);
    assertFalse(actualMembership.hasMembers());
    assertTrue(actualMembership.map.isEmpty());
  }

  /**
   * Test {@link Membership#Membership(Member, Comparator, boolean)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link Membership#map} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#Membership(Member, Comparator, boolean)}
   */
  @Test
  public void testNewMembership_whenNull_thenReturnMapSizeIsOne() {
    // Arrange and Act
    Membership actualMembership = new Membership(null, AbsoluteOrder.comp, true);

    // Assert
    HashMap<Member, MbrEntry> memberMbrEntryMap = actualMembership.map;
    assertEquals(1, memberMbrEntryMap.size());
    MbrEntry getResult = memberMbrEntryMap.get(null);
    assertNull(getResult.getMember());
    Member[] members = actualMembership.getMembers();
    assertNull(members[0]);
    assertNull(actualMembership.local);
    assertEquals(0L, getResult.lastHeardFrom);
    assertEquals(1, members.length);
    assertTrue(actualMembership.hasMembers());
  }

  /**
   * Test {@link Membership#Membership(Member, boolean)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link Membership#map} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#Membership(Member, boolean)}
   */
  @Test
  public void testNewMembership_whenNull_thenReturnMapSizeIsOne2() {
    // Arrange and Act
    Membership actualMembership = new Membership(null, true);

    // Assert
    HashMap<Member, MbrEntry> memberMbrEntryMap = actualMembership.map;
    assertEquals(1, memberMbrEntryMap.size());
    MbrEntry getResult = memberMbrEntryMap.get(null);
    assertNull(getResult.getMember());
    Member[] members = actualMembership.getMembers();
    assertNull(members[0]);
    assertNull(actualMembership.local);
    assertEquals(0L, getResult.lastHeardFrom);
    assertEquals(1, members.length);
    assertTrue(actualMembership.hasMembers());
  }

  /**
   * Test {@link Membership#memberAlive(Member)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then {@link Membership#Membership(Member)} with local is {@link MemberImpl#MemberImpl()} {@link Membership#map} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#memberAlive(Member)}
   */
  @Test
  public void testMemberAlive_givenA_thenMembershipWithLocalIsMemberImplMapSizeIsOne() {
    // Arrange
    Membership membership = new Membership(new MemberImpl());

    MemberImpl member = new MemberImpl();
    member.setHost(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    boolean actualMemberAliveResult = membership.memberAlive(member);

    // Assert
    assertEquals(1, membership.map.size());
    Member[] members = membership.getMembers();
    assertEquals(1, members.length);
    assertTrue(membership.hasMembers());
    assertTrue(actualMemberAliveResult);
    assertSame(member, members[0]);
  }

  /**
   * Test {@link Membership#memberAlive(Member)}.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#memberAlive(Member)}
   */
  @Test
  public void testMemberAlive_whenMemberImpl_thenArrayLengthIsZero() {
    // Arrange
    Membership membership = new Membership(new MemberImpl());

    // Act
    boolean actualMemberAliveResult = membership.memberAlive(new MemberImpl());

    // Assert
    assertEquals(0, membership.getMembers().length);
    assertFalse(membership.hasMembers());
    assertFalse(actualMemberAliveResult);
    assertTrue(membership.map.isEmpty());
  }

  /**
   * Test {@link Membership#addMember(Member)}.
   * <ul>
   *   <li>Given {@link Membership#Membership(Member)} with local is {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#addMember(Member)}
   */
  @Test
  public void testAddMember_givenMembershipWithLocalIsMemberImpl_thenFirstElementIsNull() {
    // Arrange
    Membership membership = new Membership(new MemberImpl());

    // Act
    membership.addMember(null);

    // Assert
    Member[] members = membership.getMembers();
    assertNull(members[0]);
    HashMap<Member, MbrEntry> memberMbrEntryMap = membership.map;
    assertEquals(1, memberMbrEntryMap.size());
    assertEquals(1, members.length);
    assertTrue(memberMbrEntryMap.containsKey(null));
    assertTrue(membership.hasMembers());
  }

  /**
   * Test {@link Membership#addMember(Member)}.
   * <ul>
   *   <li>Then {@link Membership#Membership(Member, boolean)} with local is {@code null} and includeLocal is {@code true} {@link Membership#map} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#addMember(Member)}
   */
  @Test
  public void testAddMember_thenMembershipWithLocalIsNullAndIncludeLocalIsTrueMapSizeIsOne() {
    // Arrange
    Membership membership = new Membership(null, true);

    // Act
    membership.addMember(null);

    // Assert that nothing has changed
    HashMap<Member, MbrEntry> memberMbrEntryMap = membership.map;
    assertEquals(1, memberMbrEntryMap.size());
    assertEquals(1, membership.getMembers().length);
    assertTrue(memberMbrEntryMap.containsKey(null));
    assertTrue(membership.hasMembers());
  }

  /**
   * Test {@link Membership#expire(long)}.
   * <ul>
   *   <li>Given {@link Membership#Membership(Member)} with local is {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#expire(long)}
   */
  @Test
  public void testExpire_givenMembershipWithLocalIsMemberImpl_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new Membership(new MemberImpl())).expire(10L).length);
  }

  /**
   * Test {@link Membership#hasMembers()}.
   * <ul>
   *   <li>Given {@link Membership#Membership(Member)} with local is {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#hasMembers()}
   */
  @Test
  public void testHasMembers_givenMembershipWithLocalIsMemberImpl_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new Membership(new MemberImpl())).hasMembers());
  }

  /**
   * Test {@link Membership#hasMembers()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#hasMembers()}
   */
  @Test
  public void testHasMembers_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new Membership(null, true)).hasMembers());
  }

  /**
   * Test {@link Membership#getMember(Member)}.
   * <ul>
   *   <li>Given {@link Membership#Membership(Member)} with local is {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#getMember(Member)}
   */
  @Test
  public void testGetMember_givenMembershipWithLocalIsMemberImpl_thenReturnNull() {
    // Arrange
    Membership membership = new Membership(new MemberImpl());

    // Act and Assert
    assertNull(membership.getMember(new MemberImpl()));
  }

  /**
   * Test {@link Membership#contains(Member)}.
   * <ul>
   *   <li>Given {@link Membership#Membership(Member)} with local is {@link MemberImpl#MemberImpl()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Membership#contains(Member)}
   */
  @Test
  public void testContains_givenMembershipWithLocalIsMemberImpl_thenReturnFalse() {
    // Arrange
    Membership membership = new Membership(new MemberImpl());

    // Act and Assert
    assertFalse(membership.contains(new MemberImpl()));
  }

  /**
   * Test {@link Membership#getMembers()}.
   * <p>
   * Method under test: {@link Membership#getMembers()}
   */
  @Test
  public void testGetMembers() {
    // Arrange, Act and Assert
    assertEquals(0, (new Membership(new MemberImpl())).getMembers().length);
  }
}
