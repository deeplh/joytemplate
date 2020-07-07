package com.bcwms.bean.studio;


import com.bcwms.entity.TblDanceStudio;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;


@PaginationAction(listBeanClass=TblDanceStudio.class
		,sql="select new TblDanceStudio(tds.id,tds.name,tds.createUserId,tds.createDateTime," +
		" tds.status,tdu.aka,tdu.avatarUrl) from TblDanceStudio tds,TblDanceUser tdu where tdu.userId=tds.createUserId "
		,sqlTail = " order by tds.id desc ")
public class SearchStudioAll {


	@PaginationField(sql=" and tds.status=:status ")
	private String status;


	@PaginationField(sql=" and tds.name like:name ")
	private String name;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
			return "%"+this.name+"%";
	}
		public void setName(String name) {
			this.name = name;
		}

}
