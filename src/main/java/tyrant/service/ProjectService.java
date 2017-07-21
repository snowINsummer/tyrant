package tyrant.service;

import tyrant.body.ProjectInfo;
import tyrant.body.SaveResultVo;
import tyrant.common.exception.TyrantException;
import tyrant.entity.Project;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface ProjectService {

    Project queryProject(Integer productId, String projectName);

    void queryProject(SaveResultVo saveResultVo);

    void save(ProjectInfo projectInfo) throws TyrantException;
}
