package com.sandisk.zsjtf.command;

import static org.junit.Assert.*;

import org.junit.*;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerMode;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.DurabilityLevel;
import com.sandisk.zs.type.OpenContainerMode;
import com.sandisk.zsjtf.exception.JTFException;

import static org.easymock.EasyMock.*;

public class TestZSOpenContainer {

	private String rawCommand;

	private ZSContainer zsContainer;

	private Boolean isFIFO;
	private Boolean isPersistent;
	private Boolean isEvicting;
	private Boolean isWritethru;
	private Integer containerSize;
	private Integer shardNumber;

	private Long containerID;
	private DurabilityLevel durabilityLevel;
	private OpenContainerMode openContainerMode;
	private ContainerMode containerMode;

	private ContainerProperty mockContainerProps;
	private ZSOpenContainer zsOpenContainer;

	@Before
	public void setUp() throws Exception {

		rawCommand = "ZSOpenContainer fifo_mode=yes writethru=yes num_shards=1 flags=ZS_CTNR_CREATE evicting=yes cname=c0_50 persistent=no size=1048576 async_writes=yes durability_level=ZS_DURABILITY_PERIODIC";
	}

	@After
	public void tearDown() throws Exception {

		verify(mockContainerProps);
	}

	@Test
	public void testConstruct() {

		try {

			// ContainerProperty containerProps =
			// ContainerProperty.getDefaultProperty();
			// mockContainerProps =
			// createMockBuilder(ContainerProperty.class).createMock();
			zsOpenContainer = new ZSOpenContainer(rawCommand);
			assertNotNull(zsOpenContainer);
			
			mockContainerProps = createMock("mockContainerProps",
					ContainerProperty.class);

			this.isFIFO = true;
			this.isPersistent = false;
			this.isEvicting = true;
			this.isWritethru = true;
			this.containerSize = 1048576;
			this.shardNumber = 1;
			this.durabilityLevel = DurabilityLevel.ZS_DURABILITY_PERIODIC;
			this.containerMode = ContainerMode.HASHMODE;

			mockContainerProps.setFifoMode(isFIFO);
			mockContainerProps.setPersistent(isPersistent);
			mockContainerProps.setEvicting(isEvicting);
			mockContainerProps.setWritethru(isWritethru);
			mockContainerProps.setSize(containerSize);
			mockContainerProps.setShardNumber(shardNumber);
			mockContainerProps.setDurabilityLevel(durabilityLevel);
			mockContainerProps.setContainerMode(containerMode);

			replay(mockContainerProps);

			zsOpenContainer.setContainerProperty(mockContainerProps);

			Object object = zsOpenContainer.getZSEntry();

			if (object instanceof ZSContainer) {
				this.zsContainer = (ZSContainer) object;
			} else {
				throw new JTFException(
						"object can not be casted to ZSContainer");
			}

			assertEquals(ZSContainer.class, object.getClass());

			openContainerMode = zsOpenContainer.getOpenContainerMode();

			assertNull(openContainerMode);

		} catch (JTFException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		}
	}
}
