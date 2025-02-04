package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.catalina.tribes.transport.nio.NioReceiver;
import org.junit.Test;

public class TimeBucketCounterDiffblueTest {
  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_givenNioReceiver() {
    // Arrange
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleWithFixedDelay(new NioReceiver(), 1000L, 1000L, TimeUnit.NANOSECONDS);

    // Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(0,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(executor));

    // Assert
    assertEquals(0, actualTimeBucketCounter.getNumBits());
    assertEquals(0.0d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1, actualTimeBucketCounter.getActualDuration());
    assertEquals(1L, actualTimeBucketCounter.getMillisUntilNextBucket());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) addEvent {@link NioReceiver} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_givenNioReceiverAddEventNioReceiver() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.addEvent(new NioReceiver());

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleWithFixedDelay(nioReceiver, 1000L, 1000L, TimeUnit.NANOSECONDS);

    // Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(0,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(executor));

    // Assert
    assertEquals(0, actualTimeBucketCounter.getNumBits());
    assertEquals(0.0d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1, actualTimeBucketCounter.getActualDuration());
    assertEquals(1L, actualTimeBucketCounter.getMillisUntilNextBucket());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Bind is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_givenNioReceiverBindIsNull() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setBind(null);
    nioReceiver.addEvent(new NioReceiver());

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleWithFixedDelay(nioReceiver, 1000L, 1000L, TimeUnit.NANOSECONDS);

    // Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(0,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(executor));

    // Assert
    assertEquals(0, actualTimeBucketCounter.getNumBits());
    assertEquals(0.0d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1, actualTimeBucketCounter.getActualDuration());
    assertEquals(1L, actualTimeBucketCounter.getMillisUntilNextBucket());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Executor is {@link StandardThreadExecutor} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_givenNioReceiverExecutorIsStandardThreadExecutor() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setExecutor(new StandardThreadExecutor());

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleWithFixedDelay(nioReceiver, 1000L, 1000L, TimeUnit.NANOSECONDS);

    // Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(0,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(executor));

    // Assert
    assertEquals(0, actualTimeBucketCounter.getNumBits());
    assertEquals(0.0d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1, actualTimeBucketCounter.getActualDuration());
    assertEquals(1L, actualTimeBucketCounter.getMillisUntilNextBucket());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) Timeout is ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_givenNioReceiverTimeoutIsTen() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setTimeout(10);
    nioReceiver.addEvent(new NioReceiver());

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleWithFixedDelay(nioReceiver, 1000L, 1000L, TimeUnit.NANOSECONDS);

    // Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(0,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(executor));

    // Assert
    assertEquals(0, actualTimeBucketCounter.getNumBits());
    assertEquals(0.0d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1, actualTimeBucketCounter.getActualDuration());
    assertEquals(1L, actualTimeBucketCounter.getMillisUntilNextBucket());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>Given {@link NioReceiver} (default constructor) TxBufSize is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_givenNioReceiverTxBufSizeIsThree() {
    // Arrange
    NioReceiver event = new NioReceiver();
    event.setTxBufSize(3);

    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setBind(null);
    nioReceiver.addEvent(event);

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleWithFixedDelay(nioReceiver, 1000L, 1000L, TimeUnit.NANOSECONDS);

    // Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(0,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(executor));

    // Assert
    assertEquals(0, actualTimeBucketCounter.getNumBits());
    assertEquals(0.0d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1, actualTimeBucketCounter.getActualDuration());
    assertEquals(1L, actualTimeBucketCounter.getMillisUntilNextBucket());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>Given {@link ScheduledThreadPoolExecutor#ScheduledThreadPoolExecutor(int)} with one thousand.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_givenScheduledThreadPoolExecutorWithOneThousand() {
    // Arrange
    NioReceiver nioReceiver = new NioReceiver();
    nioReceiver.setExecutor(
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(new ScheduledThreadPoolExecutor(1000)));

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleWithFixedDelay(nioReceiver, 1000L, 1000L, TimeUnit.NANOSECONDS);

    // Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(0,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(executor));

    // Assert
    assertEquals(0, actualTimeBucketCounter.getNumBits());
    assertEquals(0.0d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1, actualTimeBucketCounter.getActualDuration());
    assertEquals(1L, actualTimeBucketCounter.getMillisUntilNextBucket());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>When one thousand.</li>
   *   <li>Then return Ratio is {@code 1.049}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_whenOneThousand_thenReturnRatioIs1049() {
    // Arrange and Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(1000,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(new ScheduledThreadPoolExecutor(1)));

    // Assert
    assertEquals(1.049d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1048576, actualTimeBucketCounter.getActualDuration());
    assertEquals(20, actualTimeBucketCounter.getNumBits());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return Ratio is {@code 1.024}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_whenOne_thenReturnRatioIs1024() {
    // Arrange and Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(1,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(new ScheduledThreadPoolExecutor(1)));

    // Assert
    assertEquals(1.024d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(10, actualTimeBucketCounter.getNumBits());
    assertEquals(1024, actualTimeBucketCounter.getActualDuration());
  }

  /**
   * Test {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return NumBits is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#TimeBucketCounter(int, ScheduledExecutorService)}
   */
  @Test
  public void testNewTimeBucketCounter_whenZero_thenReturnNumBitsIsZero() {
    // Arrange and Act
    TimeBucketCounter actualTimeBucketCounter = new TimeBucketCounter(0,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(new ScheduledThreadPoolExecutor(1)));

    // Assert
    assertEquals(0, actualTimeBucketCounter.getNumBits());
    assertEquals(0.0d, actualTimeBucketCounter.getRatio(), 0.0);
    assertEquals(1, actualTimeBucketCounter.getActualDuration());
    assertEquals(1L, actualTimeBucketCounter.getMillisUntilNextBucket());
  }

  /**
   * Test {@link TimeBucketCounter#increment(String)}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#increment(String)}
   */
  @Test
  public void testIncrement_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1,
        (new TimeBucketCounter(1,
            new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(new ScheduledThreadPoolExecutor(1))))
            .increment("42"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TimeBucketCounter#getNumBits()}
   *   <li>{@link TimeBucketCounter#getRatio()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    TimeBucketCounter timeBucketCounter = new TimeBucketCounter(1,
        new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(new ScheduledThreadPoolExecutor(1)));

    // Act
    int actualNumBits = timeBucketCounter.getNumBits();

    // Assert
    assertEquals(1.024d, timeBucketCounter.getRatio(), 0.0);
    assertEquals(10, actualNumBits);
  }

  /**
   * Test {@link TimeBucketCounter#getActualDuration()}.
   * <p>
   * Method under test: {@link TimeBucketCounter#getActualDuration()}
   */
  @Test
  public void testGetActualDuration() {
    // Arrange, Act and Assert
    assertEquals(1024,
        (new TimeBucketCounter(1,
            new org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor(new ScheduledThreadPoolExecutor(1))))
            .getActualDuration());
  }

  /**
   * Test {@link TimeBucketCounter#ratioToPowerOf2(int)}.
   * <ul>
   *   <li>When forty-two.</li>
   *   <li>Then return {@code 1.524}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#ratioToPowerOf2(int)}
   */
  @Test
  public void testRatioToPowerOf2_whenFortyTwo_thenReturn1524() {
    // Arrange, Act and Assert
    assertEquals(1.524d, TimeBucketCounter.ratioToPowerOf2(42), 0.0);
  }

  /**
   * Test {@link TimeBucketCounter#ratioToPowerOf2(int)}.
   * <ul>
   *   <li>When {@link Integer#SIZE}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#ratioToPowerOf2(int)}
   */
  @Test
  public void testRatioToPowerOf2_whenSize_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1.0d, TimeBucketCounter.ratioToPowerOf2(Integer.SIZE), 0.0);
  }

  /**
   * Test {@link TimeBucketCounter#nextPowerOf2(int)}.
   * <ul>
   *   <li>When forty-two.</li>
   *   <li>Then return {@link Double#SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#nextPowerOf2(int)}
   */
  @Test
  public void testNextPowerOf2_whenFortyTwo_thenReturnSize() {
    // Arrange, Act and Assert
    assertEquals(Double.SIZE, TimeBucketCounter.nextPowerOf2(42));
  }

  /**
   * Test {@link TimeBucketCounter#nextPowerOf2(int)}.
   * <ul>
   *   <li>When {@link Integer#SIZE}.</li>
   *   <li>Then return {@link Integer#SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeBucketCounter#nextPowerOf2(int)}
   */
  @Test
  public void testNextPowerOf2_whenSize_thenReturnSize() {
    // Arrange, Act and Assert
    assertEquals(Integer.SIZE, TimeBucketCounter.nextPowerOf2(Integer.SIZE));
  }
}
