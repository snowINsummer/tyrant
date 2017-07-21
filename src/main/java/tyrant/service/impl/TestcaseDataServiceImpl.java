package tyrant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.common.constants.Constants;
import tyrant.body.SaveResultVo;
import tyrant.body.WSResultItem;
import tyrant.dao.ITestcaseDataDao;
import tyrant.entity.TestcaseData;
import tyrant.service.TestcaseDataService;

/**
 * Created by zhangli on 10/5/2017.
 */

@Service
public class TestcaseDataServiceImpl implements TestcaseDataService {

    @Autowired
    ITestcaseDataDao iTestcaseDataDao;

    @Override
    public TestcaseData queryTestcaseData(Integer testcaseModuleId, String excelNo, String caseInfo, String description) {
        TestcaseData testcaseData = iTestcaseDataDao.queryTestcaseData(testcaseModuleId, excelNo);
        if (null == testcaseData){
            testcaseData = new TestcaseData();
            testcaseData.setTestcaseModuleId(testcaseModuleId);
            testcaseData.setExcelNo(excelNo);
            testcaseData.setCaseInfo(caseInfo);
            testcaseData.setDescription(description);
            testcaseData.setCreateTime(DateFormat.getDate());
            testcaseData.setIsActive(Constants.DB_DATA_ACTIVE);
            testcaseData = iTestcaseDataDao.save(testcaseData);
        }else {
            // 存在，更新用例数据和描述
            // update后没有更新实体类，如果获取最新的数据需要再次query
            iTestcaseDataDao.update(caseInfo, description, testcaseData.getId());
        }
        return testcaseData;
    }

    @Override
    public void queryTestcaseData(SaveResultVo saveResultVo, WSResultItem wsResultItem) {
        // 根据 testcaseModuleId excelNo 获取 testcaseDataId
        String excelNo = saveResultVo.getExcelNo();
        String excelNoVo = wsResultItem.getExcelNo();
        if (null == excelNo || !excelNo.equals(excelNoVo) || null == saveResultVo.getTestcaseData()){
            Integer testcaseModuleId = saveResultVo.getTestcaseModule().getId();
            String description = wsResultItem.getDescription();
            // TODO 测试数据
            String data = wsResultItem.getCaseInfo();
            excelNo = excelNoVo;
            saveResultVo.setTestcaseData(queryTestcaseData(testcaseModuleId, excelNo, data, description));
        }
    }
}
