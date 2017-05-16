package tyrant.service;

import tyrant.entity.Product;

import java.util.List;

/**
 * Created by zhangli on 9/5/2017.
 */
public interface ProductService {

    List<Product> queryProduct();

    Product queryByProductName(String productName);
}
