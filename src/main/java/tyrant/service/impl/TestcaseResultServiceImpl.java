package tyrant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import qa.utils.StringUtil;
import tyrant.body.QueryLastFiveDaysResult;
import tyrant.body.TestcaseInfo;
import tyrant.common.DataTransformObject.ChartModel;
import tyrant.common.DataTransformObject.LastFiveDaysResult;
import tyrant.common.DataTransformObject.SeriesModel;
import tyrant.common.constants.Constants;
import tyrant.body.SaveResultVo;
import tyrant.body.WSResult;
import tyrant.body.WSResultItem;
import tyrant.dao.ITestcaseResultDao;
import tyrant.entity.*;
import tyrant.service.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangli on 9/5/2017.
 */

@Service
public class TestcaseResultServiceImpl implements TestcaseResultService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    @Autowired
    JdbcTemplateService jdbcTemplateService;

    @Override
    public void saveResult(WSResult wsResult) {
        // TODO 保持失败原子性，在处理数据之前先验证数据是否满足要求。

        // 用例类型 ws || ui || ut
//        Integer caseType = wsResult.getCaseType();
        String productName = wsResult.getProductName();
        if (null == productName || productName.isEmpty()){
            // 默认产品：XXD
            productName = Constants.XXD;
        }
        Product product = productService.queryByProductName(productName);
        String batchNoStr = wsResult.getBatchNo();
        BatchNo batchNo = batchNoService.saveAndQuery(batchNoStr);

        SaveResultVo saveResultVo = new SaveResultVo();
//        saveResultVo.setCaseType(caseType);
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

    @Override
    public ChartModel queryLastFiveDaysResult(QueryLastFiveDaysResult queryLastFiveDaysResult) {
        logger.debug("Start queryLastFiveDaysResult...");
        ChartModel chartModel = new ChartModel();
        SeriesModel seriesModel = new SeriesModel();
        List<TestcaseInfo> testcaseInfoList =  queryLastFiveDaysResult.getTestcaseInfoList();
        for(TestcaseInfo testcaseInfo : testcaseInfoList){
            Integer testcaseId = testcaseInfo.getTestcaseId();
            String testcaseName = testcaseInfo.getTestcaseName();
            String evironment = testcaseInfo.getEvironment();
//            chartModel.addCategoryList(testcaseName);
            if (null == testcaseId){
                Testcase testcase = testcaseService.queryTestcase(testcaseName);
                if (null != testcase){
                    testcaseId = testcase.getId();
                    // TODO 改造查询方法
                    List<LastFiveDaysResult> lastFiveDaysResultList = jdbcTemplateService.queryLastFiveDaysResult(testcaseId);
                    LastFiveDaysResult lastFiveDaysResult = lastFiveDaysResultList.get(0);
//                    List<Object[]> list = iTestcaseResultDao.queryLastFiveDaysResult(testcaseId);
//                    Object[] objects = list.get(0);
                    chartModel.addCategoryList(lastFiveDaysResult.getModuleName());
//                    LastFiveDaysResult lastFiveDaysResult = new LastFiveDaysResult();
//                    lastFiveDaysResult.setModuleName(objects[0].toString());
//                    lastFiveDaysResult.setLastFiveDaysSuccess(objects[1].toString());
//                    lastFiveDaysResult.setLastFiveDaysFailure(objects[2].toString());
//                    String success = lastFiveDaysResult.getLastFiveDaysSuccess();
                    Integer success = lastFiveDaysResult.getLastFiveDaysSuccess();
                    seriesModel.addPassData(success.toString());
                    Integer failure = lastFiveDaysResult.getLastFiveDaysFailure();
//                    String failure = lastFiveDaysResult.getLastFiveDaysFailure();
                    seriesModel.addFailData(failure.toString());
                    BigDecimal bdS = new BigDecimal(success);
                    BigDecimal bdF = new BigDecimal(failure);
                    BigDecimal all = bdS.add(bdF);
                    seriesModel.addTotalData(all.toString());
                    String passRate = "--";
                    if (all.longValue() != 0){
                        String per = bdS.divide(all,5,RoundingMode.HALF_DOWN).multiply(new BigDecimal(100)).toPlainString();
//                            String per = String.valueOf(Double.valueOf(passCount)/Double.valueOf(allCount) * 100);
                        passRate = StringUtil.strNumberFormat(per, null, 2, RoundingMode.HALF_UP, StringUtil.formatStr);
                    }
                    seriesModel.addPassRateData(passRate);
                }
            }
            logger.debug("testcaseId:"+testcaseId);
            logger.debug("testcaseName:"+testcaseName);
            logger.debug("evironment:"+evironment);

        }

        chartModel.setSeries(seriesModel);
        return chartModel;
    }

}
