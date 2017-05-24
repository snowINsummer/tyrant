package tyrant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.exception.HTTPException;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.ResponseInfo;
import qa.utils.JSONFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.RspData;

/**
 * Created by zhangli on 23/5/2017.
 */

@RestController
@RequestMapping("/integrationPlatform")
public class IntegrationPlatformController {

    @RequestMapping(value = "/api-docs", method = RequestMethod.GET)
    public RspData queryModuleTestcase(){
        RspData rspData = new RspData();
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        try {
            ResponseInfo responseInfo = httpClientUtil.executeGet("http://dev.xxd.com/integrationPlatform/v2/api-docs");
            rspData.setData(JSONFormat.getMapFromJson(responseInfo.getContent()));
            rspData.setCode(Constants.CODE_SUCCESS);
        } catch (HTTPException e) {
            e.printStackTrace();
        }
        return rspData;
    }
}
