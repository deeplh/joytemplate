package com.bcwms.controller.admin;

import com.bcwms.service.impl.studio.StudioCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/admin/studio-card",method = {RequestMethod.POST,RequestMethod.GET})
public class StudioCardAdminController {

    @Autowired
    private StudioCardServiceImpl studioCardService;
    /*
     * 充卡
     */
    @RequestMapping(value = "/charge")
    private void charge (@RequestParam Integer userId,
                         @RequestParam Integer studioId,
                         @RequestParam Integer studioStoreId,
                         @RequestParam Integer studioCardId,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate) throws Exception {
        studioCardService.chargeUserCard(userId,studioId,studioStoreId,studioCardId,beginDate);
    }

}
