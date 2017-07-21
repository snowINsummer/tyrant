package tyrant.service;

import tyrant.body.SaveResultVo;
import tyrant.body.WSResultItem;
import tyrant.entity.TestcaseData;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface TestcaseDataService {
    TestcaseData queryTestcaseData(Integer testcaseModuleId, String excelNo, String caseInfo, String description);

    void queryTestcaseData(SaveResultVo saveResultVo, WSResultItem wsResultItem);
}
