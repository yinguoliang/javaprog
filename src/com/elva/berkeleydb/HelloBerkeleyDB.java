package com.elva.berkeleydb;

import java.io.File;

import org.junit.Test;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.sleepycat.je.Transaction;

public class HelloBerkeleyDB {
	@Test
	public void testBasic() {
		String dirHome = "/Users/yinguoliang/Documents/tmp/berkeleydb";
		EnvironmentConfig envConf = new EnvironmentConfig();
		envConf.setAllowCreate(true);
		envConf.setReadOnly(false);
		Environment env = new Environment(new File(dirHome), envConf);

		DatabaseConfig dbConf = new DatabaseConfig();
		dbConf.setAllowCreate(true);
		dbConf.setReadOnly(false);

		Transaction txn = null;
		Database hello = env.openDatabase(txn, "hello_db", dbConf);
		// save
		for (int i = 0; i < 200; i++) {
			DatabaseEntry key = new DatabaseEntry(("hi" + i).getBytes());
			DatabaseEntry val = new DatabaseEntry(("world_" + i).getBytes());
			hello.put(txn, key, val);
		}
		// read
		for (int i = 10; i < 20; i++) {
			String key = "hi" + i;
			DatabaseEntry keyEntry = new DatabaseEntry(("hi" + i).getBytes());
			DatabaseEntry valEntry = new DatabaseEntry();
			OperationStatus status = hello.get(txn, keyEntry, valEntry, LockMode.DEFAULT);
			if (status == OperationStatus.SUCCESS) {
				String val = new String(valEntry.getData());
				System.out.println("获取到数据:key=" + key + ", val=" + val);
			} else {
				System.out.println("获取到数据失败:key=" + key + ", status=" + status);
			}
		}
		hello.close();
	}
}
