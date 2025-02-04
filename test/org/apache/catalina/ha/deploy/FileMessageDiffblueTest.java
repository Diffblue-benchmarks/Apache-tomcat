package org.apache.catalina.ha.deploy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import java.io.UnsupportedEncodingException;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class FileMessageDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileMessage#FileMessage(Member, String, String)}
   *   <li>{@link FileMessage#setMessageNumber(int)}
   *   <li>{@link FileMessage#setTotalNrOfMsgs(long)}
   *   <li>{@link FileMessage#getContextName()}
   *   <li>{@link FileMessage#getData()}
   *   <li>{@link FileMessage#getDataLength()}
   *   <li>{@link FileMessage#getFileName()}
   *   <li>{@link FileMessage#getMessageNumber()}
   *   <li>{@link FileMessage#getTotalNrOfMsgs()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    MemberImpl source = new MemberImpl();

    // Act
    FileMessage actualFileMessage = new FileMessage(source, "foo.txt", "Context Name");
    actualFileMessage.setMessageNumber(10);
    actualFileMessage.setTotalNrOfMsgs(1L);
    String actualContextName = actualFileMessage.getContextName();
    byte[] actualData = actualFileMessage.getData();
    int actualDataLength = actualFileMessage.getDataLength();
    String actualFileName = actualFileMessage.getFileName();
    int actualMessageNumber = actualFileMessage.getMessageNumber();
    long actualTotalNrOfMsgs = actualFileMessage.getTotalNrOfMsgs();

    // Assert
    assertEquals("Context Name", actualContextName);
    assertEquals("foo.txt", actualFileName);
    assertNull(actualData);
    assertEquals(0, actualDataLength);
    assertEquals(0L, actualFileMessage.getTimestamp());
    assertEquals(10, actualMessageNumber);
    assertEquals(1L, actualTotalNrOfMsgs);
    assertSame(source, actualFileMessage.getAddress());
  }

  /**
   * Test {@link FileMessage#setData(byte[], int)}.
   * <p>
   * Method under test: {@link FileMessage#setData(byte[], int)}
   */
  @Test
  public void testSetData() throws UnsupportedEncodingException {
    // Arrange
    FileMessage fileMessage = new FileMessage(new MemberImpl(), "foo.txt", "Context Name");
    byte[] data = "AXAXAXAX".getBytes("UTF-8");

    // Act
    fileMessage.setData(data, 3);

    // Assert
    assertEquals(3, fileMessage.getDataLength());
    assertSame(data, fileMessage.getData());
  }
}
