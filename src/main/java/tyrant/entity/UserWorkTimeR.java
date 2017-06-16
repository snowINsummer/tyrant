package tyrant.entity;

import java.util.List;

/**
 * Created by zhangli on 16/6/2017.
 */
public class UserWorkTimeR {

    private String name;
    private String today;
    private List daily;
    private String important;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public List getDaily() {
        return daily;
    }

    public void setDaily(List daily) {
        this.daily = daily;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }
}
