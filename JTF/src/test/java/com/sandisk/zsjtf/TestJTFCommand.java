package com.sandisk.zsjtf;

import static org.junit.Assert.*;

import org.junit.*;
import com.sandisk.zsjtf.exception.JTFException;

public class TestJTFCommand {

	private static SampleJTFCommand sampleJTFCommand;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String rawCommand = "SampleJTFCommand cname=fdfcontainer databuf_size=1024 keep_read=yes keybuf_size=50 cguid=5 keylen_end=8 start_key=0 keylen_start=8 end_key=10";
		sampleJTFCommand = new SampleJTFCommand(rawCommand);
	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() throws Exception {

		assertNotNull("Must not return a null command", sampleJTFCommand);
		// assertEquals("Return a wrong command type", SampleJTFCommand.class,
		// tJTFCommand.getClass());

		// fail("Not yet implemented");
	}

	@Test
	public void testGetStringProperty() throws JTFException {
		// cname=fdfcontainer
		String expected = "fdfcontainer";
		String actual = sampleJTFCommand.getStringProperty("cname", true);

		assertEquals(expected, actual);

	}

	@Test
	public void testGetStringPropertyWithDefault() throws JTFException {
		// String DURABILITY_LEVEL_DEFAULT_VALUE = "ZS_DURABILITY_PERIODIC";
		String expected = "ZS_DURABILITY_PERIODIC";
		String actual = sampleJTFCommand.getStringProperty(
				JTFCommand.DURABILITY_LEVEL, false,
				JTFCommand.DURABILITY_LEVEL_DEFAULT_VALUE);

		assertEquals(expected, actual);

	}

	@Test
	public void testGetIntegerProperty() throws JTFException {
		// int databuf_size=1024
		Integer expected = 1024;
		Integer actual = sampleJTFCommand.getIntegerProperty(
				JTFCommand.DATABUF_SIZE, true);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetIntegerPropertyWithDefault() throws JTFException {
		// KEY_LEN_DEFAULT_VALUE = 1;
		Integer expected = 1;
		Integer actual = sampleJTFCommand.getIntegerProperty(
				JTFCommand.KEY_LEN, false, JTFCommand.KEY_LEN_DEFAULT_VALUE);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetIntegerPropertyWithNull() throws JTFException {
		Integer expected = null;
		Integer actual = sampleJTFCommand.getIntegerProperty(
				JTFCommand.WRITETHRU, false);
		// assertNull(actual);
		assertEquals(expected, actual);

	}

	@Test(expected = JTFException.class)
	public void testGetIntegerPropertyThrowException() throws JTFException {
		sampleJTFCommand.getIntegerProperty(JTFCommand.DURABILITY_LEVEL, true);

	}

	@Test
	public void testGetLongProperty() throws JTFException {
		// long databuf_size=1024
		Long expected = 1024L;
		Long actual = sampleJTFCommand.getLongProperty(JTFCommand.DATABUF_SIZE,
				true);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetLongPropertyWithDefault() throws JTFException {
		// KEY_LEN_DEFAULT_VALUE = 1;
		Long expected = 1L;
		Long actual = sampleJTFCommand.getLongProperty(JTFCommand.KEY_LEN,
				false, (long) JTFCommand.KEY_LEN_DEFAULT_VALUE);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetLongPropertyWithNull() throws JTFException {
		Long expected = null;
		Long actual = sampleJTFCommand.getLongProperty(JTFCommand.WRITETHRU,
				false);
		// assertNull(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetBooleanProperty() throws JTFException {
		// keep_read
		Boolean expected = Boolean.TRUE;
		Boolean actual = sampleJTFCommand.getBooleanProperty(
				JTFCommand.KEEP_READ, false);

		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetBooleanPropertyWithNull() throws JTFException {
		Boolean expected = null;
		Boolean actual = sampleJTFCommand.getBooleanProperty(
				JTFCommand.FIFO_MODE, false);
		assertEquals(expected, actual);
	}

}
