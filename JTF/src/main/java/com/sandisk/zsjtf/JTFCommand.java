package com.sandisk.zsjtf;

import java.util.Properties;

import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;

/**
 * Created by ray on 7/9/14.
 */
public abstract class JTFCommand {
	/* Command key listed in alphabetical order */
	protected static final String CGUID = "cguid";
	protected static final String CNAME = "cname";
	protected static final String CHECK = "check";
	protected static final String DATA_LEN = "data_len";
	protected static final String DATA_OFFSET = "data_offset";
	protected static final String DURABILITY_LEVEL = "durability_level";
	protected static final String EVICTING = "evicting";
	protected static final String FIFO_MODE = "fifo_mode";
	protected static final String FLAGS = "flags";
	protected static final String KEEP_READ = "keep_read";
	protected static final String KEY = "key";
	protected static final String KEY_LEN = "key_len";
	protected static final String KEY_OFFSET = "key_offset";
	protected static final String NOPS = "nops";
	protected static final String NUM_SHARDS = "num_shards";
	protected static final String PERSISTENT = "persistent";
	protected static final String SIZE = "size";
	protected static final String TYPE = "type";
	protected static final String WRITETHRU = "writethru";
	protected static final String NUM_OBJS = "num_objs";

	// yjiang add below variable for the command of GetRange
	protected static final String KEYBUF_SIZE = "keybuf_size";
	protected static final String DATABUF_SIZE = "databuf_size";
	protected static final String KEYLEN_START = "keylen_start";
	protected static final String KEYLEN_END = "keylen_end";
	protected static final String START_KEY = "start_key";
	protected static final String END_KEY = "end_key";
	protected static final String START_SEQ = "start_seq";
	protected static final String END_SEQ = "end_seq";

	/* Default property value listed in alphabetical order */
	protected static final int DATA_LEN_DEFAULT_VALUE = 0;
	protected static final int DATA_OFFSET_DEFAULT_VALUE = 0;
	protected static final int DATABUF_SIZE_value = 0;
	protected static final int KEY_DEFAULT_VALUE = 0;
	// KEY_LEN_DEFAULT_VALUE is modified by yjiang from 0 to 1
	protected static final int KEY_LEN_DEFAULT_VALUE = 1;
	protected static final int KEYBUF_SIZE_value = 0;
	protected static final int KEY_OFFSET_DEFAULT_VALUE = 0;
	protected static final int NOPS_DEFAULT_VALUE = 1;
	protected static final int NUM_SHARDS_DEFAULT_VALUE = 1;
	protected static final int SIZE_DEFAULT_VALUE = 0;
	protected static final boolean CHECK_DEFAULT_VALUE = false;
	protected static final boolean EVICTING_DEFAULT_VALUE = false;
	protected static final boolean FIFO_MODE_DEFAULT_VALUE = false;
	protected static final boolean KEEP_READ_DEFAULT_VALUE = false;
	protected static final boolean PERSISTENT_DEFAULT_VALUE = false;
	protected static final boolean WRITETHRU_DEFAULT_VALUE = false;
	protected static final String DURABILITY_LEVEL_DEFAULT_VALUE = "ZS_DURABILITY_PERIODIC";
	protected static final String TYPE_DEFAULT_VALUE = "HASH";
	protected static final String ZS_CTNR_DEFAULT_VALUE = "ZS_CTNR_RW_MODE";
	protected static final String ZS_WRITE_DEFAULT_VALUE = "ZS_WRITE_EXIST_OR_NOT";

	// add default value for the flag of GetRange(yjiang add)
	protected static final int FLAGS_DEFAULT_VALUE = 0;

	// Set extreme value(yjiang add)
	public static final int MAX_KEY_LEN = 200;
	public static final int MAX_DATA_LEN = 8 * 1024 * 1024; // 8M

	private Properties args;

	protected ZSCommandExec zsCommandExec;

	public JTFCommand(String rawCommand){
		this.args = getArgs(rawCommand);
	}
	
	public void setZSCommandExec(ZSCommandExec zsCommandExec) {
		this.zsCommandExec = zsCommandExec;
	}

	public abstract String execute() throws Exception;

	public abstract String getZSCommandExecName();

//	public void setArgs(Properties args) {
//		this.args = args;
//	}

	public abstract Object getZSEntry() throws ZSContainerException, JTFException, Exception;
	
	private  Properties getArgs(String rawCommand) {
		String[] tokens = rawCommand.split("\\s+");
		
		Properties args = new Properties();

		for (int i = 1; i < tokens.length; i++) {

			String[] arg = tokens[i].split("=");
			args.setProperty(arg[0], arg[1]);
		}

		return args;
	}
	
	public static String handleSuccessReturn(){
		return "OK";
	}

	public static String handleServerErrorReturn(Exception e) {
		return "SERVER_ERROR " + e.toString();
	}

	public static String handleClientErrorReturn(Exception e) {
		return "CLIENT_ERROR " + e.toString();
	}

	protected String getStringProperty(String key, boolean must)
			throws JTFException {
		//return getProperty(key, must);
		return getStringProperty(key, must, null);
	}

	protected String getStringProperty(String key, boolean must,
			String defaultValue) throws JTFException {
		String value = getProperty(key, must);
		
		if(defaultValue==null){
			return value;
		}
		
		return value == null ? defaultValue : value;
	}

	protected Integer getIntegerProperty(String key, boolean must)
			throws JTFException {
//		String value = getProperty(key, must);
//		try {
//			return value == null ? null : new Integer(value);
//		} catch (NumberFormatException e) {
//			throw new JTFException(e.toString());
//		}
		return getIntegerProperty(key,must, null);
	}

	protected Integer getIntegerProperty(String key, boolean must,
			Integer defaultValue) throws JTFException {
		String value = getProperty(key, must);
		
		try {
			return value == null ? defaultValue : new Integer(value);
		} catch (NumberFormatException e) {
			throw new JTFException(e.toString());
		}
	}

	protected Long getLongProperty(String key, boolean must)
			throws JTFException {
//		String value = getProperty(key, must);
//		try {
//			return value == null ? null : new Long(value);
//		} catch (NumberFormatException e) {
//			throw new JTFException(e.toString());
//		}
		return getLongProperty(key, must, null);
	}

	protected Long getLongProperty(String key, boolean must, Long defalutValue)
			throws JTFException {
		String value = getProperty(key, must);
		try {
			return value == null ? defalutValue : new Long(value);
		} catch (NumberFormatException e) {
			throw new JTFException(e.toString());
		}
	}

	protected Boolean getBooleanProperty(String key, boolean must)
			throws JTFException {
		// String value = getProperty(key, must);
		// if(value == null)
		// {
		// return null;
		// }
		// if (!value.equals("yes") && !value.equals("no")) {
		// throw new JTFException(key + "neither \'yes\' nor \'no\'");
		// }
		// return value.equals("yes") ? Boolean.TRUE : Boolean.FALSE;
		return getBooleanProperty(key, must, null);
	}

	protected Boolean getBooleanProperty(String key, boolean must,
			Boolean defalutValue) throws JTFException {
		String value = getProperty(key, must);
		if (value == null) {
			return defalutValue;
		}
		if (!value.equals("yes") && !value.equals("no")) {
			throw new JTFException(key + "neither \'yes\' nor \'no\'");
		}
		return value.equals("yes") ? Boolean.TRUE : Boolean.FALSE;
	}

	private String getProperty(String key, boolean must) throws JTFException {
		String value = args.getProperty(key);
		if (value == null && must) {
			throw new JTFException(key + " not found");
		} else {
			return value;
		}
	}
}
