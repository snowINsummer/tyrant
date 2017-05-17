package tyrant.service;

import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResultItem;
import tyrant.entity.Testcase;

import java.util.List;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface TestcaseService {

    Testcase queryTestcase(Integer moduleId, String testcaseName, Integer caseType);

    void queryTestcase(SaveResultVo saveResultVo, WSResultItem wsResultItem);

    List<Testcase> queryTestcase(Integer moduleId);
}
