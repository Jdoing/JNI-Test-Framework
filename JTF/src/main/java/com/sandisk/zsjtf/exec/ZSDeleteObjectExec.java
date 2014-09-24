package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.ZSDeleteObject;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.ByteBuffManager;
import com.sandisk.zsjtf.util.MiscUtils;

public class ZSDeleteObjectExec extends ZSCommandExec {
	private Integer initialIntegerKey;
	private Integer initialKeyOffset;
	private Integer keyLength;
	private Integer nops;

	private ZSContainer zsContainer;
	private ZSDeleteObject zsWriteObject;

	public ZSDeleteObjectExec(JTFCommand jtfCommand) throws JTFException {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
		if (jtfCommand instanceof ZSDeleteObject) {
			zsWriteObject = (ZSDeleteObject) jtfCommand;
		} else {
			throw new JTFException(
					"Fail when cast jtfCommand to ZSDeleteObject type");
		}

		this.nops = zsWriteObject.getNops();
		this.initialIntegerKey = zsWriteObject.getInitialIntegerKey();
		this.keyLength = zsWriteObject.getKeyLength();
		this.initialKeyOffset = zsWriteObject.getInitialKeyOffset();

	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		try {
			int integerKey;
			int keyOffset;

			// byte[] key = new byte[keyLength];

			// initialize the key array separately to support range query(yjiang
			// modify)
			byte[] key;
			for (int i = 0; i < nops; ++i) {
				if (initialIntegerKey == null) {
					key = new byte[keyLength];
					keyOffset = initialKeyOffset + i;
					ByteBuffManager.getInstance().arraycopy(key, keyOffset,
							keyLength);
				} else {
					key = new byte[JTFCommand.MAX_KEY_LEN];
					integerKey = initialIntegerKey + i;
					MiscUtils.decodeIntegerKey(key, integerKey, keyLength);
				}

				if (zsContainer.delete(key) != 1) {
					throw new ZSContainerException(i
							+ " th ZSDeleteObject failed");
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
