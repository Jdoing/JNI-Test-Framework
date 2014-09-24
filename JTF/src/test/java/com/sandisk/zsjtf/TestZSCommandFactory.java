package com.sandisk.zsjtf;

import static org.junit.Assert.*;

import org.junit.*;
import com.sandisk.zsjtf.exec.ZSOpenContainerExec;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.global.ZSCommandExecFactory;
import com.sandisk.zsjtf.util.Log;

public class TestZSCommandFactory {

	private JTFCommand jtfCommand;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateZSCommandExec() {
		String rawCommand = "ZSOpenContainer fifo_mode=yes writethru=yes num_shards=1 flags=ZS_CTNR_CREATE evicting=yes cname=c0_50 persistent=no size=1048576 async_writes=yes durability_level=ZS_DURABILITY_PERIODIC";
		try{
		JTFCommand jtfCommand = JTFCommandFactory
				.generateCommandObject(rawCommand);

		assertNotNull(jtfCommand);
		
		ZSCommandExec zsCommandExec = ZSCommandExecFactory
				.createZSCommandExec(jtfCommand);

		assertNotNull("createZSCommandExec is a null",
				zsCommandExec);
		assertEquals("create a wrong ZSCommandExec command",
				ZSOpenContainerExec.class, zsCommandExec.getClass());
		}catch(Exception e){
			fail(e.toString());
		}

		// fail("Not yet implemented");
	}

//	@Test(expected = JTFException.class)
//	public void testCreateZSCommandExecWithNull() throws JTFException,
//			Exception {
//	
//	}
}
