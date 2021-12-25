package com.blog.dto;

import java.util.List;

public class PostResponse {
	private List<PostDto> content;
	private int pageNo;
	private int pageSize;
	private long totalNumberOfRecords;
	private int totalPages;
	private boolean last;
	
	public List<PostDto> getContent() {
		return content;
	}
	public void setContent(List<PostDto> content) {
		this.content = content;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}
	public void setTotalNumberOfRecords(long totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}

}
