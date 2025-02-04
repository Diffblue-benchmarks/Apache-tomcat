package org.apache.catalina.tribes.membership.cloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.junit.Test;

public class CloudMembershipProviderDiffblueTest {
  /**
   * Test {@link CloudMembershipProvider#getEnv(String[])}.
   * <ul>
   *   <li>When {@code Keys}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudMembershipProvider#getEnv(String[])}
   */
  @Test
  public void testGetEnv_whenKeys_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(CloudMembershipProvider.getEnv("Keys"));
  }

  /**
   * Test {@link CloudMembershipProvider#getNamespace()}.
   * <p>
   * Method under test: {@link CloudMembershipProvider#getNamespace()}
   */
  @Test
  public void testGetNamespace() {
    // Arrange, Act and Assert
    assertEquals("tomcat", (new DNSMembershipProvider()).getNamespace());
  }

  /**
   * Test {@link CloudMembershipProvider#stop(int)}.
   * <p>
   * Method under test: {@link CloudMembershipProvider#stop(int)}
   */
  @Test
  public void testStop() throws Exception {
    // Arrange, Act and Assert
    assertTrue((new DNSMembershipProvider()).stop(1));
  }

  /**
   * Test {@link CloudMembershipProvider#accept(Serializable, Member)}.
   * <p>
   * Method under test: {@link CloudMembershipProvider#accept(Serializable, Member)}
   */
  @Test
  public void testAccept() {
    // Arrange
    KubernetesMembershipProvider kubernetesMembershipProvider = new KubernetesMembershipProvider();
    SimpleDateFormat msg = new SimpleDateFormat("yyyy/mm/dd");

    // Act and Assert
    assertFalse(kubernetesMembershipProvider.accept(msg, new MemberImpl()));
  }
}
