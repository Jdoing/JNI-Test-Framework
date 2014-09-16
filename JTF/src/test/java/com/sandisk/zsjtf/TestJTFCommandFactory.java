/**
 * 
 */
package com.sandisk.zsjtf;

import static org.junit.Assert.*;
import org.junit.*;

import com.sandisk.zsjtf.command.*;
import com.sandisk.zsjtf.exception.*;

/**
 * @author jiangyu
 *
 */
public class TestJTFCommandFactory {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWithCguid() throws Exception {
		String rawCommand = "ZSGetRange cguid=5";
		JTFCommand command = JTFCommandFactory
				.generateCommandObject(rawCommand);

		assertNotNull("Must not return a null command", command);
		assertEquals("Return a wrong command type", ZSGetRange.class,
				command.getClass());
	}

	@Test
	public void testWithParameters() throws Exception {
//		String rawCommand = "ZSGetRange databuf_size=1024 keybuf_size=50 cguid=5 keylen_end=8 start_key=0 keylen_start=8 end_key=10";

		String rawCommand = "ZSGetRange databuf_size=1024 keybuf_size=50 cguid=5 keylen_end=8 start_key=0 keylen_start=8 end_key=10";
		
		JTFCommand command = JTFCommandFactory
				.generateCommandObject(rawCommand);
		assertNotNull("Must not return a null command", command);
		assertEquals("Return a wrong command type", ZSGetRange.class,
				command.getClass());
	}

	@Test(expected = JTFException.class)
	public void testGetNullCommand() throws Exception {
		String rawCommand = null;

		JTFCommandFactory.generateCommandObject(rawCommand);
	}
	
	@Test(expected = Exception.class)
//	@Ignore(value="Test later!")
	public void testWithoutParameter() throws Exception {
		String rawCommand = "ZSGetRange";
		JTFCommand command = JTFCommandFactory
				.generateCommandObject(rawCommand);
//
//		assertNotNull("Must not return a null command", command);
//		assertEquals("Return a wrong command type", ZSGetRange.class,
//				command.getClass());
		// fail("Not yet implemented");
	}
}
