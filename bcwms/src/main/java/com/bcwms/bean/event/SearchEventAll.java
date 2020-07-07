package com.bcwms.bean.event;


import com.bcwms.entity.TblDanceEvent;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;


/**
 * 查询所有状态的
 */
@PaginationAction(listBeanClass=TblDanceEvent.class
		,sql="select new TblDanceEvent(tde.id,tde.name,tde.createUserId,tde.createDateTime," +
		" tde.address,tde.stageDate,tde.beginTime,tde.endTime, " +
		" tde.eventType,tde.maxPersonNum,tde.status," +
		" tdu.aka,tdu.avatarUrl) from TblDanceEvent tde,TblDanceUser tdu where tdu.userId=tde.createUserId ",
		sqlTail = " order by tde.id desc ")
public class SearchEventAll {


	@PaginationField(sql=" and tde.status=:status ")
	private String status;


	@PaginationField(sql=" and tde.name like:name ")
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
