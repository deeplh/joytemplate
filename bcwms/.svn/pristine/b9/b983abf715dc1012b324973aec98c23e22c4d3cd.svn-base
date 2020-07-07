package com.bcwms.bean.studio;



import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceStudio;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;


import java.util.Date;



@PaginationAction(listBeanClass=TblDanceStudio.class
		,sql=" from TblDanceStudio where status="+BcwConstant.STATUS_2_YES,isPagination = false)
public class SearchStudio {


	@PaginationField(sql=" and name=:name ")
	private String name;
	

	@PaginationField(sql=" and id=:id ")
	private Integer id;

	

	@PaginationField(sql=" and establishDate=:establishDate ")
	private Date establishDate;
	

	@PaginationField(sql=" and founder=:founder ")
	private String founder;
	

	

		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getId() {
			return this.id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getEstablishDate() {
			return this.establishDate;
		}
		public void setEstablishDate(Date establishDate) {
			this.establishDate = establishDate;
		}
		public String getFounder() {
			return this.founder;
		}
		public void setFounder(String founder) {
			this.founder = founder;
		}

}
