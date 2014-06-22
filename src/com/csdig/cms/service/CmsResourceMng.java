package com.csdig.cms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.csdig.cms.model.FileWrap;

public interface CmsResourceMng {

	public List<FileWrap> listFile(String path, boolean dirAndEditable) throws Exception;
	
	public FileWrap getFile(String name) throws Exception;

	public boolean createDir(String path, String dirName) throws Exception;

	public void saveFile(String path, MultipartFile file) throws IllegalStateException, IOException;

	public void createFile(String path, String filename, String data) throws IOException;

	public String readFile(String name) throws IOException;
	
	public void updateFile(String name, String data) throws IOException;

	public int delete(String[] names) throws Exception;

	public void rename(String origName, String destName) throws Exception;

}