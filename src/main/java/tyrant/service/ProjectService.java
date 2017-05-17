package tyrant.service;

import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResultItem;
import tyrant.entity.Project;

import java.util.List;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface ProjectService {

    Project queryProject(Integer productId, String projectName);

    void queryProject(SaveResultVo saveResultVo);
}
