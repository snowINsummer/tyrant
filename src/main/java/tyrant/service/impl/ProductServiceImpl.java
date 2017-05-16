package tyrant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.common.constants.Constants;
import tyrant.dao.IProductDao;
import tyrant.entity.Product;
import tyrant.service.ProductService;

import java.util.List;

/**
 * Created by zhangli on 9/5/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> queryProduct() {
        return iProductDao.findAll();
    }

    @Override
    public Product queryByProductName(String productName) {
        Product product = iProductDao.queryByProductName(productName);
        if (null == product){
            product = new Product();
            product.setProductName(productName);
            product.setCreateTime(DateFormat.getDate());
            product.setIsActive(Constants.DB_DATA_ACTIVE);
            product = iProductDao.save(product);
        }
        return product;
    }
}
