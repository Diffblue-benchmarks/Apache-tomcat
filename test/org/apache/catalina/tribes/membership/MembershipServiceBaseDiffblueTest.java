package org.apache.catalina.tribes.membership;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.Properties;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.MembershipListener;
import org.apache.catalina.tribes.group.GroupChannel;
import org.apache.catalina.tribes.membership.cloud.CloudMembershipService;
import org.junit.Test;

public class MembershipServiceBaseDiffblueTest {
  /**
   * Test {@link MembershipServiceBase#setProperties(Properties)}.
   * <p>
   * Method under test: {@link MembershipServiceBase#setProperties(Properties)}
   */
  @Test
  public void testSetProperties() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();
    Properties properties = new Properties();

    // Act
    cloudMembershipService.setProperties(properties);

    // Assert
    assertSame(properties, cloudMembershipService.getProperties());
  }

  /**
   * Test {@link MembershipServiceBase#getProperties()}.
   * <p>
   * Method under test: {@link MembershipServiceBase#getProperties()}
   */
  @Test
  public void testGetProperties() {
    // Arrange
    McastService mcastService = new McastService();

    // Act and Assert
    assertSame(mcastService.properties, mcastService.getProperties());
  }

  /**
   * Test {@link MembershipServiceBase#hasMembers()}.
   * <p>
   * Method under test: {@link MembershipServiceBase#hasMembers()}
   */
  @Test
  public void testHasMembers() {
    // Arrange, Act and Assert
    assertFalse((new McastService()).hasMembers());
  }

  /**
   * Test {@link MembershipServiceBase#getMember(Member)}.
   * <p>
   * Method under test: {@link MembershipServiceBase#getMember(Member)}
   */
  @Test
  public void testGetMember() {
    // Arrange
    McastService mcastService = new McastService();

    // Act and Assert
    assertNull(mcastService.getMember(new MemberImpl()));
  }

  /**
   * Test {@link MembershipServiceBase#getMembers()}.
   * <p>
   * Method under test: {@link MembershipServiceBase#getMembers()}
   */
  @Test
  public void testGetMembers() {
    // Arrange, Act and Assert
    assertEquals(0, (new McastService()).getMembers().length);
  }

  /**
   * Test {@link MembershipServiceBase#getMembersByName()}.
   * <p>
   * Method under test: {@link MembershipServiceBase#getMembersByName()}
   */
  @Test
  public void testGetMembersByName() {
    // Arrange, Act and Assert
    assertEquals(0, (new McastService()).getMembersByName().length);
  }

  /**
   * Test {@link MembershipServiceBase#findMemberByName(String)}.
   * <p>
   * Method under test: {@link MembershipServiceBase#findMemberByName(String)}
   */
  @Test
  public void testFindMemberByName() {
    // Arrange, Act and Assert
    assertNull((new McastService()).findMemberByName("Name"));
  }

  /**
   * Test {@link MembershipServiceBase#setMembershipListener(MembershipListener)}.
   * <p>
   * Method under test: {@link MembershipServiceBase#setMembershipListener(MembershipListener)}
   */
  @Test
  public void testSetMembershipListener() {
    // Arrange
    McastService mcastService = new McastService();

    // Act
    mcastService.setMembershipListener(new SimpleTcpCluster());

    // Assert
    assertTrue(mcastService.listener instanceof SimpleTcpCluster);
  }

  /**
   * Test {@link MembershipServiceBase#getChannel()}.
   * <p>
   * Method under test: {@link MembershipServiceBase#getChannel()}
   */
  @Test
  public void testGetChannel() {
    // Arrange, Act and Assert
    assertNull((new McastService()).getChannel());
  }

  /**
   * Test {@link MembershipServiceBase#setChannel(Channel)}.
   * <p>
   * Method under test: {@link MembershipServiceBase#setChannel(Channel)}
   */
  @Test
  public void testSetChannel() {
    // Arrange
    McastService mcastService = new McastService();
    GroupChannel channel = new GroupChannel();

    // Act
    mcastService.setChannel(channel);

    // Assert
    assertSame(channel, mcastService.getChannel());
  }
}
