package tyrant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qa.exception.HTTPException;
import qa.exception.RunException;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.ResponseInfo;
import qa.utils.FileUtil;
import qa.utils.JSONFormat;
import qa.utils.StringUtil;
import tyrant.common.constants.Constants;
import tyrant.body.WSDataVo;
import tyrant.service.WSService;

import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangli on 24/5/2017.
 */

@Service
public class WSServiceImpl implements WSService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseInfo sendMessage(WSDataVo wsResult) throws Exception {
        String url = wsResult.getUrl();
        Map<String, String> parameters = wsResult.getParameters();
        String paraStr = "";
        for(Map.Entry<String, String> entry : parameters.entrySet()){
            paraStr += entry.getKey() + "=" + entry.getValue() + "&";
        }
        if (!paraStr.isEmpty()){
            url += "?" + paraStr;
        }
        String type = wsResult.getType().toUpperCase();
        Map<String, String> headers = wsResult.getHeaders();
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        ResponseInfo responseInfo = new ResponseInfo();
        String json = wsResult.getJson();
        if (!wsResult.isNoSign()){
            // 判断是否实时获取sign值
            httpClientUtil.setGetRspHeaders(false);
            String newS = getClientSign(httpClientUtil,headers,json);
            headers.put(Constants.JSON_TEMPLATE_HEADERS_S, newS);
        }else {
            httpClientUtil.setGetRspHeaders(true);
        }
        if (type.equals(Constants.WS_GET)){
            responseInfo = httpClientUtil.executeGetKeepConnWithHeaders(url,headers);
        }else if(type.equals(Constants.WS_POST)){

            Part part = wsResult.getFilePart();
            if (null == part){
                responseInfo = httpClientUtil.executePostKeepConnWithHeaders(url,headers,json);
            }else {
                headers.remove(Constants.REQUEST_HEADERS_CONTENTTYPE);
                String fileName = part.getSubmittedFileName();
                String tempPath = this.getClass().getResource("/").getPath()+"temp/";
                FileUtil.createFloder(tempPath);
                String filePath = tempPath + fileName;
                FileUtil.saveFile(filePath,part.getInputStream());
                File file = FileUtil.getFile(filePath);
                responseInfo = httpClientUtil.executeUploadKeepConnWithHeaders(url,headers,file);
                FileUtil.deleteFile(filePath);
            }
        }else if(type.equals(Constants.WS_PATCH)){
//            String json = wsResult.getJson();
//            String newS = getClientSign(httpClientUtil,headers,json);
//            headers.put(Constants.JSON_TEMPLATE_HEADERS_S, newS);
            responseInfo = httpClientUtil.executePatchKeepConnWithHeaders(url,headers,json);

        }else if(type.equals(Constants.WS_PUT)){
//            String json = wsResult.getJson();
//            String newS = getClientSign(httpClientUtil,headers,json);
//            headers.put(Constants.JSON_TEMPLATE_HEADERS_S, newS);
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

    private void saveFile(String saveFilePath, Part filePart) throws IOException {
        OutputStream out = null;
        InputStream filecontent = null;
        try {

            out = new FileOutputStream(new File(saveFilePath));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
    }

}
