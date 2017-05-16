package tyrant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResultItem;
import tyrant.dao.ITestcaseModuleDao;
import tyrant.entity.TestcaseModule;
import tyrant.service.TestcaseModuleService;

/**
 * Created by zhangli on 10/5/2017.
 */

@Service
public class TestcaseModuleServiceImpl implements TestcaseModuleService {

    @Autowired
    ITestcaseModuleDao iTestcaseModuleDao;

    @Override
    public TestcaseModule queryTestcaseModule(Integer testcaseId, String testcaseModuleName) {
        TestcaseModule testcaseModule = iTestcaseModuleDao.queryTestcaseModule(testcaseId, testcaseModuleName);
        if (null == testcaseModule){
            testcaseModule = new TestcaseModule();
            testcaseModule.setTestcaseId(testcaseId);
            testcaseModule.setTestcaseModuleName(testcaseModuleName);
            testcaseModule.setCreateTime(DateFormat.getDate());
            testcaseModule.setIsActive(Constants.DB_DATA_ACTIVE);
            testcaseModule = iTestcaseModuleDao.save(testcaseModule);
        }
        return testcaseModule;
    }

    @Override
    public void queryTestcaseModule(SaveResultVo saveResultVo, WSResultItem wsResultItem) {
        // 根据 testcaseId testcaseModuleName 获取 testcaseModuleId
        String testcaseModuleName = saveResultVo.getTestcaseModuleName();
        String testcaseModuleNameVo = wsResultItem.getTestcaseModuleName();
        if (null == testcaseModuleName || !testcaseModuleName.equals(testcaseModuleNameVo) || null == saveResultVo.getTestcaseModule()){
            Integer testcaseId = saveResultVo.getTestcase().getId();
            saveResultVo.setTestcaseModuleName(testcaseModuleNameVo);
            saveResultVo.setTestcaseModule(queryTestcaseModule(testcaseId, saveResultVo.getTestcaseModuleName()));
        }
    }
}
