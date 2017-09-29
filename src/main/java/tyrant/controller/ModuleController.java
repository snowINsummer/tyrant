package tyrant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tyrant.common.entity.RspData;
import tyrant.service.ModuleService;

/**
 * Created by zhangli on 16/5/2017.
 */

@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @RequestMapping(value = "/queryModuleTestcase", method = RequestMethod.GET)
    public RspData queryModuleTestcase(){
        RspData rspData = new RspData();

        rspData.setData(moduleService.queryModuleTestcase());
        return rspData;
    }

}
