package org.apache.catalina.tribes.membership.cloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.Properties;
import org.apache.catalina.tribes.MembershipProvider;
import org.apache.catalina.tribes.membership.StaticMembershipProvider;
import org.junit.Test;

public class CloudMembershipServiceDiffblueTest {
  /**
   * Test {@link CloudMembershipService#getProperty(String)}.
   * <p>
   * Method under test: {@link CloudMembershipService#getProperty(String)}
   */
  @Test
  public void testGetProperty() {
    // Arrange, Act and Assert
    assertNull((new CloudMembershipService()).getProperty("Name"));
  }

  /**
   * Test {@link CloudMembershipService#setProperty(String, String)}.
   * <ul>
   *   <li>Then {@link CloudMembershipService} (default constructor) Properties {@code connectTimeout} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#setProperty(String, String)}
   */
  @Test
  public void testSetProperty_thenCloudMembershipServicePropertiesConnectTimeoutIs42() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();
    cloudMembershipService.setConnectTimeout(10);

    // Act
    boolean actualSetPropertyResult = cloudMembershipService.setProperty("connectTimeout", "42");

    // Assert
    Properties properties = cloudMembershipService.getProperties();
    assertEquals(1, properties.size());
    assertEquals("42", properties.get("connectTimeout"));
    assertEquals(42, cloudMembershipService.getConnectTimeout());
    assertFalse(actualSetPropertyResult);
  }

  /**
   * Test {@link CloudMembershipService#setProperty(String, String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then {@link CloudMembershipService} (default constructor) Properties {@code Name} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#setProperty(String, String)}
   */
  @Test
  public void testSetProperty_whenName_thenCloudMembershipServicePropertiesNameIs42() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();

    // Act
    boolean actualSetPropertyResult = cloudMembershipService.setProperty("Name", "42");

    // Assert
    Properties properties = cloudMembershipService.getProperties();
    assertEquals(1, properties.size());
    assertEquals("42", properties.get("Name"));
    assertEquals(1000, cloudMembershipService.getConnectTimeout());
    assertTrue(actualSetPropertyResult);
  }

  /**
   * Test {@link CloudMembershipService#getMembershipProviderClassName()}.
   * <p>
   * Method under test: {@link CloudMembershipService#getMembershipProviderClassName()}
   */
  @Test
  public void testGetMembershipProviderClassName() {
    // Arrange, Act and Assert
    assertNull((new CloudMembershipService()).getMembershipProviderClassName());
  }

  /**
   * Test {@link CloudMembershipService#setMembershipProviderClassName(String)}.
   * <p>
   * Method under test: {@link CloudMembershipService#setMembershipProviderClassName(String)}
   */
  @Test
  public void testSetMembershipProviderClassName() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();

    // Act
    cloudMembershipService.setMembershipProviderClassName("Membership Provider Class Name");

    // Assert
    assertEquals("Membership Provider Class Name", cloudMembershipService.getMembershipProviderClassName());
    Properties properties = cloudMembershipService.getProperties();
    assertEquals(1, properties.size());
    assertEquals("Membership Provider Class Name",
        properties.get(CloudMembershipService.MEMBERSHIP_PROVIDER_CLASS_NAME));
  }

  /**
   * Test {@link CloudMembershipService#stop(int)}.
   * <ul>
   *   <li>Given {@link CloudMembershipService} (default constructor).</li>
   *   <li>Then array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#stop(int)}
   */
  @Test
  public void testStop_givenCloudMembershipService_thenArrayLengthIsZero() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();

    // Act
    cloudMembershipService.stop(1);

    // Assert that nothing has changed
    assertEquals(0, cloudMembershipService.getMembers().length);
    assertEquals(0, cloudMembershipService.getMembersByName().length);
  }

  /**
   * Test {@link CloudMembershipService#stop(int)}.
   * <ul>
   *   <li>Then {@link CloudMembershipService} (default constructor) MembershipProvider is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#stop(int)}
   */
  @Test
  public void testStop_thenCloudMembershipServiceMembershipProviderIsNull() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();
    cloudMembershipService.setMembershipProvider(new DNSMembershipProvider());

    // Act
    cloudMembershipService.stop(1);

    // Assert
    assertNull(cloudMembershipService.getMembershipProvider());
    assertEquals(0, cloudMembershipService.getMembers().length);
    assertEquals(0, cloudMembershipService.getMembersByName().length);
  }

  /**
   * Test {@link CloudMembershipService#stop(int)}.
   * <ul>
   *   <li>Then {@link CloudMembershipService} (default constructor) MembershipProvider {@link StaticMembershipProvider}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#stop(int)}
   */
  @Test
  public void testStop_thenCloudMembershipServiceMembershipProviderStaticMembershipProvider() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();
    StaticMembershipProvider memberProvider = new StaticMembershipProvider();
    cloudMembershipService.setMembershipProvider(memberProvider);

    // Act
    cloudMembershipService.stop(1);

    // Assert that nothing has changed
    MembershipProvider membershipProvider = cloudMembershipService.getMembershipProvider();
    assertTrue(membershipProvider instanceof StaticMembershipProvider);
    assertSame(memberProvider, membershipProvider);
  }

  /**
   * Test {@link CloudMembershipService#getLocalMember(boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_whenFalse() {
    // Arrange, Act and Assert
    assertNull((new CloudMembershipService()).getLocalMember(false));
  }

  /**
   * Test {@link CloudMembershipService#getLocalMember(boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#getLocalMember(boolean)}
   */
  @Test
  public void testGetLocalMember_whenTrue() {
    // Arrange, Act and Assert
    assertNull((new CloudMembershipService()).getLocalMember(true));
  }

  /**
   * Test {@link CloudMembershipService#setLocalMemberProperties(String, int, int, int)}.
   * <ul>
   *   <li>Then {@link CloudMembershipService} (default constructor) Properties size is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#setLocalMemberProperties(String, int, int, int)}
   */
  @Test
  public void testSetLocalMemberProperties_thenCloudMembershipServicePropertiesSizeIsFour() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();

    // Act
    cloudMembershipService.setLocalMemberProperties("42", 8080, 8080, 8080);

    // Assert
    Properties properties = cloudMembershipService.getProperties();
    assertEquals(4, properties.size());
    assertEquals("42", properties.get("tcpListenHost"));
    assertEquals("8080", properties.get("tcpListenPort"));
    assertEquals("8080", properties.get("tcpSecurePort"));
    assertEquals("8080", properties.get("udpListenPort"));
  }

  /**
   * Test {@link CloudMembershipService#getConnectTimeout()}.
   * <ul>
   *   <li>Given {@link CloudMembershipService} (default constructor).</li>
   *   <li>Then return one thousand.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#getConnectTimeout()}
   */
  @Test
  public void testGetConnectTimeout_givenCloudMembershipService_thenReturnOneThousand() {
    // Arrange, Act and Assert
    assertEquals(1000, (new CloudMembershipService()).getConnectTimeout());
  }

  /**
   * Test {@link CloudMembershipService#setConnectTimeout(int)}.
   * <p>
   * Method under test: {@link CloudMembershipService#setConnectTimeout(int)}
   */
  @Test
  public void testSetConnectTimeout() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();

    // Act
    cloudMembershipService.setConnectTimeout(10);

    // Assert
    Properties properties = cloudMembershipService.getProperties();
    assertEquals(1, properties.size());
    assertEquals("10", properties.get("connectTimeout"));
    assertEquals(10, cloudMembershipService.getConnectTimeout());
  }

  /**
   * Test {@link CloudMembershipService#getReadTimeout()}.
   * <ul>
   *   <li>Given {@link CloudMembershipService} (default constructor).</li>
   *   <li>Then return one thousand.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#getReadTimeout()}
   */
  @Test
  public void testGetReadTimeout_givenCloudMembershipService_thenReturnOneThousand() {
    // Arrange, Act and Assert
    assertEquals(1000, (new CloudMembershipService()).getReadTimeout());
  }

  /**
   * Test {@link CloudMembershipService#setReadTimeout(int)}.
   * <p>
   * Method under test: {@link CloudMembershipService#setReadTimeout(int)}
   */
  @Test
  public void testSetReadTimeout() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();

    // Act
    cloudMembershipService.setReadTimeout(10);

    // Assert
    Properties properties = cloudMembershipService.getProperties();
    assertEquals(1, properties.size());
    assertEquals("10", properties.get("readTimeout"));
    assertEquals(10, cloudMembershipService.getReadTimeout());
  }

  /**
   * Test {@link CloudMembershipService#getExpirationTime()}.
   * <ul>
   *   <li>Given {@link CloudMembershipService} (default constructor).</li>
   *   <li>Then return {@code 5000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipService#getExpirationTime()}
   */
  @Test
  public void testGetExpirationTime_givenCloudMembershipService_thenReturn5000() {
    // Arrange, Act and Assert
    assertEquals(5000L, (new CloudMembershipService()).getExpirationTime());
  }

  /**
   * Test {@link CloudMembershipService#setExpirationTime(long)}.
   * <p>
   * Method under test: {@link CloudMembershipService#setExpirationTime(long)}
   */
  @Test
  public void testSetExpirationTime() {
    // Arrange
    CloudMembershipService cloudMembershipService = new CloudMembershipService();

    // Act
    cloudMembershipService.setExpirationTime(1L);

    // Assert
    Properties properties = cloudMembershipService.getProperties();
    assertEquals(1, properties.size());
    assertEquals("1", properties.get("expirationTime"));
    assertEquals(1L, cloudMembershipService.getExpirationTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link CloudMembershipService}
   *   <li>{@link CloudMembershipService#setMembershipProvider(MembershipProvider)}
   *   <li>{@link CloudMembershipService#getMembershipProvider()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    CloudMembershipService actualCloudMembershipService = new CloudMembershipService();
    StaticMembershipProvider memberProvider = new StaticMembershipProvider();
    actualCloudMembershipService.setMembershipProvider(memberProvider);
    MembershipProvider actualMembershipProvider = actualCloudMembershipService.getMembershipProvider();

    // Assert
    assertTrue(actualMembershipProvider instanceof StaticMembershipProvider);
    assertNull(actualCloudMembershipService.getChannel());
    assertSame(memberProvider, actualMembershipProvider);
  }
}
