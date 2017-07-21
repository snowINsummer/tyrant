package tyrant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qa.utils.DateFormat;
import qa.utils.StringUtil;
import tyrant.dao.IBatchNoDao;
import tyrant.entity.BatchNo;
import tyrant.entity.Testcase;
import tyrant.service.BatchNoService;

/**
 * Created by zhangli on 10/5/2017.
 */

@Service
public class BatchNoServiceImpl implements BatchNoService {

    @Autowired
    IBatchNoDao iBatchNoDao;

    @Override
    public BatchNo saveAndQuery(String batchNoStr) {
        BatchNo batchNo = new BatchNo();
        batchNo.setBatchNoStr(batchNoStr);
        batchNo.setCreateTime(DateFormat.getDate());
        batchNo = iBatchNoDao.save(batchNo);
        return batchNo;
    }

    @Override
    public void update(BatchNo batchNo, Testcase testcase) {
        // TODO 应该在Service中处理
//        BatchNo batchNo = iBatchNoDao.getOne(batchNo);
        Integer batchNoId = batchNo.getId();
        String batchNoStr = batchNo.getBatchNoStr();

        Integer testcaseId = testcase.getId();
        // 先根据testcaseId 查询以batchNo倒序取第一条
        BatchNo lastBatchNo = iBatchNoDao.getLastBatchNo(testcaseId);
        if (null != lastBatchNo){
            String lastBatchNoStr = lastBatchNo.getBatchNoStr();
            if (lastBatchNoStr.contains(batchNoStr)){
                if (lastBatchNoStr.indexOf("_") != -1) {
                    String str = lastBatchNoStr.split("_")[1];
                    if (!str.equals("")) {
                        try {
                            batchNoStr = batchNoStr + "_" + StringUtil.addZeroToIntStr(Integer.parseInt(str) + 1, 3);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    batchNoStr = batchNoStr + "_" + StringUtil.addZeroToIntStr(1, 3);
                }
            }
        }

//        batchNo.setBatchNoStr(batchNoStr);
//        batchNo.setListTestcaseId(testcaseId);
        iBatchNoDao.update(batchNoStr, testcaseId , batchNoId);


    }
}
