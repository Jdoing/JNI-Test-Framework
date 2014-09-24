package com.sandisk.zsjtf.exec;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import org.junit.*;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zsjtf.command.ZSDeleteContainer;
import com.sandisk.zsjtf.exception.JTFException;

public class TestZSDeleteContainerExec {

	private String rawCommand = "ZSDeleteContainer cguid=55";
	private ZSContainer mockZSContainer;
	private ZSDeleteContainer zsDeleteContainer;
	private ZSDeleteContainerExec zsDeleteContainerExec;

	@Before
	public void setUp() throws Exception {

		mockZSContainer = createMock("mockZSContainer", ZSContainer.class);
	}

	@After
	public void tearDown() throws Exception {
		verify(mockZSContainer);
	}

	@Test
	public void test() {
		try {
			
			mockZSContainer.drop();
			replay(mockZSContainer);
			
			zsDeleteContainer = new ZSDeleteContainer(rawCommand);
			assertNotNull(zsDeleteContainer);

			zsDeleteContainerExec = new ZSDeleteContainerExec(zsDeleteContainer);
			assertNotNull(zsDeleteContainerExec);
			
			zsDeleteContainerExec.setZSEntry(mockZSContainer);
			
			String result = zsDeleteContainerExec.Execute();
			
			assertEquals("OK", result);

		} catch (JTFException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		}

		// fail("Not yet implemented");
	}

}
