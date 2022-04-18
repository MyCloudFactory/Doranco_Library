package com.doranco.multitiers.utils;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int PAGESIZE;

	private int pageNumber;

	private long totalCount;

	private List<T> content;

	

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page(int pAGESIZE, int pageNumber, long totalCount, List<T> content) {
		super();
		PAGESIZE = pAGESIZE;
		this.pageNumber = pageNumber;
		this.totalCount = totalCount;
		this.content = content;
	}

	public int getPAGESIZE() {
		return PAGESIZE;
	}

	public void setPAGESIZE(int pageSize) {
		PAGESIZE = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
