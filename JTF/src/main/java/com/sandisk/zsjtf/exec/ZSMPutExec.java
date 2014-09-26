package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.WriteObjectMode;
import com.sandisk.zs.type.ZSMData;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.ZSMPut;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.ByteBuffManager;
import com.sandisk.zsjtf.util.MiscUtils;

public class ZSMPutExec extends ZSCommandExec {
	private Integer initialKeyOffset;
	private Integer keyLength;
	private Integer initialDataOffset;
	private Integer dataLength;
	private Integer num_objs;

	private WriteObjectMode writeObjectMode = null;

	private ZSMPut zsMPut;

	private ZSContainer zsContainer;

	public ZSMPutExec(JTFCommand jtfCommand) throws JTFException {
		super(jtfCommand);
		// TODO Auto-generated constructor stub

		if (jtfCommand instanceof ZSMPut) {
			zsMPut = (ZSMPut) jtfCommand;
		} else {
			throw new JTFException("Fail when cast jtfCommand to ZSMPut type");
		}

		this.num_objs = zsMPut.getNum_objs();
		this.keyLength = zsMPut.getKeyLength();
		this.dataLength = zsMPut.getDataLength();
		this.initialKeyOffset = zsMPut.getInitialKeyOffset();
		this.initialDataOffset = zsMPut.getInitialDataOffset();
		this.writeObjectMode = zsMPut.getWriteObjectMode();
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		try {
			int keyOffset;
			int dataOffset;

			// int objs_written; //how many objs are written.

			ZSMData[] datas = new ZSMData[num_objs];
			byte[] key = new byte[keyLength];
			byte[] data = new byte[dataLength];

//ZSMPut data_offset=0 key_len=15 flags=ZS_WRITE_MUST_NOT_EXIST cguid=5 data_len=10 num_objs=10000 key_offset=0
			
			for (int i = 0; i < num_objs; i++) {
				keyOffset = initialKeyOffset + i;
//				ByteBuffManager.getInstance().arraycopy(key, keyOffset,
//						keyLength);
				key = MiscUtils.specialFormat(keyLength, keyOffset).getBytes();

				dataOffset = initialDataOffset + i;
				ByteBuffManager.getInstance().arraycopy(data, dataOffset,
						dataLength);

				datas[i] = new ZSMData(key, data);
			}

			// exception handled by ZSContainer
			zsContainer.multiPut(datas, writeObjectMode); // default multiPut
			// no need to return objs_written				// mode is
															// ZS_WRITE_EXIST_OR_NOT
			
			return JTFCommand.handleSuccessReturn();
			
		} catch (JTFException e) {
			return JTFCommand.handleClientErrorReturn(e);
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			return JTFCommand.handleServerErrorReturn(e);
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
