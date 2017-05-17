package tyrant.common.entity;

/**
 * Created by zhangli on 10/5/2017.
 */
public class WSResultItem {


    private String testcaseModuleName;
    private String excelNo;
    private String caseInfo;
    private String status;
    private String startTime;
    private String finishTime;
    private String duration;
    private String description;

    public String getTestcaseModuleName() {
        return testcaseModuleName;
    }

    public void setTestcaseModuleName(String testcaseModuleName) {
        this.testcaseModuleName = testcaseModuleName;
    }

    public String getExcelNo() {
        return excelNo;
    }

    public void setExcelNo(String excelNo) {
        this.excelNo = excelNo;
    }

    public String getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(String caseInfo) {
        this.caseInfo = caseInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
