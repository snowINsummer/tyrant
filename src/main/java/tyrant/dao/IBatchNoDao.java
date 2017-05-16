package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.entity.BatchNo;

/**
 * Created by zhangli on 10/5/2017.
 */

@Repository
@Transactional
public interface IBatchNoDao extends JpaRepository<BatchNo, Integer>{

    @Query(value = "select t.* FROM BATCH_NO t where t.TESTCASE_ID = ?1 order by t.BATCH_NO_STR desc limit 1",nativeQuery=true)
    BatchNo getLastBatchNo(Integer testcaseId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE BatchNo set batchNoStr = ?1 , testcaseId = ?2 where id = ?3")
    void update(String batchNoStr, Integer testcaseId, Integer batchNoId);
}
