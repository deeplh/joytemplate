package com.bcwms.bean.event;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceUserEventRelation;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

/**
 * 查询申请加入我创建的活动的用户
 */
@PaginationAction(isUseMapModel = true
		,isNativeSql = true,
		sql=" select " +
//		" new TblDanceUserEventRelation(" +
		" tduer.id, tduer.eventId,tde.name, tde.img,tde.founderType,tde.eventType," +
		" tdu.aka, tdu.avatarUrl,tduer.userId,tduer.createDateTime,tduer.status,tduer.signDateTime " +
		" from TblDanceUserEventRelation tduer," +
		" TblDanceEvent tde," +
		" TblDanceUser tdu where tduer.userId=tdu.userId and tduer.eventId=tde.id" +
		" and tde.createUserId=:createUserId "
//		" and  (tduer.status= "+BcwConstant.STATUS_0_ING+" or tduer.status="+BcwConstant.STATUS_2_YES+")"
		,sqlTail = " order by tduer.signDateTime ")
public class SearchEventApply {

	@PaginationField(sql=" and tduer.status=:status ")
	private String status;

	@PaginationField(sql=" and tde.name like:name ")
	private String name;

	@PaginationField()
	private Integer createUserId;

	@PaginationField(sql=" and tduer.eventId =:eventId ")
	private Integer eventId;
	

	public String getName() {
			return "%"+this.name+"%";
	}
		public void setName(String name) {
			this.name = name;
		}

	public Integer getCreateUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
}
