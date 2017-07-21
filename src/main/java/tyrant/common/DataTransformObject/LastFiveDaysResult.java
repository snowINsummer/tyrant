package tyrant.common.DataTransformObject;

/**
 * Created by zhangli on 20/7/2017.
 */
public class LastFiveDaysResult {

    private String moduleName;
    private String lastFiveDaysSuccess;
    private String lastFiveDaysFailure;

    public String getLastFiveDaysSuccess() {
        return lastFiveDaysSuccess;
    }

    public void setLastFiveDaysSuccess(String lastFiveDaysSuccess) {
        this.lastFiveDaysSuccess = lastFiveDaysSuccess;
    }

    public String getLastFiveDaysFailure() {
        return lastFiveDaysFailure;
    }

    public void setLastFiveDaysFailure(String lastFiveDaysFailure) {
        this.lastFiveDaysFailure = lastFiveDaysFailure;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }


}
