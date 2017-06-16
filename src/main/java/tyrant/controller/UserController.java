package tyrant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.utils.JSONFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.ReqData;
import tyrant.common.entity.RspData;
import tyrant.common.entity.UserWorkTime;
import tyrant.entity.UserWorkTimeR;
import tyrant.service.UserService;

/**
 * Created by zhangli on 16/6/2017.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUserWorkTime", method = RequestMethod.POST)
    public RspData getUserWorkTime(@RequestBody ReqData reqData){
        RspData rspData = new RspData();
        try{
            String json = JSONFormat.getObjectToJson(reqData.getData());
            UserWorkTime userWorkTime = JSONFormat.fromJson(json, UserWorkTime.class);
            UserWorkTimeR userWorkTimeR = userService.getUserWorkTime(userWorkTime);
            rspData.setCode(Constants.CODE_SUCCESS);
            rspData.setData(userWorkTimeR);
        }catch (Exception e){
            rspData.setData(e.getMessage());
            e.printStackTrace();
        }
        return rspData;
    }

}
