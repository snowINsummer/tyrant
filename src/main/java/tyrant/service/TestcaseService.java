package tyrant.service;

import tyrant.body.SaveResultVo;
import tyrant.entity.Testcase;

import java.util.List;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface TestcaseService {

    Testcase queryTestcase(Integer moduleId, String testcaseName, String environmentName, Integer caseType);

    void queryTestcase(SaveResultVo saveResultVo);

    List<Testcase> queryTestcase(Integer moduleId);

    Testcase queryTestcase(String testcaseName);
}
