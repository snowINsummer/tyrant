package tyrant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResultItem;
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
    public Testcase queryTestcase(Integer moduleId, String testcaseName, Integer caseType) {
        Testcase testcase = iTestcaseDao.queryTestcase(moduleId, testcaseName, caseType);
        if (null == testcase){
            testcase = new Testcase();
            testcase.setModuleId(moduleId);
            testcase.setTestcaseName(testcaseName);
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
                    saveResultVo.setCaseType(0);
                }else if(testcaseName.contains(Constants.TESTCASE_TYPE_UI)){
                    saveResultVo.setCaseType(1);
                }else if(testcaseName.contains(Constants.TESTCASE_TYPE_UT)){
                    saveResultVo.setCaseType(2);
                }else {
                    saveResultVo.setCaseType(0);
                }
            }
            saveResultVo.setTestcase(queryTestcase(saveResultVo.getModule().getId(), testcaseName, saveResultVo.getCaseType()));
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
