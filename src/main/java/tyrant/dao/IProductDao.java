package tyrant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tyrant.entity.Product;

/**
 * Created by zhangli on 8/5/2017.
 */
@Repository
@Transactional
public interface IProductDao extends JpaRepository<Product, Integer> {

    @Query("FROM Product where productName = :productName")
    Product queryByProductName(@Param("productName") String productName);
}
