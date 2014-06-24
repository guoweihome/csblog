package com.csdig.cms.web.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.model.FileWrap;
import com.csdig.cms.service.CmsResourceMng;
import com.csdig.cms.utils.JsonDateValueProcessor;

/**
 * 资源管理
 * 
 * @author virgilguo
 * 
 */
@Controller
@RequestMapping(ConstantDefine.AdminUrl.BASE + "/resource")
public class ResourceController {

	@Autowired
	private CmsResourceMng resourceMng;

	@RequestMapping(value = "/{name}View")
	public String list(HttpServletRequest request, @PathVariable String name,
			String type) throws Exception {
		request.setAttribute("type", type);
		return "resource/" + name;
	}

	// 树
	@RequestMapping(value = "tree")
	@ResponseBody
	public String tree(String type, String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			if (StringUtils.equals(type, ConstantDefine.ResType.RESOURCE)) {
				id = ConstantDefine.ROOT_RES_DIR;
			}
			if (StringUtils.equals(type, ConstantDefine.ResType.TEMPLATE)) {
				id = ConstantDefine.TPL_BASE;
			}
		}
		List<FileWrap> list = resourceMng.listFile(id, true);
		return JSONArray.fromObject(list, dateJsonConfig()).toString();
	}

	// 修改界面
	@RequestMapping(value = "editView")
	public String editView(HttpServletRequest request, String id)
			throws Exception {
		String content = resourceMng.readFile(id);
		request.setAttribute("content", content);
		request.setAttribute("file", resourceMng.getFile(id));
		return "resource/editView";
	}

	// 列表
	@RequestMapping(value = "fileList")
	@ResponseBody
	public String fileList(HttpServletRequest request, String id, String type)
			throws Exception {
		List<FileWrap> list = resourceMng.listFile(id, false);
		request.setAttribute("list", list);
		return JSONArray.fromObject(list, dateJsonConfig()).toString();
	}

	// 修改文件
	@RequestMapping(value = "write")
	@ResponseBody
	public Map<String, Object> write(String id, String content)
			throws Exception {
		FileUtils.write(resourceMng.getFile(id).getFile(), content, "utf-8");
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	// 新建文件夹
	@RequestMapping(value = "createDir")
	@ResponseBody
	public Map<String, Object> createDir(String path, String dirName)
			throws Exception {
		resourceMng.createDir(path, dirName);
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	// 文件删除
	@RequestMapping(value = "delFile")
	@ResponseBody
	public Map<String, Object> delFile(String[] id) throws Exception {
		resourceMng.delete(id);
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	// 重命名
	@RequestMapping(value = "rename")
	@ResponseBody
	public Map<String, Object> rename(String origName, String destName)
			throws Exception {
		resourceMng.rename(origName, destName);
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(HttpServletRequest request) throws IOException {
		String responseStr = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String id = multipartRequest.getParameter("id");
		// 获取前台传值
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String ctxPath = request.getSession().getServletContext()
				.getRealPath(id);
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			File uploadFile = new File(ctxPath, fileName);
			FileCopyUtils.copy(mf.getBytes(), uploadFile);
			responseStr = "上传成功";
		}

		return responseStr;
	}

	private JsonConfig dateJsonConfig() {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		return config;
	}
}
