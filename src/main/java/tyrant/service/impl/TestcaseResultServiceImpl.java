package tyrant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import tyrant.common.constants.Constants;
import tyrant.common.entity.SaveResultVo;
import tyrant.common.entity.WSResult;
import tyrant.common.entity.WSResultItem;
import tyrant.dao.ITestcaseResultDao;
import tyrant.entity.*;
import tyrant.service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangli on 9/5/2017.
 */

@Service
public class TestcaseResultServiceImpl implements TestcaseResultService {

    @Autowired
    ProductService productService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ModuleService moduleService;
    @Autowired
    TestcaseService testcaseService;
    @Autowired
    TestcaseModuleService testcaseModuleService;
    @Autowired
    TestcaseDataService testcaseDataService;
    @Autowired
    BatchNoService batchNoService;
    @Autowired
    ITestcaseResultDao iTestcaseResultDao;

    @Override
    public void saveResult(WSResult wsResult) {
        // 用例类型 ws || ui || ut
        Integer caseType = wsResult.getCaseType();
        String productName = wsResult.getProductName();
        if (null == productName || productName.isEmpty()){
            // 默认产品：XXD
            productName = Constants.XXD;
        }
        Product product = productService.queryByProductName(productName);
        String batchNoStr = wsResult.getBatchNo();
        BatchNo batchNo = batchNoService.saveAndQuery(batchNoStr);

        SaveResultVo saveResultVo = new SaveResultVo();
        saveResultVo.setCaseType(caseType);
        saveResultVo.setProduct(product);
        saveResultVo.setProjectName(wsResult.getProjectName());
        saveResultVo.setModuleName(wsResult.getModuleName());
        saveResultVo.setTestcaseName(wsResult.getTestcaseName());

        List<TestcaseResult> listTR = new ArrayList();
        List<WSResultItem> itemList  = wsResult.getItemList();
        for(WSResultItem wsResultItem : itemList){
            // 根据 projectName productId 获取 projectId
            projectService.queryProject(saveResultVo);

            // 根据 moduleName projectId 获取 moduleId
            moduleService.queryModule(saveResultVo);

            // 根据 testcaseName moduleId 获取 testcaseId
            testcaseService.queryTestcase(saveResultVo);
            // 更细batchNo相关数据
            batchNoService.update(batchNo,saveResultVo.getTestcase());

            // 根据 testcaseId testcaseModuleName 获取 testcaseModuleId
            testcaseModuleService.queryTestcaseModule(saveResultVo, wsResultItem);

            // 根据 testcaseModuleId excelNo 获取 testcaseDataId
            // TODO autotest 需要传 excelNo
            // TODO 测试数据
            testcaseDataService.queryTestcaseData(saveResultVo, wsResultItem);

            // 保存每条用例结果
            TestcaseResult testcaseResult = new TestcaseResult();
            testcaseResult.setBatchNoId(batchNo.getId());
            testcaseResult.setTestcaseDataId(saveResultVo.getTestcaseData().getId());
            testcaseResult.setStatus(wsResultItem.getStatus());
            testcaseResult.setStartTime(DateFormat.getDatafromTimeMillis(wsResultItem.getStartTime()));
            testcaseResult.setFinishTime(DateFormat.getDatafromTimeMillis(wsResultItem.getFinishTime()));
            testcaseResult.setDuration(wsResultItem.getDuration());
//            testcaseResult.setPassRate(map.get("passRate"));
            listTR.add(testcaseResult);
        }

        iTestcaseResultDao.save(listTR);
    }
}
