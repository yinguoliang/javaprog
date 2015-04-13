package com.elva.zip;

import java.io.File;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class ZipCompressorAnt {

	private File zipFile;

	public ZipCompressorAnt(String pathName) {
		zipFile = new File(pathName);
	}
	
	public void compressDirctory(String srcPathName) {
		File srcdir = new File(srcPathName);
		if (!srcdir.exists())
			throw new RuntimeException(srcPathName + "�����ڣ�");
		
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);
		
		//fileSet.setIncludes("**/*.java"); ������Щ�ļ����ļ��� eg:zip.setIncludes("*.java");
		//fileSet.setExcludes(...); �ų���Щ�ļ����ļ���
		zip.addFileset(fileSet);
		
		zip.execute();
	}
	public void compressFile(String srcPathName) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "�����ڣ�");
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setFile(file);
		
		zip.addFileset(fileSet);
		zip.execute();
	}
	public void compressFile(List<File> fileList) {
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		for(File file : fileList){
			fileSet.setFile(file);
		}
		zip.addFileset(fileSet);
		zip.execute();
	}
}
