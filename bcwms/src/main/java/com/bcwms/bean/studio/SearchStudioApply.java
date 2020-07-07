package com.bcwms.bean.studio;



import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceUserStudioRelation;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;


@PaginationAction(listBeanClass=TblDanceUserStudioRelation.class
		,sql=" select new TblDanceUserStudioRelation(tdusr.id, tdusr.studioId,tdc.name, tdc.img," +
		" tdu.aka, tdu.avatarUrl,tdusr.userId,tdusr.createDateTime) from TblDanceUserStudioRelation tdusr," +
		" TblDanceStudio tdc,TblDanceUser tdu where tdusr.userId=tdu.userId and tdusr.studioId=tdc.id" +
		" and  tdusr.status= "+BcwConstant.STATUS_0_ING,sqlTail = " order by tdusr.id desc " )
public class SearchStudioApply {



	@PaginationField(sql=" and tdc.name like:name ")
	private String name;
	

	@PaginationField(sql=" and tdusr.studioId in :studioId ",dataTypeWhenWithIn=Integer.class)
	private String studioId;

	@PaginationField(sql=" and tdusr.studioId =:businessId ")
	private Integer businessId;

	public String getName() {
			return "%"+this.name+"%";
	}
		public void setName(String name) {
			this.name = name;
		}

	public String getStudioId() {
		return BcwUserHolder.getDanceUser().getStudioIdsForRole();
	}

	public void setStudioId(String studioId) {
		this.studioId = studioId;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
}

