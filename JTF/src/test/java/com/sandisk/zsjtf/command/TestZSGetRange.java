package com.sandisk.zsjtf.command;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public void test() throws JTFException {
		String rawCommand = "ZSGetRange cguid=5";
		// String[] tokens = rawCommand.split("\\s+");
		// Properties initargs = parse(tokens);

		ZSGetRange range = new ZSGetRange(rawCommand);

		assertNotNull("ZSGetRange must not null", range);
	}

}
