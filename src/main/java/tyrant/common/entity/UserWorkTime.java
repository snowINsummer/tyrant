package tyrant.common.entity;

/**
 * Created by zhangli on 16/6/2017.
 */
public class UserWorkTime {

    private String name;
    private Integer year;
    private Integer month;
    private String day;
    private Integer dailyNeedWorkHour;
    private Integer weekNeedToWorkHour;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDailyNeedWorkHour() {
        return dailyNeedWorkHour;
    }

    public void setDailyNeedWorkHour(Integer dailyNeedWorkHour) {
        this.dailyNeedWorkHour = dailyNeedWorkHour;
    }

    public Integer getWeekNeedToWorkHour() {
        return weekNeedToWorkHour;
    }

    public void setWeekNeedToWorkHour(Integer weekNeedToWorkHour) {
        this.weekNeedToWorkHour = weekNeedToWorkHour;
    }
}
