package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.entity.TestcaseModule;

/**
 * Created by zhangli on 10/5/2017.
 */

@Repository
@Transactional
public interface ITestcaseModuleDao extends JpaRepository<TestcaseModule, Integer>{

    @Query("FROM TestcaseModule where testcaseId = :testcaseId and testcaseModuleName = :testcaseModuleName")
    TestcaseModule queryTestcaseModule(@Param("testcaseId") Integer testcaseId, @Param("testcaseModuleName") String testcaseModuleName);


}
