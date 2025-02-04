package org.apache.catalina.authenticator.jaspic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.security.auth.message.AuthException;
import jakarta.security.auth.message.AuthStatus;
import jakarta.security.auth.message.MessageInfo;
import jakarta.security.auth.message.module.ServerAuthModule;
import java.util.ArrayList;
import java.util.Map;
import javax.security.auth.Subject;
import org.junit.Test;

public class SimpleServerAuthContextDiffblueTest {
  /**
   * Test {@link SimpleServerAuthContext#validateRequest(MessageInfo, Subject, Subject)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link TesterServerAuthModuleA} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleServerAuthContext#validateRequest(MessageInfo, Subject, Subject)}
   */
  @Test
  public void testValidateRequest_givenArrayListAddTesterServerAuthModuleA_thenReturnNull() throws AuthException {
    // Arrange
    ArrayList<ServerAuthModule> modules = new ArrayList<>();
    modules.add(new TesterServerAuthModuleA());
    SimpleServerAuthContext simpleServerAuthContext = new SimpleServerAuthContext(modules);
    MessageInfoImpl messageInfo = new MessageInfoImpl();
    Subject clientSubject = new Subject();

    // Act and Assert
    assertNull(simpleServerAuthContext.validateRequest(messageInfo, clientSubject, new Subject()));
    Map<String, Object> map = messageInfo.getMap();
    assertEquals(1, map.size());
    assertEquals(0, ((Integer) map.get("moduleIndex")).intValue());
  }

  /**
   * Test {@link SimpleServerAuthContext#validateRequest(MessageInfo, Subject, Subject)}.
   * <ul>
   *   <li>When {@link MessageInfoImpl#MessageInfoImpl()}.</li>
   *   <li>Then return toString is {@code SEND_FAILURE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleServerAuthContext#validateRequest(MessageInfo, Subject, Subject)}
   */
  @Test
  public void testValidateRequest_whenMessageInfoImpl_thenReturnToStringIsSendFailure() throws AuthException {
    // Arrange
    SimpleServerAuthContext simpleServerAuthContext = new SimpleServerAuthContext(new ArrayList<>());
    MessageInfoImpl messageInfo = new MessageInfoImpl();
    Subject clientSubject = new Subject();

    // Act
    AuthStatus actualValidateRequestResult = simpleServerAuthContext.validateRequest(messageInfo, clientSubject,
        new Subject());

    // Assert
    assertEquals("SEND_FAILURE", actualValidateRequestResult.toString());
    assertTrue(messageInfo.getMap().isEmpty());
    assertSame(actualValidateRequestResult.SEND_FAILURE, actualValidateRequestResult);
  }

  /**
   * Test {@link SimpleServerAuthContext#cleanSubject(MessageInfo, Subject)}.
   * <ul>
   *   <li>Then {@link MessageInfoImpl#MessageInfoImpl()} Map Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleServerAuthContext#cleanSubject(MessageInfo, Subject)}
   */
  @Test
  public void testCleanSubject_thenMessageInfoImplMapEmpty() throws AuthException {
    // Arrange
    SimpleServerAuthContext simpleServerAuthContext = new SimpleServerAuthContext(new ArrayList<>());
    MessageInfoImpl messageInfo = new MessageInfoImpl();

    // Act
    simpleServerAuthContext.cleanSubject(messageInfo, new Subject());

    // Assert that nothing has changed
    assertTrue(messageInfo.getMap().isEmpty());
  }

  /**
   * Test {@link SimpleServerAuthContext#cleanSubject(MessageInfo, Subject)}.
   * <ul>
   *   <li>Then {@link MessageInfoImpl#MessageInfoImpl()} Map size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleServerAuthContext#cleanSubject(MessageInfo, Subject)}
   */
  @Test
  public void testCleanSubject_thenMessageInfoImplMapSizeIsOne() throws AuthException {
    // Arrange
    ArrayList<ServerAuthModule> modules = new ArrayList<>();
    modules.add(new TesterServerAuthModuleA());
    SimpleServerAuthContext simpleServerAuthContext = new SimpleServerAuthContext(modules);
    MessageInfoImpl messageInfo = new MessageInfoImpl();

    // Act
    simpleServerAuthContext.cleanSubject(messageInfo, new Subject());

    // Assert
    Map<String, Object> map = messageInfo.getMap();
    assertEquals(1, map.size());
    assertEquals("init()-cleanSubject()-", map.get("trace"));
  }
}
