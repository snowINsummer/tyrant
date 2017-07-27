package tyrant.common.DataTransformObject;

import java.util.List;

/**
 * Created by zhangli on 26/7/2017.
 */
public class LastFiveDaysResultReport {

    private String moduleName;
    private String testcaseName;
    private List<ReportInfo> listReport;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public List<ReportInfo> getListReport() {
        return listReport;
    }

    public void setListReport(List<ReportInfo> listReport) {
        this.listReport = listReport;
    }
}
