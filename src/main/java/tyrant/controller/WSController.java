package tyrant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.httpClient.ResponseInfo;
import qa.utils.JSONFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.ReqData;
import tyrant.common.entity.RspData;
import tyrant.common.entity.WSDataVo;
import tyrant.service.WSService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
//import javax.servlet.http.HttpSession;

/**
 * Created by zhangli on 24/5/2017.
 */


@RestController
@RequestMapping("/ws")
public class WSController {

    @Autowired
    WSService wsService;
    // TODO 获取客户端的session，生成一个session数据池，设定一个超时时间，超时后提示重新开始。
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public RspData saveResult(@RequestBody ReqData reqData, HttpServletRequest request){
        RspData rspData = new RspData();
        // TODO 暂时的接口没有登录需求，暂时可以不考虑session问题
//        HttpSession session = request.getSession();
//        session.getMaxInactiveInterval();
        try{
            String json = JSONFormat.getObjectToJson(reqData.getData());
            WSDataVo wsDataVo = JSONFormat.fromJson(json, WSDataVo.class);
            ResponseInfo responseInfo = wsService.sendMessage(wsDataVo);
            Map map = JSONFormat.getMapFromJson(responseInfo.getContent());
            rspData.setCode(Constants.CODE_SUCCESS);
            rspData.setData(map);
        }catch (Exception e){
            rspData.setData(e.getMessage());
            e.printStackTrace();
        }
        return rspData;
    }

}
