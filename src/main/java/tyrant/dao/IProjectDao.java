package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.entity.Project;

/**
 * Created by zhangli on 10/5/2017.
 */

@Repository
@Transactional
public interface IProjectDao extends JpaRepository<Project, Integer> {

    @Query("FROM Project where productId = :productId and projectName = :projectName")
    Project queryProject(@Param("productId") Integer productId, @Param("projectName") String projectName);
}
