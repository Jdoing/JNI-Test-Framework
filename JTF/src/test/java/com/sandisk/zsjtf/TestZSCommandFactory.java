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

	String ZSCommandExecName;
	private ZSRangeAdapter zsRangeAdapter;
	@Before
	public void setUp() throws Exception {
		ZSCommandExecName = "ZSGetRangeExec";
		zsRangeAdapter = new ZSRangeAdapter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateZSCommandExec() throws JTFException, Exception {
		ZSCommandExec zsCommandExec = ZSCommandExecFactory.createZSCommandExec(ZSCommandExecName,zsRangeAdapter);
		
		assertNotNull("createZSCommandExec a null zsGetRangeExec",zsCommandExec);
		assertEquals("create a wrong ZSCommandExec command",ZSGetRangeExec.class,zsCommandExec.getClass());
		
		//fail("Not yet implemented");
	}

}
