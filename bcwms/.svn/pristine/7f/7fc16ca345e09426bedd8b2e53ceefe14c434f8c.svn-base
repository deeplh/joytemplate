package com.bcwms.bean.crew;


import com.bcwms.entity.TblDanceCrew;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceCrew.class
		,sql="select new TblDanceCrew(tdc.id,tdc.name,tdc.createUserId,tdc.createDateTime," +
		" tdc.status,tdu.aka,tdu.avatarUrl) " +
		" from TblDanceCrew tdc,TblDanceUser tdu where tdu.userId=tdc.createUserId "
		,sqlTail = " order by tdc.id desc ")
public class SearchCrewAll {


	@PaginationField(sql=" and tdc.status=:status ")
	private String status;


	@PaginationField(sql=" and tdc.name like:name ")
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
