package com.sandisk.zsjtf.command;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import org.junit.*;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.exec.ZSDeleteContainerExec;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zsjtf.util.NameIDMapper;

public class TestZSDeleteContainer {
	private ZSDeleteContainer zsDeleteContainer;
	private ContainerProperty mockContainerProperty;
	private ZSDeleteContainerExec mockZSDeleteContainerExec;

	private ZSContainer container;
	private Long containerID = 5L;
	private String containerName = "testContainerName";

	private String rawCommand;

	@Before
	public void setUp() throws Exception {
		mockContainerProperty = createMock("mockContainerProperty",
				ContainerProperty.class);

		mockZSDeleteContainerExec = createMock("mockZSDeleteContainerExec",
				ZSDeleteContainerExec.class);

	}

	@After
	public void tearDown() throws Exception {
		verify(mockZSDeleteContainerExec);
	}

	@Test
	public void test() {

		try {

			expect(mockZSDeleteContainerExec.Execute()).andReturn("OK");
			replay(mockZSDeleteContainerExec);

			container = new ZSContainer(containerName, mockContainerProperty);
			assertNotNull(container);

			NameIDMapper.getInstance().setNameIDMap(containerName, containerID);
			ContainerManager.getInstance().setContainer(containerID, container);

			rawCommand = "ZSDeleteContainer cguid=5";

			zsDeleteContainer = new ZSDeleteContainer(rawCommand);
			assertNotNull(zsDeleteContainer);

			zsDeleteContainer.setZSCommandExec(mockZSDeleteContainerExec);

			Object object = zsDeleteContainer.getZSEntry();
			assertNotNull(object);
			
			assertEquals(ZSContainer.class, object.getClass());
			ZSContainer zsContainer = (ZSContainer)object;
//			long cguid =zsContainer.getContainerId();
			
//			assertEquals(5L, cguid);
			
			String result = zsDeleteContainer.execute();
			
			assertEquals("OK", result);
			
		} catch (JTFException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		}
	}
}
