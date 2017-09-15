package tyrant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.exception.HTTPException;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.ResponseInfo;
import qa.utils.JSONFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.ReqData;
import tyrant.common.entity.RspData;
import tyrant.body.WSDataVo;
import tyrant.service.WSService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
            if (responseInfo.getStatus() == 200){
                Map map = JSONFormat.getMapFromJson(responseInfo.getContent());
                rspData.setCode(Constants.CODE_SUCCESS);
                rspData.setData(map);
            }else {
                rspData.setData(responseInfo);
            }

        }catch (Exception e){
            rspData.setData(e.getMessage());
            e.printStackTrace();
        }
        return rspData;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public RspData uploadFile(HttpServletRequest request){
        RspData rspData = new RspData();
        String data = request.getParameter("data");
        WSDataVo wsDataVo = JSONFormat.fromJson(data, WSDataVo.class);
        try {
            Part filePart = request.getPart("file");
            wsDataVo.setFilePart(filePart);
            ResponseInfo responseInfo = wsService.sendMessage(wsDataVo);
            if (responseInfo.getStatus() == 200){
                Map map = JSONFormat.getMapFromJson(responseInfo.getContent());
                rspData.setCode(Constants.CODE_SUCCESS);
                rspData.setData(map);
            }else {
                rspData.setData(responseInfo);
            }
        } catch (Exception e) {
            rspData.setData(e.getMessage());
            e.printStackTrace();
        }
        return rspData;
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public RspData test(HttpServletRequest request){
        RspData rspData = new RspData();
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String url = "http://test-fk.xxd.com/verify/pre_addBaseInfo.htm";
        Map header = new HashMap();
        header.put("Content-Type","application/x-www-form-urlencoded");
        header.put("cookie","gr_user_id=8641d984-5347-4b0a-ba27-a24f5e64b5b1; _ga=GA1.2.7A05B3DB-CAD0-440E-AD2D-900081333731.1504591862922; JSESSIONID=CF7BD9000BA2A970373B2498C2B283B9");
        String json = "{\n" +
                "    \"customerForm.accountBalance\": \"1\",\n" +
                "    \"customerForm.age\": \"37\",\n" +
                "    \"customerForm.belongArea\": \"0\",\n" +
                "    \"customerForm.birthday\": \"1980-03-21\",\n" +
                "    \"customerForm.carNum\": \"1\",\n" +
                "    \"customerForm.carPrice\": \"1\",\n" +
                "    \"customerForm.children\": \"\",\n" +
                "    \"customerForm.constellation\": \"白羊座\",\n" +
                "    \"customerForm.createdBy\": \"hejunhao\",\n" +
                "    \"customerForm.createdTime\": \"2017-09-14 18:39:06.0\",\n" +
                "    \"customerForm.customerVocation\": \"549\",\n" +
                "    \"customerForm.domicile\": \"1\",\n" +
                "    \"customerForm.education\": \"6\",\n" +
                "    \"customerForm.email\": \"zhangli@xinxindai.com\",\n" +
                "    \"customerForm.guarantor\": \"\",\n" +
                "    \"customerForm.homePhone\": \"1\",\n" +
                "    \"customerForm.houseDateEnd\": \"\",\n" +
                "    \"customerForm.houseDateStart\": \"\",\n" +
                "    \"customerForm.houseNum\": \"1\",\n" +
                "    \"customerForm.housePrice\": \"1\",\n" +
                "    \"customerForm.housingConditions\": \"0\",\n" +
                "    \"customerForm.id\": \"28623\",\n" +
                "    \"customerForm.idCardAddr\": \"1\",\n" +
                "    \"customerForm.idCardAuth\": \"\",\n" +
                "    \"customerForm.idNumber\": \"632626198003213899\",\n" +
                "    \"customerForm.isOfficeHouse\": \"0\",\n" +
                "    \"customerForm.maritalNumYear\": \"1\",\n" +
                "    \"customerForm.maritalStatus\": \"1\",\n" +
                "    \"customerForm.mobilePhonesNum\": \"13319878763\",\n" +
                "    \"customerForm.name\": \"樊亚测试\",\n" +
                "    \"customerForm.nowResidence\": \"1\",\n" +
                "    \"customerForm.officeCondition\": \"0\",\n" +
                "    \"customerForm.officeDateEnd\": \"\",\n" +
                "    \"customerForm.officeDateStart\": \"\",\n" +
                "    \"customerForm.officeTotalArea\": \"1\",\n" +
                "    \"customerForm.sex\": \"0\",\n" +
                "    \"customerForm.unitAddress\": \"1\",\n" +
                "    \"customerForm.unitPhone\": \"1\",\n" +
                "    \"customerForm.zodiac\": \"1\",\n" +
                "    \"orderNo\": \"S3100170914006\"\n" +
                "}";
        Map data = JSONFormat.getMapFromJson(json);
//        ResponseInfo responseInfo = httpClientUtil.executePostFormUrlencoded(url,false,header,data,null);
        ResponseInfo responseInfo;
        try {
            responseInfo = httpClientUtil.executePatchWithHeaders(url,header,json);
            rspData.setData(responseInfo);
        } catch (HTTPException e) {
            rspData.setData(e);
            e.printStackTrace();
        }
//        rspData.setData(responseInfo);
        return rspData;
    }


}
