package tyrant.service;

import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResultItem;
import tyrant.entity.TestcaseData;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface TestcaseDataService {
    TestcaseData queryTestcaseData(Integer testcaseModuleId, String excelNo, String caseInfo, String description);

    void queryTestcaseData(SaveResultVo saveResultVo, WSResultItem wsResultItem);
}
