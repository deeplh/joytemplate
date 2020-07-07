package com.keepjoy.core.bean.pagination;

import java.util.ArrayList;
import java.util.List;

public class Pagination{

	private int pageSize=10;

	private int page=1;

	private int total;//所有记录数

	private int pages;//所有页数

	private List<?> rows;
	
	
	public Pagination(){
		
	}

	public Pagination(List<?> rows, int _totalRecords, int _pageIndex,int _pageSize) {
		this.rows = rows;
		this.total = _totalRecords;
		this.pageSize = _pageSize;
		this.page = _pageIndex;
		if(this.total==0)
		{
			this.pages=0;
		}else
		{
			this.pages=(this.total%this.pageSize > 0) ? (this.total/this.pageSize+1) : (this.total/this.pageSize);
		}
		
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	@SuppressWarnings("rawtypes")
	public List<?> getRows() {
		return rows==null?new ArrayList():rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}


}
