package tyrant.body;

import java.util.List;

/**
 * Created by zhangli on 20/7/2017.
 */
public class QueryLastFiveDaysResult {

    private List<TestcaseInfo> testcaseInfoList;

    public List<TestcaseInfo> getTestcaseInfoList() {
        return testcaseInfoList;
    }

    public void setTestcaseInfoList(List<TestcaseInfo> testcaseInfoList) {
        this.testcaseInfoList = testcaseInfoList;
    }
}
