package tyrant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResultItem;
import tyrant.dao.IModuleDao;
import tyrant.entity.Module;
import tyrant.entity.Testcase;
import tyrant.service.ModuleService;
import tyrant.service.TestcaseService;

import java.util.List;

/**
 * Created by zhangli on 10/5/2017.
 */

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    IModuleDao iModuleDao;
    @Autowired
    TestcaseService testcaseService;

    @Override
    public Module queryModule(Integer projectId, String moduleName) {
        Module module = iModuleDao.queryModule(projectId, moduleName);
        if (null == module){
            module = new Module();
            module.setProjectId(projectId);
            module.setModuleName(moduleName);
            module.setCreateTime(DateFormat.getDate());
            module.setIsActive(Constants.DB_DATA_ACTIVE);
            module = iModuleDao.save(module);
        }
        return module;
    }

    @Override
    public void queryModule(SaveResultVo saveResultVo, WSResultItem wsResultItem) {
        String moduleName = saveResultVo.getModuleName();
        String moduleNameVo = wsResultItem.getModuleName();
        if (null == moduleName || !moduleName.equals(moduleNameVo) || null == saveResultVo.getModule()){
            Integer projectId = saveResultVo.getProject().getId();
            saveResultVo.setModuleName(moduleNameVo);
            saveResultVo.setModule(queryModule(projectId, saveResultVo.getModuleName()));
        }
    }

    @Override
    public List<Module> queryModuleTestcase() {
        List<Module> moduleList = queryAll();
        for (Module module : moduleList){
            List<Testcase> testcaseList = testcaseService.queryTestcase(module.getId());
            module.setTestcaseList(testcaseList);
        }
        return moduleList;
    }

    @Override
    public List<Module> queryAll() {
        return iModuleDao.findAll();
    }
}
