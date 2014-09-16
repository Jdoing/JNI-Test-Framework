package com.sandisk.zsjtf;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;

import com.sandisk.zsjtf.command.ZSGetRange;

public class TestJTFCommand {

	private static SampleJTFCommand sampleJTFCommand;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String rawCommand = "SampleJTFCommand flags=1 cname=fdfcontainer databuf_size=1024 keybuf_size=50 cguid=5 keylen_end=8 start_key=0 keylen_start=8 end_key=10";
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

}
