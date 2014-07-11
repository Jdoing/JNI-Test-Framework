package com.sandisk.zs.exception;

import com.sandisk.zs.NativeObject;

/**
 * File: ZSExceptionHandler.java 
 * Author: zane, ymiao, candy
 * 
 * Created on July 30, 2013
 * 
 * SanDisk Proprietary Material, © Copyright 2013 SanDisk, all rights reserved.
 * http://www.sandisk.com THIS IS NOT A CONTRIBUTION
 */

/**
 * Handler the zs error with the zs exit code.
 */
public class ZSExceptionHandler extends NativeObject{
	
	static final native String ZSStringError(int errorCode);
/*
	private static String handle(int resultCode, Object[] args){
		String message = "";
		switch(resultCode){
			case 2 : message = "ZS failure.";break;
			case 3 : message = "ZS generic failure.";break;
			case 4 : message = "ZS generic container failure.";break;
			case 5 : message = "Container not open.";break;
			case 6 : message = "Invalid container type.";break;
//			case 7 : message = "Invalid parameter.";break;
			case 7 : message = "Container %s does not exist.";break;//TODO need fix to 8
			case 9 : message = "Unpreload container failed.";break;
			case 10: message = "Container %s already exists.";break;
			case 11: message = "ZS shard not found.";break;
			case 12: message = "Object %s does not exist.";break;
			case 13: message = "Object %s already exists.";break;
			case 14: message = "Object %s is too big.";break;
			case 15: message = "Storage read failed.";break;
			case 16: message = "Storage write failed.";break;
			case 17: message = "Memory alloc failed.";break;
			case 18: message = "Lock invalid op.";break;//???
			case 19: message = "Already unlocked.";break;
			case 20: message = "Already read locked.";break;
			case 21: message = "Already write locked.";break;
			case 22: message = "Object %s not cached.";break;
			case 23: message = "SM waiting.";break;//???
			case 24: message = "To many OPIDs.";break;
			case 25: message = "Transaction conflict.";break;
			case 26: message = "PIN conflict";break;
			case 27: message = "Object %s is deleted.";break;
			case 28: message = "No transaction conflict.";break;
			case 29: message = "Already read pinned.";break;
			case 30: message = "Already write pinned.";break;
			case 31: message = "Transaction pin conflict";break;
			case 32: message = "No PIN conflict.";break;
			case 33: message = "Transaction flush.";break;
			case 34: message = "Transaction lock.";break;
			case 35: message = "Transaction unlock.";break;
			case 36: message = "Unsupported request.";break;
			case 37: message = "Unknow request.";break;
			case 38: message = "Bad bpuf pointer.";break;
			case 39: message = "Bad pdata pointer.";break;
			case 40: message = "Bad success pointer.";break;
			case 41: message = "Not pined.";break;
			case 42: message = "Not read locked.";break;
			case 43: message = "Not write locked.";break;
			case 44: message = "PIN flush.";break;
			case 45: message = "Bad context.";break;
			case 46: message = "In transaction.";break;
			case 47: message = "Noncacheable container.";break;
			case 48: message = "Out of contexts.";break;
			case 49: message = "Invalid range.";break;
			case 50: message = "Out of memory.";break;
			case 51: message = "Not in transaction.";break;
			case 52: message = "Transaction aborted.";break;
			case 53: message = "Failure MBox.";break;
			case 54: message = "Failure MSG alloc.";break;
			case 55: message = "Failure MSG send.";break;
			case 56: message = "Failure MSG receive.";break;
			case 57: message = "Enumeration end.";break;
			case 58: message = "Bad key.";break;
			case 59: message = "Failure container open.";break;
			case 60: message = "Bad pexptime pointer.";break;
			case 61: message = "Bad pinvtime pointer.";break;
			case 62: message = "Bad pstat pointer.";break;
			case 63: message = "Bad ppcbuf pointer.";break;
			case 64: message = "Bad size pointer.";break;
			case 65: message = "Expired.";break;
			case 66: message = "Expired fail.";break;
			case 67: message = "Protocol error.";break;
			case 68: message = "Too many containers.";break;
			case 69: message = "Stopped container.";break;
			case 70: message = "Get meta data failed.";break;
			case 71: message = "Put meta data failed.";break;
			case 72: message = "Get direntry failed.";break;
			case 73: message = "Expiry get failed.";break;
			case 74: message = "Expiry delete failed.";break;
			case 75: message = "Exist failed.";break;
			case 76: message = "No pshard.";break;
			case 77: message = "Shard delete service failed.";break;
			case 78: message = "Start shard map entry failed.";break;
			case 79: message = "Stop shard map entry failed.";break;
			case 80: message = "Delete shard map entry failed.";break;
			case 81: message = "Create shard map entry failed.";break;
			case 82: message = "Flash delete failed.";break;
			case 83: message = "ZS_FLASH_EPERM";break;
			case 84: message = "ZS_FLASH_ENOENT";break;
			case 85: message = "ZS_FLASH_EAGAIN";break;
			case 86: message = "ZS_FLASH_ENOMEM";break;
			case 87: message = "ZS_FLASH_EDATASIZE";break;
			case 88: message = "ZS_FLASH_EBUSY";break;
			case 89: message = "ZS_FLASH_EEXIST";break;
			case 90: message = "ZS_FLASH_EACCES";break;
			case 91: message = "ZS_FLASH_INVAL";break;
			case 92: message = "ZS_FLASH_EMFILE";break;
			case 93: message = "ZS_FLASH_ENOSPC";break;
			case 94: message = "ZS_FLASH_ENOBUFS";break;
			case 95: message = "ZS_FLASH_EDQUOT";break;
			case 96: message = "ZS_FLASH_STALE_CURSOR";break;
			case 97: message = "ZS_FLASH_EDELFAIL";break;
			case 98: message = "ZS_FLASH_EINCONS";break;
			case 99: message = "ZS_STALE_LTIME";break;
			case 100:message = "ZS_WRONG_NODE";break;
			case 101:message = "ZS_UNAVAILABLE";break;
			case 102:message = "ZS_TEST_FAIL";break;
			case 103:message = "ZS_TEST_CRASH";break;
			case 104:message = "VERSION_CHECK_NO_PEER";break;
			case 105:message = "VERSION_CHECK_BAD_VERSION";break;
			case 106:message = "VERSION_CHECK_FAILED";break;
			case 107:message = "Meta data version is too new.";break;
			case 108:message = "Meta data is invalid.";break;
			case 109:message = "BAD_META_SEQNO";break;
			case 110:message = "BAD_LTIME";break;
			case 111:message = "LEASE_EXISTS";break;
			case 112:message = "ZS_BUSY";break;
			case 113:message = "ZS_SHUTDOWN";break;
			case 114:message = "ZS_TIMEOUT";break;
			case 115:message = "ZS_NODE_DEAD";break;
			case 116:message = "ZS_SHARD_DOES_NOT_EXIST";break;
			case 117:message = "ZS_STATE_CHANGED";break;
			case 118:message = "ZS_NO_META";break;
			case 119:message = "ZS_TEST_MODEL_VIOLATION";break;
			case 120:message = "REPLICATION_NOT_READY";break;
			case 121:message = "REPLICATION_BAD_TYPE";break;
			case 122:message = "REPLICATION_BAD_STATE";break;
			case 123:message = "ZS_NODE_INVALID";break;
			case 124:message = "ZS_CORRUPT_INVALID";break;
			case 125:message = "ZS_QUEUE_FULL";break;
			case 126:message = "ZS_RMT_CONTAINER_UNKNOW";break;
			case 127:message = "FLASH_RMT_EDELFAIL";break;
			case 128:message = "LOCK RESERVED";break;
			case 129:message = "KEY_TOO_LONG";break;
			case 130:message = "NO_WRITEBACK_IN_STORE_MODE";break;
			case 131:message = "WRITEBACK_CACHING_DISABLED";break;
			case 132:message = "UPDATE_DUPLICATE";break;
			case 133:message = "FAILURE_CONTAINER_TOO_SMALL";break;
			default: message = "Execute ZS action failed.";
		}
		return String.format(message, args);
	}
	*/
	public static void handleContainer(int resultCode, Object...params) throws ZSContainerException{
		if(resultCode!=1){
			String message = ZSStringError(resultCode);
			throw new ZSContainerException(message);
		}
	}
	
	public static void handleThread(int resultCode, Object...params) throws ZSThreadException{
		if(resultCode!=1){
			String message = ZSStringError(resultCode);
			throw new ZSThreadException(message);
		}
	}
	public static void handleClient(int resultCode, Object...params) throws ZSException{
		if(resultCode!=1){
			String message = ZSStringError(resultCode);
			throw new ZSException(message);
		}
	}
}
