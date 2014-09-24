package com.sandisk.zsjtf.exec;

import java.util.Arrays;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.ZSReadObject;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.ByteBuffManager;
import com.sandisk.zsjtf.util.Log;
import com.sandisk.zsjtf.util.MiscUtils;

public class ZSReadObjectExec extends ZSCommandExec {
	private Integer initialIntegerKey;
	private Integer initialKeyOffset; // Specify key start pos.
	private Integer keyLength;
	private Integer initialDataOffset; // Specify verify data start pos.
	private Integer dataLength;
	private Integer nops; // Specify read operation numbers.
	private Boolean check; // Specify if we should verify data after read ops
							// finish.
	private Boolean keepRead; // Specify if command should discontinued when
								// read error happens.
	/* Command arg list end */

	private int readFailedCnt = 0;
	private int verifyFailedCnt = 0;

	private ZSContainer zsContainer;
	private ZSReadObject zsReadObject;

	public ZSReadObjectExec(JTFCommand jtfCommand) throws JTFException {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
		if (jtfCommand instanceof ZSReadObject) {
			zsReadObject = (ZSReadObject) jtfCommand;
		} else {
			throw new JTFException(
					"Fail when cast jtfCommand to ZSReadObject type");
		}
		
		this.dataLength = zsReadObject.getDataLength();
		this.nops = zsReadObject.getNops();
		this.initialIntegerKey = zsReadObject.getInitialIntegerKey();
		this.keyLength = zsReadObject.getKeyLength();
		this.initialKeyOffset = zsReadObject.getInitialKeyOffset();
		this.keepRead = zsReadObject.getKeepRead();
		this.check = zsReadObject.getCheck();
		this.initialDataOffset = zsReadObject.getInitialDataOffset();
		this.dataLength = zsReadObject.getDataLength();
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		try {
			Log.logDebug("Start readObject=>");
			int integerKey;
			int keyOffset;
			int dataOffset;
			byte[] result = new byte[0];
			// byte[] key = new byte[keyLength];
			// initialize the key array separately to support range query(yjiang
			// modify)
			byte[] key;
			byte[] data = new byte[dataLength];
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

				try {
					result = zsContainer.read(key);
				} catch (ZSContainerException e) {
					if (keepRead) {
						readFailedCnt++;
					} else {
						throw new ZSContainerException(i
								+ " th ZSReadObject failed");
					}
				}

				if (check) {
					dataOffset = initialDataOffset + i;
					ByteBuffManager.getInstance().arraycopy(data, dataOffset,
							dataLength);
					if (!Arrays.equals(data, result)) {
						verifyFailedCnt++;
					}
				}
			}

			return handleSuccessReturn();
		} catch (ZSException e) {
			return JTFCommand.handleServerErrorReturn(e);
		} catch (JTFException e) {
			return JTFCommand.handleClientErrorReturn(e);
		}
	}

	private String handleSuccessReturn() throws ZSException {
		if (readFailedCnt == 0 && verifyFailedCnt == 0) {
			return "OK";
		} else {
			String msg = readFailedCnt + " items read failed "
					+ verifyFailedCnt + " items check failed";
			throw new ZSException(msg);
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
