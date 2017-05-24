package tyrant.service.impl;

import qa.exception.HTTPException;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.ResponseInfo;
import tyrant.common.constants.Constants;
import tyrant.common.entity.WSDataVo;
import tyrant.service.WSService;

import java.util.Map;

/**
 * Created by zhangli on 24/5/2017.
 */
public class WSServiceImpl implements WSService {

    @Override
    public ResponseInfo sendMessage(WSDataVo wsResult) throws HTTPException {

        String url = wsResult.getUrl();
        String type = wsResult.getType();
        Map<String, String> headers = wsResult.getHeaders();
        Map<String, String> parameters = wsResult.getParameters();
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        ResponseInfo responseInfo = new ResponseInfo();
        if (type.equals(Constants.WS_GET)){
            String paraStr = "";
            for(Map.Entry<String, String> entry : parameters.entrySet()){
                paraStr += entry.getKey() + "=" + entry.getValue() + "&";
            }
            url += "?" + paraStr;
            responseInfo = httpClientUtil.executeGetKeepConnWithHeaders(url,headers);
        }else if(type.equals(Constants.WS_POST)){
            responseInfo = httpClientUtil.executePostKeepConnWithHeaders(url,headers,wsResult.getJson());
        }else if(type.equals(Constants.WS_PATCH)){
            responseInfo = httpClientUtil.executePatchKeepConnWithHeaders(url,headers,wsResult.getJson());

        }else if(type.equals(Constants.WS_PUT)){
            responseInfo = httpClientUtil.executePutKeepConnWithHeaders(url,headers,wsResult.getJson());
        }
        return responseInfo;
    }
}
