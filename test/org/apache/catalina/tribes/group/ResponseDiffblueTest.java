package org.apache.catalina.tribes.group;

import static org.junit.Assert.assertSame;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class ResponseDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Response#Response()}
   *   <li>{@link Response#setMessage(Serializable)}
   *   <li>{@link Response#setSource(Member)}
   *   <li>{@link Response#getMessage()}
   *   <li>{@link Response#getSource()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    Response actualResponse = new Response();
    SimpleDateFormat message = new SimpleDateFormat("yyyy/mm/dd");
    actualResponse.setMessage(message);
    MemberImpl source = new MemberImpl();
    actualResponse.setSource(source);
    Serializable actualMessage = actualResponse.getMessage();

    // Assert
    assertSame(message, actualMessage);
    assertSame(source, actualResponse.getSource());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link MemberImpl#MemberImpl()}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Response#Response(Member, Serializable)}
   *   <li>{@link Response#setMessage(Serializable)}
   *   <li>{@link Response#setSource(Member)}
   *   <li>{@link Response#getMessage()}
   *   <li>{@link Response#getSource()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters_whenMemberImpl() {
    // Arrange
    MemberImpl source = new MemberImpl();

    // Act
    Response actualResponse = new Response(source, new SimpleDateFormat("yyyy/mm/dd"));
    SimpleDateFormat message = new SimpleDateFormat("yyyy/mm/dd");
    actualResponse.setMessage(message);
    MemberImpl source2 = new MemberImpl();
    actualResponse.setSource(source2);
    Serializable actualMessage = actualResponse.getMessage();

    // Assert
    assertSame(message, actualMessage);
    assertSame(source2, actualResponse.getSource());
  }
}
