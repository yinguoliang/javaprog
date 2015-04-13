package com.elva.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**
 * JDK原生方法
 * @author lenovo
 *
 */
public class RawZip {
	/**
	 * 用java自带的类压缩文件
	 * @throws Exception
	 */
	public void zip() throws Exception{
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("f:/temp/test.zip"));
		//*********  add text entry ********
		ZipEntry entry1 = new ZipEntry("a.txt");
		entry1.setComment("file:text");
		entry1.setTime(System.currentTimeMillis());
		zos.putNextEntry(entry1);
		FileInputStream fis1 = new FileInputStream("f:/temp/a.txt");
		int i = 0;
		while((i=fis1.read())!=-1)
			zos.write(i);
		fis1.close();
		//******** add jpg ***********
		ZipEntry entry2 = new ZipEntry("Penguins.jpg");
		entry2.setComment("file:jpg");
		entry2.setTime(System.currentTimeMillis());
		zos.putNextEntry(entry2);
		FileInputStream fis2 = new FileInputStream("f:/temp/Penguins.jpg");
		while((i=fis2.read())!=-1)
			zos.write(i);
		fis2.close();
		//******** add dir ***********
		ZipEntry entry3 = new ZipEntry("dirs/a/");
		entry3.setComment("file:dis");
		entry3.setTime(System.currentTimeMillis());
		zos.putNextEntry(entry3);
		//******** add dir ***********
		ZipEntry entry4 = new ZipEntry("dirs/a/resize.gif");
		entry4.setComment("file:dissss");
		entry4.setTime(System.currentTimeMillis());
		zos.putNextEntry(entry4);		
		FileInputStream fis4 = new FileInputStream("f:/temp/Penguins.jpg");
		while((i=fis4.read())!=-1)
			zos.write(i);
		fis4.close();
		
		zos.close();
	}
	/**
	 * 使用java自带的方法进行解压
	 * @throws Exception
	 */
	public void unzip()throws Exception{
		//
		File zipFile = new File("f:/temp/temp.zip");
		String destPath = zipFile.getParentFile().getAbsolutePath();
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
		ZipEntry entry = null;
		//遍历entry，zip中包括文件夹及子文件都是一个entry,zis会一次遍历
		while((entry=zis.getNextEntry())!=null){
			if(entry.isDirectory()){
				System.out.println("unzip dirs>>> "+entry.getName());
				File dest = new File(destPath+"/"+entry.getName());
				if(dest.exists()==false)
					dest.mkdirs();
			}else{
				System.out.println("unzip file>>> "+entry.getName());
				File dest = new File(destPath+"/"+entry.getName());
				if(dest.getParentFile().exists()==false)
					dest.getParentFile().mkdirs();
				FileOutputStream fos = new FileOutputStream(dest);
				int i = 0;
				while((i=zis.read())!=-1){
					fos.write(i);
				}
				fos.close();
			}
		}
		zis.close();
	}
	public static void main(String[] args) throws Exception {
		new RawZip().zip();
	}

}
