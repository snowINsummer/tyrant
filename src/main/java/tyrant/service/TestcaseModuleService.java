package tyrant.service;

import tyrant.body.SaveResultVo;
import tyrant.body.WSResultItem;
import tyrant.entity.TestcaseModule;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface TestcaseModuleService {

    TestcaseModule queryTestcaseModule(Integer testcaseId, String testcaseModuleName);

    void queryTestcaseModule(SaveResultVo saveResultVo, WSResultItem wsResultItem);
}
