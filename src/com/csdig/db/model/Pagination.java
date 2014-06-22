/*
 *  Date: 2011-7-21 ����02:19:16
 *  Copyright (c) 2011 asiainfo-linkage
 */
package com.csdig.db.model;

import java.util.List;

public class Pagination<T> {

	public static final int DEF_COUNT = 20;

	protected int pageSize = Pagination.DEF_COUNT;

	protected int pageNo = 1;

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	/**
	 * �Ƿ��һҳ
	 */
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	/**
	 * ��һҳҳ��
	 */
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * �Ƿ����һҳ
	 */
	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}

	/**
	 * ��һҳҳ��
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * ����ҳ�룬ʹ���������ҳ��
	 */
	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int tp = getCount();
		if (pageNo > tp) {
			pageNo = tp;
		}
	}

	/**
	 * ���ҳ��
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * ÿҳ�������
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * �ܹ���ҳ
	 */
	public int getTotalPage() {
		int totalPage = count / pageSize;
		if (totalPage == 0 || count % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}

	public Pagination() {
	}

	public Pagination(int pageNo, int pageSize, int count) {
		setPageNo(pageNo);
		setPageSize(pageSize);
		setCount(count);
		adjustPageNo();
	}

	private int count;

	private List<T> list;

	private List<T> footer;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getFooter() {
		return footer;
	}

	public void setFooter(List<T> footer) {
		this.footer = footer;
	}

	/**
	 * ��һ�����λ��
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

}
