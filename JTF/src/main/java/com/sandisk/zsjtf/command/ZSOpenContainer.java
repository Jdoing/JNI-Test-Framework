/*
 * File: ZSOpenContainer.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zs.type.ContainerMode;
import com.sandisk.zs.type.DurabilityLevel;
import com.sandisk.zs.type.OpenContainerMode;

/**
 * ZSOpenContainer command class.
 *
 * @author rchen
 *
 *         args: cname=%s fifo_mode=yes|no persistent=yes|no evicting=yes|no
 *         writethru=yes|no size=%d num_shards=%d
 *         durability_level=ZS_DURABILITY_PERIODIC
 *         |ZS_DURABILITY_SW_CRASH_SAFE|ZS_DURABILITY_HW_CRASH_SAFE
 *         flags=ZS_CTNR_CREATE|ZS_CTNR_RW_MODE|ZS_CTNR_RO_MODE type=HASH|BTREE
 *
 *         must have: cname
 *
 *         return: success: OK cguid=%ull failed: SERVER_ERROR %s | CLIENT_ERROR
 *         %s
 */
public class ZSOpenContainer extends JTFCommand {

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

	private String ZSAdapterName = "ZSContainerAdapter";
	private String ZSCommandExecName = "ZSOpenContainerExec";

	public ZSOpenContainer(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub

		getProperties();
		setDurabilityLevel();
		setContainerMode();
		setOpenContainerMode();
		
	}

	@Override
	public String execute() {
		// try {
		// getProperties();
		// getDurabilityLevel();
		// getContainerMode();
		// getOpenContainerMode();
		// openContainer();
		// return handleSuccessReturn();
		// } catch (ZSContainerException e) {
		// return handleServerErrorReturn(e);
		// } catch (JTFException e) {
		// return handleClientErrorReturn(e);
		// }

		return null;
	}

	private void getProperties() throws JTFException {
		containerName = getStringProperty(CNAME, true);
		containerSize = getIntegerProperty(SIZE, false, SIZE_DEFAULT_VALUE);
		shardNumber = getIntegerProperty(NUM_SHARDS, false, 1);
		isFIFO = getBooleanProperty(FIFO_MODE, false, FIFO_MODE_DEFAULT_VALUE);
		isPersistent = getBooleanProperty(PERSISTENT, false,
				PERSISTENT_DEFAULT_VALUE);
		isEvicting = getBooleanProperty(EVICTING, false, EVICTING_DEFAULT_VALUE);
		isWritethru = getBooleanProperty(WRITETHRU, false,
				WRITETHRU_DEFAULT_VALUE);
		dLevel = getStringProperty(DURABILITY_LEVEL, false,
				DURABILITY_LEVEL_DEFAULT_VALUE);
		flags = getStringProperty(FLAGS, false, ZS_CTNR_DEFAULT_VALUE);
		type = getStringProperty(TYPE, false, TYPE_DEFAULT_VALUE);
	}

	private void setDurabilityLevel() throws JTFException {
		if (dLevel.equals("ZS_DURABILITY_PERIODIC")) {
			durabilityLevel = DurabilityLevel.ZS_DURABILITY_PERIODIC;
		} else if (dLevel.equals("ZS_DURABILITY_SW_CRASH_SAFE")) {
			durabilityLevel = DurabilityLevel.ZS_DURABILITY_SW_CRASH_SAFE;
		} else if (dLevel.equals("ZS_DURABILITY_HW_CRASH_SAFE")) {
			durabilityLevel = DurabilityLevel.ZS_DURABILITY_HW_CRASH_SAFE;
		} else {
			throw new JTFException("Durability level unrecognized");
		}
	}

	private void setContainerMode() throws JTFException {
		if (type.equals("HASH")) {
			containerMode = ContainerMode.HASHMODE;
		} else if (type.equals("BTREE")) {
			containerMode = ContainerMode.BTREEMODE;
		} else {
			throw new JTFException("Container mode unrecognized");
		}
	}

	private void setOpenContainerMode() throws JTFException {
		if (flags.equals("ZS_CTNR_CREATE")) {
			openContainerMode = null;
		} else if (flags.equals("ZS_CTNR_RW_MODE")) {
			openContainerMode = OpenContainerMode.READ_WRITE;
		} else if (flags.equals("ZS_CTNR_RO_MODE")) {
			openContainerMode = OpenContainerMode.READ_ONLY;
		} else {
			throw new JTFException("Open container mode unrecognized");
		}
	}

	// private void openContainer() throws JTFException, ZSContainerException
	// {
	// ZSContainer container;
	// if (openContainerMode == null) {
	// /* Create a new container. */
	// ContainerProperty containerProps =
	// ContainerProperty.getDefaultProperty();
	//
	// containerProps.setFifoMode(isFIFO);
	// containerProps.setPersistent(isPersistent);
	// containerProps.setEvicting(isEvicting);
	// containerProps.setWritethru(isWritethru);
	// containerProps.setSize(containerSize);
	// containerProps.setShardNumber(shardNumber);
	// containerProps.setDurabilityLevel(durabilityLevel);
	// containerProps.setContainerMode(containerMode);
	//
	// container = new ZSContainer(containerName, containerProps);
	// container.create();
	// containerID = container.getContainerId();
	// NameIDMapper.getInstance().setNameIDMap(containerName, containerID);
	// ContainerManager.getInstance().setContainer(containerID, container);
	// } else {
	// /* Open an existing container. */
	// containerID = NameIDMapper.getInstance().getNameIDMap(containerName);
	// container = ContainerManager.getInstance().getContainer(containerID);
	// container.open(openContainerMode);
	// }
	// }

	@Override
	protected String handleSuccessReturn() {
		return "OK cguid=" + containerID;
	}

	@Override
	public String getZSAdapterName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContainerName() {
		return containerName;
	}

	public Boolean getIsFIFO() {
		return isFIFO;
	}

	public Boolean getIsPersistent() {
		return isPersistent;
	}

	public Boolean getIsEvicting() {
		return isEvicting;
	}

	public Boolean getIsWritethru() {
		return isWritethru;
	}

	public Integer getContainerSize() {
		return containerSize;
	}

	public Integer getShardNumber() {
		return shardNumber;
	}

	public String getdLevel() {
		return dLevel;
	}

	public String getFlags() {
		return flags;
	}

	public String getType() {
		return type;
	}

	public Long getContainerID() {
		return containerID;
	}

	public DurabilityLevel getDurabilityLevel() {
		return durabilityLevel;
	}

	public OpenContainerMode getOpenContainerMode() {
		return openContainerMode;
	}

	public ContainerMode getContainerMode() {
		return containerMode;
	}
}
