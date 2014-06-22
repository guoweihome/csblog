package com.csdig.cms.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csdig.cms.common.RealPathResolver;
import com.csdig.cms.model.FileWrap;
import com.csdig.cms.model.FileWrap.FileComparator;
import com.csdig.cms.service.CmsResourceMng;

@Service
public class CmsResourceMngImpl implements CmsResourceMng {

	@Autowired
	private RealPathResolver realPathResolver;

	@Override
	public List<FileWrap> listFile(String path, boolean dirAndEditable) throws Exception {
		File parent = new File(realPathResolver.get(path));
		if (parent.exists()) {
			File[] files;
			if (dirAndEditable) {
				files = parent.listFiles(filter);
			} else {
				files = parent.listFiles();
			}
			Arrays.sort(files, new FileComparator());
			List<FileWrap> list = new ArrayList<FileWrap>(files.length);
			for (File f : files) {
				list.add(new FileWrap(f, realPathResolver.get("")));
			}
			return list;
		} else {
			return new ArrayList<FileWrap>(0);
		}
	}
	
	@Override
	public FileWrap getFile(String name) throws Exception {
		File file = new File(realPathResolver.get(name));
		return new FileWrap(file,realPathResolver.get(""));
	}

	@Override
	public boolean createDir(String path, String dirName)  throws Exception{
		File parent = new File(realPathResolver.get(path));
		parent.mkdirs();
		File dir = new File(parent, dirName);
		return dir.mkdir();
	}

	@Override
	public void saveFile(String path, MultipartFile file) throws IllegalStateException, IOException {
		File dest = new File(realPathResolver.get(path), file.getOriginalFilename());
		file.transferTo(dest);
	}

	@Override
	public void createFile(String path, String filename, String data) throws IOException {
		File parent = new File(realPathResolver.get(path));
		parent.mkdirs();
		File file = new File(parent, filename);
		FileUtils.writeStringToFile(file, data, "UTF-8");
	}

	@Override
	public String readFile(String name) throws IOException {
		File file = new File(realPathResolver.get(name));
		return FileUtils.readFileToString(file, "UTF-8");
	}

	@Override
	public void updateFile(String name, String data) throws IOException {
		File file = new File(realPathResolver.get(name));
		FileUtils.writeStringToFile(file, data, "UTF-8");
	}

	@Override
	public int delete(String[] names) throws Exception {
		int count = 0;
		File f;
		for (String name : names) {
			f = new File(realPathResolver.get(name));
			if (FileUtils.deleteQuietly(f)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public void rename(String origName, String destName)  throws Exception{
		File orig = new File(realPathResolver.get(origName));
		File dest = new File(realPathResolver.get(destName));
		orig.renameTo(dest);
	}

	// 文件夹和可编辑文件则显示
	private FileFilter filter = new FileFilter() {
		public boolean accept(File file) {
			return file.isDirectory() || FileWrap.editableExt(FilenameUtils.getExtension(file.getName()));
		}
	};

	
}
