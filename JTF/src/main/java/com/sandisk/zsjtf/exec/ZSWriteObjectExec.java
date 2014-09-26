package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zs.type.WriteObjectMode;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.ZSWriteObject;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.ByteBuffManager;
import com.sandisk.zsjtf.util.MiscUtils;

public class ZSWriteObjectExec extends ZSCommandExec {

	private ZSWriteObject zsWriteObject;
	private ZSContainer zsContainer;

	private Integer initialIntegerKey;
	private Integer initialKeyOffset;
	private Integer keyLength;
	private Integer initialDataOffset;
	private Integer dataLength;
	private Integer nops;
	private String flags;
	/* Command arg list end */

	private WriteObjectMode writeObjectMode;

//	public static final int MAX_KEY_LEN = 200;

	public ZSWriteObjectExec(JTFCommand jtfCommand) throws JTFException {
		super(jtfCommand);
		// TODO Auto-generated constructor stub

		if (jtfCommand instanceof ZSWriteObject) {
			zsWriteObject = (ZSWriteObject) jtfCommand;
		} else {
			throw new JTFException(
					"Fail when cast jtfCommand to ZSWriteObject type");
		}

		this.dataLength = zsWriteObject.getDataLength();
		this.nops = zsWriteObject.getNops();
		this.initialIntegerKey = zsWriteObject.getInitialIntegerKey();
		this.keyLength = zsWriteObject.getKeyLength();
		this.initialKeyOffset = zsWriteObject.getInitialKeyOffset();
		this.initialDataOffset = zsWriteObject.getInitialDataOffset();
		this.writeObjectMode = zsWriteObject.getWriteObjectMode();
	}

	@Override
	public String Execute() {
		try {
			int integerKey;
			int keyOffset;
			int dataOffset;

			// byte[] key = new byte[keyLength];
			byte[] key;
			byte[] data = new byte[dataLength];

			for (int i = 0; i < nops; ++i) {
				if (initialIntegerKey == null) {
					key = new byte[keyLength];
					keyOffset = initialKeyOffset + i;
					/* Decode key by offset and length */
					ByteBuffManager.getInstance().arraycopy(key, keyOffset,
							keyLength);
				} else {
					key = new byte[JTFCommand.MAX_KEY_LEN];
					integerKey = initialIntegerKey + i;
					/* Decode key by integer key and length */
					MiscUtils.decodeIntegerKey(key, integerKey, keyLength);
				}

				dataOffset = initialDataOffset + i;
				ByteBuffManager.getInstance().arraycopy(data, dataOffset,
						dataLength);

				if (zsContainer.write(key, data, writeObjectMode) != 1) {
					throw new ZSContainerException(i
							+ " th ZSWriteObject failed");
				}
			}

			return JTFCommand.handleSuccessReturn();
		} catch (ZSException e) {
			return JTFCommand.handleServerErrorReturn(e);
		} catch (JTFException e) {
			return JTFCommand.handleClientErrorReturn(e);
		}

	}

	@Override
	public void setZSEntry(Object zsEntry) throws JTFException {
		// TODO Auto-generated method stub
		if (zsEntry instanceof ZSContainer) {
			zsContainer = (ZSContainer) zsEntry;
		} else {
			throw new JTFException("ZSEntry do not match ZSContainer");
		}
	}

}
