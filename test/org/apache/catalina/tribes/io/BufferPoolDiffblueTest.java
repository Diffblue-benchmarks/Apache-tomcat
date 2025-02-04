package org.apache.catalina.tribes.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BufferPoolDiffblueTest {
  /**
   * Test {@link BufferPool#getBuffer(int, boolean)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return Length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link BufferPool#getBuffer(int, boolean)}
   */
  @Test
  public void testGetBuffer_whenOne_thenReturnLengthIsZero() {
    // Arrange and Act
    XByteBuffer actualBuffer = BufferPool.getBufferPool().getBuffer(1, true);

    // Assert
    assertEquals(0, actualBuffer.getLength());
    assertEquals(1, actualBuffer.getCapacity());
    assertTrue(actualBuffer.getDiscard());
    assertArrayEquals(new byte[]{}, actualBuffer.getBytes());
    assertArrayEquals(new byte[]{0}, actualBuffer.getBytesDirect());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BufferPool#setMaxSize(int)}
   *   <li>{@link BufferPool#getMaxSize()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    BufferPool bufferPool = BufferPool.getBufferPool();

    // Act
    bufferPool.setMaxSize(1);

    // Assert
    assertEquals(1, bufferPool.getMaxSize());
  }
}
