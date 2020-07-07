package com.bcwms.bean.crew;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceUserCrewRelation;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceUserCrewRelation.class
		,sql=" select new TblDanceUserCrewRelation(tducr.id, tducr.crewId,tdc.name, tdc.img," +
		" tdu.aka, tdu.avatarUrl,tducr.userId,tducr.createDateTime) from TblDanceUserCrewRelation tducr," +
		" TblDanceCrew tdc,TblDanceUser tdu where tducr.userId=tdu.userId and tducr.crewId=tdc.id" +
		" and  tducr.status= "+BcwConstant.STATUS_0_ING,sqlTail = " order by tducr.id desc " )
public class SearchCrewApply {

	@PaginationField(sql=" and tdc.name like:name ")
	private String name;
	

	@PaginationField(sql=" and tducr.crewId in :crewId ",dataTypeWhenWithIn=Integer.class)
	private String crewId;

	@PaginationField(sql=" and tducr.crewId =:businessId ")
	private Integer businessId;

	public String getName() {
			return "%"+this.name+"%";
	}
		public void setName(String name) {
			this.name = name;
		}

	public String getCrewId() {
		return BcwUserHolder.getDanceUser().getCrewIdsForRole();
	}

	public void setCrewId(String crewId) {
		this.crewId = crewId;
	}

	public Integer getBusinessId() {
		BcwCommonService.removeNoticeMeByTypeId(BcwConstant.NOTICE_PUBLISH,BcwConstant.TYPE_CREW,this.businessId);

		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
}
