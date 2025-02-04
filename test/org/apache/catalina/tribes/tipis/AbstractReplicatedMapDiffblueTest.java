package org.apache.catalina.tribes.tipis;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import org.apache.catalina.ha.session.DeltaSession;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapEntry;
import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapMessage;
import org.junit.Test;

public class AbstractReplicatedMapDiffblueTest {
  /**
   * Test MapEntry {@link MapEntry#apply(byte[], int, int, boolean)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then not {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Proxy.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#apply(byte[], int, int, boolean)}
   */
  @Test
  public void testMapEntryApply_whenMinusOne_thenNotMapEntryWithKeyAndValueProxy()
      throws IOException, ClassNotFoundException {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act
    mapEntry.apply("AXAXAXAX".getBytes("UTF-8"), 2, -1, true);

    // Assert that nothing has changed
    assertFalse(mapEntry.isDiffable());
    assertFalse(mapEntry.isProxy());
    assertTrue(mapEntry.isActive());
    assertTrue(mapEntry.isPrimary());
  }

  /**
   * Test MapEntry {@link MapEntry#apply(byte[], int, int, boolean)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then not {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Proxy.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#apply(byte[], int, int, boolean)}
   */
  @Test
  public void testMapEntryApply_whenOne_thenNotMapEntryWithKeyAndValueProxy()
      throws IOException, ClassNotFoundException {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setValue(new DeltaSession());

    // Act
    mapEntry.apply(null, 2, 1, false);

    // Assert
    assertFalse(mapEntry.isDiffable());
    assertFalse(mapEntry.isProxy());
    assertTrue(mapEntry.isActive());
    assertTrue(mapEntry.isPrimary());
  }

  /**
   * Test MapEntry {@link MapEntry#apply(byte[], int, int, boolean)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then not {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Active.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#apply(byte[], int, int, boolean)}
   */
  @Test
  public void testMapEntryApply_whenZero_thenNotMapEntryWithKeyAndValueActive()
      throws IOException, ClassNotFoundException {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setValue(new DeltaSession());

    // Act
    mapEntry.apply(null, 2, 0, false);

    // Assert
    assertFalse(mapEntry.isActive());
    assertFalse(mapEntry.isDiffable());
    assertFalse(mapEntry.isPrimary());
    assertTrue(mapEntry.isProxy());
  }

  /**
   * Test MapEntry {@link MapEntry#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#equals(Object)}
   */
  @Test
  public void testMapEntryEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertNotEquals(mapEntry, new MapEntry<>("Key", "Value"));
  }

  /**
   * Test MapEntry {@link MapEntry#equals(Object)}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#equals(Object)}
   */
  @Test
  public void testMapEntryEquals_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>(42, "Value");

    // Act and Assert
    assertEquals(mapEntry, 42);
  }

  /**
   * Test MapEntry {@link MapEntry#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#equals(Object)}
   */
  @Test
  public void testMapEntryEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertNotEquals(mapEntry, null);
  }

  /**
   * Test MapEntry {@link MapEntry#equals(Object)}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#equals(Object)}
   */
  @Test
  public void testMapEntryEquals_whenOtherIsSame_thenReturnNotEqual() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertNotEquals(mapEntry, mapEntry);
  }

  /**
   * Test MapEntry {@link MapEntry#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#equals(Object)}
   */
  @Test
  public void testMapEntryEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertNotEquals(mapEntry, "Different type to MapEntry");
  }

  /**
   * Test MapEntry getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MapEntry#setBackup(boolean)}
   *   <li>{@link MapEntry#setBackupNodes(Member[])}
   *   <li>{@link MapEntry#setCopy(boolean)}
   *   <li>{@link MapEntry#setKey(Object)}
   *   <li>{@link MapEntry#setPrimary(Member)}
   *   <li>{@link MapEntry#setProxy(boolean)}
   *   <li>{@link MapEntry#setValue(Object)}
   *   <li>{@link MapEntry#toString()}
   *   <li>{@link MapEntry#getBackupNodes()}
   *   <li>{@link MapEntry#getKey()}
   *   <li>{@link MapEntry#getPrimary()}
   *   <li>{@link MapEntry#getValue()}
   *   <li>{@link MapEntry#isBackup()}
   *   <li>{@link MapEntry#isCopy()}
   *   <li>{@link MapEntry#isProxy()}
   * </ul>
   */
  @Test
  public void testMapEntryGettersAndSetters() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act
    mapEntry.setBackup(true);
    Member[] nodes = new Member[]{new MemberImpl()};
    mapEntry.setBackupNodes(nodes);
    mapEntry.setCopy(true);
    Object actualSetKeyResult = mapEntry.setKey("Key");
    MemberImpl m = new MemberImpl();
    mapEntry.setPrimary(m);
    mapEntry.setProxy(true);
    Object actualSetValueResult = mapEntry.setValue("Value");
    String actualToStringResult = mapEntry.toString();
    Member[] actualBackupNodes = mapEntry.getBackupNodes();
    Object actualKey = mapEntry.getKey();
    Member actualPrimary = mapEntry.getPrimary();
    Object actualValue = mapEntry.getValue();
    boolean actualIsBackupResult = mapEntry.isBackup();
    boolean actualIsCopyResult = mapEntry.isCopy();

    // Assert
    assertEquals("Key", actualKey);
    assertEquals("Key", actualSetKeyResult);
    assertEquals("MapEntry[key:Key; value:Value; primary:false; backup:true; proxy:true;]", actualToStringResult);
    assertEquals("Value", actualValue);
    assertEquals("Value", actualSetValueResult);
    assertTrue(actualIsBackupResult);
    assertTrue(actualIsCopyResult);
    assertTrue(mapEntry.isProxy());
    assertSame(m, actualPrimary);
    assertSame(nodes, actualBackupNodes);
  }

  /**
   * Test MapEntry {@link MapEntry#isActive()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Proxy is {@code true}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isActive()}
   */
  @Test
  public void testMapEntryIsActive_givenMapEntryWithKeyAndValueProxyIsTrue_thenReturnFalse() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setProxy(true);

    // Act and Assert
    assertFalse(mapEntry.isActive());
  }

  /**
   * Test MapEntry {@link MapEntry#isActive()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isActive()}
   */
  @Test
  public void testMapEntryIsActive_givenMapEntryWithKeyAndValue_thenReturnTrue() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertTrue(mapEntry.isActive());
  }

  /**
   * Test MapEntry {@link MapEntry#isDiffable()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isDiffable()}
   */
  @Test
  public void testMapEntryIsDiffable_givenMapEntryWithKeyAndValue_thenReturnFalse() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertFalse(mapEntry.isDiffable());
  }

  /**
   * Test MapEntry {@link MapEntry#isDiffable()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isDiffable()}
   */
  @Test
  public void testMapEntryIsDiffable_thenReturnTrue() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setValue(new DeltaSession());

    // Act and Assert
    assertTrue(mapEntry.isDiffable());
  }

  /**
   * Test MapEntry {@link MapEntry#isKeySerializable()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isKeySerializable()}
   */
  @Test
  public void testMapEntryIsKeySerializable_givenMapEntryWithKeyAndValue() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertTrue(mapEntry.isKeySerializable());
  }

  /**
   * Test MapEntry {@link MapEntry#isKeySerializable()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Key is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isKeySerializable()}
   */
  @Test
  public void testMapEntryIsKeySerializable_givenMapEntryWithKeyAndValueKeyIsNull() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setKey(null);

    // Act and Assert
    assertTrue(mapEntry.isKeySerializable());
  }

  /**
   * Test MapEntry {@link MapEntry#isPrimary()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Backup is {@code true}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isPrimary()}
   */
  @Test
  public void testMapEntryIsPrimary_givenMapEntryWithKeyAndValueBackupIsTrue_thenReturnFalse() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setBackup(true);

    // Act and Assert
    assertFalse(mapEntry.isPrimary());
  }

  /**
   * Test MapEntry {@link MapEntry#isPrimary()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Copy is {@code true}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isPrimary()}
   */
  @Test
  public void testMapEntryIsPrimary_givenMapEntryWithKeyAndValueCopyIsTrue_thenReturnFalse() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setCopy(true);

    // Act and Assert
    assertFalse(mapEntry.isPrimary());
  }

  /**
   * Test MapEntry {@link MapEntry#isPrimary()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Proxy is {@code true}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isPrimary()}
   */
  @Test
  public void testMapEntryIsPrimary_givenMapEntryWithKeyAndValueProxyIsTrue_thenReturnFalse() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setProxy(true);

    // Act and Assert
    assertFalse(mapEntry.isPrimary());
  }

  /**
   * Test MapEntry {@link MapEntry#isPrimary()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isPrimary()}
   */
  @Test
  public void testMapEntryIsPrimary_givenMapEntryWithKeyAndValue_thenReturnTrue() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertTrue(mapEntry.isPrimary());
  }

  /**
   * Test MapEntry {@link MapEntry#isSerializable()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isSerializable()}
   */
  @Test
  public void testMapEntryIsSerializable_givenMapEntryWithKeyAndValue() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertTrue(mapEntry.isSerializable());
  }

  /**
   * Test MapEntry {@link MapEntry#isSerializable()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Key is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isSerializable()}
   */
  @Test
  public void testMapEntryIsSerializable_givenMapEntryWithKeyAndValueKeyIsNull() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setKey(null);
    mapEntry.setValue(null);

    // Act and Assert
    assertTrue(mapEntry.isSerializable());
  }

  /**
   * Test MapEntry {@link MapEntry#isValueSerializable()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isValueSerializable()}
   */
  @Test
  public void testMapEntryIsValueSerializable_givenMapEntryWithKeyAndValue() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");

    // Act and Assert
    assertTrue(mapEntry.isValueSerializable());
  }

  /**
   * Test MapEntry {@link MapEntry#isValueSerializable()}.
   * <ul>
   *   <li>Given {@link MapEntry#MapEntry(Object, Object)} with {@code Key} and {@code Value} Value is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapEntry#isValueSerializable()}
   */
  @Test
  public void testMapEntryIsValueSerializable_givenMapEntryWithKeyAndValueValueIsNull() {
    // Arrange
    MapEntry<Object, Object> mapEntry = new MapEntry<>("Key", "Value");
    mapEntry.setValue(null);

    // Act and Assert
    assertTrue(mapEntry.isValueSerializable());
  }

  /**
   * Test MapEntry {@link MapEntry#MapEntry(Object, Object)}.
   * <p>
   * Method under test: {@link MapEntry#MapEntry(Object, Object)}
   */
  @Test
  public void testMapEntryNewMapEntry() {
    // Arrange and Act
    MapEntry<Object, Object> actualMapEntry = new MapEntry<>("Key", "Value");

    // Assert
    assertEquals("Key", actualMapEntry.getKey());
    assertEquals("Value", actualMapEntry.getValue());
    assertNull(actualMapEntry.getBackupNodes());
    assertNull(actualMapEntry.getPrimary());
    assertFalse(actualMapEntry.isBackup());
    assertFalse(actualMapEntry.isCopy());
    assertFalse(actualMapEntry.isDiffable());
    assertFalse(actualMapEntry.isProxy());
    assertTrue(actualMapEntry.isActive());
    assertTrue(actualMapEntry.isKeySerializable());
    assertTrue(actualMapEntry.isPrimary());
    assertTrue(actualMapEntry.isSerializable());
    assertTrue(actualMapEntry.isValueSerializable());
  }

  /**
   * Test MapMessage {@link MapMessage#clone()}.
   * <p>
   * Method under test: {@link MapMessage#clone()}
   */
  @Test
  public void testMapMessageClone() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act
    MapMessage actualCloneResult = (new MapMessage(mapId, 1, true, key, value, diffvalue, primary,
        new Member[]{new MemberImpl()})).clone();

    // Assert
    Serializable key2 = actualCloneResult.getKey();
    assertTrue(key2 instanceof SimpleDateFormat);
    Member primary2 = actualCloneResult.getPrimary();
    assertTrue(primary2 instanceof MemberImpl);
    assertEquals("MSG_BACKUP", actualCloneResult.getTypeDesc());
    assertEquals(1, actualCloneResult.getMsgType());
    assertEquals(1, actualCloneResult.getBackupNodes().length);
    assertTrue(actualCloneResult.isDiff());
    assertSame(key, key2);
    assertSame(value, actualCloneResult.getValue());
    assertSame(primary, primary2);
    byte[] expectedDiffValue = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedDiffValue, actualCloneResult.getDiffValue());
    byte[] expectedMapId = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedMapId, actualCloneResult.getMapId());
  }

  /**
   * Test MapMessage {@link MapMessage#getKey()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getKey()}
   */
  @Test
  public void testMapMessageGetKey_thenReturnNull() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertNull(
        (new MapMessage(mapId, 1, true, null, value, diffvalue, primary, new Member[]{new MemberImpl()})).getKey());
  }

  /**
   * Test MapMessage {@link MapMessage#getKey()}.
   * <ul>
   *   <li>Then return {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy/mm/dd}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getKey()}
   */
  @Test
  public void testMapMessageGetKey_thenReturnSimpleDateFormatWithYyyyMmDd() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertSame(key,
        (new MapMessage(mapId, 1, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getKey());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_BACKUP}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgBackup() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_BACKUP",
        (new MapMessage(mapId, 1, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_COPY}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgCopy() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_COPY", (new MapMessage(mapId, MapMessage.MSG_COPY, true, key, value, diffvalue, primary,
        new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_INIT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgInit() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_INIT",
        (new MapMessage(mapId, 8, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_PROXY}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgProxy() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_PROXY",
        (new MapMessage(mapId, 3, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_REMOVE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgRemove() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_REMOVE",
        (new MapMessage(mapId, 4, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_RETRIEVE_BACKUP}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgRetrieveBackup() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_RETRIEVE_BACKUP",
        (new MapMessage(mapId, 2, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_START}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgStart() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_START",
        (new MapMessage(mapId, 6, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_STATE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgState() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_STATE",
        (new MapMessage(mapId, 5, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code MSG_STOP}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnMsgStop() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("MSG_STOP",
        (new MapMessage(mapId, 7, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getTypeDesc()}.
   * <ul>
   *   <li>Then return {@code UNKNOWN}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getTypeDesc()}
   */
  @Test
  public void testMapMessageGetTypeDesc_thenReturnUnknown() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertEquals("UNKNOWN",
        (new MapMessage(mapId, 0, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getTypeDesc());
  }

  /**
   * Test MapMessage {@link MapMessage#getValue()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getValue()}
   */
  @Test
  public void testMapMessageGetValue_thenReturnNull() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertNull(
        (new MapMessage(mapId, 1, true, key, null, diffvalue, primary, new Member[]{new MemberImpl()})).getValue());
  }

  /**
   * Test MapMessage {@link MapMessage#getValue()}.
   * <ul>
   *   <li>Then return {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy/mm/dd}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#getValue()}
   */
  @Test
  public void testMapMessageGetValue_thenReturnSimpleDateFormatWithYyyyMmDd() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act and Assert
    assertSame(value,
        (new MapMessage(mapId, 1, true, key, value, diffvalue, primary, new Member[]{new MemberImpl()})).getValue());
  }

  /**
   * Test MapMessage getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MapMessage#toString()}
   *   <li>{@link MapMessage#getBackupNodes()}
   *   <li>{@link MapMessage#getDiffValue()}
   *   <li>{@link MapMessage#getKeyData()}
   *   <li>{@link MapMessage#getMapId()}
   *   <li>{@link MapMessage#getMsgType()}
   *   <li>{@link MapMessage#getPrimary()}
   *   <li>{@link MapMessage#getValueData()}
   *   <li>{@link MapMessage#isDiff()}
   * </ul>
   */
  @Test
  public void testMapMessageGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();
    MemberImpl memberImpl = new MemberImpl();
    MapMessage mapMessage = new MapMessage(mapId, 1, true, key, value, diffvalue, primary, new Member[]{memberImpl});

    // Act
    mapMessage.toString();
    Member[] actualBackupNodes = mapMessage.getBackupNodes();
    byte[] actualDiffValue = mapMessage.getDiffValue();
    byte[] actualKeyData = mapMessage.getKeyData();
    byte[] actualMapId = mapMessage.getMapId();
    int actualMsgType = mapMessage.getMsgType();
    Member actualPrimary = mapMessage.getPrimary();
    byte[] actualValueData = mapMessage.getValueData();

    // Assert
    assertEquals((byte) -19, actualKeyData[1]);
    assertEquals((byte) -19, actualValueData[1]);
    assertEquals((byte) -84, actualKeyData[0]);
    assertEquals((byte) -84, actualValueData[0]);
    assertEquals((byte) 0, actualKeyData[2]);
    assertEquals((byte) 0, actualKeyData[46325]);
    assertEquals((byte) 0, actualKeyData[6]);
    assertEquals((byte) 0, actualValueData[2]);
    assertEquals((byte) 0, actualValueData[46325]);
    assertEquals((byte) 0, actualValueData[6]);
    assertEquals(1, actualMsgType);
    assertEquals(1, actualBackupNodes.length);
    assertEquals((byte) 26, actualKeyData[7]);
    assertEquals((byte) 26, actualValueData[7]);
    assertEquals((byte) 5, actualKeyData[3]);
    assertEquals((byte) 5, actualValueData[3]);
    assertTrue(mapMessage.isDiff());
    assertEquals('.', actualKeyData[17]);
    assertEquals('.', actualKeyData[MapMessage.MSG_NOTIFY_MAPMEMBER]);
    assertEquals('.', actualValueData[17]);
    assertEquals('.', actualValueData[MapMessage.MSG_NOTIFY_MAPMEMBER]);
    assertEquals('D', actualKeyData[Float.PRECISION]);
    assertEquals('D', actualValueData[Float.PRECISION]);
    assertEquals('S', actualKeyData[18]);
    assertEquals('S', actualValueData[18]);
    assertEquals('a', actualKeyData[MapMessage.MSG_ACCESS]);
    assertEquals('a', actualKeyData[MapMessage.MSG_COPY]);
    assertEquals('a', actualValueData[MapMessage.MSG_ACCESS]);
    assertEquals('a', actualValueData[MapMessage.MSG_COPY]);
    assertEquals('e', actualKeyData[14]);
    assertEquals('e', actualKeyData[23]);
    assertEquals('e', actualValueData[14]);
    assertEquals('e', actualValueData[23]);
    assertEquals('i', actualKeyData[19]);
    assertEquals('i', actualValueData[19]);
    assertEquals('j', actualKeyData[8]);
    assertEquals('j', actualValueData[8]);
    assertEquals('l', actualKeyData[22]);
    assertEquals('l', actualValueData[22]);
    assertEquals('m', actualKeyData[20]);
    assertEquals('m', actualValueData[20]);
    assertEquals('p', actualKeyData[21]);
    assertEquals('p', actualValueData[21]);
    assertEquals('r', actualKeyData[5]);
    assertEquals('r', actualValueData[5]);
    assertEquals('s', actualKeyData[4]);
    assertEquals('s', actualValueData[4]);
    assertEquals('t', actualKeyData[AbstractReplicatedMap.DEFAULT_INITIAL_CAPACITY]);
    assertEquals('t', actualKeyData[MapMessage.MSG_PING]);
    assertEquals('t', actualValueData[AbstractReplicatedMap.DEFAULT_INITIAL_CAPACITY]);
    assertEquals('t', actualValueData[MapMessage.MSG_PING]);
    assertEquals('v', actualKeyData[MapMessage.MSG_STATE_COPY]);
    assertEquals('v', actualValueData[MapMessage.MSG_STATE_COPY]);
    assertEquals('x', actualKeyData[15]);
    assertEquals('x', actualValueData[15]);
    assertSame(primary, actualPrimary);
    assertSame(memberImpl, actualBackupNodes[0]);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualDiffValue);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMapId);
  }

  /**
   * Test MapMessage {@link MapMessage#key(ClassLoader[])}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#key(ClassLoader[])}
   */
  @Test
  public void testMapMessageKey_thenReturnNull() throws IOException, ClassNotFoundException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();
    MapMessage mapMessage = new MapMessage(mapId, 1, true, null, value, diffvalue, primary,
        new Member[]{new MemberImpl()});

    // Act and Assert
    assertNull(mapMessage.key(new ClassLoader[]{new ParallelWebappClassLoader()}));
  }

  /**
   * Test MapMessage {@link MapMessage#key(ClassLoader[])}.
   * <ul>
   *   <li>Then return {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy/mm/dd}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#key(ClassLoader[])}
   */
  @Test
  public void testMapMessageKey_thenReturnSimpleDateFormatWithYyyyMmDd() throws IOException, ClassNotFoundException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();
    MapMessage mapMessage = new MapMessage(mapId, 1, true, key, value, diffvalue, primary,
        new Member[]{new MemberImpl()});

    // Act and Assert
    assertSame(key, mapMessage.key(new ClassLoader[]{new ParallelWebappClassLoader()}));
  }

  /**
   * Test MapMessage {@link MapMessage#MapMessage(byte[], int, boolean, Serializable, Serializable, byte[], Member, Member[])}.
   * <ul>
   *   <li>Then Key return {@link SimpleDateFormat}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#MapMessage(byte[], int, boolean, Serializable, Serializable, byte[], Member, Member[])}
   */
  @Test
  public void testMapMessageNewMapMessage_thenKeyReturnSimpleDateFormat() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();

    // Act
    MapMessage actualMapMessage = new MapMessage(mapId, 1, true, key, value, diffvalue, primary,
        new Member[]{new MemberImpl()});

    // Assert
    Serializable key2 = actualMapMessage.getKey();
    assertTrue(key2 instanceof SimpleDateFormat);
    assertSame(key, key2);
    assertSame(value, actualMapMessage.getValue());
    byte[] expectedDiffValue = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedDiffValue, actualMapMessage.getDiffValue());
    byte[] expectedMapId = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedMapId, actualMapMessage.getMapId());
  }

  /**
   * Test MapMessage {@link MapMessage#MapMessage(byte[], int, boolean, Serializable, Serializable, byte[], Member, Member[])}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then Primary return {@link MemberImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#MapMessage(byte[], int, boolean, Serializable, Serializable, byte[], Member, Member[])}
   */
  @Test
  public void testMapMessageNewMapMessage_whenA_thenPrimaryReturnMemberImpl() throws UnsupportedEncodingException {
    // Arrange
    MemberImpl primary = new MemberImpl();

    // Act
    MapMessage actualMapMessage = new MapMessage(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 1, true, null,
        null, new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, primary, new Member[]{new MemberImpl()});

    // Assert
    Member primary2 = actualMapMessage.getPrimary();
    assertTrue(primary2 instanceof MemberImpl);
    Member[] backupNodes = actualMapMessage.getBackupNodes();
    Member member = backupNodes[0];
    assertTrue(member instanceof MemberImpl);
    assertNull(actualMapMessage.getKeyData());
    assertNull(actualMapMessage.getValueData());
    assertNull(actualMapMessage.getKey());
    assertNull(actualMapMessage.getValue());
    assertEquals(1, backupNodes.length);
    assertEquals(primary2, member);
    byte[] expectedDiffValue = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedDiffValue, actualMapMessage.getDiffValue());
    byte[] expectedMapId = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedMapId, actualMapMessage.getMapId());
    assertArrayEquals(new byte[]{}, primary2.getCommand());
    assertArrayEquals(new byte[]{}, primary2.getDomain());
    assertArrayEquals(new byte[]{}, primary2.getHost());
    assertArrayEquals(new byte[]{}, primary2.getPayload());
    assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, primary2.getUniqueId());
  }

  /**
   * Test MapMessage {@link MapMessage#setKey(Serializable)}.
   * <p>
   * Method under test: {@link MapMessage#setKey(Serializable)}
   */
  @Test
  public void testMapMessageSetKey() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();
    MapMessage mapMessage = new MapMessage(mapId, 1, true, key, value, diffvalue, primary,
        new Member[]{new MemberImpl()});

    // Act
    mapMessage.setKey(null);

    // Assert
    Serializable key2 = mapMessage.getKey();
    assertTrue(key2 instanceof SimpleDateFormat);
    assertNull(mapMessage.getKeyData());
    assertEquals(key, key2);
  }

  /**
   * Test MapMessage {@link MapMessage#setKey(Serializable)}.
   * <ul>
   *   <li>When {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy/mm/dd}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#setKey(Serializable)}
   */
  @Test
  public void testMapMessageSetKey_whenSimpleDateFormatWithYyyyMmDd() throws UnsupportedEncodingException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();
    MapMessage mapMessage = new MapMessage(mapId, 1, true, key, value, diffvalue, primary,
        new Member[]{new MemberImpl()});
    SimpleDateFormat key2 = new SimpleDateFormat("yyyy/mm/dd");

    // Act
    mapMessage.setKey(key2);

    // Assert that nothing has changed
    assertSame(key2, mapMessage.getKey());
  }

  /**
   * Test MapMessage {@link MapMessage#value(ClassLoader[])}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#value(ClassLoader[])}
   */
  @Test
  public void testMapMessageValue_thenReturnNull() throws IOException, ClassNotFoundException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();
    MapMessage mapMessage = new MapMessage(mapId, 1, true, key, null, diffvalue, primary,
        new Member[]{new MemberImpl()});

    // Act and Assert
    assertNull(mapMessage.value(new ClassLoader[]{new ParallelWebappClassLoader()}));
  }

  /**
   * Test MapMessage {@link MapMessage#value(ClassLoader[])}.
   * <ul>
   *   <li>Then return {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy/mm/dd}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapMessage#value(ClassLoader[])}
   */
  @Test
  public void testMapMessageValue_thenReturnSimpleDateFormatWithYyyyMmDd() throws IOException, ClassNotFoundException {
    // Arrange
    byte[] mapId = "AXAXAXAX".getBytes("UTF-8");
    SimpleDateFormat key = new SimpleDateFormat("yyyy/mm/dd");
    SimpleDateFormat value = new SimpleDateFormat("yyyy/mm/dd");
    byte[] diffvalue = "AXAXAXAX".getBytes("UTF-8");
    MemberImpl primary = new MemberImpl();
    MapMessage mapMessage = new MapMessage(mapId, 1, true, key, value, diffvalue, primary,
        new Member[]{new MemberImpl()});

    // Act and Assert
    assertSame(value, mapMessage.value(new ClassLoader[]{new ParallelWebappClassLoader()}));
  }
}
