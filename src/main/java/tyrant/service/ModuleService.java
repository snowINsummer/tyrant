package tyrant.service;

import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResultItem;
import tyrant.entity.Module;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface ModuleService {
    Module queryModule(Integer projectId, String moduleName);

    void queryModule(SaveResultVo saveResultVo, WSResultItem wsResultItem);
}
