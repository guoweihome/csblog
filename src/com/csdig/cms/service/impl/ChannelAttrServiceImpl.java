package com.csdig.cms.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.dao.CmsChannelAttrDAO;
import com.csdig.cms.dao.CmsChannelDAO;
import com.csdig.cms.dao.CmsModelItemDAO;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.model.CmsChannelAttr;
import com.csdig.cms.model.CmsModelItem;
import com.csdig.cms.service.ChannelAttrService;
import com.csdig.cms.utils.ImageUtils;

@Service
public class ChannelAttrServiceImpl implements ChannelAttrService {

	@Autowired
	private CmsChannelAttrDAO channelAttrDao;

	@Autowired
	private CmsChannelDAO channelDao;

	@Autowired
	private CmsModelItemDAO modelItemDao;

	@Override
	public List<CmsChannelAttr> getByChannelId(int channelId) throws Exception {
		CmsChannel channel = channelDao.findById(channelId);
		List<CmsChannelAttr> channelAttrList = channelAttrDao.getByChannelId(channelId);
		for (CmsChannelAttr attr : channelAttrList) {
			CmsModelItem modelItem = modelItemDao.getByModelIdAndField(channel.getModelId(), attr.getAttrName());
			attr.setModelItem(modelItem);
		}
		return channelAttrList;
	}

	@Override
	public List<CmsChannelAttr> listAll() throws Exception {
		return channelAttrDao.listAll();
	}

	@Override
	public void add(CmsChannelAttr attr) throws Exception {
		channelAttrDao.add(attr);
	}

	@Override
	public void update(CmsChannelAttr attr) throws Exception {
		channelAttrDao.update(attr);
	}

	@Override
	public void delete(int id) throws Exception {
		CmsChannelAttr attr = new CmsChannelAttr();
		attr.setChannelAttrId(id);
		channelAttrDao.delete(attr);
	}

	@Override
	public CmsChannelAttr getById(int id) throws Exception {
		CmsChannelAttr channelAttr = channelAttrDao.findById(id);
		CmsChannel channel = channelDao.findById(channelAttr.getChannelId());
		Integer modelId = channel.getModelId();
		String field = channelAttr.getAttrName();
		CmsModelItem modelItem = modelItemDao.getByModelIdAndField(modelId, field);
		channelAttr.setModelItem(modelItem);
		return channelAttr;
	}

	@Override
	public List<CmsChannelAttr> getByChannelIdAndAttrName(int channelId, String attrName) throws Exception {
		return channelAttrDao.getByChannelIdAndAttrName(channelId, attrName);
	}

	/**
	 * 列出可用的文件
	 */
	@Override
	public List<Hashtable<String, Object>> listSelectFiles(File currentPathFile, String order) throws Exception {
		if (StringUtils.isEmpty(order)) {
			order = "name";
		}
		List<Hashtable<String, Object>> fileList = new ArrayList<Hashtable<String, Object>>();

		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles(filter)) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(ImageUtils.IMAGE_EXT).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}

		return fileList;
	}

	// 文件夹和可编辑文件则显示
	private FileFilter filter = new FileFilter() {
		public boolean accept(File file) {
			return file.isDirectory() ||ImageUtils.isValidImageExt(FilenameUtils.getExtension(file.getName()));
		}
	};

	public class NameComparator implements Comparator<Hashtable<String, Object>> {

		@Override
		public int compare(Hashtable<String, Object> hashA, Hashtable<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
			}
		}

	}

	public class SizeComparator implements Comparator<Hashtable<String, Object>> {
		public int compare(Hashtable<String, Object> hashA, Hashtable<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator<Hashtable<String, Object>> {
		public int compare(Hashtable<String, Object> hashA, Hashtable<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
			}
		}
	}

}
