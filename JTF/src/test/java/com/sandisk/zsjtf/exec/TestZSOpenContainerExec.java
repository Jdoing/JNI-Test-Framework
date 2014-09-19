package com.sandisk.zsjtf.exec;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.OpenContainerMode;
import com.sandisk.zsjtf.command.ZSOpenContainer;
import com.sandisk.zsjtf.exception.JTFException;

import static org.easymock.EasyMock.*;

public class TestZSOpenContainerExec {
	private String rawCommand;

	private ZSOpenContainerExec zsOpenContainerExec;
	private ZSContainer mockZSContainer;

	@Before
	public void setUp() throws Exception {

		rawCommand = "ZSOpenContainer fifo_mode=yes writethru=yes num_shards=1 flags=ZS_CTNR_CREATE evicting=yes cname=c0_50 persistent=no size=1048576 async_writes=yes durability_level=ZS_DURABILITY_PERIODIC";
	}

	@After
	public void tearDown() throws Exception {
//		verify(mockZSContainer);
	}

	@Test
	public void testConstruct() {
		try {
			ZSOpenContainer zsOpenContainer = new ZSOpenContainer(rawCommand);
			assertNotNull(zsOpenContainer);
			//
			// zsOpenContainerExec = new ZSOpenContainerExec(zsOpenContainer);
			// assertNotNull(zsOpenContainerExec);

			// ZSContainer mockZSContainer =
			// createMock("mockZSContainer",ZSContainer.class);
			// mockZSContainer.create();
			// zsContainer.open(OpenContainerMode.READ_WRITE);
			// replay(mockZSContainer);

			// zsOpenContainerExec.setZSEntry(mockZSContainer);

			// String result = zsOpenContainerExec.Execute();

			// assertEquals("OK",result);

		} catch (JTFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
			// } catch (ZSContainerException e) {
			// // TODO Auto-generated catch block
			// fail(e.toString());
			// }

		}

	}
}
