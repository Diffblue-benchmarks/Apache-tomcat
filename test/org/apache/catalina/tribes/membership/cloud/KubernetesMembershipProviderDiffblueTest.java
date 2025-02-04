package org.apache.catalina.tribes.membership.cloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class KubernetesMembershipProviderDiffblueTest {
  /**
   * Test {@link KubernetesMembershipProvider#stop(int)}.
   * <p>
   * Method under test: {@link KubernetesMembershipProvider#stop(int)}
   */
  @Test
  public void testStop() throws Exception {
    // Arrange, Act and Assert
    assertTrue((new KubernetesMembershipProvider()).stop(1));
  }

  /**
   * Test {@link KubernetesMembershipProvider#fetchMembers()}.
   * <p>
   * Method under test: {@link KubernetesMembershipProvider#fetchMembers()}
   */
  @Test
  public void testFetchMembers() {
    // Arrange, Act and Assert
    assertEquals(0, (new KubernetesMembershipProvider()).fetchMembers().length);
  }

  /**
   * Test new {@link KubernetesMembershipProvider} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link KubernetesMembershipProvider}
   */
  @Test
  public void testNewKubernetesMembershipProvider() {
    // Arrange and Act
    KubernetesMembershipProvider actualKubernetesMembershipProvider = new KubernetesMembershipProvider();

    // Assert
    assertEquals("tomcat", actualKubernetesMembershipProvider.getNamespace());
    assertNull(actualKubernetesMembershipProvider.localIp);
    assertNull(actualKubernetesMembershipProvider.url);
    assertNull(actualKubernetesMembershipProvider.startTime);
    assertNull(actualKubernetesMembershipProvider.streamProvider);
    assertEquals(0, actualKubernetesMembershipProvider.connectionTimeout);
    assertEquals(0, actualKubernetesMembershipProvider.port);
    assertEquals(0, actualKubernetesMembershipProvider.readTimeout);
    assertEquals(5000L, actualKubernetesMembershipProvider.expirationTime);
    assertFalse(actualKubernetesMembershipProvider.hasMembers());
    assertTrue(actualKubernetesMembershipProvider.headers.isEmpty());
  }
}
