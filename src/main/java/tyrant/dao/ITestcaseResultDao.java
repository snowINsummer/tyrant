package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.common.DataTransformObject.LastFiveDaysResult;
import tyrant.entity.TestcaseResult;

import java.util.List;


/**
 * Created by zhangli on 9/5/2017.
 */
@Repository
@Transactional
public interface ITestcaseResultDao extends JpaRepository<TestcaseResult,Integer> {


    @Query(value = "select * from \n" +
            "(\n" +
            "\tselect m.MODULE_NAME  from MODULE m\n" +
            "\tLEFT JOIN TESTCASE t1 on m.ID = t1.MODULE_ID\n" +
            "\twhere t1.ID = ?1\n" +
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
            "\t\t) temp_days\n" +
            "\tLEFT JOIN\n" +
            "\t\t(\n" +
            "\t\tselect bn.s,bn.BATCH_NO_STR,count(tcr.ID) num from TESTCASE_RESULT tcr \n" +
            "\t\tright JOIN \n" +
            "\t\t (\n" +
            "\t\t\tselect ss.id,ss.BATCH_NO_STR,ss.s from \n" +
            "\t\t\t\t(\n" +
            "\t\t\t\tselect b.id,b.BATCH_NO_STR,SUBSTRING(b.BATCH_NO_STR FROM 1 FOR 10) as 's' from BATCH_NO b \n" +
            "\t\t\t\twhere b.TESTCASE_ID=?1 and b.BATCH_NO_STR>date_format(DATE_SUB(NOW(), interval 5 day), '%Y-%m-%d')\n" +
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
            "\t\t\t\twhere b.TESTCASE_ID=?1 and b.BATCH_NO_STR>date_format(DATE_SUB(NOW(), interval 5 day), '%Y-%m-%d')\n" +
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
            ") at1 on 1=1",
            nativeQuery=true)
    List<Object[]> queryLastFiveDaysResult(Integer testcaseId);
}
