package com.sandisk.jtf.commands;

import com.sandisk.jtf.*;
import com.sandisk.jtf.exception.JTFException;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.ZSRange;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.RangeData;
import com.sandisk.zsexample.ExampleUtil;

public class ZSGetNextRange extends JTFCommand {

	private Integer n_in;
	private Boolean check;

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		try {
			getProperties();
			ZSContainer container = ExampleUtil.createBtreeContainer("c0");
			ZSRange range = new ZSRange(container.getContainerId());

			RangeData[] resultDatas = range.getDatas(n_in);
			
			int check_fail=0;
			
			if(check){
				for(int i = 0, length = resultDatas.length; i < length; i++){
					
				}
			}
		} catch (JTFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private void getProperties() throws JTFException {
		n_in = this.getIntegerProperty("n_in", true);
		check = this.getBooleanProperty("check", true);
	}
}
