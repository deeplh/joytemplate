package com.bcwms.service.impl;


import com.bcwms.entity.TblDanceStyle;
import com.bcwms.entity.TblDanceUserStyleRelation;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class StyleServiceImpl {
    @Autowired
    private KeepJoyDaoImpl dao;

    public void createStyle(String name,List<FileAttrBean> fileAttrBeanList,String info){
        TblDanceStyle obj=new TblDanceStyle();
        obj.setName(name);
        obj.setInfo(info);
        obj.setCreateDateTime(BcwCommonService.getNow());
        obj.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        obj.setInitial(name.substring(0,1).toUpperCase());
        if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {
            obj.setImg(fileAttrBeanList.get(0).getNewName());
        }

        dao.save(obj);

    }


    public TblDanceStyle getStyle(Integer id){
        TblDanceStyle tds=dao.get(TblDanceStyle.class,id);
        if(null==tds){
            throw new KeepJoyServiceException("id数据异常");
        }

        TblDanceUserStyleRelation td=dao.findObjectFromListByHql(TblDanceUserStyleRelation.class,
                " from TblDanceUserStyleRelation where styleId=? and userId=? ",id,BcwUserHolder.getDanceUser().getUserId());


        if(null!=td){
            tds.setMine(true);
        }

        return tds;
    }



    public void updateStyle(TblDanceStyle style,List<FileAttrBean> fileAttrBeanList){

        if(null== style || style.getId()==null){
            throw new KeepJoyServiceException("id格式异常");
        }

        TblDanceStyle obj = dao.get(TblDanceStyle.class,style.getId());
        if(null==obj){
            throw new KeepJoyServiceException("id数据异常");
        }

        boolean isUpdate=false;

        if(StringUtils.isNotEmpty(style.getName()) && !style.getName().equals(obj.getName())){
            obj.setName(style.getName());
            isUpdate=true;
        }

        if(StringUtils.isNotEmpty(style.getInfo()) && !style.getInfo().equals(obj.getInfo())){
            obj.setInfo(style.getInfo());
            isUpdate=true;
        }

        if (null!=fileAttrBeanList && fileAttrBeanList.size()>0
                && !fileAttrBeanList.get(0).getNewName().equals(obj.getImg())) {
            obj.setImg(fileAttrBeanList.get(0).getNewName());
            isUpdate=true;
        }

        if(isUpdate)dao.update(obj);
    }





}
