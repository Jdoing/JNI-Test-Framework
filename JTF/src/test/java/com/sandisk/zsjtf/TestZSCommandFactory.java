package com.sandisk.zsjtf;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.exec.ZSGetRangeExec;

public class TestZSCommandFactory {

	private String ZSCommandExecName;

	private ZSRangeAdapter zsRangeAdapter;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateZSCommandExec() throws JTFException, Exception {
		ZSCommandExecName = "ZSGetRangeExec";
		zsRangeAdapter = new ZSRangeAdapter();
		ZSCommandExec zsCommandExec = ZSCommandExecFactory.createZSCommandExec(
				ZSCommandExecName, zsRangeAdapter);

		assertNotNull("createZSCommandExec a null zsGetRangeExec",
				zsCommandExec);
		assertEquals("create a wrong ZSCommandExec command",
				ZSGetRangeExec.class, zsCommandExec.getClass());

		// fail("Not yet implemented");
	}

	@Test(expected = JTFException.class)
	public void testCreateZSCommandExecWithNull() throws JTFException, Exception {
		ZSCommandExecName = null;
		zsRangeAdapter = null;
		ZSCommandExecFactory.createZSCommandExec(ZSCommandExecName,
				zsRangeAdapter);

	}
}
