package tyrant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.httpClient.ResponseInfo;
import qa.utils.JSONFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.RspData;
import tyrant.common.entity.WSDataVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.Map;

/**
 * Created by zhangli on 26/6/2017.
 */

@RestController
@RequestMapping("/testcase")
public class TestcaseController {


    /**
     * 保存用例数据到数据库
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RspData uploadFile(HttpServletRequest request){
        RspData rspData = new RspData();
        String data = request.getParameter("data");
        WSDataVo wsDataVo = JSONFormat.fromJson(data, WSDataVo.class);
        try {
            Part filePart = request.getPart("file");
            if (null != filePart.getName()){
                wsDataVo.setFilePart(filePart);
            }
//            ResponseInfo responseInfo = wsService.sendMessage(wsDataVo);
//            if (responseInfo.getStatus() == 200){
//                Map map = JSONFormat.getMapFromJson(responseInfo.getContent());
//                rspData.setCode(Constants.CODE_SUCCESS);
//                rspData.setData(map);
//            }else {
//                rspData.setData(responseInfo);
//            }
        } catch (Exception e) {
            rspData.setData(e.getMessage());
            e.printStackTrace();
        }
        return rspData;
    }

}
