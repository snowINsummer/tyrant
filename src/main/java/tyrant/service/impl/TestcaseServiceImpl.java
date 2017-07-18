package tyrant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.SaveResultVo;
import tyrant.common.enums.Environment;
import tyrant.common.enums.TestcaseType;
import tyrant.dao.ITestcaseDao;
import tyrant.entity.Testcase;
import tyrant.service.TestcaseService;

import java.util.List;

/**
 * Created by zhangli on 10/5/2017.
 */

@Service
public class TestcaseServiceImpl implements TestcaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ITestcaseDao iTestcaseDao;

    @Override
    public Testcase queryTestcase(Integer moduleId, String testcaseName, String environmentName, Integer caseType) {
        Testcase testcase = iTestcaseDao.queryTestcase(moduleId, testcaseName, environmentName, caseType);
        if (null == testcase){
            testcase = new Testcase();
            testcase.setModuleId(moduleId);
            testcase.setTestcaseName(testcaseName);
            testcase.setEnvironmentName(environmentName);
            testcase.setCaseType(caseType);
            testcase.setCreateTime(DateFormat.getDate());
            testcase.setIsActive(Constants.DB_DATA_ACTIVE);
            testcase = iTestcaseDao.save(testcase);
        }
        return testcase;
    }

    @Override
    public void queryTestcase(SaveResultVo saveResultVo) {
        // 根据 testcaseName moduleId 获取 testcaseId
        String testcaseName = saveResultVo.getTestcaseName();

        if (null != testcaseName && null == saveResultVo.getTestcase()){
            if (null == saveResultVo.getCaseType()){
                if (testcaseName.contains(Constants.TESTCASE_TYPE_WS)) {
                    saveResultVo.setCaseType(TestcaseType.WS.type());
                }else if(testcaseName.contains(Constants.TESTCASE_TYPE_UI)){
                    saveResultVo.setCaseType(TestcaseType.UI.type());
                }else if(testcaseName.contains(Constants.TESTCASE_TYPE_UT)){
                    saveResultVo.setCaseType(TestcaseType.UT.type());
                }else {
                    saveResultVo.setCaseType(TestcaseType.WS.type());
//                    saveResultVo.setCaseType(0);
                }
            }
            Environment environment = Environment.GET;
            String environmentName = environment.getEnvironment(testcaseName);
            Testcase testcase = queryTestcase(saveResultVo.getModule().getId(), testcaseName, environmentName, saveResultVo.getCaseType());

            saveResultVo.setTestcase(testcase);
        }

        if (null == testcaseName && null == saveResultVo.getTestcase()){
            logger.error("null == testcaseName && null == saveResultVo.getTestcase()");
        }
    }

    @Override
    public List<Testcase> queryTestcase(Integer moduleId) {
        return iTestcaseDao.queryTestcase(moduleId);
    }
}
