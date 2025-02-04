package org.apache.catalina.security;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SecurityListenerDiffblueTest {
  /**
   * Test new {@link SecurityListener} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SecurityListener}
   */
  @Test
  public void testNewSecurityListener() {
    // Arrange and Act
    SecurityListener actualSecurityListener = new SecurityListener();

    // Assert
    assertEquals("0007", actualSecurityListener.getMinimumUmask());
    assertEquals("root", actualSecurityListener.getCheckedOsUsers());
    assertEquals(-1, actualSecurityListener.getBuildDateWarningAgeDays());
  }

  /**
   * Test {@link SecurityListener#setCheckedOsUsers(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityListener#setCheckedOsUsers(String)}
   */
  @Test
  public void testSetCheckedOsUsers_whenEmptyString() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setCheckedOsUsers("");

    // Assert
    assertEquals("", securityListener.getCheckedOsUsers());
  }

  /**
   * Test {@link SecurityListener#setCheckedOsUsers(String)}.
   * <ul>
   *   <li>When {@code janedoe}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityListener#setCheckedOsUsers(String)}
   */
  @Test
  public void testSetCheckedOsUsers_whenJanedoe() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setCheckedOsUsers("janedoe");

    // Assert
    assertEquals("janedoe,root", securityListener.getCheckedOsUsers());
  }

  /**
   * Test {@link SecurityListener#setCheckedOsUsers(String)}.
   * <ul>
   *   <li>When {@code ,janedoe}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityListener#setCheckedOsUsers(String)}
   */
  @Test
  public void testSetCheckedOsUsers_whenJanedoe2() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setCheckedOsUsers(",janedoe");

    // Assert
    assertEquals("janedoe,root", securityListener.getCheckedOsUsers());
  }

  /**
   * Test {@link SecurityListener#setCheckedOsUsers(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link SecurityListener} (default constructor) CheckedOsUsers is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityListener#setCheckedOsUsers(String)}
   */
  @Test
  public void testSetCheckedOsUsers_whenNull_thenSecurityListenerCheckedOsUsersIsEmptyString() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setCheckedOsUsers(null);

    // Assert
    assertEquals("", securityListener.getCheckedOsUsers());
  }

  /**
   * Test {@link SecurityListener#getCheckedOsUsers()}.
   * <p>
   * Method under test: {@link SecurityListener#getCheckedOsUsers()}
   */
  @Test
  public void testGetCheckedOsUsers() {
    // Arrange, Act and Assert
    assertEquals("root", (new SecurityListener()).getCheckedOsUsers());
  }

  /**
   * Test {@link SecurityListener#setMinimumUmask(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then {@link SecurityListener} (default constructor) MinimumUmask is {@code 0042}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityListener#setMinimumUmask(String)}
   */
  @Test
  public void testSetMinimumUmask_when42_thenSecurityListenerMinimumUmaskIs0042() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setMinimumUmask("42");

    // Assert
    assertEquals("0042", securityListener.getMinimumUmask());
  }

  /**
   * Test {@link SecurityListener#setMinimumUmask(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then {@link SecurityListener} (default constructor) MinimumUmask is {@code 0000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityListener#setMinimumUmask(String)}
   */
  @Test
  public void testSetMinimumUmask_whenEmptyString_thenSecurityListenerMinimumUmaskIs0000() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setMinimumUmask("");

    // Assert
    assertEquals("0000", securityListener.getMinimumUmask());
  }

  /**
   * Test {@link SecurityListener#setMinimumUmask(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link SecurityListener} (default constructor) MinimumUmask is {@code 0000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityListener#setMinimumUmask(String)}
   */
  @Test
  public void testSetMinimumUmask_whenNull_thenSecurityListenerMinimumUmaskIs0000() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setMinimumUmask(null);

    // Assert
    assertEquals("0000", securityListener.getMinimumUmask());
  }

  /**
   * Test {@link SecurityListener#getMinimumUmask()}.
   * <p>
   * Method under test: {@link SecurityListener#getMinimumUmask()}
   */
  @Test
  public void testGetMinimumUmask() {
    // Arrange, Act and Assert
    assertEquals("0007", (new SecurityListener()).getMinimumUmask());
  }

  /**
   * Test {@link SecurityListener#setBuildDateWarningAgeDays(String)}.
   * <p>
   * Method under test: {@link SecurityListener#setBuildDateWarningAgeDays(String)}
   */
  @Test
  public void testSetBuildDateWarningAgeDays() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setBuildDateWarningAgeDays("Age Days");

    // Assert that nothing has changed
    assertEquals(-1, securityListener.getBuildDateWarningAgeDays());
  }

  /**
   * Test {@link SecurityListener#setBuildDateWarningAgeDays(String)}.
   * <p>
   * Method under test: {@link SecurityListener#setBuildDateWarningAgeDays(String)}
   */
  @Test
  public void testSetBuildDateWarningAgeDays2() {
    // Arrange
    SecurityListener securityListener = new SecurityListener();

    // Act
    securityListener.setBuildDateWarningAgeDays("42");

    // Assert
    assertEquals(42, securityListener.getBuildDateWarningAgeDays());
  }

  /**
   * Test {@link SecurityListener#getBuildDateWarningAgeDays()}.
   * <p>
   * Method under test: {@link SecurityListener#getBuildDateWarningAgeDays()}
   */
  @Test
  public void testGetBuildDateWarningAgeDays() {
    // Arrange, Act and Assert
    assertEquals(-1, (new SecurityListener()).getBuildDateWarningAgeDays());
  }
}
