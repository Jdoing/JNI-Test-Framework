package com.sandisk.zsjtf.command;

import static org.junit.Assert.*;
import org.junit.*;

import com.sandisk.zsjtf.exception.JTFException;

public class TestZSGetRange {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String rawCommand = "ZSGetRange cguid=5";
		// String[] tokens = rawCommand.split("\\s+");
		// Properties initargs = parse(tokens);

		ZSGetRange range;
		try {
			range = new ZSGetRange(rawCommand);
			assertNotNull("ZSGetRange must not null", range);
		} catch (JTFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
