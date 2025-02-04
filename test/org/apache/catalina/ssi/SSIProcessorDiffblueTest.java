package org.apache.catalina.ssi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import org.apache.catalina.connector.RequestFacade;
import org.apache.coyote.Response;
import org.junit.Test;

public class SSIProcessorDiffblueTest {
  /**
   * Test {@link SSIProcessor#SSIProcessor(SSIExternalResolver, int, boolean)}.
   * <ul>
   *   <li>Then {@link SSIProcessor#ssiExternalResolver} return {@link SSIServletExternalResolver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIProcessor#SSIProcessor(SSIExternalResolver, int, boolean)}
   */
  @Test
  public void testNewSSIProcessor_thenSsiExternalResolverReturnSSIServletExternalResolver() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));
    SSIServletExternalResolver ssiExternalResolver = new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8");

    // Act
    SSIProcessor actualSsiProcessor = new SSIProcessor(ssiExternalResolver, 1, true);

    // Assert
    SSIExternalResolver ssiExternalResolver2 = actualSsiProcessor.ssiExternalResolver;
    assertTrue(ssiExternalResolver2 instanceof SSIServletExternalResolver);
    HashMap<String, SSICommand> stringSsiCommandMap = actualSsiProcessor.commands;
    assertEquals(ExpressionTokenizer.TOKEN_END, stringSsiCommandMap.size());
    assertTrue(stringSsiCommandMap.containsKey("echo"));
    assertTrue(stringSsiCommandMap.containsKey("else"));
    assertTrue(stringSsiCommandMap.containsKey("fsize"));
    assertTrue(stringSsiCommandMap.containsKey("include"));
    assertTrue(stringSsiCommandMap.containsKey("set"));
    SSICommand expectedGetResult = stringSsiCommandMap.get("else");
    assertSame(expectedGetResult, stringSsiCommandMap.get("endif"));
    assertSame(ssiExternalResolver.VARIABLE_NAMES, ((SSIServletExternalResolver) ssiExternalResolver2).VARIABLE_NAMES);
    assertSame(ssiExternalResolver.req, ((SSIServletExternalResolver) ssiExternalResolver2).req);
    assertSame(ssiExternalResolver.res, ((SSIServletExternalResolver) ssiExternalResolver2).res);
  }

  /**
   * Test {@link SSIProcessor#SSIProcessor(SSIExternalResolver, int, boolean)}.
   * <ul>
   *   <li>Then {@link SSIProcessor#ssiExternalResolver} return {@link SSIServletExternalResolver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SSIProcessor#SSIProcessor(SSIExternalResolver, int, boolean)}
   */
  @Test
  public void testNewSSIProcessor_thenSsiExternalResolverReturnSSIServletExternalResolver2() {
    // Arrange
    HttpServletRequestWrapper req = new HttpServletRequestWrapper(new RequestFacade(null));
    SSIServletExternalResolver ssiExternalResolver = new SSIServletExternalResolver(null, req,
        new HttpServletResponseWrapper(new org.apache.catalina.connector.Response(new Response())), true, 1, "UTF-8");

    // Act
    SSIProcessor actualSsiProcessor = new SSIProcessor(ssiExternalResolver, 1, true);

    // Assert
    SSIExternalResolver ssiExternalResolver2 = actualSsiProcessor.ssiExternalResolver;
    assertTrue(ssiExternalResolver2 instanceof SSIServletExternalResolver);
    HashMap<String, SSICommand> stringSsiCommandMap = actualSsiProcessor.commands;
    assertEquals(ExpressionTokenizer.TOKEN_END, stringSsiCommandMap.size());
    assertTrue(stringSsiCommandMap.containsKey("echo"));
    assertTrue(stringSsiCommandMap.containsKey("else"));
    assertTrue(stringSsiCommandMap.containsKey("fsize"));
    assertTrue(stringSsiCommandMap.containsKey("include"));
    assertTrue(stringSsiCommandMap.containsKey("set"));
    SSICommand expectedGetResult = stringSsiCommandMap.get("else");
    assertSame(expectedGetResult, stringSsiCommandMap.get("endif"));
    assertSame(ssiExternalResolver.VARIABLE_NAMES, ((SSIServletExternalResolver) ssiExternalResolver2).VARIABLE_NAMES);
    assertSame(ssiExternalResolver.req, ((SSIServletExternalResolver) ssiExternalResolver2).req);
    assertSame(ssiExternalResolver.res, ((SSIServletExternalResolver) ssiExternalResolver2).res);
  }
}
