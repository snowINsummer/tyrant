package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.entity.TestcaseData;

/**
 * Created by zhangli on 10/5/2017.
 */

@Repository
@Transactional
public interface ITestcaseDataDao extends JpaRepository<TestcaseData, Integer>{

    @Query("FROM TestcaseData where testcaseModuleId = :testcaseModuleId and excelNo = :excelNo")
    TestcaseData queryTestcaseData(@Param("testcaseModuleId") Integer testcaseModuleId, @Param("excelNo") String excelNo);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE TestcaseData set caseInfo = ?1 , description = ?2 where id = ?3")
    void update(String caseInfo, String description, Integer id);
}
