package com.sandisk.zsjtf;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sandisk.zsjtf.command.ZSGetRange;

public  class TestJTFCommand extends JTFCommand {



	public TestJTFCommand(String rowCommand) {
		super(rowCommand);
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test() throws Exception {
		String rawCommand = "ZSGetRange databuf_size=1024 keybuf_size=50 cguid=5 keylen_end=8 start_key=0 keylen_start=8 end_key=10";
		TestJTFCommand tJTFCommand = (TestJTFCommand)JTFCommandFactory.generateCommandObject(rawCommand);
		
		assertNotNull("Must not return a null command", tJTFCommand);
		assertEquals("Return a wrong command type", ZSGetRange.class,
				tJTFCommand.getClass());
		
		//fail("Not yet implemented");
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
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

}
