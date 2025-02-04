package org.apache.catalina.users;

import static org.junit.Assert.assertTrue;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.junit.Test;

public class SparseUserDatabaseDiffblueTest {
  /**
   * Test {@link SparseUserDatabase#isSparse()}.
   * <p>
   * Method under test: {@link SparseUserDatabase#isSparse()}
   */
  @Test
  public void testIsSparse() {
    // Arrange, Act and Assert
    assertTrue((new DataSourceUserDatabase(new BasicDataSource(), "42")).isSparse());
  }
}
