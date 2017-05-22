package tyrant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.SaveResultVo;
import tyrant.dao.IProjectDao;
import tyrant.entity.Project;
import tyrant.service.ProjectService;

import java.util.List;

/**
 * Created by zhangli on 10/5/2017.
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IProjectDao iProjectDao;

    @Override
    public Project queryProject(Integer productId, String projectName) {
        Project project = iProjectDao.queryProject(productId, projectName);
        if (null == project){
            project = new Project();
            project.setProductId(productId);
            project.setProjectName(projectName);
            project.setCreateTime(DateFormat.getDate());
            project.setIsActive(Constants.DB_DATA_ACTIVE);
            project = iProjectDao.save(project);
        }
        return project;
    }

    @Override
    public void queryProject(SaveResultVo saveResultVo) {
        // 根据 projectName productId 获取 projectId
        String projectName = saveResultVo.getProjectName();
        if (null != projectName && null == saveResultVo.getProject()){
            saveResultVo.setProject(queryProject(saveResultVo.getProduct().getId(), projectName));
        }

        if (null == projectName && null == saveResultVo.getProject()){
            logger.error("null == projectName && null == saveResultVo.getProject()");
        }

    }


}
