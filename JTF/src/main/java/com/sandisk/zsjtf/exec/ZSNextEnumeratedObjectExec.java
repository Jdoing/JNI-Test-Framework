package com.sandisk.zsjtf.exec;

import java.util.List;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ZSObjectOp;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;

public class ZSNextEnumeratedObjectExec extends ZSCommandExec {

	private ZSContainer zsContainer;

	public ZSNextEnumeratedObjectExec(JTFCommand jtfCommand) {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		// do enuerate
		try {
			String ret = "OK";
			int count = nextEnumeratedObject();
			ret += ("\nenumerate " + count + "objects");
			return ret;
		} catch (ZSContainerException e) {
			return "SERVER_ERROR " + e.toString();
		} catch (JTFException e) {
			return "CLIENT_ERROR " + e.toString();
		}

	}

	private int nextEnumeratedObject() throws JTFException,
			ZSContainerException {
		// do enuerate
		int enumerator_count = 0;
		int success_count = 0;
		
		zsContainer.bulkEnumCreate();
		List<ZSObjectOp> list = null;
		while ((list = zsContainer.bulkEnumNext(enumerator_count)).size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).getKey();
				list.get(i).getData();
				assert list.get(i).getRetCode() == 1; // ZS_SUCCESS
				success_count++;
			}
		}

		return success_count;
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
