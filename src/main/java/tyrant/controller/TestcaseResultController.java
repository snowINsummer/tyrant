package tyrant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qa.utils.JSONFormat;
import tyrant.body.QueryLastFiveDaysResult;
import tyrant.common.DataTransformObject.ChartModel;
import tyrant.common.constants.Constants;
import tyrant.common.entity.ReqData;
import tyrant.common.entity.RspData;
import tyrant.body.WSResult;
import tyrant.service.TestcaseResultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;


/**
 * Created by zhangli on 9/5/2017.
 */

@RestController
@RequestMapping("/testcase")
public class TestcaseResultController {

    @Autowired
    TestcaseResultService trService;

    @RequestMapping(value = "/saveResult", method = RequestMethod.POST)
    public RspData saveResult(@RequestBody ReqData reqData){
        RspData rspData = new RspData();
        try{
            String json = JSONFormat.getObjectToJson(reqData.getData());
            WSResult wsResult = JSONFormat.fromJson(json, WSResult.class);
            trService.saveResult(wsResult);
            rspData.setCode(Constants.CODE_SUCCESS);
            rspData.setData(Constants.SAVE_SUCCESS);
        }catch (Exception e){
            rspData.setData(Constants.SAVE_FAILURE);
            e.printStackTrace();
        }
        return rspData;
    }

    @RequestMapping(value = "/uploadReport", method = RequestMethod.POST)
    public RspData uploadReport(HttpServletRequest request){
        // contentType: false,
        RspData rspData = new RspData();
        String name = request.getParameter("name");
        try {
            Part filePart = request.getPart("file");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return rspData;

    }

    @RequestMapping(value = "/queryLastFiveDaysResult", method = RequestMethod.POST)
    public RspData queryLastFiveDaysResult(@RequestBody ReqData reqData){
        RspData rspData = new RspData();
        try{
            String json = JSONFormat.getObjectToJson(reqData.getData());
            QueryLastFiveDaysResult queryLastFiveDaysResult = JSONFormat.fromJson(json, QueryLastFiveDaysResult.class);
            ChartModel chartModel = trService.queryLastFiveDaysResult(queryLastFiveDaysResult);
            rspData.setCode(Constants.CODE_SUCCESS);
            rspData.setData(chartModel);
        }catch (Exception e){
            rspData.setData(e.getMessage());
            e.printStackTrace();
        }
        return rspData;
    }

}
