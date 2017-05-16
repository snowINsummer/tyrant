package tyrant.service;

import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResultItem;
import tyrant.entity.TestcaseModule;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface TestcaseModuleService {

    TestcaseModule queryTestcaseModule(Integer testcaseId, String testcaseModuleName);

    void queryTestcaseModule(SaveResultVo saveResultVo, WSResultItem wsResultItem);
}
