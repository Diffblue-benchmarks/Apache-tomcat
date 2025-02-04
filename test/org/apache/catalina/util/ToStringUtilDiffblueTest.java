package org.apache.catalina.util;

import static org.junit.Assert.assertEquals;
import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Manager;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.ha.session.BackupManager;
import org.junit.Test;

public class ToStringUtilDiffblueTest {
  /**
   * Test {@link ToStringUtil#toString(Contained)} with {@code contained}.
   * <ul>
   *   <li>Then return {@code BasicAuthenticator[Container is null]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ToStringUtil#toString(Contained)}
   */
  @Test
  public void testToStringWithContained_thenReturnBasicAuthenticatorContainerIsNull() {
    // Arrange, Act and Assert
    assertEquals("BasicAuthenticator[Container is null]", ToStringUtil.toString(new BasicAuthenticator()));
  }

  /**
   * Test {@link ToStringUtil#toString(Contained)} with {@code contained}.
   * <ul>
   *   <li>Then return {@code BasicAuthenticator[StandardContext[null]]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ToStringUtil#toString(Contained)}
   */
  @Test
  public void testToStringWithContained_thenReturnBasicAuthenticatorStandardContextNull() {
    // Arrange
    BasicAuthenticator contained = new BasicAuthenticator();
    contained.setContainer(new StandardContext());

    // Act and Assert
    assertEquals("BasicAuthenticator[StandardContext[null]]", ToStringUtil.toString(contained));
  }

  /**
   * Test {@link ToStringUtil#toString(Object, Container)} with {@code obj}, {@code container}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code String[Container is null]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ToStringUtil#toString(Object, Container)}
   */
  @Test
  public void testToStringWithObjContainer_whenObj_thenReturnStringContainerIsNull() {
    // Arrange, Act and Assert
    assertEquals("String[Container is null]", ToStringUtil.toString("Obj", (Container) null));
  }

  /**
   * Test {@link ToStringUtil#toString(Object, Container)} with {@code obj}, {@code container}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code String[StandardContext[null]]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ToStringUtil#toString(Object, Container)}
   */
  @Test
  public void testToStringWithObjContainer_whenObj_thenReturnStringStandardContextNull() {
    // Arrange, Act and Assert
    assertEquals("String[StandardContext[null]]", ToStringUtil.toString("Obj", new StandardContext()));
  }

  /**
   * Test {@link ToStringUtil#toString(Object, Manager)} with {@code obj}, {@code manager}.
   * <ul>
   *   <li>Then return {@code String[BackupManager[StandardContext[null]]]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ToStringUtil#toString(Object, Manager)}
   */
  @Test
  public void testToStringWithObjManager_thenReturnStringBackupManagerStandardContextNull() {
    // Arrange
    BackupManager manager = new BackupManager();
    manager.setContext(new StandardContext());

    // Act and Assert
    assertEquals("String[BackupManager[StandardContext[null]]]", ToStringUtil.toString("Obj", manager));
  }

  /**
   * Test {@link ToStringUtil#toString(Object, Manager)} with {@code obj}, {@code manager}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return {@code String[BackupManager[Container is null]]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ToStringUtil#toString(Object, Manager)}
   */
  @Test
  public void testToStringWithObjManager_whenObj_thenReturnStringBackupManagerContainerIsNull() {
    // Arrange, Act and Assert
    assertEquals("String[BackupManager[Container is null]]", ToStringUtil.toString("Obj", new BackupManager()));
  }
}
