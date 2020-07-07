package com.bcwms.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.bcwms.bean.card.SearchCard;
import com.bcwms.entity.TblDanceCard;
import com.bcwms.service.impl.CardServiceImpl;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/card",method = {RequestMethod.POST,RequestMethod.GET})
public class CardAdminController {

    @Autowired
    public CardServiceImpl cardServiceImpl;

    @RequestMapping(value = "/search")
    private Object search (SearchCard sc) throws Exception
    {
        KeepJoyPagination kjp=new KeepJoyPagination(sc);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/create")
    private void create (@RequestParam String TblDanceCardStr) throws Exception {
        TblDanceCard tdlc = JSONObject.parseObject(TblDanceCardStr,TblDanceCard.class);
        cardServiceImpl.create(tdlc);
    }

    @RequestMapping(value = "/update")
    private void update (@RequestParam String TblDanceCardStr) throws Exception {
        TblDanceCard tdlc = JSONObject.parseObject(TblDanceCardStr,TblDanceCard.class);

        cardServiceImpl.update(tdlc,null);
    }

    @RequestMapping(value = "/remove")
    private void remove (@RequestParam Integer id) throws Exception {
        cardServiceImpl.remove(id);
    }

    @RequestMapping(value = "/get")
    private TblDanceCard get (@RequestParam Integer id) throws Exception {
        return cardServiceImpl.get(id);
    }

}
