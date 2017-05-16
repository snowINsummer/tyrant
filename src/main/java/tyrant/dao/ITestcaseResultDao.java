package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.entity.TestcaseResult;



/**
 * Created by zhangli on 9/5/2017.
 */
@Repository
@Transactional
public interface ITestcaseResultDao extends JpaRepository<TestcaseResult,Integer> {


}
