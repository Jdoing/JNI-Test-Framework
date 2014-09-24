package com.sandisk.zsjtf.command;

import static org.junit.Assert.*;

import org.junit.*;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zsjtf.util.NameIDMapper;

import static org.easymock.EasyMock.*;

public class TestZSCloseContainer {

	private ZSCloseContainer zsCloseContainer;
	private ContainerProperty mockContainerProperty;
	private ZSContainer container;
	private Long containerID = 5L;
	private String containerName = "testContainerName";

	private String rawCommand;

	@Before
	public void setUp() throws Exception {
		mockContainerProperty = createMock("mockContainerProperty",
				ContainerProperty.class);

		container = new ZSContainer(containerName, mockContainerProperty);

		NameIDMapper.getInstance().setNameIDMap(containerName, containerID);
		ContainerManager.getInstance().setContainer(containerID, container);
	}

	@Test
	public void test() {

		rawCommand = "ZSCloseContainer cguid=5";

		try {
			zsCloseContainer = new ZSCloseContainer(rawCommand);

			assertNotNull(zsCloseContainer);

			Object object = zsCloseContainer.getZSEntry();

			assertNotNull(object);

			assertEquals(ZSContainer.class, object.getClass());
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
