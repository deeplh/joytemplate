package com.bcwms.service.impl.studio;


import com.bcwms.entity.TblDanceStudioStore;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class StudioStoreServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;



    public void createStudioStore(TblDanceStudioStore obj, List<FileAttrBean> fileAttrBeanList) throws Exception {

        if(null!=fileAttrBeanList && fileAttrBeanList.size()>0){
            obj.setImg(fileAttrBeanList.get(0).getNewName());
        }
        dao.save(obj);
    }


    public TblDanceStudioStore getStudioStore(Integer id) throws Exception {
        TblDanceStudioStore objLocal = dao.get(TblDanceStudioStore.class,id);
        if(null==objLocal){
            throw new KeepJoyServiceException("id数据异常");
        }
        return objLocal;
    }



    public void updateStudioStore(TblDanceStudioStore obj, List<FileAttrBean> fileAttrBeanList) throws Exception {

        if(obj.getId()==null){
            throw new KeepJoyServiceException("id格式异常");
        }

        TblDanceStudioStore objLocal = dao.get(TblDanceStudioStore.class,obj.getId());
        if(null==objLocal){
            throw new KeepJoyServiceException("id数据异常");
        }

        BcwCommonService.commonGroupUpdate(objLocal,obj,fileAttrBeanList);

    }

}
