package tyrant.common.DataTransformObject;

/**
 * Created by zhangli on 20/7/2017.
 */
public class LastFiveDaysResult {

    private String moduleName;
    private Integer lastFiveDaysSuccess;
    private Integer lastFiveDaysFailure;

    public Integer getLastFiveDaysSuccess() {
        return lastFiveDaysSuccess;
    }

    public void setLastFiveDaysSuccess(Integer lastFiveDaysSuccess) {
        this.lastFiveDaysSuccess = lastFiveDaysSuccess;
    }

    public Integer getLastFiveDaysFailure() {
        return lastFiveDaysFailure;
    }

    public void setLastFiveDaysFailure(Integer lastFiveDaysFailure) {
        this.lastFiveDaysFailure = lastFiveDaysFailure;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }


}
