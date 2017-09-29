package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.entity.Testcase;

import java.util.List;


/**
 * Created by zhangli on 10/5/2017.
 */

@Repository
@Transactional
public interface ITestcaseDao extends JpaRepository<Testcase, Integer>{

    @Query("FROM Testcase where moduleId = :moduleId and testcaseName = :testcaseName and environmentName = :environmentName and caseType = :caseType")
    Testcase queryTestcase(@Param("moduleId") Integer moduleId, @Param("testcaseName") String testcaseName, @Param("environmentName") String environmentName, @Param("caseType") Integer caseType);

    @Query("FROM Testcase where moduleId = :moduleId")
    List<Testcase> queryTestcase(@Param("moduleId") Integer moduleId);

    @Query("FROM Testcase where testcaseName = :testcaseName")
    Testcase queryTestcase(@Param("testcaseName") String testcaseName);
}
