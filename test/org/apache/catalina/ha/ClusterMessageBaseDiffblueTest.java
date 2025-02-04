package org.apache.catalina.ha;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.ha.deploy.FileMessage;
import org.apache.catalina.ha.session.SessionMessageImpl;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class ClusterMessageBaseDiffblueTest {
  /**
   * Test {@link ClusterMessageBase#getAddress()}.
   * <p>
   * Method under test: {@link ClusterMessageBase#getAddress()}
   */
  @Test
  public void testGetAddress() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"), "Session ID", "Unique ID"))
        .getAddress());
  }

  /**
   * Test {@link ClusterMessageBase#getTimestamp()}.
   * <p>
   * Method under test: {@link ClusterMessageBase#getTimestamp()}
   */
  @Test
  public void testGetTimestamp() {
    // Arrange, Act and Assert
    assertEquals(0L, (new FileMessage(new MemberImpl(), "foo.txt", "Context Name")).getTimestamp());
  }

  /**
   * Test {@link ClusterMessageBase#setAddress(Member)}.
   * <p>
   * Method under test: {@link ClusterMessageBase#setAddress(Member)}
   */
  @Test
  public void testSetAddress() throws UnsupportedEncodingException {
    // Arrange
    SessionMessageImpl sessionMessageImpl = new SessionMessageImpl("Context Name", 1, "AXAXAXAX".getBytes("UTF-8"),
        "Session ID", "Unique ID");
    MemberImpl member = new MemberImpl();

    // Act
    sessionMessageImpl.setAddress(member);

    // Assert
    assertSame(member, sessionMessageImpl.getAddress());
  }

  /**
   * Test {@link ClusterMessageBase#setTimestamp(long)}.
   * <p>
   * Method under test: {@link ClusterMessageBase#setTimestamp(long)}
   */
  @Test
  public void testSetTimestamp() {
    // Arrange
    FileMessage fileMessage = new FileMessage(new MemberImpl(), "foo.txt", "Context Name");

    // Act
    fileMessage.setTimestamp(10L);

    // Assert
    assertEquals(10L, fileMessage.getTimestamp());
  }
}
