package tyrant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qa.exception.HTTPException;
import qa.exception.RunException;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.ResponseInfo;
import qa.utils.JSONFormat;
import qa.utils.StringUtil;
import tyrant.common.constants.Constants;
import tyrant.common.entity.WSDataVo;
import tyrant.service.WSService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangli on 24/5/2017.
 */

@Service
public class WSServiceImpl implements WSService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseInfo sendMessage(WSDataVo wsResult) throws HTTPException, RunException {
        String url = wsResult.getUrl();
        String type = wsResult.getType().toUpperCase();
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
            String json = wsResult.getJson();
            String newS = getClientSign(httpClientUtil,headers,json);
            headers.put(Constants.JSON_TEMPLATE_HEADERS_S, newS);
            responseInfo = httpClientUtil.executePostKeepConnWithHeaders(url,headers,json);
        }else if(type.equals(Constants.WS_PATCH)){
            String json = wsResult.getJson();
            String newS = getClientSign(httpClientUtil,headers,json);
            headers.put(Constants.JSON_TEMPLATE_HEADERS_S, newS);
            responseInfo = httpClientUtil.executePatchKeepConnWithHeaders(url,headers,json);

        }else if(type.equals(Constants.WS_PUT)){
            String json = wsResult.getJson();
            String newS = getClientSign(httpClientUtil,headers,json);
            headers.put(Constants.JSON_TEMPLATE_HEADERS_S, newS);
            responseInfo = httpClientUtil.executePutKeepConnWithHeaders(url,headers,json);
        }
        return responseInfo;
    }

    /**
     * 获取sign值
     * @param httpClientUtil http工具类
     * @param headers 所有头信息
     * @param rspBody 响应body模板
     * @return
     * @throws HTTPException
     * @throws RunException
     */
    public String getClientSign(HttpClientUtil httpClientUtil, Map<String, String> headers, String rspBody) throws HTTPException, RunException {
        logger.info("获取sign...");
        Map<String, String> map = new HashMap<String, String>();
//        String clientId = headers.get(Constants.REQUEST_HEADERS_CLIENTID);
        String clientId = "XXD_LOAN_API";

        String clientTime = headers.get(Constants.REQUEST_HEADERS_CLIENTTIME);
        map.put(Constants.REQUEST_HEADERS_CLIENTID, clientId);
        map.put(Constants.REQUEST_HEADERS_CLIENTTIME, clientTime);
        String url = "http://dev.xxd.com/integrationPlatform/demo/createClientSign?client_id="+clientId+"&client_time="+clientTime+"&client_key=123456&bodyString="+ StringUtil.urlEncoderUTF8(rspBody);
        ResponseInfo responseInfo = httpClientUtil.executeGetKeepConnWithHeaders(url,map);
        logger.info("sign="+responseInfo.getContent());
        if (responseInfo.getStatus() == 200){
            String content = responseInfo.getContent();
            if (content.startsWith("{")){
                throw new RunException("获取sing报错："+content);
            }else {
                return responseInfo.getContent();
            }
        }else {
            throw new RunException("获取sing报错："+JSONFormat.getObjectToJson(responseInfo));
        }
    }


}
