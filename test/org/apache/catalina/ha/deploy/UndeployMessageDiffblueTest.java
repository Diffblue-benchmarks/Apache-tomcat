package org.apache.catalina.ha.deploy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class UndeployMessageDiffblueTest {
  /**
   * Test {@link UndeployMessage#UndeployMessage(Member, long, String, String)}.
   * <p>
   * Method under test: {@link UndeployMessage#UndeployMessage(Member, long, String, String)}
   */
  @Test
  public void testNewUndeployMessage() {
    // Arrange
    MemberImpl address = new MemberImpl();

    // Act
    UndeployMessage actualUndeployMessage = new UndeployMessage(address, 10L, "42", "Context Name");

    // Assert
    Member address2 = actualUndeployMessage.getAddress();
    assertTrue(address2 instanceof MemberImpl);
    assertEquals("42", actualUndeployMessage.getUniqueId());
    assertEquals("Context Name", actualUndeployMessage.getContextName());
    assertEquals(10L, actualUndeployMessage.getTimestamp());
    assertSame(address, address2);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UndeployMessage#setAddress(Member)}
   *   <li>{@link UndeployMessage#setTimestamp(long)}
   *   <li>{@link UndeployMessage#getAddress()}
   *   <li>{@link UndeployMessage#getContextName()}
   *   <li>{@link UndeployMessage#getTimestamp()}
   *   <li>{@link UndeployMessage#getUniqueId()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    UndeployMessage undeployMessage = new UndeployMessage(new MemberImpl(), 10L, "42", "Context Name");
    MemberImpl address = new MemberImpl();

    // Act
    undeployMessage.setAddress(address);
    undeployMessage.setTimestamp(10L);
    Member actualAddress = undeployMessage.getAddress();
    String actualContextName = undeployMessage.getContextName();
    long actualTimestamp = undeployMessage.getTimestamp();

    // Assert
    assertEquals("42", undeployMessage.getUniqueId());
    assertEquals("Context Name", actualContextName);
    assertEquals(10L, actualTimestamp);
    assertSame(address, actualAddress);
  }
}
