package tyrant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.body.ProjectInfo;
import tyrant.common.constants.Constants;
import tyrant.common.entity.SaveResultVo;
import tyrant.common.exception.TyrantException;
import tyrant.dao.IProjectDao;
import tyrant.entity.Product;
import tyrant.entity.Project;
import tyrant.service.ProductService;
import tyrant.service.ProjectService;


/**
 * Created by zhangli on 10/5/2017.
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IProjectDao iProjectDao;
    @Autowired
    ProductService productService;

    @Override
    public Project queryProject(Integer productId, String projectName) {
        Project project = iProjectDao.queryProject(productId, projectName);
        if (null == project){
            project = new Project();
            project.setProductId(productId);
            project.setProjectName(projectName);
            project.setCreateTime(DateFormat.getDate());
            project.setIsActive(Constants.DB_DATA_ACTIVE);
            project = iProjectDao.save(project);
        }
        return project;
    }

    @Override
    public void queryProject(SaveResultVo saveResultVo) {
        // 根据 projectName productId 获取 projectId
        String projectName = saveResultVo.getProjectName();
        if (null != projectName && null == saveResultVo.getProject()){
            saveResultVo.setProject(queryProject(saveResultVo.getProduct().getId(), projectName));
        }

        if (null == projectName && null == saveResultVo.getProject()){
            logger.error("null == projectName && null == saveResultVo.getProject()");
        }

    }

    @Override
    public void save(ProjectInfo projectInfo) throws TyrantException {
        String productName = projectInfo.getProductName();
        Product product;
        if (null != productName && !productName.isEmpty()){
            product = productService.queryByProductName(productName);
        }else {
            product = productService.queryByProductName(Constants.XXD);
        }

        String projectName = projectInfo.getProjectName();
        Project project = queryProject(product.getId(), projectName);
        if (null == project){
            project = new Project();
            project.setProductId(product.getId());
            project.setProjectName(projectName);
            project.setCreateTime(DateFormat.getDate());
            project.setIsActive(Constants.DB_DATA_ACTIVE);
            iProjectDao.save(project);
        }else {
            throw new TyrantException(Constants.DB_DATA_REPETITION, projectName);
        }

    }


}
