package com.elva.h2db.store;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVStore;
import org.junit.Test;

public class MVStoreMain {
	//@Test
	public void testMVStore() throws Exception {
		for (int i = 0; i < 100; i++) {
			MVStore store = MVStore.open("/Users/yinguoliang/Documents/tmp/h2store/hello");
			String key = "hellox";
			MVMap<String, String> map = store.openMap(key);
			System.out.println(map.get(key));
			map.put(key, "world");
			System.out.println(map.get(key));
			Thread.sleep(2000);
			store.commit();
			store.close();
		}
	}

	@Test
	public void testMVStoreVersion() throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
		MVStore store = MVStore.open("/Users/yinguoliang/Documents/tmp/h2store/hello");
		store.setRetentionTime(10000000);
		String key = "testMVStoreVersion";
		long oldVersion = 80;
		MVMap<String, String> map = store.openMap(key);
		MVMap<String, String> oldMap = map.openVersion(oldVersion);
		long v = store.getCurrentVersion();
		String newValue = "HI," + sdf.format(new Date());
		String oldValue = map.put(key, newValue);
		System.out.println("new=" + newValue + ",old=" + oldValue + ",v=" + v);
		String oldVersionValue = oldMap.get(key);
		System.out.println("oldVersionValue="+oldVersionValue+", v="+oldVersion);
		store.commit();
		store.close();
	}

}
