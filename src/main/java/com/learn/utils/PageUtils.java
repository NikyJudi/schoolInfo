package com.learn.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * 在Java中的这个Serializable接口其实是给jvm(Java虚拟机)看的，通知jvm，我不对这个类做序列化了，你(jvm)帮我序列化就好了
 *Serializable用于将对象序列化，序列化是将对象状态转换为可保持或传输的格式的过程。
 *
 * 4#12:59:00
 */
public class PageUtils implements Serializable {
	//serialVersionUID是用来辅助对象的序列化与反序列化的
	//原则上序列化后的数据当中的serialVersionUID与当前类当中的serialVersionUID一致
	private static final long serialVersionUID = 1L;
	//总记录数
	private int totalCount;
	//每页记录数
	private int pageSize;
	//总页数
	private int totalPage;
	//当前页数
	private int currPage;
	//列表数据
	private List<?> list;
	
	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currPage    当前页数
	 */
	public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
}
