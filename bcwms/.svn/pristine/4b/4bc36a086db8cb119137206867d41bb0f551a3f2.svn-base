package com.keepjoy.core.controller;



import com.keepjoy.core.module.datadict.bean.SearchDataDictType;
import com.keepjoy.core.module.datadict.bean.SearchDataDictTypeValue;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class KeepJoyController implements IKeepJoyController{

    @RequestMapping(value = "/datadict/search_data_dict_type",method = {RequestMethod.POST,RequestMethod.GET})
    public Object searchDataDictType(SearchDataDictType ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }


    @RequestMapping(value = "/datadict/search_data_dict_type_value",method = {RequestMethod.POST,RequestMethod.GET})
    public Object searchDataDictTypeValue(SearchDataDictTypeValue ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }
}
