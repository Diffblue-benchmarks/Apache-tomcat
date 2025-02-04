package org.apache.catalina.tribes.jmx;

import static org.junit.Assert.assertNull;
import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.group.GroupChannel;
import org.junit.Test;

public class JmxRegistryDiffblueTest {
  /**
   * Test {@link JmxRegistry#getRegistry(Channel)}.
   * <ul>
   *   <li>Given {@code jmxRegistry.no.domain}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JmxRegistry#getRegistry(Channel)}
   */
  @Test
  public void testGetRegistry_givenJmxRegistryNoDomain() {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("Channel");
    channel.setJmxEnabled(true);
    channel.setJmxDomain("jmxRegistry.no.domain");

    // Act and Assert
    assertNull(JmxRegistry.getRegistry(channel).registerJmx("Keyprop", "Bean"));
  }

  /**
   * Test {@link JmxRegistry#getRegistry(Channel)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link GroupChannel} (default constructor) Name is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JmxRegistry#getRegistry(Channel)}
   */
  @Test
  public void testGetRegistry_givenName_whenGroupChannelNameIsName() {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("Name");

    // Act and Assert
    assertNull(JmxRegistry.getRegistry(channel).registerJmx("Keyprop", "Bean"));
  }

  /**
   * Test {@link JmxRegistry#getRegistry(Channel)}.
   * <ul>
   *   <li>Given {@code type=Channel,channel=}.</li>
   *   <li>When {@link GroupChannel} (default constructor) Name is {@code type=Channel,channel=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JmxRegistry#getRegistry(Channel)}
   */
  @Test
  public void testGetRegistry_givenTypeChannelChannel_whenGroupChannelNameIsTypeChannelChannel() {
    // Arrange
    GroupChannel channel = new GroupChannel();
    channel.setName("type=Channel,channel=");
    channel.setJmxEnabled(true);
    channel.setJmxDomain("jmxRegistry.no.domain");

    // Act and Assert
    assertNull(JmxRegistry.getRegistry(channel));
  }

  /**
   * Test {@link JmxRegistry#getRegistry(Channel)}.
   * <ul>
   *   <li>When {@link GroupChannel} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JmxRegistry#getRegistry(Channel)}
   */
  @Test
  public void testGetRegistry_whenGroupChannel_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(JmxRegistry.getRegistry(new GroupChannel()));
  }

  /**
   * Test {@link JmxRegistry#getRegistry(Channel)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JmxRegistry#getRegistry(Channel)}
   */
  @Test
  public void testGetRegistry_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(JmxRegistry.getRegistry(null));
  }
}
