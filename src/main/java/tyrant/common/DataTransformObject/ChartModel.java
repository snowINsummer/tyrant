package tyrant.common.DataTransformObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snow.zhang on 2016/7/4.
 */
public class ChartModel {

    //图表X轴数据
    private List<String> categoryList = new ArrayList<String>();
    //图表Y轴数据
    private SeriesModel series = new SeriesModel();

    public void addCategoryList(String item){
        getCategoryList().add(item);
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public SeriesModel getSeries() {
        return series;
    }

    public void setSeries(SeriesModel series) {
        this.series = series;
    }
}
