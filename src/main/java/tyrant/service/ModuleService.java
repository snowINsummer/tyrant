package tyrant.service;

import tyrant.body.SaveResultVo;
import tyrant.entity.Module;

import java.util.List;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface ModuleService {
    Module queryModule(Integer projectId, String moduleName);

    void queryModule(SaveResultVo saveResultVo);

    List<Module> queryModuleTestcase();

    List<Module> queryAll();
}
