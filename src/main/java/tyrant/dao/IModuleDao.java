package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.entity.Module;


/**
 * Created by zhangli on 10/5/2017.
 */

@Repository
@Transactional
public interface IModuleDao extends JpaRepository<Module, Integer>{

    @Query("FROM Module where projectId = :projectId and moduleName = :moduleName")
    Module queryModule(@Param("projectId") Integer projectId, @Param("moduleName") String moduleName);

}
