package org.apache.catalina.tribes.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

public class DirectByteArrayOutputStreamDiffblueTest {
  /**
   * Test {@link DirectByteArrayOutputStream#DirectByteArrayOutputStream(int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DirectByteArrayOutputStream#DirectByteArrayOutputStream(int)}
   */
  @Test
  public void testNewDirectByteArrayOutputStream_whenThree_thenReturnSizeIsZero() {
    // Arrange and Act
    DirectByteArrayOutputStream actualDirectByteArrayOutputStream = new DirectByteArrayOutputStream(3);

    // Assert
    assertEquals(0, actualDirectByteArrayOutputStream.size());
    assertArrayEquals(new byte[]{}, actualDirectByteArrayOutputStream.getArray());
    assertArrayEquals(new byte[]{0, 0, 0}, actualDirectByteArrayOutputStream.getArrayDirect());
  }

  /**
   * Test {@link DirectByteArrayOutputStream#write(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link DirectByteArrayOutputStream#DirectByteArrayOutputStream(int)} with size is three size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DirectByteArrayOutputStream#write(int)}
   */
  @Test
  public void testWriteWithInt_thenDirectByteArrayOutputStreamWithSizeIsThreeSizeIsOne() throws IOException {
    // Arrange
    DirectByteArrayOutputStream directByteArrayOutputStream = new DirectByteArrayOutputStream(3);

    // Act
    directByteArrayOutputStream.write(19088743);

    // Assert
    assertEquals(1, directByteArrayOutputStream.size());
    assertArrayEquals(new byte[]{'g'}, directByteArrayOutputStream.getArray());
    assertArrayEquals(new byte[]{'g', 0, 0}, directByteArrayOutputStream.getArrayDirect());
  }

  /**
   * Test {@link DirectByteArrayOutputStream#write(int)} with {@code int}.
   * <ul>
   *   <li>Then {@link DirectByteArrayOutputStream#DirectByteArrayOutputStream(int)} with size is zero size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DirectByteArrayOutputStream#write(int)}
   */
  @Test
  public void testWriteWithInt_thenDirectByteArrayOutputStreamWithSizeIsZeroSizeIsOne() throws IOException {
    // Arrange
    DirectByteArrayOutputStream directByteArrayOutputStream = new DirectByteArrayOutputStream(0);

    // Act
    directByteArrayOutputStream.write(19088743);

    // Assert
    assertEquals(1, directByteArrayOutputStream.size());
    assertArrayEquals(new byte[]{'g'}, directByteArrayOutputStream.getArray());
    assertArrayEquals(new byte[]{'g'}, directByteArrayOutputStream.getArrayDirect());
  }

  /**
   * Test {@link DirectByteArrayOutputStream#size()}.
   * <p>
   * Method under test: {@link DirectByteArrayOutputStream#size()}
   */
  @Test
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new DirectByteArrayOutputStream(3)).size());
  }

  /**
   * Test {@link DirectByteArrayOutputStream#getArrayDirect()}.
   * <p>
   * Method under test: {@link DirectByteArrayOutputStream#getArrayDirect()}
   */
  @Test
  public void testGetArrayDirect() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0}, (new DirectByteArrayOutputStream(3)).getArrayDirect());
  }

  /**
   * Test {@link DirectByteArrayOutputStream#getArray()}.
   * <p>
   * Method under test: {@link DirectByteArrayOutputStream#getArray()}
   */
  @Test
  public void testGetArray() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{}, (new DirectByteArrayOutputStream(3)).getArray());
  }
}
