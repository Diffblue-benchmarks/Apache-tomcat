package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.MembershipListener;
import org.apache.catalina.tribes.MembershipService;
import org.apache.catalina.tribes.group.GroupChannel;
import org.junit.Test;

public class MembershipProviderBaseDiffblueTest {
  /**
   * Test {@link MembershipProviderBase#hasMembers()}.
   * <p>
   * Method under test: {@link MembershipProviderBase#hasMembers()}
   */
  @Test
  public void testHasMembers() {
    // Arrange, Act and Assert
    assertFalse((new StaticMembershipProvider()).hasMembers());
  }

  /**
   * Test {@link MembershipProviderBase#setMembershipListener(MembershipListener)}.
   * <p>
   * Method under test: {@link MembershipProviderBase#setMembershipListener(MembershipListener)}
   */
  @Test
  public void testSetMembershipListener() {
    // Arrange
    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();

    // Act
    staticMembershipProvider.setMembershipListener(new SimpleTcpCluster());

    // Assert
    assertTrue(staticMembershipProvider.membershipListener instanceof SimpleTcpCluster);
  }

  /**
   * Test {@link MembershipProviderBase#setMembershipService(MembershipService)}.
   * <ul>
   *   <li>Then {@link StaticMembershipProvider} (default constructor) {@link MembershipProviderBase#service} {@link McastService}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MembershipProviderBase#setMembershipService(MembershipService)}
   */
  @Test
  public void testSetMembershipService_thenStaticMembershipProviderServiceMcastService() {
    // Arrange
    StaticMembershipProvider staticMembershipProvider = new StaticMembershipProvider();

    McastService service = new McastService();
    service.setChannel(new GroupChannel());

    // Act
    staticMembershipProvider.setMembershipService(service);

    // Assert
    assertTrue(staticMembershipProvider.service instanceof McastService);
  }
}
