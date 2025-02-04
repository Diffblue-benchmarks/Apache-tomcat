package org.apache.catalina.tribes.membership.cloud;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class DNSMembershipProviderDiffblueTest {
  /**
   * Test {@link DNSMembershipProvider#stop(int)}.
   * <p>
   * Method under test: {@link DNSMembershipProvider#stop(int)}
   */
  @Test
  public void testStop() throws Exception {
    // Arrange, Act and Assert
    assertTrue((new DNSMembershipProvider()).stop(1));
  }

  /**
   * Test {@link DNSMembershipProvider#fetchMembers()}.
   * <p>
   * Method under test: {@link DNSMembershipProvider#fetchMembers()}
   */
  @Test
  public void testFetchMembers() {
    // Arrange and Act
    Member[] actualFetchMembersResult = (new DNSMembershipProvider()).fetchMembers();

    // Assert
    Member member = actualFetchMembersResult[0];
    assertTrue(member instanceof MemberImpl);
    assertEquals(-1, member.getSecurePort());
    assertEquals(-1, member.getUdpPort());
    assertEquals(-1L, member.getMemberAliveTime());
    assertEquals(0, member.getPort());
    assertEquals(0, ((MemberImpl) member).getMsgCount());
    assertEquals(0L, ((MemberImpl) member).getServiceStartTime());
    assertEquals(1, actualFetchMembersResult.length);
    assertEquals(77, member.getDataLength());
    assertEquals(77, ((MemberImpl) member).getData().length);
    assertFalse(member.isFailing());
    assertFalse(member.isLocal());
    assertFalse(member.isSuspect());
    assertTrue(member.isReady());
    assertArrayEquals(new byte[]{}, member.getCommand());
    assertArrayEquals(new byte[]{}, member.getDomain());
    assertArrayEquals(new byte[]{}, member.getPayload());
    assertArrayEquals(new byte[]{Byte.MAX_VALUE, 0, 0, 1}, member.getHost());
    assertArrayEquals(new byte[]{-11, '(', 'v', 'M', 'b', 'M', -79, ')', -77, ',', '!', -5, -54, '\f', -72, -42},
        member.getUniqueId());
  }

  /**
   * Test new {@link DNSMembershipProvider} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link DNSMembershipProvider}
   */
  @Test
  public void testNewDNSMembershipProvider() {
    // Arrange and Act
    DNSMembershipProvider actualDnsMembershipProvider = new DNSMembershipProvider();

    // Assert
    assertEquals("tomcat", actualDnsMembershipProvider.getNamespace());
    assertNull(actualDnsMembershipProvider.localIp);
    assertNull(actualDnsMembershipProvider.url);
    assertNull(actualDnsMembershipProvider.startTime);
    assertNull(actualDnsMembershipProvider.streamProvider);
    assertEquals(0, actualDnsMembershipProvider.connectionTimeout);
    assertEquals(0, actualDnsMembershipProvider.port);
    assertEquals(0, actualDnsMembershipProvider.readTimeout);
    assertEquals(5000L, actualDnsMembershipProvider.expirationTime);
    assertFalse(actualDnsMembershipProvider.hasMembers());
    assertTrue(actualDnsMembershipProvider.headers.isEmpty());
  }
}
