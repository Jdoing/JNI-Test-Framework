package com.sandisk.zsjtf.adapter;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerMode;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zs.type.DurabilityLevel;
import com.sandisk.zs.type.OpenContainerMode;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.ZSOpenContainer;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSAdapter;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zsjtf.util.NameIDMapper;

public class ZSContainerAdapter extends ZSAdapter {
	/* Command arg list start */
	private String containerName;
	private Boolean isFIFO;
	private Boolean isPersistent;
	private Boolean isEvicting;
	private Boolean isWritethru;
	private Integer containerSize;
	private Integer shardNumber;
	private String dLevel;
	private String flags;
	private String type;
	/* Command arg list end */

	private Long containerID;
	private DurabilityLevel durabilityLevel;
	private OpenContainerMode openContainerMode;
	private ContainerMode containerMode;

	private ZSOpenContainer zsOpenContainer;

	private ZSContainer container;

	@Override
	public void CreateZSObject(JTFCommand jtfCommand) throws Exception {
		// TODO Auto-generated method stub

		if(jtfCommand instanceof ZSOpenContainer)
		{
			zsOpenContainer = (ZSOpenContainer)jtfCommand;
		}else
		{
			throw new JTFException("Create ZSOpenContainer error");
		}
		
		this.openContainerMode = zsOpenContainer.getOpenContainerMode();
		this.isFIFO = zsOpenContainer.getIsFIFO();
		this.isPersistent = zsOpenContainer.getIsPersistent();
		this.isEvicting = zsOpenContainer.getIsEvicting();
		this.isWritethru = zsOpenContainer.getIsWritethru();
		this.containerSize = zsOpenContainer.getContainerSize();
		this.shardNumber = zsOpenContainer.getShardNumber();
		this.durabilityLevel = zsOpenContainer.getDurabilityLevel();
		this.containerMode = zsOpenContainer.getContainerMode();
		this.containerName = zsOpenContainer.getContainerName();
		
		if (openContainerMode == null) {
			/* Create a new container. */
			ContainerProperty containerProps = ContainerProperty
					.getDefaultProperty();

			containerProps.setFifoMode(isFIFO);
			containerProps.setPersistent(isPersistent);
			containerProps.setEvicting(isEvicting);
			containerProps.setWritethru(isWritethru);
			containerProps.setSize(containerSize);
			containerProps.setShardNumber(shardNumber);
			containerProps.setDurabilityLevel(durabilityLevel);
			containerProps.setContainerMode(containerMode);

			container = new ZSContainer(containerName, containerProps);
			// container.create();
			containerID = container.getContainerId();
			NameIDMapper.getInstance().setNameIDMap(containerName, containerID);
			ContainerManager.getInstance().setContainer(containerID, container);
		} else {
			/* Open an existing container. */
			containerID = NameIDMapper.getInstance()
					.getNameIDMap(containerName);
			container = ContainerManager.getInstance()
					.getContainer(containerID);
			// container.open(openContainerMode);
		}
	}

	public void openContainer() throws ZSContainerException {
		if (openContainerMode == null) {
			container.create();
		} else {
			container.open(openContainerMode);
		}
	}
	
	public void closeContainer(){
		
	}

}
