package tyrant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tyrant.common.entity.RspData;
import tyrant.service.ProductService;

/**
 * Created by zhangli on 8/5/2017.
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/queryProduct", method = RequestMethod.GET)
    public RspData queryProduct(){
        RspData rspData = new RspData();
        rspData.setData(productService.queryProduct());
        return rspData;
    }
}
