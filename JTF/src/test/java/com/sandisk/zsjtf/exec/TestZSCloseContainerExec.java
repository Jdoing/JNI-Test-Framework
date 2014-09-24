package com.sandisk.zsjtf.exec;

import static org.junit.Assert.*;

import org.junit.*;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.ZSCloseContainer;
import com.sandisk.zsjtf.exception.JTFException;

import static org.easymock.EasyMock.*;

public class TestZSCloseContainerExec {

	private ZSCloseContainerExec zsCloseContainerExec;
	private ZSContainer mockZSContainer;
	private String rawCommand;
	
	@Before
	public void setUp() throws Exception{
		rawCommand = "ZSCloseContainer cguid=5";
		
		mockZSContainer = createMock("mockZSContainer",ZSContainer.class);
		
		}
	
	@After
	public void tearDown() throws Exception{
//		verify(mockZSContainer);
	}
	
	@Test
	public void test() {
		try {
			JTFCommand jtfCommand = new ZSCloseContainer(rawCommand);
			assertNotNull(jtfCommand);
			
			zsCloseContainerExec = new ZSCloseContainerExec(jtfCommand);
			assertNotNull(zsCloseContainerExec);
			
			zsCloseContainerExec.setZSEntry(mockZSContainer);
			
			String result =zsCloseContainerExec.Execute();
			
			assertEquals("OK", result);
			
		} catch (JTFException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		}
		
		
	}

}
