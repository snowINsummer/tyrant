package tyrant.common.DataTransformObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snow.zhang on 2016/7/4.
 */
public class SeriesModel {

    private List<String> pass = new ArrayList<String>();
    private List<String> fail = new ArrayList<String>();
    private List<String> total = new ArrayList<String>();
    private List<String> passRate = new ArrayList<String>();

    public void addPassData(String value){
        getPassData().add(value);
    }

    public List<String> getPassData() {
        return pass;
    }

    public void addFailData(String value){
        getFailData().add(value);
    }

    public List<String> getFailData() {
        return fail;
    }

    public void addTotalData(String value){
        getTotalData().add(value);
    }

    public List<String> getTotalData() {
        return total;
    }

    public void addPassRateData(String value){
        getPassRateData().add(value);
    }

    public List<String> getPassRateData() {
        return passRate;
    }
}
