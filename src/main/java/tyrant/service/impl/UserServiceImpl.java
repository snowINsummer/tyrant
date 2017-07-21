package tyrant.service.impl;

import org.springframework.stereotype.Service;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.ResponseInfo;
import qa.utils.DateFormat;
import qa.utils.JSONFormat;
import qa.utils.StringUtil;
import tyrant.body.UserWorkTime;
import tyrant.entity.UserWorkTimeR;
import tyrant.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangli on 16/6/2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserWorkTimeR getUserWorkTime(UserWorkTime userWorkTime) throws Exception {
        UserWorkTimeR userWorkTimeR = new UserWorkTimeR();

        String userName = userWorkTime.getName();
        Integer year = userWorkTime.getYear();
        Integer month = userWorkTime.getMonth();
        String day = userWorkTime.getDay();
        Integer dailyNeedWorkHour = userWorkTime.getDailyNeedWorkHour();
        Integer weekNeedToWorkHour = userWorkTime.getWeekNeedToWorkHour();
        if (null == year) year = DateFormat.getYear();
        if (null == month) month = DateFormat.getMonth();
        if (StringUtil.isEmpty(day)) day = "undefined";
        if (null == dailyNeedWorkHour) dailyNeedWorkHour = 9;
        if (null == weekNeedToWorkHour) weekNeedToWorkHour = 45;

        userWorkTimeR.setName(userName);

        String coderStr = StringUtil.urlEncoderUTF8(userName);
        String monthStr = StringUtil.addZeroToIntStr(month, 2);
//        String url = "http://192.168.38.205/getsomeoneinfo?name=%E5%BC%A0%E5%8A%9B&year=2017&month="+month+"&day=undefined";
        String url = "http://192.168.38.205/getsomeoneinfo?name=" + coderStr + "&year="+year+"&month=" + monthStr + "&day="+day;
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        ResponseInfo responseInfo = httpClientUtil.executeGet(url);
        List<List> list = JSONFormat.getListFromJson(responseInfo.getContent());

        Long weekWorkTime = 0l;

        // 定义输出日期格式
        Date currentDate = DateFormat.getDate();
        List<Date> days = DateFormat.dateToWeek(currentDate);
        userWorkTimeR.setToday(DateFormat.getData("yyyy-MM-dd", currentDate));
        List<String> dailyInfo = new ArrayList<String>();
        for (Date date : days) {
            List<String> punchCardTime = new ArrayList();
            String dateStr = DateFormat.getData("yyyy-MM-dd", date);
            for (List<String> l : list) {
                String time = l.get(3);
                if (time.contains(dateStr)) {
                    punchCardTime.add(time);
                }
            }
            StringBuffer info = new StringBuffer();
            info.append(dateStr);
            info.append(" 打卡时间:" + punchCardTime.toString());
            if (punchCardTime.size() > 0) {
                long dayWorkTime = DateFormat.getDiffTime(punchCardTime.get(0), punchCardTime.get(punchCardTime.size() - 1));
                weekWorkTime += dayWorkTime;
                info.append(DateFormat.getWorkTime(dayWorkTime));
                info.append("。工作满"+dailyNeedWorkHour+"小时的下班时间：" + DateFormat.getOffDutyTime(punchCardTime.get(0), dailyNeedWorkHour));
            } else {
                info.append("。未打卡。");
            }
            dailyInfo.add(info.toString());
        }
        userWorkTimeR.setDaily(dailyInfo);
        StringBuffer info = new StringBuffer();
        info.append("一周需要工作时间：" + weekNeedToWorkHour + "小时。");

        info.append("已" + DateFormat.getWorkTime(weekWorkTime)+"。");

        info.append(DateFormat.needToWork(weekNeedToWorkHour, weekWorkTime));

        String firdayData = DateFormat.getData("yyyy-MM-dd", days.get(4));
        info.append("本周五日期：" + firdayData+"。");
        info.append("本周五最早打卡时间：" + DateFormat.getPunchCardTime(weekNeedToWorkHour, weekWorkTime, firdayData, list)+"。");
        userWorkTimeR.setImportant(info.toString());
        return userWorkTimeR;
    }
}
