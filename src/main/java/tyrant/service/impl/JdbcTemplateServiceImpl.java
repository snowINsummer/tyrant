package tyrant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import tyrant.common.DataTransformObject.LastFiveDaysResult;
import tyrant.service.JdbcTemplateService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangli on 24/7/2017.
 */
@Service
public class JdbcTemplateServiceImpl implements JdbcTemplateService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LastFiveDaysResult> queryLastSevenDaysResult(Integer testcaseId) {
        String sql = "select * from \n" +
                "(\n" +
                "\tselect m.MODULE_NAME  from MODULE m\n" +
                "\tLEFT JOIN TESTCASE t1 on m.ID = t1.MODULE_ID\n" +
                "\twhere t1.ID = \n" + testcaseId +
                ") mt\n" +
                "LEFT JOIN\n" +
                "(\n" +
                "\tselect sum(success) lastFiveDaysSuccess,sum(failure) lastFiveDaysFailure from\n" +
                "\t(\n" +
                "\tselect '1' id,temp_days.tempday batchNo,IFNULL(stasus_success.num,0) success,IFNULL(stasus_failure.num,0) failure from \n" +
                "\t\t(\n" +
                "\t\tSELECT date_format(DATE_SUB(NOW(), interval 0 day), '%Y-%m-%d') tempday\n" +
                "\t\tUNION \n" +
                "\t\tSELECT date_format(DATE_SUB(NOW(), interval 1 day), '%Y-%m-%d') tempday\n" +
                "\t\tUNION \n" +
                "\t\tSELECT date_format(DATE_SUB(NOW(), interval 2 day), '%Y-%m-%d') tempday\n" +
                "\t\tUNION \n" +
                "\t\tSELECT date_format(DATE_SUB(NOW(), interval 3 day), '%Y-%m-%d') tempday\n" +
                "\t\tUNION \n" +
                "\t\tSELECT date_format(DATE_SUB(NOW(), interval 4 day), '%Y-%m-%d') tempday\n" +
                "\t\tUNION \n" +
                "\t\tSELECT date_format(DATE_SUB(NOW(), interval 5 day), '%Y-%m-%d') tempday\n" +
                "\t\tUNION \n" +
                "\t\tSELECT date_format(DATE_SUB(NOW(), interval 6 day), '%Y-%m-%d') tempday\n" +
                "\t\t) temp_days\n" +
                "\tLEFT JOIN\n" +
                "\t\t(\n" +
                "\t\tselect bn.s,bn.BATCH_NO_STR,count(tcr.ID) num from TESTCASE_RESULT tcr \n" +
                "\t\tright JOIN \n" +
                "\t\t (\n" +
                "\t\t\tselect ss.id,ss.BATCH_NO_STR,ss.s from \n" +
                "\t\t\t\t(\n" +
                "\t\t\t\tselect b.id,b.BATCH_NO_STR,SUBSTRING(b.BATCH_NO_STR FROM 1 FOR 10) as 's' from BATCH_NO b \n" +
                "\t\t\t\twhere b.TESTCASE_ID=" + testcaseId + " and b.BATCH_NO_STR>date_format(DATE_SUB(NOW(), interval 7 day), '%Y-%m-%d')\n" +
                "\t\t\t\tORDER BY b.BATCH_NO_STR desc \n" +
                "\t\t\t\t) ss \n" +
                "\t\t\tGROUP BY ss.s \n" +
                "\t\t\tORDER BY ss.BATCH_NO_STR desc\n" +
                "\t\t ) bn on tcr.batch_no_id = bn.id\n" +
                "\t\twhere tcr.Status='SUCCESS'\n" +
                "\t\tGROUP BY bn.id \n" +
                "\t\tORDER BY bn.id desc\n" +
                "\t\t) stasus_success on temp_days.tempday=stasus_success.s\n" +
                "\tLEFT JOIN\n" +
                "\t\t(\n" +
                "\t\tselect bn.s,bn.BATCH_NO_STR,count(tcr.ID) num from TESTCASE_RESULT tcr \n" +
                "\t\tright JOIN \n" +
                "\t\t(\n" +
                "\t\t\tselect ss.id,ss.BATCH_NO_STR,ss.s from \n" +
                "\t\t\t(\n" +
                "\t\t\t\tselect b.id,b.BATCH_NO_STR,SUBSTRING(b.BATCH_NO_STR FROM 1 FOR 10) as 's' from BATCH_NO b \n" +
                "\t\t\t\twhere b.TESTCASE_ID=" + testcaseId + " and b.BATCH_NO_STR>date_format(DATE_SUB(NOW(), interval 5 day), '%Y-%m-%d')\n" +
                "\t\t\t\tORDER BY b.BATCH_NO_STR desc \n" +
                "\t\t\t) ss \n" +
                "\t\t\tGROUP BY ss.s \n" +
                "\t\t\tORDER BY ss.BATCH_NO_STR desc\n" +
                "\t\t) bn on tcr.batch_no_id = bn.id\n" +
                "\t\twhere tcr.Status<>'SUCCESS'\n" +
                "\t\tGROUP BY bn.id \n" +
                "\t\tORDER BY bn.BATCH_NO_STR desc\n" +
                "\t\t) stasus_failure on temp_days.tempday=stasus_failure.s \n" +
                "\t) allResult\n" +
                "\tGROUP BY allResult.id\n" +
                ") at1 on 1=1";
        return (List<LastFiveDaysResult>) jdbcTemplate.query(sql, new RowMapper<LastFiveDaysResult>(){

            @Override
            public LastFiveDaysResult mapRow(ResultSet rs, int rowNum) throws SQLException {
                LastFiveDaysResult lastFiveDaysResult = new LastFiveDaysResult();
                lastFiveDaysResult.setModuleName(rs.getNString(1));
                lastFiveDaysResult.setLastFiveDaysSuccess(rs.getInt(2));
                lastFiveDaysResult.setLastFiveDaysFailure(rs.getInt(3));
                return lastFiveDaysResult;
            }

        });
    }
}
