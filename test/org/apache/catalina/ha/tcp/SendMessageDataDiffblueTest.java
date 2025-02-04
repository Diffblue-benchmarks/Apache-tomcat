package org.apache.catalina.ha.tcp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class SendMessageDataDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SendMessageData#SendMessageData(Object, Member, Exception)}
   *   <li>{@link SendMessageData#getDestination()}
   *   <li>{@link SendMessageData#getException()}
   *   <li>{@link SendMessageData#getMessage()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    MemberImpl destination = new MemberImpl();
    Exception exception = new Exception("foo");

    // Act
    SendMessageData actualSendMessageData = new SendMessageData("Message", destination, exception);
    Member actualDestination = actualSendMessageData.getDestination();
    Exception actualException = actualSendMessageData.getException();

    // Assert
    assertEquals("Message", actualSendMessageData.getMessage());
    assertSame(exception, actualException);
    assertSame(destination, actualDestination);
  }
}
