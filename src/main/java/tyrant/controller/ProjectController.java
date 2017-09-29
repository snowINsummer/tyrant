package tyrant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.utils.JSONFormat;
import tyrant.body.ProjectInfo;
import tyrant.common.constants.Constants;
import tyrant.common.entity.ReqData;
import tyrant.common.entity.RspData;
import tyrant.common.exception.TyrantException;
import tyrant.service.ProjectService;

/**
 * Created by zhangli on 30/6/2017.
 */

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RspData save(@RequestBody ReqData reqData){
        RspData rspData = new RspData();
        try {
            String json = JSONFormat.getObjectToJson(reqData.getData());
            ProjectInfo projectInfo = JSONFormat.fromJson(json, ProjectInfo.class);
            projectService.save(projectInfo);
            rspData.setCode(Constants.CODE_SUCCESS);
            rspData.setMessage(Constants.SAVE_SUCCESS);
        }catch (TyrantException e){
            rspData.setCode(e.getCode());
            rspData.setMessage(e.getMessage());
        }catch (Exception e){
            rspData.setMessage(e.getMessage());
        }
        return rspData;
    }
}
